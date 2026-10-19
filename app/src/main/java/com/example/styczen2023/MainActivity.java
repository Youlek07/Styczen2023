
package com.example.styczen2023;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText nameEditText, surnameEditText, charCountEditText;
    Spinner positionSpinner;
    CheckBox textSizeCheckBox, numbersCheckBox, specialCharsCheckBox;
    Button generateButton, submitButton;

    String LOWERCASE_ALPHABET = "qwertyuiopasdfghjklzxcvbnm";
    String UPPERCASE_ALPHABET = LOWERCASE_ALPHABET.toUpperCase();
    String NUMBERS = "0123456789";
    String SPECIAL_CHARS = " !@#$%^&*()_+-=";

    String genpass = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEditText);
        surnameEditText = findViewById(R.id.surnameEditText);
        charCountEditText = findViewById(R.id.charCountEditText);

        positionSpinner = findViewById(R.id.positionSpinner);

        textSizeCheckBox = findViewById(R.id.textSizeCheckBox);
        numbersCheckBox = findViewById(R.id.numbersCheckBox);
        specialCharsCheckBox = findViewById(R.id.specialCharsCheckBox);

        generateButton = findViewById(R.id.generateButton);
        submitButton = findViewById(R.id.submitButton);

        String[] positions = {
                "Kierownik",
                "Starszy programista",
                "Młodszy programista",
                "Tester"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                positions
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        positionSpinner.setAdapter(adapter);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = charCountEditText.getText().toString().trim();

                if (input.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Wprowadź długość hasła!", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    int charCount = Integer.parseInt(input);

                    if (charCount <= 0) {
                        Toast.makeText(MainActivity.this, "Długość musi być większa od 0", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    genpass = generatePassword(charCount);

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Wprowadź poprawną liczbę!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String surname = surnameEditText.getText().toString();
                String position = positionSpinner.getSelectedItem().toString();

                Toast.makeText(MainActivity.this, "Dane pracownika: " + name + " " + surname + " " + position + " Hasło: " + genpass, Toast.LENGTH_LONG).show();
            }
        });
    }

    public String generatePassword(Integer charCount) {
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < charCount; i++) {
            if (textSizeCheckBox.isChecked() && i == 0) {
                int randomUpperIndex = random.nextInt(UPPERCASE_ALPHABET.length());
                password.append(UPPERCASE_ALPHABET.charAt(randomUpperIndex));
            } else if (numbersCheckBox.isChecked() && i == 1) {
                int randomNumberIndex = random.nextInt(NUMBERS.length());
                password.append(NUMBERS.charAt(randomNumberIndex));
            } else if (specialCharsCheckBox.isChecked() && i == 2) {
                int randomSpecialCharIndex = random.nextInt(SPECIAL_CHARS.length());
                password.append(SPECIAL_CHARS.charAt(randomSpecialCharIndex));
            } else {
                int randomLowerIndex = random.nextInt(LOWERCASE_ALPHABET.length());
                password.append(LOWERCASE_ALPHABET.charAt(randomLowerIndex));
            }
        }
        return password.toString();
    }
}
