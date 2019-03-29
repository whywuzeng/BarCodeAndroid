package unisoft.wjan.barcode;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import unisoft.wjan.barcode.plugins.heatrenewal.DownLoaderTask;
import unisoft.wjan.barcode.plugins.heatrenewal.ZipExtractorTask;
import unisoft.wjan.barcode.util.JurisdictionUtils;


public class SplashActivity extends AppCompatActivity {

    private static String[] PERMISSIONS_STORAGE = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA};
    private static final int PM_CAMERA_AND_WIFI = 1002;
    private String PATH;

    @SuppressLint("HandlerLeak")
    private Handler handler =new Handler();

  public ZipExtractorTask.ZipOverListener mZipOverListener = new ZipExtractorTask.ZipOverListener() {
    @Override
    public void zipOver() {
      runOnUiThread(new Runnable() {
        @Override
        public void run() {
          loadView();
        }
      });
    }
  };
  private boolean isFill;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    PATH = Environment.getExternalStorageDirectory() + "/" + getApplication().getPackageName() + "/";
    if (Build.VERSION.SDK_INT >= 23) {//判断当前系统是不是Android6.0
      methodRequiresTwoPermission();
    } else {
      doDownLoadWork();
    }
  }

  private void methodRequiresTwoPermission() {

    JurisdictionUtils.requestPermissions(this, 1, PERMISSIONS_STORAGE, new JurisdictionUtils.OnPermissionListener() {
      @Override
      public void onPermissionGranted() {
        //权限获取成功，进行你需要的操作
        doDownLoadWork();
//        handler.postDelayed(new Runnable() {
//          @Override
//          public void run() {
//            loadView();
//          }
//        },1500);
      }

      @Override
      public void onPermissionDenied() {
        Toast.makeText(SplashActivity.this, "app 权限未获取可能无法正常使用", Toast.LENGTH_LONG).show();
        //权限获取失败，进行你需要的操作
//        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
//          new AppSettingsDialog.Builder(this).build().show();
//        }
        //最好是可以跳转到setttings 界面
      }
    });

  }

  /**
   * 下载
   */
  private void doDownLoadWork() {
    isFill = false;
    SharedPreferences sp = getSharedPreferences("User", MODE_PRIVATE);
    String value = sp.getString("isVersion", "");
    String url = "";
    if (value != null) {
      if (value.equals("true")) {
        url = "https://exeutest.blob.core.chinacloudapi.cn/app/barcodesversion.json";
      } else{
        url = "https://exeutest.blob.core.chinacloudapi.cn/app/barcodesversion.json";
//        url = "http://tt.ab-inbev.cn/TrackApp/AppUpdate/dtsversion.json";
      }
    } else {
      url = "http://tt.ab-inbev.cn/TrackApp/AppUpdate/dtsversion.json";
    }
    DownLoaderTask task = new DownLoaderTask(url,
            PATH, this, mZipOverListener, isFill);
    task.execute();
  }

  private void loadView() {
    SharedPreferences sp = getSharedPreferences("User", MODE_PRIVATE);
    String isVersion = sp.getString("isVersion", "");
    String value = "";
    String isLoaderValue="";
    String isLoaderValueText="";
//        String value = "6.0.0";
    if (isVersion.equals("true")) {
      isLoaderValueText = sp.getString("isLoaderValueText", "");
    } else
      isLoaderValue = sp.getString("isLoaderValue", "");

    SharedPreferences.Editor edit = sp.edit();
    if (isVersion.equals("true")) {
      edit.putString("isValueTest", isLoaderValueText);
    } else
      edit.putString("isValue", isLoaderValue);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
    //获取当前时间
    Date date = new Date(System.currentTimeMillis());
    edit.putString("jsLastUpdateTime", simpleDateFormat.format(date));
    //提交数据存入到xml文件中
    edit.commit();
    ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
    RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
    Intent intent = new Intent(SplashActivity.this, WXPageActivity.class);
    Uri data = getIntent().getData();
    if (data != null) {
      intent.setData(data);
    }
    intent.putExtra("from", "splash");
    startActivity(intent);
    finish();
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    // Forward results to EasyPermissions
    JurisdictionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
  }
}
