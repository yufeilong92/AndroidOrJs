package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtn1;
    private WebView webView;
    private Button mBtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        //实例化webview组件
        //webView.setWebViewClient(new WebViewClient());
        //加载assets目录下的html文件
        webView.loadUrl("file:///android_asset/js_android.html");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
        //映射.可以调用js里面的方法
        webView.addJavascriptInterface(new JSInterface(), "jsi");

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });

        //java调用js方法 的点击事件, webView.loadUrl("javascript:androidCallJs()");
        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl("javascript:androidCallJs()");
            }
        });


        //加载百度页面的点击事件
        mBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl("http://www.baidu.com");
                setContentView(webView);
                //  Toast.makeText(MainActivity.this,"ee",Toast.LENGTH_SHORT).show();
            }
        });
    }



    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
  /*  public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack(); //调用goBack()返回WebView的上一页面
            return true;
        }
        return false;
    }*/


    private final class JSInterface{
        /**
         * 注意这里的@JavascriptInterface注解， target是4.2以上都需要添加这个注解，否则无法调用
         * @param text
         */
        @JavascriptInterface
        public void showToast(String text){
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        }
        @JavascriptInterface
        public void showJsText(String text){
            webView.loadUrl("javascript:jsText('"+text+"')");
        }
    }

    private void initView() {
        mBtn1 = (Button) findViewById(R.id.btn1);
        webView = (WebView) findViewById(R.id.webview);
        mBtn3 = (Button) findViewById(R.id.btn3);

        mBtn1.setOnClickListener(this);
        mBtn3.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:

                break;
            case R.id.btn3:

                break;
        }
    }
}
