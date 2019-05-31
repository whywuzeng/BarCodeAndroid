package unisoft.wjan.barcode.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 字符串工具类
 */
public class StrUtil {
	/**
	 * 格式化数字为int
	 *
	 * @param vStr
	 * @return
	 */
	public static int nullToInt(Object vStr) {
		int str = 0;
		String v = StrUtil.nullToStr(vStr);
		if ("".equals(v)) {
			return str;
		}
		try {
			str = Integer.valueOf(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 格式化数字为int
	 *
	 * @param vStr
	 * @return
	 */
	public static int nullToInt(Object vStr, int initValue) {
		int str = initValue;
		String v = StrUtil.nullToStr(vStr);
		if ("".equals(v)) {
			return str;
		}
		try {
			str = Integer.valueOf(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 格式化数字为long
	 *
	 * @param vStr
	 * @return
	 */
	public static long nullToLong(Object vStr, long initValue) {
		long str = initValue;
		String v = StrUtil.nullToStr(vStr);
		if ("".equals(v)) {
			return str;
		}
		try {
			str = Long.valueOf(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 格式化数字为long
	 *
	 * @param vStr
	 * @return
	 */
	public static long nullToLong(Object vStr) {
		return nullToLong(vStr, 0);
	}

	/**
	 * 对象转换成字符串
	 *
	 * @param obj
	 * @return
	 */
	public static String nullToString(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString();
	}

	/**
	 * 格式化数字为double
	 *
	 * @param vStr
	 * @return
	 */
	public static Double nullToDouble(Object vStr) {
		Double str = 0.00;
		String v = StrUtil.nullToStr(vStr);
		if ("".equals(v)) {
			return str;
		}
		try {
			str = Double.valueOf(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 格式化数字为Boolean
	 *
	 * @param vStr
	 * @return
	 */
	public static boolean nullToBoolean(Object vStr) {
		boolean str = false;
		String v = StrUtil.nullToStr(vStr);
		if ("".equals(v)) {
			return str;
		}
		try {
			str = Boolean.valueOf(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 假如obj对象 是null返回""
	 *
	 * @param obj
	 * @return
	 */
	public static String nullToStr(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString();
	}

	public static int StringToInt(String s) {
		int tmp = 0;
		if (s == null)
			return 0;
		try {
			tmp = Integer.parseInt(s);
		} catch (Exception e) {
			tmp = 0;
		}
		return tmp;
	}

	public static float StringToFloat(String s) {
		float tmp = 0;
		if (s == null)
			return 0;
		try {
			tmp = Float.parseFloat(s);
		} catch (Exception e) {
			tmp = 0;
		}
		return tmp;
	}

	/**
	 * 把字符串转换成BigDecimal,并用format进行格式化操作
	 *
	 * @param obj
	 * @param format
	 * @return
	 */
	public static BigDecimal formatBigDecimal(Number bd, String format) {
		DecimalFormat df = new DecimalFormat(format);
		return new BigDecimal(df.format(bd));
	}

	/**
	 * 把字符串转换成Double,并用format进行格式化操作<br/>
	 * formatDouble(0.00,"0.00")转成数字后为0.0请使用formatDoubleStr方法
	 *
	 * @param obj
	 * @param format
	 * @return
	 */
	public static double formatDouble(Object bd, String format) {
		DecimalFormat df = new DecimalFormat(format);
		double obj = 0.00;
		obj = Double.valueOf(df.format(obj));
		if (!"".equals(StrUtil.nullToStr(bd))) {
			obj = Double.valueOf(df.format(Double.valueOf(bd.toString())));
		}
		return obj;
	}

	/**
	 * 把字符串转换成Double,并用format进行格式化操作
	 *
	 * @param obj
	 * @param format
	 * @return
	 */
	public static String formatDoubleStr(Object bd, String format) {
		DecimalFormat df = new DecimalFormat(format);
		double obj = 0.00;
		obj = Double.valueOf(df.format(obj));
		if (!"".equals(StrUtil.nullToStr(bd))) {
			return df.format(Double.valueOf(bd.toString()));
		}
		return obj + "";
	}

	/**
	 * 把字符串转换成BigDecimal
	 *
	 * @param obj
	 * @param format
	 * @return
	 */
	public static BigDecimal nullToBigDecimal(Object obj) {
		if ("".equals(StrUtil.nullToStr(obj))) {
			obj = "0.00";
		}
		BigDecimal bd = new BigDecimal(obj.toString());
		return bd;
	}

	/**
	 * 获取String显示长度 中文占2位 英文1位
	 *
	 * @param s
	 * @return
	 */
	public static int length(String s) {
		if (s == null)
			return 0;
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
	}

	private static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}
}
