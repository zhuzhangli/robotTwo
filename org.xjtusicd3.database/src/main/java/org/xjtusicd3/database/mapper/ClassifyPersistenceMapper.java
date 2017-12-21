package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.ClassifyPersistence;

public interface ClassifyPersistenceMapper  extends IBaseDao<ClassifyPersistence, String>{
	// zyq_robot_分类_获取一级分类
	@Select("SELECT FAQCLASSIFYID,FAQCLASSIFYNAME FROM TBL_FAQclassify WHERE FAQPARENTID='0'")
	public List<ClassifyPersistence> FirstClassify_robot();
	
	// zyq_robot_分类_获取二级分类
	@Select("SELECT TBL_FAQclassify.FAQCLASSIFYID,TBL_FAQclassify.FAQCLASSIFYNAME,sum(TBL_FAQquestion.COLLECTION) as a FROM TBL_FAQclassify,TBL_FAQquestion WHERE TBL_FAQclassify.FAQCLASSIFYID=TBL_FAQquestion.FAQCLASSIFYID AND TBL_FAQclassify.FAQPARENTID=#{0} GROUP BY TBL_FAQquestion.FAQCLASSIFYID ORDER BY a DESC")
	public List<ClassifyPersistence> SecondClassify_robot(String ParentId);
	
	// zyq_question_查看问答模块的分类
	@Select("SELECT FAQCLASSIFYID FROM TBL_FAQclassify WHERE FAQCLASSIFYNAME=#{0} AND FAQPARENTID=#{1}")
	public String question_ClassifyListByName(String ClassifyName,String faqParentId);
	
	//通过分类id查找分类名
	@Select("SELECT FAQCLASSIFYNAME FROM TBL_FAQclassify WHERE FAQCLASSIFYID=#{0}")
	public String getClassifyNameById(String classifyId);
	
	// abstract:获取当前问题分类的上一级分类
	@Select("SELECT FAQPARENTID FROM TBL_FAQclassify WHERE FAQCLASSIFYID=#{0}")
	public String faq_parentId(String faq_classifyId);
	
	//根据分类号查找分类信息
	@Select("SELECT * FROM TBL_FAQclassify WHERE FAQCLASSIFYID=#{0} ")
	public List<ClassifyPersistence> getInfoById(String classifyId);
	
	// 获取parentId的下一级分类信息	
	@Select("SELECT TBL_FAQclassify.FAQCLASSIFYID,TBL_FAQclassify.FAQCLASSIFYNAME,sum(TBL_FAQquestion.SCAN+TBL_FAQquestion.COLLECTION*10) as a FROM TBL_FAQclassify,TBL_FAQquestion WHERE TBL_FAQclassify.FAQCLASSIFYID=TBL_FAQquestion.FAQCLASSIFYID AND TBL_FAQclassify.FAQPARENTID=#{0} GROUP BY TBL_FAQquestion.FAQCLASSIFYID ORDER BY a DESC")
	public List<ClassifyPersistence> SecondClassify_robot2(String ParentId);
	
	//zyq_faq1_下面4栏推荐_按照浏览量	 
	@Select("SELECT TBL_FAQclassify.FAQCLASSIFYID,TBL_FAQclassify.FAQCLASSIFYNAME,sum(SCAN) as a FROM TBL_FAQquestion,TBL_FAQclassify WHERE TBL_FAQquestion.FAQCLASSIFYID = TBL_FAQclassify.FAQCLASSIFYID AND TBL_FAQclassify.FAQPARENTID=#{0} AND TBL_FAQquestion.FAQSTATE = 2 GROUP BY TBL_FAQquestion.FAQCLASSIFYID ORDER BY a DESC LIMIT 4")
	public List<ClassifyPersistence> faq1_SecondClassify(String ParentId);
	
	//根据分类号查找父id
	@Select("SELECT FAQPARENTID FROM TBL_FAQclassify WHERE FAQCLASSIFYID=#{0}")
	public String faq2_classifyParentId(String ClassifyId);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * zyq_spider_按照分类名称查找
	 */
	@Select("SELECT * FROM TBL_FAQclassify WHERE FAQCLASSIFYNAME=#{0} AND FAQPARENTID=#{1}")
	public List<ClassifyPersistence> spider_ClassifyListByName(String ClassifyName,String parentId);
}
