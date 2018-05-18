package isen_brest.projet_m1;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static isen_brest.projet_m1.utils.CameraUtil.copyToDir;
import isen_brest.projet_m1.utils.CustomGridviewModif; //utilise le layout GridView importé

@SuppressWarnings("FieldCanBeLocal")
public class ModifSeqActivity extends AppCompatActivity {

    //déclaration du texte par défaut et du texte dans lequel on charge les données
    private TextView modif_text;
    private TextView modif_serial_description;

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

    //------------------------Exemple pour test !-----------
    Integer[] Icons = {
            R.drawable.img_1, R.drawable.img_2,
            R.drawable.img_3, R.drawable.img_4,
            R.drawable.img_1, R.drawable.img_2,
            R.drawable.img_4
    };
    String[] iconDescriptions = {
            "image 1", "image 2", "image 3", "image 4", "image 5", "image 6", "image 7"
    };
    //------------------------A remplacer par les images prises -------

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_seq);

        //branchement des variables avec leur layout
        modif_text = (TextView) findViewById(R.id.modif_seq_default_text);
        modif_serial_description = (TextView) findViewById(R.id.modif_serial_description);

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

        //affichage des données importées depuis le clic précédent
        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                newString = null;
            } else {
                newString = extras.getString("description");
            }
        } else {
            newString = (String) savedInstanceState.getSerializable("description");
        }
        modif_serial_description.setText(newString);

        //variables liées au cochage des cases
        Boolean syntheseVocaleChecked = synthese_vocale.isChecked();
        Boolean defilManuelChecked = defil_manuel.isChecked();

        //-------------exemple pour test : changer le contenu du GridView-----
        //adapteur CustomGridview
        final CustomGridviewModif adapter = new CustomGridviewModif(this, iconDescriptions, Icons);
        modifGrid.setAdapter(adapter);

        //Méthode Onclick pour chaque étape du séquentiel
        modifGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                int position_reelle = position +1;
                Toast.makeText(ModifSeqActivity.this, "Clic sur l'item : " + position_reelle, Toast.LENGTH_SHORT).show();
            }
        });
    }
    //---------------------fin------------------------//

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
}




