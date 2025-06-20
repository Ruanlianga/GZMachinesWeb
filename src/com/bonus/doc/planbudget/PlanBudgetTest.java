package com.bonus.doc.planbudget;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.bonus.doc.docutil.ConvertUtils;
import com.bonus.doc.docutil.DocHelper;
import com.lowagie.text.Document;

public class PlanBudgetTest {

	public static void main(String[] args) throws Exception {
		SimpleDateFormat sdf=new SimpleDateFormat("hh-mm-ss");
		Document document = DocHelper.createDocument("F:/myDemo" + sdf.format(new Date()) + ".doc");
		Date dt = new Date();
		SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
		String reportMonth = matter1.format(dt);
		reportMonth = ConvertUtils.getUpperDate(reportMonth);
		String month = "2017-06";
		CoverPagePlanBudget cpp = new CoverPagePlanBudget(ConvertUtils.getCurrentYear(month) + "年1-"
				+ ConvertUtils.getCurrentMonth(month) + "月份综合计划与财务预算执行监测分析报告", "国网宿州供电公司", reportMonth);
		cpp.product(document);
		document.close();

	}
}
