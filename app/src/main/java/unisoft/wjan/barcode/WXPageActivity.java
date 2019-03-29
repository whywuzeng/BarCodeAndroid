package unisoft.wjan.barcode;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXRenderErrorCode;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.ui.component.NestedContainer;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXSoInstallMgrSdk;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import unisoft.wjan.barcode.hotreload.HotReloadManager;
import unisoft.wjan.barcode.plugins.heatrenewal.DownLoaderTask;
import unisoft.wjan.barcode.plugins.heatrenewal.ZipExtractorTask;
import unisoft.wjan.barcode.util.AppConfig;
import unisoft.wjan.barcode.util.Constants;
import unisoft.wjan.barcode.util.JurisdictionUtils;
import unisoft.wjan.barcode.util.QuitterActivity;


public class WXPageActivity extends AbsWeexActivity implements
    WXSDKInstance.NestedInstanceInterceptor {

  /**
   * 是否调试
   */
  private boolean isDebug = false;
  private static final String TAG = "WXPageActivity";
  private ProgressBar mProgressBar;
  private TextView mTipView;
  private boolean mFromSplash = false;
  private HotReloadManager mHotReloadManager;

  private JSCallback onQRScannCallback = null;
  private String PATH = "";
  private ProgressDialog mDialog;
  private boolean isFill;
  String strurl;
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

  public void setOnScanFinishCallback(JSCallback callback) {
    this.onQRScannCallback = callback;
  }

  @Override
  public void onCreateNestInstance(WXSDKInstance instance, NestedContainer container) {
    Log.d(TAG, "Nested Instance created.");
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_wxpage);
    String isTestUrl = AppConfig.sPreferences.getString("isTestUrl", "");
      Log.e(TAG, "onCreate: "+isTestUrl );
    if (android.os.Build.VERSION.SDK_INT > 9) {
      StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
      StrictMode.setThreadPolicy(policy);

    }

    mDialog = new ProgressDialog(this);

    mContainer = (ViewGroup) findViewById(R.id.container);
    mProgressBar = (ProgressBar) findViewById(R.id.progress);
    mTipView = (TextView) findViewById(R.id.index_tip);

    Intent intent = getIntent();
    Uri uri = intent.getData();
    String from = intent.getStringExtra("from");

    //本地保存的路径
    PATH = Environment.getExternalStorageDirectory() + "/" + getApplication().getPackageName() + "/";
    mFromSplash = "splash".equals(from);

    if (uri == null) {
      uri = Uri.parse("{}");
    }
    if (uri != null) {
      try {
        if (!TextUtils.isEmpty(getIntent().getStringExtra("instanceId"))) {
          uri = Uri.parse("{WeexBundle:'" + uri.toString() + "'}");
        }
        JSONObject initData = new JSONObject(uri.toString());
        String bundleUrl = initData.optString("WeexBundle", null);
        if (bundleUrl != null) {
          mUri = Uri.parse(bundleUrl);
        }
//        {"WeexBundle":"http:\/\/192.168.1.185:8081\/dist\/calendars\/calendars.js?_wx_tpl=http:\/\/192.168.1.185:8081\/dist\/calendars\/calendars.js"}
        String ws = initData.optString("Ws", null);
//         ws = "http://192.168.1.185:8081/dist/calendars/calendars.js";
        if (!TextUtils.isEmpty(ws)) {
          mHotReloadManager = new HotReloadManager(ws, new HotReloadManager.ActionListener() {
            @Override
            public void reload() {
              runOnUiThread(new Runnable() {
                @Override
                public void run() {
                  Toast.makeText(WXPageActivity.this, "Hot reload", Toast.LENGTH_SHORT).show();
                  createWeexInstance();
                  renderPage();
                }
              });
            }

            @Override
            public void render(final String bundleUrl) {
              runOnUiThread(new Runnable() {
                @Override
                public void run() {
                  Toast.makeText(WXPageActivity.this, "Render: " + bundleUrl, Toast.LENGTH_SHORT).show();
                  createWeexInstance();
                  loadUrl(bundleUrl);
                }
              });
            }
          });
        } else {
          WXLogUtils.w("Weex", "can not get hot reload config");
        }
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
    //这里是否意味第一次开始的
    if (mUri == null) {
      String localPath = "file:///" + PATH;
      mUri = Uri.parse(AppConfig.getLaunchUrl(localPath + "bundlejs/"));
    }

    if (!WXSoInstallMgrSdk.isCPUSupport()) {
      mProgressBar.setVisibility(View.INVISIBLE);
      mTipView.setText(R.string.cpu_not_support_tip);
      return;
    }

    if (isTestUrl.equals("N"))
      strurl = getUrl(mUri);
    else
      strurl = getUrl(mUri).replaceAll("http:", "");
    if (getSupportActionBar() != null) {
      getSupportActionBar().setTitle(strurl);
      if (!isDebug) {
        getSupportActionBar().hide();
      }
    }

    JurisdictionUtils.requestPermissions(this, 1, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA}, new JurisdictionUtils.OnPermissionListener() {
      @Override
      public void onPermissionGranted() {
        //权限获取成功，进行你需要的操作
        doDownLoadWork();

      }

      @Override
      public void onPermissionDenied() {
        Toast.makeText(WXPageActivity.this, "app 权限未获取可能无法正常使用", Toast.LENGTH_LONG).show();
        //权限获取失败，进行你需要的操作
      }
    });

  }

  private String getUrl(Uri uri) {
    String url = uri.toString();
    String scheme = uri.getScheme();
    if (uri.isHierarchical()) {
      if (TextUtils.equals(scheme, "http") || TextUtils.equals(scheme, "https")) {
        String weexTpl = uri.getQueryParameter(Constants.WEEX_TPL_KEY);
        if (!TextUtils.isEmpty(weexTpl)) {
          url = weexTpl;
        }
      }
    }
    return url;
  }

  protected void preRenderPage() {
    mProgressBar.setVisibility(View.VISIBLE);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    Intent intent = new Intent("requestPermission");
    intent.putExtra("REQUEST_PERMISSION_CODE", requestCode);
    intent.putExtra("permissions", permissions);
    intent.putExtra("grantResults", grantResults);
    LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    JurisdictionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
  }


  @Override
  public void onRenderSuccess(WXSDKInstance instance, int width, int height) {
    mProgressBar.setVisibility(View.GONE);
    mTipView.setVisibility(View.GONE);
  }

  @Override
  public void onException(WXSDKInstance instance, String errCode, String msg) {
    mProgressBar.setVisibility(View.GONE);
    mTipView.setVisibility(View.VISIBLE);
    if (TextUtils.equals(errCode, WXRenderErrorCode.WX_NETWORK_ERROR)) {
      mTipView.setText(R.string.index_tip);
    } else {
      mTipView.setText("render error:" + errCode);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    if (isDebug) {
      getMenuInflater().inflate(mFromSplash ? R.menu.main_scan : R.menu.main, menu);
    }
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_refresh:
        createWeexInstance();
        renderPage();
        break;
      case R.id.action_scan:
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan a barcode");
        //integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(true);
        integrator.setOrientationLocked(false);
        integrator.setBarcodeImageEnabled(true);
        integrator.setPrompt(getString(R.string.capture_qrcode_prompt));
        integrator.initiateScan();
        break;
      case android.R.id.home:
        finish();
      default:
        break;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
    if (result != null) {
      if (result.getContents() == null) {
        Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
      } else {
        handleDecodeInternally(result.getContents());
      }
    }
    super.onActivityResult(requestCode, resultCode, data);
  }

  // Put up our own UI for how to handle the decoded contents.
  private void handleDecodeInternally(String code) {

    if (!TextUtils.isEmpty(code)) {
      Uri uri = Uri.parse(code);
      if (uri.getQueryParameterNames().contains("bundle")) {
        WXEnvironment.sDynamicMode = uri.getBooleanQueryParameter("debug", false);
        WXEnvironment.sDynamicUrl = uri.getQueryParameter("bundle");
        String tip = WXEnvironment.sDynamicMode ? "Has switched to Dynamic Mode" : "Has switched to Normal Mode";
        Toast.makeText(this, tip, Toast.LENGTH_SHORT).show();
        finish();
        return;
      } else if (uri.getQueryParameterNames().contains("_wx_devtool")) {
        WXEnvironment.sRemoteDebugProxyUrl = uri.getQueryParameter("_wx_devtool");
        WXEnvironment.sDebugServerConnectable = true;
        WXSDKEngine.reload();
        Toast.makeText(this, "devtool", Toast.LENGTH_SHORT).show();
        return;
      } else if (code.contains("_wx_debug")) {
        uri = Uri.parse(code);
        String debug_url = uri.getQueryParameter("_wx_debug");
        WXSDKEngine.switchDebugModel(true, debug_url);
        finish();
      } else {
        if (this.onQRScannCallback != null) {
          Map data = new HashMap();
          data.put("result", true);
          data.put("qrcode", code);
          this.onQRScannCallback.invokeAndKeepAlive(data);
        } else {
          JSONObject data = new JSONObject();
          try {
            data.put("WeexBundle", Uri.parse(code).toString());
            Intent intent = new Intent(WXPageActivity.this, WXPageActivity.class);
            intent.setData(Uri.parse(data.toString()));
            startActivity(intent);
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    if (mHotReloadManager != null) {
      mHotReloadManager.destroy();
    }
  }


  /**
   * 下载
   */
  private void doDownLoadWork() {

    isFill = true;

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
    //DownLoaderTask task = new DownLoaderTask("http://192.168.9.155/johnny/test.h264", getCacheDir().getAbsolutePath()+"/", this);
    task.execute();
  }

  private void loadView() {
//    Uri uri = Uri.parse(strurl);
//    if (uri != null && TextUtils.equals(uri.getScheme(), "file")) {
//      loadUrl(uri.toString());
//    }else {
//      File file = new File(strurl);
//      if (file.exists()) {
//        Uri uri = Uri.fromFile(file);
//        String struurl = uri.toString();
//        loadUrl(struurl);
//      }
//    }
    loadUrl(strurl);
    QuitterActivity.addActivity(this);
  }
}
