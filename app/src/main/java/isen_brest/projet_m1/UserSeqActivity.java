package isen_brest.projet_m1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

import isen_brest.projet_m1.model.Sequentiel;
import isen_brest.projet_m1.utils.FilesUtil;
import isen_brest.projet_m1.utils.JsonUtil;

public class UserSeqActivity extends AppCompatActivity {

    //Variables
    private TextView nomEtape;
    private ImageView media;
    private Button suivant;

    private List<Sequentiel.Etape> etapes;
    private Integer numEtapeActuelle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_seq);

        //branchement des variables avec leur layout
        nomEtape = (TextView) findViewById(R.id.nomEtape);
        media = (ImageView) findViewById(R.id.media);
        suivant = (Button) findViewById(R.id.suivant);

        //------------------------------ Temporaire -----------------------------
        //-----------------------------------------------------------------------
        // Création de l'objet Sequentiel depuis le fichier
        File pathname = FilesUtil.getJsonDir(this);
        String data = FilesUtil.openJsonFile(pathname, "Exemple de séquentiel"+".json");

        JSONObject jsonTest = null;
        try {
            jsonTest = new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Sequentiel seqTest = JsonUtil.toSequentiel(jsonTest);
        //-----------------------------------------------------------------------


        //-------------------- Conservé pour avoir une trace --------------------
        //-----------------------------------------------------------------------
        //affichage des données importées au clic sur l'activité précédente
        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {newString = null;}
            else {newString = extras.getString("description");}
        }
        else {newString = (String) savedInstanceState.getSerializable("description");}
        //-----------------------------------------------------------------------
        //-----------------------------------------------------------------------


        // On récupère la liste des activités et on initialise 'numEtapeActuelle'
        etapes = seqTest.getEtapeList();
        numEtapeActuelle = 0;
    }

    // Méthode utilisée par le bouton "suivant"
    public void suivantClick (View view){
        if(numEtapeActuelle<etapes.size()-1){numEtapeActuelle++;}

        displayEtape();
    }

    // Méthode utilisée par le bouton "precedent"
    public void precedentClick (View view){
        if(numEtapeActuelle>0){numEtapeActuelle--;}

        displayEtape();
    }

    private void displayEtape (){
        // On affiche le texte de l'étape
        nomEtape.setText(etapes.get(numEtapeActuelle).getNomEtape());

        // On affiche l'image
        String name = etapes.get(numEtapeActuelle).getMedia();
        int id = getResources().getIdentifier(name, "drawable", getPackageName());
        media.setImageDrawable(getResources().getDrawable(id));

        //Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(media);
    }
}
