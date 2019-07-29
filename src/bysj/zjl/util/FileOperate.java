package bysj.zjl.util;

import java.io.File;

/**
 * 文件操作
 * @author Administrator
 *
 */
public class FileOperate {
	public static void main(String[] args) {
		String path="D:\\bysjFile\\10\\2015\\1001\\1510064101\\入驻项目申报书（提交版）.doc";
		//delFile(path);
		//subString();
		absPath();
	}
	//文件删除
	public static void delFile(String path) {
		File file = new File(path);
		if(file.exists()) {
			if(file.isFile()) {
				file.delete();
			}
		}
	}
	//文件截取
	public static void subString() {
		String docAdviceId = "1510064101"+"1"+"02".substring(1);
		System.out.println(docAdviceId);
	}
	//获取tomcat物理路径
	public static void absPath() {
		String location="/doc/10/2015/1001/1510064101/151006410102.doc".substring(4).replaceAll("/","\\\\\\\\");
		System.out.println("I:\\\\SSMDM"+location);
	}
}