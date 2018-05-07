package main;

import java.io.File;

import SQL_Database.LightsaberDB_Queries;
import model.CrystalProperties;
import model.CrystalPropertiesList;
import model.LightsaberInformation;
import model.Lightsaber;
import model.LightsaberList;

public class main {

	private static LightsaberDB_Queries db = new LightsaberDB_Queries();

	public static void main(String[] args) throws Exception {
		args = new String[] {"resources/lightsabers.xml", "10", "Master Jedi Saber"};
		if(args.length == 3) {
			LightsaberInformation desiredLightsaber = new LightsaberInformation();
			desiredLightsaber.setLightsaberList(new File(args[0]));
			desiredLightsaber.setPadawanAge(Integer.parseInt(args[1]));
			desiredLightsaber.setLightsaberName(args[2]);

			//load in crystal properties
			loadCrystalProperties();
			
			//load in lightsaberList
			loadLightsabers(desiredLightsaber);
			
			

			printResult(desiredLightsaber);
		}
	}

	private static void printResult(LightsaberInformation desiredLightsaber) {
		System.out.println("F = " + desiredLightsaber.getPadawanAge() * 10);
		System.out.println("");
		System.out.println("Jedi power usage:");
		System.out.println("	F needed = " + 22 + "F");
		System.out.println("Crystal detail:");
		System.out.println("	Crystal type: " + "Dagobah" + " crystal");
		System.out.println("	Price: " + 37 * 22);
	}

	private static void loadCrystalProperties() throws Exception {
		JAXBParser parser = new JAXBParser();
		CrystalPropertiesList crystalPropertiesList = parser.unmarshalCrystalsPropertiesXML("resources/crystal_properties.xml");
		for(CrystalProperties cp: crystalPropertiesList.getCrystalProperties()) {
			db.createCrystalPropertiesEntry(cp);
		}
		
	}
	
	private static void loadLightsabers(LightsaberInformation desiredLightsaber) throws Exception {
		JAXBParser parser = new JAXBParser();
		LightsaberList lightsaberList = parser.unmarshalLightsabersXML(desiredLightsaber.getLightsaberList());
		for(Lightsaber lightsaber: lightsaberList.getLightsabers()) {
			db.createLightSaberEntry(lightsaber);
		}
		
	}
}
