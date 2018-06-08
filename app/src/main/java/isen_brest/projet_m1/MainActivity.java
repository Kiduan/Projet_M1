package isen_brest.projet_m1;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;

import org.json.JSONObject;

import java.io.File;

import isen_brest.projet_m1.model.Sequentiel;
import isen_brest.projet_m1.utils.FilesUtil;
import isen_brest.projet_m1.utils.JsonUtil;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // ATTENTION - temporairement utilisé pour créer des séquentiels de test
        // ---------------------------------------------------------------------------
        File pathname = FilesUtil.getJsonDir(this);

        Sequentiel sequentiel1 = Sequentiel.createSeqTest(null);
        JSONObject jsonObject1 = JsonUtil.toJson(sequentiel1);
        FilesUtil.createFile(jsonObject1.toString().getBytes(), pathname, sequentiel1.getNomSequentiel()+".json");

        Sequentiel sequentiel2 = Sequentiel.createSeqTest("Test 2");
        JSONObject jsonObject2 = JsonUtil.toJson(sequentiel2);
        FilesUtil.createFile(jsonObject2.toString().getBytes(), pathname, sequentiel2.getNomSequentiel()+".json");

        Sequentiel sequentiel3 = Sequentiel.createSeqTest("Troisième test");
        JSONObject jsonObject3 = JsonUtil.toJson(sequentiel3);
        FilesUtil.createFile(jsonObject3.toString().getBytes(), pathname, sequentiel3.getNomSequentiel()+".json");

        Sequentiel sequentiel4 = Sequentiel.createSeqTest("Fourth");
        JSONObject jsonObject4 = JsonUtil.toJson(sequentiel4);
        FilesUtil.createFile(jsonObject4.toString().getBytes(), pathname, sequentiel4.getNomSequentiel()+".json");
        // ---------------------------------------------------------------------------
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
}
