package id.rizaldraw.app;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.content.Intent;
import android.net.Uri;

public class MainActivity extends Activity {
    private WebView webView;

    private static final String HOME =
        "<html><head><meta name='viewport' content='width=device-width,initial-scale=1'>" +
        "<style>" +
        "body{background:#090909;color:#f2c96d;font-family:sans-serif;padding:20px}" +
        "h1{text-align:center;letter-spacing:3px}" +
        "p{color:#ddd;text-align:center}" +
        ".card{border:1px solid #b99546;border-radius:12px;padding:20px;margin:18px 0;background:#151515}" +
        "button{background:#c5a04d;color:#090909;border:0;border-radius:7px;padding:12px;width:100%;font-weight:bold}" +
        "a{color:#f2c96d;display:block;margin-top:14px}" +
        "</style></head><body>" +
        "<h1>RIZALDRAW</h1><p>DATA RESULT • LIVE DRAW</p>" +
        "<div id='home'>" +
        "<div class='card'><h2>3D</h2><p>Pasaran 3 Digit</p><button onclick=\"show('3D')\">BUKA 3D</button></div>" +
        "<div class='card'><h2>4D</h2><p>Pasaran 4 Digit</p><button onclick=\"show('4D')\">BUKA 4D</button></div>" +
        "<div class='card'><h2>5D</h2><p>Pasaran 5 Digit</p><button onclick=\"show('5D')\">BUKA 5D</button></div>" +
        "</div><div id='detail' style='display:none'><button onclick='backHome()'>KEMBALI</button><h2 id='title'></h2><div id='links'></div></div>" +
        "<script>" +
        "function show(k){document.getElementById('home').style.display='none';document.getElementById('detail').style.display='block';document.getElementById('title').innerText=k+' DATA RESULT & LIVE DRAW';" +
        "let d={" +
        "'3D':[['Lihat sumber 3D','https://latolatolotto.com/'],['Sumber 3D alternatif','https://europelotto.click/']]," +
        "'4D':[['Singapore Pools 4D Results','https://www.singaporepools.com.sg/en/product/Pages/4d_results.aspx']]," +
        "'5D':[['Hokidraw History','https://hokidraw.com/history.html'],['Hokidraw Live Draw','https://www.hokidraw.com/live-draw.html']]};" +
        "document.getElementById('links').innerHTML=d[k].map(x=>'<a href=\"'+x[1]+'\">'+x[0]+' ↗</a>').join('');}" +
        "function backHome(){document.getElementById('home').style.display='block';document.getElementById('detail').style.display='none';}" +
        "</script></body></html>";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        webView.setBackgroundColor(0xFF090909);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri uri = request.getUrl();
                if ("https".equals(uri.getScheme()) || "http".equals(uri.getScheme())) {
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                    return true;
                }
                return false;
            }
        });
        setContentView(webView);
        webView.loadDataWithBaseURL(null, HOME, "text/html", "UTF-8", null);
    }

    @Override
    public void onBackPressed() {
        if (webView != null) {
            webView.evaluateJavascript(
                "if(document.getElementById('detail').style.display!='none'){backHome();true}else{false}",
                value -> {
                    if ("false".equals(value)) finish();
                }
            );
        } else {
            super.onBackPressed();
        }
    }
}
