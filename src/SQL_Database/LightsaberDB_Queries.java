package SQL_Database;

import java.sql.ResultSet;
import java.sql.SQLException;

import constants.Constant;
import model.Crystal;
import model.CrystalProperties;
import model.Lightsaber;

public class LightsaberDB_Queries extends LightsaberDB_DAO {

	public LightsaberDB_Queries() {
		super();
	}

	public void create(String table, String columnNames, String values) {
		super.create(table + " (" + columnNames + ") VALUES (" + values + ")");
	}

	public ResultSet read(String table, String columnNames, String conditions) {
		if (!conditions.isEmpty()) {
			return super.read(columnNames + " FROM " + table + " WHERE " + conditions);
		} 
		else {
			return super.read(columnNames + " FROM " + table);
		}
	}

	public void update(String table, String changes, String conditions) {
		if (!conditions.isEmpty()) {
			super.update(table + " set " + changes + " WHERE " + conditions);
		}
		else {
			super.update(table + " set " + changes);
		}
	}

	public void delete(String table, String conditions) {
		if (!conditions.isEmpty()) {
			super.delete(table + " WHERE " + conditions);
		}
		else {
			super.delete(table);
		}
	}

	public void read(String table, String columnNames) {
		read(table, columnNames, "");
	}

	public void update(String table, String changes) {
		update(table, changes, "");
	}

	public void delete(String table) {
		delete(table, "");
	}

	//####################################################################

	//Specific queries
	public void createLightSaberEntry(Lightsaber lightsaber) {
		createLightsaberCrystalEntry(lightsaber.getCrystal());
		createLightsaberEntry(lightsaber);
	}

	//TODO create a better way to make queries
	private void createLightsaberEntry(Lightsaber lightsaber) {
		if(!isDuplicateLightsaber(lightsaber)) {
			create(Constant.TABLE_LIGHTSABER, "given_ID, name, available, crystal_color", lightsaber.getId() + ", " + "\'" + lightsaber.getName() + "\'" + ", " + lightsaber.getAvailable() + ", " + "\'" + lightsaber.getCrystal().getColor() + "\'");
		}
	}

	private void createLightsaberCrystalEntry(Crystal crystal) {
		if(!isDuplicateCrystal(crystal)) {
			create(Constant.TABLE_CRYSTAL, "crystal_color, planet_of_origin_name", "\'" + crystal.getColor() + "\'" + ", " + "\'" + determineCrystalOriginPlanetName(crystal.getColor()) + "\'");
		}
	}

	public void createCrystalPropertiesEntry(CrystalProperties crystalProperties) {
		if(!isDuplicateCrystalProperties(crystalProperties)) {
			create(Constant.TABLE_CRYSTALS_PROPERTIES, "planet_of_origin_name, crystal_price, power_usage", "\'" + crystalProperties.getPlanet_of_origin_name() + "\'" + ", " + crystalProperties.getCrystal_price() + ", " + crystalProperties.getPower_usage());
		}
	}

	private boolean isDuplicate(String table, String columnNames, String conditions) {
		ResultSet rs = read(table, columnNames, conditions);
		try {
			if(rs != null) {
				return rs.isBeforeFirst();
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean isDuplicateCrystalProperties(CrystalProperties crystalProperties) {
		return isDuplicate(Constant.TABLE_CRYSTALS_PROPERTIES, "*", "planet_of_origin_name = " + "\'" + crystalProperties.getPlanet_of_origin_name() + "\'" + " AND crystal_price = " + crystalProperties.getCrystal_price() + " AND power_usage = " + crystalProperties.getPower_usage());
	}

	private boolean isDuplicateCrystal(Crystal crystal) {
		return isDuplicate(Constant.TABLE_CRYSTAL, "*", "crystal_color = " + "\'" + crystal.getColor().toUpperCase() + "\'" + " AND planet_of_origin_name = " + "\'" + determineCrystalOriginPlanetName(crystal.getColor()) + "\'");
	}

	private boolean isDuplicateLightsaber(Lightsaber lightsaber) {
		return isDuplicate(Constant.TABLE_LIGHTSABER, "*", "given_ID = " + "\'" + lightsaber.getId() + "\'" + " AND name = " + "\'" + lightsaber.getName() + "\'" + " AND crystal_color = " + "\'" + lightsaber.getCrystal().getColor().toUpperCase() + "\'");
	}

	private String determineCrystalOriginPlanetName(String crystalColor) {
		switch (crystalColor.toUpperCase()) {
		case "RED":
			return "Ilum";
		case "BLUE":
			return "Dantooine";
		case "GREEN":
			return "Dagobah";
		default:
			return "Unknown";
		}
	}
}
