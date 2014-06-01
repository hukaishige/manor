package com.startup.app.analytic.message.from;

import java.util.Map;

import com.startup.app.analytic.message.BaseMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class LocationMessageFrom extends BaseMessage {
	public final static String LOCATION_X ="Location_X";
	public final static String LOCATION_Y ="Location_Y";
	public final static String SCALE ="Scale";
	public final static String LABEL ="Label";
	
	@XStreamAlias(LOCATION_X)
	private double locationX;
	
	@XStreamAlias(LOCATION_Y)
	private double locationY;
	
	@XStreamAlias(SCALE)
	private long scale;

	@XStreamAlias(LABEL)
	private String label;
	
	public double getLocationX() {
		return locationX;
	}

	public void setLocationX(double locationX) {
		this.locationX = locationX;
	}

	public double getLocationY() {
		return locationY;
	}

	public void setLocationY(double locationY) {
		this.locationY = locationY;
	}

	public long getScale() {
		return scale;
	}

	public void setScale(long scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	@Override
	public void deserialize(Map<String, String> map) {
		super.deserialize(map);
		
		locationX = Double.parseDouble(map.get(LOCATION_X));
		locationY = Double.parseDouble(map.get(LOCATION_Y));
		scale = Long.parseLong(map.get(SCALE));
		label = map.get(LABEL);
	}
}
