package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv,mathTv;
    MaterialButton buttonC,buttonOpen,buttonClose;
    MaterialButton buttonSlash,buttonUmn,buttonPlus,buttonMinus,buttonRavn;
    MaterialButton buttonZero,buttonOne,buttonTwo,buttonThree,buttonFour,buttonFive,buttonSix,buttonSeven,buttonEight,buttonNine;
    MaterialButton buttonAC,buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        mathTv = findViewById(R.id.math_tv);

        assignId(buttonC,R.id.button_c);
        assignId(buttonOpen,R.id.button_open);
        assignId(buttonClose,R.id.button_close);
        assignId(buttonSlash,R.id.button_slash);
        assignId(buttonUmn,R.id.button_umn);
        assignId(buttonPlus,R.id.button_plus);
        assignId(buttonMinus,R.id.button_minus);
        assignId(buttonRavn,R.id.button_ravn);
        assignId(buttonZero,R.id.button_zero);
        assignId(buttonOne,R.id.button_one);
        assignId(buttonTwo,R.id.button_two);
        assignId(buttonThree,R.id.button_three);
        assignId(buttonFour,R.id.button_four);
        assignId(buttonFive,R.id.button_five);
        assignId(buttonSix,R.id.button_six);
        assignId(buttonSeven,R.id.button_sev);
        assignId(buttonEight,R.id.button_eigh);
        assignId(buttonNine,R.id.button_nine);
        assignId(buttonAC,R.id.button_ac);
        assignId(buttonDot,R.id.button_dot);



    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = mathTv.getText().toString();

        if(buttonText.equals("AC")){
            mathTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            mathTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        }else {
            dataToCalculate = dataToCalculate+buttonText;
        }

        mathTv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Error")){
            resultTv.setText(finalResult);
        }

    }

    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Error";
        }
    }
}