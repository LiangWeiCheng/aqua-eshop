package com.aqua.office.poi.ppt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.SlideShow;

public class SlideUtil {
	
	public static SlideShow dealSlide(String input, SlideModel slideModel) throws Exception {
		HSLFSlideShow hslfSlideShow = new HSLFSlideShow(input);
		SlideShow slideShow = new SlideShow(hslfSlideShow);
		Slide slide = slideShow.getSlides()[0];
		TextRun[] textRuns = slide.getTextRuns();
		String depart = textRuns[3].getRichTextRuns()[0].getText();
		String lineSeparator = depart.substring(depart.length()-1,depart.length());
		Method setValueMethod = slideModel.getClass().getMethod("setValue", new Class[]{Integer.class,TextRun.class,slideModel.getClass(),String.class});
		for (int i = 0; i < textRuns.length; i++) {
			setValueMethod.invoke(slideModel, new Object[]{i,textRuns[i],slideModel,lineSeparator});
//			setValue(i,textRuns[i],slideModel,lineSeparator);
		}
		return slideShow;
	}
	
	public static void writeSlide(SlideShow slideShow, String filePath) throws FileNotFoundException, IOException{
		slideShow.write(new FileOutputStream(new File(filePath)));
	}

}
