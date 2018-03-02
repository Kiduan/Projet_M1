package isen_brest.projet_m1.utils;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import isen_brest.projet_m1.R;

public class CustomGridview  extends BaseAdapter {


    private Context mContext;

    private final Integer[] Icons;
    private final String[] iconDescriptions;


    public CustomGridview(Context c, String[] iconDescriptions, Integer[] Icons)
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
        View seqList;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            seqList = new View(mContext);
            seqList = inflater.inflate(R.layout.custom_gridview, null);
            TextView textView = (TextView) seqList.findViewById(R.id.custom_user_menu_icon_description);
            ImageView imageView = (ImageView) seqList.findViewById(R.id.custom_user_menu_icon);

            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            textView.setText(iconDescriptions[position]);
            imageView.setImageResource(Icons[position]);

        }
        else {
            seqList = (View) convertView;
        }

        return seqList;


    }
}


