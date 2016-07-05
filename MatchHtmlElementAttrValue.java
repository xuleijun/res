import java.util.ArrayList;  
import java.util.List;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
  
/** 
 * @use ·æwèHTMLh~{Iwè®«I<P 
 * @ProjectName stuff 
 * @Author <a href="mailto:mhmyqn@qq.com">mikan</a></br> 
 * @Date 2012-11-19 ºß08:27:24 </br> 
 * @FullName com.mmq.regex.MatchHtmlElementAttrValue.java </br> 
 * @JDK 1.6.0 </br> 
 * @Version 1.0 </br> 
 */  
public class MatchHtmlElementAttrValue {  
      
    /** 
     * ·æwèHTMLh~{Iwè®«I<P 
     * @param source vCzI¹¶{ 
     * @param element h~{¼Ì 
     * @param attr h~{I®«¼Ì 
     * @return ®«<Pñ\ 
     */  
    public static List<String> match(String source, String element, String attr) {  
        List<String> result = new ArrayList<String>();  
        String reg = "<" + element + "[^<>]*?\\s" + attr + "=['\"]?(.*?)['\"]?\\s.*?>";  
        Matcher m = Pattern.compile(reg).matcher(source);  
        while (m.find()) {  
            String r = m.group(1);  
            result.add(r);  
        }  
        return result;  
    }  
      
    public static void main(String[] args) {  
        String source = "<a title=Ìç¥b href=''>aaa</a><a title='kú¥b' href=''>bbb</a>";  
        List<String> list = match(source, "a", "title");  
        System.out.println(list);  
    }  
}  