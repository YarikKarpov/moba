package com.example.ais;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button shops;
    Button clients;
    Button worker;
    Button postavka;
    Button books;
    Button postavshick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        postavshick = findViewById(R.id.button5);
        books =findViewById(R.id.button2);
        postavka = findViewById(R.id.button4);
        worker = findViewById(R.id.button6);
        clients = findViewById(R.id.button3);
        shops = findViewById(R.id.button);

        clients.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, clienttable.class);
            startActivity(intent);
        });
        postavshick.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, postavshick.class);
            startActivity(intent);
        });
        books.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, books.class);
            startActivity(intent);
        });

        shops.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, shopsactivity.class);
            startActivity(intent);
        });
        worker.setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this, Workers.class);
            startActivity(intent);
        });
        postavka.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, postavka.class);
            startActivity(intent);
        });
        DatabaseConnect dbConnect = new DatabaseConnect(this);
        try {
            dbConnect.createDatabase();
            dbConnect.openDatabase();
        } catch (IOException e) {
            Log.e("DatabaseError", "Failed to initialize database", e);
            Toast.makeText(this, "Database initialization failed", Toast.LENGTH_SHORT).show();
        }
    }
}
