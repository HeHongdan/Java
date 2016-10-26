package com.hhd.java.A_3_08_wifi;

import android.app.Activity;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hhd.java.A_3_08_wifi.Wifi.WifiManageUtils;
import com.hhd.java.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;


public class WiFiServerActivity extends Activity {

    //自定义标记
    private static final int CONNECT_SUCCESS = 0x1;//连接成功
    private static final int CONNECT_FAIL = 0x2;//连接失败
    private static final int RECEIVER_INFO = 0x3;//接收内容
    private static final int SET_EDITTEXT_NULL = 0x4;//输入框内容为空

    //WiFi
    WifiManager wifiManager;
    WifiManageUtils wifiManageUtils;

    //Socket
    ServerSocket serverSocket;
    Socket socket;

    //流
    PrintStream out;//输出打印流
    BufferedReader in;//输入缓冲读流

    //组件
    private TextView tv_server;
    private EditText et_server;
    private Button send_server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wi_fi_server);
        tv_server= (TextView) findViewById(R.id.tv_server);
        et_server= (EditText) findViewById(R.id.et_server);
        send_server= (Button) findViewById(R.id.send_server);

        init();

        send_server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void init() {
        if (wifiManager.isWifiEnabled()){//如果wifi为激活状态则关闭wifi
            wifiManager.setWifiEnabled(false);
        }
        Boolean b=wifiManageUtils.stratWifiAp("HHD","12345678",3);
        if (b) {
//            serverThread = new WifiServerThread(context, testh);
//            serverThread.start();

            tv_server.setText("服务器已启动，正在等待连接...\n");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    //1.得到本地蓝牙设备
                    //2.创建蓝牙设备Socket服务器端
                    try {
                        //UUID
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
        } else {

        }
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
//                    new Thread(new ReceiverInfoThread()).start();//启动接收消息的线程
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

    //设置文本框（拼接）
    private void setInfo(String info){
        StringBuffer sb=new StringBuffer();
        sb.append(tv_server.getText());//append附加（在原先文本的后面追加）
        sb.append(info);
        tv_server.setText(sb);
    }







//
//        Boolean b = wifimanageutils.stratWifiAp(mSSID, mPasswd, 3);
//        if (b) {
//            serverThread = new WifiServerThread(context, testh);
//            Toast.makeText(context, "server 端启动", 3000).show();
//            serverThread.start();
//        } else {
//            btnServer.setEnabled(true);
//            Toast.makeText(context, "server 端失败，请重试", 3000).show();
//        }
//    }
}
