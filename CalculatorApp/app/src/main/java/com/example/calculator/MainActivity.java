package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private String currentNumber = "0";
    private String previousNumber = "";
    private String operator = "";
    private boolean isNewNumber = true;
    private boolean hasDecimal = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);
        
        initializeButtons();
    }

    private void initializeButtons() {
        // Number buttons
        findViewById(R.id.btnZero).setOnClickListener(v -> appendNumber("0"));
        findViewById(R.id.btnOne).setOnClickListener(v -> appendNumber("1"));
        findViewById(R.id.btnTwo).setOnClickListener(v -> appendNumber("2"));
        findViewById(R.id.btnThree).setOnClickListener(v -> appendNumber("3"));
        findViewById(R.id.btnFour).setOnClickListener(v -> appendNumber("4"));
        findViewById(R.id.btnFive).setOnClickListener(v -> appendNumber("5"));
        findViewById(R.id.btnSix).setOnClickListener(v -> appendNumber("6"));
        findViewById(R.id.btnSeven).setOnClickListener(v -> appendNumber("7"));
        findViewById(R.id.btnEight).setOnClickListener(v -> appendNumber("8"));
        findViewById(R.id.btnNine).setOnClickListener(v -> appendNumber("9"));

        // Operator buttons
        findViewById(R.id.btnAdd).setOnClickListener(v -> setOperator("+"));
        findViewById(R.id.btnSubtract).setOnClickListener(v -> setOperator("-"));
        findViewById(R.id.btnMultiply).setOnClickListener(v -> setOperator("×"));
        findViewById(R.id.btnDivide).setOnClickListener(v -> setOperator("÷"));

        // Function buttons
        findViewById(R.id.btnClear).setOnClickListener(v -> clearAll());
        findViewById(R.id.btnDelete).setOnClickListener(v -> deleteLast());
        findViewById(R.id.btnPercent).setOnClickListener(v -> calculatePercent());
        findViewById(R.id.btnDecimal).setOnClickListener(v -> appendDecimal());
        findViewById(R.id.btnEquals).setOnClickListener(v -> calculateResult());
    }

    private void appendNumber(String number) {
        if (isNewNumber) {
            currentNumber = number;
            isNewNumber = false;
        } else {
            // Prevent multiple leading zeros
            if (currentNumber.equals("0") && !number.equals("0")) {
                currentNumber = number;
            } else if (!currentNumber.equals("0") || !number.equals("0")) {
                currentNumber += number;
            }
        }
        updateDisplay();
    }

    private void appendDecimal() {
        if (isNewNumber) {
            currentNumber = "0.";
            isNewNumber = false;
            hasDecimal = true;
        } else if (!hasDecimal) {
            currentNumber += ".";
            hasDecimal = true;
        }
        updateDisplay();
    }

    private void setOperator(String op) {
        if (!operator.isEmpty() && !isNewNumber) {
            calculateResult();
        }
        previousNumber = currentNumber;
        operator = op;
        isNewNumber = true;
        hasDecimal = false;
    }

    private void calculateResult() {
        if (operator.isEmpty() || previousNumber.isEmpty()) {
            return;
        }

        try {
            BigDecimal num1 = new BigDecimal(previousNumber);
            BigDecimal num2 = new BigDecimal(currentNumber);
            BigDecimal result = BigDecimal.ZERO;

            switch (operator) {
                case "+":
                    result = num1.add(num2);
                    break;
                case "-":
                    result = num1.subtract(num2);
                    break;
                case "×":
                    result = num1.multiply(num2);
                    break;
                case "÷":
                    if (num2.compareTo(BigDecimal.ZERO) == 0) {
                        showError("Cannot divide by zero");
                        return;
                    }
                    result = num1.divide(num2, 10, RoundingMode.HALF_UP);
                    break;
            }

            // Remove trailing zeros
            currentNumber = result.stripTrailingZeros().toPlainString();
            operator = "";
            previousNumber = "";
            isNewNumber = true;
            hasDecimal = currentNumber.contains(".");
            updateDisplay();

        } catch (NumberFormatException e) {
            showError("Error");
        }
    }

    private void calculatePercent() {
        try {
            BigDecimal num = new BigDecimal(currentNumber);
            BigDecimal result = num.divide(new BigDecimal("100"), 10, RoundingMode.HALF_UP);
            currentNumber = result.stripTrailingZeros().toPlainString();
            hasDecimal = currentNumber.contains(".");
            updateDisplay();
        } catch (NumberFormatException e) {
            showError("Error");
        }
    }

    private void clearAll() {
        currentNumber = "0";
        previousNumber = "";
        operator = "";
        isNewNumber = true;
        hasDecimal = false;
        updateDisplay();
    }

    private void deleteLast() {
        if (currentNumber.length() > 1) {
            if (currentNumber.endsWith(".")) {
                hasDecimal = false;
            }
            currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
        } else {
            currentNumber = "0";
            isNewNumber = true;
            hasDecimal = false;
        }
        updateDisplay();
    }

    private void updateDisplay() {
        tvDisplay.setText(currentNumber);
    }

    private void showError(String message) {
        tvDisplay.setText(message);
        currentNumber = "0";
        previousNumber = "";
        operator = "";
        isNewNumber = true;
        hasDecimal = false;
    }
}
