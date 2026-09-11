package com.example.stickynoteswidget;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Button;

public class AddNoteActivity extends Activity {
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_add_note);

        EditText title = findViewById(R.id.title);
        EditText text = findViewById(R.id.text);
        Button save = findViewById(R.id.save);

        save.setOnClickListener(v -> {
            getSharedPreferences("notes", MODE_PRIVATE)
                .edit()
                .putString("title", title.getText().toString())
                .putString("text", text.getText().toString())
                .apply();

            Intent update = new Intent(
                this,
                StickyWidgetProvider.class
            );
            update.setAction(
                StickyWidgetProvider.ACTION_UPDATE
            );
            sendBroadcast(update);

            finish();
        });
    }
}
