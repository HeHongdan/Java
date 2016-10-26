package com.hhd.java.A_3_08_wifi;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hhd.java.A_3_08_0102.ClientBluetoothActivity;
import com.hhd.java.A_3_08_0102.ServerBluetoothActivity;
import com.hhd.java.R;

public class WiFiActivity extends Activity {
    private Button server;
    private Button client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wi_fi);

        server= (Button) findViewById(R.id.server);
        client= (Button) findViewById(R.id.client);

        server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toServer=new Intent(WiFiActivity.this, WiFiServerActivity.class);
                startActivity(toServer);

            }
        });

        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toClient = new Intent(WiFiActivity.this,WiFiClientActivity.class);
                startActivity(toClient);
            }
        });
    }
}
