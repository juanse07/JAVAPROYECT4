package com.PyMESoft.Facturas.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.PyMESoft.Facturas.Adapadores.BuscarAdaptador;
import com.PyMESoft.Facturas.Adapadores.Customtoolbar;
import com.PyMESoft.Facturas.Adapadores.NoteHomisAdapter;
import com.PyMESoft.Facturas.Adapadores.homeinvoiceadapterclass;
import com.PyMESoft.Facturas.ClickInterface1;
import com.PyMESoft.Facturas.Constants;
import com.PyMESoft.Facturas.Constructores.NoteHomis;
import com.PyMESoft.Facturas.Constructores.NoteProducto;
import com.PyMESoft.Facturas.Constructores.constcards;
import com.PyMESoft.Facturas.Crearproducto;
import com.PyMESoft.Facturas.HomeNote;
import com.PyMESoft.Facturas.InclientActivity;
import com.PyMESoft.Facturas.NoteProdViewModel;
import com.PyMESoft.Facturas.R;
import com.PyMESoft.Facturas.SelectLanguaje;
import com.PyMESoft.Facturas.ViewModel.NoteHomisViewModel;
import com.PyMESoft.Facturas.ViewModel.SharedViewModel;
import com.PyMESoft.Facturas.reminderAc;
import com.PyMESoft.Facturas.settingsAcivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class homeinvoice2 extends AppCompatActivity implements ClickInterface1 {

    RecyclerView recyclerview1;
    private Customtoolbar customtoolbar;
    DatabaseReference myDatabasehome, ref;
    SwipeRefreshLayout swipehome;
    BottomNavigationView navigationView;
    ImageView reminder_1, IMAGEHOME,toolbar_imageview;
    CardView card_opciones, toolbar_cardview;
    FirebaseAuth mAuth;
    SearchView edtbuscar;
    NavigationView navdrawer;
    CardView searchbarhelper;

    String Firekey;
    Toolbar toolbar;

    RecyclerView RecyclerBusqueda;
    RecyclerView.Adapter madapter;
    CharSequence tipodoc, tipodoc2;
    NoteHomisViewModel noteHomisViewModel;
    SelectLanguaje selectLanguaje;
    String Lang;
    ArrayList<NoteHomis>noteHomis1;
    Context context;




    int contarventas;
    HomeNote homeNote = new HomeNote();
    List<HomeNote> listahomenotes = new ArrayList<>();
    List<NoteProducto> ListaProd1;
    ArrayList<String> ListaProductos;
    SearchView toolbar_searchview;

    RecyclerView.LayoutManager Lmanager;
    String Start, EndDate;
    NoteProdViewModel noteProdViewModel;
    TextView title2,toolbar_title;
    AppBarLayout appbar1;
    ArrayList<CalendarContract.Events> eventsLists, filterevents;
    DrawerLayout drawer1;
    ActionBarDrawerToggle mdrawer;
    LinearLayout gridmenu;
     SharedViewModel sharedViewModel;
     homeinvoiceadapterclass myadaptador2;
     NoteHomisAdapter myadaptador3;
    ArrayList<String> Busquedas;
    ArrayList<constcards> Listahomesql;
    FirebaseRecyclerAdapter firebaseRecyclerAdapter, firebaseRecyclerAdapter2;

    ArrayList<String> listaproductos;
    ArrayList<String> getListaNombres;
    View cartas1;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        Lang= Constants.getSP(getApplicationContext()).getLANG();
        SetLanguage(Lang);
        setContentView(R.layout.activity_home1);


        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        mAuth = FirebaseAuth.getInstance();
//        String id = mAuth.getCurrentUser().getUid();
        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        customtoolbar= findViewById(R.id.custom_toolbar);
        toolbar_title= findViewById(R.id.toolbar_tittle);
        toolbar= findViewById(R.id.toolbar_relativelay);
        toolbar_searchview =findViewById(R.id.toolbar_searchview);
//        toolbar_imageview= findViewById(R.id.toolbar_back_button);
//        toolbar_cardview = customtoolbar.findViewById(R.id.toolbar_cardview);
        searchbarhelper = findViewById(R.id.searchbarhelper);

        drawer1 = findViewById(R.id.drawer1);

        swipehome = findViewById(R.id.swipehome);
        appbar1 = findViewById(R.id.appbar1);


        navdrawer = findViewById(R.id.navdrawer);

        RecyclerBusqueda = findViewById(R.id.RecyclerBusqueda);


        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.backarrow));
//        toolbar_cardview.setVisibility(View.GONE);
        searchbarhelper.setVisibility(View.GONE);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setTitle("pyme");
//        PreferenceManager.setDefaultValues(this,R.xml.preference,false);
        setContext1(getApplicationContext());
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            drawer1.openDrawer(Gravity.LEFT);

            Menu menu = navdrawer.getMenu();
            MenuItem item = menu.findItem(R.id.pymename);

