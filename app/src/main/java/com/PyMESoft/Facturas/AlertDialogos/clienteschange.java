package com.PyMESoft.Facturas.AlertDialogos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.PyMESoft.Facturas.Adapadores.ClienteAdapter;
import com.PyMESoft.Facturas.ClickInterface1;
import com.PyMESoft.Facturas.R;
import com.PyMESoft.Facturas.ViewModel.SharedViewModel;
import com.PyMESoft.Facturas.constructornom2;

import java.util.ArrayList;

public class clienteschange {
    View view2;
    android.app.AlertDialog.Builder builder1;
    ArrayList<constructornom2> ListaClientes;
    Activity activty;

    private ClienteAdapter myadaptador3;

    public AlertDialog ClienteChange(Context context, View v, SharedViewModel sharedViewModel, ClickInterface1 clickInterface1) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context, R.style.Theme_MaterialComponents_Dialog_Alert);
        setBuilder(builder);
        final View view = LayoutInflater.from(context).inflate(R.layout.changeclient, (ConstraintLayout) v.findViewById(R.id.clienteChange_layout));
        builder.setView(view);
        setview(view);
        ListaClientes= new ArrayList<>();
        final android.app.AlertDialog alertDialog = builder.create();
        RecyclerView recycler_clientChange = v.findViewById(R.id.clientchange_recycler);
        recycler_clientChange.setLayoutManager(new LinearLayoutManager(recycler_clientChange.getContext()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recycler_clientChange.getContext());
        // linearLayoutManager.setReverseLayout(true);
        //linearLayoutManager.setStackFromEnd(true);
        recycler_clientChange.setLayoutManager(linearLayoutManager);




        myadaptador3 = new ClienteAdapter(ListaClientes,clickInterface1);
        recycler_clientChange.setAdapter(myadaptador3);

        if(alertDialog.getWindow() !=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

        return alertDialog;
    }



    public View getiew() {
        return view2;
    }

    public void setview(View view2) {
        this.view2 = view2;
    }

    public android.app.AlertDialog.Builder getBuilder() {
        return builder1;
    }

    public void setBuilder(android.app.AlertDialog.Builder builder1) {
        this.builder1 = builder1;
    }

}
