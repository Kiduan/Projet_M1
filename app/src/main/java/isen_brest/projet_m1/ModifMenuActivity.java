package isen_brest.projet_m1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import isen_brest.projet_m1.model.Sequentiel;
import isen_brest.projet_m1.utils.CustomGridview;
import isen_brest.projet_m1.utils.FilesUtil;
import isen_brest.projet_m1.utils.JsonUtil;

public class ModifMenuActivity extends AppCompatActivity {

    //déclaration des variables : Gridview, tableau d'icônes importées, et description correspondante
    private GridView seqList = null;
    Integer[] Icons = {
            R.drawable.img_1, R.drawable.img_2,
            R.drawable.img_3, R.drawable.img_4,
            R.drawable.img_1, R.drawable.img_2,
    };
    String[] iconDescriptions = {
            "image 1", "image 2", "image 3", "image 4", "image 5", "image 6",
    };

    //déclaration des 3 boutons supplémentaires
    private Button options;
    private Button add;
    private Button share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_menu);

        //branchement des variables avec leur layout
        seqList = (GridView) findViewById(R.id.modif_seq_list);
        options = (Button) findViewById(R.id.options_btn);
        add = (Button) findViewById(R.id.add_seq_btn);
        share = (Button) findViewById(R.id.share_btn);

        //adapteur CustomGridView
        final CustomGridview adapter = new CustomGridview(this, iconDescriptions, Icons);
        seqList.setAdapter(adapter);

        //méthode OnClick qui permet à chaque item d'exporter ses données correspondantes vers l'activité suivante
        seqList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent modifseqActivity = new Intent(view.getContext(),ModifSeqActivity.class);
                modifseqActivity.putExtra("description",((TextView) view.findViewById(R.id.custom_user_menu_icon_description)).getText());
                startActivity(modifseqActivity);
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
