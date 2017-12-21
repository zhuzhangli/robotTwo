package org.xjtusicd3.database.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.RobotAnswerPersistence;

public interface RobotAnswerPersistenceMapper extends IBaseDao<RobotAnswerPersistence, String>{
	//查看是否已填写过满意度
	@Select("SELECT ROBOTANSWERID FROM TBL_RobotAnswer WHERE USERQUESTIONID = #{0}")
	String getQuertionInfo(String questionId);
	
	
	
	
	
	
	
	
	
	
	
	
	/*@Insert("INSERT INTO TBL_RobotAnswer(ROBOTANSWERID,SATICFACTION,USERQUESTIONID,FAQANSWERID,QUESTIONSTATE)"
			+ " VALUES (#{0},#{1},#{2},#{3},#{4})")
	void addUserSaticfaction(String robotAnswerId, int saticfaction, String questionId, String answerId, int questionState);*/

	
	//更新已处理的状态
	@Update("UPDATE TBL_RobotAnswer SET QUESTIONSTATE=#{1}  WHERE USERQUESTIONID=#{0}")
	void updateRobotAnswerState(String questionId, int questionState);


	

}
