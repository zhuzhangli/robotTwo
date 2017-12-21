package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_TimeStamp")
public class TimeStampPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="TIMEID")
	private String TIMEID;
	@TableField(columnName="NAME")
	private String NAME;
	@TableField(columnName="TIME")
	private String TIME;
	@TableField(columnName="BEGINTIME")
	private String BEGINTIME;
	public String getTIMEID() {
		return TIMEID;
	}
	public void setTIMEID(String tIMEID) {
		TIMEID = tIMEID;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getTIME() {
		return TIME;
	}
	public void setTIME(String tIME) {
		TIME = tIME;
	}
	public String getBEGINTIME() {
		return BEGINTIME;
	}
	public void setBEGINTIME(String bEGINTIME) {
		BEGINTIME = bEGINTIME;
	}
	
	
}
