# AndroidOrJs

# js 调用android
```
<input type="button" onclick="jsi.showToast('js调用Android,我是徐庆臣')" value="js调用java代码"/>

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
 webView.loadUrl("javascript:androidCallJs()");

     <script type="text/javascript">

               //这个方式是被java调用的
              function androidCallJs(){
              alert("java调用js弹窗");
              }
      </script>
```
