package se.atg.service.harrykart.test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.Errors;

import se.atg.service.harrykart.exception.HKException;
import se.atg.service.harrykart.helper.XMLValidatorDeserialize;
import se.atg.service.harrykart.model.RaceResult;
import se.atg.service.harrykart.response.RaceResultDTO;
import se.atg.service.harrykart.response.ResponeMapper;
import se.atg.service.harrykart.service.HarryKartService;

public class HarryKartTest {

	XMLValidatorDeserialize validatorDeserialize = new XMLValidatorDeserialize();
	HarryKartService harryKartService = new HarryKartService();
	ResponeMapper responeMapper = new ResponeMapper();

	@Before
	public void setUp() throws Exception {
		ReflectionTestUtils.setField(harryKartService, "trackLength", 1000.0);
	}

	@Test
	public void testInput0XmlHarryKart() throws HKException {
		String inputXML = readFileToString("input_0.xml");
		Errors validationErrors = null;
		ArrayList<RaceResultDTO> topThreePostions = responeMapper.getTopThreePostions(harryKartService
				.getResults(validatorDeserialize.validateAndDeserializeXML(inputXML, validationErrors)));
		assertEquals("TIMETOBELUCKY", topThreePostions.get(0).getHorse());
		assertEquals("HERCULES BOKO", topThreePostions.get(1).getHorse());
		assertEquals("CARGO DOOR", topThreePostions.get(2).getHorse());
	}

	@Test
	public void testInput1XmlHarryKart() throws HKException {
		String inputXML = readFileToString("input_1.xml");
		Errors validationErrors = null;
		ArrayList<RaceResultDTO> topThreePostions = responeMapper.getTopThreePostions(harryKartService
				.getResults(validatorDeserialize.validateAndDeserializeXML(inputXML, validationErrors)));
		assertEquals("WAIKIKI SILVIO", topThreePostions.get(0).getHorse());
		assertEquals("TIMETOBELUCKY", topThreePostions.get(1).getHorse());
		assertEquals("HERCULES BOKO", topThreePostions.get(2).getHorse());
	}

	@Test
	public void speedToZeroXmlHarryKart() throws HKException {
		String inputXML = readFileToString("speedtozero.xml");
		Errors validationErrors = null;
		ArrayList<RaceResult> results = harryKartService
		.getResults(validatorDeserialize.validateAndDeserializeXML(inputXML, validationErrors));
		assertEquals(3, results.size());
	}
	
	@Test
	public void samePowerUpXmlHarryKart() throws HKException {
		String inputXML = readFileToString("samepowerup.xml");
		Errors validationErrors = null;
		ArrayList<RaceResultDTO> topThreePostions = responeMapper.getTopThreePostions(harryKartService
				.getResults(validatorDeserialize.validateAndDeserializeXML(inputXML, validationErrors)));
		assertEquals(1, topThreePostions.get(0).getPosition());
		assertEquals(1, topThreePostions.get(1).getPosition());
		assertEquals(1, topThreePostions.get(2).getPosition());
		assertEquals(1, topThreePostions.get(3).getPosition());
	}
	
	private String readFileToString(String filename) throws HKException {

		String xmlString = "";
		try {
			InputStream input = this.getClass().getClassLoader().getResourceAsStream(filename);
			if (input != null) {
				BufferedReader br = new BufferedReader(new InputStreamReader(input));
				xmlString = br.lines().collect(Collectors.joining(System.lineSeparator()));
			} else {
				throw new HKException("110001");
			}
		} catch (Exception e) {
			System.out.println(e);
			throw new HKException("110001");
		}
		return xmlString;
	}

}
