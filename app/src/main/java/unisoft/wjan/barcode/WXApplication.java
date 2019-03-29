package unisoft.wjan.barcode;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.StrictMode;

import com.alibaba.weex.plugin.loader.WeexPluginContainer;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

import unisoft.wjan.barcode.extend.ImageAdapter;
import unisoft.wjan.barcode.extend.WXEventModule;
import unisoft.wjan.barcode.plugins.qrcodesann.qrcodesann;
import unisoft.wjan.barcode.util.AppConfig;

public class WXApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    WXSDKEngine.addCustomOptions("appName", "WXSample");
    WXSDKEngine.addCustomOptions("appGroup", "WXApp");

    if (Build.VERSION.SDK_INT>=18) {
      StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
      StrictMode.setVmPolicy(builder.build());
      builder.detectFileUriExposure();
    }

    SharedPreferences sp = getSharedPreferences("User", MODE_PRIVATE);
    SharedPreferences.Editor edit = sp.edit();
    //通过editor对象写入数据NN
//    edit.putString("isTestUrl", "Y");//是否使用热更新JS
//    edit.commit();

    WXSDKEngine.initialize(this,
        new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build()
    );
    try {
      //跳转URL
      WXSDKEngine.registerModule("event", WXEventModule.class);
      //扫描
      WXSDKEngine.registerModule("ytqrdecoder",qrcodesann.class);

    } catch (WXException e) {
      e.printStackTrace();
    }
    AppConfig.init(this);
    WeexPluginContainer.loadAll(this);
  }
}
