package org.xjtusicd3.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.xjtusicd3.database.helper.AnswerHelper;
import org.xjtusicd3.database.helper.ClassifyHelper;

import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.AnswerPersistence;
import org.xjtusicd3.database.model.ClassifyPersistence;

import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.portal.view.FaqView;
/**
 * 
 * @author zzl
 * @abstract faq管理_faqPage.ftl
 *
 */
public class FaqManagerService {
	/**
	 * @param FAQSTATE = 2 通过审核；FAQSTATE = 1待审核
	 * @return faqPendingAudits
	 */
	public static List<FaqView> faqPendingAudits() {
		//待审核faq视图
		List<FaqView> faqPendingAudits = new  ArrayList<FaqView>();
		
		//获取所有待审核faq -- 即FAQSTATE字段为1
		List<QuestionPersistence> faqLists = QuestionHelper.faqPendingAudits(0);
				
		for(QuestionPersistence faqList:faqLists){
			FaqView faqPendingAudit = new FaqView();
			
			faqPendingAudit.setFAQQUESTIONID(faqList.getFAQQUESTIONID());
			faqPendingAudit.setFAQTITLE(faqList.getFAQTITLE());
			faqPendingAudit.setFAQDESCRIPTION(faqList.getFAQDESCRIPTION());
			
			List<ClassifyPersistence> classifyPersistences = ClassifyHelper.getInfoById(faqList.getFAQCLASSIFYID());
			faqPendingAudit.setFAQCLASSIFYNAME(classifyPersistences.get(0).getFAQCLASSIFYNAME());
			faqPendingAudit.setFAQKEYWORDS(faqList.getFAQKEYWORDS());
			faqPendingAudit.setMODIFYTIME(faqList.getMODIFYTIME());
			
			List<UserPersistence> list = UserHelper.getUserInfoById(faqList.getUSERID());
			faqPendingAudit.setUSERID(faqList.getUSERID());
			faqPendingAudit.setUSERNAME(list.get(0).getUSERNAME());
			
			List<AnswerPersistence> answerPersistences = AnswerHelper.getAnswerByQuestionId(faqList.getFAQQUESTIONID());
			faqPendingAudit.setFAQCONTENT(answerPersistences.get(0).getFAQCONTENT());
			
			faqPendingAudits.add(faqPendingAudit);
		}	
		return faqPendingAudits;
	}

	/**
	 * 
	 * @param title
	 * @param username
	 * @return true or false
	 */
	public static List<QuestionPersistence> faqAdd(String title, String username) {
		//获取用户ID
		String userId = UserHelper.getUserIdByName(username);
		
		//判断是否重复添加
		List<QuestionPersistence> isExist = QuestionHelper.titleIsExist(title,userId);
		
		return isExist;
	}

	/**
	 * 获取faq问题及答案信息
	 * @param q
	 * @return
	 */
	public static FaqView getAllFaqInfo(String faqQuestionId) {
		//待编辑faq视图
		FaqView faqEdit = new FaqView();
		
		if (faqQuestionId!=null) {
			//获取faqQuestionId对应的问题列表
			List<QuestionPersistence> faqQuserionList = QuestionHelper.getFaqQuestionInfo(faqQuestionId);
			
			//获取faqQuestionId对应的答案列表
			List<AnswerPersistence> faqAnswerList = AnswerHelper.getAnswerByQuestionId(faqQuestionId);
			
			faqEdit.setFAQQUESTIONID(faqQuestionId);
			faqEdit.setFAQTITLE(faqQuserionList.get(0).getFAQTITLE());
			
			List<ClassifyPersistence> classifyPersistences = ClassifyHelper.getInfoById(faqQuserionList.get(0).getFAQCLASSIFYID());
			faqEdit.setFAQCLASSIFYID(faqQuserionList.get(0).getFAQCLASSIFYID());
			faqEdit.setFAQCLASSIFYNAME(classifyPersistences.get(0).getFAQCLASSIFYNAME());
		
			faqEdit.setFAQKEYWORDS(faqQuserionList.get(0).getFAQKEYWORDS());
			faqEdit.setFAQDESCRIPTION(faqQuserionList.get(0).getFAQDESCRIPTION());
			faqEdit.setFAQCONTENT(faqAnswerList.get(0).getFAQCONTENT());
			
			List<UserPersistence> userlist = UserHelper.getUserInfoById(faqQuserionList.get(0).getUSERID());
			faqEdit.setUSERNAME(userlist.get(0).getUSERNAME());
		}
		return faqEdit;
	}

	//获取已审核FAQ
	public static List<FaqView> faqAudited() {
		//待审核faq视图
				List<FaqView> faqAudited = new  ArrayList<FaqView>();
				
				//获取所有已审核faq -- 即FAQSTATE字段为2
				List<QuestionPersistence> faqLists = QuestionHelper.faqAudited(0);
						
				for(QuestionPersistence faqList:faqLists){
					FaqView faqPendingAudit = new FaqView();
					
					faqPendingAudit.setFAQQUESTIONID(faqList.getFAQQUESTIONID());
					faqPendingAudit.setFAQTITLE(faqList.getFAQTITLE());
					faqPendingAudit.setFAQDESCRIPTION(faqList.getFAQDESCRIPTION());
					
					List<ClassifyPersistence> classifyPersistences = ClassifyHelper.getInfoById(faqList.getFAQCLASSIFYID());
					faqPendingAudit.setFAQCLASSIFYNAME(classifyPersistences.get(0).getFAQCLASSIFYNAME());
					faqPendingAudit.setFAQKEYWORDS(faqList.getFAQKEYWORDS());
					faqPendingAudit.setMODIFYTIME(faqList.getMODIFYTIME());
					
					List<UserPersistence> list = UserHelper.getUserInfoById(faqList.getUSERID());
					faqPendingAudit.setUSERID(faqList.getUSERID());
					faqPendingAudit.setUSERNAME(list.get(0).getUSERNAME());
					
					List<AnswerPersistence> answerPersistences = AnswerHelper.getAnswerByQuestionId(faqList.getFAQQUESTIONID());
					faqPendingAudit.setFAQCONTENT(answerPersistences.get(0).getFAQCONTENT());
					
					faqAudited.add(faqPendingAudit);
				}	
				return faqAudited;
	}

	/**
	 * 删除待审核faq
	 * @param questionId
	 */
	public static void deleteFAQ(String questionId) {
		//删除待审核信息
		QuestionHelper.deleteFAQ(questionId);		
		
	}
	
	
	
	
	

}
