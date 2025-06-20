package com.bonus.doc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.rtf.RtfWriter2;

public abstract class AbstractReportDocProducer {

	protected Logger logger = Logger.getLogger(AbstractReportDocProducer.class);

	public boolean product(String file,HttpServletRequest request) throws Exception {
		File f = new File(file);
		FileOutputStream fos = new FileOutputStream(f);
		return product(fos,request);
	}
	
	public boolean product(OutputStream os,HttpServletRequest request) {

		Document document = new Document(PageSize.A4, 90, 90, 70, 70);// top
		try {
			RtfWriter2 rtfWriter2 = RtfWriter2.getInstance(document, os);
			rtfWriter2.getDocumentSettings().setImageWrittenAsBinary(false);
			document.open();
			product(document,request);
			document.close();
			return true;
		} catch (Exception e) {
			logger.error("gen doc error,",e);
			return false;
		}
	}

	public abstract boolean product(Document document,HttpServletRequest request);

}
