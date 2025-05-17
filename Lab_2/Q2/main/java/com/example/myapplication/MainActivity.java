package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private float currentValue = 0f;  // Store the value to be passed to the next activity

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

        editText = findViewById(R.id.editTextText2);
        editText.setText("0"); // Initial value

        // Get references to the buttons
        Button one = findViewById(R.id.one);
        Button two = findViewById(R.id.two);
        Button three = findViewById(R.id.three);
        Button four = findViewById(R.id.four);
        Button five = findViewById(R.id.five);
        Button six = findViewById(R.id.six);
        Button seven = findViewById(R.id.seven);
        Button eight = findViewById(R.id.eight);
        Button nine = findViewById(R.id.nine);
        Button zero = findViewById(R.id.zero);
        Button plus = findViewById(R.id.plus);
        Button minus = findViewById(R.id.minus);
        Button multiply = findViewById(R.id.multiply);
        Button divide = findViewById(R.id.divide);
        Button ac = findViewById(R.id.AC);
        Button solve = findViewById(R.id.button2);

        // Set onClickListeners for the number buttons
        setNumberButtonClickListener(one, "1");
        setNumberButtonClickListener(two, "2");
        setNumberButtonClickListener(three, "3");
        setNumberButtonClickListener(four, "4");
        setNumberButtonClickListener(five, "5");
        setNumberButtonClickListener(six, "6");
        setNumberButtonClickListener(seven, "7");
        setNumberButtonClickListener(eight, "8");
        setNumberButtonClickListener(nine, "9");
        setNumberButtonClickListener(zero, "0");

        // Set onClickListeners for the operator buttons
        setOperatorButtonClickListener(plus, "+");
        setOperatorButtonClickListener(minus, "-");
        setOperatorButtonClickListener(multiply, "*");
        setOperatorButtonClickListener(divide, "/");

        // Clear button (AC)
        ac.setOnClickListener(v -> {
            editText.setText("0"); // Reset to 0
            currentValue = 0f; // Reset the value
        });
        // Solve button
        solve.setOnClickListener(v -> {
            // Get the current expression from the EditText
            String expression = editText.getText().toString().trim();  // Trim to avoid leading/trailing spaces

            // Check if the expression is not empty
            if (!expression.isEmpty()) {
                // Hardcode the expression evaluation logic
                float result = evaluateExpressionHardcoded(expression);

                // Send the computed result to the next activity
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("result", result);  // Send the computed result
                startActivity(intent);
            } else {
                // Handle the case where the expression is empty
                editText.setText("0");  // Reset the input field or display a message
            }
        });
    }

    private void setNumberButtonClickListener(Button button, String value) {
        button.setOnClickListener(v -> {
            String currentText = editText.getText().toString();
            // If the current value is 0 (initial value), replace it with the new number
            if (currentText.equals("0")) {
                editText.setText(value);
            } else {
                // Otherwise, append the value to the current text
                editText.append(value);
            }
        });
    }

    private void setOperatorButtonClickListener(Button button, String operator) {
        button.setOnClickListener(v -> {
            String currentText = editText.getText().toString();
            // Only allow operators to be added if there is already a number
            if (!currentText.equals("0") && !currentText.endsWith("+") && !currentText.endsWith("-") &&
                    !currentText.endsWith("*") && !currentText.endsWith("/")) {
                editText.append(operator);
            }
        });
    }

    // Hardcoded method to evaluate basic mathematical expressions
    private float evaluateExpressionHardcoded(String expression) {
        float result = 0f;

        try {
            // Trim spaces and split by space
            expression = expression.trim();
            String[] tokens = expression.split(" ");

            if (tokens.length != 3) {
                result = 0f; // Invalid expression format
            } else {
                float num1 = Float.parseFloat(tokens[0]);
                String operator = tokens[1];
                float num2 = Float.parseFloat(tokens[2]);

                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            result = 0f;  // Handle division by zero
                        }
                        break;
                    default:
                        result = 0f;  // In case the operator is unrecognized
                        break;
                }
            }
        } catch (Exception e) {
            result = 0f;  // In case the expression is invalid or improperly formatted
        }

        return result;
    }

}
