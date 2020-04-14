package com.example.todo_liste;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button addBtn;
    private Button settingsBtn;
    private ListView listView;

    private ArrayList<Item> itemsArrayList = new ArrayList<>();
    private ItemListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBtn = findViewById(R.id.addBtn);
        settingsBtn = findViewById(R.id.settingsBtn);
        listView = findViewById(R.id.listView);

        adapter = new ItemListAdapter(this, R.layout.adapter_view_layout, itemsArrayList);

        //itemsArrayList = FileHelper.readData(this);
        listView.setAdapter(adapter);


        //addToListView(new Item("Titel 1", "Beschreibung 1", "Sehr Wichtig","10.03.1998"));


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetailActivity();
            }
        });


        try {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Item item = itemsArrayList.get(position);
                    String title = item.getTitle();
                    String description = item.getDescription();
                    String prio = item.getPriority();
                    String date = item.getDate();

                    backToDetailActivity(title, description, prio, date);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }






    private void openDetailActivity() {
        startActivityForResult(new Intent(this, DetailActivity.class), 0);
    }


    private void addToListView(Item item){

        itemsArrayList.add(item);
       adapter.add(item);

        //FileHelper.writeData(itemsArrayList,MainActivity.this);
        Toast.makeText(MainActivity.this, "Item Added", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0){
            if(resultCode == RESULT_OK){

                Item item = new Item(data.getStringExtra("Title"), data.getStringExtra("Description"), data.getStringExtra("Priority"), data.getStringExtra("Date"));
                //String title = data.getStringExtra("Title");
                addToListView(item);
            }if(resultCode == RESULT_CANCELED){
                Toast.makeText(MainActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void backToDetailActivity(String title, String description, String prio, String date) {
            Intent resultIntent = new Intent(this, DetailActivity.class);

            resultIntent.putExtra("Title", title);
            resultIntent.putExtra("Description", description);
            resultIntent.putExtra("Priority", prio);
            resultIntent.putExtra("Date", date);

            setResult(RESULT_OK, resultIntent);
            finish();

    }
}


