package com.PyMESoft.Facturas;

import com.PyMESoft.Facturas.Constructores.NoteProducto;

public interface ClickInterface1 {
    void onItemClick(int position);
    void onButtonAddClick(int position);
    void onButtonclienteClick(int position);
    void passingproductoClick(int position, CharSequence Producto, CharSequence Precio,CharSequence Cantidad);
    void passingprecio1Click(int position, CharSequence Precio);
    void passingcliente2Click(int position, CharSequence Cliente,String phone,String Email,String Address, String City);
    void passfirebasekey(String key);
    void preferenciasacalculadora();
    void Preferenciasingreo(int estado, String dias, String Tipo);
    void passingpositionk(int position);
    void PassTipoDoc(int position,CharSequence tipoDoc);
    void PassnoteprodPosition(int position, String Producto, String Cantidad, String Precio, NoteProducto currentnote);
    void onsearchQuery(String query);




}
