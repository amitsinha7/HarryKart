package se.atg.service.harrykart.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.Objects;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import se.atg.service.harrykart.exception.HKException;
import se.atg.service.harrykart.model.HarryKart;

@Component
public class XMLValidatorDeserialize {

	private static final String SCHEMA_FILE = "input.xsd";

	public HarryKart validateAndDeserializeXML(String xmlString, Errors validationErrors) throws HKException {

		HarryKart hk = null;
		try {
			if (!validate(xmlString)) {
				validationErrors.reject("110002");
			}
			XmlMapper xmlMapper = new XmlMapper();
			hk = xmlMapper.readValue(xmlString, HarryKart.class);
		} catch (IOException e) {
			System.out.println(e);
			throw new HKException("110003");
		}
		return hk;
	}

	private boolean validate(String inputXML) {
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {
			Schema schema = schemaFactory.newSchema(new File(getResource(SCHEMA_FILE)));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new StringReader(inputXML)));
			return true;
		} catch (SAXException | IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private String getResource(String filename) throws FileNotFoundException {
		URL resource = this.getClass().getClassLoader().getResource(filename);
		Objects.requireNonNull(resource);
		return resource.getFile();
	}

}
