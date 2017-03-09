package pt.iscte.appdb;

import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.view.Menu;

import android.widget.TextView;;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		criar();
		listar();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	
	public void criar(){
		DataBaseHelper db = new DataBaseHelper(this);
		 try {
			 db.createDataBase();
		 } catch (IOException ioe) {
			 throw new Error("Avaria na criacao de base de dados");
		 }
		  
		 try {
			 db.openDataBase();
		 }catch(SQLException sqle){
			 throw sqle;
		 }
	
	}
	
    public void listar() {
        DataBaseHelper db = new DataBaseHelper(this);
        
        db.openDataBase();
        Cursor cursor = db.listar();
 
        String texto="";
        
        if (cursor.moveToFirst()) {
            do {
                
                texto += cursor.getString(0) +"\n";
                texto += cursor.getString(1) +"\n";
                texto += cursor.getString(2) +"\n";

            } while (cursor.moveToNext());
        }
        
        TextView textView1 = (TextView) findViewById(R.id.textView1);
		textView1.setText(texto);
    }
}
