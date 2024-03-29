package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="tbl_userquestion")
public class UserQuestionPersistence
{
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="USERQUESTIONID")
	private String USERQUESTIONID;
	@TableField(columnName="QUESTIONTITLE")
	private String QUESTIONTITLE;
	@TableField(columnName="QUESTIONTIME")
	private String QUESTIONTIME;
	@TableField(columnName="ISFAQ")
	private int ISFAQ;
	@TableField(columnName="USERID")
	private String USERID;
	public String getUSERQUESTIONID()
	{
		return USERQUESTIONID;
	}
	public void setUSERQUESTIONID(String uSERQUESTIONID)
	{
		USERQUESTIONID = uSERQUESTIONID;
	}
	public String getQUESTIONTITLE()
	{
		return QUESTIONTITLE;
	}
	public void setQUESTIONTITLE(String qUESTIONTITLE)
	{
		QUESTIONTITLE = qUESTIONTITLE;
	}
	public String getQUESTIONTIME()
	{
		return QUESTIONTIME;
	}
	public void setQUESTIONTIME(String qUESTIONTIME)
	{
		QUESTIONTIME = qUESTIONTIME;
	}
	
	public int getISFAQ() {
		return ISFAQ;
	}
	public void setISFAQ(int iSFAQ) {
		ISFAQ = iSFAQ;
	}
	public String getUSERID()
	{
		return USERID;
	}
	public void setUSERID(String uSERID)
	{
		USERID = uSERID;
	} 
}
