package com.hhd.java.J_1_20_0205;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.hhd.java.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 * J20-02 Socket概念和简单的TCP程序
 *
 */
public class EchoClientActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_echo_client);
    }

    public void connectionClick(View view) {
        try {
            Socket socket = new Socket("192.168.199.148", 8000);
            System.out.println("与服务器连接成功...");
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            PrintStream ps = new PrintStream(out);
            ps.println("你好，服务器。我是客户端！");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String info = br.readLine();
            System.out.println(info);
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
