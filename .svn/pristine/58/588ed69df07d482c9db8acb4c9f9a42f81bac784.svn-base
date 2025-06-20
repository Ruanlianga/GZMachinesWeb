package com.bonus.settlement.calc;

import java.util.Comparator;

import com.bonus.settlement.beans.SettlementDetailsBean;

public class BackDateComparator implements Comparator<SettlementDetailsBean> {

	@Override
	public int compare(SettlementDetailsBean d1, SettlementDetailsBean d2) {

		int r = d1.getReturnDate().compareTo(d2.getReturnDate());
		
		return r;
	}

}
