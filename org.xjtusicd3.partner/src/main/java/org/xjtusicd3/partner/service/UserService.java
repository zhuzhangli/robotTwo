package org.xjtusicd3.partner.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.AnswerHelper;
import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.CollectionHelper;
import org.xjtusicd3.database.helper.CommentHelper;
import org.xjtusicd3.database.helper.CommunityAnswerHelper;
import org.xjtusicd3.database.helper.CommunityQuestionHelper;
import org.xjtusicd3.database.helper.ITHelper;
import org.xjtusicd3.database.helper.PayHelper;
import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.helper.ShareHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.AnswerPersistence;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.CollectionPersistence;
import org.xjtusicd3.database.model.CommentPersistence;
import org.xjtusicd3.database.model.CommunityAnswerPersistence;
import org.xjtusicd3.database.model.CommunityQuestionPersistence;
import org.xjtusicd3.database.model.GeneraluserPersistence;
import org.xjtusicd3.database.model.ITPersistence;
import org.xjtusicd3.database.model.PayPersistence;
import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.database.model.SharePersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.filter.MD5;
import org.xjtusicd3.partner.view.Personal2_CommunityView;
import org.xjtusicd3.partner.view.Personal2_FaqView;
import org.xjtusicd3.partner.view.Personal2_PayView;
import org.xjtusicd3.partner.view.Personal2_indexList;

public class UserService {
	//login_ajax_注册
	public static void login_register(String name,String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		UUID uuid = UUID.randomUUID();
    	Date date=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = format.format(date);
		password = MD5.EncoderByMd5(password);		
		String userimage = "images/user.png";
		
		/*USERSTATE = 0表明用户待审核;USERSTATE = 1通过审核*/		 
		UserHelper.login_register(uuid.toString(),name,password,1,createTime,userimage);
	}

	
	//判断用户是否登录	
	public static boolean isLogin(String username, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		password = MD5.EncoderByMd5(password);
		//判断用户是否登录
		Boolean loginUser = UserHelper.isLogin(username, password);
		return loginUser;
	}

	
	//获取登录用户信息	
	public static List<UserPersistence> loginUserInfo(String username) {
		List<UserPersistence> loginUser = UserHelper.getUserInfo(username);
		return loginUser;
	}
	
