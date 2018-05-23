package isen_brest.projet_m1.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

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

import isen_brest.projet_m1.R;

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

    // Donne la liste des id des ressources contenus 'raw'
    private void listResources () {

    }

    // Liste tous les fichiers JSON du dossier de l'application
    private void ListJson () {

    }

    public static void showPicture (Context context, ImageView view, String path) {
        //Picasso.get().load(path).into(view);
        Picasso.get().load(context.getResources().getIdentifier("img_1.jpg", "drawable", context.getPackageName())).into(view);
    }
}
