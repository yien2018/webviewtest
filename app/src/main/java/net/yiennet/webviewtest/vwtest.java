package net.yiennet.webviewtest;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//}
public class vwtest extends AppCompatActivity {
    private WebView webView=null;
    private ProgressBar pb_progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb_progress=findViewById(R.id.pb_loading);
        webView = findViewById(R.id.wv_webview);
        webView.setWebViewClient(new WebViewClient());
        loadWeb();
    }
    public void loadWeb(){
        String url = "http://h5.mse.360.cn/";
        //此方法可以在webview中打开链接而不会跳转到外部浏览器
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pb_progress.setVisibility(View.VISIBLE);
// TODO Auto-generated method stub
                super.onProgressChanged(view, newProgress);
                pb_progress.setProgress(newProgress);
                if(newProgress==100){
                    pb_progress.setVisibility(View.INVISIBLE);
                }

            }
        });

        webView.getSettings().setJavaScriptEnabled(true);//开启JavaScript支持
        webView.loadUrl(url);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //重写onKeyDown，当浏览网页，WebView可以后退时执行后退操作。
        if(keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
//
            new AlertDialog.Builder(this)
                    .setTitle("标题")
                    .setMessage("简单的消息提示框")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            webView.goBack();

                        }
                    })
                    .show();


        }
        return super.onKeyDown(keyCode, event);
    }
}
