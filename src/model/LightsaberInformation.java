package model;

import java.io.File;

public class LightsaberInformation {
	
	private String lightsaberName;
	private int padawanAge;
	private File lightsaberList;
	
	public String getLightsaberName() {
		return lightsaberName;
	}
	public void setLightsaberName(String lightsaberName) {
		this.lightsaberName = lightsaberName;
	}
	public int getPadawanAge() {
		return padawanAge;
	}
	public void setPadawanAge(int padawanAge) {
		this.padawanAge = padawanAge;
	}
	public File getLightsaberList() {
		return lightsaberList;
	}
	public void setLightsaberList(File lightsaberList) {
		this.lightsaberList = lightsaberList;
	}
}
