package com.PyMESoft.Facturas.Constructores;

import java.io.File;

public class PdfFileConstructor {
   private File File_forUpload;

//    public PdfFileConstructor(){}

        public File getfile(){return File_forUpload;}
        public void setfile(File File_forUpload) {
            this.File_forUpload= File_forUpload ;
        }


}