	/**
     * zyq_personal2_展示自己的主页
     */
	public static List<Personal2_indexList> personal2_indexList(String username) {
		List<Personal2_indexList> personal2_indexLists = new ArrayList<Personal2_indexList>();		
		List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
		//查看自己的知识库_每次查看5条
		List<QuestionPersistence> qList = QuestionHelper.personal2_faq_Limit(userPersistences.get(0).getUSERID(),0,5);		
		if (qList.size()!=0) {
			for(QuestionPersistence questionPersistence:qList){
				Personal2_indexList personal2_indexList = new Personal2_indexList();
				
				personal2_indexList.setUserId(userPersistences.get(0).getUSERID());
				personal2_indexList.setUserName(userPersistences.get(0).getUSERNAME());
				personal2_indexList.setUserImage(userPersistences.get(0).getAVATAR());				
				//判断是创建知识还是修改知识	!!!
				boolean isNew = QuestionHelper.personal2_Ismodify(questionPersistence.getFAQQUESTIONID(),"1");								
				if (isNew == false) {
					personal2_indexList.setHow("创建了知识");
				}else{
					personal2_indexList.setHow("修改了知识");
				}
				personal2_indexList.setQuestionId(questionPersistence.getFAQQUESTIONID());
				personal2_indexList.setTitle(questionPersistence.getFAQTITLE());
				personal2_indexList.setContent(questionPersistence.getFAQDESCRIPTION());
				personal2_indexList.setTime(questionPersistence.getMODIFYTIME());
				personal2_indexLists.add(personal2_indexList);
			}
		}
		
		//查看自己的论坛
		List<CommunityQuestionPersistence> cList = CommunityQuestionHelper.notice_CommunityQuestion_Limit(userPersistences.get(0).getUSERID(),0,5);		
		if (cList.size()!=0) {
			for(CommunityQuestionPersistence communityQuestionPersistence:cList){
				Personal2_indexList personal2_indexList = new Personal2_indexList();
				
				personal2_indexList.setUserId(userPersistences.get(0).getUSERID());
				personal2_indexList.setUserName(userPersistences.get(0).getUSERNAME());
				personal2_indexList.setUserImage(userPersistences.get(0).getAVATAR());
				personal2_indexList.setHow("在问吧提问");
				personal2_indexList.setQuestionId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
				personal2_indexList.setTitle(communityQuestionPersistence.getTITLE());
				personal2_indexList.setContent(communityQuestionPersistence.getCONTENT());
				personal2_indexList.setTime(communityQuestionPersistence.getTIME());
				
				//通过classifyId获取分类信息
				List<ClassifyPersistence> classifyPersistences = ClassifyHelper.getInfoById(communityQuestionPersistence.getCLASSIFYID());				
				personal2_indexList.setFrom(classifyPersistences.get(0).getFAQCLASSIFYNAME());				
				personal2_indexList.setFromImage(classifyPersistences.get(0).getFAQCLASSIFYIMAGE());
				personal2_indexLists.add(personal2_indexList);
			}
		}
		
		//查找关注的对象
		List<PayPersistence> payPersistences = PayHelper.payList_Limit(userPersistences.get(0).getUSERID(),0,5);
		if(payPersistences.size()!=0){
			for(PayPersistence payPersistence:payPersistences){
				//获取被关注对象信息
				List<UserPersistence> userPersistences2 = UserHelper.getUserInfoById(payPersistence.getBEPAYUSERID());
				for(UserPersistence userPersistence:userPersistences2){
					Personal2_indexList personal2_indexList = new Personal2_indexList();
					personal2_indexList.setUserId(userPersistences.get(0).getUSERID());
					personal2_indexList.setUserName(userPersistences.get(0).getUSERNAME());
					personal2_indexList.setUserImage(userPersistences.get(0).getAVATAR());
					personal2_indexList.setHow("关注了用户");
					personal2_indexList.setTouserId(userPersistence.getUSERID());
					personal2_indexList.setTouserName(userPersistence.getUSERNAME());
					personal2_indexList.setTouserImage(userPersistence.getAVATAR());
					personal2_indexList.setTouserSex(userPersistence.getGENDER());
					personal2_indexList.setTouserAddress(userPersistence.getUSERADDRESS());
					List<ITPersistence> itPersistences = ITHelper.IT(userPersistence.getUSERID());
					if (itPersistences.size()!=0) {
						personal2_indexList.setTouserJob(itPersistences.get(0).getGOODWORK());
					}
					personal2_indexList.setTime(payPersistence.getTIME());
					personal2_indexLists.add(personal2_indexList);
				}
			}
		}
		
		//查找出被关注的对象
		List<PayPersistence> payPersistences1 = PayHelper.bepayList(userPersistences.get(0).getUSERID());
		if (payPersistences1.size()!=0) {
			for(PayPersistence payPersistence:payPersistences1){
				//找出关注对象信息
				List<UserPersistence> userPersistences2 = UserHelper.getUserInfoById(payPersistence.getPAYUSERID());
				for(UserPersistence userPersistence:userPersistences2){
					//查看关注的人专注了who
					//查看是否关注
					List<PayPersistence> list = PayHelper.getpayList(userPersistences.get(0).getUSERID(), userPersistence.getUSERID());
					if (list.size()!=0) {
						//关注
						List<PayPersistence> payPersistences2 = PayHelper.payList_time_Limit(userPersistence.getUSERID(),list.get(0).getTIME(),0,5);
						for(PayPersistence payPersistence2:payPersistences2){
							Personal2_indexList personal2_indexList = new Personal2_indexList();
							personal2_indexList.setUserId(userPersistence.getUSERID());
							personal2_indexList.setUserName(userPersistence.getUSERNAME());
							personal2_indexList.setUserImage(userPersistence.getAVATAR());
							personal2_indexList.setHow("关注了用户");
							List<UserPersistence> userPersistences3 = UserHelper.getUserInfoById(payPersistence.getBEPAYUSERID());
							if (userPersistences3.size()!=0) {
								personal2_indexList.setTouserId(userPersistences3.get(0).getUSERID());
								personal2_indexList.setTouserName(userPersistences3.get(0).getUSERNAME());
								personal2_indexList.setTouserImage(userPersistences3.get(0).getAVATAR());
								personal2_indexList.setTouserSex(userPersistences3.get(0).getGENDER());
								personal2_indexList.setTouserAddress(userPersistences3.get(0).getUSERADDRESS());
							}
							List<ITPersistence> itPersistences = ITHelper.IT(userPersistences3.get(0).getUSERID());
							if (itPersistences.size()!=0) {
								personal2_indexList.setTouserJob(itPersistences.get(0).getGOODWORK());
							}
							personal2_indexList.setTime(payPersistence2.getTIME());
							personal2_indexLists.add(personal2_indexList);
							
						}
					}
					
					//查看分享的FAQ信息
					List<SharePersistence> sharePersistences = ShareHelper.getShareList_FAQ_Limit(userPersistence.getUSERID(),0,5);
					for(SharePersistence sharePersistence:sharePersistences){
						Personal2_indexList personal2_indexList = new Personal2_indexList();
						personal2_indexList.setUserId(userPersistence.getUSERID());
						personal2_indexList.setUserName(userPersistence.getUSERNAME());
						personal2_indexList.setUserImage(userPersistence.getAVATAR());
						personal2_indexList.setHow("推荐了知识");
						List<QuestionPersistence> questionPersistences = QuestionHelper.faq3_faqcontent(sharePersistence.getFAQQUESTIONID());
						if (questionPersistences.size()!=0) {
							personal2_indexList.setQuestionId(questionPersistences.get(0).getFAQQUESTIONID());
							personal2_indexList.setTitle(questionPersistences.get(0).getFAQTITLE());
							personal2_indexList.setContent(questionPersistences.get(0).getFAQDESCRIPTION());
						}
						personal2_indexList.setTime(sharePersistence.getTIME());
						personal2_indexLists.add(personal2_indexList);
					}
					
					//查看分享的Community信息
					List<SharePersistence> sharePersistences2 = ShareHelper.getShareList_community_Limit(userPersistence.getUSERID(),0,5);
					for(SharePersistence sharePersistence2:sharePersistences2){
						Personal2_indexList personal2_indexList = new Personal2_indexList();
						personal2_indexList.setUserId(userPersistence.getUSERID());
						personal2_indexList.setUserName(userPersistence.getUSERNAME());
						personal2_indexList.setUserImage(userPersistence.getAVATAR());
						personal2_indexList.setHow("推荐了问题");
						List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question2_getCommunity(sharePersistence2.getCOMMUNITYQUESTIONID());
						if (communityQuestionPersistences.size()!=0) {
							personal2_indexList.setQuestionId(communityQuestionPersistences.get(0).getCOMMUNITYQUESTIONID());
							personal2_indexList.setTitle(communityQuestionPersistences.get(0).getTITLE());
							personal2_indexList.setContent(communityQuestionPersistences.get(0).getCONTENT());
							List<ClassifyPersistence> classifyPersistences = ClassifyHelper.getInfoById(communityQuestionPersistences.get(0).getCLASSIFYID());
							personal2_indexList.setFrom(classifyPersistences.get(0).getFAQCLASSIFYNAME());
							personal2_indexList.setFromImage(classifyPersistences.get(0).getFAQCLASSIFYIMAGE());
						}
						personal2_indexList.setTime(sharePersistence2.getTIME());
						personal2_indexLists.add(personal2_indexList);

					}
				}
			}
		}
		List<Personal2_indexList> list = ListSort(personal2_indexLists);
		return list;
	}
	
