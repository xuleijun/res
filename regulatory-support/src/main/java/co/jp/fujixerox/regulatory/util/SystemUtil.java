package co.jp.fujixerox.regulatory.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class SystemUtil {
	
	 /**
	  * ファイルあるいはフォルダが存在するかどうかを判断します
	  * @param folderPath フォルダーパス
	  * @param filePath     ファイルパス
	  * @throws IOException 
	  */
	 public static void isExist(String folderPath, String filePath) throws IOException {
		 if (StringUtils.isNotEmpty(folderPath)) {
			 
			 File folder = new File(folderPath);
			 //　フォルダーが存在しないの場合
			 if (!folder.exists()  && !folder .isDirectory()) {
				 folder.mkdirs();
			 }
		 }
		 if (StringUtils.isNotEmpty(filePath)) {
			 
			 File file = new File(filePath);
			 //　ファイルが存在しないの場合
			 if (!file.exists()) {
				 file.createNewFile();
			 }
		 }
	 }
	 
	 /**
	  * ファイルあるいはフォルダが存在するかどうかを判断します
	  * 
	  * @retrun システム日時
	  */
	 public static String getSystemDate() {
		 
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		 String sysDate = sdf.format( new  Date());
		 
		 return sysDate;
	 }
	 
	 /**
	  * フォルダの下ですべてのファイルを取得する
	  * 
	  * @param dir 入力フォルダー
	  * @retrun filelist ファイルリスト
	  */
	public static List<String> getAllFiles(String dir) throws Exception {
		List<String> filelist = new ArrayList<String>();
		File root = new File(dir);
		File[] files = root.listFiles();
		for(File file : files){
			if (!file.isDirectory()) {
				filelist.add(file.getAbsolutePath());
			}
		}
		return filelist;
	}
	
	/**
	 * ファイルを削除する
	 * 
	 * @param sPath ファイルパス
	 */
	public static void deleteFile(String sPath) {
	    File file = new File(sPath);  
	    // ファイルが存在する場合  
	    if (file.exists() && file.isFile()) {
	    	file.delete();
	    }  
	} 
}
