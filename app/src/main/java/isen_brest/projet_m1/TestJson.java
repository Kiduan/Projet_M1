package isen_brest.projet_m1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.InputStream;

public class TestJson extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_json);

        InputStream inputStream = getResources().openRawResource(R.raw.seq_test);
    }
}
