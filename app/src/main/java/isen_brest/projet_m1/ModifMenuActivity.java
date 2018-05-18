package isen_brest.projet_m1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.TextView;

import isen_brest.projet_m1.utils.CustomGridviewMenu;

public class ModifMenuActivity extends AppCompatActivity {

    //déclaration des variables : Gridview, tableau d'icônes importées, et description correspondante
    private GridView seqList = null;
    Integer[] Icons = {
            R.drawable.img_1, R.drawable.img_2,
            R.drawable.img_3, R.drawable.img_4,
            R.drawable.img_1, R.drawable.img_2,
            R.drawable.img_3, R.drawable.img_4,
            R.drawable.img_1, R.drawable.img_2,
            R.drawable.img_3, R.drawable.img_4,
            R.drawable.img_1, R.drawable.img_2,
            R.drawable.img_3, R.drawable.img_4,
            R.drawable.img_1, R.drawable.img_2,
            R.drawable.img_3, R.drawable.img_4,
            R.drawable.img_1, R.drawable.img_2,
            R.drawable.img_3, R.drawable.img_4,
            R.drawable.img_1, R.drawable.img_2,
            R.drawable.img_3, R.drawable.img_4,
            R.drawable.img_1, R.drawable.img_2,
            R.drawable.img_3, R.drawable.img_4,
            R.drawable.img_1, R.drawable.img_2,
            R.drawable.img_3, R.drawable.img_4,
            R.drawable.img_1, R.drawable.img_2,


    };
    String[] iconDescriptions = {
            "image 1", "image 2", "image 3", "image 4", "image 5", "image 6",
            "image 7", "image 8", "image 9", "image 10",
            "image 11", "image 12", "image 13", "image 14",
            "image 15", "image 16", "image 17", "image 18",
            "image 19", "image 20", "image 21", "image 22",
            "image 23", "image 24", "image 25", "image 26",
            "image 27", "image 28", "image 29", "image 30",
            "image 31", "image 32", "image 33", "image 34",
            "image 35", "image 36", "image 37", "image 38",
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
        final CustomGridviewMenu adapter = new CustomGridviewMenu(this, iconDescriptions, Icons);
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
