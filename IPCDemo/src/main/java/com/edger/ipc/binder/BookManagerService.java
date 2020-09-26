package com.edger.ipc.binder;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.edger.ipc.binder.IBookManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class BookManagerService extends Service {

    private static final String TAG = "BookManagerService";

    private AtomicBoolean mIsServiceDestroyed = new AtomicBoolean(false);

    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();

    // private CopyOnWriteArrayList<IOnNewBookArrivedListener> mListeners =
    //         new CopyOnWriteArrayList<>();

    private RemoteCallbackList<IOnNewBookArrivedListener> mListeners = new RemoteCallbackList<>();

    private Binder mBinder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            // SystemClock.sleep(5000);
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int check = checkCallingOrSelfPermission("com.edger.ipc.permission" +
                    ".ACCESS_BOOK_SERVICE");
            if (check == PackageManager.PERMISSION_DENIED) {
                return false;
            }

            String packagename = null;
            String[] packages = getPackageManager().getPackagesForUid(getCallingUid());
            if (packages != null && packages.length > 0) {
                packagename = packages[0];
            }
            if (packagename == null || !packagename.startsWith("com.edger")) {
                return false;
            }
            return super.onTransact(code, data, reply, flags);
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            // if (!mListeners.contains(listener)) {
            //     mListeners.add(listener);
            // } else {
            //     Log.d(TAG, "already exists.");
            // }
            // Log.d(TAG, "registerListener, size: " + mListeners.size());

            Log.d(TAG, "registerListener listener : " + listener);
            mListeners.register(listener);
            Log.d(TAG, "registerListener current size : " + mListeners.beginBroadcast());
            mListeners.finishBroadcast();
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            // if (mListeners.contains(listener)) {
            //     mListeners.remove(listener);
            //     Log.d(TAG, "unregister listener successfully.");
            // } else {
            //     Log.d(TAG, "not found, cannot register listener.");
            // }
            // Log.d(TAG, "unregisterListener, current size : " + mListeners.size());
            Log.d(TAG, "unregisterListener listener: " + listener);
            mListeners.unregister(listener);
            Log.d(TAG, "unregister successfully.");
            Log.d(TAG, "unregisterListener current size : " + mListeners.beginBroadcast());
            mListeners.finishBroadcast();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1, "Android"));
        mBookList.add(new Book(2, "iOS"));
        new Thread(new ServiceWorker()).start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        int check = checkCallingOrSelfPermission("com.edger.ipc.permission.ACCESS_BOOK_SERVICE");
        Log.d(TAG, "onBind check = " + check);
        if (check == PackageManager.PERMISSION_DENIED) {
            Log.d(TAG, "onBind: check failed.");
            return null;
        }
        return mBinder;
    }

    private void onNewBookArrived(Book book) throws RemoteException {
        mBookList.add(book);
        // Log.d(TAG, "onNewBookArrived, notify listeners: " + mListeners.size());
        // for (int i = 0; i < mListeners.size(); i++) {
        //     IOnNewBookArrivedListener listener = mListeners.get(i);
        //     Log.d(TAG, "onNewBookArrived, notify listener: " + listener);
        //     listener.onNewBookArrived(book);
        // }
        int n = mListeners.beginBroadcast();
        for (int i = 0; i < n; i++) {
            IOnNewBookArrivedListener listener = mListeners.getBroadcastItem(i);
            if (listener != null) {
                listener.onNewBookArrived(book);
            }
        }
        mListeners.finishBroadcast();
    }

    private class ServiceWorker implements Runnable {

        @Override
        public void run() {
            while (!mIsServiceDestroyed.get()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int bookId = mBookList.size() + 1;
                Book newBook = new Book(bookId, "new book#" + bookId);
                try {
                    onNewBookArrived(newBook);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