//            SpannableString s = new SpannableString(menu.findItem(R.id.pymename).getTitle().toString());
//            s.setSpan(new TextAppearanceSpan(homeinvoice2.this, R.style.titledrawer), 0, s.length(), 0);
            String first = "<font color='#03bfa5'>PyMESoft</font>" ;
            String secondname="<font color='#ffffff'>Facturas</font>";
            item.setTitle(Html.fromHtml(first+secondname));
//            item.setTitle(s);
            //new ForegroundColorSpan(getResources().getColor(R.color.colorAccent))


            // open drawer here
            if (drawer1.isDrawerOpen(GravityCompat.START)) {
                drawer1.closeDrawer(GravityCompat.START);
            } else {
                drawer1.openDrawer(GravityCompat.START);
            }
            Log.d("HOLAA","SI FUNCIONA" );
        }
    });

        ImageView searchIcon = (ImageView)toolbar_searchview.findViewById(androidx.appcompat.R.id.search_button);
        ImageView searchIconClose = (ImageView)toolbar_searchview.findViewById(androidx.appcompat.R.id.search_close_btn);


        searchIconClose.setColorFilter(getResources().getColor(R.color.mdtp_white),
                android.graphics.PorterDuff.Mode.SRC_IN);

        searchIcon.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.magnifyingglass));


        SearchView.SearchAutoComplete searchAutoComplete =   (SearchView.SearchAutoComplete)toolbar_searchview.findViewById(androidx.appcompat.R.id.search_src_text);
        searchAutoComplete.setHintTextColor(getResources().getColor(R.color.mdtp_white));
        searchAutoComplete.setTextColor(getResources().getColor(R.color.mdtp_white));

        String first = "<font color='#03bfa5'>PyMESoft</font>" ;
        String secondname="<font color='#ffffff'>Facturas</font>";
        customtoolbar.setTittle(Html.fromHtml(first+secondname));
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        sharedViewModel.init();
        sharedViewModel.getSearchTipo().observe(this, new Observer<CharSequence>() {
            @Override
            public void onChanged(final CharSequence charSequence) {
           tipodoc2= charSequence.toString();
           toolbar_searchview.setQuery(tipodoc2,false);


            }
        });

        toolbar_searchview.setOnSearchClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                searchbarhelper.setVisibility(View.VISIBLE);
                toolbar_title.setVisibility(View.INVISIBLE);
                if(toolbar_searchview!=null) {
                    toolbar_searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {

                            query=tipodoc2.toString();
                            myadaptador2.getFilter().filter(query);
//                            myadaptador2.notifyDataSetChanged();




                            return true;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {

                            myadaptador2.getFilter().filter(newText);
//                            myadaptador2.notifyDataSetChanged();




                            return false;
                        }
                    });
                }

            }
        });




        toolbar_searchview.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                toolbar_title.setVisibility(View.VISIBLE);
                searchbarhelper.setVisibility(View.GONE);
                return false;
            }
        });

        mdrawer = new ActionBarDrawerToggle(this, drawer1, R.string.Open, R.string.Close);
        drawer1.addDrawerListener(mdrawer);
        mdrawer.syncState();






        File imgFile = new File("/storage/emulated/0/PyMESoft/Logotipo/logopng");
        if (imgFile.exists()) {

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

//            toolbar_imageview.setImageBitmap(myBitmap);
        }

        myDatabasehome = FirebaseDatabase.getInstance().getReference().child("VENTAS");


        Busquedas = new ArrayList<>();
        String Invoice=getResources().getString(R.string.Sales);
        String Receipts=getResources().getString(R.string.Receipts);
        String Draft=getResources().getString(R.string.Draft);
        Busquedas.add(Invoice);
        Busquedas.add(Receipts);
        Busquedas.add(Draft);



        RecyclerBusqueda.setHasFixedSize(true);

        Lmanager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        madapter = new BuscarAdaptador(Busquedas, this);
        RecyclerBusqueda.setLayoutManager(Lmanager);
        RecyclerBusqueda.setAdapter(madapter);
        sharedViewModel = new ViewModelProvider(homeinvoice2.this).get(SharedViewModel.class);


        swipehome.setColorSchemeResources(R.color.colorAccent);
        swipehome.hasNestedScrollingParent();

        swipehome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                toolbar_searchview.setQuery("", false);


                sharedViewModel = new ViewModelProvider(homeinvoice2.this).get(SharedViewModel.class);


               sharedViewModel.init2();

                sharedViewModel.getdato().observe(homeinvoice2.this, new Observer<ArrayList<constcards>>() {
                    @Override
                    public void onChanged(ArrayList<constcards> Constcard) {
                        myadaptador2.notifyDataSetChanged();


                    }
                });
               myadaptador2 = new homeinvoiceadapterclass(sharedViewModel.getdato().getValue());




                recyclerview1.setAdapter(myadaptador2);

                swipehome.setRefreshing(false);
            }
        });


        //myDatabasehome.keepSynced(true);


        recyclerview1 = (RecyclerView) findViewById(R.id.recyclerview1);
        //recyclerview1.setHasFixedSize(true);
        recyclerview1.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerview1.setLayoutManager(linearLayoutManager);
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
       sharedViewModel.init2();



        sharedViewModel.getdato().observe(this, new Observer<ArrayList<constcards>>() {
            @Override
            public void onChanged(ArrayList<constcards> Constcard) {
                myadaptador2.notifyDataSetChanged();}
        });
