package com.bonus.doc.planbudget;

import java.awt.Color;

import com.bonus.doc.docutil.DocHelper;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.rtf.style.RtfFont;

public class CoverPagePlanBudget {
	
	private String title;

	private String department;

	private String time;

	public CoverPagePlanBudget(String title, String department, String time) {
		super();
		this.title = title;
		this.department = department;
		this.time = time;
	}

	public void product(Document document) throws Exception {

		Font f = new RtfFont("黑 体", 24f, Font.NORMAL, Color.BLACK);// 小一号
		// 添加空格
		DocHelper.fillBankRow(document, f, 4);
		// 添加大标题
		insertText(document, title, 1, "黑 体", 24f, Font.NORMAL, Color.BLACK);//小 一号
		// 添加空格
		DocHelper.fillBankRow(document, f, 35);
		insertText(document, department, 1, "仿宋_GB2312", 18f, Font.NORMAL, Color.BLACK);// 小二
		insertText(document, time, 1, "仿宋_GB2312", 18f, Font.NORMAL, Color.BLACK);// 小二
		// 添加空格
		DocHelper.fillBankRow(document, f, 3);
		/* 封面加载完毕 */
	}

	protected void insertText(Document document, String text, int alignment, String fontType, float fontSize, int b,
			Color c) {
		Font f = new RtfFont(fontType, fontSize, b, c);
		DocHelper.insertText(document, text, alignment, f);
	}
	
}
