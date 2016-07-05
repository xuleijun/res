import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Time {
public static void main(String[] args) throws IOException {  
        testConvertZwrq();  
    }  
  
    private static void testConvertZwrq() {  
        String[] list = new String[] { "�񁛁���N�����O�\��", "����N�l���O�\��", "�񁛁���N�\�񌎎O���", "�����N�\�񌎓���" };  
        for (String s : list) {  
            Date d = convertJpDate(s);  
            System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(d));  
        }  
    }  
  
    public static Date convertJpDate(String cprq) {  
        int yearPos = cprq.indexOf("�N");  
        int monthPos = cprq.indexOf("��");  
        String jpYear = cprq.substring(0, yearPos);  
        String jpMonth = cprq.substring(yearPos + 1, monthPos);  
        String jpDay = cprq.substring(monthPos + 1, cprq.length() - 1);  
        String year = ConvertJpYear(jpYear);  
        String month = ConvertJpDateNumber(jpMonth);  
        String day = ConvertJpDateNumber(jpDay);  
        Calendar c = Calendar.getInstance();  
        c.set(Calendar.YEAR, Integer.parseInt(year));  
        c.set(Calendar.MONTH, Integer.parseInt(month)-1);  
        c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));  
        return c.getTime();  
    }  
  
    private static String ConvertJpYear(String jpYear) {  
        if(jpYear.length() == 2)  
           return "20" + ConvertJpNumberChar(jpYear);  
        else  
            return ConvertJpNumberChar(jpYear);  
    }  
  
    private static String ConvertJpDateNumber(String jpNumber) {  
        if (jpNumber.length() == 1) {  
            if(jpNumber.equals("�\"))  
           return "10";  
            else  
           return ConvertJpNumberChar(jpNumber);  
        } else if (jpNumber.length() == 2) {  
            if (jpNumber.startsWith("�\")) {  
                return "1" + ConvertJpNumberChar(jpNumber.substring(1, 2));  
            } else if (jpNumber.endsWith("�\")) {  
                return ConvertJpNumberChar(jpNumber.substring(0, 1)) + "0";  
            } else {  
                return ConvertJpNumberChar(jpNumber);  
            }  
        } else if (jpNumber.length() == 3) {  
            return ConvertJpNumberChar(jpNumber.substring(0, 1) + jpNumber.substring(2, 3));  
        }  
        return null;  
    }  
  
    private static String ConvertJpNumberChar(String jpNumberStr) {  
        String ALL_CN_NUMBER = "���댳���O�l�ܘZ������";  
        String ALL_NUMBER = "001123456789";  
        StringBuffer buf = new StringBuffer();  
        for (int i = 0; i < jpNumberStr.length(); i++) {  
            char c = jpNumberStr.charAt(i);  
            int index = ALL_CN_NUMBER.indexOf(c);  
            if (index != -1) {  
                buf.append(ALL_NUMBER.charAt(index));  
            } else {  
                buf.append(jpNumberStr.charAt(i));  
            }  
        }  
        return buf.toString();  
    }  

}