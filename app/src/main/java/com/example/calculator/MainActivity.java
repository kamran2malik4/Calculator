package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //These views object will display user input and result of query
    //These will be accessed in many places so these are made global
    private TextView m_resultView, m_inputView;

    //This variable Keeps Track of which symbol was pressed, 0 mean no symbol
    private char m_currentSymbol;

    //These variables define the Symbolic Constant for Operators
    private final char PLUS = '+', MINUS = '-', MULTIPLY = '*', DIVIDE = '/', MODULUS = '%';

    //These Variable keeps track of values before and after the Operator
    private double value1 = 0, value2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_resultView = findViewById(R.id.display_result);
        m_inputView = findViewById(R.id.display_input);

        Button zero, one, two, three, four, five, six, seven, eight, nine,
            dot, clear, undo, plus, minus, multiply, divide, modulus, equal;

        zero = findViewById(R.id.zero_button);
        one = findViewById(R.id.one_button);
        two = findViewById(R.id.two_button);
        three = findViewById(R.id.three_button);
        four = findViewById(R.id.four_button);
        five = findViewById(R.id.five_button);
        six = findViewById(R.id.six_button);
        seven = findViewById(R.id.seven_button);
        eight = findViewById(R.id.eight_button);
        nine = findViewById(R.id.nine_button);
        dot = findViewById(R.id.dot_button);
        clear = findViewById(R.id.clear_button);
        undo = findViewById(R.id.undo_button);
        plus = findViewById(R.id.plus_button);
        minus = findViewById(R.id.minus_button);
        multiply = findViewById(R.id.multiply_button);
        divide = findViewById(R.id.divide_button);
        modulus = findViewById(R.id.modulus_button);
        equal = findViewById(R.id.equal_button);

        //Setting Click listener for Number Buttons
        zero.setOnClickListener(new UserInputClickListener("0"));
        one.setOnClickListener(new UserInputClickListener("1"));
        two.setOnClickListener(new UserInputClickListener("2"));
        three.setOnClickListener(new UserInputClickListener("3"));
        four.setOnClickListener(new UserInputClickListener("4"));
        five.setOnClickListener(new UserInputClickListener("5"));
        six.setOnClickListener(new UserInputClickListener("6"));
        seven.setOnClickListener(new UserInputClickListener("7"));
        eight.setOnClickListener(new UserInputClickListener("8"));
        nine.setOnClickListener(new UserInputClickListener("9"));
        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = m_inputView.getText().toString();
                //This check makes sure that decimal is entered once only
                if(!input.contains(".")){
                    m_inputView.setText(input + '.');
                }
            }
        });

        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { undoInput();
            }
        });

        //Setting Click listeners for rest of the buttons
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetCalculator();
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addition();
            }
        });

        minus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                subtraction();
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                multiplication();
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                division();
            }
        });

        modulus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modulo();
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateResults();
            }
        });
    }

    //This function handles input extraction for value1 for different operators
    private void extractValueOne(String input){
        if(input.isEmpty()){
            value1 = value1;
        }
        else if(input.length() == 1 && input.charAt(0) == PLUS){
            value1 = value1;
        }
        else if(input.length() == 1 && input.charAt(0) == MINUS){
            value1 = value1;
        }
        else if(input.length() == 1 && input.charAt(0) == '×'){
            value1 = value1;
        }
        else if(input.length() == 1 && input.charAt(0) == '÷'){
            value1 = value1;
        }
        else if(input.length() == 1 && input.charAt(0) == '%'){
            value1 = value1;
        }
        else{
            value1 = Double.parseDouble(input);
        }
        Log.i("Addition: ", " " + value1);
    }

    //This function handles addition with proper error handling for calculator
    private void addition(){
        String input = m_inputView.getText().toString();
        extractValueOne(input);
        m_inputView.setText("+");
        m_currentSymbol = PLUS;
    }

    //This function handles subtraction with proper error handling for calculator
    private void subtraction(){
        String input = m_inputView.getText().toString();
        extractValueOne(input);
        m_inputView.setText("-");
        m_currentSymbol = MINUS;
        Log.i("Subtraction: ", " " + value1);
    }

    //This Function handles multiplication with proper error handing for calculator
    private void multiplication(){
        String input = m_inputView.getText().toString();
        extractValueOne(input);
        m_inputView.setText("×");
        m_currentSymbol = MULTIPLY;
    }

    //This Function handles division with proper error handing for calculator
    private void division(){
        String input = m_inputView.getText().toString();
        extractValueOne(input);
        m_inputView.setText("÷");
        m_currentSymbol = DIVIDE;
    }

    //This Function handles modulus with proper error handing for calculator
    private void modulo(){
        String input = m_inputView.getText().toString();
        extractValueOne(input);
        m_inputView.setText("%");
        m_currentSymbol = MODULUS;
    }

    //This function will do math
    private double doMath(double value1, double value2){
        switch (m_currentSymbol){
            case '+':      return value1 + value2;
            case '-':     if(value1 == 0.0){ return value2; }
                            return value1 - value2;
            case '*':  return value1 * value2;
            case '/':    return value1 / value2;
            case '%':   return value1 % value2;
            default:
                return value2;
        }
    }

    //This function handles input extraction for value2 for different operators
    private void extractValueTwo(String input){
        if(input.isEmpty()){
            value2 = 0;
        }
        else if(input.length() == 1 && input.charAt(0) == PLUS){
            value2 = value2;
        }
        else if(input.length() == 1 && input.charAt(0) == MINUS){
            value2 = value2;
        }
        else if(input.length() == 1 && input.charAt(0) == '×'){
            value2 = value2;
        }
        else if(input.length() == 1 && input.charAt(0) == '÷'){
            value2 = value2;
        }
        else if(input.length() == 1 && input.charAt(0) == '%'){
            value2 = value2;
        }
        else{
            value2 = Double.parseDouble(input);
        }
    }

    private void calculateResults(){
        String input = m_inputView.getText().toString();
        Log.i("Result: ", "Value1: " + value1 + ", Value2: " + input);
        extractValueTwo(input);
        double result = doMath(value1, value2);
        m_resultView.setText(result + "");
        m_inputView.setText("");
        m_currentSymbol = ' ';
        value1 = 0;
        value2 = 0;
        Log.i("Result: ", "Value1: " + value1 + ", Value2: " + value2);
    }

    //This function undo the last input user inserted
    private void undoInput(){
        String value = m_inputView.getText().toString();
        if(value.isEmpty()){
            return;
        }
        else{
            int lastNumber = value.length() - 1;
            value = value.substring(0, lastNumber);
            m_inputView.setText(value);
        }
    }

    //This function resets the state of calculator
    private void resetCalculator(){
        m_currentSymbol = ' ';
        m_inputView.setText("");
        m_resultView.setText("");
        value1 = 0;
        value2 = 0;
    }

    //This function removes any symbol before inserting a value
    private void removeSymbols(String input){
        if(input.isEmpty()){
            return;
        }
        else if(input.length() == 1 && input.charAt(0) == PLUS){
            m_inputView.setText("");
        }
        else if(input.length() == 1 && input.charAt(0) == MINUS){
            if(value1 == 0){
                m_inputView.setText(input);
            }
            else{
                m_inputView.setText("");
            }
        }
        else if(input.length() == 1 && input.charAt(0) == '×'){
            m_inputView.setText("");
        }
        else if(input.length() == 1 && input.charAt(0) == '÷'){
            m_inputView.setText("");
        }
        else if(input.length() == 1 && input.charAt(0) == '%'){
            m_inputView.setText("");
        }
    }

    class UserInputClickListener implements View.OnClickListener {
        private String m_input;
        public UserInputClickListener(String input){ m_input = input; }
        @Override
        public void onClick(View view) {
            String input = m_inputView.getText().toString();
            removeSymbols(input);
            if(input.length() == 14){
                Toast.makeText(getApplicationContext(), "Cannot enter more than characters numbers", Toast.LENGTH_SHORT).show();
            }else{
                m_inputView.setText(m_inputView.getText().toString() + m_input);
            }
        }
    }
}