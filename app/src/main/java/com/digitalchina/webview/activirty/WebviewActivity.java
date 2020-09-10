package com.digitalchina.webview.activirty;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.digitalchina.webview.R;

public class WebviewActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webView = findViewById(R.id.webview);


        WebSettings settings = webView.getSettings();
        webView.setWebViewClient(new WebViewClient());
        //设置User-Agent字符串
        webView.getSettings().setUserAgentString("android");
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //支持缩放
        settings.setSupportZoom(true);
        //支持手势缩放
        settings.setBuiltInZoomControls(true);
        //是否显示缩放按钮
        settings.setDisplayZoomControls(false);
        //设置文本编码
        settings.setDefaultTextEncodingName("utf-8");
        //将图片调整到适合WebView的大小
        settings.setUseWideViewPort(true);
        //是否使用预览模式加载界面
        settings.setLoadWithOverviewMode(true);

        Intent i = getIntent();
        String url = i.getStringExtra("url");
        if (url!=null){
            Log.e("the url is:",url);
        }else {
            Log.e("url error "," url is null");
        }
        if (url==null){
            return;
        }
        //initWebView();

        webView.loadUrl(url);
    }

    private void initWebView() {
        //启用支持javascript
        WebSettings settings = webView.getSettings();
        //设置不调用第三方浏览器
        webView.setWebViewClient(new WebViewClient());

        //设置User-Agent字符串
        webView.getSettings().setUserAgentString("android");

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //支持缩放
        settings.setSupportZoom(true);
        //支持手势缩放
        settings.setBuiltInZoomControls(true);
        //是否显示缩放按钮
        settings.setDisplayZoomControls(false);
        //设置文本编码
        settings.setDefaultTextEncodingName("utf-8");
        //将图片调整到适合WebView的大小
        settings.setUseWideViewPort(true);

        //是否使用预览模式加载界面
        settings.setLoadWithOverviewMode(true);

        //开启DOM存储API权限
        settings.setDomStorageEnabled(true);
        //保存表单数据
        settings.setSaveFormData(true);

        //支持多屏窗口
        settings.setSupportMultipleWindows(true);

        //缓存API是否开启
        settings.setAppCacheEnabled(false);
        //不适用缓存
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        // 取消WebView中滚动或拖动到顶部、底部时的阴影
        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        // 取消滚动条白边效果
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        // >= 19(SDK4.4)启动硬件加速，否则启动软件加速
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            settings.setLoadsImagesAutomatically(true); //支持自动加载图片
        } else {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            settings.setLoadsImagesAutomatically(false);
        }
    }
}
