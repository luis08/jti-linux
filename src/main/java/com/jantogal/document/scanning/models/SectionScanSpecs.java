package com.jantogal.document.scanning.models;

import java.util.Map;

public class SectionScanSpecs {
	private String id;
	private int x;
	private int y;
	private int width;
	private int height;
	private Map<String, String> patterns;
	
	public String getId(){
		return this.id;
	}
	public void setId( String id ){
		this.id = id;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Map<String, String> getPatterns() {
		return patterns;
	}
	public void setPatterns(Map<String, String> patterns) {
		this.patterns = patterns;
	}
}
