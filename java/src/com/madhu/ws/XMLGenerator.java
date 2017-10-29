
package com.madhu.ws;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XMLGenerator {
	protected Document response;
	protected Class implementation;
	protected String nameSpace;

	public XMLGenerator(ServiceDescriptor desc) throws ParserConfigurationException {
		implementation = desc.getImplementation();
		nameSpace = desc.getNameSpace();
		response = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
	}

	protected Element createElement(Node parent, String tag, String ns) {
		Element e = createElement(tag, ns);
		parent.appendChild(e);
		return e;
	}

	protected Element createElement(Element parent, String tag) {
		Element e = createElement(tag);
		parent.appendChild(e);
		return e;
	}

	protected Element createElement(String tag, String ns) {
		return response.createElementNS(ns, tag);
	}

	protected Element createElement(String tag) {
		return response.createElement(tag);
	}
	
	public Document getResponse() {
		return response;
	}
	
	/**
	 * Writes XML response to an OutputStream
	 * 
	 * @param os Stream to write to
	 * @throws TransformerException
	 */
	public void writeXMLTo(OutputStream os) throws TransformerException {
		writeXMLTo(response, os);
	}

	/**
	 * Writes XML to an OutputStream
	 * 
	 * @param node The XML root node to write, can be a Document or Element
	 * @param os Stream to write to
	 * @throws TransformerException
	 */
	public void writeXMLTo(Node node, OutputStream os) throws TransformerException {
		TransformerFactory xformFactory = TransformerFactory.newInstance();  
		Transformer idTransform = xformFactory.newTransformer();
		Source input = new DOMSource(node);
		Result output = new StreamResult(os);
		idTransform.transform(input, output);
	}

	/**
	 * Determine if a method cannot be exposed as a web service (restricted)
	 * 
	 * @param method The method to (potentially) expose
	 * @return true if the given method cannot be exposed (is restricted)
	 */
	public boolean isRestricted(Method method) {
		String name = method.getName();
		return name.equals("initInetEnvironment") || name.equals("getAuthorizor") ||
			!Modifier.isPublic(method.getModifiers());
	}

	/**
	 * Determine if a field should not be serialized (ignored)
	 * 
	 * @param field the field to test
	 * @return true if the given field should not be serialized (ignored)
	 */
	public boolean ignoreField(Field field) {
		int mod = field.getModifiers();
		return Modifier.isTransient(mod) || Modifier.isStatic(mod);
	}
}