//
  noteHomisViewModel = new ViewModelProvider(this).get(NoteHomisViewModel.class);
//            }
//        });
       myadaptador2 = new homeinvoiceadapterclass(sharedViewModel.getdato().getValue());






        recyclerview1.setAdapter(myadaptador2);





        navigationView.setSelectedItemId(R.id.home);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
//                    case R.id.listaclientes2:
//
//                        startActivity(new Intent(getApplicationContext(), ingresodat.class));
//                        overridePendingTransition(0, 0);
//                        return true;
                    case R.id.action_home:
                        startActivity(new Intent(getApplicationContext(), homeinvoice2.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.action_more:
                        startActivity(new Intent(getApplicationContext(), InclientActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.action_add:
                        startActivity(new Intent(getApplicationContext(), Crearproducto.class));
                        overridePendingTransition(0, 0);
                        return true;
                    // case R.id.action_Pdf:
                    //   startActivity(new Intent(getApplicationContext(), pdfviewer.class));
                    // overridePendingTransition(0, 0);
                    //return true;
                }
                return false;
            }
        });

        navdrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.logout1:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(), loginactivity.class));
                        return true;
                    case  R.id.confempresa:
                        startActivity(new Intent(getApplicationContext(), settingsAcivity.class));
                        return true;


                }
                return false;
            }
        });


    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onButtonAddClick(int position) {

    }

    @Override
    public void onButtonclienteClick(int position) {

    }

    @Override
    public void passingproductoClick(int position, CharSequence Producto, CharSequence Precio, CharSequence Cantidad) {

    }


    @Override
    public void passingprecio1Click(int position, CharSequence Precio) {

    }

    @Override
    public void passingcliente2Click(int position, CharSequence Cliente, String phone, String Email, String Address, String City) {

    }


    @Override
    public void passfirebasekey(String key) {
        Firekey=key;

    }

    @Override
    public void preferenciasacalculadora() {

    }

    @Override
    public void Preferenciasingreo(int estado, String dias, String Tipo) {

    }

    @Override
    public void passingpositionk(int position) {

    }

    @Override
    public void PassTipoDoc(int position, CharSequence tipoDoc) {
        // tipodoc2=tipoDoc.toString();
        //FilterData();

        sharedViewModel = new ViewModelProvider(homeinvoice2.this).get(SharedViewModel.class);
        Log.d("value of the state",String.valueOf(tipoDoc));
        tipodoc2= tipoDoc;

       sharedViewModel.setSearchTipo(tipoDoc);

    }

    @Override
    public void PassnoteprodPosition(int position, String Producto, String Cantidad, String Precio, NoteProducto currentnote) {

    }

    @Override
    public void onsearchQuery(String query) {

    }


    public void iniciarhome(View view) {


        Intent i = new Intent(this, fragments3.class);
        startActivity(i);
    }

    public void reminderpage(View view) {
        Intent i = new Intent(this, reminderAc.class);
        startActivity(i);
    }


//    public void downloadpdf(Context context, String file, String fileExtension, String destinationDirectory, String url) {
//        // url = .getText().toString().trim();
//
//        DownloadManager downloadManager = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
//        Uri uri = Uri.parse(url);
//        DownloadManager.Request request = new DownloadManager.Request(uri);
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        request.setDestinationInExternalFilesDir(context, destinationDirectory, file + fileExtension);
//        downloadManager.enqueue(request);
//
//    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onResume() {
        super.onResume();



//        Locale locale = Constants.getSP(homeinvoice2.this).getLANG();
//        Locale.setDefault(locale);
//        Configuration config = getBaseContext().getResources().getConfiguration();
//        config.locale = locale;
//        getBaseContext().getResources().updateConfiguration(config,
//                getBaseContext().getResources().getDisplayMetrics());
        File imgFile = new File("/storage/emulated/0/PyMESoft/Logotipo/logopng");
        if (imgFile.exists()) {

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());


//           toolbar_imageview.setImageBitmap(myBitmap);

        }
        navigationView.setSelectedItemId(R.id.home);

    }

    @Override
    protected void onRestart() {

        super.onRestart();
        navigationView.setSelectedItemId(R.id.home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Lang=Constants.getSP(getApplicationContext()).getLANG();
        SetLanguage(Lang);


        navigationView.setSelectedItemId(R.id.home);


        return super.onCreateOptionsMenu(menu);

    }


    public void SetLanguage(String Lang) {

        Locale locale = new Locale(Lang);

        Locale.setDefault(locale);
        Configuration configuration = new Configuration();

        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
//        Intent refresh = new Intent(getIntent());
//        startActivity(refresh);
//        finish();

//        Constants.getSP(getApplicationContext()).setLANG(Language);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void LoadLocale(String lang) {
//        SetLanguage(lang);

    }
    public Context context1(){
        return context;
    }
    public void setContext1(Context context) {
        this.context = context;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toolbar.setNavigationIcon(R.drawable.menu);
    }
}







