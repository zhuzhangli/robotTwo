package org.xjtusicd3.portal.view;

public class Problem_AnswerView {
	//问题
	private String problemId;//社区问题id
	private String problemTitle;
	private String problemContent;
	private String problemClassifyName;
	private String problemClassifyId;
	private String problemUserName;
	private String problemUserId;
	private String problemTime;
	
	//答案
	private String answerUserName;
	private String answerTime;
	private String answerContent;
	
	
	
	public String getProblemUserId() {
		return problemUserId;
	}
	public void setProblemUserId(String problemUserId) {
		this.problemUserId = problemUserId;
	}
	public String getProblemClassifyId() {
		return problemClassifyId;
	}
	public void setProblemClassifyId(String problemClassifyId) {
		this.problemClassifyId = problemClassifyId;
	}
	public String getProblemId() {
		return problemId;
	}
	public void setProblemId(String problemId) {
		this.problemId = problemId;
	}
	public String getProblemTitle() {
		return problemTitle;
	}
	public void setProblemTitle(String problemTitle) {
		this.problemTitle = problemTitle;
	}
	public String getProblemContent() {
		return problemContent;
	}
	public void setProblemContent(String problemContent) {
		this.problemContent = problemContent;
	}
	public String getProblemClassifyName() {
		return problemClassifyName;
	}
	public void setProblemClassifyName(String problemClassifyName) {
		this.problemClassifyName = problemClassifyName;
	}
	public String getProblemUserName() {
		return problemUserName;
	}
	public void setProblemUserName(String problemUserName) {
		this.problemUserName = problemUserName;
	}
	public String getProblemTime() {
		return problemTime;
	}
	public void setProblemTime(String problemTime) {
		this.problemTime = problemTime;
	}
	public String getAnswerUserName() {
		return answerUserName;
	}
	public void setAnswerUserName(String answerUserName) {
		this.answerUserName = answerUserName;
	}
	public String getAnswerTime() {
		return answerTime;
	}
	public void setAnswerTime(String answerTime) {
		this.answerTime = answerTime;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	
	
}
