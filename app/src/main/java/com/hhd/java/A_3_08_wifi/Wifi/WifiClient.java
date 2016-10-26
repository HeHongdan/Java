package com.hhd.java.A_3_08_wifi.Wifi;

import android.net.DhcpInfo;

/**
 * 开启客户端部分代码
 * Created by HHJ on 2016/9/2.
 */
public class WifiClient {

//    btnClient.setOnClickListener(new OnClickListener()
//    {
//
//        @Override
//        public void onClick(View v)
//        {
//            WifiConfiguration netConfig = wifimanageutils
//                    .getCustomeWifiClientConfiguration(mSSID, mPasswd,3);
//
//            int wcgID = wifiManager.addNetwork(netConfig);
//            boolean b = wifiManager.enableNetwork(wcgID, true);
//
//            Boolean iptoready = false;
//            if (!b)
//            {
//                Toast.makeText(context, "wifi 连接配置不可用", 3000).show();
//                return;
//            }
//            while (!iptoready)
//            {
//                try
//                {
//                    // 为了避免程序一直while循环，让它睡个100毫秒在检测……
//                    Thread.currentThread();
//                    Thread.sleep(100);
//                }
//                catch (InterruptedException ie)
//                {
//                }
//
//                DhcpInfo dhcp = new WifiManageUtils(context).getDhcpInfo();
//                int ipInt = dhcp.gateway;
//                if (ipInt != 0)
//                {
//                    iptoready = true;
//                }
//            }
//            wifiLock.acquire();
//            clientThread = new WifiClientThread(context);
//            clientThread.start();
//        }
//    });
}
