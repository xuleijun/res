import java.io.BufferedReader;  
import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.InputStreamReader;  
  
/**  
 * csvï∂åèâêÕ  
 * @title: CSVAnalysis    
 * @description:     
 * @version 1.0  
 */  
class CSVAnalysis {  
    public static void main(String[] args) {  
        InputStreamReader fr = null;  
        BufferedReader br = null;  
        try {  
            fr = new InputStreamReader(new FileInputStream("D:\\XML\\upload\\code.csv"));  
            br = new BufferedReader(fr);  
            String rec = null;  
            String[] argsArr = null;  
            while ((rec = br.readLine()) != null) {  
                argsArr = rec.split(",");  
                //for (int i = 0; i < argsArr.length; i++) {  
                	if(argsArr[0].equals("êléñâ@")){
                		//return argsArr[1];
                		System.out.print(argsArr[1] +"\t"); 
                	} 
                    //System.out.print("num " + (i + 1) + ":" + argsArr[i] +"\t");  
                //}  
                //System.out.println(argsArr.length);  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (fr != null)  
                    fr.close();  
                if (br != null)  
                    br.close();  
            } catch (IOException ex) {  
                ex.printStackTrace();  
            }  
        }
    	System.out.print("999999"); 
    }  
}  