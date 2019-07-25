package company.com.allder1.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import company.com.allder1.R;

public class PaymentActivity extends AppCompatActivity {
    WebView webViewPaymentNETS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        webViewPaymentNETS = findViewById(R.id.wedviewPaymentNETS);

        webViewPaymentNETS.getSettings().setJavaScriptEnabled(true);
        webViewPaymentNETS.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webViewPaymentNETS.getSettings().setSupportMultipleWindows(true);
        webViewPaymentNETS.setWebViewClient(new WebViewClient());
        webViewPaymentNETS.setWebChromeClient(new WebChromeClient());
        String urlUserPolicy = "https://www.nets.com.sg/";
        webViewPaymentNETS.loadUrl(urlUserPolicy);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

}
