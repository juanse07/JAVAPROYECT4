package com.PyMESoft.Facturas.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.PyMESoft.Facturas.Adapadores.Customtoolbar;
import com.PyMESoft.Facturas.Constants;
import com.PyMESoft.Facturas.Constructores.PdfFileConstructor;
import com.PyMESoft.Facturas.Interface2;
import com.PyMESoft.Facturas.NoteProdViewModel;
import com.PyMESoft.Facturas.Pdfs_disenos.Pdfbasicclass;
import com.PyMESoft.Facturas.Pdfs_disenos.Pdfsolarisclass;
import com.PyMESoft.Facturas.Pdfs_disenos.Pdfstructuredclass;
import com.PyMESoft.Facturas.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

//import com.github.barteksc.pdfviewer.util.FitPolicy;


public class pdfviewer3 extends AppCompatActivity  implements Interface2 {
    private PDFView pdfView;

    ImageView back1;
    private File file;
    int comprobarpdf;


    Pdfstructuredclass pdfstructuredclass=new Pdfstructuredclass();
    Pdfbasicclass pdfbasicclass=new Pdfbasicclass();
    Pdfsolarisclass pdfsolarisclass=new Pdfsolarisclass();
   PdfFileConstructor pdfFileConstructor= new PdfFileConstructor();



    TextView title6;
    BaseFont baseFont=null;
    Uri pdfUri;
    Toolbar customtol;
    androidx.appcompat.widget.SearchView toolbar_searchview;
    CardView toolbar_cardview;
    TextView toolbar_tittle;


    ArrayList<Double> listacuero3;

    NoteProdViewModel noteProdViewModel;
    DecimalFormat format = new DecimalFormat("0.00");


    StorageReference storageReference;





    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


    Document mDoc = new Document(PageSize.LETTER,36,36,53,56);

//
    String productoventas2;

    String precioventas2;

  String valorventas2;
  double valorbr;
    double ValorImp;
    double ValorDesc;
    double TaxValue;
    double TaxValue2;
    double DiscountValue;
    double ValorImp2;
    double valorneto;
  String fechaventas2,pdfemail,pdfphone,pdfcity,pdfaddress;
   String estadoventas2;
//    String horareal2;
  String diaspago,Fecha2;
    DatabaseReference myrootDbaseref5;
    FirebaseStorage mystorage;
    FirebaseAuth mAuth;


    ArrayList<String>List1,Lista7;
    ArrayList<Double>List2,List3,List4;
File File_upload, fileupload;
Context context;
   String nombreventas2;

    String pattern = "EEEEE MMMMM yyyy HH:mm:ss.SSSZ";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("en", "US"));
    String mFilename = simpleDateFormat.format(System.currentTimeMillis());
    private Customtoolbar customtoolbar;
    Activity activity=this;




    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat hora1 = new SimpleDateFormat("HH:mm:ss");
    String hora2 = hora1.format(calendar.getTime());
    String mFilepath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+   File.separator+ "PyMESoft"+
            File.separator+"invoices"+File.separator +mFilename.replaceAll(":",".");

// String mFilepath = Environment.getExternalStorageDirectory() +   File.separator+ "PyMESoft"+
//  File.separator+"invoices"+File.separator +mFilename.replaceAll(":",".");
    //////////////

//    ContextWrapper cw = new ContextWrapper(getApplicationContext());
//    File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
//    File file = new File(directory, "UniqueFileName" + ".jpg");


   // byte[] outputStream = new ByteArrayOutputStream();
    byte[] outputStream2;
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 111;
    private static final int REQUEST_CODE_ASK_PERMISSIONS_2 = 112;
    // InputStream in = new ByteArrayInputStream(outputStream2.toByteArray());

    // Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewer2);
        pdfView = (PDFView) findViewById(R.id.pdfView);
        title6 = findViewById(R.id.title6);



        customtoolbar= findViewById(R.id.custom_toolbar);
//        toolbar_cardview= findViewById(R.id.toolbar_cardview);
        toolbar_tittle= findViewById(R.id.toolbar_tittle);
        customtol= findViewById(R.id.toolbar_relativelay);
        toolbar_searchview= findViewById(R.id.toolbar_searchview);
        myrootDbaseref5 = FirebaseDatabase.getInstance().getReference();

        mystorage = FirebaseStorage.getInstance();
        storageReference = mystorage.getReference();

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

        ExecutorService service= Executors.newFixedThreadPool(50);
