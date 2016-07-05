/*
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class StringCruncher
{
	public static void main ( String[] args )
	{
		String str = "���Ӊ�?��?2011�N09��13���I���Q�o�C�i���a��\�O�N�l���\�O���@�����\�܍��j��@?str1=\"?�D2014-09-13����\"??�꘢�������C���ʗL�����C�A���pstr1.matches(regex)�I?���o���I?�ʐ�?�I�C???��?��H";
		String reg  ="\\s+\\�N\\s+\\��\\s+\\��";
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
    private static String ONE = "���a";
    //private static String PATTERN = "(com/7655/zh-cn/preview/)(\\w{" + ONE.length() + "})(/preview)";
	//private static String PATTERN = "("+ONE+")([\u4e00-\u9fa5]+)(�N)([\u4e00-\u9fa5]+)(��)([\u4e00-\u9fa5]{1,3}$+)(��)";
    	private static String PATTERN = "(���@|����|�@��|(?<=</date>)[^�{��]+�{��)��[��O�l�ܘZ������]{0,1}[�S]{0,1}[�Z���O�l�ܘZ������]{0,1}[�\]{0,1}[�Z���O�l�ܘZ������]{0,1}��";
    public static void main(String [] args){
        ArrayList list = new ArrayList();
        Pattern p = Pattern.compile(StringCruncher.PATTERN);
        String input =  "�@�����\�܍����ߑ��\�܍����ߑ攪�\�l��</date>�����{�ߑ攪�\�l��</date>���{�ߑ攪�\�l��";
    	
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