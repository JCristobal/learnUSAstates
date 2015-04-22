package jcristobal.learnusastates;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


public class MainActivity extends ActionBarActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // WebView básico
        String URL = "http://jcristobal.github.io/learnUSAstates/index.html";
        WebView webview;
        webview = (WebView)findViewById(R.id.webView);

        // Para que se vea incrustado en el webview
        webview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }});

        webview.getSettings().setJavaScriptEnabled(true);    // Permitimos que se ejecute JavaScript
        webview.getSettings().setLoadWithOverviewMode(true); // Ajustamos la vista para que no se vea demasiado grande
        webview.getSettings().setUseWideViewPort(true);
        //webview.getSettings().setBuiltInZoomControls(true);  // habilitamos el zoom
        //webview.setInitialScale(100);
        webview.loadUrl(URL);

        // Para la barra de proceso
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        webview.setWebChromeClient(new WebChromeClient()
        {
            @Override
            public void onProgressChanged(WebView view, int progress)
            {
                progressBar.setProgress(0);
                progressBar.setVisibility(View.VISIBLE);
                MainActivity.this.setProgress(progress * 1000);

                progressBar.incrementProgressBy(progress);

                if(progress == 100)
                {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu); //return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit) {
            //return true;
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
