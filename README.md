# AndroidOrJs

# js 调用android
```
<input type="button" onclick="jsi.showToast('js调用Android,我是徐庆臣')" value="js调用java代码"/>

    webSettings.setJavaScriptEnabled(true);
        //映射.可以调用js里面的方法
        webView.addJavascriptInterface(new JSInterface(), "jsi");
           //加载百度页面的点击事件
        mBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl("http://www.baidu.com");
                setContentView(webView);
                //  Toast.makeText(MainActivity.this,"ee",Toast.LENGTH_SHORT).show();
            }
        });

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

```

# androd 调用js方法
```
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
     <script type="text/javascript">

               //这个方式是被java调用的
              function androidCallJs(){
              alert("java调用js弹窗");
              }
      </script>
```
