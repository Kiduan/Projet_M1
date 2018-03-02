package isen_brest.projet_m1;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    // Méthode utilisée par le bouton "Démarrer"
    public void startClick (View view){
        Intent menuActivity = new Intent(this,UserMenuActivity.class);
        startActivity(menuActivity);

    }

    // Méthode utilisée par le bouton "Paramètres"
    public void paramClick (View view){
        Intent passwordActivity = new Intent(this, PasswordActivity.class);
        startActivity(passwordActivity);
    }


    // Méthode utilisée par le bouton 3
    public void button3Click (View view){
        Toast.makeText(MainActivity.this,
                "Placeholder3 is clicked!", Toast.LENGTH_SHORT).show();
    }

    // Méthode utilisée par le bouton 4
    public void button4Click (View view){
        Toast.makeText(MainActivity.this,
                "Placeholder4 is clicked!", Toast.LENGTH_SHORT).show();
    }
}