	/*
	 * zyq_personal2_ajax_获取更多的个人主页信息
	 */
	public static List<Personal2_indexList> personal2_indexList_Limit(String username, String time1, String time2,String time3, String time11, String time22, String time33) {
		List<Personal2_indexList> personal2_indexLists = new ArrayList<Personal2_indexList>();
		List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
		//查看自己的知识库
		List<QuestionPersistence> qList = QuestionHelper.personal2_faq_Limit_Time(userPersistences.get(0).getUSERID(),0,5,time2);
		if (qList.size()!=0) {
			for(QuestionPersistence questionPersistence:qList){
				Personal2_indexList personal2_indexList = new Personal2_indexList();
				personal2_indexList.setUserId(userPersistences.get(0).getUSERID());
				personal2_indexList.setUserName(userPersistences.get(0).getUSERNAME());
				personal2_indexList.setUserImage(userPersistences.get(0).getAVATAR());
				//判断是创建知识还是修改知识
				boolean isNew = QuestionHelper.personal2_Ismodify(questionPersistence.getFAQQUESTIONID(),"1");			
				if (isNew == false) {
					personal2_indexList.setHow("创建了知识");
				}else{
					personal2_indexList.setHow("修改了知识");
				}
				personal2_indexList.setQuestionId(questionPersistence.getFAQQUESTIONID());
				personal2_indexList.setTitle(questionPersistence.getFAQTITLE());
				personal2_indexList.setContent(questionPersistence.getFAQDESCRIPTION());
				personal2_indexList.setTime(questionPersistence.getMODIFYTIME());
				personal2_indexLists.add(personal2_indexList);
			}
		}
		//查看自己的论坛
		List<CommunityQuestionPersistence> cList = CommunityQuestionHelper.notice_CommunityQuestion_Limit_Time(userPersistences.get(0).getUSERID(),0,5,time3);
		if (cList.size()!=0) {
			for(CommunityQuestionPersistence communityQuestionPersistence:cList){
				Personal2_indexList personal2_indexList = new Personal2_indexList();
				personal2_indexList.setUserId(userPersistences.get(0).getUSERID());
				personal2_indexList.setUserName(userPersistences.get(0).getUSERNAME());
				personal2_indexList.setUserImage(userPersistences.get(0).getAVATAR());
				personal2_indexList.setHow("在问吧提问");
				personal2_indexList.setQuestionId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
				personal2_indexList.setTitle(communityQuestionPersistence.getTITLE());
				personal2_indexList.setContent(communityQuestionPersistence.getCONTENT());
				personal2_indexList.setTime(communityQuestionPersistence.getTIME());
				List<ClassifyPersistence> classifyPersistences = ClassifyHelper.getInfoById(communityQuestionPersistence.getCLASSIFYID());
				personal2_indexList.setFrom(classifyPersistences.get(0).getFAQCLASSIFYNAME());
				personal2_indexList.setFromImage(classifyPersistences.get(0).getFAQCLASSIFYIMAGE());
				personal2_indexLists.add(personal2_indexList);
			}
		}
		//查找关注的对象
		List<PayPersistence> payPersistences = PayHelper.payList_time_Limit(userPersistences.get(0).getUSERID(),time1,0,5);
		if(payPersistences.size()!=0){
			for(PayPersistence payPersistence:payPersistences){
				List<UserPersistence> userPersistences2 = UserHelper.getUserInfoById(payPersistence.getBEPAYUSERID());
				for(UserPersistence userPersistence:userPersistences2){
					Personal2_indexList personal2_indexList = new Personal2_indexList();
					personal2_indexList.setUserId(userPersistences.get(0).getUSERID());
					personal2_indexList.setUserName(userPersistences.get(0).getUSERNAME());
					personal2_indexList.setUserImage(userPersistences.get(0).getAVATAR());
					personal2_indexList.setHow("关注了用户");
					personal2_indexList.setTouserId(userPersistence.getUSERID());
					personal2_indexList.setTouserName(userPersistence.getUSERNAME());
					personal2_indexList.setTouserImage(userPersistence.getAVATAR());
					personal2_indexList.setTouserSex(userPersistence.getGENDER());
					personal2_indexList.setTouserAddress(userPersistence.getUSERADDRESS());
					List<ITPersistence> itPersistences = ITHelper.IT(userPersistence.getUSERID());
					if (itPersistences.size()!=0) {
						personal2_indexList.setTouserJob(itPersistences.get(0).getGOODWORK());
					}
					personal2_indexList.setTime(payPersistence.getTIME());
					personal2_indexLists.add(personal2_indexList);
				}
			}
		}
		//查找出关注的对象
		List<PayPersistence> payPersistences1 = PayHelper.bepayList(userPersistences.get(0).getUSERID());
		if (payPersistences1.size()!=0) {
			for(PayPersistence payPersistence:payPersistences1){
				List<UserPersistence> userPersistences2 = UserHelper.getUserInfoById(payPersistence.getPAYUSERID());
				for(UserPersistence userPersistence:userPersistences2){
					//查看关注的人专注了who
					List<PayPersistence> list = PayHelper.getpayList(userPersistences.get(0).getUSERID(), userPersistence.getUSERID());
					if (list.size()!=0) {
						List<PayPersistence> payPersistences2 = PayHelper.payList_time_Limit_Time(userPersistence.getUSERID(),list.get(0).getTIME(),0,5,time11);
						for(PayPersistence payPersistence2:payPersistences2){
							Personal2_indexList personal2_indexList = new Personal2_indexList();
							personal2_indexList.setUserId(userPersistence.getUSERID());
							personal2_indexList.setUserName(userPersistence.getUSERNAME());
							personal2_indexList.setUserImage(userPersistence.getAVATAR());
							personal2_indexList.setHow("关注了用户");
							List<UserPersistence> userPersistences3 = UserHelper.getUserInfoById(payPersistence.getBEPAYUSERID());
							if (userPersistences3.size()!=0) {
								personal2_indexList.setTouserId(userPersistences3.get(0).getUSERID());
								personal2_indexList.setTouserName(userPersistences3.get(0).getUSERNAME());
								personal2_indexList.setTouserImage(userPersistences3.get(0).getAVATAR());
								personal2_indexList.setTouserSex(userPersistences3.get(0).getGENDER());
								personal2_indexList.setTouserAddress(userPersistences3.get(0).getUSERADDRESS());
							}
							List<ITPersistence> itPersistences = ITHelper.IT(userPersistences3.get(0).getUSERID());
							if (itPersistences.size()!=0) {
								personal2_indexList.setTouserJob(itPersistences.get(0).getGOODWORK());
							}
							personal2_indexList.setTime(payPersistence2.getTIME());
							personal2_indexLists.add(personal2_indexList);
							
						}
					}
					
					//查看分享的FAQ信息
					List<SharePersistence> sharePersistences = ShareHelper.getShareList_FAQ_Limit_Time(userPersistence.getUSERID(),0,5,time22);
					for(SharePersistence sharePersistence:sharePersistences){
						Personal2_indexList personal2_indexList = new Personal2_indexList();
						personal2_indexList.setUserId(userPersistence.getUSERID());
						personal2_indexList.setUserName(userPersistence.getUSERNAME());
						personal2_indexList.setUserImage(userPersistence.getAVATAR());
						personal2_indexList.setHow("推荐了知识");
						List<QuestionPersistence> questionPersistences = QuestionHelper.faq3_faqcontent(sharePersistence.getFAQQUESTIONID());
						if (questionPersistences.size()!=0) {
							personal2_indexList.setQuestionId(questionPersistences.get(0).getFAQQUESTIONID());
							personal2_indexList.setTitle(questionPersistences.get(0).getFAQTITLE());
							personal2_indexList.setContent(questionPersistences.get(0).getFAQDESCRIPTION());
						}
						personal2_indexList.setTime(sharePersistence.getTIME());
						personal2_indexLists.add(personal2_indexList);
					}
					//查看分享的Community信息
					List<SharePersistence> sharePersistences2 = ShareHelper.getShareList_community_Limit_Time(userPersistence.getUSERID(),0,5,time33);
					for(SharePersistence sharePersistence2:sharePersistences2){
						Personal2_indexList personal2_indexList = new Personal2_indexList();
						personal2_indexList.setUserId(userPersistence.getUSERID());
						personal2_indexList.setUserName(userPersistence.getUSERNAME());
						personal2_indexList.setUserImage(userPersistence.getAVATAR());
						personal2_indexList.setHow("推荐了问题");
						List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question2_getCommunity(sharePersistence2.getCOMMUNITYQUESTIONID());
						if (communityQuestionPersistences.size()!=0) {
							personal2_indexList.setQuestionId(communityQuestionPersistences.get(0).getCOMMUNITYQUESTIONID());
							personal2_indexList.setTitle(communityQuestionPersistences.get(0).getTITLE());
							personal2_indexList.setContent(communityQuestionPersistences.get(0).getCONTENT());
							List<ClassifyPersistence> classifyPersistences = ClassifyHelper.getInfoById(communityQuestionPersistences.get(0).getCLASSIFYID());
							personal2_indexList.setFrom(classifyPersistences.get(0).getFAQCLASSIFYNAME());
							personal2_indexList.setFromImage(classifyPersistences.get(0).getFAQCLASSIFYIMAGE());
						}
						personal2_indexList.setTime(sharePersistence2.getTIME());
						personal2_indexLists.add(personal2_indexList);

					}
				}
			}
		}
		List<Personal2_indexList> list = ListSort(personal2_indexLists);
		return list;
	}
	
