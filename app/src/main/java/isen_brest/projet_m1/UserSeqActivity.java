package isen_brest.projet_m1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserSeqActivity extends AppCompatActivity {

    //déclaration des variables : texte par défaut, et données importées au clic précédent
    TextView serial_description;
    private TextView default_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_seq);

        //branchement des variables avec leur layout
        default_txt = (TextView) findViewById(R.id.activity_user_seq_default_txt);
        serial_description = (TextView) findViewById(R.id.serialized_description);

        //affichage des données importées au clic sur l'activité précédente
        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                newString = null;
            }
            else {
                newString = extras.getString("description");
            }
        }
        else {
            newString = (String) savedInstanceState.getSerializable("description");
        }
        serial_description.setText(newString);
    }
}
