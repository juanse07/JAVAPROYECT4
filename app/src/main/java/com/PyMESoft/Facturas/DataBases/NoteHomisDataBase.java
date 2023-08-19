package com.PyMESoft.Facturas.DataBases;

import android.content.Context;
import android.os.AsyncTask;

import com.PyMESoft.Facturas.Constructores.NoteHomis;
import com.PyMESoft.Facturas.Dao.NoteHomisDao;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {NoteHomis.class},version = 7)

public abstract class NoteHomisDataBase extends RoomDatabase {
    @NonNull
    private static com.PyMESoft.Facturas.DataBases.NoteHomisDataBase instance;
    public abstract NoteHomisDao noteHomisDao();
    public static synchronized com.PyMESoft.Facturas.DataBases.NoteHomisDataBase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(), com.PyMESoft.Facturas.DataBases.NoteHomisDataBase.class,"note_Homisdatabase")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomcallback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomcallback=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new NoteHomisDataBase.PopulatedbAsynkTask(instance).execute();
        }
    };
    public static class PopulatedbAsynkTask extends AsyncTask<Void,Void,Void> {
        private NoteHomisDao noteHomisDao;
        private PopulatedbAsynkTask(com.PyMESoft.Facturas.DataBases.NoteHomisDataBase db){
            noteHomisDao=db.noteHomisDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //noteDao.Insert(new Note(String.valueOf(47)));
            //noteDao.Insert(new Note(String.valueOf(89.9)));

            return null;
        }
    }

}
