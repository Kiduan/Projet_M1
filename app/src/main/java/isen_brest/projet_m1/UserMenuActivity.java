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

import static isen_brest.projet_m1.utils.FilesUtil.getJsonDir;
import static isen_brest.projet_m1.utils.FilesUtil.getMediaDir;
import static isen_brest.projet_m1.utils.FilesUtil.openJsonFile;
import static isen_brest.projet_m1.utils.JsonUtil.toSequentiel;

import isen_brest.projet_m1.model.Sequentiel;
import isen_brest.projet_m1.utils.CustomGridviewMenu; //utilise le layout GridView importé

public class UserMenuActivity extends AppCompatActivity {

    //déclaration des variables : le GridView, le tableau d'icônes importées, et leurs descriptions
    private GridView seqList = null;
    Integer[] Icons = {
            R.drawable.img_1, R.drawable.img_2,
            R.drawable.img_3, R.drawable.img_4,
            R.drawable.img_1, R.drawable.img_2,
            R.drawable.img_1, R.drawable.img_2,
            R.drawable.img_3, R.drawable.img_4,
            R.drawable.img_1, R.drawable.img_2,
            R.drawable.img_1, R.drawable.img_2,
            R.drawable.img_3, R.drawable.img_4,
            R.drawable.img_1, R.drawable.img_2,
            R.drawable.img_1, R.drawable.img_2,
            R.drawable.img_3, R.drawable.img_4,
            R.drawable.img_1, R.drawable.img_2,
    };
    String[] iconDescriptions = {
        "image 1", "image 2", "image 3", "image 4", "image 5", "image 6",
            "image 7", "image 8", "image 9", "image 10", "image 11", "image 12",
            "image 13", "image 14", "image 15", "image 16", "image 17", "image 18",
            "image 19", "image 20", "image 21", "image 22", "image 23", "image 24",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        seqList = (GridView) findViewById(R.id.seq_list);


        // ---------- Temporaire ----------
        // --------------------------------
        List<Sequentiel> sequentiels = listSequentiels(this);

        String[] iconDescr = arrayNomSeq(sequentiels);
        String[] iconStr = arrayIconSeq(sequentiels);

        Bitmap[] iconBitmap = new Bitmap[iconStr.length];
        for (int i = 0; i < iconStr.length; i++) {
            iconBitmap[i] = mediaToBitmap(this, iconStr[i]);
        }

        Integer[] iconInt = new Integer[iconStr.length];
        for (int i = 0; i < iconStr.length; i++) {
            iconInt[i] = R.drawable.img_2;
        }
        // ---------------------------------
        // ---------------------------------


        //adapteur CustomGridview
        //final CustomGridviewMenu adapter = new CustomGridviewMenu(this, iconDescriptions, Icons);
        final CustomGridviewMenu adapter = new CustomGridviewMenu(this, iconDescr, iconBitmap);
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

    private List<Sequentiel> listSequentiels (Context context)
    {
        List<Sequentiel> sequentiels = new ArrayList<>();

        File directory = getJsonDir(context);

        File[] files = directory.listFiles();

        for (int i = 0; i < files.length; i++)
        {
            // openJsonFile retourne un JSONObject depuis un fichier
            // toSequentiel retourne un Sequentiel depuis un JSONObject
            // add ajoute ce sequentiel à la liste "sequentiels"
            sequentiels.add( toSequentiel( openJsonFile( files[i] ) ) );
        }

        return sequentiels;
    }

    private String[] arrayNomSeq(List<Sequentiel> listSeq)
    {
        String[] nomSeq = new String[listSeq.size()];

        for (int i = 0; i < listSeq.size(); i++) {
            nomSeq[i] = listSeq.get(i).getNomSequentiel();
        }

        return nomSeq;
    }

    private String[] arrayIconSeq(List<Sequentiel> listSeq)
    {
        String[] IconSeq = new String[listSeq.size()];

        for (int i = 0; i < listSeq.size(); i++) {
            IconSeq[i] = listSeq.get(i).getEtapeList().get(0).getMedia();
        }

        return IconSeq;
    }

    private Bitmap mediaToBitmap(Context context, String media){
        File filepath = new File(getMediaDir(context), media);
        Bitmap bitmap = null;

        if(filepath.exists()){
            bitmap = BitmapFactory.decodeFile(filepath.toString());
        }
        else {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.img_1);
        }

        return bitmap;
    }
}
