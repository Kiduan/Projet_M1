package isen_brest.projet_m1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static isen_brest.projet_m1.utils.FilesUtil.listSequentiels;
import static isen_brest.projet_m1.utils.FilesUtil.getMediaDir;
import static isen_brest.projet_m1.utils.FilesUtil.mediaToBitmap;

import isen_brest.projet_m1.model.Sequentiel;
import isen_brest.projet_m1.utils.CustomGridviewMenu; //utilise le layout GridView importé

public class UserMenuActivity extends AppCompatActivity {

    //déclaration des variables : le GridView, le tableau d'icônes importées, et leurs descriptions
    private GridView seqList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        //
        seqList = (GridView) findViewById(R.id.seq_list);


        // --------------------------------------------------------------------------------
        List<Sequentiel> sequentiels = listSequentiels(this);

        String[] iconDescr = new String[sequentiels.size()];

        String iconStr;
        Bitmap[] iconBitmap = new Bitmap[sequentiels.size()];

        for (int i = 0; i < sequentiels.size(); i++) {
            iconDescr[i] = sequentiels.get(i).getNomSequentiel();

            iconStr = sequentiels.get(i).getEtapeList().get(0).getMedia();
            iconBitmap[i] = mediaToBitmap(this, iconStr);
        }



        // --------------------------------------------------------------------------------
        //adapteur CustomGridview
        final CustomGridviewMenu adapter = new CustomGridviewMenu(this, iconDescr, iconBitmap);
        seqList.setAdapter(adapter);

        //Méthode Onclick pour chaque séquentiel : et export de données vers l'activité suivante
        seqList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent userseqActivity = new Intent(view.getContext(),UserSeqActivity.class);
                userseqActivity.putExtra("sequentiel", position);
                startActivity(userseqActivity);
            }
        });

    }
}
