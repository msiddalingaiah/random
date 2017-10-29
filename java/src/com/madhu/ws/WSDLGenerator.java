
package com.madhu.ws;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;

public class WSDLGenerator extends XMLGenerator {
	private HashMap<String, Element> complexTypes;
	private String serviceURL;

	public WSDLGenerator(ServiceDescriptor desc, String serviceURL) throws ParserConfigurationException {
		super(desc);
		this.serviceURL = serviceURL;
		complexTypes = new HashMap<String, Element>();
	}

	public void generateWSDL() {
		Element definitions = createElement(response, "wsdl:definitions", "http://schemas.xmlsoap.org/wsdl/");
		definitions.setAttribute("targetNamespace", nameSpace);
		definitions.setAttribute("xmlns:impl", nameSpace);
		definitions.setAttribute("xmlns:intf", nameSpace);
		definitions.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
		definitions.setAttribute("xmlns:wsdlsoap", "http://schemas.xmlsoap.org/wsdl/soap/");
		Element types = createElement(definitions, "wsdl:types");
		Element schema = createElement(types, "schema", "http://www.w3.org/2001/XMLSchema");
		schema.setAttribute("targetNamespace", nameSpace);
		Method[] methods = implementation.getDeclaredMethods();

		for (Method method : methods) {
			if (isRestricted(method)) {
				continue;
			}
			String name = method.getName();
			Element in = createElement(schema, "element");
			in.setAttribute("name", name);
			Element ctype = createElement(in, "complexType");
			Element seq = createElement(ctype, "sequence");
			Class<?>[] parameterTypes = method.getParameterTypes();
			String[] paramNames = new String[parameterTypes.length];
			for (int i = 0; i < paramNames.length; i++) {
				paramNames[i] = String.format("arg%d", i);
			}
			// use @ParameterNames("a,b") annotation if it exists
			ParameterNames ann = method.getAnnotation(ParameterNames.class);
			if (ann != null) {
				String[] as = ann.value().split(", *");
				for (int i = 0; i < paramNames.length; i++) {
					paramNames[i] = i < as.length ? as[i] : String.format("arg%d", i);
				}
			}
			for (int i = 0; i < parameterTypes.length; i++) {
				Element e = createElement(seq, "element");
				e.setAttribute("name", paramNames[i]);
				e.setAttribute("type", getXMLType(parameterTypes[i]).getType());
			}
			Element out = createElement(schema, "element");
			out.setAttribute("name", String.format("%sResponse", name));
			ctype = createElement(out, "complexType");
			seq = createElement(ctype, "sequence");
			if (method.getReturnType() != Void.TYPE) {
				Element e = createElement(seq, "element");
				e.setAttribute("name", String.format("%sReturn", name));
				e.setAttribute("type", getXMLType(method.getReturnType()).getType());
			}
			Element message = createElement(definitions, "wsdl:message");
			message.setAttribute("name", String.format("%sRequest", name));
			Element part = createElement(message, "wsdl:part");
			part.setAttribute("element", String.format("intf:%s", name));
			part.setAttribute("name", "parameters");

			message = createElement(definitions, "wsdl:message");
			message.setAttribute("name", String.format("%sResponse", name));
			part = createElement(message, "wsdl:part");
			part.setAttribute("element", String.format("intf:%sResponse", name));
			part.setAttribute("name", "parameters");
		}
		for (Element e : complexTypes.values()) {
			schema.appendChild(e);
		}

		Element port = createElement(definitions, "wsdl:portType");
		port.setAttribute("name", implementation.getSimpleName());
		for (Method method : methods) {
			if (isRestricted(method)) {
				continue;
			}
			String name = method.getName();
			Element op = createElement(port, "wsdl:operation");
			op.setAttribute("name", name);
			Element in = createElement(op, "wsdl:input");
			in.setAttribute("message", String.format("intf:%sRequest", name));
			in.setAttribute("name", String.format("%sRequest", name));
			Element out = createElement(op, "wsdl:output");
			out.setAttribute("message", String.format("intf:%sResponse", name));
			out.setAttribute("name", String.format("%sResponse", name));
		}

		Element bind = createElement(definitions, "wsdl:binding");
		bind.setAttribute("name", String.format("%sSoapBinding", implementation.getSimpleName()));
		bind.setAttribute("type", String.format("intf:%s", implementation.getSimpleName()));
		Element bind2 = createElement(bind, "wsdlsoap:binding");
		bind2.setAttribute("style", "document");
		bind2.setAttribute("transport", "http://schemas.xmlsoap.org/soap/http");
		for (Method method : methods) {
			if (isRestricted(method)) {
				continue;
			}
			String name = method.getName();
			Element op = createElement(bind, "wsdl:operation");
			op.setAttribute("name", name);
			Element op2 = createElement(op, "wsdlsoap:operation");
			op2.setAttribute("soapAction", name);

			Element in = createElement(op, "wsdl:input");
			in.setAttribute("name", String.format("%sRequest", name));
			Element body = createElement(in, "wsdlsoap:body");
			body.setAttribute("use", "literal");
			Element out = createElement(op, "wsdl:output");
			out.setAttribute("name", String.format("%sResponse", name));
			body = createElement(out, "wsdlsoap:body");
			body.setAttribute("use", "literal");
		}

		Element service = createElement(definitions, "wsdl:service");
		service.setAttribute("name", String.format("%sService", implementation.getSimpleName()));
		port = createElement(service, "wsdl:port");
		port.setAttribute("binding", String.format("intf:%sSoapBinding", implementation.getSimpleName()));
		port.setAttribute("name", implementation.getSimpleName());
		Element ws = createElement(port, "wsdlsoap:address");
		ws.setAttribute("location", serviceURL);
	}

