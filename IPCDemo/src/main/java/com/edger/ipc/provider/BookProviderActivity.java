package com.edger.ipc.provider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.edger.ipc.R;
import com.edger.ipc.binder.Book;
import com.edger.ipc.model.UserParcelable;

public class BookProviderActivity extends AppCompatActivity {

    private static final String TAG = "BookProviderActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_provider);

        // Uri uri = Uri.parse("content://com.edger.ipc.provider");
        // getContentResolver().query(uri, null, null, null, null);
        // getContentResolver().query(uri, null, null, null, null);
        // getContentResolver().query(uri, null, null, null, null);

        Uri bookUri = Uri.parse("content://com.edger.ipc.provider/book");
        ContentValues values = new ContentValues();
        values.put("_id", 6);
        values.put("name", "程序设计的艺术");
        getContentResolver().insert(bookUri, values);

        Cursor bookCursor = getContentResolver().query(bookUri,
                new String[]{"_id", "name"}, null, null, null);
        if (bookCursor != null) {
            while (bookCursor.moveToNext()) {
                Book book = new Book();
                book.bookId = bookCursor.getInt(0);
                book.bookName = bookCursor.getString(1);
                Log.d(TAG, "query book: " + book.toString());
            }
            bookCursor.close();
        }

        Uri userUri = Uri.parse("content://com.edger.ipc.book.provider/user");

        Cursor userCursor = getContentResolver().query(userUri,
                new String[]{"_id", "name", "sex"}, null, null, null);
        if (userCursor != null) {
            while (userCursor.moveToNext()) {
                UserParcelable user = new UserParcelable();
                user.userId = userCursor.getInt(0);
                user.userName = userCursor.getString(1);
                user.isMale = userCursor.getInt(2) == 1;
                Log.d(TAG, "query user: " + user.toString());
            }
            userCursor.close();
        }

    }
}