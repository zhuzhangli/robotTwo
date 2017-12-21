package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.AgreePersistence;

public interface AgreePersistenceMapper extends IBaseDao<AgreePersistence, String>{
	//zyq_question2_通过社区答案Id和用户Id获取agreeId
	@Select("SELECT AGREEID FROM TBL_Agree WHERE COMMUNITYANSWERID=#{0} AND USERID=#{1}")
	String getAgree(String communityanswerId, String userid);
	
	/*
	 * zyq_question2_对于答案点赞
	 */
	@Insert("INSERT INTO TBL_Agree(AGREEID,COMMUNITYANSWERID,USERID,TOUSERID,TIME,ISNOTICE) VALUES (#{0},#{1},#{2},#{3},#{4},#{5})")
	void saveAgree(String agreeid, String communityanswerId, String userid,String touserid,String time,int isnotice);
	
	/*
	 * zyq_question2_取消点赞
	 */
	@Delete("DELETE FROM TBL_Agree WHERE AGREEID=#{0}")
	void deleteAgree(String agreeid);
	
	@Select("SELECT COUNT(1) FROM TBL_Agree WHERE COMMUNITYANSWERID=#{0}")
	int getAgreeSizeByAnswerId(String communityanswerId);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * zyq_question_查看用户点赞
	 */
	@Select("SELECT * FROM TBL_Agree WHERE USERID=#{0}")
	List<AgreePersistence> getAgreebyUserId(String userid);
	
}
