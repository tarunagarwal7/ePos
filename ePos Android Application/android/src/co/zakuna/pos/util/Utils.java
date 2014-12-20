package co.zakuna.pos.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

public class Utils {
	private final static String CURR_NAME = "Rp. ";
	private final static String CURR_FORMAT = "#,##0.-";

	public final static String currencyToString(Double value) {
		DecimalFormat df = new DecimalFormat(
			CURR_FORMAT, new DecimalFormatSymbols(Locale.GERMAN));
		return CURR_NAME + df.format(value);
	}

	public final static Double stringToCurrency(String result) {
		try {
			DecimalFormat df = new DecimalFormat(
				CURR_FORMAT, new DecimalFormatSymbols(Locale.GERMAN));
			return df.parse(result.replace(CURR_NAME, "")).doubleValue();
		} catch(ParseException e) {
			return null;
		}
	}

	public final static String getDevice(Context c) {
		try {
			PackageInfo pInfo = c.getPackageManager().getPackageInfo(c.getPackageName(), 0);
			String version = String.valueOf(pInfo.versionName);
			return version;
		}catch (NameNotFoundException e) { return null; }
	}

}
