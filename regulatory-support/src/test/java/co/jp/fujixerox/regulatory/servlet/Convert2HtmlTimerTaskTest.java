package co.jp.fujixerox.regulatory.servlet;

import co.jp.fujixerox.regulatory.util.Convert2HtmlTimerTask;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class Convert2HtmlTimerTaskTest extends TestCase {
	
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public Convert2HtmlTimerTaskTest(String testName)
    {
    	
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite(Convert2HtmlTimerTaskTest.class);
    }

    /**
     * 正常係
     */
	public void testCase1() {
		Convert2HtmlTimerTask task = new Convert2HtmlTimerTask();
		task.run();
	}
}
