package baranek.vojtech.ftpclient;

import android.app.Activity;
import android.os.Bundle;

import com.artifex.mupdfdemo.MuPDFCore;

/**
 * Created by Frank on 16/10/25.
 */
public class TestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_mb_activity);
        try {
            MuPDFCore core = new MuPDFCore(this, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
