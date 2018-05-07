package model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "sabers")
public class LightsaberList {
    @XmlElement(name="saber")
    private List<Lightsaber> lightsaberList = new ArrayList<Lightsaber>();

    
    public List<Lightsaber> getLightsabers() {
        return lightsaberList;
    }

    public void setPersons(List<Lightsaber> lightsabers) {
        this.lightsaberList = lightsabers;
    }
}