import java.util.*;
import java.text.*;

class ConvDate {
	private String convJtGDate(String jpDate) throws ParseException {
		Integer gc = 0;
		String nenGou = jpDate.substring(0, 2);
		String kanjiYear = jpDate.substring(2, jpDate.indexOf("�N"));
		String kanjiMouth = jpDate.substring(jpDate.indexOf("�N") + 1, jpDate.indexOf("��"));
		String kanjiDay = jpDate.substring(jpDate.indexOf("��") + 1, jpDate.indexOf("��"));
		String sujiYear = ConvertJpDateNumber(kanjiYear);
		String sujiMouth = ConvertJpDateNumber(kanjiMouth);
		String sujiDay = ConvertJpDateNumber(kanjiDay);

		Integer yy = Integer.parseInt(sujiYear);
		Integer mm = Integer.parseInt(sujiMouth);
		Integer dd = Integer.parseInt(sujiDay);

		if (nenGou.equals("����")) {
			gc = 4;
		} else if (nenGou.equals("���a")) {
			gc = 3;
		} else if (nenGou.equals("�吳")) {
			gc = 2;
		} else if (nenGou.equals("����")) {
			gc = 1;
		}
		Locale.setDefault(new Locale("ja", "JP", "JP"));
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.ERA, gc);
		cal.set(yy, mm - 1, dd);
		Locale.setDefault(new Locale("en", "EN", "EN"));
		SimpleDateFormat gdf = new SimpleDateFormat("yyyy�NMM��dd��");

		return gdf.format(cal.getTime());
	}

	private String convGtJDate(String src) throws ParseException {
		Locale.setDefault(new Locale("en", "EN", "EN"));
		SimpleDateFormat gdf = new SimpleDateFormat("yyyyMMdd");
		Locale.setDefault(new Locale("ja", "JP", "JP"));
		SimpleDateFormat jdf = new SimpleDateFormat("GGGGyy�NMM��dd��");
		return jdf.format(gdf.parse(src.replace("/", "")));
	}

	private static String ConvertJpDateNumber(String jpNumber) {
		if (jpNumber.length() == 1) {
			if (jpNumber.equals("�\"))
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

	public static void main(String[] args) {
		try {
			ConvDate cd = new ConvDate();
			System.out.println(cd.convJtGDate("������\�ܔN�l������"));
			System.out.println(cd.convJtGDate("���a�܏\�Z�N�\�ꌎ���"));
			System.out.println(cd.convJtGDate("���a43�N03��21��"));
			System.out.println(cd.convJtGDate("�吳3�N9��2��"));
			System.out.println(cd.convJtGDate("����15�N8��13��"));

			//System.out.println(cd.convGtJDate("2013/04/01"));
			//System.out.println(cd.convGtJDate("1981/11/02"));
			//System.out.println(cd.convGtJDate("1968/03/21"));
			//System.out.println(cd.convGtJDate("1914/09/02"));
			//System.out.println(cd.convGtJDate("1882/08/13"));
		} catch (Exception e) {
			System.out.println(e.toString());
			if (e.getCause() != null)
				for (StackTraceElement ste : e.getCause().getStackTrace())
					System.out.println(ste.toString());
			else
				for (StackTraceElement ste : e.getStackTrace())
					System.out.println(ste.toString());
		}
	}
}
