package co.jp.fujixerox.regulatory.util;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Convert2HtmlTimerTask extends TimerTask {

	private static Logger logger = LogManager.getLogger(Convert2HtmlTimerTask.class);
	private static boolean isRunning = false;
	private Properties props = null;

	public Convert2HtmlTimerTask() {
		try {
			props = new Properties();
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("system.properties");
			props.load(in);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public void run() {
		try {
			if (!isRunning) {
				isRunning = true;
				logger.info("バッチ処理開始...");

				// Pdf入力フォルダー
				String pdfPath = props.getProperty("batchinput_pdf");
				String wordPath = props.getProperty("batchinput_word");
				String dcuworksPath = props.getProperty("batchinput_dcuworks");
				String excelPath = props.getProperty("batchinput_excel");
				// pdf2htmlEX ファイルパス
				String exeFilePath = props.getProperty("toolpath");
				// DocuWorks2Pdf ツールパス
				String batPath = props.getProperty("file2pdfBat");
				// printto.exeパス
				String printtoPath = props.getProperty("printtoPath");

				// フォルダーが存在しないの場合
				SystemUtil.isExist(pdfPath, null);
				SystemUtil.isExist(wordPath, null);
				SystemUtil.isExist(dcuworksPath, null);
				SystemUtil.isExist(excelPath, null);

				// Pdfファイルリスト
				List<String> pdfFileList = SystemUtil.getAllFiles(pdfPath);
				boolean pdfDeleteFlg = false;
				if (pdfFileList.size() == 0) {
					logger.info("変換するのPDFファイルが見つかりません。");
				} else {
					for (String pdfFilePath : pdfFileList) {
						// Pdfファイル名
						String pdflFileName = pdfFilePath.substring(pdfFilePath.lastIndexOf("\\") + 1);
						// HTMLファイル名
						String htmlFileName = pdflFileName.substring(0, pdflFileName.lastIndexOf("."));
						// 拡張子
						String type = pdfFilePath.substring(pdfFilePath.lastIndexOf(".") + 1, pdfFilePath.length())
								.toLowerCase();
						// システム日時
						String sysDate = SystemUtil.getSystemDate();
						// 出力パス
						String outputPath = props.getProperty("pdf2htmloutput") + File.separator + sysDate;
						if ("pdf".equals(type)) {
							pdfDeleteFlg = Pdf2HtmlUtil.convertToHtmlEX(exeFilePath, pdfFilePath, outputPath,
									htmlFileName);
							// 入力Pdfファイルの削除
							if (pdfDeleteFlg) {
								SystemUtil.deleteFile(pdfFilePath);
							}
						}
					}
				}

				// wordファイルリスト
				List<String> wordFileList = SystemUtil.getAllFiles(wordPath);
				boolean wordDeleteFlg = false;
				if (wordFileList.size() == 0) {
					logger.info("変換するのWORDファイルが見つかりません。");
				} else {
					for (String wordFilePath : wordFileList) {
						// Wordファイル名
						String wordFileName = wordFilePath.substring(wordFilePath.lastIndexOf("\\") + 1);
						// HTMLファイル名
						String htmlFileName = wordFileName.substring(0, wordFileName.lastIndexOf("."));
						// システム日時
						String sysDate = SystemUtil.getSystemDate();
						// 出力パス
						String outputPath = props.getProperty("word2htmloutput") + File.separator + sysDate;
						// 拡張子
						String type = wordFilePath.substring(wordFilePath.lastIndexOf(".") + 1, wordFilePath.length())
								.toLowerCase();

						if ("doc".equals(type) || "docx".equals(type)) {
							wordDeleteFlg = JacobUtil.wordToHtml(wordFilePath, outputPath,
									outputPath + File.separator + htmlFileName + ".html", batPath, printtoPath,
									exeFilePath);
							// 入力Wordファイルの削除
							if (wordDeleteFlg) {
								SystemUtil.deleteFile(wordFilePath);
							}
						}
					}
				}
				
				// dcuworksファイルリスト
				List<String> xdwFileList = SystemUtil.getAllFiles(dcuworksPath);
				boolean xdwDeleteFlg = false;
				if (xdwFileList.size() == 0) {
					logger.info("変換するのDcuworksファイルが見つかりません。");
				} else {
					for (String xdwFilePath : xdwFileList) {
						String type = xdwFilePath.substring(xdwFilePath.lastIndexOf(".") + 1,
								xdwFilePath.length()).toLowerCase();
						// PDF出力パス
						String outputPath = props.getProperty("docuWorks2pdfOutput");
						if ("xdw".equals(type)) {
							
							// Dcuworksファイル変換
							xdwDeleteFlg = DocuWorksUtil.print(xdwFilePath, batPath, printtoPath, outputPath,
									exeFilePath);
							// 入力xdwファイルの削除
							if (xdwDeleteFlg) {
								SystemUtil.deleteFile(xdwFilePath);
							}
						}
					}
				}
				
				// excelファイルリスト
				List<String> excelFileList = SystemUtil.getAllFiles(excelPath);
				boolean excelDeleteFlg = false;
				if (excelFileList.size() == 0) {
					logger.info("変換するのExcelファイルが見つかりません。");
				} else {
					for (String excelFilePath : excelFileList) {
						// Excelファイル名
						String excelFileName = excelFilePath.substring(excelFilePath.lastIndexOf("\\") + 1);
						// HTMLファイル名
						String htmlFileName = excelFileName.substring(0, excelFileName.lastIndexOf("."));
						// システム日時
						String sysDate = SystemUtil.getSystemDate();
						// 出力パス
						String outputPath = props.getProperty("excel2htmloutput") + File.separator + sysDate;
						// 拡張子
						String type = excelFilePath.substring(excelFilePath.lastIndexOf(".") + 1, excelFilePath.length())
								.toLowerCase();

						if ("xls".equals(type) || "xlsx".equals(type)) {
							SystemUtil.isExist(outputPath, null);
							excelDeleteFlg = JacobUtil.excelToHtml(excelFilePath, outputPath + File.separator
									+ htmlFileName + ".html");
							// 入力Excelファイルの削除
							if (excelDeleteFlg) {
								SystemUtil.deleteFile(excelFilePath);
							}
						}
					}
				}
				
				logger.info("バッチ処理 終了...");
				isRunning = false;
			} else {
				logger.info("前回任務は終わっていませんでした。");
			}
		} catch (Exception e) {
			logger.error("バッチ処理実行中で例外が発生しました。", e);
		}
	}
}
