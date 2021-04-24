package com.unicordoba.labactividadcorte2.Log;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.Date;

public class Log {
    public Context context;
    String root;
    public void WritetoFile(String data, Context context) {
        this.context = context;
         root = context.getExternalFilesDir(null).getAbsolutePath();
        Toast.makeText(context, root, Toast.LENGTH_SHORT).show();
        FileWriter fileWriter;
        BufferedWriter bw = null;
        File f = new File(root, "LogRegistro.txt");
        try {
            fileWriter = new FileWriter(root + "/LogRegistro.txt", true);
            if (f.exists()) {
                try {
                    bw = new BufferedWriter(fileWriter);
                    bw.write(data + "\n");
                    bw.close();
                } catch (IOException e){

                }
            } else{
                try{
                    f.createNewFile();
                    }catch (IOException e){
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    public String readToFile(Context context){
        String mensaje= "";
        root= context.getExternalFilesDir(null).getAbsolutePath();
        File file;
        file =new File(root, "LogRegistro.txt");
        try{
            BufferedReader fin = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = fin.readLine();
            while (line != null){
                mensaje= line+"\n"+mensaje;
                line = fin.readLine();
            }
            fin.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return mensaje;
    }
    public String CapturarIngreso(Context context){
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        Toast.makeText(context,currentDateTimeString+"  Ingreso registrado",Toast.LENGTH_LONG).show();
        return currentDateTimeString+"  Ingreso registrado";
    }
}
