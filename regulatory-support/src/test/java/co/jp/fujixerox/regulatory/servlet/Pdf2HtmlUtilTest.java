package co.jp.fujixerox.regulatory.servlet;

import co.jp.fujixerox.regulatory.util.Pdf2HtmlUtil;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class Pdf2HtmlUtilTest extends TestCase {
	
	private String exeFilePath = null;
	private String pdfFile = null;
	private String destDir = null;
	private String htmlFileName = null;

	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public Pdf2HtmlUtilTest(String testName){
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(Pdf2HtmlUtilTest.class);
    }

    /**
     * 異常係
     * exeファイルを入力していません 
     */
	public void testCase1() {
		exeFilePath = "";
		pdfFile = "D:\\test\\test.pdf";
		destDir = "D:\\test\\output\\pdf2html\\test1";
		htmlFileName = "demo1.html";
		boolean flg = Pdf2HtmlUtil.convertToHtmlEX(exeFilePath, pdfFile, destDir, htmlFileName);
		assertFalse(flg);
	}
	
    /**
     * 異常係
     * Pdfファイが未入力の場合
     */
	public void testCase2() {
		exeFilePath = "D:\\test\\pdf2htmlEX\\pdf2htmlEX.exe";
		pdfFile = "";
		destDir = "D:\\test\\output\\pdf2html\\test1";
		htmlFileName = "demo1.html";
		boolean flg = Pdf2HtmlUtil.convertToHtmlEX(exeFilePath, pdfFile, destDir, htmlFileName);
		assertFalse(flg);
	}
	
    /**
     * 異常係
     * 変換後ファイル出力パスが未入力の場合
     */
	public void testCase3() {
		exeFilePath = "D:\\test\\pdf2htmlEX\\pdf2htmlEX.exe";
		pdfFile = "D:\\test\\test.pdf";
		destDir = "";
		htmlFileName = "demo1.html";
		boolean flg = Pdf2HtmlUtil.convertToHtmlEX(exeFilePath, pdfFile, destDir, htmlFileName);
		assertFalse(flg);
	}
	
    /**
     * 異常係
     * HTMLファイル名が未入力の場合
     */
	public void testCase4() {
		exeFilePath = "D:\\test\\pdf2htmlEX\\pdf2htmlEX.exe";
		pdfFile = "D:\\test\\test.pdf";
		destDir = "D:\\test\\output\\pdf2html\\test1";
		htmlFileName = "";
		boolean flg = Pdf2HtmlUtil.convertToHtmlEX(exeFilePath, pdfFile, destDir, htmlFileName);
		assertFalse(flg);
	}
	
    /**
     * 異常係
     * ファイル変換異常
     * 指定された変換ツールが見つかりません。
     */
	public void testCase5() {
		exeFilePath = "D:\\test\\pdf2htmlEX\\xxxx.exe";
		pdfFile = "D:\\test\\test.pdf";
		destDir = "D:\\test\\output\\pdf2html\\test1";
		htmlFileName = "demo1.html";
		boolean flg = Pdf2HtmlUtil.convertToHtmlEX(exeFilePath, pdfFile, destDir, htmlFileName);
		assertFalse(flg);
	}
	
    /**
     * 正常係
     */
	public void testCase6() {
		exeFilePath = "D:\\test\\pdf2htmlEX\\pdf2htmlEX.exe";
		pdfFile = "D:\\test\\test.pdf";
		destDir = "D:\\test\\output\\pdf2html\\test1";
		htmlFileName = "demo1";
		boolean flg = Pdf2HtmlUtil.convertToHtmlEX(exeFilePath, pdfFile, destDir, htmlFileName);
		assertTrue(flg);
	}

}
