package com.laura.notizen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Laura on 21.04.2015.
 */
public class  Adapter extends ArrayAdapter<Note> {
    private LayoutInflater mLayoutInflater;


    public Adapter(Context context) {
        super(context, 0);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.listview_items, null); //hier das xml nehmen das für ein einzelnes Listview item steht

            holder = new ViewHolder();
            //hier wieder alle elemente der listview_item.xml initialisieren
            holder.titel = (TextView) convertView.findViewById(R.id.tV_Titel);
            holder.note = (TextView) convertView.findViewById(R.id.tV_Notiz);

            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();

        Note n = getItem(position);

        holder.titel.setText(n.getTitle());
        holder.note.setText(n.getContent());
        return convertView;
    }

    private static class ViewHolder {
        //hier alle elemente aus der listview_item.xml aufzählen;
        public TextView titel;
        public TextView note;
    }
}