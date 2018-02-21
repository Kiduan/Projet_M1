package isen_brest.projet_m1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Méthode utilisé par le bouton 1
    public void button1Click (View view){
        Intent intent = new Intent(this,TestJson.class);
        startActivity(intent);
    }

    // Méthode utilisé par le bouton 2
    public void button2Click (View view){
        Toast.makeText(MainActivity.this,
                "Placeholder2 is clicked!", Toast.LENGTH_SHORT).show();
    }

    // Méthode utilisé par le bouton 3
    public void button3Click (View view){
        Toast.makeText(MainActivity.this,
                "Placeholder3 is clicked!", Toast.LENGTH_SHORT).show();
    }

    // Méthode utilisé par le bouton 4
    public void button4Click (View view){
        Toast.makeText(MainActivity.this,
                "Placeholder4 is clicked!", Toast.LENGTH_SHORT).show();
    }
}
