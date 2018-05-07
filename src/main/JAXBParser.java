package main;

import java.io.File;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.junit.jupiter.api.Test;

import model.CrystalPropertiesList;
import model.LightsaberList;

public class JAXBParser {
	
	public LightsaberList unmarshalLightsabersXML(String fileLocation) throws Exception {
		return unmarshalLightsabersXML(new File(fileLocation));
	}

	// unMarshallingWithStAX
	// for lightsabers
	LightsaberList unmarshalLightsabersXML(File file) throws Exception {
	    FileReader fr = new FileReader(file);
	    JAXBContext jc = JAXBContext.newInstance(LightsaberList.class);
	    Unmarshaller unmarshaller = jc.createUnmarshaller();
	    XMLInputFactory xmlif = XMLInputFactory.newInstance();
	    XMLStreamReader xmler = xmlif.createXMLStreamReader(fr);
	    LightsaberList lightsaberList = (LightsaberList)unmarshaller.unmarshal(xmler);
	    return lightsaberList;
	}
	
	// for crystals
	CrystalPropertiesList unmarshalCrystalsPropertiesXML(String fileLocation) throws Exception {
		return unmarshalCrystalsPropertiesXML(new File(fileLocation));
	}

	private CrystalPropertiesList unmarshalCrystalsPropertiesXML(File file) throws Exception {
	    FileReader fr = new FileReader(file);
	    JAXBContext jc = JAXBContext.newInstance(CrystalPropertiesList.class);
	    Unmarshaller unmarshaller = jc.createUnmarshaller();
	    XMLInputFactory xmlif = XMLInputFactory.newInstance();
	    XMLStreamReader xmler = xmlif.createXMLStreamReader(fr);
	    CrystalPropertiesList crystalPropertiesList = (CrystalPropertiesList)unmarshaller.unmarshal(xmler);
	    return crystalPropertiesList;
	}
}