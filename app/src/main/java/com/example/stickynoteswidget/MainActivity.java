package com.example.stickynoteswidget;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        Button add = findViewById(R.id.add);
        add.setOnClickListener(v ->
            startActivity(new Intent(this, AddNoteActivity.class))
        );
    }
}
