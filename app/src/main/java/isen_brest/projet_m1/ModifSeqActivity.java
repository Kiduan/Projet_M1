package isen_brest.projet_m1;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.File;
import java.util.List;

import static isen_brest.projet_m1.utils.CameraUtil.copyToDir;
import static isen_brest.projet_m1.utils.FilesUtil.createFile;
import static isen_brest.projet_m1.utils.FilesUtil.getJsonDir;
import static isen_brest.projet_m1.utils.FilesUtil.listSequentiels;
import static isen_brest.projet_m1.utils.FilesUtil.mediaToBitmap;

import isen_brest.projet_m1.model.Sequentiel;
import isen_brest.projet_m1.utils.CustomGridviewModif; //utilise le layout GridView importé
import isen_brest.projet_m1.utils.JsonUtil;

@SuppressWarnings("FieldCanBeLocal")
public class ModifSeqActivity extends AppCompatActivity {

    // Déclaration du séquentiel manipulé
    private Sequentiel seq;
    // Déclaration de la liste des étapes du séquentiel modifié/crée
    private List<Sequentiel.Etape> etapes;
    // Déclaration de l'ancien nom du séquentiel
    private String oldSeqName;

    //déclaration des boutons et cases relatifs au défilement du séquentiel
    private Button slow_seq_button;
    private TextView vitesse_seq_text;
    private Button fast_seq_button;
    private CheckBox defil_manuel;

    //déclaration des boutons et cases concernant d'autres options
    private Button take_picture;
    private CheckBox synthese_vocale;

    //déclaration du GridView utilisé pour l'affichage des étapes
    private GridView modifGrid = null;

    //déclaration des boutons paramètres de chaque étape (appartiennent au Gridview)
    private Button slow_etape_button;
    private TextView vitesse_etape_text;
    private Button fast_etape_button;

    private Button lower_etape_button;
    private TextView number_etape_text;
    private Button upper_etape_button;

    //bouton pour ajouter une étape (en dehors du Gridview)
    private Button add_etape_button;

    //bouton pour valdier le séquentiel
    private Button confirm_button;

