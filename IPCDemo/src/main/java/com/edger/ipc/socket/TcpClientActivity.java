package com.edger.ipc.socket;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.edger.ipc.R;
import com.edger.ipc.utils.IOUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TcpClientActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "TcpClientActivity";

    public static final int MESSAGE_RECEIVE_NEW_MSG = 1;
    public static final int MESSAGE_SOCKET_CONNECT = 2;

    private Button sendMsgButton;
    private TextView messageTextView;
    private EditText messageEditText;

    private PrintWriter mPrintWriter;
    private Socket clientSocket;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_RECEIVE_NEW_MSG:
                    messageTextView.setText(messageTextView.getText() + (String) msg.obj);
                    break;
                case MESSAGE_SOCKET_CONNECT:
                    sendMsgButton.setEnabled(true);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcp_client);

        messageTextView = findViewById(R.id.msg_container);
        sendMsgButton = findViewById(R.id.send);
        sendMsgButton.setOnClickListener(this);
        messageEditText = findViewById(R.id.msg);

        Intent intent = new Intent(this, TcpServerService.class);
        startService(intent);
        new Thread(new Runnable() {
            @Override
            public void run() {
                connectToTcpSer();
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        if (clientSocket != null) {
            try {
                clientSocket.shutdownInput();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (v == sendMsgButton) {
            final String msg = messageEditText.getText().toString();
            if (!TextUtils.isEmpty(msg) && mPrintWriter != null) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mPrintWriter.println(msg);

                    }
                }).start();
                messageEditText.setText("");
                String time = formatDateTime(System.currentTimeMillis());
                String showMsg = "self " + time + ":" + msg + "\n";
                messageTextView.setText(messageTextView.getText() + showMsg);
            }
        }
    }

    private void connectToTcpSer() {
        Socket socket = null;
        while (socket == null) {
            try {
                socket = new Socket("localhost", 8688);
                clientSocket = socket;
                mPrintWriter = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())), true);
                mHandler.sendEmptyMessage(MESSAGE_SOCKET_CONNECT);
                Log.d(TAG, "Connect server successfully.");
            } catch (IOException e) {
                SystemClock.sleep(1000);
                Log.d(TAG, "Connect tcp server failed, retry...");
                e.printStackTrace();
            }

            try {
                if (socket != null) {
                    BufferedReader bufferedReader =
                            new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    while (!TcpClientActivity.this.isFinishing()) {
                        String msg = bufferedReader.readLine();
                        Log.d(TAG, "Receive : " + msg);
                        if (msg != null) {
                            String time = formatDateTime(System.currentTimeMillis());
                            String showMsg = "server " + time + ":" + msg + "\n";
                            mHandler.obtainMessage(MESSAGE_RECEIVE_NEW_MSG, showMsg).sendToTarget();
                        }
                    }
                    Log.d(TAG, "quit...");
                    IOUtils.close(bufferedReader);
                    IOUtils.close(mPrintWriter);
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private String formatDateTime(long currentTimeMillis) {
        return new SimpleDateFormat("(HH:mm:ss)").format(new Date(currentTimeMillis));
    }
}