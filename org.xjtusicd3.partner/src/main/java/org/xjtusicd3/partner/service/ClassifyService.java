package org.xjtusicd3.partner.service;

import java.util.ArrayList;
import java.util.List;

import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.partner.view.Faq1_ClassifyView;
import org.xjtusicd3.partner.view.Faq1_faqContentView;
public class ClassifyService {
	/**
	 * robot-分类
	 */
	public static String classify(){
		int num  = 1;
		//获取所有一级分类
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.classifyName();
		int length = classifyPersistences.size()+1;
		String string = "";
		for(ClassifyPersistence classifyPersistence:classifyPersistences){
			String firstTitle = "";
			String content = "";
			String secondTitle = "";
			String faqTitle = "";
			String content_string = "{\"title\":\"";
			String firstTitle_string = "{\"title\":\"";
			//获取所有二级分类
			List<ClassifyPersistence> classifyPersistences2 = ClassifyHelper.classifyName2(classifyPersistence.getFAQCLASSIFYID());
			int length2 = classifyPersistences2.size()+1;
			for(ClassifyPersistence classifyPersistence2:classifyPersistences2){
				String content2 = "";
				//获取该二级分类下faq问题中收藏量前三的faq
				List<QuestionPersistence> faqPersistences = QuestionHelper.SecondClassify_robot(classifyPersistence2.getFAQCLASSIFYID());
				int length3 = faqPersistences.size()+1;
				for(QuestionPersistence faqPersistence:faqPersistences){
					length3--;
					faqTitle = zhuanyi(faqPersistence.getFAQTITLE());
					content2 += "{\"faqTitle\":\""+faqTitle+"\"}";
					if (length3>1) {
						content2 += ",";
					}else {
						content2 += "";
					}
				}
				length2--;
				secondTitle = classifyPersistence2.getFAQCLASSIFYNAME();
				content +=content_string+secondTitle+"\",\"content\":["+content2+ "]}";
				if (length2>1) {
					content += ",";
				}else {
					content += "";
				}
			}
				firstTitle = classifyPersistence.getFAQCLASSIFYNAME();
				string += firstTitle_string+firstTitle+"\","+"\"id\":\"speedMenu"+num+"\","+"\"content\":["+content+"]"+"}";
				num++;
				length--;
				if (length>1) {
					string += ",";
				}else {
					string += "";
				}
			}
		return string;
		}
	
	/**
	 * 标题转义
	 */
	public static String zhuanyi(String string){
		string = string.replace("\"", "'");
		return string;
	}
	
	/**
	 * faq1_下面4栏推荐_按照浏览量
	 */
	public static List<Faq1_ClassifyView> faq1_ClassifyView(String parentId){
		List<Faq1_ClassifyView> faq1_ClassifyViews = new ArrayList<Faq1_ClassifyView>();
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.faq1_SecondClassify(parentId);
		for(ClassifyPersistence classifyPersistence:classifyPersistences){
			//查找推荐的第一条数据
			QuestionPersistence questionPersistences = QuestionHelper.faq1_faqPersistences(classifyPersistence.getFAQCLASSIFYID());
			Faq1_faqContentView faq1View = new Faq1_faqContentView(questionPersistences);
			//查找推荐的剩余5条
			List<Faq1_faqContentView> faq1Views2 = new ArrayList<Faq1_faqContentView>();
			List<QuestionPersistence> questionPersistences2 = QuestionHelper.faq1_faqPersistences2(classifyPersistence.getFAQCLASSIFYID());
			for(QuestionPersistence questionPersistence:questionPersistences2){
				Faq1_faqContentView faq2View = new Faq1_faqContentView(questionPersistence);
				faq1Views2.add(faq2View);
			}
			Faq1_ClassifyView view = new Faq1_ClassifyView(classifyPersistence);
			view.setContent(faq1View);
			view.setContent2(faq1Views2);
			faq1_ClassifyViews.add(view);
		}
		return faq1_ClassifyViews;
	}
	
	
	/**
	 * 获取该分类的父分类信息
	 */
	public static List<ClassifyPersistence> faq2_classify(String ClassifyId){
		//根据分类号查找父id
		String classifyParentId = ClassifyHelper.faq2_classifyParentId(ClassifyId);
		//查找父分类信息
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.getInfoById(classifyParentId);
		return classifyPersistences;
	}
	
	/**
	 * 获取该分类的信息
	 */
	public static List<ClassifyPersistence> faq2_classify2(String ClassifyId){
		//获取该分类信息
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.getInfoById(ClassifyId);
		return classifyPersistences;
	}

}
