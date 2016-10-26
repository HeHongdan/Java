package com.hhd.java.A_3_08_0102;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hhd.java.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.UUID;

public class ClientBluetoothActivity extends Activity {

    //自定义标记
    private static final int CONNECT_SUCCESS = 0x1;//CONNECT_SUCCESS = 0x1;//连接成功
    private static final int CONNECT_FAIL = 0x2;//CONNECT_FAIL = 0x2;//连接失败
    private static final int RECEIVER_INFO = 0x3;//RECEIVER_INFO = 0x3;//接收内容
    private static final int SET_EDITTEXT_NULL = 0x4;//SET_EDITTEXT_NULL = 0x4;//输入框内容为空

    //蓝牙
    BluetoothSocket socket=null;
    BluetoothAdapter adapter=null;
    BluetoothDevice device=null;

    //流
    PrintStream out;
    BufferedReader in;

    //组件
    private TextView tv_client;
    private EditText et_client;
    private Button send_client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_blutooth);

        tv_client= (TextView) findViewById(R.id.tv_client);
        et_client= (EditText) findViewById(R.id.et_client);
        send_client= (Button) findViewById(R.id.send_client);

        init();

        send_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String content=et_client.getText().toString();
                if (TextUtils.isEmpty(content)){
                    Toast.makeText(ClientBluetoothActivity.this, "消息不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        out.print(content);
                        out.flush();
                        handler.sendEmptyMessage(SET_EDITTEXT_NULL);
                    }
                }).start();
            }
        });
    }

    private void init() {
        tv_client.setText("正在连接服务器...\n");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //1.得到本地蓝牙设备
                    adapter = BluetoothAdapter.getDefaultAdapter();
                    //2.通过本地默认蓝牙获取远程蓝牙设备
                    device = adapter.getRemoteDevice("48:6B:2C:A6:9F:94");//蓝牙地址
                    //3.根据UUID创建并返回一个BluetoothSocket
//                serverSocket = adapter.listenUsingRfcommWithServiceRecord("test", UUID.fromString("00000000-2527-eef3-ffff-ffffe3160865"));
                    // 串口协议Rfcomm
                    socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00000000-2527-eef3-ffff-ffffe3160865"));
                    if (socket != null) {
                        //连接服务器
                        socket.connect();
                        //4.处理输入出流
                        //向服务器写(打印到服务器)
                        out=new PrintStream(socket.getOutputStream());
                        //读取服务器
                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));//实例化一个缓冲读流接收（服务器端的输入读流（从socket中获取输入流））
                    }
                    handler.sendEmptyMessage(CONNECT_SUCCESS);
                }catch (IOException e) {
                        e.printStackTrace();
                        Message msg=handler.obtainMessage(CONNECT_FAIL,e.getLocalizedMessage());
                        handler.sendMessage(msg);
                }
            }
        }).start();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case CONNECT_SUCCESS:
                    setInfo("连接成功！\n");//提示
                    send_client.setEnabled(true);//按钮可以响应
                    System.out.println("蓝牙名称="+device.getName());
//                    System.out.println("蓝牙UUID="+device.getUuids());
                    System.out.println("蓝牙地址="+device.getAddress());
                    //启动接收消息的线程
                    new Thread(new ReceiverInfoThread()).start();
                    break;

                case CONNECT_FAIL:
                    setInfo("连接失败！\n");//提示并失败原因
                    setInfo(msg.obj.toString()+"\n");
                    break;

                case RECEIVER_INFO:
                    setInfo(msg.obj.toString()+"\n");//重新设置文本框
                    break;

                case SET_EDITTEXT_NULL:
                    et_client.setText("");//清空文本框
                    break;

                default:
                    break;
            }
        }
    };

    //接收消息的线程
    private boolean isReceiver = true;
    class ReceiverInfoThread implements Runnable {
        @Override
        public void run() {
            String info=null;
            while (isReceiver){
                try {
                    info = in.readLine();
                    Message msg=handler.obtainMessage(RECEIVER_INFO,info);
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //设置文本框（拼接）
    private void setInfo(String info){
        StringBuffer sb=new StringBuffer();
        sb.append(tv_client.getText());
        sb.append(info);
        tv_client.setText(sb);
    }
}
