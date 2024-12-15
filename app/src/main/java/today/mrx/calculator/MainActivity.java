package today.mrx.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.view.Window;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.button.MaterialButton;
import java.util.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView input,output;
    MaterialButton button_1,button_2,button_3,button_4,button_5,button_6,button_7,button_8,button_9,button_0;
    MaterialButton button_c,button_ac;
    MaterialButton button_division,button_multiply,button_addition,button_subtraction;
    MaterialButton button_Zeros,button_Modulo,button_dot,button_equal;


    String[] operators = {"*", "/", "-", "+","%"};
    String[] dot={"."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        input = findViewById(R.id.input);
        output = findViewById(R.id.output);

        button_0 = findViewById(R.id.button_0);
        button_1 = findViewById(R.id.button_1);
        button_2 = findViewById(R.id.button_2);
        button_3 = findViewById(R.id.button_3);
        button_4 = findViewById(R.id.button_4);
        button_5 = findViewById(R.id.button_5);
        button_6 = findViewById(R.id.button_6);
        button_7 = findViewById(R.id.button_7);
        button_8 = findViewById(R.id.button_8);
        button_9 = findViewById(R.id.button_9);

        button_c = findViewById(R.id.button_c);
        button_ac = findViewById(R.id.button_ac);

        button_division = findViewById(R.id.button_division);
        button_multiply = findViewById(R.id.button_multiply);
        button_addition = findViewById(R.id.button_addition);
        button_subtraction = findViewById(R.id.button_subtraction);

        button_Zeros = findViewById(R.id.button_Zeros);
        button_Modulo = findViewById(R.id.button_modulo);
        button_dot = findViewById(R.id.button_dot);
        button_equal = findViewById(R.id.button_equal);

    }


    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String buttonText = button.getText().toString();
        String inputText = input.getText().toString();

        if(v.getId() == R.id.button_equal){
            answer(inputText);
        }

        if (v.getId() == R.id.button_ac) {   // This is used to clear Whole inputs.
            allClear();
        }else if(v.getId() == R.id.button_c) { // This is used to clear the last character only.
            input.setText(Clear(inputText));
        }else if( v.getId() == R.id.button_1 || v.getId() == R.id.button_2 || v.getId() == R.id.button_3 || v.getId() == R.id.button_4 || v.getId() == R.id.button_5 || v.getId() == R.id.button_6 || v.getId() == R.id.button_7 || v.getId() == R.id.button_8 || v.getId() == R.id.button_9){
            input.setText(inputText.equals("0") ? "" : inputText);
            inputText=input.getText().toString();
            input.setText(inputText.concat(buttonText));
        }else if (v.getId() == R.id.button_division || v.getId() == R.id.button_multiply || v.getId() == R.id.button_addition || v.getId() == R.id.button_subtraction || v.getId() == R.id.button_modulo){
            if(inputText.isEmpty() || lastIndex(inputText,dot)) {
                input.setText(inputText.concat("0").concat(buttonText));
            } else if (v.getId() == R.id.button_multiply || v.getId() == R.id.button_division || v.getId() == R.id.button_modulo) {
                if(lastIndex(inputText,operators)){
                    inputText = inputText.substring(0,inputText.length()-1);
                }
                if(lastIndex(inputText,operators)){
                    inputText = inputText.substring(0,inputText.length()-1);
                }
                input.setText(inputText.concat(buttonText));
            } else if (v.getId() == R.id.button_addition) {
                if (lastIndex(inputText,new String[]{"*","/","+"})){
                    input.setText(inputText.substring(0,inputText.length()-1).concat(buttonText));
                } else if (lastIndex(inputText,new String[]{"-"})) {
                    if(lastIndex(inputText.substring(0,inputText.length()-1),new String[]{"%"})){
                        input.setText(inputText.substring(0,inputText.length()-1).concat(buttonText));
                    } else if (lastIndex(inputText.substring(0,inputText.length()-1),new String[]{"*","/"})) {
                        input.setText(inputText.substring(0,inputText.length()-2).concat(buttonText));
                    }else {
                        input.setText(inputText.substring(0,inputText.length()-1).concat(buttonText));
                    }
                }else{
                    input.setText(inputText.concat(buttonText));
                }
            } else if (v.getId() == R.id.button_subtraction) {
                if(lastIndex(inputText,new String[]{"+","-"})){
                    input.setText(inputText.substring(0,inputText.length()-1).concat(buttonText));
                }else {
                    input.setText(inputText.concat(buttonText));
                }
            }else {
                input.setText(inputText.concat(buttonText));
            }
        }else if(v.getId() == R.id.button_0) {
            if(lastIndex(inputText,operators) || lastIndex(inputText,dot )|| inputText.isEmpty()){
                input.setText(inputText.concat(buttonText));
                return;
            }
            String[] temp = inputText.split("[*/%+-]");

            if ((!temp[temp.length-1].contains(".")) && Double.parseDouble(temp[temp.length-1])==0 ) {
                return;
            }else{
                input.setText(inputText.concat(buttonText));
            }
        }else if(v.getId() == R.id.button_Zeros){
            if ((inputText.isEmpty() || lastIndex(inputText,operators)) && v.getId() == R.id.button_Zeros) {
                return;
            }
            String[] temp = inputText.split("[%*/+-]");

            if ((!temp[temp.length-1].contains(".")) && Double.parseDouble(inputText) == 0) {
                return;
            } else {
                input.setText(inputText.concat(buttonText));
            }
        }else if(v.getId() == R.id.button_dot) {
            if (inputText.isEmpty() || lastIndex(inputText, operators)) {
                input.setText(inputText.concat("0").concat(buttonText));
                return;
            }
            String[] temp = inputText.split("[%*/+-]");
            if (temp[temp.length-1].contains(".")) {
                return;
            }else {
                input.setText(inputText.concat(buttonText));
            }
        }else{
            return;
        }
    }
    private double calDouble(String input1, String operator, String input2){
        double result=0;
        double val1 =Double.parseDouble(input1);
        double val2 =Double.parseDouble(input2);

        switch (operator){
            case "*":
                result =val1*val2;
                break;
            case "/":
                result =val1/val2;
                break;
            case "%":
                result = val1 % val2;
                break;
            case "+":
                result =val1+val2;
                break;
            case "-":
                result =val1-val2;
                break;
        }
        return result;
    }
    public void answer(String expression) {
        if((!expression.isEmpty()) && (lastIndex(expression,this.operators) || lastIndex(expression,this.dot))){
            expression=expression.substring(0,expression.length()-1);
        }
        if((!expression.isEmpty()) && lastIndex(expression,this.operators)){
            expression=expression.substring(0,expression.length()-1);
        }
        String regex = "(?<=[-+*/%])|(?=[-+*/%])";

        List<String> tokens = new ArrayList<>(Arrays.asList(expression.split(regex)));
        for(int i=1;i<tokens.size()-1;i++){
            if(tokens.get(i).equals("-") && "*/".contains(tokens.get(i-1))){
                tokens.set(i+1,"-".concat(tokens.get(i+1)));
                tokens.remove(i);
            }else if (tokens.get(i).equals("%") && "+-".contains(tokens.get(i+1))) {
                tokens.set(i-1,Double.toString(Double.parseDouble(tokens.get(i-1))/100));
                tokens.remove(i);
            }
        }

        for(int i=1;i<tokens.size()-1;i+=2) {
            if("/".contains(tokens.get(i)) && tokens.get(i+1).equals("0")){
                tokens.clear();
                tokens.add("can't divisible by 0");
                break;
            }
            if("*/%".contains(tokens.get(i))){
                    String tr = Double.toString(calDouble(tokens.get(i-1), tokens.get(i),tokens.get(i+1)));
                    tokens.set(i+1,tr);
                    tokens.remove(i);
                    tokens.remove(i-1);
                    i-=2;
            }
        }
//
        for(int i=1;i<tokens.size()-1;i+=2){
            System.out.println(tokens);
            String tr = Double.toString(calDouble(tokens.get(i-1), tokens.get(i),tokens.get(i+1)));
            tokens.set(i+1,tr);
            tokens.remove(i);
            tokens.remove(i-1);
            i-=2;
        }


        String token = tokens.get(0);
        try {
            if (token == null || token.isEmpty()) {
                output.setText("Invalid input");
                return;
            }
            double doubleValue = Double.parseDouble(token);
            int intValue = (int) doubleValue;
            output.setText((doubleValue == intValue) ? String.valueOf(intValue) : String.valueOf(doubleValue));
        }catch (Exception e){
            output.setText(tokens.get(0));
        }
    }
    public String Clear(String inputText){
        if(!inputText.isEmpty())
            inputText = inputText.substring(0,inputText.length()-1);
        return inputText;}
    public void allClear() {
        input.setText("");
        output.setText("");
    }
    public boolean lastIndex(String str, String[] operators){
        if(!str.isEmpty()) {
            String last_index = String.valueOf(str.charAt(str.length() - 1));
            return Arrays.asList(operators).contains(last_index);
        }else{
            return false;
        }
    }
}