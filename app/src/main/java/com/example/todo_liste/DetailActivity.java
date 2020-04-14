package com.example.todo_liste;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class DetailActivity extends AppCompatActivity {

    private Button hinzufügenBtn;

    private EditText editTextTitle;
    private EditText editTextDescription;

    private Button priorityHighBtn;
    private Button priorityMiddleBtn;
    private Button priorityLowBtn;

    private Button AblaufDatumBtn;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    String prio = "";
    String date = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        hinzufügenBtn = findViewById(R.id.hinzufügenBtn);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        priorityHighBtn = findViewById(R.id.priorityHighBtn);
        priorityMiddleBtn = findViewById(R.id.priorityMiddleBtn);
        priorityLowBtn = findViewById(R.id.priorityLowBtn);

        AblaufDatumBtn = findViewById(R.id.AblaufDatumBtn);

        AblaufDatumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        DetailActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        onDateSetListener,
                        year, month, day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                date = dayOfMonth + "." + month + "." + year;
            }
        };

        hinzufügenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMainActivity();
            }
        });

        priorityHighBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prio = "High";
            }
        });

        priorityLowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prio = "Low";
            }
        });

        priorityMiddleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prio = "Middle";
            }
        });
    }

    private void backToMainActivity() {
        if(editTextTitle.getText().toString().equals("")){
            Toast.makeText(this, "Please insert something", Toast.LENGTH_SHORT).show();
        }else{
            String title = editTextTitle.getText().toString();
            String description = editTextDescription.getText().toString();

            Intent resultIntent = new Intent(this, MainActivity.class);

            resultIntent.putExtra("Title", title);
            resultIntent.putExtra("Description", description);
            resultIntent.putExtra("Priority", prio);
            resultIntent.putExtra("Date", date);

            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0){
            if(resultCode == RESULT_OK){

                editTextTitle.setText(data.getStringExtra("Title"));
                editTextDescription.setText(data.getStringExtra("Description"));
                prio = data.getStringExtra("Priority");
                date = data.getStringExtra("Date");

            }if(resultCode == RESULT_CANCELED){
                Toast.makeText(DetailActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
            }



        }
    }
}
