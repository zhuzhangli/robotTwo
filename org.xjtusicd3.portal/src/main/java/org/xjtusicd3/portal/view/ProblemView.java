package org.xjtusicd3.portal.view;
//用于展示问题中心未解决问题
public class ProblemView{
	private String problemId;
	private String problemTitle;
	private String problemContent;
	private String problemClassifyName;
	private String problemUserName;
	private String problemTime;
	
//	private String problemAnswerId;
//	private String problemAnswer;
//	private String problemAnswerTime;
//	private String problemAnswerUser;
		
				
	public String getProblemId()
	{
		return problemId;
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
	public void setProblemId(String problemId)
	{
		this.problemId = problemId;
	}
	public String getProblemTitle()
	{
		return problemTitle;
	}
	public void setProblemTitle(String problemTitle)
	{
		this.problemTitle = problemTitle;
	}
	
	public String getProblemTime()
	{
		return problemTime;
	}
	public void setProblemTime(String problemTime)
	{
		this.problemTime = problemTime;
	}
	public String getProblemContent()
	{
		return problemContent;
	}
	public void setProblemContent(String problemContent)
	{
		this.problemContent = problemContent;
	}
}