	//对list里面的元素进行time的排序
	private static List<Personal2_indexList> ListSort(List<Personal2_indexList> list){
		Collections.sort(list,new Comparator<Personal2_indexList>() {
			@Override
			public int compare(Personal2_indexList o1, Personal2_indexList o2) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					Date dt1 = format.parse(o1.getTime());
					Date dt2 = format.parse(o2.getTime());
					if (dt1.getTime()<dt2.getTime()) {
						return 1;
					}else if (dt1.getTime()>dt2.getTime()) {
						return -1;
					}else {
						return 0;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return 0;
			}
		});
		return list;
	}	
	
	/**
	 * zyq_personal2_ajax_获取知识库列表
	 */
	public static List<Personal2_FaqView> getpersonalFaq(String userId) {
		List<Personal2_FaqView> personal2_FaqViews = new ArrayList<Personal2_FaqView>();
		List<QuestionPersistence> questionPersistences = QuestionHelper.personal2_faq_Limit(userId,0,5);
		System.out.println("知识库大小："+questionPersistences.size());
		for(QuestionPersistence questionPersistence:questionPersistences){
			Personal2_FaqView personal2_FaqView = new Personal2_FaqView();
			personal2_FaqView.setFaqId(questionPersistence.getFAQQUESTIONID());
			personal2_FaqView.setTitle(questionPersistence.getFAQTITLE());
			List<AnswerPersistence> answerPersistences = AnswerHelper.faq3_faqContent(questionPersistence.getFAQQUESTIONID());
			if (answerPersistences.size()!=0) {
				personal2_FaqView.setContent(answerPersistences.get(0).getFAQCONTENT());				
				String username = UserHelper.getUserNameById(answerPersistences.get(0).getUSERID());
				personal2_FaqView.setUsername(username);			
				personal2_FaqView.setUserId(answerPersistences.get(0).getUSERID());
			}			
			personal2_FaqView.setTime(questionPersistence.getMODIFYTIME());			
			personal2_FaqView.setScanNumber(questionPersistence.getSCAN());
			personal2_FaqView.setCollectionNumber(questionPersistence.getCOLLECTION());
			
			//查看有多少评论及回复
			int commentCount = CommentHelper.commentInfo(questionPersistence.getFAQQUESTIONID());
			personal2_FaqView.setCommentNumber(Integer.toString(commentCount));
			if (questionPersistences.size()==5) {
				personal2_FaqView.setIsMore("1");
			}else {
				personal2_FaqView.setIsMore("0");
			}
			personal2_FaqViews.add(personal2_FaqView);
		}
		return personal2_FaqViews;
	}
	
	/**
	 * zyq_personal2_ajax_获取更多的知识库列表
	 */
	public static List<Personal2_FaqView> getpersonalFaq_More(String userId,int startnumber) {
		List<Personal2_FaqView> personal2_FaqViews = new ArrayList<Personal2_FaqView>();
		List<QuestionPersistence> questionPersistences = QuestionHelper.personal2_faq_Limit(userId,startnumber,5);
		for(QuestionPersistence questionPersistence:questionPersistences){
			Personal2_FaqView personal2_FaqView = new Personal2_FaqView();
			personal2_FaqView.setFaqId(questionPersistence.getFAQQUESTIONID());
			personal2_FaqView.setTitle(questionPersistence.getFAQTITLE());
			List<AnswerPersistence> answerPersistences = AnswerHelper.faq3_faqContent(questionPersistence.getFAQQUESTIONID());
			if (answerPersistences.size()!=0) {
				personal2_FaqView.setContent(answerPersistences.get(0).getFAQCONTENT());				
				String username = UserHelper.getUserNameById(answerPersistences.get(0).getUSERID());
				personal2_FaqView.setUsername(username);
				personal2_FaqView.setUserId(answerPersistences.get(0).getUSERID());
			}
			personal2_FaqView.setTime(questionPersistence.getMODIFYTIME());			
			personal2_FaqView.setScanNumber(questionPersistence.getSCAN());
			int faqCollectionCount = CollectionHelper.getCollectionFaqCount(questionPersistence.getFAQQUESTIONID());
			personal2_FaqView.setCollectionNumber(Integer.toString(faqCollectionCount));			
			//查看有多少评论及回复
			int commentCount = CommentHelper.commentInfo(questionPersistence.getFAQQUESTIONID());
			personal2_FaqView.setCommentNumber(Integer.toString(commentCount));
			if (questionPersistences.size()==5) {
				personal2_FaqView.setIsMore("1");
			}
			personal2_FaqViews.add(personal2_FaqView);
		}
		return personal2_FaqViews;
	}
	
