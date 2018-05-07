package model;

import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.junit.jupiter.api.Test;

public class Unmarshalling_Test {

	@Test
	public void testUnMarshallingWithStAX() throws Exception {
	    FileReader fr = new FileReader("resources/lightsabers.xml" );
	    JAXBContext jc = JAXBContext.newInstance(LightsaberList.class);
	    Unmarshaller unmarshaller = jc.createUnmarshaller();
	    XMLInputFactory xmlif = XMLInputFactory.newInstance();
	    XMLStreamReader xmler = xmlif.createXMLStreamReader(fr);
	    LightsaberList obj = (LightsaberList)unmarshaller.unmarshal(xmler);
	}
}