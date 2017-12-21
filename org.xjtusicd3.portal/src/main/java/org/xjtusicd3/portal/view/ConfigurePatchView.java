package org.xjtusicd3.portal.view;

public class ConfigurePatchView {
	//配置整体信息
	private String CONFIGUREID;
	private String CONFIGURETYPE;
	private String CONFIGURENAME;
	private String FILESIZE;
	private String URL;

	private String DOWNLOADTIMES;
	private String PRODUCER;
	
	private String CONFIGURETIME;
	private int ISCONFIGURE;
	
	//补丁特有
	private String OS;
	private String LANGUAGE;
	private String PATCHINTRODUCTION;
	public String getCONFIGUREID() {
		return CONFIGUREID;
	}
	public void setCONFIGUREID(String cONFIGUREID) {
		CONFIGUREID = cONFIGUREID;
	}
	public String getCONFIGURETYPE() {
		return CONFIGURETYPE;
	}
	public void setCONFIGURETYPE(String cONFIGURETYPE) {
		CONFIGURETYPE = cONFIGURETYPE;
	}
	public String getCONFIGURENAME() {
		return CONFIGURENAME;
	}
	public void setCONFIGURENAME(String cONFIGURENAME) {
		CONFIGURENAME = cONFIGURENAME;
	}
	public String getFILESIZE() {
		return FILESIZE;
	}
	public void setFILESIZE(String fILESIZE) {
		FILESIZE = fILESIZE;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getDOWNLOADTIMES() {
		return DOWNLOADTIMES;
	}
	public void setDOWNLOADTIMES(String dOWNLOADTIMES) {
		DOWNLOADTIMES = dOWNLOADTIMES;
	}
	public String getPRODUCER() {
		return PRODUCER;
	}
	public void setPRODUCER(String pRODUCER) {
		PRODUCER = pRODUCER;
	}
	public String getCONFIGURETIME() {
		return CONFIGURETIME;
	}
	public void setCONFIGURETIME(String cONFIGURETIME) {
		CONFIGURETIME = cONFIGURETIME;
	}
	public int getISCONFIGURE() {
		return ISCONFIGURE;
	}
	public void setISCONFIGURE(int iSCONFIGURE) {
		ISCONFIGURE = iSCONFIGURE;
	}
	public String getOS() {
		return OS;
	}
	public void setOS(String oS) {
		OS = oS;
	}
	public String getLANGUAGE() {
		return LANGUAGE;
	}
	public void setLANGUAGE(String lANGUAGE) {
		LANGUAGE = lANGUAGE;
	}
	public String getPATCHINTRODUCTION() {
		return PATCHINTRODUCTION;
	}
	public void setPATCHINTRODUCTION(String pATCHINTRODUCTION) {
		PATCHINTRODUCTION = pATCHINTRODUCTION;
	}

	
}