	/**
	 * zyq_personal2_ajax_获取收藏FAQ
	 */
	public static List<Personal2_FaqView> getCollectionFaq(String userid) {
		List<Personal2_FaqView> personal2_FaqViews = new ArrayList<Personal2_FaqView>();
		List<CollectionPersistence> collectionPersistences = CollectionHelper.getCollectionFaq(userid,0,5);
		for(CollectionPersistence collectionPersistence:collectionPersistences){
			Personal2_FaqView personal2_FaqView = new Personal2_FaqView();
			personal2_FaqView.setFaqId(collectionPersistence.getFAQQUESTIONID());
			List<QuestionPersistence> questionPersistences = QuestionHelper.faq3_faqcontent(collectionPersistence.getFAQQUESTIONID());
			if (questionPersistences.size()!=0) {
				personal2_FaqView.setTitle(questionPersistences.get(0).getFAQTITLE());
				List<AnswerPersistence> answerPersistences = AnswerHelper.faq3_faqContent(questionPersistences.get(0).getFAQQUESTIONID());
				personal2_FaqView.setContent(answerPersistences.get(0).getFAQCONTENT());
				String username = UserHelper.getUserNameById(answerPersistences.get(0).getUSERID());
				personal2_FaqView.setUsername(username);
				personal2_FaqView.setUserId(answerPersistences.get(0).getUSERID());
				personal2_FaqView.setTime(questionPersistences.get(0).getMODIFYTIME());			
				personal2_FaqView.setScanNumber(questionPersistences.get(0).getSCAN());
				int faqCollectionCount = CollectionHelper.getCollectionFaqCount(questionPersistences.get(0).getFAQQUESTIONID());
				personal2_FaqView.setCollectionNumber(Integer.toString(faqCollectionCount));				
				int commentCount = CommentHelper.commentInfo(questionPersistences.get(0).getFAQQUESTIONID());
				personal2_FaqView.setCommentNumber(Integer.toString(commentCount));
				if (collectionPersistences.size()==5) {
					personal2_FaqView.setIsMore("1");
				}else{
					personal2_FaqView.setIsMore("0");
				}
				personal2_FaqViews.add(personal2_FaqView);
			}
		}
		return personal2_FaqViews;
	}
	
	/**
	 * zyq_personal2_ajax_获取更多的收藏FAQ
	 */
	public static List<Personal2_FaqView> getCollectionFaq_More(String userid,int startnumber) {
		List<Personal2_FaqView> personal2_FaqViews = new ArrayList<Personal2_FaqView>();
		List<CollectionPersistence> collectionPersistences = CollectionHelper.getCollectionFaq(userid,startnumber,5);
		for(CollectionPersistence collectionPersistence:collectionPersistences){
			Personal2_FaqView personal2_FaqView = new Personal2_FaqView();
			personal2_FaqView.setFaqId(collectionPersistence.getFAQQUESTIONID());
			List<QuestionPersistence> questionPersistences = QuestionHelper.faq3_faqcontent(collectionPersistence.getFAQQUESTIONID());
			if (questionPersistences.size()!=0) {
				personal2_FaqView.setTitle(questionPersistences.get(0).getFAQTITLE());
				List<AnswerPersistence> answerPersistences = AnswerHelper.faq3_faqContent(questionPersistences.get(0).getFAQQUESTIONID());
				personal2_FaqView.setContent(answerPersistences.get(0).getFAQCONTENT());
				String username = UserHelper.getUserNameById(answerPersistences.get(0).getUSERID());
				personal2_FaqView.setUsername(username);
				personal2_FaqView.setUserId(answerPersistences.get(0).getUSERID());
				personal2_FaqView.setTime(questionPersistences.get(0).getMODIFYTIME());				
				personal2_FaqView.setScanNumber(questionPersistences.get(0).getSCAN());
				int faqCollectionCount = CollectionHelper.getCollectionFaqCount(questionPersistences.get(0).getFAQQUESTIONID());
				personal2_FaqView.setCollectionNumber(Integer.toString(faqCollectionCount));
				int commentCount = CommentHelper.commentInfo(questionPersistences.get(0).getFAQQUESTIONID());
				personal2_FaqView.setCommentNumber(Integer.toString(commentCount));
				if (collectionPersistences.size()==5) {
					personal2_FaqView.setIsMore("1");
				}else{
					personal2_FaqView.setIsMore("0");
				}
				personal2_FaqViews.add(personal2_FaqView);
			}
		}
		return personal2_FaqViews;
	}
	
	/**
	 * zyq_personal2_ajax_获取FAQ的评论
	 */
	public static List<Personal2_FaqView> getCommentFaq(String userid) {
		List<Personal2_FaqView> personal2_FaqViews = new ArrayList<Personal2_FaqView>();
		List<CommentPersistence> commentPersistences = CommentHelper.personal2_getFaqComment_Limit(userid,"0",0,5);
		for(CommentPersistence commentPersistence:commentPersistences){
			Personal2_FaqView personal2_FaqView = new Personal2_FaqView();
			personal2_FaqView.setFaqId(commentPersistence.getFAQQUESTIONID());
			List<QuestionPersistence> questionPersistences = QuestionHelper.faq3_faqcontent(commentPersistence.getFAQQUESTIONID());
			if (questionPersistences.size()!=0) {
				personal2_FaqView.setTitle(questionPersistences.get(0).getFAQTITLE());
				List<AnswerPersistence> answerPersistences = AnswerHelper.faq3_faqContent(questionPersistences.get(0).getFAQQUESTIONID());
				personal2_FaqView.setContent(answerPersistences.get(0).getFAQCONTENT());
				String username = UserHelper.getUserNameById(answerPersistences.get(0).getUSERID());
				personal2_FaqView.setUsername(username);
				personal2_FaqView.setUserId(answerPersistences.get(0).getUSERID());
				personal2_FaqView.setTime(questionPersistences.get(0).getMODIFYTIME());
				personal2_FaqView.setScanNumber(questionPersistences.get(0).getSCAN());				
				int faqCollectionCount = CollectionHelper.getCollectionFaqCount(questionPersistences.get(0).getFAQQUESTIONID());
				personal2_FaqView.setCollectionNumber(Integer.toString(faqCollectionCount));			
				int commentCount = CommentHelper.commentInfo(questionPersistences.get(0).getFAQQUESTIONID());
				personal2_FaqView.setCommentNumber(Integer.toString(commentCount));
				if (commentPersistences.size()==5) {
					personal2_FaqView.setIsMore("1");
				}else{
					personal2_FaqView.setIsMore("0");
				}
				
				personal2_FaqView.setReplyId(commentPersistence.getCOMMENTID());
				personal2_FaqView.setReply(commentPersistence.getCOMMENTCONTENT());
				personal2_FaqView.setIsreply("我的评论");
				personal2_FaqView.setReplytime(commentPersistence.getCOMMENTTIME());				
				int faqcommentCount = CommentHelper.faq3_getCommentCountById(commentPersistence.getCOMMENTPARENTID());
				personal2_FaqView.setReplyNumber(Integer.toString(faqcommentCount));
				personal2_FaqView.setParentId(commentPersistence.getCOMMENTID());				
				personal2_FaqViews.add(personal2_FaqView);
			}
		}
		return personal2_FaqViews;
	}
	
