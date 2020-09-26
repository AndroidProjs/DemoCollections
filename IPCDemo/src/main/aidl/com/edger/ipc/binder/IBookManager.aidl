package com.edger.ipc.binder;

import com.edger.ipc.binder.Book;
import com.edger.ipc.binder.IOnNewBookArrivedListener;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unregisterListener(IOnNewBookArrivedListener listener);
}
