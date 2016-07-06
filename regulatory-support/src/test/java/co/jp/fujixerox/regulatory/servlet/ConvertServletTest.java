package co.jp.fujixerox.regulatory.servlet;

import java.io.IOException;

import co.jp.fujixerox.regulatory.util.SendPost;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ConvertServletTest extends TestCase {
	
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ConvertServletTest(String testName)
    {
    	
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite(ConvertServletTest.class);
    }
    
    /**
     * 異常係
     * 入力ファイルが不正の場合
     */
    public void testpdf2htmlCase1() {
    	SendPost sendPost = new SendPost();
    	try {
			int code = sendPost.send("http://localhost:8080/regulatory-support/ConvertServlet",
					"D:\\test\\test.txt", "pdf2html");
			assertEquals(code, 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 正常係
     */
    public void testpdf2htmlCase2() {
    	SendPost sendPost = new SendPost();
    	try {
			int code = sendPost.send("http://localhost:8080/regulatory-support/ConvertServlet",
					"D:\\test\\test.pdf", "pdf2html");
			assertEquals(code, 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 異常係
     * 入力ファイルが不正の場合
     */
    public void testword2htmlCase3() {
    	SendPost sendPost = new SendPost();
    	try {
			int code = sendPost.send("http://localhost:8080/regulatory-support/ConvertServlet",
					"D:\\test\\test.txt", "word2html");
			assertEquals(code, 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 正常係
     * docファイル入力
     */
    public void testword2htmlCase4() {
    	SendPost sendPost = new SendPost();
    	try {
			int code = sendPost.send("http://localhost:8080/regulatory-support/ConvertServlet",
					"D:\\test\\test1.doc", "word2html");
			assertEquals(code, 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 正常係
     * docxファイル入力
     */
    public void testword2htmlCase5() {
    	SendPost sendPost = new SendPost();
    	try {
			int code = sendPost.send("http://localhost:8080/regulatory-support/ConvertServlet",
					"D:\\test\\test2.docx", "word2html");
			assertEquals(code, 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 正常係
     * xdwファイル入力
     */
    public void testword2htmlCase6() {
    	SendPost sendPost = new SendPost();
    	try {
			int code = sendPost.send("http://localhost:8080/regulatory-support/ConvertServlet",
					"D:\\test\\test.xdw", "dcuworks2html");
			assertEquals(code, 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 正常係
     * xlsファイル入力
     */
    public void testword2htmlCase7() {
    	SendPost sendPost = new SendPost();
    	try {
			int code = sendPost.send("http://localhost:8080/regulatory-support/ConvertServlet",
					"D:\\test\\test.xls", "excel2html");
			assertEquals(code, 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 正常係
     * xlsxファイル入力
     */
    public void testword2htmlCase8() {
    	SendPost sendPost = new SendPost();
    	try {
			int code = sendPost.send("http://localhost:8080/regulatory-support/ConvertServlet",
					"D:\\test\\test.xlsx", "excel2html");
			assertEquals(code, 200);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
