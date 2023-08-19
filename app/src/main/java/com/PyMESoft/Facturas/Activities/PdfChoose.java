package com.PyMESoft.Facturas.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.PyMESoft.Facturas.Adapadores.Customtoolbar;
import com.PyMESoft.Facturas.Adapadores.pdfadaptador;
import com.PyMESoft.Facturas.Constants;
import com.PyMESoft.Facturas.Constructores.PdfChooseContructor;
import com.PyMESoft.Facturas.R;

import java.util.ArrayList;

public class PdfChoose extends AppCompatActivity {
    RecyclerView Recpdf;
    RecyclerView.Adapter pdfAdapter;
    pdfadaptador pdfadaptador;
    PdfChooseContructor pdfChooseContructor;
    ArrayList<PdfChooseContructor> myImageList;
    pdfadaptador pdfadap;
    ArrayList<String>titulos;
    ArrayList<Integer>myImageList2;
    RecyclerView.LayoutManager Lmanager;
    pdfadaptador.Viewholder viewholder;
    private Customtoolbar customtoolbar;
    Toolbar customtol;
    androidx.appcompat.widget.SearchView toolbar_searchview;
    CardView toolbar_cardview;
    TextView toolbar_tittle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_choose);
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


        Recpdf = findViewById(R.id.Recpdf);

//        pdfChooseContructor=new PdfChooseContructor();
//
//        pdfChooseContructor.setImage(R.drawable.pdfstructured);
//        pdfChooseContructor.setImage(R.drawable.simplepdfformat);
//        pdfChooseContructor.setTittle("PDFSTRUCTURED");
//        pdfChooseContructor.setTittle("");
        String pdfopcion = Constants.getSP(PdfChoose.this).getPDFPREFERENCE();

        myImageList = new ArrayList<>();
        myImageList.add(new PdfChooseContructor(R.drawable.structuredjpg, "Classic Structured"));
        myImageList.add(new PdfChooseContructor(R.drawable.ligerojpg, "Classic Ligero"));

        myImageList.add(new PdfChooseContructor(R.drawable.solarisjpg,"Solaris"));

//        pdfChooseContructor.setImage(R.drawable.pdfstructured);


        Recpdf.setHasFixedSize(true);

        Lmanager = new GridLayoutManager(this, 2);

        pdfAdapter = new pdfadaptador(myImageList);
        Recpdf.setLayoutManager(Lmanager);
        Recpdf.setAdapter(pdfAdapter);

    }



// later...
//        myImageView.setImageResource(myImageList.get(i));
    }
