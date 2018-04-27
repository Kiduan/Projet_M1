package isen_brest.projet_m1;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import static isen_brest.projet_m1.utils.CameraUtil.copyToDir;
import isen_brest.projet_m1.utils.CustomGridview; //utilise le layout GridView importé

@SuppressWarnings("FieldCanBeLocal")
public class ModifSeqActivity extends AppCompatActivity {

    //déclaration du texte par défaut et du texte dans lequel on charge les données
    private TextView modif_text;
    private TextView modif_serial_description;
    private Button take_picture;
    private GridView seqGrid = null;

    //------------------------Exemple pour test !-----------
    Integer[] Icons = {
            R.drawable.img_1, R.drawable.img_2,
            R.drawable.img_3, R.drawable.img_4,
            R.drawable.img_1, R.drawable.img_2,
    };
    String[] iconDescriptions = {
            "image 1", "image 2", "image 3", "image 4", "image 5", "image 6",
    };
    //------------------------A remplacer par les images prises -------

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_seq);

        //branchement des variables avec leur layout
        modif_text = (TextView) findViewById(R.id.modif_seq_default_text);
        modif_serial_description = (TextView) findViewById(R.id.modif_serial_description);
        take_picture = (Button) findViewById(R.id.take_picture);
        seqGrid = (GridView) findViewById(R.id.seq_grid);

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

        //-------------exemple pour test : changer le contenu du GridView-----
        //adapteur CustomGridview
        final CustomGridview adapter = new CustomGridview(this, iconDescriptions, Icons);
        seqGrid.setAdapter(adapter);

        //Méthode Onclick pour chaque étape du séquentiel
        seqGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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




