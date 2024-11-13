package com.example.ais;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class books extends AppCompatActivity {

    private DatabaseConnect dbConnect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_books);
        dbConnect = new DatabaseConnect(this);

        List<String> dataList = getDataFromDatabase();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
    }

    private List<String> getDataFromDatabase() {
        List<String> dataList = new ArrayList<>();
        try {
            dbConnect.openDatabase();


            String query = "SELECT * FROM Книги";
            Cursor cursor = dbConnect.getReadableDatabase().rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range")
                    int columnIndex = cursor.getColumnIndex("Код,Название,Жанр,Автор");
                    if (columnIndex != -1) {
                        String data = cursor.getString(columnIndex);
                        dataList.add(data);
                    }
                } while (cursor.moveToNext());
            }

            cursor.close();
        } catch (Exception e) {
            Log.e("books", "Ошибка при получении данных из базы данных", e);
        } finally {
            dbConnect.close();
        }
        return dataList;
    }
}