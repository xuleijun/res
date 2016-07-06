package co.jp.fujixerox.regulatory.util;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class JacobUtil {
	
	private static final Logger logger = LogManager.getLogger(JacobUtil.class.getName());
	
	// ８：htmlファイル
	public static final int WORD_HTML = 8;
	
	// 44：excelファイル
	public static final int EXCEL_HTML = 44;
	
	/**
	 * WordファイルHTML変換機能
	 * 
	 * @param docfile
	 *            WORDファイルパス
	 * @param outputPath
	 * 			  ファイルパス
	 * @param htmlfile
	 *            HTMLファイル名
     * @param batPath
     * 			  batパス
     * @param printtoPath
     * 			  printtoツールパス
	 */
	public static boolean wordToHtml(String docfile, String outputPath, String htmlfile, String batPath,
			String printtoPath, String exeFilePath) {
		
		String type = docfile.substring(docfile.lastIndexOf(".") + 1, docfile.length()).toLowerCase();
		
		if (StringUtils.isEmpty(docfile) || StringUtils.isEmpty(outputPath) || StringUtils.isEmpty(htmlfile)
				|| !("doc".equals(type) || "docx".equals(type))) {
			logger.error("word2htmlのパラメーターが不正です。");
			return false;
		}
		
		logger.info("*****word2html転換中...*****");

		try {
			SystemUtil.isExist(outputPath, null);
			Runtime rt = Runtime.getRuntime();
			System.out.println("cmd /c " + batPath + " " + "\"" + docfile+ "\"" + " " + outputPath + " " + printtoPath);
			Process process = rt.exec("cmd /c " + batPath + " " + "\"" + docfile+ "\"" + " " + outputPath + " " + printtoPath);
			process.waitFor();
		} catch (Exception e) {
			logger.error("word2html変換中で例外が発生しました。", e);
        	return false;
		}
		
		// wordファイル名
		String docFileName = docfile.substring(docfile.lastIndexOf(File.separator)+1);
		// pdfファイル名
		String pdfFileName = docFileName.substring(0, docFileName.lastIndexOf("."));
   	 	// pdfファイルパス
   	 	final String pdfFilePath = outputPath + File.separator + pdfFileName + ".pdf";
   	 	// システム日時
		String sysDate = SystemUtil.getSystemDate();
   	 	// HTML出力パス
		String htmlPath = outputPath + File.separator + sysDate;
		// BullZip PDF printer実行終了の判断
    	File file = new File(pdfFilePath);
    	boolean delFlg = false;
        while(true){
        	if (file.exists()) {
        		logger.info("docxファイルの変換が成功しました。");
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
        logger.info("*****word2html変換完了しました。********");
        
		// 入力Pdfファイルの削除
		if (delFlg) {
			SystemUtil.deleteFile(pdfFilePath);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ExcelファイルHTML変換機能
	 * 
	 * @param xlsfile
	 *            EXCELファイルパス
	 * @param htmlfile
	 *            変換後ファイル出力パス
	 */
	public static boolean excelToHtml(String xlsfile, String htmlfile) {
		
		String type = xlsfile.substring(xlsfile.lastIndexOf(".") + 1, xlsfile.length()).toLowerCase();
		if (StringUtils.isEmpty(xlsfile) || StringUtils.isEmpty(htmlfile)
				|| !("xls".equals(type) || "xlsx".equals(type))) {
			logger.error("excel2htmlのパラメーターが不正です。");
			return false;
		}

		// excelアプリケーションをスタートさせます
		ActiveXComponent app = new ActiveXComponent("Excel.Application");
		try {
			app.setProperty("Visible", new Variant(false));
			Dispatch excels = app.getProperty("Workbooks").toDispatch();
			Dispatch excel = Dispatch.invoke(excels, "Open", Dispatch.Method,
					new Object[] { xlsfile, new Variant(false), new Variant(true) }, new int[1]).toDispatch();
			Dispatch.invoke(excel, "SaveAs", Dispatch.Method, new Object[] { htmlfile, new Variant(EXCEL_HTML) },
					new int[1]);
			Variant f = new Variant(false);
			Dispatch.call(excel, "Close", f);
		} catch (Exception e) {
			logger.error("excel2html変換中で例外が発生しました。", e);
			return false;
		} finally {
			app.invoke("Quit", new Variant[] {});
		}
		logger.info("*****excel2html変換完了しました。********");
		return true;
	}
}
