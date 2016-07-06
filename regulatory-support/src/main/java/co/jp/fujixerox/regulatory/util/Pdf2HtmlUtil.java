package co.jp.fujixerox.regulatory.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Pdf2HtmlUtil {
	
	private static final Logger logger = LogManager.getLogger(Pdf2HtmlUtil.class.getName());
	
    /** 
     * PDFファィルHTML変換
     * @param exeFilePath pdf2htmlEX.exeファイルパス
     * @param pdfFile PDFファイルパス
     * @param destDir HTMLファイル出力パス
     * @param htmlFileName HTMLファイル名
     * @return 
     */
	public static boolean convertToHtmlEX(String exeFilePath, String pdfFile, String destDir, String htmlFileName) {
        try {
        	// 拡張子
			String type = pdfFile.substring(pdfFile.lastIndexOf(".") + 1, pdfFile.length()).toLowerCase();
	        if(StringUtils.isEmpty(exeFilePath)
	                || StringUtils.isEmpty(pdfFile)
	                || !"pdf".equals(type)
	                || StringUtils.isEmpty(htmlFileName)) {
	            logger.error("pdf2htmlのパラメーターが不正です。");
	            return false;
	        }
	        SystemUtil.isExist(destDir, null);
	        Runtime rt = Runtime.getRuntime();
	        StringBuilder command = new StringBuilder();
	        command.append(exeFilePath).append(" ");
	        
	        // 実行コマンド
	        if(destDir != null  &&  !"".equals(destDir.trim())) {
	        	command.append("--dest-dir ").append(destDir.replace(" ", "\" \"")).append(" ");
	        	// HTML元素の数量(default: 0)
	        	command.append("--optimize-text 1 ");
	        	command.append("--zoom 1.4 ");
	        	//htmlの中でリンクを表示する：0——false，1——true  
	        	command.append("--process-outline 0 ");
	        	//フォント(default ttf) ttf,otf,woff,svg
	        	command.append("--font-format woff ");
	        	//ファイルのパスの中の空欄を削除します
	        	command.append(pdfFile.replace(" ", "\" \"")).append(" ");
	        	command.append(htmlFileName.replace(" ", "\" \""));
	        }
            if(htmlFileName.indexOf(".html")==-1)  {
            	command.append(".html");  
            }

	        logger.info("Command："+command.toString());  
            Process p = rt.exec(command.toString());  
            StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(), "Message");
            // スクリーンエラー流
            errorGobbler.start();    
            StreamGobbler outGobbler = new StreamGobbler(p.getInputStream(), "STDOUT");
            // スクリーン輸出流
            outGobbler.start();
            int w = p.waitFor();
            int v = p.exitValue();
            if(w==0 && v==0){
                return true;
            }  
        } catch (Exception e) {
        	logger.error("pdf2html変換中で例外が発生しました。", e);
        	return false;
        }  
        return false;
	}
}
