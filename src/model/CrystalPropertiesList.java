package model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "crystals")
public class CrystalPropertiesList {
    @XmlElement(name="crystal_properties")
    private List<CrystalProperties> crystalProperties = new ArrayList<CrystalProperties>();

	public List<CrystalProperties> getCrystalProperties() {
		return crystalProperties;
	}

	public void setCrystalProperties(List<CrystalProperties> crystalProperties) {
		this.crystalProperties = crystalProperties;
	}
}