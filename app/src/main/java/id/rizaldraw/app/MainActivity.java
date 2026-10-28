package id.rizaldraw.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;
import android.webkit.WebResourceRequest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

public class MainActivity extends Activity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.rgb(15,15,15));
        getWindow().setNavigationBarColor(Color.rgb(15,15,15));

        webView = new WebView(this);
        webView.setBackgroundColor(Color.rgb(15,15,15));
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return handleRizalDrawUrl(url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return handleRizalDrawUrl(request.getUrl().toString());
            }
 
            private boolean handleRizalDrawUrl(String url) {
                Uri uri = Uri.parse(url);
                if ("rizaldraw".equals(uri.getScheme())) {
                    String target = uri.getHost();
                    if ("home".equals(target)) showHome();
                    else if ("prediksi".equals(target)) showPrediksi();
                    else if ("web".equals(target)) showWeb();
                    else if ("market".equals(target)) showMarket(uri.getPath());
                    else if ("browser".equals(target)) openBrowser(uri.getPath());
                    return true;
                }
                return false;
            }
        });
        setContentView(webView);
        showHome();
    }

    private String shell(String title, String body) {
        return "<!doctype html><html><head><meta name='viewport' content='width=device-width,initial-scale=1'>" +
            "<style>body{margin:0;background:#101010;color:#eee;font-family:Arial;padding:18px;padding-bottom:80px}" +
            "h1{color:#d8b45a;font-size:25px}h2{color:#d8b45a;font-size:18px}" +
            ".card{background:#1b1b1b;border:1px solid #80652c;border-radius:13px;padding:15px;margin:10px 0}" +
            "a.btn{display:block;text-decoration:none;background:#d8b45a;color:#111;text-align:center;padding:13px;border-radius:9px;font-weight:bold;margin-top:10px}" +
            ".muted{color:#aaa;font-size:13px}.nav{position:fixed;bottom:0;left:0;right:0;background:#181818;border-top:1px solid #80652c;display:flex;justify-content:space-around;padding:12px 0}" +
            ".nav a{color:#d8b45a;text-decoration:none;font-weight:bold;font-size:12px}</style></head><body>" +
            "<h1>👑 RIZALDRAW</h1>" + body +
            "<div class='nav'><a href='rizaldraw://home'>PASARAN</a><a href='rizaldraw://prediksi'>PREDIKSI</a><a href='rizaldraw://web'>WEB</a></div></body></html>";
    }

    private void load(String html) {
        webView.loadDataWithBaseURL("https://rizaldraw.local/", html, "text/html", "UTF-8", null);
    }

    private void showHome() {
        String body = "<p class='muted'>PILIH KATEGORI PASARAN</p>";
        body += card("PASARAN 3D", "Lato Lato • Europe Lotto • Amazon Lotto", "3d");
        body += card("PASARAN 4D", "9 pilihan pasaran", "4d");
        body += card("PASARAN 5D", "Hokidraw dan pasaran lainnya", "5d");
        load(shell("RizalDraw", body));
    }

    private String card(String title, String desc, String path) {
        return "<div class='card'><h2>" + title + "</h2><p>" + desc + "</p><a class='btn' href='rizaldraw://market/" + path + "'>LIHAT PASARAN</a></div>";
    }

    private void showMarket(String path) {
        String type = path == null ? "" : path.replace("/", "");
        String[] names;
        if ("3d".equals(type)) {
            names = new String[]{"Lato Lato", "Europe Lotto", "Amazon Lotto"};
        } else if ("4d".equals(type)) {
            names = new String[]{"Totomacau", "Sydney Lotto", "Hongkong Lotto",
                "Kingkong Pools", "Singapore Pools", "Taiwan Lottery",
                "Cambodia Lotto", "Jowopools", "Jakarta Pools"};
        } else {
            names = new String[]{"Hokidraw"};
        }

        StringBuilder body = new StringBuilder(
            "<p class='muted'>DAFTAR PASARAN</p>");

        for (String name : names) {
            String url;
            if ("Lato Lato".equals(name)) {
                url = "https://latolatolotto.com/";
            } else if ("Europe Lotto".equals(name)) {
                url = "https://europelotto.click";
            } else if ("Amazon Lotto".equals(name)) {
                url = "https://amazonlotto.site/";
            } else if ("Totomacau".equals(name)) {
                url = "https://www.totomacaupools.com/checker.php";
            } else if ("Sydney Lotto".equals(name)) {
                url = "https://sydneylotto.com/";
            } else if ("Hongkong Lotto".equals(name)) {
                url = "https://hongkonglotto.com/";
            } else if ("Kingkong Pools".equals(name)) {
                url = "https://kingkongpools.com/";
            } else if ("Singapore Pools".equals(name)) {
                url = "https://www.singaporepools.com.sg/";
            } else if ("Taiwan Lottery".equals(name)) {
                url = "https://taiwanlottery.com.tw/";
            } else if ("Cambodia Lotto".equals(name)) {
                url = "https://kambojalotto.com/";
            } else if ("Jowopools".equals(name)) {
                url = "http://www.jowopools.net/";
            } else if ("Jakarta Pools".equals(name)) {
                url = "https://jakartapools.id";
            } else {
                url = "https://hokidraw.com/history.html";
            }

            body.append("<div class='card'><h2>").append(name)
                .append("</h2><a class='btn' href='").append(url)
                .append("'>BUKA PASARAN</a></div>");
        }

        load(shell("Pasaran", body.toString()));
    }

    private void showPrediksi() {
        String body = "<p class='muted'>PILIH SITUS PREDIKSI</p>" +
            "<div class='card'><h2>Prediksi WOSTOTO</h2><a class='btn' href='https://wos.prediksiwostotobaru.com/?page=prediksi-mobile'>BUKA WOSTOTO</a></div>" +
            "<div class='card'><h2>Prediksi TOGELTO</h2><a class='btn' href='https://togel.to/prediksi'>BUKA TOGELTO</a></div>";
        load(shell("Prediksi", body));
    }

    private void showWeb() {
        String body = "<p class='muted'>PILIH BROWSER DI HP LU</p>" +
            "<div class='card'><h2>Chrome</h2><a class='btn' href='rizaldraw://browser/chrome'>BUKA CHROME</a></div>" +
            "<div class='card'><h2>DuckDuckGo</h2><a class='btn' href='rizaldraw://browser/duckduckgo'>BUKA DUCKDUCKGO</a></div>" +
            "<div class='card'><h2>Via Browser</h2><a class='btn' href='rizaldraw://browser/via'>BUKA VIA</a></div>";
        load(shell("WEB", body));
    }

    private void openBrowser(String path) {
        String type = path == null ? "" : path.replace("/", "");
        String pkg = "chrome".equals(type) ? "com.android.chrome" :
                     "duckduckgo".equals(type) ? "com.duckduckgo.mobile.android" : "mark.via.gp";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
        intent.setPackage(pkg);
        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Browser belum terpasang. Pasang browser tersebut atau gunakan browser lain.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) webView.goBack();
        else showHome();
    }
}
