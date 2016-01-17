package com.jantogal.document.scanning;

import com.jantogal.document.scanning.models.SectionScanSpecs;

import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;

public class PageSectionScanner {
	private int pageNumber;
	private byte[] imageBuffer;
	private SectionScanSpecs scanSpecs;
	
	public PageSectionScanner( byte[] imageBuffer, SectionScanSpecs scanSpecs,  int pageNumber ){
		this.imageBuffer = imageBuffer;
		this.scanSpecs = scanSpecs;
		this.pageNumber = pageNumber;
		validateState();
	}
	
	public byte[] getImageBuffer() {
		return imageBuffer;
	}

	public SectionScanSpecs getScanSecs() {
		return scanSpecs;
	}
	
	public Map<String, String> findText() throws TesseractException, IOException{
		validateState();
		
		Map<String, String> results = new TreeMap<String, String>();
	    
		Tesseract tesseract = new Tesseract();
	    
	    String ocrResult = readText(tesseract);
        
		results.put("A", ocrResult);
		return results;
	}

	private String readText(Tesseract tesseract) throws IOException, TesseractException {
		tesseract.setTessVariable("TESSDATA_PREFIX", "/usr/include/tesseract");
	    tesseract.setDatapath("/usr/share/tessdata");
	    tesseract.setPageSegMode(ITessAPI.TessPageSegMode.PSM_SINGLE_COLUMN);
		InputStream in = new ByteArrayInputStream(this.imageBuffer);
		BufferedImage bImageFromConvert = ImageIO.read(in);
        Rectangle r = new Rectangle(scanSpecs.getX(), scanSpecs.getY(), scanSpecs.getWidth(), scanSpecs.getHeight());
        String ocrResult = tesseract.doOCR( bImageFromConvert, r );
		return ocrResult;
	}
	private void validateState() {
		if( this.imageBuffer == null ){
			throw new IllegalStateException("Image Buffer cannot be null.");
		}
		if( this.scanSpecs == null ){
			throw new IllegalStateException("Scan Specs cannot be null.");
		}
		if( this.pageNumber < 1 ){
			throw new IllegalStateException("Page numbers must be greater than zero.");
		}
	}
}
