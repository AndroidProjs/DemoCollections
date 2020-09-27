package com.edger.ipc.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BookProvider extends ContentProvider {

    private static final String TAG = "BookProvider";

    public static final String AUTHORYITY = "com.edger.ipc.provider";

    public static final Uri BOOK_CONTENT_URI = Uri.parse("content://" + AUTHORYITY + "/book");
    public static final Uri USER_CONTENT_URI = Uri.parse("content://" + AUTHORYITY + "/user");

    public static final int BOOK_URI_CODE = 0;
    public static final int USER_URI_CODE = 1;

    public static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORYITY, "book", BOOK_URI_CODE);
        URI_MATCHER.addURI(AUTHORYITY, "user", USER_URI_CODE);
    }

    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate, current Thread : " + Thread.currentThread().getName());
        context = getContext();
        initProviderData();
        return false;
    }

    private void initProviderData() {
        sqLiteDatabase = new DbOpenHelper(context).getWritableDatabase();
        sqLiteDatabase.execSQL("delete from " + DbOpenHelper.BOOK_TABLE_NAME);
        sqLiteDatabase.execSQL("delete from " + DbOpenHelper.USER_TABLE_NAME);
        sqLiteDatabase.execSQL("insert into book values(3, 'Android');");
        sqLiteDatabase.execSQL("insert into book values(4, 'iOS');");
        sqLiteDatabase.execSQL("insert into book values(5, 'Html5');");
        sqLiteDatabase.execSQL("insert into user values(1, 'jake', 1);");
        sqLiteDatabase.execSQL("insert into user values(2, 'jasmine', 0);");
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection, @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        Log.d(TAG, "query, current thread : " + Thread.currentThread().getName());
        String table = getTableName(uri);
        checkTableName(table, uri);
        return sqLiteDatabase.query(table, projection, selection, selectionArgs, null, sortOrder,
                null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.d(TAG, "getType: ");
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.d(TAG, "insert: ");
        String table = getTableName(uri);
        checkTableName(table, uri);
        sqLiteDatabase.insert(table, null, values);
        context.getContentResolver().notifyChange(uri, null);
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        Log.d(TAG, "delete: ");
        String table = getTableName(uri);
        checkTableName(table, uri);
        int count = sqLiteDatabase.delete(table, selection, selectionArgs);
        if (count > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
            return count;
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values,
                      @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "update: ");
        String table = getTableName(uri);
        checkTableName(table, uri);
        int row = sqLiteDatabase.update(table, values, selection, selectionArgs);
        if (row > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return row;
    }

    private void checkTableName(String table, Uri uri) {
        if (table == null) {
            throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    private String getTableName(Uri uri) {
        String tableName = null;
        switch (URI_MATCHER.match(uri)) {
            case BOOK_URI_CODE:
                tableName = DbOpenHelper.BOOK_TABLE_NAME;
                break;
            case USER_URI_CODE:
                tableName = DbOpenHelper.USER_TABLE_NAME;
                break;
            default:
                break;
        }
        return tableName;
    }
}
