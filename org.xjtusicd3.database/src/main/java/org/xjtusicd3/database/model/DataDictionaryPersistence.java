package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_DataDictionary")
public class DataDictionaryPersistence {
	@TableKey(strategy = Strategy.NORMAL)
	@TableField(columnName="DATADICTIONARYID")
	private String DATADICTIONARYID;
	@TableField(columnName="DATANAME")
	private String DATANAME;
	@TableField(columnName="DATAVALUE")
	private String DATAVALUE;
	@TableField(columnName="REMARKS")
	private String REMARKS;
	@TableField(columnName="DATATYPE")
	private String DATATYPE;
	@TableField(columnName="DATARANGE")
	private String DATARANGE;
	public String getDATADICTIONARYID() {
		return DATADICTIONARYID;
	}
	public void setDATADICTIONARYID(String dATADICTIONARYID) {
		DATADICTIONARYID = dATADICTIONARYID;
	}
	public String getDATANAME() {
		return DATANAME;
	}
	public void setDATANAME(String dATANAME) {
		DATANAME = dATANAME;
	}
	public String getDATAVALUE() {
		return DATAVALUE;
	}
	public void setDATAVALUE(String dATAVALUE) {
		DATAVALUE = dATAVALUE;
	}
	public String getREMARKS() {
		return REMARKS;
	}
	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}
	public String getDATATYPE() {
		return DATATYPE;
	}
	public void setDATATYPE(String dATATYPE) {
		DATATYPE = dATATYPE;
	}
	public String getDATARANGE() {
		return DATARANGE;
	}
	public void setDATARANGE(String dATARANGE) {
		DATARANGE = dATARANGE;
	}

	
}
