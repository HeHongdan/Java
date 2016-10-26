package com.hhd.java.A_3_08_0102;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.UUID;

import com.hhd.java.R;

public class ServerBluetoothActivity extends Activity {

    //自定义标记
    private static final int CONNECT_SUCCESS = 0x1;//连接成功
    private static final int CONNECT_FAIL = 0x2;//连接失败
    private static final int RECEIVER_INFO = 0x3;//接收内容
    private static final int SET_EDITTEXT_NULL = 0x4;//输入框内容为空

    //组件
    private TextView tv_server;
    private EditText et_server;
    private Button send_server;

    //蓝牙
    BluetoothServerSocket serverSocket;//蓝牙设备Socket服务器端
    BluetoothSocket socket;//蓝牙设备Socket客户端
    BluetoothAdapter adapter;//本地蓝牙设备

    //流
    PrintStream out;//输出打印流
    BufferedReader in;//输入缓冲读流

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_bluetooth);

        tv_server= (TextView) findViewById(R.id.tv_server);
        et_server= (EditText) findViewById(R.id.et_server);
        send_server= (Button) findViewById(R.id.send_server);

        init();

        send_server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String content = et_server.getText().toString();
                if (TextUtils.isEmpty(content)){
                    Toast.makeText(ServerBluetoothActivity.this,"消息不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        out.println(content);
                        out.flush();//刷新该流的缓冲。通过将所有缓冲的输出字节写入到底层输出流然后刷新该流的缓冲，完成此操作。
                        handler.sendEmptyMessage(SET_EDITTEXT_NULL);//清空文本框
                    }
                }).start();
            }
        });
    }

    private void init() {
        tv_server.setText("服务器已启动，正在等待连接...\n");

        new Thread(new Runnable() {
            @Override
            public void run() {
                //1.得到本地蓝牙设备
                adapter = BluetoothAdapter.getDefaultAdapter();
                //2.创建蓝牙设备Socket服务器端
                try {
                    //UUID
                    serverSocket = adapter.listenUsingRfcommWithServiceRecord("test", UUID.fromString("00000000-2527-eef3-ffff-ffffe3160865"));
                    //3.阻塞等待蓝牙设备Socket客户端请求
                    socket=serverSocket.accept();//获取得到客户端Socket
                    if (socket != null){
                        //4.处理输入出流
                        out=new PrintStream(socket.getOutputStream());//写流
                        in=new BufferedReader(new InputStreamReader(socket.getInputStream()));//读流
                    }
                    handler.sendEmptyMessage(CONNECT_SUCCESS);
                } catch (IOException e) {
                    e.printStackTrace();
                    Message msg = handler.obtainMessage(CONNECT_FAIL,e.getLocalizedMessage());//getLocalizedMessage()：Exception的本地化描述
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }

    //Handler
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case RECEIVER_INFO:
                    setInfo(msg.obj.toString()+"\n");//重新设置文本框
                    break;

                case SET_EDITTEXT_NULL:
                    et_server.setText("");//清空文本框
                    break;

                case CONNECT_SUCCESS:
                    setInfo("连接成功！\n");//提示
                    send_server.setEnabled(true);//按钮可以响应
                    new Thread(new ReceiverInfoThread()).start();//启动接收消息的线程
                    break;

                case CONNECT_FAIL:
                    setInfo("连接失败！\n");//提示并失败原因
                    setInfo(msg.obj.toString()+"\n");
                    break;

                default:
                    break;
            }
        }
    };

    //接收消息的线程
    private boolean isReceiver=true;
    class ReceiverInfoThread implements Runnable{
        @Override
        public void run() {
            String info=null;
            while (isReceiver){
                try {
                    info=in.readLine();
                    Message msg = handler.obtainMessage(RECEIVER_INFO,info);
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
        sb.append(tv_server.getText());//append附加（在原先文本的后面追加）
        sb.append(info);
        tv_server.setText(sb);
    }


}