    //editText pour choisir le nom du séquentiel
    private EditText titre_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_seq);

        //branchement des variables avec leur layout
        slow_seq_button = (Button) findViewById(R.id.slow_seq_btn);
        vitesse_seq_text = (TextView) findViewById(R.id.vitesse_seq_text);
        fast_seq_button = (Button) findViewById(R.id.fast_seq_btn);
        defil_manuel = (CheckBox) findViewById(R.id.defil_manuel);

        take_picture = (Button) findViewById(R.id.take_picture);
        synthese_vocale = (CheckBox) findViewById(R.id.synth_vocale);

        modifGrid = (GridView) findViewById(R.id.modif_grid);

        slow_etape_button = (Button) findViewById(R.id.slow_etape_btn);
        vitesse_etape_text = (TextView) findViewById(R.id.vitesse_etape_text);
        fast_etape_button = (Button) findViewById(R.id.fast_etape_btn);

        lower_etape_button = (Button) findViewById(R.id.lower_etape_btn);
        number_etape_text = (TextView) findViewById(R.id.number_etape_text);
        upper_etape_button = (Button) findViewById(R.id.upper_etape_btn);

        add_etape_button = (Button) findViewById(R.id.add_etape_btn);

        confirm_button = (Button) findViewById(R.id.confirm_button);

        titre_edit = (EditText) findViewById(R.id.titre_edit);

        //variables liées au cochage des cases
        Boolean syntheseVocaleChecked = synthese_vocale.isChecked();
        Boolean defilManuelChecked = defil_manuel.isChecked();


        // -------------------------------------------------------------------------------
        // Récupération du séquentiel sélectionné à l'activité précédente
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                seq = new Sequentiel();
            } else {
                seq = listSequentiels(this).get(extras.getInt("sequentiel"));
            }
        } else {
            seq = (Sequentiel) savedInstanceState.getSerializable("sequentiel");
        }

        // On récupère la liste des activités et on initialise 'numEtapeActuelle'
        etapes = seq.getEtapeList();
        // On récupère le nom du séquentiel, si c'est un nouveau la chaine est vide
        oldSeqName = seq.getNomSequentiel();


        // --------------------------------------------------------------------------------
        // On affiche les paramètres du séquentiel

        // Nom du séquentiel
        titre_edit.setText(seq.getNomSequentiel());

        // Défilement
        if (seq.getDefilement().equals("manuel")){
            defil_manuel.setChecked(true);
        } else {
            defil_manuel.setChecked(false);
        }

        // Synthèse vocale
        if (seq.getVoix() == null){
            synthese_vocale.setChecked(false);
        } else{
            synthese_vocale.setChecked(seq.getVoix());
        }

        // --------------------------------------------------------------------------------
        // On affiche les étapes
        //displayEtapes();

        String[] iconDescr = new String[etapes.size()];

        String iconStr;
        Bitmap[] iconBitmap = new Bitmap[etapes.size()];

        for (int i = 0; i < etapes.size(); i++) {
            iconDescr[i] = etapes.get(i).getNomEtape();

            iconStr = etapes.get(i).getMedia();
            iconBitmap[i] = mediaToBitmap(this, iconStr);
        }


        //-------------exemple pour test : changer le contenu du GridView-----
        //adapteur CustomGridview
        final CustomGridviewModif adapter = new CustomGridviewModif(this, iconDescr, iconBitmap);
        modifGrid.setAdapter(adapter);

        //Méthode Onclick pour chaque étape du séquentiel
        modifGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                int position_reelle = position + 1;
                Toast.makeText(ModifSeqActivity.this, "Clic sur l'item : " + position_reelle, Toast.LENGTH_SHORT).show();
            }
        });
        //---------------------fin------------------------//

    }

    //-------------------------------------------------------------
    //------fonction prise de photo sur clic du bouton Photo-----//
    static final int REQUEST_TAKE_PHOTO = 1;
    static int PHOTO_TAKEN = -1;

    public void photo_click(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent takePictureIntent) {

        if (requestCode == REQUEST_TAKE_PHOTO) {
            if (resultCode == PHOTO_TAKEN) {
                copyToDir(this, takePictureIntent.getData());
                Toast.makeText(ModifSeqActivity.this, "Photo enregistrée !", Toast.LENGTH_SHORT).show();

            }
        }
    }

    //-------------------------------------------------------------
    // Méthode utilisée par le bouton "Ajouter une étape"
    public void addEtapeClick (View view){

    }

    // Méthode utilisée par le bouton "Valider"
    public void ConfirmClick (View view){

        //-------------------------------------------------------------
        // On vérifie si le séquentiel est vide
        if(etapes.size() == 0){
            Toast.makeText(this, "Le séquentiel est vide", Toast.LENGTH_LONG).show();
            return;
        }

        //-------------------------------------------------------------
        // On récupère les différents paramètres du séquentiel

        // Nom du séquentiel
        seq.setNomSequentiel(titre_edit.getText().toString());

        // Défilement
        if(defil_manuel.isChecked()){
            seq.setDefilement("manuel");
        } else {
            seq.setDefilement("minuteur");
        }

        // Synthèse vocale
        seq.setVoix(synthese_vocale.isChecked());

        // Icône
        seq.setIconeSequentiel(etapes.get(0).getMedia());

        // Etapes
        seq.setEtapeList(etapes);

        //-------------------------------------------------------------
        // On créer le fichier JSON associé
        JSONObject jsonObject = JsonUtil.toJson(seq);
        File pathname = getJsonDir(this);
        createFile(jsonObject.toString().getBytes(), pathname, seq.getNomSequentiel()+".json");

        //-------------------------------------------------------------
        // On retourne à la liste des séquentiels
        Intent modifMenuActivity = new Intent(this,ModifMenuActivity.class);
        startActivity(modifMenuActivity);
    }

    //-------------------------------------------------------------
    // Méthode permettant l'affichage des étapes
    private void displayEtapes (){

        String[] iconDescr = new String[etapes.size()];

        String iconStr;
        Bitmap[] iconBitmap = new Bitmap[etapes.size()];

        for (int i = 0; i < etapes.size(); i++) {
            iconDescr[i] = etapes.get(i).getNomEtape();

            iconStr = etapes.get(i).getMedia();
            iconBitmap[i] = mediaToBitmap(this, iconStr);
        }


        //-------------exemple pour test : changer le contenu du GridView-----
        //adapteur CustomGridview
        final CustomGridviewModif adapter = new CustomGridviewModif(this, iconDescr, iconBitmap);
        modifGrid.setAdapter(adapter);

        //Méthode Onclick pour chaque étape du séquentiel
        modifGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                int position_reelle = position + 1;
                Toast.makeText(ModifSeqActivity.this, "Clic sur l'item : " + position_reelle, Toast.LENGTH_SHORT).show();
            }
        });
        //---------------------fin------------------------//
    }

    // Méthode vitesse plus"
    public void vitessePlusClick (View view){
        Toast.makeText(ModifSeqActivity.this, "test", Toast.LENGTH_SHORT).show();

    }
}




