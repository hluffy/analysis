package com.dk.object;

public class IBeacon {
	private String Uuid;
	private Integer Maior;
	private Integer Minor;
	private Integer Rssi;
	private Integer Power;
	private Integer Bat;
	
	public IBeacon(){
		
	}
	public IBeacon(String Uuid,Integer Maior,Integer Minor,Integer Rssi,Integer Power,Integer Bat){
		this.Uuid = Uuid;
		this.Maior = Maior;
		this.Minor = Minor;
		this.Rssi = Rssi;
		this.Power = Power;
		this.Bat = Bat;
	}
	public String getUuid() {
		return Uuid;
	}
	public void setUuid(String uuid) {
		Uuid = uuid;
	}
	public Integer getMaior() {
		return Maior;
	}
	public void setMaior(Integer maior) {
		Maior = maior;
	}
	public Integer getMinor() {
		return Minor;
	}
	public void setMinor(Integer minor) {
		Minor = minor;
	}
	public Integer getRssi() {
		return Rssi;
	}
	public void setRssi(Integer rssi) {
		Rssi = rssi;
	}
	public Integer getPower() {
		return Power;
	}
	public void setPower(Integer power) {
		Power = power;
	}
	public Integer getBat() {
		return Bat;
	}
	public void setBat(Integer bat) {
		Bat = bat;
	}
	

}
