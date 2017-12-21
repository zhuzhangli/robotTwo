package org.xjtusicd3.partner.filter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadTxt {
	public static void main(String args[])throws Exception{
		File file = new File("E:\\faqKeywords.txt");//Text文件
		BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
		String s = null;
		while((s = br.readLine())!=null){//使用readLine方法，一次读一行
			System.out.println(s);
			String[] resultArray = s.split("\t");
			System.out.println(resultArray[1]);
		}
		br.close();
	}
}
