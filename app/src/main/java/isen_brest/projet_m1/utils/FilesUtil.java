package isen_brest.projet_m1.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import isen_brest.projet_m1.R;
import isen_brest.projet_m1.model.Sequentiel;

import static isen_brest.projet_m1.utils.JsonUtil.toSequentiel;

public class FilesUtil {

    // Vérifie si le stockage externe est accessible en lecture et écriture
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    // Vérifie si le stockage externe est accessible en lecture
    public static boolean isExternalStorageReadable() {

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    // Retourne un fichier contenant le chemin jusqu'au dossier Json.
    // Ce dossier est situé dans le dossier files du répertoire privé externe de l'application.
    // Si le dossier Json n'existe pas, il est crée.
    public static File getJsonDir(Context context) {
        File file = new File(context.getExternalFilesDir(null), "Json");
        if (!file.mkdirs()) {
            Log.e("Error: ", "Directory not created");
        }
        return file;
    }

    // Comme getJsonDir mais pour le dossier "Media"
    public static File getMediaDir(Context context) {
        File file = new File(context.getExternalFilesDir(null), "Media");
        if (!file.mkdirs()) {
            Log.e("Error: ", "Directory not created");
        }
        return file;
    }

    // Crée un fichier à l'emplacement fourni par pathname, au titre par filename et au contenu par data
    public static void  createFile(byte [] data, File pathname, String filename) {

        File file = new File(pathname, filename); // Crée le chemin jusqu'à l'emplacement du fichier

        try {
            OutputStream os = new FileOutputStream(file);
            os.write(data);
            os.close();
        } catch (IOException e) {
            Log.w("Error", "Error writing " + file, e);
        }
    }

    // Supprime le fichier à l'emplacement fourni par pathname et au titre par filename
    public static void deleteFile(File pathname, String filename) {

        File file = new File(pathname, filename); // Crée le chemin jusqu'à l'emplacement du fichier
        if (file != null) {
            file.delete();
        }
    }

    // Ouvre le fichier JSON à l'emplacement fourni par pathname et au titre par filename.
    // Retourne le contenu du fichier sous la forme d'une chaine de caractères
    public static JSONObject openJsonFile(File jsonFile) {

        String data = null;
        JSONObject jsonObject = null;

        try {
            FileInputStream fis = new FileInputStream(jsonFile);

            FileChannel fc = fis.getChannel();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());

            data = Charset.defaultCharset().decode(bb).toString();

            fis.close();
        } catch (IOException e) {
            Log.w("Error", "Error reading " + jsonFile, e);
        }

        try {
            jsonObject = new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    // Parcours le dossier Json de l'application et retourne une liste des objets Sequentiels
    public static List<Sequentiel> listSequentiels (Context context)
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

    // Vérifie l'existence de l'image donnée par "media" dans le dossier media de l'application
    // Retourne un objet Bitmap de l'image
    // Retourne une image par défaut si l'image n'existe pas
    public static Bitmap mediaToBitmap(Context context, String media){

        File filepath;
        Bitmap bitmap;

        // Si la chaine est vide on retourne l'image par défaut
        if(media.isEmpty()){
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.img_1);
        }

        // L'objet File pointe sur le media choisi dans le dossier media de l'application
        filepath = new File(getMediaDir(context), media);

        // Si le fichier existe, on construit l'objet Bitmap correspondant
        if (filepath.exists()) {
            bitmap = BitmapFactory.decodeFile(filepath.toString());
        }
        // Si le fichier n'existe pas, on retourne l'image par défaut
        else {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.img_1);
        }

        return bitmap;
    }



    // Non utilisée, non testée
    // Copie une ressource depuis 'raw' vers le dossier de l'application
    private void copyResourceToExternalStorage(Context context, int resourceId, String resourceName){

        String path = context.getExternalFilesDir(null) + resourceName;

        try{
            InputStream in = context.getResources().openRawResource(resourceId);

            FileOutputStream out = null;
            out = new FileOutputStream(path);

            byte[] buff = new byte[1024];
            int read = 0;
            try {
                while ((read = in.read(buff)) > 0) {
                    out.write(buff, 0, read);
                }
            } finally {
                in.close();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