	/**
	 * zyq_personal2_ajax_获取更多FAQ的评论
	 */
	public static List<Personal2_FaqView> getCommentFaq_More(String userid, int startnumber) {
		List<Personal2_FaqView> personal2_FaqViews = new ArrayList<Personal2_FaqView>();
		List<CommentPersistence> commentPersistences = CommentHelper.personal2_getFaqComment_Limit(userid,"0",startnumber,5);
		for(CommentPersistence commentPersistence:commentPersistences){
			Personal2_FaqView personal2_FaqView = new Personal2_FaqView();
			personal2_FaqView.setFaqId(commentPersistence.getFAQQUESTIONID());
			List<QuestionPersistence> questionPersistences = QuestionHelper.faq3_faqcontent(commentPersistence.getFAQQUESTIONID());
			if (questionPersistences.size()!=0) {
				personal2_FaqView.setTitle(questionPersistences.get(0).getFAQTITLE());
				List<AnswerPersistence> answerPersistences = AnswerHelper.faq3_faqContent(questionPersistences.get(0).getFAQQUESTIONID());
				personal2_FaqView.setContent(answerPersistences.get(0).getFAQCONTENT());
				String username = UserHelper.getUserNameById(answerPersistences.get(0).getUSERID());
				personal2_FaqView.setUsername(username);
				personal2_FaqView.setUserId(answerPersistences.get(0).getUSERID());
				personal2_FaqView.setTime(questionPersistences.get(0).getMODIFYTIME());
				personal2_FaqView.setScanNumber(questionPersistences.get(0).getSCAN());				
				int faqcollectionCount = CollectionHelper.getCollectionFaqCount(questionPersistences.get(0).getFAQQUESTIONID());
				personal2_FaqView.setCollectionNumber(Integer.toString(faqcollectionCount));
				int commentCount = CommentHelper.commentInfo(questionPersistences.get(0).getFAQQUESTIONID());
				personal2_FaqView.setCommentNumber(Integer.toString(commentCount));
				if (commentPersistences.size()==5) {
					personal2_FaqView.setIsMore("1");
				}else{
					personal2_FaqView.setIsMore("0");
				}
				
				personal2_FaqView.setReplyId(commentPersistence.getCOMMENTID());
				personal2_FaqView.setReply(commentPersistence.getCOMMENTCONTENT());
				personal2_FaqView.setIsreply("我的评论");
				personal2_FaqView.setReplytime(commentPersistence.getCOMMENTTIME());				
				int faqcommentCount = CommentHelper.faq3_getCommentCountById(commentPersistence.getCOMMENTPARENTID());
				personal2_FaqView.setReplyNumber(Integer.toString(faqcommentCount));
				personal2_FaqView.setParentId(commentPersistence.getCOMMENTID());
				
				personal2_FaqViews.add(personal2_FaqView);
			}
		}
		return personal2_FaqViews;
	}
	
	/**
	 * zyq_personal2_ajax_获取问吧的问题
	 */
	public static List<Personal2_CommunityView> getpersonalCommunity(String userId) {
		List<Personal2_CommunityView> personal2_CommunityViews = new ArrayList<Personal2_CommunityView>();
		List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.notice_CommunityQuestion_Limit(userId, 0, 5);
		for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
			Personal2_CommunityView personal2_CommunityView = new Personal2_CommunityView();
			personal2_CommunityView.setClassifyId(communityQuestionPersistence.getCLASSIFYID());
			List<ClassifyPersistence> classifyPersistences = ClassifyHelper.getInfoById(communityQuestionPersistence.getCLASSIFYID());
			personal2_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
			personal2_CommunityView.setClassifyImage(classifyPersistences.get(0).getFAQCLASSIFYIMAGE());	
			personal2_CommunityView.setTitle(communityQuestionPersistence.getTITLE());
			personal2_CommunityView.setQuestionId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
			personal2_CommunityView.setTime(communityQuestionPersistence.getTIME());			
			int communityAnswerCount = CommunityAnswerHelper.question_CommunityAnswerCount(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
			personal2_CommunityView.setAnswerNumber(Integer.toString(communityAnswerCount));
			if (communityQuestionPersistences.size()==5) {
				personal2_CommunityView.setIsMore("1");
			}else{
				personal2_CommunityView.setIsMore("0");
			}
			personal2_CommunityViews.add(personal2_CommunityView);
		}
		return personal2_CommunityViews;
	}
	
