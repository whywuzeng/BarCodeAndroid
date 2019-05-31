package unisoft.wjan.barcode.util;

import android.content.Context;
import android.util.Log;


/**
 * Created by budao on 2016/10/12.
 */
public class AppConfig {
  private static final String TAG = "AppConfig";
  public static AppPreferences sPreferences = new AppPreferences();

  public static void init(Context context) {
    loadAppSetting(context);
  }

  public static String getLaunchUrl(String url) {
//    SharedPreferences sp = mCotext.getSharedPreferences("User", mCotext.MODE_PRIVATE);
    String isTestUrl = sPreferences.getString("isTestUrl", "");
    Log.e(TAG, "getLaunchUrl: "+isTestUrl );

    if(isTestUrl.equals("N")) {
      return sPreferences.getString("launch_url", "http://192.168.1.185:8081/dist/login.js");
    }
    else if(isTestUrl.equals("L")){
      return "file://assets/login.js";
    }
    else {
      return url + "login.js";
    }
  }

  public static Boolean isLaunchLocally() {
    return sPreferences.getBoolean("launch_locally", false);
  }

  private static void loadAppSetting(Context context) {
    AppConfigXmlParser parser = new AppConfigXmlParser();
    parser.parse(context);
    sPreferences = parser.getPreferences();
  }
}
