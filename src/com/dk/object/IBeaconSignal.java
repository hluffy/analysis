package com.dk.object;

public class IBeaconSignal {
	private String uuid;
	private Integer power;
	private Integer rssi;
	private Integer maior;
	private Integer minor;
	private Integer bat;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Integer getPower() {
		return power;
	}
	public void setPower(Integer power) {
		this.power = power;
	}
	public Integer getRssi() {
		return rssi;
	}
	public void setRssi(Integer rssi) {
		this.rssi = rssi;
	}
	public Integer getMaior() {
		return maior;
	}
	public void setMaior(Integer maior) {
		this.maior = maior;
	}
	public Integer getMinor() {
		return minor;
	}
	public void setMinor(Integer minor) {
		this.minor = minor;
	}
	public Integer getBat() {
		return bat;
	}
	public void setBat(Integer bat) {
		this.bat = bat;
	}
	

}
