package com.example.todo_liste;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ItemListAdapter extends ArrayAdapter<Item> {

    private Context context;
    int resource;
    ArrayList<Item> arrayList;

    public ItemListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Item> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrayList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(resource, parent, false);

        TextView title = row.findViewById(R.id.textViewTitle);
        TextView desc = row.findViewById(R.id.textViewDescription);
        TextView prio = row.findViewById(R.id.textViewPriority);
        TextView date = row.findViewById(R.id.textViewDate);

        title.setText(arrayList.get(position).getTitle());
        desc.setText(arrayList.get(position).getDescription());
        prio.setText(arrayList.get(position).getPriority());
        date.setText(arrayList.get(position).getDate());


        return row;

        /*
        String title = getItem(position).getTitle();
        String description = getItem(position).getDescription();
        String priority = getItem(position).getPriority();
        String date = getItem(position).getDate();

        Item item = new Item(title, description, priority, date);

        LayoutInflater inflater = LayoutInflater.from(this.context);
        convertView = inflater.inflate(resource, parent, false);

        TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
        TextView textViewDescription = convertView.findViewById(R.id.textViewDescription);
        TextView textViewPriority = convertView.findViewById(R.id.textViewPriority);
        TextView textViewDate = convertView.findViewById(R.id.textViewDate);

        textViewTitle.setText(title);
        textViewDescription.setText(description);
        textViewPriority.setText(priority);
        textViewDate.setText(date);

        return convertView;

         */
    }
}
