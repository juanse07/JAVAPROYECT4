package com.PyMESoft.Facturas;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

public interface Interface2 {
    void OnaddClick(double getinput);
    void outputClick(ArrayList<Double> Lista5);
    void streamclick(ByteArrayOutputStream outputStream);
    void passFile(File file);
}
