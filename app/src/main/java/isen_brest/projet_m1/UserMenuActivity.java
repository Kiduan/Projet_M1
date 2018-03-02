package isen_brest.projet_m1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.TextView;


import isen_brest.projet_m1.utils.CustomGridview; //utilise le layout GridView importé

public class UserMenuActivity extends AppCompatActivity {

    //déclaration des variables : le GridView, le tableau d'icônes importées, et leurs descriptions
    private GridView seqList = null;
    Integer[] Icons = {
            R.drawable.img_1, R.drawable.img_2,
            R.drawable.img_3, R.drawable.img_4,
            R.drawable.img_1, R.drawable.img_2,
    };
    String[] iconDescriptions = {
        "image 1", "image 2", "image 3", "image 4", "image 5", "image 6",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        seqList = (GridView) findViewById(R.id.seq_list);

        //adapteur CustomGridview
        final CustomGridview adapter = new CustomGridview(this, iconDescriptions, Icons);
        seqList.setAdapter(adapter);

        //Méthode Onclick pour chaque séquentiel : et export de données vers l'activité suivante
        seqList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent userseqActivity = new Intent(view.getContext(),UserSeqActivity.class);
                userseqActivity.putExtra("description",((TextView) view.findViewById(R.id.custom_user_menu_icon_description)).getText());
                startActivity(userseqActivity);

            }

        });

    }




}
