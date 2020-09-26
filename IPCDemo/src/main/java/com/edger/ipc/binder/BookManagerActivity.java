package com.edger.ipc.binder;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.edger.ipc.R;

import java.util.Arrays;
import java.util.List;

public class BookManagerActivity extends AppCompatActivity {

    private static final String TAG = "BookManagerActivity";

    public static final int MESSAGE_NEW_BOOK_ARRIVED = 1;

    IBookManager mRemoteBookManager;

    private static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_NEW_BOOK_ARRIVED:
                    Log.d(TAG, "receive new book: " + msg.obj);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };

    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if (mRemoteBookManager == null) {
                Log.d(TAG, "binderDied: mRemoteBookManager is null.");
                return;
            }
            mRemoteBookManager.asBinder().unlinkToDeath(deathRecipient, 0);
            mRemoteBookManager = null;
            Log.d(TAG, "binder died. current thread name : " + Thread.currentThread().getName());
        }
    };

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mRemoteBookManager = IBookManager.Stub.asInterface(service);
            try {
                mRemoteBookManager.asBinder().linkToDeath(deathRecipient, 0);

                List<Book> list = mRemoteBookManager.getBookList();
                Log.i(TAG, "query book list, list type: " + list.getClass().getCanonicalName());
                Log.i(TAG, "query book list: " + Arrays.toString(list.toArray()));

                Book newBook = new Book(3, "Android 开发艺术探索");
                mRemoteBookManager.addBook(newBook);
                Log.i(TAG, "add book : " + newBook);

                list = mRemoteBookManager.getBookList();
                Log.i(TAG, "query book list: " + Arrays.toString(list.toArray()));
                mRemoteBookManager.registerListener(mIOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG,
                    "onServiceDisconnected, current thread name: " + Thread.currentThread().getName());
        }
    };

    private IOnNewBookArrivedListener mIOnNewBookArrivedListener =
            new IOnNewBookArrivedListener.Stub() {
                @Override
                public void onNewBookArrived(Book newBook) throws RemoteException {
                    // Toast.makeText(BookManagerActivity.this, "receive new book " + newBook,
                    //         Toast.LENGTH_SHORT).show();
                    mHandler.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED, newBook).sendToTarget();
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manager);

        Intent intent = new Intent(BookManagerActivity.this,
                BookManagerService.class);
        bindService(intent, mConnection, BIND_AUTO_CREATE);

        Button button = findViewById(R.id.bind_to_book_manager_service);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BookManagerActivity.this, "click button", Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (mRemoteBookManager != null) {
                            try {
                                Log.d(TAG, "run getBookList in thread.");
                                List<Book> list = mRemoteBookManager.getBookList();
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (mRemoteBookManager != null
                && mRemoteBookManager.asBinder().isBinderAlive()) {
            try {
                Log.i(TAG, "unregister listener: " + mIOnNewBookArrivedListener);
                mRemoteBookManager.unregisterListener(mIOnNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(mConnection);
        super.onDestroy();
    }
}