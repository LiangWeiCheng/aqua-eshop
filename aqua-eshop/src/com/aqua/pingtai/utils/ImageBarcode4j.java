package com.aqua.pingtai.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.DefaultConfiguration;
import org.krysalis.barcode4j.BarcodeGenerator;
import org.krysalis.barcode4j.BarcodeUtil;
import org.krysalis.barcode4j.impl.datamatrix.DataMatrix;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

public class ImageBarcode4j {
	
	public static Configuration buildCfg(String size) {
		DefaultConfiguration cfg = new DefaultConfiguration("barcode");
		DefaultConfiguration child = new DefaultConfiguration("datamatrix");
		cfg.addChild(child);
		DefaultConfiguration attr1;
		DefaultConfiguration attr;
		attr1 = new DefaultConfiguration("module-width");
		attr1.setValue(size);// 改变大小
		child.addChild(attr1);
		attr = new DefaultConfiguration("shape");
		attr.setValue("force-square");
		child.addChild(attr);
		return cfg;
	}

	public static void addChild(DefaultConfiguration parent, String attrName, Object attrValue) {
		DefaultConfiguration attr;
		attr = new DefaultConfiguration(attrName);
		if (attrValue instanceof String) {
			attr.setValue((String) attrValue);
		} else {
			attr.setValue((Integer) attrValue);
			attr.setValue((Integer) attrValue);
		}
		parent.addChild(attr);
	}

	public static void create(String path, String code) throws Exception {
		try {
			DataMatrix bean = new DataMatrix();
			BarcodeUtil util = BarcodeUtil.getInstance();
			Configuration cfg = buildCfg("1.2");
			BarcodeGenerator gen = util.createBarcodeGenerator(cfg);
			bean.configure(cfg);
			final int dpi = 130;
			File outputFile = new File(path);
			OutputStream out = new FileOutputStream(outputFile);
			try {
				BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/jpeg", dpi, BufferedImage.TYPE_BYTE_GRAY, false, 0);
				gen.generateBarcode(canvas, code);
				canvas.finish();
			} finally {
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		String aa = "xingming donghuajian dianhua 13871558042 qq 150584428 email dongcb678-163-com wangzhi www-lierenkeji-com";
		ImageBarcode4j.create("d://donghuajian.jpg", aa);
	}

}
