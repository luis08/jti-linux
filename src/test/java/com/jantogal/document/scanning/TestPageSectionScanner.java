package com.jantogal.document.scanning;

import org.junit.Test;

import com.jantogal.document.scanning.models.SectionScanSpecs;

public class TestPageSectionScanner {

	private static final int PAGE_NUMBER_ONE = 1;
	
	@Test(expected = IllegalStateException.class)
	public void testNoNullImage(){
		byte[] nullImageBuffer = null;
		new PageSectionScanner( nullImageBuffer, new SectionScanSpecs(),PAGE_NUMBER_ONE);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testNoNullScanSpecs(){
		byte[] imageBuffer = new byte[0]; 
		SectionScanSpecs nullSectionScanSpecs = null;
		new PageSectionScanner( imageBuffer, nullSectionScanSpecs, PAGE_NUMBER_ONE );
	}
}
