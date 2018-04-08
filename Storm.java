package com.example.brysonwilks.roadwork;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Storm extends MainActivity {

    public int start, end, duration;

    EditText startInput;
    EditText durationInput;

    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startInput = (EditText) findViewById(R.id.inputStart);
        durationInput = (EditText) findViewById(R.id.inputDuration);

        next = (Button) findViewById(R.id.buttonNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start = startInput.getText();
                duration = duartionInput.getText();
            }
        });
    }
}
