package isen_brest.projet_m1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity {

    // champ de saisie du mot de passe
    private EditText password_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        //branchement du champ de saisie
        password_field = (EditText) findViewById(R.id.password_input);
    }

    //méthode OnClick qui permet de : valider ou non le mot de passe, et passer à l'activité suivante
    public void password_click(View view) {

        String password = "0000"; //mot de passe temporaire stocké dans "password"
        if ( password_field.getText().toString().equals(password)) {
            Intent modifmenuActivity = new Intent(this, ModifMenuActivity.class);
            startActivity(modifmenuActivity);
        } else {
            Toast.makeText(this,
                    "Mot de passe invalide !", Toast.LENGTH_SHORT).show();
            Intent passwordActivity = new Intent(this, PasswordActivity.class);
            startActivity(passwordActivity);
        }
    }

}
