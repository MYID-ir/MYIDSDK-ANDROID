package com.myid.sdkdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.myid.sdk.LanguageMyid;
import  com.myid.sdk.MyidAi;
import com.myid.sdk.ResultListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyidAi myidSDK = new MyidAi(this, "KEY","TOKEN");
        myidSDK.language(LanguageMyid.PERSIAN);
        Button btn_livenessCard = (Button) findViewById(R.id.livenessCard);
        Button btn_liveness = (Button) findViewById(R.id.liveness);
        TextView status_text = (TextView) findViewById(R.id.status_text);

        btn_liveness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int result = ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        status_text.setText("در حال پردازش...");
                        myidSDK.liveNess("REFERENCE_ID",new ResultListener() {
                                                      @Override
                                                      public void onResult(Object Value) {
                                                          status_text.setText(Value.toString());
                                                      }
                                                  }
                        );


                    } else {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                            Toast.makeText(MainActivity.this, "دسترسی ها را تایید بفرمایید.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "درسترسی های را تایید بفرمایید.", Toast.LENGTH_LONG).show();
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        }


                    }
                }


            }
        });



        btn_livenessCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int result = ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        status_text.setText("در حال پردازش...");
                        myidSDK.cardLiveness("REFERENCE_ID" ,new ResultListener() {
                                                          @Override
                                                          public void onResult(Object Value) {
                                                              status_text.setText(Value.toString());
                                                          }
                                                      }
                        );


                    } else {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                            Toast.makeText(MainActivity.this, "دسترسی ها را تایید بفرمایید.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "درسترسی های را تایید بفرمایید.", Toast.LENGTH_LONG).show();
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        }
                    }
                }
            }
        });



    }
}