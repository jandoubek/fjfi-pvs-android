package com.itborci.gui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.itborci.R;

public class MainActivity extends Activity {

    private static String TAG = "rooster-android-app";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
        setContentView(R.layout.main);
    }
}

