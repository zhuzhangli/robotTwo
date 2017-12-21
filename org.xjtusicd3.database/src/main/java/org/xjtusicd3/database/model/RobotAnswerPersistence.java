package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_RobotAnswer")
public class RobotAnswerPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="ROBOTANSWERID")
	private String ROBOTANSWERID;
	@TableField(columnName="SATICFACTION")
	private int SATICFACTION;
	@TableField(columnName="USERQUESTIONID")
	private String USERQUESTIONID;
	@TableField(columnName="FAQANSWERID")
	private String FAQANSWERID;
	@TableField(columnName="QUESTIONSTATE")
	private int QUESTIONSTATE;
	
	
	
	
	public int getQUESTIONSTATE() {
		return QUESTIONSTATE;
	}
	public void setQUESTIONSTATE(int qUESTIONSTATE) {
		QUESTIONSTATE = qUESTIONSTATE;
	}
	public String getROBOTANSWERID() {
		return ROBOTANSWERID;
	}
	public void setROBOTANSWERID(String rOBOTANSWERID) {
		ROBOTANSWERID = rOBOTANSWERID;
	}
	public int getSATICFACTION() {
		return SATICFACTION;
	}
	public void setSATICFACTION(int sATICFACTION) {
		SATICFACTION = sATICFACTION;
	}
	public String getUSERQUESTIONID() {
		return USERQUESTIONID;
	}
	public void setUSERQUESTIONID(String uSERQUESTIONID) {
		USERQUESTIONID = uSERQUESTIONID;
	}
	public String getFAQANSWERID() {
		return FAQANSWERID;
	}
	public void setFAQANSWERID(String fAQANSWERID) {
		FAQANSWERID = fAQANSWERID;
	}
	
	
}
