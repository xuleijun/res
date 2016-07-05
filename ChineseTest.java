import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ChineseTest {
	private static String ToDBCNum(String SBCNumberStr) {
		String ALL_SBCNUMBER = "‚O‚P‚Q‚R‚S‚T‚U‚V‚W‚X";
		String ALL_DBCNUMBER = "0123456789";
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < SBCNumberStr.length(); i++) {
			char c = SBCNumberStr.charAt(i);
			int index = ALL_SBCNUMBER.indexOf(c);
			if (index != -1) {
				buf.append(ALL_DBCNUMBER.charAt(index));
			} else {
				buf.append(SBCNumberStr.charAt(i));
			}
		}
		return buf.toString();
	}

    public static void main(String[] args) {
        
        System.out.println(ToDBCNum("‚P‚Q‚R"));
        
    }
}
