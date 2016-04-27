package com.example.quentefrio;

import java.io.Serializable;

public class Dados implements Serializable {
	private static final long serialVersionUID = -6708264038669036552L;

	double latitude, longitude;

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}