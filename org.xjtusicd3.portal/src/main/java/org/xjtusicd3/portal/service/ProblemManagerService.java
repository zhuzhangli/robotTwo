package org.xjtusicd3.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.CommunityAnswerHelper;
import org.xjtusicd3.database.helper.CommunityQuestionHelper;
import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.CommunityAnswerPersistence;
import org.xjtusicd3.database.model.CommunityQuestionPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.portal.view.ProblemView;
import org.xjtusicd3.portal.view.Problem_AnswerView;

/**
 * 
 * @author zzl
 *
 */
public class ProblemManagerService {
	/**
	 * @return problemUnresolveds
	 */
	public static List<ProblemView> problemUnresolved() {
		//问题未处理视图_2017年11月6日09:14:03
		List<ProblemView> problemUnresolveds = new ArrayList<ProblemView>();
		
		//获取问题中心中所有没有最佳答案的问题信息
		List<CommunityQuestionPersistence> problemUnresolvedLists = CommunityQuestionHelper.unResolvedProblems();
		
		for(CommunityQuestionPersistence problemUnresolvedList:problemUnresolvedLists){
			ProblemView problemUnresolved = new ProblemView();
			
			problemUnresolved.setProblemId(problemUnresolvedList.getCOMMUNITYQUESTIONID()); 
			problemUnresolved.setProblemTitle(problemUnresolvedList.getTITLE()); 
			problemUnresolved.setProblemContent(problemUnresolvedList.getCONTENT());
			
			List<ClassifyPersistence> classifyPersistences = ClassifyHelper.getInfoById(problemUnresolvedList.getCLASSIFYID());
			problemUnresolved.setProblemClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
			
			List<UserPersistence> userPersistences = UserHelper.getUserInfoById(problemUnresolvedList.getUSERID());
			problemUnresolved.setProblemUserName(userPersistences.get(0).getUSERNAME());
			problemUnresolved.setProblemTime(problemUnresolvedList.getTIME());
						
			problemUnresolveds.add(problemUnresolved);
		}
		return problemUnresolveds;
	}

	
	/**
	 * @abstract 获取问题中心有最佳答案的问题
	 * @return problemResolveds
	 */
	public static List<Problem_AnswerView> problemResolved() {
		//有最佳答案视图
		List<Problem_AnswerView> problemResolveds = new ArrayList<Problem_AnswerView>();
		
		//获取问题中心中所有有最佳答案的问题信息
		List<CommunityQuestionPersistence> problemResolvedLists = CommunityQuestionHelper.resolvedProblems();
		
		for(CommunityQuestionPersistence problemResolvedList:problemResolvedLists){
			Problem_AnswerView problem_AnswerView = new Problem_AnswerView();
			
			problem_AnswerView.setProblemId(problemResolvedList.getCOMMUNITYQUESTIONID()); 
			problem_AnswerView.setProblemTitle(problemResolvedList.getTITLE()); 
			problem_AnswerView.setProblemContent(problemResolvedList.getCONTENT());
			
			List<ClassifyPersistence> classifyPersistences = ClassifyHelper.getInfoById(problemResolvedList.getCLASSIFYID());
			problem_AnswerView.setProblemClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
			
			List<UserPersistence> userPersistences = UserHelper.getUserInfoById(problemResolvedList.getUSERID());
			problem_AnswerView.setProblemUserName(userPersistences.get(0).getUSERNAME());
			problem_AnswerView.setProblemTime(problemResolvedList.getTIME());
				
			//查询社区回复者信息
			List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.getBestAnswer(problemResolvedList.getCOMMUNITYQUESTIONID());
			List<UserPersistence> ulist = UserHelper.getUserInfoById(communityAnswerPersistences.get(0).getUSERID());
			problem_AnswerView.setAnswerUserName(ulist.get(0).getUSERNAME());
			problem_AnswerView.setAnswerTime(communityAnswerPersistences.get(0).getTIME());
			problem_AnswerView.setAnswerContent(communityAnswerPersistences.get(0).getCONTENT());
					
			problemResolveds.add(problem_AnswerView);
		}
		return problemResolveds;
	}

	/**
	 * 
	 * @param communityProblemId
	 * @return problemView
	 */
	public static ProblemView getUnResolvedEventDetail(String communityProblemId) {
		//获取查看的问题信息
		List<CommunityQuestionPersistence> communityQuestionPersistence = CommunityQuestionHelper.getCommunityQuestionById(communityProblemId);

		ProblemView problemView = new ProblemView();
		
		problemView.setProblemId(communityQuestionPersistence.get(0).getCOMMUNITYQUESTIONID()); 
		problemView.setProblemTitle(communityQuestionPersistence.get(0).getTITLE()); 
		problemView.setProblemContent(communityQuestionPersistence.get(0).getCONTENT());
		
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.getInfoById(communityQuestionPersistence.get(0).getCLASSIFYID());
		problemView.setProblemClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
		
		List<UserPersistence> userPersistences = UserHelper.getUserInfoById(communityQuestionPersistence.get(0).getUSERID());
		problemView.setProblemUserName(userPersistences.get(0).getUSERNAME());
		 
		problemView.setProblemTime(communityQuestionPersistence.get(0).getTIME());
				
		return problemView;
	}

	/**
	 * 
	 * @param communityProblemId
	 * @return
	 */
	public static Problem_AnswerView getResolvedEventDetail(String communityProblemId) {
		//获取查看的问题信息
		List<CommunityQuestionPersistence> communityQuestionPersistence = CommunityQuestionHelper.getCommunityQuestionById(communityProblemId);

		Problem_AnswerView problem_AnswerView = new Problem_AnswerView();
		
		problem_AnswerView.setProblemId(communityProblemId); 
		problem_AnswerView.setProblemTitle(communityQuestionPersistence.get(0).getTITLE()); 
		problem_AnswerView.setProblemContent(communityQuestionPersistence.get(0).getCONTENT());
		
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.getInfoById(communityQuestionPersistence.get(0).getCLASSIFYID());
		System.out.println("分类ID号："+communityQuestionPersistence.get(0).getCLASSIFYID());
		
		problem_AnswerView.setProblemClassifyId(communityQuestionPersistence.get(0).getCLASSIFYID());
		problem_AnswerView.setProblemClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
		
		List<UserPersistence> userPersistences = UserHelper.getUserInfoById(communityQuestionPersistence.get(0).getUSERID());
		problem_AnswerView.setProblemUserId(communityQuestionPersistence.get(0).getUSERID());
		problem_AnswerView.setProblemUserName(userPersistences.get(0).getUSERNAME());
		 
		problem_AnswerView.setProblemTime(communityQuestionPersistence.get(0).getTIME());
		
		//获取最佳答案信息
		List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.getBestAnswer(communityProblemId);

		problem_AnswerView.setAnswerContent(communityAnswerPersistences.get(0).getCONTENT());
		List<UserPersistence> ulist = UserHelper.getUserInfoById(communityAnswerPersistences.get(0).getUSERID());
		problem_AnswerView.setAnswerUserName(ulist.get(0).getUSERNAME());		
		problem_AnswerView.setAnswerTime(communityAnswerPersistences.get(0).getTIME());
			
		return problem_AnswerView;
	}

	/**
	 * 
	 * @param questionId
	 * @param questionState
	 */
	public static void updateCommunityQuestionState(String questionId, int questionState) {
		
		CommunityQuestionHelper.updateCommunityQuestionState(questionId, questionState);
		
	}

}
