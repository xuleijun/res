/*
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class StringCruncher
{
	public static void main ( String[] args )
	{
		String str = "注意我?里?2011年09月13日的是找出，（昭和二十三年四月十三日法律第二十五号）比如?str1=\"?好2014-09-13哈哈\"??一个字符串，里面有日期，但是用str1.matches(regex)的?得出来的?果是?的，???怎?做？";
		String reg  ="\\s+\\年\\s+\\月\\s+\\日";
		Pattern pattern = Pattern.compile (reg);
		Matcher matcher = pattern.matcher (str);
		while (matcher.find ())
		{
			System.out.println (matcher.group ());
		}
	}
}
*/


import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;
class StringCruncher {
    private static String ONE = "昭和";
    //private static String PATTERN = "(com/7655/zh-cn/preview/)(\\w{" + ONE.length() + "})(/preview)";
	//private static String PATTERN = "("+ONE+")([\u4e00-\u9fa5]+)(年)([\u4e00-\u9fa5]+)(月)([\u4e00-\u9fa5]{1,3}$+)(日)";
    	private static String PATTERN = "(憲法|政令|法律|(?<=</date>)[^府令]+府令)第[二三四五六七八九]{0,1}[百]{0,1}[〇一二三四五六七八九]{0,1}[十]{0,1}[〇一二三四五六七八九]{0,1}号";
    public static void main(String [] args){
        ArrayList list = new ArrayList();
        Pattern p = Pattern.compile(StringCruncher.PATTERN);
        String input =  "法律第二十五号政令第二十五号政令第八十四号</date>総理府令第八十四号</date>総府令第八十四号";
    	
        Matcher m = p.matcher(input);
    	
        while(m.find()) {
        	list.add(m.group());
        }
    	System.out.println(list);
        System.out.println(list.get(3));
    	//System.out.println(input);
    	//System.out.println(PATTERN.length());
    }
}