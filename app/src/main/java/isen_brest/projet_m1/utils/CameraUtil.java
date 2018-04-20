package isen_brest.projet_m1.utils;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;


public class CameraUtil {

       public static File getPhotosDir(Context context) {
           File Photos = new File(context.getExternalFilesDir(null), "Photos");
           if (!Photos.mkdirs()) {
               Log.e("Error: ", "Directory not created");
           }
           return Photos;
       }
   }




