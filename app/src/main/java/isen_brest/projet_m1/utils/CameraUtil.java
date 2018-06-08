package isen_brest.projet_m1.utils;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CameraUtil {

    //création du fichier "Photos"
    public static File getPhotosDir(Context context) {
        File Photos = new File(context.getExternalFilesDir(null), "Photos");
        if (!Photos.mkdirs()) {
            Log.e("Error: ", "Directory not created");
        }
        return Photos;
    }

    //récupération du chemin réel de la photo prise (utilisé pour la copier dans "Photos"
    //fonction trouvée sur internet, qui fonctionne très bien mais dont on n'a pas compris toutes les subtilités
    public static String getRealPathFromURI(Context context, Uri uri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(uri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    //fonction qui permet de copier la photo prise dans le dossier voulu, avec le nom voulu
    public static void copyToDir (Context context, Uri uri) {
        try {
            String picture_path = getPhotosDir(context).toString();

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File picture = new File(picture_path, "IMG_" + timeStamp + ".jpg");
            String input_path = getRealPathFromURI(context, uri);

            FileChannel fis = new FileInputStream(input_path).getChannel();
            FileChannel fos = new FileOutputStream(picture).getChannel();
            if (fis != null && fos != null) {
                fos.transferFrom(fis, 0, fis.size());
            }
            fis.close();
            fos.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


}