service.execute(new Runnable() {
    @Override
    public void run() {

        runOnUiThread(new Runnable() {


            @Override
            public void run() {
                construirpdf();
                dibujarpdf();

            }
        });
    }
});











////////////////////////////////////

    }



    public void compartir(View view) throws IOException {
        RecibirBundleFragment3();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, REQUEST_CODE_ASK_PERMISSIONS);


            } else {




            }
        } else {









        }






        if(comprobarpdf==1){
            file=new File(pdfbasicclass.getfile2().getAbsolutePath());

        }else if(comprobarpdf==0){
             file=new File(pdfstructuredclass.getfile2().getAbsolutePath());

        }else if (comprobarpdf==2){
            file=new File(pdfsolarisclass.getfile2().getAbsolutePath());
        }

        Uri pdfUri = Uri.fromFile(file);
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);

        shareIntent.putExtra(Intent.EXTRA_STREAM, pdfUri);
        shareIntent.setType("application/pdf");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);


        try {

            startActivity(Intent.createChooser(shareIntent, "share"));
        } catch (ActivityNotFoundException e) {

            // Instruct the user to install a PDF reader here, or something

            //}


        }
    }

    public void home1(View view) {
   guardarbdventas();
        finish();

        Intent i = new Intent(pdfviewer3.this, homeinvoice2.class);
        startActivity(i);
        noteProdViewModel=new ViewModelProvider(this).get(NoteProdViewModel.class);
        noteProdViewModel.DeleteAll();

    }


    @Override
    public void OnaddClick(double getinput) {

    }

    @Override
    public void outputClick(ArrayList<Double> Lista5) {


    }

    @Override
    public void streamclick(ByteArrayOutputStream outputStream) {

    }

    @Override
    public void passFile(File file) {
//        fileupload=file;


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    //new PdfSave().execute();
                  try {
                   RecibirBundleFragment3();
                      if(comprobarpdf==0){
                          pdfstructuredclass.createstructuredpdf(pdfviewer3.this,productoventas2,precioventas2,valorventas2,
                                  valorbr,valorneto,fechaventas2,estadoventas2,diaspago,Fecha2,nombreventas2,
                                  Lista7,List1,List2,List3,List4,outputStream, mFilepath,ValorDesc,ValorImp,ValorImp2,DiscountValue,TaxValue,TaxValue2,
                                  pdfphone,pdfemail,pdfaddress,pdfcity);


                      }else if(comprobarpdf==1){
                          pdfbasicclass.createpdf(pdfviewer3.this,productoventas2,precioventas2,valorventas2,
                                  valorbr,valorneto,fechaventas2,estadoventas2,diaspago,Fecha2,nombreventas2,
                                  Lista7,List1,List2,List3,List4,outputStream, mFilepath,ValorDesc,ValorImp,ValorImp2,DiscountValue,TaxValue,TaxValue2,
                                  pdfphone,pdfemail,pdfaddress,pdfcity);



                      }else if (comprobarpdf==2){
                          pdfsolarisclass.createpdf(pdfviewer3.this,productoventas2,precioventas2,valorventas2,
                                  valorbr,valorneto,fechaventas2,estadoventas2,diaspago,Fecha2,nombreventas2,
                                  Lista7,List1,List2,List3,List4,outputStream, mFilepath,ValorDesc,ValorImp,ValorImp2,DiscountValue,TaxValue,TaxValue2,
                                  pdfphone,pdfemail,pdfaddress,pdfcity);

                      }

//                      else if(comprobarpdf==1){
//

                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }


                    Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(this, "Permiso no apto para mayores", Toast.LENGTH_SHORT).show();
                }


            }
            case REQUEST_CODE_ASK_PERMISSIONS_2:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    uploadpdf(pdfUri);


                } else {
                    Toast.makeText(pdfviewer3.this, "Permiso negado para adultos", Toast.LENGTH_SHORT).show();
                }

            }
        }






    }


    public void uploadpdf() {

        switch(comprobarpdf){
            case 0:
                fileupload = new File(pdfstructuredclass.getfile2().getAbsolutePath());
                break;
            case 1:
                fileupload = new File(pdfbasicclass.getfile2().getAbsolutePath());
                break;
            case 2:  fileupload = new File(pdfsolarisclass.getfile2().getAbsolutePath());
                break;
        }


//
//
//



            mAuth = FirebaseAuth.getInstance();
            String id = mAuth.getCurrentUser().getUid();


            // pdfUri = Uri.fromFile(new File(pdfFile.getAbsolutePath()))
            pdfUri = Uri.fromFile(fileupload);


            storageReference.child("pdfcloud2").child(mFilename).child(id).putFile(pdfUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                            // String url = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                            taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Uri downloadurl = uri;

                                    String url2 = String.valueOf(downloadurl);
                                    mAuth = FirebaseAuth.getInstance();
                                    String id = mAuth.getCurrentUser().getUid();


                                    String nombreventas = nombreventas2;
                                    String fechaventas = fechaventas2;
                                    String horaventas = "n/a";//horaventas2;
                                    String productoventas = productoventas2;
                                    //String colorventas = ghora.getText().toString();
//                                String unidadesventas = unidadesventas2;
                                    String precioventas = precioventas2;
                                    String medidaventas = "n/a"; //medidaventas2;
                                    String valorventas = valorventas2;
                                    String estado = estadoventas2;


                                    DatabaseReference newref = myrootDbaseref5.child("VENTAS").child(id).push();


                                    Map<String, Object> datosventa = new HashMap<>();
                                    datosventa.put("Fecha", fechaventas);
                                    datosventa.put("Hora", horaventas);
                                    datosventa.put("Cliente", nombreventas);
                                    datosventa.put("Producto", productoventas);
                                    datosventa.put("Medida", medidaventas);
                                    datosventa.put("Precio", precioventas);
//                                datosventa.put("Unidades", unidadesventas);
                                    datosventa.put("Valor", valorventas);
                                    datosventa.put("Estado", estado);
                                    datosventa.put("pdfurl", url2);
                                    datosventa.put("Key_fire", newref.getKey());
                                    datosventa.put("Fechaparapago", Fecha2);
                                    datosventa.put("Dias_plazo", diaspago);


                                    datosventa.put("Id_usuario", id);
                                    newref.setValue(datosventa);
                                    Toast.makeText(pdfviewer3.this, "Se ha registrado la Venta", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    });


//         Instruct the user to install a PDF reader here, or something



    }
    public void construirpdf(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, REQUEST_CODE_ASK_PERMISSIONS);


            } else {


                try {


                    RecibirBundleFragment3();


                    if (comprobarpdf == 1) {
                        pdfbasicclass.createpdf(pdfviewer3.this, productoventas2, precioventas2, valorventas2,
                                valorbr, valorneto, fechaventas2, estadoventas2, diaspago, Fecha2, nombreventas2,
                                Lista7, List1, List2, List3, List4, outputStream, mFilepath, ValorDesc, ValorImp, ValorImp2,
                                DiscountValue, TaxValue, TaxValue2, pdfphone, pdfemail, pdfaddress, pdfcity);



                    } else if (comprobarpdf == 0) {
                        pdfstructuredclass.createstructuredpdf(pdfviewer3.this, productoventas2, precioventas2, valorventas2,
                                valorbr, valorneto, fechaventas2, estadoventas2, diaspago, Fecha2, nombreventas2,
                                Lista7, List1, List2, List3, List4, outputStream, mFilepath, ValorDesc, ValorImp, ValorImp2,
                                DiscountValue, TaxValue, TaxValue2, pdfphone, pdfemail, pdfaddress, pdfcity);


                    } else if (comprobarpdf == 2) {
                        pdfsolarisclass.createpdf(pdfviewer3.this, productoventas2, precioventas2, valorventas2,
                                valorbr, valorneto, fechaventas2, estadoventas2, diaspago, Fecha2, nombreventas2,
                                Lista7, List1, List2, List3, List4, outputStream, mFilepath, ValorDesc, ValorImp, ValorImp2,
                                DiscountValue, TaxValue, TaxValue2, pdfphone, pdfemail, pdfaddress, pdfcity);



                    }


                } catch (DocumentException e) {
                    Toast.makeText(pdfviewer3.this, "no inicia", Toast.LENGTH_SHORT).show();
                }








            }
        }


    }
    public void dibujarpdf(){
        pdfView.fromBytes(outputStream.toByteArray())
                //.enableSwipe(true)
                //.swipeHorizontal(false)
                //.enableDoubletap(true)
                //.enableAntialiasing(true)
                //.fitEachPage(true)
                //.spacing(0)
                //.autoSpacing(true)

                //.pageFling(true)
                //.pageSnap(true)
                //.fitEachPage(true)
                .swipeHorizontal(true)
                //.pageSnap(true)
                //.autoSpacing(true)
                // .pageFling(true)


                .load();

    }

        public void guardarbdventas () {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_DENIED) {
                    String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                    requestPermissions(permissions, REQUEST_CODE_ASK_PERMISSIONS_2);

                } else {




                    Log.d("comprobar",String.valueOf(comprobarpdf));
//                    uploadpdf(file);
                    uploadpdf();

                }


            } else {
//                uploadpdf(file);
                uploadpdf();
            }


            Toast.makeText(pdfviewer3.this, "Se ha registrado la Venta", Toast.LENGTH_SHORT).show();
            //Intent i = new Intent(VistaAA.this, home1.class);
            //startActivity(i);

        }    ///////////////////////


        public void RecibirBundleFragment3 () {

            comprobarpdf = Constants.getSP(this).getPDFPOSITION();


            listacuero3 = (ArrayList<Double>) getIntent().getSerializableExtra("WLTP_list");

            // gnombre2.setText(getIntent().getExtras().getString("Nombre1"));
            nombreventas2 = getIntent().getExtras().getString("Nombre1");
//        RecibirNoteprod=(Map<String,String>).getSerializableExtra("mapa");


            fechaventas2 = getIntent().getExtras().getString("Fecha1").trim();

//         horaventas2=getIntent().getExtras().getString("Hora1");

            productoventas2 = getIntent().getExtras().getString("Producto1");

            List1 = (ArrayList<String>) getIntent().getSerializableExtra("listaProd1");
            List2 = (ArrayList<Double>) getIntent().getSerializableExtra("listaPre1");
            List3 = (ArrayList<Double>) getIntent().getSerializableExtra("listaCant1");
            List4 = (ArrayList<Double>) getIntent().getSerializableExtra("listaResultado");
//        List5= (ArrayList<Double>) getIntent().getSerializableExtra("ListaImp");
//        List6=(ArrayList<Double>) getIntent().getSerializableExtra("ListaRimp");
            Lista7 = (ArrayList<String>) getIntent().getSerializableExtra("ListaDesc");

            valorbr = getIntent().getExtras().getDouble("valorbruto");
            ValorImp = getIntent().getExtras().getDouble("valorimp");
            ValorImp2 = getIntent().getExtras().getDouble("valorimp2");
            ValorDesc = getIntent().getExtras().getDouble("valordesc");
            TaxValue = getIntent().getExtras().getDouble("impuestopercent");
            TaxValue2 = getIntent().getExtras().getDouble("impuesto2percent");
            DiscountValue = getIntent().getExtras().getDouble("descpercent");
            valorneto = getIntent().getExtras().getDouble("valorneto");
            pdfphone = getIntent().getExtras().getString("pdfphone");
            pdfemail = getIntent().getExtras().getString("pdfemail");
            pdfaddress = getIntent().getExtras().getString("pdfaddress");
            pdfcity = getIntent().getExtras().getString("pdfcity");
//        SumaResultado=getIntent().getExtras().getString("Total1");


//        unidadesventas2=getIntent().getExtras().getString("Unidades1");

            precioventas2 = getIntent().getExtras().getString("Precio1");

//       medidaventas2=getIntent().getExtras().getString("Medida1");
//
            valorventas2 = getIntent().getExtras().getString("Total1");

            estadoventas2 = getIntent().getExtras().getString("Estado1");
            Fecha2 = getIntent().getExtras().getString("Fecha2").trim();
            //outputStream2=getIntent().getExtras().getByteArray("wpa");


//        horareal2=hora2;
            diaspago = Constants.getSP(this).getDIAS();


        }
        public void createpdfFile(Activity activity){


        }

//


}