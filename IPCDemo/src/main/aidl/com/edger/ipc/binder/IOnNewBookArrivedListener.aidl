package com.edger.ipc.binder;

import com.edger.ipc.binder.Book;

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}