	private XMLType getXMLType(Class<?> type) {
		if (type == String.class) {
			return new XMLType("string", "xsd:string");
		} else if (type == Boolean.TYPE) {
			return new XMLType("boolean", "xsd:boolean");
		} else if (type == Byte.TYPE) {
			return new XMLType("byte", "xsd:byte");
		} else if (type == Short.TYPE) {
			return new XMLType("short", "xsd:short");
		} else if (type == Integer.TYPE) {
			return new XMLType("int", "xsd:int");
		} else if (type == Float.TYPE) {
			return new XMLType("float", "xsd:float");
		} else if (type == Double.TYPE) {
			return new XMLType("double", "xsd:double");
		}
		if (type.isArray()) {
			type = type.getComponentType();
			if (type == Byte.TYPE) {
				return new XMLType("base64Binary", "xsd:base64Binary");
			}
			XMLType compType = getXMLType(type);
			String name = String.format("ArrayOf_%s", compType.getName());
			String typeName = String.format("impl:%s", name);
			XMLType complexType = new XMLType(name, typeName);
			if (complexTypes.get(typeName) == null) {
				Element ct = createElement("complexType");
				ct.setAttribute("name", name);
				Element seq = createElement(ct, "sequence");
				Element e = createElement(seq, "element");
				e.setAttribute("maxOccurs", "unbounded");
				e.setAttribute("minOccurs", "0");
				e.setAttribute("name", compType.getName());
				e.setAttribute("type", compType.getType());
				complexTypes.put(typeName, ct);
			}
			return complexType;
		}
		String name = type.getSimpleName();
		String typeName = String.format("impl:%s", name);
		XMLType complexType = new XMLType(name, typeName);
		if (complexTypes.get(typeName) == null) {
			Element ct = createElement("complexType");
			complexTypes.put(typeName, ct);
			ct.setAttribute("name", name);
			Element seq = createElement(ct, "sequence");
			do {
				Field[] fields = type.getDeclaredFields();
				for (Field field : fields) {
					if (ignoreField(field)) {
						continue;
					}
					XMLType fType = getXMLType(field.getType());
					Element e = createElement(seq, "element");
					e.setAttribute("name", field.getName());
					e.setAttribute("type", fType.getType());
				}
				type = type.getSuperclass();
			} while (type != null && type != Object.class);
		}
		return complexType;
	}

	public static void main(String[] args) throws Exception {
		WSDLGenerator gen = new WSDLGenerator(
				new ServiceDescriptor("com.madhu.ws.proxy.test.TestService"),
				"http://localhost:9080/WebServiceProxy/WS/TestService");
		gen.generateWSDL();
		gen.writeXMLTo(System.out);
	}
}
