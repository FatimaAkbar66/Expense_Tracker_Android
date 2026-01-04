package com.example.expensetracker; // Apna sahi package name check karein

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddExpenseActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editTitle, editAmount;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // AGAR XML FILE KA NAAM activity_add HAI TO YE LINE SAHI HAI:
        setContentView(R.layout.activity_add);

        myDb = new DatabaseHelper(this);
        editTitle = findViewById(R.id.editTitle);
        editAmount = findViewById(R.id.editAmount);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            String title = editTitle.getText().toString();
            String amount = editAmount.getText().toString();

            if(!title.isEmpty() && !amount.isEmpty()) {
                boolean isInserted = myDb.insertData(title, amount);
                if(isInserted) {
                    Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            } else {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}