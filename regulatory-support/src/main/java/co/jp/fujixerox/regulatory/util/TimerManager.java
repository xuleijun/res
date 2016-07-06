package co.jp.fujixerox.regulatory.util;

import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TimerManager implements ServletContextListener {
	private static final Logger logger = LogManager.getLogger(Pdf2HtmlUtil.class.getName());
	// 時間間隔
 	private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
 	private Properties props = null;
 	
 	// タイマー
 	private Timer timer;
 	
    public TimerManager() {
    	try{
			props = new Properties();
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("system.properties");
			props.load(in);
    	} catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * Html変換任務を実行します
     */
 	public void contextInitialized(ServletContextEvent event) {
 		logger.info("定時任務を起動します");
 		String hour = props.getProperty("batchstart_hour");
 		String minute = props.getProperty("batchstart_minute");
 		String second = props.getProperty("batchstart_second");

		Calendar calendar = Calendar.getInstance();
	
		/*** 2:00実行 ***/
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
	
		calendar.set(Calendar.MINUTE, Integer.parseInt(minute));
	
		calendar.set(Calendar.SECOND, Integer.parseInt(second));
	
		// 初めて任務の時間
		Date date=calendar.getTime();
	
		// 実行して時間はシステム時間より小きく
		if (date.before(new Date())) {
		    date = this.addDay(date, 1);
		}
	
		timer = new Timer("convert2Html", true);
	
		Convert2HtmlTimerTask task = new Convert2HtmlTimerTask();
	
		// 実行任務
		timer.schedule(task,date,PERIOD_DAY);
	}
	
	/**  
	 * Web応用が終わる時任務を停止します
	 */
	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();   
		logger.info("定時任務を停止します");
	}   

	/**
	 * 日数を追加します
	 * @param date 初めて任務の時間
	 * @param num  日数
	 * @return 追加後の時間
	 */
	public Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}
}
