package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.ClassifyPersistenceMapper;
import org.xjtusicd3.database.model.ClassifyPersistence;


public class ClassifyHelper {
	/**
	 * 获取一级分类
	 */
	public static List<ClassifyPersistence> classifyName(){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		List<ClassifyPersistence> list = mapper.FirstClassify_robot();
		session.close();
		return list;
	}	
	
	/**
	 * 获取二级分类
	 */
	public static List<ClassifyPersistence> classifyName2(String ParentId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		List<ClassifyPersistence> list = mapper.SecondClassify_robot(ParentId);
		session.close();
		return list;
	}
	
	/**
	 * zyq_question_查找分类名是ClassifyName，faq父id是FAQPARENTID的一级分类
	 */
	public static String question_ClassifyListByName(String ClassifyName,String faqParentId)
	{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		String list = mapper.question_ClassifyListByName(ClassifyName,faqParentId);
		session.close();
		return list;
	}
	
	/**
	 * 通过分类id查找分类名
	 * @param classifyId
	 * @return
	 */
	public static String getClassifyNameById(String classifyId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		String classifyName = mapper.getClassifyNameById(classifyId);
		session.close();
		return classifyName;
	}
	
	/**
	 * author:zzl
	 * abstract:获取当前问题分类的上一级分类
	 * data:2017年9月15日09:58:36
	 */
	public static String faq_parentId(String faq_classifyId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		String classifyParentId = mapper.faq_parentId(faq_classifyId);
		session.close();
		return classifyParentId;
	}
	
	/**
	 * author:zzl
	 * abstract:获取分类信息
	 * data:2017年9月17日19:33:19
	 */
	public static List<ClassifyPersistence> getInfoById(String classifyId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		List<ClassifyPersistence> list = mapper.getInfoById(classifyId);
		session.close();
		return list;
	}
	
		
	/**
	 * 获取parentId的下一级分类信息
	 */
	public static List<ClassifyPersistence> faq1_ClassifyName(String parentId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		List<ClassifyPersistence> list = mapper.SecondClassify_robot2(parentId);
		session.close();
		return list;
	}
	
	/**
	 * faq1_下面4栏推荐_按照浏览量_limit4
	 */
	public static List<ClassifyPersistence> faq1_SecondClassify(String parentId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		List<ClassifyPersistence> list = mapper.faq1_SecondClassify(parentId);
		session.close();
		return list;
	}
	
	/**
	 * 根据分类号查找父id
	 */
	public static String faq2_classifyParentId(String ClassifyId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		String classifyParentId = mapper.faq2_classifyParentId(ClassifyId);
		session.close();
		return classifyParentId;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * spider_分类的添加
	 */
	public static void save(ClassifyPersistence classifyPersistence) throws Exception{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		mapper.save(classifyPersistence);
		session.close();
	}
	/*
	 * spider_按照分类名称查找
	 */
	public static List<ClassifyPersistence> spider_ClassifyListByName(String ClassifyName,String parentId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		List<ClassifyPersistence> list = mapper.spider_ClassifyListByName(ClassifyName,parentId);
		session.close();
		return list;
	}	
}
