package company.com.allder1.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import company.com.allder1.R;
import company.com.allder1.utils.Const;

public class UserPolicyActivity extends AppCompatActivity {
    WebView webViewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_policy);
        webViewUser=findViewById(R.id.wedviewUserPolicy);

        webViewUser.getSettings().setJavaScriptEnabled(true);
        webViewUser.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webViewUser.getSettings().setSupportMultipleWindows(true);
        webViewUser.setWebViewClient(new WebViewClient());
        webViewUser.setWebChromeClient(new WebChromeClient());
        String urlUserPolicy = "https://policies.google.com/privacy?hl=en";
        webViewUser.loadUrl(urlUserPolicy);
    }
}
