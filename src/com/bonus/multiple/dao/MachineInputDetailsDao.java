package com.bonus.multiple.dao;


import java.util.List;

import com.bonus.core.BonusBatis;
import com.bonus.multiple.beans.MachineInputDetailsBean;
import com.bonus.sys.BaseDao;

@BonusBatis
public interface MachineInputDetailsDao extends BaseDao<MachineInputDetailsBean>{

	List<MachineInputDetailsBean> findInputDetails(MachineInputDetailsBean o);
 
}
