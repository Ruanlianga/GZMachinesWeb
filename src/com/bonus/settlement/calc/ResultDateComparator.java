package com.bonus.settlement.calc;

import java.util.Comparator;

public class ResultDateComparator implements Comparator<BalanceAgreementModelResultBean> {

	@Override
	public int compare(BalanceAgreementModelResultBean d1, BalanceAgreementModelResultBean d2) {

		int r = d1.getLeaseDate().compareTo(d2.getLeaseDate());
		
		if(r > 1) {
			return d1.getReturnDate().compareTo(d2.getReturnDate());
		}
		
		return r;
	}

}
