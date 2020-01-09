package com.example.calculator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.mariuszgromada.math.mxparser.Expression;

import java.util.ArrayList;

public class basic_fragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.basic_fragment, container, false);

        ArrayList<View> buttons = new ArrayList<View>();
        buttons.addAll(view.findViewById(R.id.l1).getTouchables());
        buttons.addAll(view.findViewById(R.id.l2).getTouchables());
        buttons.addAll(view.findViewById(R.id.l3).getTouchables());
        buttons.addAll(view.findViewById(R.id.l4).getTouchables());
        buttons.addAll(view.findViewById(R.id.l5).getTouchables());

        for (View b : buttons)
        {
            b.setOnClickListener(this);
        }
        return view;
    }

    @Override
    public void onClick(View view)
    {
        String buttonText = ((Button)view).getText().toString();
        if (buttonText.equals("*") || buttonText.equals("/") || buttonText.equals("-") ||
                buttonText.equals("+") || buttonText.equals(",")){
            operationCLick(view);
        }
        else if (buttonText.equals("Clear")){
            onClear(view);
        }
        else if (buttonText.equals("Backspace"))
        {
            onBackspaceClick(view);
        }
        else if (buttonText.equals("=")){
            resultClick(view);
        }
        else {
            digitClick(view);
        }

    }

    public void digitClick(View view){
        Button button = (Button)view;
        String str = InterfaceActivity.getStr();
        if (str.length() > 0 && (str.charAt(str.length()-1) == ')' ||
                str.charAt(str.length()-1) == '!' ||
                str.charAt(str.length()-1) == 'e' ||
                str.charAt(str.length()-1) == 'i'))
        {
            str += "*";
            InterfaceActivity.setStr(str);
            InterfaceActivity.getRes().setText(str);
        }
        str += button.getText().toString();
        InterfaceActivity.setStr(str);
        InterfaceActivity.getRes().setText(str);
    }

    public void operationCLick(View view){
        Button button = (Button)view;
        String get = "";
        get += button.getText();
        String str = InterfaceActivity.getStr();
        if (get.equals(",") && InterfaceActivity.getcanFloat() && str.length() > 0 && checkIsNum(str)){
            InterfaceActivity.setcanFloat(false);
            str += ".";
        }
        else if (get.equals("-") && str.length() == 0)
        {
            str += button.getText();
            InterfaceActivity.setcanFloat(true);
        }
        else if (!get.equals(",") && str.length() > 0 && (checkIsNum(str) ||
                str.charAt(str.length()-1) == '!' ||
                str.charAt(str.length()-1) == ')' ||
                str.charAt(str.length()-1) == 'e' ||
                str.charAt(str.length()-1) == 'i')){
            str += button.getText();
            InterfaceActivity.setcanFloat(true);
        }
        InterfaceActivity.getRes().setText(str);
        InterfaceActivity.setStr(str);
    }

    public void resultClick(View view){
        String str = InterfaceActivity.getStr();
        Expression expression = new Expression(str);
        String result = String.valueOf(expression.calculate());

        if (result.charAt(result.length()-2)=='.' && result.charAt(result.length()-1)=='0')
        {
            result = result.substring(0, result.length()-2);
        }
        if (result.contains("."))
        {
            str = result;
            InterfaceActivity.setcanFloat(false);
        }
        else
        {
            str = result;
            InterfaceActivity.setcanFloat(true);
        }

        if (result.equals("NaN"))
        {
            result = "";
            str = result;
            InterfaceActivity.setcanFloat(true);
            InterfaceActivity.InvalidExpression();
        }

        InterfaceActivity.getRes().setText(str);
        InterfaceActivity.setStr(str);
    }

    public void onClear(View view){
        InterfaceActivity.setStr("");
        InterfaceActivity.setcanFloat(true);
        InterfaceActivity.getRes().setText("");
    }

    public boolean checkIsNum(String string){
        return (string.charAt(string.length()-1) >= '0' && string.charAt(string.length()-1) <= '9');
    }


    public void onBackspaceClick(View view)
    {
        if (InterfaceActivity.getStr().length() > 0)
        {
            InterfaceActivity.setStr(InterfaceActivity.getStr().substring(0, InterfaceActivity.getStr().length()-1));
            InterfaceActivity.getRes().setText(InterfaceActivity.getStr());
        }
    }

    private ActivityInterface InterfaceActivity;
}