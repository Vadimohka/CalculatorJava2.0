package com.example.calculator;

import android.widget.TextView;

public interface ActivityInterface {
    String getStr();
    void setStr(String a);
    TextView getRes();
    boolean getcanFloat();
    void setcanFloat(boolean a);
    void InvalidExpression();
}
