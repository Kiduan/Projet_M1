package isen_brest.projet_m1.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import isen_brest.projet_m1.R;

public class CustomGridviewModif extends BaseAdapter {

    private Context mContext;
    private Bitmap[] Icons;
    private final String[] iconDescriptions;

    public CustomGridviewModif(Context c, String[] iconDescriptions, Bitmap[] Icons)
    {
        mContext = c;
        this.Icons = Icons;
        this.iconDescriptions = iconDescriptions;
    }

    @Override
    public int getCount() {
        return iconDescriptions.length;
    }
    @Override
    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View modifList;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            modifList = new View(mContext);
            modifList = inflater.inflate(R.layout.custom_gridview_modif, null);

        }
        else {
            modifList = (View) convertView;
        }

        TextView textView = (TextView) modifList.findViewById(R.id.custom_user_menu_icon_description);
        ImageView imageView = (ImageView) modifList.findViewById(R.id.custom_user_menu_icon);

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        textView.setText(iconDescriptions[position]);
        imageView.setImageBitmap(Icons[position]);
        return modifList;
    }

}


