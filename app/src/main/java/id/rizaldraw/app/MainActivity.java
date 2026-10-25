package id.rizaldraw.app;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.view.KeyEvent;

public class MainActivity extends Activity {
    private WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        webView.setBackgroundColor(0xFF090909);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        setContentView(webView);
        webView.loadDataWithBaseURL(null, HOME, "text/html", "UTF-8", null);
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private static final String HOME = """
<!doctype html>
<html lang="id">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<style>
body{margin:0;background:#090909;color:#f5f1e6;font-family:Arial}
header{padding:25px 18px;border-bottom:1px solid #66521c}
h1{color:#d4af37;letter-spacing:2px}
main{padding:16px}
.card{background:#151515;border:1px solid #66521c;border-radius:12px;padding:16px;margin:12px 0}
button{background:#d4af37;color:#090909;border:0;border-radius:8px;padding:10px;font-weight:bold}
a{color:#d4af37}
small{color:#aaa}
</style>
</head>
<body>
<header><h1>RizalDraw</h1><small>Pasaran • Data Result • Sumber</small></header>
<main id="home">
<div class="card"><b>Pasaran 3D</b><p><button onclick="show('3D')">Buka</button></p></div>
<div class="card"><b>Pasaran 4D</b><p><button onclick="show('4D')">Buka</button></p></div>
<div class="card"><b>Pasaran 5D</b><p><button onclick="show('5D')">Buka</button></p></div>
<small>Hasil dan jadwal mengikuti informasi yang tersedia dari situs sumber.</small>
</main>
<main id="detail" style="display:none">
<button onclick="back()">← Kembali</button><h2 id="title"></h2><div class="card" id="links"></div>
</main>
<script>
const sources={
'3D':[['Lato Lato','https://latolatolotto.com/'],['Europe Lotto','https://europelotto.click'],['Amazon Lotto','https://amazonlotto.site/']],
'4D':[['Singapore Pools — hasil resmi','https://www.singaporepools.com.sg/en/product/Pages/4d_results.aspx'],['Sydney Lotto','https://sydneylotto.com/'],['Hongkong Lotto','https://hongkonglotto.com/']],
'5D':[['Hokidraw — riwayat','https://hokidraw.com/history.html'],['Hokidraw — live draw','https://www.hokidraw.com/live-draw.html']]
};
function show(k){document.getElementById('home').style.display='none';document.getElementById('detail').style.display='block';document.getElementById('title').textContent='Pasaran '+k;document.getElementById('links').innerHTML=sources[k].map(s=>'<p><a href="'+s[1]+'">'+s[0]+' ↗</a></p>').join('')}
function back(){document.getElementById('detail').style.display='none';document.getElementById('home').style.display='block'}
</script>
</body>
</html>
""";
}
