
package com.madhu.ws;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SOAPProxy extends XMLGenerator {
	private Document request;
	private String methodName;
	private XPath path;

	public SOAPProxy(ServiceDescriptor desc, Document doc) throws Exception {
		super(desc);
		this.request = doc;

		path = XPathFactory.newInstance().newXPath();
		NodeList ns = (NodeList) path.evaluate("/Envelope/Body/*", request, XPathConstants.NODESET);
		if (ns.getLength() != 1) {
			throw new IllegalArgumentException("Expected 1 element in SOAP body, found " +
					ns.getLength());
		}
		methodName = ((Element) ns.item(0)).getTagName();
		if (methodName.indexOf(':') >= 0) {
			methodName = methodName.substring(methodName.indexOf(':') + 1);
		}
	}

	public SOAPProxy(ServiceDescriptor desc, InputStream inputStream) throws Exception {
		this(desc, DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream));
	}

	public Document invoke(Object object) throws XPathExpressionException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException, NotSerializableException {
		Method method = findMethod(methodName, implementation);
		if (isRestricted(method)) {
			throw new IllegalArgumentException(
					String.format("Method %s in %s is restricted", methodName, implementation.getName()));
		}
		NodeList ns = (NodeList) path.evaluate("/Envelope/Body/*/*", request, XPathConstants.NODESET);
		Object[] args = getArguments(ns, method);
		Object result = method.invoke(object, args);
		return createResponse(result);
	}

	private Method findMethod(String methodName, Class clazz) {
		Method method = null;
		Method[] methods = clazz.getDeclaredMethods();
		for (Method m : methods) {
			if (methodName.equals(m.getName())) {
				if (method != null) {
					throw new IllegalArgumentException(
						String.format("%s overloads method %s, overloaded methods not supported",
							clazz.getName(), methodName));
				}
				method = m;
			}
		}
		if (method == null) {
			throw new IllegalArgumentException(
					String.format("Method %s not found in %s", methodName, clazz.getName()));
		}
		return method;
	}

	private Object[] getArguments(NodeList methodNode, Method method) throws XPathExpressionException, NotSerializableException, IllegalAccessException, InstantiationException {
		path = XPathFactory.newInstance().newXPath();
		int n = methodNode.getLength();
		Class<?>[] parameterTypes = method.getParameterTypes();
		if (parameterTypes.length != n) {
			throw new IllegalArgumentException(String.format("Wrong number of arguments to method %s",
					method.getName()));
		}
		Object[] args = new Object[n];
		for (int i = 0; i < n; i++) {
			args[i] = deserialize(methodNode.item(i), parameterTypes[i]);
		}
		return args;
	}

	private Object deserialize(Node node, Class clazz) throws XPathExpressionException, IllegalAccessException, InstantiationException, NotSerializableException {
		if (clazz.isPrimitive()) {
			String value = node.getTextContent().trim();
			if (clazz == Boolean.TYPE) {
				return ("1".equals(value) || "true".equals(value)) ? new Boolean(true) : new Boolean(false);
			} else if (clazz == Byte.TYPE) {
				return Byte.valueOf(value);
			} else if (clazz == Short.TYPE) {
				return Short.valueOf(value);
			} else if (clazz == Integer.TYPE) {
				return Integer.valueOf(value);
			} else if (clazz == Long.TYPE) {
				return Long.valueOf(value);
			} else if (clazz == Float.TYPE) {
				return Float.valueOf(value);
			} else if (clazz == Double.TYPE) {
				return Double.valueOf(value);
			}
			throw new IllegalArgumentException("Unexpected parameter type " + clazz.getName());
		}
		Node nil = (Node) path.evaluate("@nil", node, XPathConstants.NODE);
		if (nil != null && nil.getTextContent().equals("true")) {
			return null;
		}
		if (clazz == String.class) {
			return node.getTextContent().trim();
		}
		if (clazz.isArray()) {
			Class<?> aClass = clazz.getComponentType();
			if (aClass == Byte.TYPE) {
				return Base64.base64ToBytes(node.getTextContent());
			}
			NodeList ans = (NodeList) path.evaluate("./*", node, XPathConstants.NODESET);
			int aSize = ans.getLength();
			int[] size = new int[] { aSize };
			Object array = Array.newInstance(aClass, size);
			for (int i = 0; i < aSize; i++) {
				Object value = deserialize(ans.item(i), aClass);
				Array.set(array, i, value);
			}
			return array;
		}
		// value objects must define a zero argument constructor
		Object object = clazz.newInstance();
		if (!(object instanceof Serializable)) {
			throw new NotSerializableException(clazz.getName());
		}
		do {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				if (ignoreField(field)) {
					continue;
				}
				Node fNode = (Node) path.evaluate(field.getName(), node, XPathConstants.NODE);
				field.setAccessible(true);
				field.set(object, deserialize(fNode, field.getType()));
			}
			clazz = clazz.getSuperclass();
		} while (clazz != null && clazz != Object.class);
		return object;
	}

	private Document createResponse(Object result) throws IllegalArgumentException, IllegalAccessException {
		Element env = response.createElementNS("http://schemas.xmlsoap.org/soap/envelope/", "soapenv:Envelope");
		response.appendChild(env);
		Element header = response.createElementNS("http://schemas.xmlsoap.org/soap/envelope/", "soapenv:Header");
		env.appendChild(header);
		Element body = response.createElementNS("http://schemas.xmlsoap.org/soap/envelope/", "soapenv:Body");
		env.appendChild(body);
		Element res = response.createElementNS(nameSpace, String.format("p155:%sResponse", methodName));
		body.appendChild(res);
		Element ret = response.createElementNS(nameSpace, String.format("p155:%sReturn", methodName));
		res.appendChild(ret);
		serialize(ret, result);
		return response;
	}

	private void serialize(Element parent, Object object) throws IllegalArgumentException, IllegalAccessException {
		if (object == null) {
			parent.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:nil", "true");
			return;
		}
		Class clazz = object.getClass();
		if (clazz.isArray()) {
			Class type = clazz.getComponentType();
			if (type == Byte.TYPE) {
				parent.setTextContent(Base64.bytesToBase64((byte[]) object));
				return;
			}
			String typeName;
			if (type == String.class) {
				typeName = "string";
			} else if (type == Boolean.TYPE) {
				typeName = "boolean";
			} else if (type == Short.TYPE) {
				typeName = "short";
			} else if (type == Integer.TYPE) {
				typeName = "int";
			} else if (type == Long.TYPE) {
				typeName = "long";
			} else if (type == Float.TYPE) {
				typeName = "float";
			} else if (type == Double.TYPE) {
				typeName = "double";
			} else {
				typeName = type.getSimpleName();
			}
			int n = Array.getLength(object);
			for (int i = 0; i < n; i++) {
				Element e = response.createElement(typeName);
				parent.appendChild(e);
				serialize(e, Array.get(object, i));
			}
			return;
		}
		// a small hack -- avoids checking for wrapper classes and String ;-)
		if (clazz.isPrimitive() || clazz.getPackage().getName().equals("java.lang")) {
			if (clazz == Boolean.class) {
				parent.setTextContent(object.equals(Boolean.TRUE) ? "1" : "0");
			} else {
				parent.setTextContent(object.toString());
			}
			return;
		}
		do {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				if (Modifier.isTransient(field.getModifiers())) {
					continue;
				}
				field.setAccessible(true);
				Element e = response.createElement(field.getName());
				parent.appendChild(e);
				serialize(e, field.get(object));
			}
			clazz = clazz.getSuperclass();
		} while (clazz != null && clazz != Object.class);
	}

	public String getMethodName() {
		return methodName;
	}
	
	public static void main(String[] args) throws Exception {
		String soap = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><soapenv:Header/><soapenv:Body><p558:doIt xmlns:p558=\"http://proxy.ws.inet.madhu.com\"><hand><PlayingCard><nameOfTheGame>Fizbin</nameOfTheGame><rank>Jack</rank><suit>Spades</suit></PlayingCard><PlayingCard><nameOfTheGame>52 card pickup</nameOfTheGame><rank xsi:nil=\"true\"/><suit>Diamonds</suit></PlayingCard></hand></p558:doIt></soapenv:Body></soapenv:Envelope>";
		ServiceDescriptor sd = new ServiceDescriptor("com.madhu.ws.proxy.test.TestService");
		SOAPProxy sp = new SOAPProxy(sd, new ByteArrayInputStream(soap.getBytes()));
		sp.invoke(sd.getImplementation().newInstance());
		sp.writeXMLTo(System.out);
	}
}
