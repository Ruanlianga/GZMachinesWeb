package com.bonus.doc.docutil;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.log4j.Logger;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;

public class DocHelper {

	private static final Logger logger = Logger.getLogger(DocHelper.class);
	
	public static Document createDocument(String filePath) {

		File file = new File(filePath);
		Document document = new Document(PageSize.A4, 90, 90, 70, 70);
		try {
			RtfWriter2.getInstance(document, new FileOutputStream(file));
			document.open();
		} catch (Exception e) {
			logger.error("create doc fail,reason:",e);
			return null;
		}
		return document;
	}

	public static void insertImg(Document document, String imgUrl, int alignment, float widthScale, float heightScale)
			throws Exception {
		// 添加图片
		Image img = Image.getInstance(imgUrl);

		if (img == null)
			return;
		img.setAlignment(alignment);
		img.scalePercent(widthScale, heightScale);
		document.add(img);
	}
	
	public static void insertChunks(Document document, int alignment, Chunk[] chunk) {
		Paragraph p = new Paragraph();
		p.setAlignment(alignment);
		for(int i=0;i<chunk.length;i++){
			p.add(chunk[i]);
		}		
		insert(document,p);
	}
	
	public static void insertChunks(Document document, int alignment, Chunk[] chunk,float leading) {
		Paragraph p = new Paragraph();
		p.setAlignment(alignment);
		p.setLeading(leading);
		for(int i=0;i<chunk.length;i++){
			p.add(chunk[i]);
		}		
		insert(document,p);
	}
	
	public static void insertChunk(Document document, int alignment, Chunk chunk) {
		Paragraph p = new Paragraph();
		p.setAlignment(alignment);
		p.add(chunk);
		insert(document,p);
	}
	
	public static void insertChunk(Document document, int alignment, Chunk chunk ,float leading) {
		Paragraph p = new Paragraph();
		p.setAlignment(alignment);
		p.setLeading(leading);
		p.add(chunk);
		insert(document,p);
	}
	
	private static void insert(Document document,Paragraph p){
		try {
			document.add(p);
		} catch (DocumentException e) {
			logger.error("document add Paragraph fail,reason:",e);
		}
	}

	public static void insertText(Document document, String text, int alignment, Font f) {
		Paragraph p = new Paragraph(text, f);
		p.setAlignment(alignment);
		insert(document,p);
	}
	
	public static void insertText(Document document, String text, int alignment, Font f,float leading) {
		Paragraph p = new Paragraph(text, f);
		p.setAlignment(alignment);
		p.setLeading(leading);
		insert(document,p);
	}

	public static void fillBankRow(Document document, Font f, int rowNum) throws Exception {
		for (int i = 0; i < rowNum; i++) {
			DocHelper.insertText(document, "", 1, f);
		}
	}
	
	public static Cell createHeaderCell(String text){
		Cell cell = new Cell(text);
		cell.setBackgroundColor(Color.LIGHT_GRAY);
		return cell;
	}
	

	public static Table getTable(List<VariousCountBean> beans,String tableHead,String tableBody) throws BadElementException {
		Table table = new Table(beans.size() + 1);
		table.setBorderWidthRight(25);
		table.setWidth(99);
		table.setBorderWidth(1);
		table.setBorderColor(Color.BLACK);
		table.setBackgroundColor(Color.RED);
		table.setAutoFillEmptyCells(true);
		table.setPadding(0);
		table.setSpacing(0);
		
		table.addCell(createHeaderCell(tableHead));
		for (int i = 0; i < beans.size(); i++) {
			table.addCell(createHeaderCell(beans.get(i).getUnit()));
		}
		table.addCell(tableBody);
		for (int i = 0; i < beans.size(); i++) {
			table.addCell(new Paragraph(beans.get(i).getProject_num()));
		}
		return table;
	}
	
	public static Table getTableDoc(List<DocVariousCountBean> beans,String tableHead,String tableBody) throws BadElementException {
		Table table = new Table(beans.size() + 1);
		table.setBorderWidthRight(25);
		table.setWidth(99);
		table.setBorderWidth(1);
		table.setBorderColor(Color.BLACK);
		table.setBackgroundColor(Color.RED);
		table.setAutoFillEmptyCells(true);
		table.setPadding(0);
		table.setSpacing(0);
		
		table.addCell(createHeaderCell(tableHead));
		for (int i = 0; i < beans.size(); i++) {
			table.addCell(createHeaderCell(beans.get(i).getUnit()));
		}
		table.addCell(tableBody);
		for (int i = 0; i < beans.size(); i++) {
			table.addCell(new Paragraph(beans.get(i).getAvg_time()));
		}
		return table;
	}
	
