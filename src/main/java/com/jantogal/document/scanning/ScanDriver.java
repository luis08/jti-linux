package com.jantogal.document.scanning;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import com.jantogal.document.scanning.models.SectionScanSpecs;

import net.sourceforge.tess4j.TesseractException;

public class ScanDriver {
	public static void main(String[] args) throws IOException {
		String path = "/home/luis/Documents/Galvez/invoices/FT0000052.pdf";

		byte[] imageBuffer = getFileBytes(path);
		SectionScanSpecs scanSpecs = new SectionScanSpecs();

		scanSpecs.setX(100);
		scanSpecs.setY(100);
		scanSpecs.setHeight(100);
		scanSpecs.setWidth(100);

		PageSectionScanner s = new PageSectionScanner(imageBuffer, scanSpecs, 1);
		try {
			Map<String, String> t = s.findText();
			printMap(t);

		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void printMap(Map<String, String> t) {
		if (t.isEmpty()) {
			System.out.println("It's empty");
		} else {
			t.entrySet().stream().forEach(e ->{
				System.out.println(String.format("Key:[%s]         Value:[%s]", e.getKey(), e.getValue()));
			});
		}
		
	}

	private static byte[] getFileBytes(String pdfPath) throws IOException {
		File file = new File(pdfPath);

		if (!file.exists()) {
			throw new IllegalArgumentException("The path to the image is not valid");
		}

		Path path = Paths.get(pdfPath);
		byte[] imageBuffer = Files.readAllBytes(path);
		return imageBuffer;
	}
}
