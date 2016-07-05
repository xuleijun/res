/*
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class StringCruncher
{
	public static void main ( String[] args )
	{
		String str = "Σδ?’?2011N0913ϊI₯QoCiΊaρ\ONl\Oϊ@₯ζρ\άjδ@?str1=\"?D2014-09-13ϋϋ\"??κ’ψC’ΚLϊϊCA₯pstr1.matches(regex)I?ΎoI?Κ₯?IC????τH";
		String reg  ="\\s+\\N\\s+\\\\s+\\ϊ";
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
    private static String ONE = "Ίa";
    //private static String PATTERN = "(com/7655/zh-cn/preview/)(\\w{" + ONE.length() + "})(/preview)";
	//private static String PATTERN = "("+ONE+")([\u4e00-\u9fa5]+)(N)([\u4e00-\u9fa5]+)()([\u4e00-\u9fa5]{1,3}$+)(ϊ)";
    	private static String PATTERN = "(@|­ί|@₯|(?<=</date>)[^{ί]+{ί)ζ[ρOlάZ΅ͺγ]{0,1}[S]{0,1}[ZκρOlάZ΅ͺγ]{0,1}[\]{0,1}[ZκρOlάZ΅ͺγ]{0,1}";
    public static void main(String [] args){
        ArrayList list = new ArrayList();
        Pattern p = Pattern.compile(StringCruncher.PATTERN);
        String input =  "@₯ζρ\ά­ίζρ\ά­ίζͺ\l</date>{ίζͺ\l</date>{ίζͺ\l";
    	
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