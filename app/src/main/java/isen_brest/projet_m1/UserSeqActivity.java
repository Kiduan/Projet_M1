package isen_brest.projet_m1;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import isen_brest.projet_m1.model.Sequentiel;

import static isen_brest.projet_m1.utils.FilesUtil.listSequentiels;
import static isen_brest.projet_m1.utils.FilesUtil.mediaToBitmap;

// Cette activité permet de faire défiler les étapes du séquentiel choisi à l'activité précédente
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

        //---------------------------------------------------------------------------
        //branchement des variables avec leur layout
        nomEtape = (TextView) findViewById(R.id.nomEtape);
        media = (ImageView) findViewById(R.id.media);
        suivant = (Button) findViewById(R.id.suivant);


        //---------------------------------------------------------------------------
        // Ajout d'un OnClickListener pour l'image
        View.OnClickListener clickListener = new View.OnClickListener() {
            public void onClick(View v) {
                if (v.equals(media)) {
                    if(numEtapeActuelle<etapes.size()-1){numEtapeActuelle++;}

                    displayEtape();
                }
            }
        };
        media.setOnClickListener(clickListener);

        //---------------------------------------------------------------------------
        // Récupération du séquentiel sélectionné à l'activité précédente
        Sequentiel seq;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {seq = null;}
            else {seq = listSequentiels(this).get(extras.getInt("sequentiel"));}
        }
        else {seq = (Sequentiel) savedInstanceState.getSerializable("sequentiel");}


        // On récupère la liste des activités et on initialise 'numEtapeActuelle'
        etapes = seq.getEtapeList();
        numEtapeActuelle = 0;

        displayEtape();
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

    // Méthode permettant d'afficher l'image indiqué dans la variable "media" de l'étape
    private void displayEtape (){
        // On affiche le texte de l'étape
        nomEtape.setText(etapes.get(numEtapeActuelle).getNomEtape());

        // On récupère "media"
        String name = etapes.get(numEtapeActuelle).getMedia();

        // On obtient un bitmap de l'image donnée
        Bitmap bitmap = mediaToBitmap(this, name);

        // On affiche l'image
        media.setImageBitmap(bitmap);

    }
}