	/**
	 * zyq_personal2_ajax_获取更多问吧的问题
	 */
	public static List<Personal2_CommunityView> getMoreCommunity(String userid, int startnumber) {
		List<Personal2_CommunityView> personal2_CommunityViews = new ArrayList<Personal2_CommunityView>();
		List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.notice_CommunityQuestion_Limit(userid, startnumber, 5);
		for(CommunityQuestionPersistence communityQuestionPersistence:communityQuestionPersistences){
			Personal2_CommunityView personal2_CommunityView = new Personal2_CommunityView();
			personal2_CommunityView.setClassifyId(communityQuestionPersistence.getCLASSIFYID());
			List<ClassifyPersistence> classifyPersistences = ClassifyHelper.getInfoById(communityQuestionPersistence.getCLASSIFYID());
			personal2_CommunityView.setClassifyImage(classifyPersistences.get(0).getFAQCLASSIFYIMAGE());
			personal2_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
			personal2_CommunityView.setTitle(communityQuestionPersistence.getTITLE());
			personal2_CommunityView.setQuestionId(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
			personal2_CommunityView.setTime(communityQuestionPersistence.getTIME());		
			int communityAnswerCount = CommunityAnswerHelper.question_CommunityAnswerCount(communityQuestionPersistence.getCOMMUNITYQUESTIONID());
			personal2_CommunityView.setAnswerNumber(Integer.toString(communityAnswerCount));
			if (communityQuestionPersistences.size()==5) {
				personal2_CommunityView.setIsMore("1");
			}else{
				personal2_CommunityView.setIsMore("0");
			}
			personal2_CommunityViews.add(personal2_CommunityView);
		}
		return personal2_CommunityViews;
	}
	
	/**
	 * zyq_personal2_ajax_获取问吧的关注_答案
	 */
	public static List<Personal2_CommunityView> getPayCommunity(String userId) {
		List<Personal2_CommunityView> personal2_CommunityViews = new ArrayList<Personal2_CommunityView>();
		List<CollectionPersistence> collectionPersistences = CollectionHelper.personal2_PayCommunity_Limit(userId,0,5);
		for(CollectionPersistence collectionPersistence:collectionPersistences){
			Personal2_CommunityView personal2_CommunityView = new Personal2_CommunityView();
			List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswerId(collectionPersistence.getCOMMUNITYANSWERID());
			List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question2_getCommunity(communityAnswerPersistences.get(0).getCOMMUNITYQUESTIONID());
			personal2_CommunityView.setClassifyId(communityQuestionPersistences.get(0).getCLASSIFYID());
			List<ClassifyPersistence> classifyPersistences = ClassifyHelper.getInfoById(communityQuestionPersistences.get(0).getCLASSIFYID());
			personal2_CommunityView.setClassifyImage(classifyPersistences.get(0).getFAQCLASSIFYIMAGE());
			personal2_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
			personal2_CommunityView.setTitle(communityQuestionPersistences.get(0).getTITLE());			
			personal2_CommunityView.setQuestionId(communityAnswerPersistences.get(0).getCOMMUNITYQUESTIONID());
			if (collectionPersistences.size()==5) {
				personal2_CommunityView.setIsMore("1");
			}else{
				personal2_CommunityView.setIsMore("0");
			}
			
			personal2_CommunityView.setContent(communityAnswerPersistences.get(0).getCONTENT());
			int commentTotalnumber = CommentHelper.faq3_getCommentReply(communityAnswerPersistences.get(0).getCOMMUNITYANSWERID());
			personal2_CommunityView.setReplyNumber(Integer.toString(commentTotalnumber));
			personal2_CommunityView.setReplyTime(communityAnswerPersistences.get(0).getTIME());			
			
			personal2_CommunityViews.add(personal2_CommunityView);
		}
		return personal2_CommunityViews;
	}
	
	/**
	 * zyq_personal2_ajax_获取更多问吧的关注答案
	 */
	public static List<Personal2_CommunityView> getMorePayCommunity(String userid, int startnumber) {
		List<Personal2_CommunityView> personal2_CommunityViews = new ArrayList<Personal2_CommunityView>();
		List<CollectionPersistence> collectionPersistences = CollectionHelper.personal2_PayCommunity_Limit(userid,startnumber,5);
		for(CollectionPersistence collectionPersistence:collectionPersistences){
			Personal2_CommunityView personal2_CommunityView = new Personal2_CommunityView();
			List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswerId(collectionPersistence.getCOMMUNITYANSWERID());
			List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question2_getCommunity(communityAnswerPersistences.get(0).getCOMMUNITYQUESTIONID());
			personal2_CommunityView.setClassifyId(communityQuestionPersistences.get(0).getCLASSIFYID());
			List<ClassifyPersistence> classifyPersistences = ClassifyHelper.getInfoById(communityQuestionPersistences.get(0).getCLASSIFYID());
			personal2_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
			personal2_CommunityView.setClassifyImage(classifyPersistences.get(0).getFAQCLASSIFYIMAGE());
			personal2_CommunityView.setTitle(communityQuestionPersistences.get(0).getTITLE());
			personal2_CommunityView.setQuestionId(communityAnswerPersistences.get(0).getCOMMUNITYQUESTIONID());
			if (collectionPersistences.size()==5) {
				personal2_CommunityView.setIsMore("1");
			}else{
				personal2_CommunityView.setIsMore("0");
			}
			
			personal2_CommunityView.setContent(communityAnswerPersistences.get(0).getCONTENT());
			int commentTotalnumber = CommentHelper.faq3_getCommentReply(communityAnswerPersistences.get(0).getCOMMUNITYANSWERID());
			personal2_CommunityView.setReplyNumber(Integer.toString(commentTotalnumber));
			personal2_CommunityView.setReplyTime(communityAnswerPersistences.get(0).getTIME());
			personal2_CommunityViews.add(personal2_CommunityView);
		}
		return personal2_CommunityViews;
	}
	
	/**
	 * zyq_personal2_ajax_获取问吧我的回答
	 */
	public static List<Personal2_CommunityView> getReplyCommunity(String userId) {
		List<Personal2_CommunityView> personal2_CommunityViews = new ArrayList<Personal2_CommunityView>();
		//查看评论
		List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.personal2_ReplyCommunity(userId,0,5);
		for(CommunityAnswerPersistence communityAnswerPersistence:communityAnswerPersistences){
			Personal2_CommunityView personal2_CommunityView = new Personal2_CommunityView();
			List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question2_getCommunity(communityAnswerPersistence.getCOMMUNITYQUESTIONID());
			personal2_CommunityView.setClassifyId(communityQuestionPersistences.get(0).getCLASSIFYID());
			List<ClassifyPersistence> classifyPersistences = ClassifyHelper.getInfoById(communityQuestionPersistences.get(0).getCLASSIFYID());
			personal2_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
			personal2_CommunityView.setClassifyImage(classifyPersistences.get(0).getFAQCLASSIFYIMAGE());
			personal2_CommunityView.setTitle(communityQuestionPersistences.get(0).getTITLE());		
			personal2_CommunityView.setQuestionId(communityAnswerPersistence.getCOMMUNITYQUESTIONID());
			if (communityAnswerPersistences.size()==5) {
				personal2_CommunityView.setIsMore("1");
			}else{
				personal2_CommunityView.setIsMore("0");
			}
			
			personal2_CommunityView.setIsreply("0");			
			personal2_CommunityView.setContent(communityAnswerPersistence.getCONTENT());
			int commentTotalnumber = CommentHelper.faq3_getCommentReply(communityAnswerPersistences.get(0).getCOMMUNITYANSWERID());
			personal2_CommunityView.setReplyNumber(Integer.toString(commentTotalnumber));
			personal2_CommunityView.setReplyTime(communityAnswerPersistence.getTIME());			
			personal2_CommunityViews.add(personal2_CommunityView);
		}
		return personal2_CommunityViews;
	}
	
	/**
	 * zyq_personal2_ajax_获取更多问吧的回答
	 */
	public static List<Personal2_CommunityView> getMoreReplyCommunity(String userid,int startNumber) {
		List<Personal2_CommunityView> personal2_CommunityViews = new ArrayList<Personal2_CommunityView>();
		//查看评论
		List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.personal2_ReplyCommunity(userid,startNumber,5);
		for(CommunityAnswerPersistence communityAnswerPersistence:communityAnswerPersistences){
			Personal2_CommunityView personal2_CommunityView = new Personal2_CommunityView();
			List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question2_getCommunity(communityAnswerPersistence.getCOMMUNITYQUESTIONID());
			personal2_CommunityView.setClassifyId(communityQuestionPersistences.get(0).getCLASSIFYID());
			List<ClassifyPersistence> classifyPersistences = ClassifyHelper.getInfoById(communityQuestionPersistences.get(0).getCLASSIFYID());
			personal2_CommunityView.setClassifyName(classifyPersistences.get(0).getFAQCLASSIFYNAME());
			personal2_CommunityView.setClassifyImage(classifyPersistences.get(0).getFAQCLASSIFYIMAGE());
			personal2_CommunityView.setTitle(communityQuestionPersistences.get(0).getTITLE());
			personal2_CommunityView.setQuestionId(communityAnswerPersistence.getCOMMUNITYQUESTIONID());
			if (communityAnswerPersistences.size()==5) {
				personal2_CommunityView.setIsMore("1");
			}else{
				personal2_CommunityView.setIsMore("0");
			}
			
			personal2_CommunityView.setIsreply("0");			
			personal2_CommunityView.setContent(communityAnswerPersistence.getCONTENT());
			int commentTotalnumber = CommentHelper.faq3_getCommentReply(communityAnswerPersistences.get(0).getCOMMUNITYANSWERID());
			personal2_CommunityView.setReplyNumber(Integer.toString(commentTotalnumber));
			personal2_CommunityView.setReplyTime(communityAnswerPersistence.getTIME());
			
			personal2_CommunityViews.add(personal2_CommunityView);
		}
		return personal2_CommunityViews;
	}
	
	/**
	 * zyq_personal2_ajax_获取关注
	 */
	public static List<Personal2_PayView> getPay(String userId){
		List<Personal2_PayView> personal2_PayViews = new ArrayList<Personal2_PayView>();
		List<PayPersistence> payPersistences2 = PayHelper.payList(userId);
		for(PayPersistence payPersistence:payPersistences2){
			Personal2_PayView personal2_PayView = new Personal2_PayView();
			//获取被关注用户信息
			List<UserPersistence> userPersistences = UserHelper.getUserInfoById(payPersistence.getBEPAYUSERID());			
			personal2_PayView.setUserId(userPersistences.get(0).getUSERID());
			personal2_PayView.setUserName(userPersistences.get(0).getUSERNAME());
			personal2_PayView.setUserImage(userPersistences.get(0).getAVATAR());
			//关注人数
			int payCount = PayHelper.payListSize(userPersistences.get(0).getUSERID());
			personal2_PayView.setPayNumber(Integer.toString(payCount));
			//粉丝数
			int bePayCount = PayHelper.bepayListSize(userPersistences.get(0).getUSERID());			
			personal2_PayView.setBepayNumber(Integer.toString(bePayCount));			
			List<ITPersistence> itPersistences = ITHelper.IT(userPersistences.get(0).getUSERID());
			if (itPersistences.size()!=0) {
				personal2_PayView.setWork(itPersistences.get(0).getGOODWORK());
			}						
			List<PayPersistence> persistences = PayHelper.getpayList(payPersistence.getBEPAYUSERID(), userId);
			List<PayPersistence> persistences2 = PayHelper.getpayList(userId,payPersistence.getBEPAYUSERID());
			if (persistences.size()!=0&&persistences2.size()!=0) {
				personal2_PayView.setIsTogetherPay("1");
			}else {
				personal2_PayView.setIsTogetherPay("0");
			}
			personal2_PayViews.add(personal2_PayView);
		}
		return personal2_PayViews;
	}
	
	/**
	 * zyq_personal2_ajax_获取关注
	 */
	public static List<Personal2_PayView> getbePay(String userId){
		List<Personal2_PayView> personal2_PayViews = new ArrayList<Personal2_PayView>();
		//回去当前用户被关注信息
		List<PayPersistence> payPersistences2 = PayHelper.bepayList(userId);
		for(PayPersistence payPersistence:payPersistences2){
			Personal2_PayView personal2_PayView = new Personal2_PayView();
			//获取关注userId用户的关注人信息
			List<UserPersistence> userPersistences = UserHelper.getUserInfoById(payPersistence.getPAYUSERID());
			personal2_PayView.setUserId(userPersistences.get(0).getUSERID());
			personal2_PayView.setUserName(userPersistences.get(0).getUSERNAME());
			personal2_PayView.setUserImage(userPersistences.get(0).getAVATAR());
			//关注人数
			int payCount = PayHelper.payListSize(userPersistences.get(0).getUSERID());			
			personal2_PayView.setPayNumber(Integer.toString(payCount));
			//粉丝数
			int bypayCount = PayHelper.bepayListSize(userPersistences.get(0).getUSERID());			
			personal2_PayView.setBepayNumber(Integer.toString(bypayCount));			
			List<ITPersistence> itPersistences = ITHelper.IT(userPersistences.get(0).getUSERID());
			if (itPersistences.size()!=0) {
				personal2_PayView.setWork(itPersistences.get(0).getGOODWORK());
			}			
			List<PayPersistence> persistences = PayHelper.getpayList(userId,payPersistence.getPAYUSERID());
			List<PayPersistence> persistences2 = PayHelper.getpayList(payPersistence.getPAYUSERID(),userId);
			if (persistences.size()!=0&&persistences2.size()!=0) {
				personal2_PayView.setIsTogetherPay("1");
			}else {
				personal2_PayView.setIsTogetherPay("0");
			}
			personal2_PayViews.add(personal2_PayView);
		}
		return personal2_PayViews;
	}
	
	/**
	 * zyq_personal2_关注
	 */
	public static void savePay(String userId, String touserId) {
		PayPersistence payPersistence = new PayPersistence();
		payPersistence.setPAYID(UUID.randomUUID().toString());
		payPersistence.setPAYUSERID(userId);
		payPersistence.setBEPAYUSERID(touserId);
		Date date=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        payPersistence.setTIME(time);
        PayHelper.savePay(payPersistence);
	}

}