	public static String imagePathDoc(List<DocVariousCountBean> beans,String imagehead,String describe){
		if(beans==null){
			return "";
		}
		String imagehea1="\""+imagehead+"\"";
		String describe1="\""+describe+"\"";
		String untiData="\""+beans.get(0).getUnit()+"\"";
		String numData=beans.get(0).getProject_num();
		for(int i=1;i<beans.size();i++){
			untiData+=",\""+beans.get(i).getUnit()+"\"";
			numData+=","+beans.get(i).getProject_num();
		}
		
		String str1 ="{\"chartConfig\":{\"title\":{\"text\":"+imagehea1+","
				+"\"x\":\"center\"},\"legend\":{\"data\":["+describe1+"],"
				+ "\"x\":\"right\",\"y\":\"center\"},\"calculable\":true,"
				+ "\"xAxis\":[{\"type\":\"category\","
				+ "\"data\":["+untiData+"]}],"
				+ "\"yAxis\":[{\"type\":\"value\"}],"
				+ "\"series\":[{\"name\":"+describe1+",\"type\":\"bar\","
				+ "\"itemStyle\":{\"normal\":{\"label\":{\"show\":true,\"position\":\"inside\",\"textStyle\":{\"fontWeight\":\"bold\"}}}},"
				+ "\"data\":["+numData+"]}]},"
				+ "\"width\":840,\"height\":400,\"clipRect\":{\"top\":0,\"left\":10,\"width\":840,\"height\":400}}";
		
		String str2 ="{\"chartConfig\":{\"title\":{\"text\":"+imagehea1+","
				+"\"x\":\"center\"},\"legend\":{\"data\":["+describe1+"],"
				+ "\"x\":\"right\",\"y\":\"center\"},\"calculable\":true,"
				+ "\"xAxis\":[{\"type\":\"category\","
				+ "\"data\":["+untiData+"]}],"
				+ "\"yAxis\":[{\"type\":\"value\"}],"
				+ "\"series\":[{\"name\":"+describe1+",\"type\":\"bar\","
				+ "\"itemStyle\":{\"normal\":{\"label\":{\"show\":true,\"position\":\"inside\",\"textStyle\":{\"fontWeight\":\"bold\"}}}},"
				+ "\"data\":["+numData+"]}]},"
				+ "\"width\":1200,\"height\":600,\"clipRect\":{\"top\":0,\"left\":0,\"width\":1200,\"height\":600}}";
		
		if(beans.size()<=14){
			return str1;
		}else{
			return str2;
		}
		
	}
	
	
	public static String imagePathBar(List<DocVariousCountBean> beans,String imagehead,String describe){
		if(beans==null){
			return "";
		}
		//String imagehea1="\""+imagehead+"\"";
		//String describe1="\""+describe+"\"";
		//String untiData="\""+beans.get(0).getUnit()+"\"";
		//String numData=beans.get(0).getProject_num();
		//for(int i=1;i<beans.size();i++){
		//	untiData+=",\""+beans.get(i).getUnit()+"\"";
		//	numData+=","+beans.get(i).getProject_num();
		//}
		
		String str ="{\"chartConfig\":{\"title\" : {"
				+ "\"text\": \"投资计划-项目类型（宿州）\","
				+ "\"x\":\"center\""
				+ "},"
				+ "\"calculable\" : \"true\","
				+ "\"series\" : ["
				+ "{"
				+ "\"name\":\"投资计划\","
				+"\"type\":\"pie\","
				+ "\"itemStyle\": {"
				+ "\"normal\": {"
				+ "\"label\" : {"
				+ "\"show\": true, "
				+ "\"formatter\": \"{b}：{c}万元({d}%)\""
				+ "}"
				+ "}"
				+ "},"
				+ "\"radius\" : \"60%\","
				+ "\"center\": [\"50%\", \"55%\"],"
				+ "\"data\":[]"
				+ "}"
				+ "]}";
		
		return str;
	}
	
	public static String imagePath(List<VariousCountBean> beans,String imagehead,String describe){
		if(beans==null){
			return "";
		}
		String imagehea1="\""+imagehead+"\"";
		String describe1="\""+describe+"\"";
		String untiData="\""+beans.get(0).getUnit()+"\"";
		String numData=beans.get(0).getProject_num();
		for(int i=1;i<beans.size();i++){
			untiData+=",\""+beans.get(i).getUnit()+"\"";
			numData+=","+beans.get(i).getProject_num();
		}
		
		String str ="{\"chartConfig\":{\"title\":{\"text\":"+imagehea1+","
				+"\"x\":\"center\"},\"legend\":{\"data\":["+describe1+"],"
				+ "\"x\":\"right\",\"y\":\"center\"},\"calculable\":true,"
				+ "\"xAxis\":[{\"type\":\"category\","
				+ "\"data\":["+untiData+"]}],"
				+ "\"yAxis\":[{\"type\":\"value\"}],"
				+ "\"series\":[{\"name\":"+describe1+",\"type\":\"bar\","
				+ "\"itemStyle\":{\"normal\":{\"label\":{\"show\":true,\"position\":\"inside\",\"textStyle\":{\"fontWeight\":\"bold\"}}}},"
				+ "\"data\":["+numData+"]}]},"
				+ "\"width\":840,\"height\":400,\"clipRect\":{\"top\":0,\"left\":10,\"width\":840,\"height\":400}}";
		
		return str;
	}
	
	
}
