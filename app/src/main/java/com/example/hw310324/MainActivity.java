package com.example.hw310324;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText inputET;
    TextView result;
    Button saveButton;

    Button deleteButton;

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

        inputET = findViewById(R.id.inputET);
        result = findViewById(R.id.result);
        saveButton = findViewById(R.id.saveButton);
        deleteButton = findViewById(R.id.deleteButton);

        onButtonsClick();

    }

    private void onButtonsClick() {
        saveButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String textSaved = "";
        String messageEmpty = "Нечего сохранять";
        String messageSaved = "Сохранено";
        String messageDeleted = "Поля очищены";

        if (inputET.getText().toString().isEmpty()) {
            Toast.makeText(this, messageEmpty, Toast.LENGTH_SHORT).show();
        }
        switch (v.getId()) {
            case R.id.saveButton:
                textSaved = inputET.getText().toString();
                result.setText(textSaved);
                Toast.makeText(this, messageSaved, Toast.LENGTH_SHORT).show();
                break;
            case R.id.deleteButton:
                Snackbar snackbar = Snackbar.make(
                        v,
                        "Подтвердите удаление",
                        Snackbar.LENGTH_LONG
                ).setAction("Удалить", view -> {
                    result.setText("");
                    inputET.setText("");
                    Toast.makeText(this, messageDeleted, Toast.LENGTH_SHORT).show();
                });
                snackbar.show();
                break;
        }
    }
}