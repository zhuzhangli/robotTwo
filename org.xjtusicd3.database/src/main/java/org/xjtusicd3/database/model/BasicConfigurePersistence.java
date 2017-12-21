package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_BasicConfigure")
public class BasicConfigurePersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="BASICCONFIGUREID")
	private String BASICCONFIGUREID;
	@TableField(columnName="CONFIGUREID")
	private String CONFIGUREID;
	@TableField(columnName="DEPARTMENTID")
	private String DEPARTMENTID;
	public String getBASICCONFIGUREID() {
		return BASICCONFIGUREID;
	}
	public void setBASICCONFIGUREID(String bASICCONFIGUREID) {
		BASICCONFIGUREID = bASICCONFIGUREID;
	}
	public String getCONFIGUREID() {
		return CONFIGUREID;
	}
	public void setCONFIGUREID(String cONFIGUREID) {
		CONFIGUREID = cONFIGUREID;
	}
	public String getDEPARTMENTID() {
		return DEPARTMENTID;
	}
	public void setDEPARTMENTID(String dEPARTMENTID) {
		DEPARTMENTID = dEPARTMENTID;
	}
	
	
	
	
}
