package com.example.calculator;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class scientific_fragment  extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.scientific_fragment, container, false);

        ArrayList<View> buttons = new ArrayList<View>();
        buttons.addAll(view.findViewById(R.id.ll1).getTouchables());
        buttons.addAll(view.findViewById(R.id.ll2).getTouchables());
        buttons.addAll(view.findViewById(R.id.ll3).getTouchables());

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

        if (buttonText.equals("sin" )  || buttonText.equals("cos") || buttonText.equals("tan") ||
                buttonText.equals("ln") || buttonText.equals("log10") || buttonText.equals("√"))
        {
            trigonomitricalClick(view);
        }
        else if (buttonText.equals("pi") || buttonText.equals("e"))
        {
            constClick(view);
        }
        else if (buttonText.equals("!"))
        {
            factorialClick(view);
        }
        else if (buttonText.equals("^"))
        {
            operationCLick(view);
        }
        else
        {
            bracketClick(view);
        }
    }

    public void trigonomitricalClick(View view){
        Button button = (Button)view;
        String get = "";
        get += button.getText();
        String str = InterfaceActivity.getStr();
        if (str.length() == 0 || (!checkIsNum(str) && str.charAt(str.length()-1) != '.')){
            if (get.equals("√"))
                get = "sqrt";
            str += get + "(";
            InterfaceActivity.getRes().setText(str);
            InterfaceActivity.setStr(str);
        }

        if (str.length() != 0 && checkIsNum(str))
        {
            str += "*" + get + "(";
            InterfaceActivity.getRes().setText(str);
            InterfaceActivity.setStr(str);
        }
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

    public void constClick(View view){
        Button button = (Button)view;
        String str = InterfaceActivity.getStr();

        if (str.length() !=0 && (checkIsNum(str) || str.charAt(str.length() - 1) == ')'))
        {
            str += "*";
            str += button.getText();
            InterfaceActivity.getRes().setText(str);
            InterfaceActivity.setStr(str);
        }
        else if (str.length() == 0 || (!checkIsNum(str) && str.charAt(str.length()-1) != '.'))
        {
            str += button.getText();
            InterfaceActivity.getRes().setText(str);
            InterfaceActivity.setStr(str);
        }
    }

    public void factorialClick(View view){
        Button button = (Button)view;
        String str = InterfaceActivity.getStr();
        if (str.length() > 0 && checkIsNum(str)){
            str += button.getText();
            InterfaceActivity.getRes().setText(str);
            InterfaceActivity.setStr(str);
        }
    }

    public void bracketClick(View view){
        Button button = (Button)view;
        String get = "";
        get += button.getText();
        String str = InterfaceActivity.getStr();

        if (str.length() == 0 || str.charAt(str.length()-1) != '.') {
            if (get.equals("(") && str.length() > 0 && (str.charAt(str.length()-1) == 'i' ||
                    str.charAt(str.length()-1) == ')' ||
                    checkIsNum("" + str.charAt(str.length()-1)) ||
                    str.charAt(str.length()-1) == 'e'))
            {
                str += "*";
                str += get;
                InterfaceActivity.getRes().setText(str);
                InterfaceActivity.setStr(str);
            }
            else
            {
                str += get;
                InterfaceActivity.getRes().setText(str);
                InterfaceActivity.setStr(str);
            }
        }
    }

    public boolean checkIsNum(String string){
        return (string.charAt(string.length()-1) >= '0' && string.charAt(string.length()-1) <= '9');
    }

    private ActivityInterface InterfaceActivity;

    @Override
    public void onAttach(@NonNull Activity activity)
    {
        super.onAttach(activity);
        try
        {
            InterfaceActivity = (ActivityInterface)activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + "must implement ActivityInterface.");
        }
    }
}
