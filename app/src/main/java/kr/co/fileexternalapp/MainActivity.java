package kr.co.fileexternalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    String FILENAME = "test2.txt";
    EditText edit;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)==false){
            Toast.makeText(this,"외부 스토리지 실패",Toast.LENGTH_SHORT).show();
        }

        edit = (EditText) findViewById(R.id.EditText01);
        Button readButton = (Button) findViewById(R.id.read);
        readButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                File file = new File(getExternalFilesDir(null), FILENAME);
                try {
                    InputStream is;
                    is = new FileInputStream(file);
                    byte[] buffer = new byte[is.available()];
                    is.read(buffer);
                    edit.setText(new String(buffer));
                    is.close();

                    Toast.makeText(getApplicationContext(),"외부 스토리지 읽기 성공",Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        Button writeButton = (Button) findViewById(R.id.write);
        writeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                File file = new File(getExternalFilesDir(null), FILENAME);
                try {
                    OutputStream os = new FileOutputStream(file);
                    os.write(edit.getText().toString().getBytes());
                    os.close();

                    Toast.makeText(getApplicationContext(),"외부 스토리지 저장 성공",Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}