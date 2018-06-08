package isen_brest.projet_m1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import isen_brest.projet_m1.model.Sequentiel;
import isen_brest.projet_m1.utils.CustomGridviewMenu;
import isen_brest.projet_m1.utils.FilesUtil;

import static isen_brest.projet_m1.utils.FilesUtil.getJsonDir;
import static isen_brest.projet_m1.utils.FilesUtil.listSequentiels;
import static isen_brest.projet_m1.utils.FilesUtil.mediaToBitmap;

public class ModifMenuActivity extends AppCompatActivity {

    //déclaration des variables : Gridview, tableau d'icônes importées, et description correspondante
    private GridView seqList = null;


    //déclaration des 3 boutons supplémentaires
    private Button options;
    private Button add;
    private Button share;

    //Chemin jusqu'au dossier Json
    File pathname;

    // Liste des séquentiels
    List<Sequentiel> sequentiels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_menu);

        //branchement des variables avec leur layout
        seqList = (GridView) findViewById(R.id.modif_seq_list);
        options = (Button) findViewById(R.id.options_btn);
        add = (Button) findViewById(R.id.add_seq_btn);
        share = (Button) findViewById(R.id.share_btn);

        //Chemin jusqu'au dossier Json
        pathname = getJsonDir(this);

        // --------------------------------------------------------------------------------
        // On récupère la liste des séquentiels du dossier Json
        sequentiels = listSequentiels(this);

        String[] iconDescr = new String[sequentiels.size()];

        String iconStr;
        Bitmap[] iconBitmap = new Bitmap[sequentiels.size()];

        for (int i = 0; i < sequentiels.size(); i++) {
            iconDescr[i] = sequentiels.get(i).getNomSequentiel();

            iconStr = sequentiels.get(i).getEtapeList().get(0).getMedia();
            iconBitmap[i] = mediaToBitmap(this, iconStr);
        }

        // --------------------------------------------------------------------------------
        //adapteur CustomGridView
        final CustomGridviewMenu adapter = new CustomGridviewMenu(this, iconDescr, iconBitmap);
        seqList.setAdapter(adapter);

        //méthode OnClick qui permet à chaque item d'exporter ses données correspondantes vers l'activité suivante
        seqList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent modifseqActivity = new Intent(view.getContext(),ModifSeqActivity.class);
                modifseqActivity.putExtra("sequentiel", position);
                startActivity(modifseqActivity);
            }
        });

        //méthode qui permet de supprimer l'élément choisi
        seqList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                FilesUtil.deleteFile(pathname, sequentiels.get(position).getNomSequentiel() + ".json");

                return true;
            }
        });
    }

    // méthode OnClick sur le bouton "Ajouter"
    public void add_click(View view) {
        Intent modifseqActivity = new Intent(this, ModifSeqActivity.class);
        startActivity(modifseqActivity);
    }

    // méthode OnClick sur le bouton "Partager"
    public void share_click(View view) {
    }
}
