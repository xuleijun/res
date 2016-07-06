package co.jp.fujixerox.regulatory.servlet;

import co.jp.fujixerox.regulatory.util.JacobUtil;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class JacobUtilTest extends TestCase {
	
	private String docfile = null;
	private String outputPath = null;
	private String htmlfile = null;
	private String batPath = null;
	private String printtoPath = null;
	private String exeFilePath = null;

	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public JacobUtilTest(String testName)
    {
    	
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite(JacobUtilTest.class);
    }

    /**
     * 異常係
     * docファイルが未入力の場合
     */
	public void testCase1() {
		docfile = "";
		outputPath = "D:\\test\\output\\word2html\\test";
		htmlfile = "D:\\test\\output\\word2html\\test\\test.html";
		batPath = "D:\\test\\docuWorks2pdf\\print.bat";
		printtoPath = "D:\\test\\docuWorks2pdf";
		exeFilePath = "D:\\test\\pdf2htmlEX\\pdf2htmlEX.exe";
		boolean flg = JacobUtil.wordToHtml(docfile, outputPath, htmlfile, batPath, printtoPath, exeFilePath);
		assertFalse(flg);
	}
	
    /**
     * 異常係
     * ファイルパスが未入力の場合
     */
	public void testCase2() {
		docfile = "D:\\test\\test1.doc";
		outputPath = "";
		htmlfile = "D:\\test\\output\\word2html\\test\\test.html";
		batPath = "D:\\test\\docuWorks2pdf\\print.bat";
		printtoPath = "D:\\test\\docuWorks2pdf";
		exeFilePath = "D:\\test\\pdf2htmlEX\\pdf2htmlEX.exe";
		boolean flg = JacobUtil.wordToHtml(docfile, outputPath, htmlfile, batPath, printtoPath, exeFilePath);
		assertFalse(flg);
	}
	
    /**
     * 異常係
     * 変換後ファイル出力パスが未入力の場合
     */
	public void testCase3() {
		docfile = "D:\\test\\input\\word2html\\test1.doc";
		outputPath = "D:\\test\\output\\word2html\\test";
		htmlfile = "";
		batPath = "D:\\test\\docuWorks2pdf\\print.bat";
		printtoPath = "D:\\test\\docuWorks2pdf";
		exeFilePath = "D:\\test\\pdf2htmlEX\\pdf2htmlEX.exe";
		boolean flg = JacobUtil.wordToHtml(docfile, outputPath, htmlfile, batPath, printtoPath, exeFilePath);
		assertFalse(flg);
	}
	
    /**
     * 異常係
     * 入力ファイルが不正の場合
     */
	public void testCase4() {
		docfile = "D:\\test\\test.txt";
		outputPath = "D:\\test\\output\\word2html\\test";
		htmlfile = "D:\\test\\output\\word2html\\test\\test.html";
		batPath = "D:\\test\\docuWorks2pdf\\print.bat";
		printtoPath = "D:\\test\\docuWorks2pdf";
		exeFilePath = "D:\\test\\pdf2htmlEX\\pdf2htmlEX.exe";
		boolean flg = JacobUtil.wordToHtml(docfile, outputPath, htmlfile, batPath, printtoPath, exeFilePath);
		assertFalse(flg);
	}
	
    /**
     * 異常係
     * ファイル変換異常
     * 出力フォルダをリードオンリーに設定する
     */
//	public void testCase5() {
//		docfile = "D:\\test\\test1.doc";
//		outputPath = "D:\\test\\output\\word2html\\readonly";
//		htmlfile = "D:\\test\\output\\word2html\\readonly\\txtfile.html";
//		batPath = "D:\\test\\docuWorks2pdf\\print.bat";
//		printtoPath = "D:\\test\\docuWorks2pdf";
//		exeFilePath = "D:\\test\\pdf2htmlEX\\pdf2htmlEX.exe";
//		boolean flg = JacobUtil.wordToHtml(docfile, outputPath, htmlfile, batPath, printtoPath, exeFilePath);
//		assertFalse(flg);
//	}
	
    /**
     * 正常係
     * docファイル
     */
	public void testCase6() {
		docfile = "D:\\test\\test1.doc";
		outputPath = "D:\\test\\output\\word2html\\test";
		htmlfile = "D:\\test\\output\\word2html\\test\\test1.html";
		batPath = "D:\\test\\docuWorks2pdf\\print.bat";
		printtoPath = "D:\\test\\docuWorks2pdf";
		exeFilePath = "D:\\test\\pdf2htmlEX\\pdf2htmlEX.exe";
		boolean flg = JacobUtil.wordToHtml(docfile, outputPath, htmlfile, batPath, printtoPath, exeFilePath);
		assertTrue(flg);
	}
	
    /**
     * 正常係
     * docxファイル
     */
	public void testCase7() {
		docfile = "D:\\test\\test2.docx";
		outputPath = "D:\\test\\output\\word2html\\test";
		htmlfile = "D:\\test\\output\\word2html\\test\\test2.html";
		batPath = "D:\\test\\docuWorks2pdf\\print.bat";
		printtoPath = "D:\\test\\docuWorks2pdf";
		exeFilePath = "D:\\test\\pdf2htmlEX\\pdf2htmlEX.exe";
		boolean flg = JacobUtil.wordToHtml(docfile, outputPath, htmlfile, batPath, printtoPath, exeFilePath);
		assertTrue(flg);
	}

}
