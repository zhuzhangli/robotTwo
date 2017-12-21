package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_Department")
public class DepartmentPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="DEPARTMENTID")
	private String DEPARTMENTID;	
	@TableField(columnName="DEPARTMENTNAME")
	private String DEPARTMENTNAME;
	public String getDEPARTMENTID() {
		return DEPARTMENTID;
	}
	public void setDEPARTMENTID(String dEPARTMENTID) {
		DEPARTMENTID = dEPARTMENTID;
	}
	public String getDEPARTMENTNAME() {
		return DEPARTMENTNAME;
	}
	public void setDEPARTMENTNAME(String dEPARTMENTNAME) {
		DEPARTMENTNAME = dEPARTMENTNAME;
	}
	
	
}
