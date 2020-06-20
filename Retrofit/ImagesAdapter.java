package com.example.retrofit2stub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

public class ImagesAdapter extends BaseAdapter {

    Context ctx;
    Picasso picasso;
    Hit[] hits;


    @Override
    public long getItemId(int pos) {
        return pos;
    }

    public ImagesAdapter(Context ctx, Hit[] hits) {
        this.ctx = ctx;
        this.hits = hits;
        picasso = new Picasso.Builder(ctx).build();
    }

    @Override
    public int getCount() {
        return hits.length;
    }

    @Override
    public Object getItem(int pos) {
        return hits[pos];
    }



    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        Hit hit = hits[pos];
        convertView = LayoutInflater.from(ctx).
                inflate(R.layout.picture_item, parent, false);
        ImageView iv = convertView.findViewById(R.id.pic);
        picasso.load(hit.previewURL).error(R.drawable.no_image).into(iv);
        return convertView;
    }
}
