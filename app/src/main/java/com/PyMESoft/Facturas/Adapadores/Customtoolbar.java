package com.PyMESoft.Facturas.Adapadores;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.text.Spanned;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import android.widget.TextView;

import androidx.appcompat.widget.SearchView;

import com.PyMESoft.Facturas.R;

public class Customtoolbar extends LinearLayout {

    private ImageButton BackButton;
    private TextView tittleTextView;
    private
    SearchView searchView;

    public Customtoolbar(Context context, AttributeSet attrs) {
        super(context,attrs);
        initialize(context);
    }
    public Customtoolbar (Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        initialize(context);

    }

    private void initialize(Context context) {
        try {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate (R.layout.custom_toolbar_layout, this, true);
//            BackButton = view.findViewById(R.id.toolbar_back_button);
            tittleTextView = view.findViewById(R.id.toolbar_tittle);
            searchView= view.findViewById(R.id.toolbar_searchview);
        } catch (Exception e) {
            Log.e(TAG, "onCreateView", e);
            throw e;
        }



    }

    public void setBackButtonClickListener(OnClickListener listener){
        BackButton.setOnClickListener(listener);
    }
    public void setTittle(Spanned title){
        tittleTextView.setText(title);
    }
    public void setSearchViewOnclickListener(OnClickListener listener){




    }
    public void setvisibility(int value){
        tittleTextView.setVisibility(value);

    }



}
