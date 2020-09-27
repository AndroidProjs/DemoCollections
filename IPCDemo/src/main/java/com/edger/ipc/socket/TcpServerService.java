package com.edger.ipc.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.edger.ipc.utils.IOUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TcpServerService extends Service {

    private static final String TAG = "TcpServerService";

    private boolean isServiceDestroyed = false;

    private String[] preDefinedMessages = new String[]{
            "你好啊，哈哈",
            "请问你叫什么名字啊",
            "今天深圳天气不错啊，shy",
            "你知道吗？我可以和多个人同时聊天哦",
            "给你讲个笑话吧：据说爱笑的人运气都不会太差，不知道真假。"
    };

    @Override
    public void onCreate() {
        new Thread(new TcpServer()).start();
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        isServiceDestroyed = true;
        super.onDestroy();
    }

    private class TcpServer implements Runnable {

        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(8688);
            } catch (IOException e) {
                Log.d(TAG, "establish tcp server failed, port 8688");
                e.printStackTrace();
                return;
            }

            while (!isServiceDestroyed) {
                try {
                    // 接受客户端请求
                    final Socket client = serverSocket.accept();
                    Log.d(TAG, "accept");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void responseClient(Socket client) throws IOException {
        // 用于接收客户端消息
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        // 用于向客户端发送消息
        PrintWriter out = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(client.getOutputStream())), true);
        out.println("欢迎来到聊天室！");
        while (!isServiceDestroyed) {
            String str = in.readLine();
            Log.d(TAG, "Message from client: " + str);
            if (str == null) {
                break;
            }
            int i = new Random().nextInt(preDefinedMessages.length);
            String msg = preDefinedMessages[i];
            out.println(msg);
            Log.d(TAG, "send : " + msg);
        }
        Log.d(TAG, "client quit.");
        //关闭流
        IOUtils.close(in);
        IOUtils.close(out);
        client.close();
    }
}
