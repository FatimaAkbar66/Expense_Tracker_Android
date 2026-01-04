package com.example.expensetracker;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    ListView listView;
    ArrayList<String> expenseList;
    ArrayAdapter adapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);
        listView = findViewById(R.id.listView);
        fab = findViewById(R.id.fab);
        expenseList = new ArrayList<>();

        loadData();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddExpenseActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData(); // Jab naya data add ho kar wapis ayein to refresh ho jaye
    }

    private void loadData() {
        expenseList.clear();
        Cursor res = myDb.getAllData();
        while (res.moveToNext()) {
            expenseList.add(res.getString(1) + " : Rs. " + res.getString(2));
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, expenseList);
        listView.setAdapter(adapter);
    }
}