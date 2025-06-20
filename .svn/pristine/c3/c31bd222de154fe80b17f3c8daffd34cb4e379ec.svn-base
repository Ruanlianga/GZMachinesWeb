package com.bonus.settlement.calc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bonus.settlement.beans.SettlementDetailsBean;

public class BalanceAgreementModelGroup {

	private Logger logger = Logger.getLogger(BalanceAgreementModelGroup.class);

	private List<SettlementDetailsBean> collars;

	private List<SettlementDetailsBean> backs;

	public BalanceAgreementModelGroup(List<SettlementDetailsBean> collars, List<SettlementDetailsBean> backs) {
		super();
		this.collars = collars;
		this.backs = backs;
	}

	public List<BalanceAgreementModelBean> groupByModelId(){
		Map<String,BalanceAgreementModelBean> maps = new HashMap<String,BalanceAgreementModelBean>();
		
		for (SettlementDetailsBean collarBean : collars) {
			String agreementCode = collarBean.getAgreementCode();
			String maModelId = collarBean.getTypeId();
			boolean isCount = collarBean.isCount();//是否计数
			String key = agreementCode;
			key += ":";
			key += maModelId;
			key += isCount;
			
			BalanceAgreementModelBean bean = maps.get(key);
			if(bean == null){
				bean = new BalanceAgreementModelBean();
				bean.setAgreementCode(agreementCode);
				bean.setMaModelId(maModelId);
				bean.setCount(isCount);//是否计数
			}
			bean.addCollar(collarBean);
			maps.put(key, bean);
		}
		
		for (SettlementDetailsBean backBean : backs) {
			String agreementCode = backBean.getAgreementCode();
			String maModelId = backBean.getTypeId();
			boolean isCount = backBean.isCount();//是否计数
			String key = agreementCode;
			key += ":";
			key += maModelId;
			key += isCount;
			
			BalanceAgreementModelBean bean = maps.get(key);
			if(bean == null){
				bean = new BalanceAgreementModelBean();
				bean.setAgreementCode(agreementCode);
				bean.setMaModelId(maModelId);
				bean.setCount(isCount);//是否计数
			}
			bean.addBack(backBean);
			maps.put(key, bean);
		}
		
		List<BalanceAgreementModelBean> beans = new ArrayList<BalanceAgreementModelBean>();
		beans.addAll(maps.values());
		
		return beans;
	}
}
