package com.PyMESoft.Facturas;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import com.PyMESoft.Facturas.Activities.homeinvoice2;
import com.PyMESoft.Facturas.Adapadores.Customtoolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.text.Html;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Locale;

public class SelectLanguaje extends AppCompatActivity {
    RadioButton Englishbutton, Españolbutton;
    RadioGroup Idiomagroup;
    private Customtoolbar customtoolbar;
    Toolbar customtol;
    androidx.appcompat.widget.SearchView toolbar_searchview;
    CardView toolbar_cardview;
    TextView toolbar_tittle;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LoadLocale(Constants.getSP(getApplicationContext()).getLANG());
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_languaje);

        Englishbutton = findViewById(R.id.englishbutton);
        Españolbutton = findViewById(R.id.españolbutton);
        Idiomagroup = findViewById(R.id.idiomagroup);
        customtoolbar= findViewById(R.id.custom_toolbar);
//        toolbar_cardview= findViewById(R.id.toolbar_cardview);
        toolbar_tittle= findViewById(R.id.toolbar_tittle);
        customtol= findViewById(R.id.toolbar_relativelay);
        toolbar_searchview= findViewById(R.id.toolbar_searchview);


        customtol.setNavigationIcon(getResources().getDrawable(R.drawable.backarrow));

        setSupportActionBar(customtol);
        customtol.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String first = "<font color='#03bfa5'>PyMESoft</font>" ;
        String secondname="<font color='#ffffff'>Facturas</font>";
        String next = getResources().getString(R.string.app_name2);
        //toolbar_searchview.setIconified(false);

        toolbar_searchview.setVisibility(View.GONE);
        customtoolbar.setTittle(Html.fromHtml(first+secondname));


        Idiomagroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.englishbutton:
                      SetLanguage("en");
                        Constants.getSP(getApplicationContext()).setLANG("en");
              startActivity(new Intent(getApplicationContext(), homeinvoice2.class));
//                        Intent intent = new Intent(getIntent());
//                        finish();
//                        startActivity(intent);
                        break;

                    case R.id.españolbutton:
                     SetLanguage("esp");
                        Constants.getSP(getApplicationContext()).setLANG("esp");


                       startActivity(new Intent(getApplicationContext(), homeinvoice2.class));
//                        Intent intent2 = new Intent(getIntent());
//                        finish();
//                        startActivity(intent2);


                        break;
                }

            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void SetLanguage(String Language) {
        Locale locale = new Locale(Language);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();

        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());

        Constants.getSP(getApplicationContext()).setLANG(Language);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void LoadLocale(String lang) {
        SetLanguage(Constants.getSP(getApplicationContext()).getLANG());

    }
}
