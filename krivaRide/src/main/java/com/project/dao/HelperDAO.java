package com.project.dao;

import java.util.List;

import com.project.entities.ConstantsEM;

public interface HelperDAO {
	
	public int getTableRowCount(String tableName);
	public List<Object> getTableData(String tableName); 
	public int saveConstants(ConstantsEM constantObj);
}
