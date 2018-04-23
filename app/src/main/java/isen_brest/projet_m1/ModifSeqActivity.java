package isen_brest.projet_m1;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

import static isen_brest.projet_m1.utils.CameraUtil.getPhotosDir;

@SuppressWarnings("FieldCanBeLocal")
public class ModifSeqActivity extends AppCompatActivity {

    //déclaration du texte par défaut et du texte dans lequel on charge les données
    private TextView modif_text;
    private TextView modif_serial_description;
    private Button take_picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_seq);

        //branchement des variables avec leur layout
        modif_text = (TextView) findViewById(R.id.modif_seq_default_text);
        modif_serial_description = (TextView) findViewById(R.id.modif_serial_description);
        take_picture = (Button) findViewById(R.id.take_picture);

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
    }

    //fonction prise de photo sur clic du bouton Photo
    static final int REQUEST_TAKE_PHOTO = 1;
    static int PHOTO_TAKEN = -1;

    public void photo_click(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            File Picture = new File(getPhotosDir(this), "picture.jpg");

            Uri uri = Uri.fromFile(Picture);
            //  takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent takePictureIntent) {

        if (requestCode == REQUEST_TAKE_PHOTO) {

            // Make sure the request was successful
            if (resultCode == PHOTO_TAKEN) {
                Toast.makeText(ModifSeqActivity.this, "Toast", Toast.LENGTH_LONG).show();

                //Enregistrer la photo au bon endroit (dans file Photos)
                File Picture = new File(getPhotosDir(this), "picture.jpg");
                try {
                    FileChannel fis = new FileInputStream(takePictureIntent.getData().toString()).getChannel();
                    FileChannel fos = new FileOutputStream(Picture).getChannel();
                    if (fis != null && fos != null) {
                        fos.transferFrom(fis, 0, fis.size());
                    }
                    fis.close();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(ModifSeqActivity.this,
                        "La photo est stockée", Toast.LENGTH_SHORT).show();

                //  Uri pathPicture = Uri.fromFile(Picture);
                // takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, pathPicture);
            }
        }
    }
}




