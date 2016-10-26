package com.hhd.java.A_3_08_0102;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hhd.java.R;

public class BluetoothActivity extends Activity {

    private Button server;
    private Button client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        server= (Button) findViewById(R.id.server);
        client= (Button) findViewById(R.id.client);

        server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toServer=new Intent(BluetoothActivity.this, ServerBluetoothActivity.class);
                startActivity(toServer);

            }
        });

        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toClient = new Intent(BluetoothActivity.this,ClientBluetoothActivity.class);
                startActivity(toClient);
            }
        });
    }
}
