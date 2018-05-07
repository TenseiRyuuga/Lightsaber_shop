package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "planet_of_origin_name",
    "crystal_price",
    "power_usage"
})
public class CrystalProperties {
	
	String planet_of_origin_name;
	int crystal_price;
	int power_usage;
	
	public String getPlanet_of_origin_name() {
		return planet_of_origin_name;
	}
	public void setPlanet_of_origin_name(String planet_of_origin_name) {
		this.planet_of_origin_name = planet_of_origin_name;
	}
	public int getCrystal_price() {
		return crystal_price;
	}
	public void setCrystal_price(int crystal_price) {
		this.crystal_price = crystal_price;
	}
	public int getPower_usage() {
		return power_usage;
	}
	public void setPower_usage(int power_usage) {
		this.power_usage = power_usage;
	}
}
