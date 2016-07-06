package co.jp.fujixerox.regulatory.util;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DocuWorksUtil {
	
	private final static Logger logger = LogManager.getLogger(DocuWorksUtil.class.getName());
	
    /**
     * DocuWorksファイルPdf変換機能
     * 
     * @param xdwfile
     * 			  xdwファイルパス
     * @param batPath
     * 			  batパス
     * @param printtoPath
     * 			  printtoツールパス
     * @param outputPath
     * 			　pdfファイルパス
     * @param exeFilePath
     * 			  pdf2htmlEXファイルパス
     * @return
     */
	public static boolean print(String xdwfile, String batPath, String printtoPath, String outputPath,
			String exeFilePath) {
		
		String type = xdwfile.substring(xdwfile.lastIndexOf(".") + 1, xdwfile.length()).toLowerCase();
		
		if(StringUtils.isEmpty(xdwfile) || !type.equals("xdw")) {
            logger.error("dcuworks2htmlのパラメーターが不正です。");
            return false;
        }
		
		Runtime rt = Runtime.getRuntime();
		try {
			System.out.println("cmd /c " + batPath + " " + "\"" + xdwfile + "\"" + " " + outputPath + " " + printtoPath);
			Process process = rt.exec("cmd /c " + batPath + " " + "\"" + xdwfile + "\"" + " " + outputPath + " " + printtoPath);
			process.waitFor();
		} catch (Exception e) {
			logger.error("dcuworks2html変換中で例外が発生しました。", e);
        	return false;
		}
		
		// xdwファイル名
		String xdwlFileName = xdwfile.substring(xdwfile.lastIndexOf(File.separator)+1);
		// pdfファイル名
		String pdfFileName = xdwlFileName.substring(0, xdwlFileName.lastIndexOf("."));
   	 	// pdfファイルパス
   	 	String pdfFilePath = outputPath + File.separator + pdfFileName + ".pdf";
   	 	// システム日時
		String sysDate = SystemUtil.getSystemDate();
   	 	// HTML出力パス
		String htmlPath = outputPath + File.separator + sysDate;
		
		// BullZip PDF printer実行終了の判断
    	File file = new File(pdfFilePath);
    	boolean delFlg = false;
        while(true){
        	if (file.exists()) {
        		logger.info("DocuWorksファイルの変換が成功しました。");
        		// pdf2html
        		delFlg = Pdf2HtmlUtil.convertToHtmlEX(exeFilePath, pdfFilePath, htmlPath, pdfFileName);
				break;
			}
            try {
                Thread.sleep(2000);  
            } catch (InterruptedException e) {
                e.printStackTrace();
            }  
        }
		
		// 入力Pdfファイルの削除
		if (delFlg) {
			SystemUtil.deleteFile(pdfFilePath);
			return true;
		} else {
			return false;
		}
	}
}
