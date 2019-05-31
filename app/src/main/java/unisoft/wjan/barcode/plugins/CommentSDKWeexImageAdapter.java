package unisoft.wjan.barcode.plugins;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;

//import com.bumptech.glide.request.animation.GlideAnimation;

/**
 * Created by mac on 2018/11/1.
 */

public class CommentSDKWeexImageAdapter implements IWXImgLoaderAdapter {
    public CommentSDKWeexImageAdapter() {}

    @Override
    public void setImage(final String url, final ImageView view,
                         WXImageQuality quality, final WXImageStrategy strategy) {

        WXSDKManager.getInstance().postOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (view == null || view.getLayoutParams() == null) {
                    return;
                }
                if (TextUtils.isEmpty(url)) {
                    view.setImageBitmap(null);
                    return;
                }
                if (url.startsWith("file://")) {
                    //本地同一文件名重复拍照,还是加载的老图片，关闭内存和disk缓存
                    Glide.with(WXEnvironment.getApplication()).load(url).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(view);
                    return;
                }

                String temp = url;
                if (url.startsWith("//")) {
                    temp = "http:" + url;
                }
                Glide.with(WXEnvironment.getApplication()).load(temp).into(view);
            }
        }, 0);
    }


}

