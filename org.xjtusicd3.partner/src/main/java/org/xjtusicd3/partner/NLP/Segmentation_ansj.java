package org.xjtusicd3.partner.NLP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.ansj.domain.Result;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.tire.library.Library;
import org.xjtusicd3.database.helper.AnswerHelper;
import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.model.AnswerPersistence;
import org.xjtusicd3.database.model.NLP_Word;
import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.partner.view.robot_Chat;
import org.xjtusicd3.partner.view.robot_ChatView;

public class Segmentation_ansj {
	/**
	 * author:zhaoyanqing
	 * abstract:聊天机器人接口，用户根据自然语言提问，利用ansj将分好的词构建词频VSM模型，随后利用余弦进行相似度计算
	 * date:2017年8月18日 18:51:34
	 */
	public static List<robot_Chat> robot_Chats(String comment) throws Exception{
		List<NLP_Word> nList = Segmentation_ansj.similarScoreFirst(comment);

		//!!!String localurl = System.getProperty("user.dir");
		String tomcatPath = System.getProperty("user.dir").replace("bin", "webapps");

		File file1 = new File(tomcatPath+"/org.xjtusicd3.partner/library/faqKeywords.txt");//Text文件
		BufferedReader br = new BufferedReader(new FileReader(file1));//构造一个BufferedReader类来读取文件
		String s = null;
		List<robot_Chat> robot_Chats = new ArrayList<robot_Chat>();
		while((s = br.readLine())!=null){//使用readLine方法，一次读一行
			robot_Chat robot_Chat = new robot_Chat();
			String[] resultArray = s.split("\t");
			double value = Segmentation_ansj.similarScoreSecond(nList, resultArray[1]);
			if (value>=0.4) {
				robot_Chat.setQuestionId(resultArray[0]);
				robot_Chat.setValue(value);
				robot_Chats.add(robot_Chat);
			}
		}
		br.close();
		return robot_Chats;
	}
	
	/**
	 * author:zhaoyanqing
	 * abstract:利用ansj将分好的词，根据词频做VSM模型
	 * date:2017年8月18日 18:51:34
	 */
	public static List<NLP_Word> similarScoreFirst(String text) throws Exception{
//!!!		String localurl = System.getProperty("user.dir");
		String localurl = System.getProperty("user.dir").replace("bin", "webapps");
		
		Forest forest = Library.makeForest(localurl+"/org.xjtusicd3.partner/library/computer.dic");
		Result terms = ToAnalysis.parse(text,forest);

		Result result = terms.recognition(StopLibrary.get());
		String aString = result.toStringWithOutNature();
		String[] resultArray = aString.split(",");
		List<NLP_Word> list = new ArrayList<NLP_Word>();
		for(int i=0;i<resultArray.length;i++){
			NLP_Word nlp_Word = new NLP_Word();
			nlp_Word.setText(resultArray[i]);
			nlp_Word.setFrequency(0);
			list.add(nlp_Word);
		}
		return list;
	}
	
	public static double similarScoreSecond(List<NLP_Word> list,String text) throws Exception{

		String[] resultArray = text.split(",");

		//把得到的语句分词
		List<NLP_Word> list1 = new ArrayList<NLP_Word>();
		for(int i=0;i<resultArray.length;i++){
			NLP_Word nlp_Word = new NLP_Word();
			nlp_Word.setText(resultArray[i]);
			nlp_Word.setFrequency(0);
			list1.add(nlp_Word);
		}

		double score =  TextSimilarity.similarScore(list, list1);
		return score;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static List<robot_ChatView> similarScoreSecond1() throws Exception{
		//!!!String localurl = System.getProperty("user.dir");
		
		String tomcatPath = System.getProperty("user.dir").replace("bin", "webapps");
		
		Forest forest = Library.makeForest(tomcatPath+"/org.xjtusicd3.partner/library/computer.dic");
		List<QuestionPersistence> questionPersistences = QuestionHelper.getFaqTotal();
		List<robot_ChatView> robot_ChatViews = new ArrayList<robot_ChatView>();
		for(QuestionPersistence questionPersistence:questionPersistences){
			Result terms = ToAnalysis.parse(questionPersistence.getFAQTITLE(),forest);
			String result = terms.recognition(StopLibrary.get()).toStringWithOutNature(",");
			String[] resultArray = result.split(",");
			//把得到的语句分词
			List<NLP_Word> list1 = new ArrayList<NLP_Word>();
			for(int i=0;i<resultArray.length;i++){
				NLP_Word nlp_Word = new NLP_Word();
				nlp_Word.setText(resultArray[i]);
				nlp_Word.setFrequency(0);
				list1.add(nlp_Word);
			}
			robot_ChatView robot_ChatView = new robot_ChatView();
			robot_ChatView.setNlp_Words(list1);
			
			robot_ChatView.setQuestion(questionPersistence.getFAQTITLE());
			robot_ChatView.setQuestionId(questionPersistence.getFAQQUESTIONID());
			List<AnswerPersistence> answerPersistences = AnswerHelper.faq3_faqContent(questionPersistence.getFAQQUESTIONID());
			robot_ChatView.setAnswer(answerPersistences.get(0).getFAQCONTENT());
			robot_ChatViews.add(robot_ChatView);
		}
		return robot_ChatViews;
	}


}
