package com.example.newsapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.newsapp.models.User;
import com.example.newsapp.models.Article;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "news_app.db";
    private static final int DB_VERSION = 1;

    // Table names
    private static final String TABLE_USERS = "Users";
    private static final String TABLE_ARTICLES = "Articles";

    // Users columns
    private static final String USER_ID = "id";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";

    // Articles columns
    private static final String ARTICLE_ID = "id";
    private static final String TITLE = "title";
    private static final String CONTENT = "content";
    private static final String VIEWS = "views";

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng Users
        String createUserTable = "CREATE TABLE " + TABLE_USERS + "(" +
                USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                USERNAME + " TEXT UNIQUE," +
                PASSWORD + " TEXT," +
                ROLE + " TEXT)";
        db.execSQL(createUserTable);

        // Tạo bảng Articles
        String createArticleTable = "CREATE TABLE " + TABLE_ARTICLES + "(" +
                ARTICLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TITLE + " TEXT," +
                CONTENT + " TEXT," +
                VIEWS + " INTEGER)";
        db.execSQL(createArticleTable);

        // Dữ liệu mẫu
        db.execSQL("INSERT INTO " + TABLE_USERS + "(" + USERNAME + "," + PASSWORD + "," + ROLE + ") " +
                "VALUES('admin','admin123','admin')," +
                "('user1','123456','user')");

        db.execSQL("INSERT INTO " + TABLE_ARTICLES + "(" + TITLE + "," + CONTENT + "," + VIEWS + ") VALUES " +
                "('Tin công nghệ 1','Nội dung bài báo công nghệ số 1',10)," +
                "('Tin thể thao 2','Nội dung thể thao hấp dẫn',25)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa và tạo lại nếu nâng cấp
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARTICLES);
        onCreate(db);
    }

    // ====================== USER CRUD ======================

    public User checkLogin(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null,
                USERNAME + "=? AND " + PASSWORD + "=?",
                new String[]{username, password},
                null, null, null);

        if (cursor.moveToFirst()) {
            User user = new User(
                    cursor.getInt(cursor.getColumnIndexOrThrow(USER_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(USERNAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(PASSWORD)),
                    cursor.getString(cursor.getColumnIndexOrThrow(ROLE))
            );
            cursor.close();
            return user;
        }
        return null;
    }

    public boolean isUsernameExists(String username) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null,
                USERNAME + "=?", new String[]{username},
                null, null, null);
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }

    public boolean registerUser(User user) {
        if (isUsernameExists(user.getUsername())) return false;

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME, user.getUsername());
        values.put(PASSWORD, user.getPassword());
        values.put(ROLE, user.getRole());
        long result = db.insert(TABLE_USERS, null, values);
        return result != -1;
    }

    public boolean updatePassword(String username, String newPassword) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PASSWORD, newPassword);
        int result = db.update(TABLE_USERS, values, USERNAME + "=?", new String[]{username});
        return result > 0;
    }

    public String getPassword(String username) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[]{PASSWORD},
                USERNAME + "=?", new String[]{username},
                null, null, null);
        if (cursor.moveToFirst()) {
            String password = cursor.getString(0);
            cursor.close();
            return password;
        }
        return null;
    }

    // ====================== ARTICLE CRUD ======================

    public List<Article> getAllArticles() {
        List<Article> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_ARTICLES, null, null, null, null, null, ARTICLE_ID + " DESC");

        if (cursor.moveToFirst()) {
            do {
                Article article = new Article(
                        cursor.getInt(cursor.getColumnIndexOrThrow(ARTICLE_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(TITLE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(CONTENT)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(VIEWS))
                );
                list.add(article);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return list;
    }

    public List<Article> searchArticles(String keyword) {
        List<Article> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_ARTICLES, null,
                TITLE + " LIKE ?", new String[]{"%" + keyword + "%"},
                null, null, ARTICLE_ID + " DESC");

        if (cursor.moveToFirst()) {
            do {
                Article article = new Article(
                        cursor.getInt(cursor.getColumnIndexOrThrow(ARTICLE_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(TITLE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(CONTENT)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(VIEWS))
                );
                list.add(article);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return list;
    }

    public boolean addArticle(Article article) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE, article.getTitle());
        values.put(CONTENT, article.getContent());
        values.put(VIEWS, article.getViews());
        long result = db.insert(TABLE_ARTICLES, null, values);
        return result != -1;
    }

    public boolean updateArticle(Article article) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE, article.getTitle());
        values.put(CONTENT, article.getContent());
        values.put(VIEWS, article.getViews());
        int result = db.update(TABLE_ARTICLES, values, ARTICLE_ID + "=?",
                new String[]{String.valueOf(article.getId())});
        return result > 0;
    }

    public void deleteArticle(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_ARTICLES, ARTICLE_ID + "=?", new String[]{String.valueOf(id)});
    }

    public void increaseViewCount(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_ARTICLES + " SET " + VIEWS + " = " + VIEWS + " + 1 WHERE " + ARTICLE_ID + "=" + id);
    }

    // ====================== STATISTICS ======================

    public int getTotalArticles() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_ARTICLES, null);
        if (cursor.moveToFirst()) {
            int total = cursor.getInt(0);
            cursor.close();
            return total;
        }
        return 0;
    }

    public int getTotalViews() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + VIEWS + ") FROM " + TABLE_ARTICLES, null);
        if (cursor.moveToFirst()) {
            int total = cursor.getInt(0);
            cursor.close();
            return total;
        }
        return 0;
    }

    public int getTotalUsers() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_USERS, null);
        if (cursor.moveToFirst()) {
            int total = cursor.getInt(0);
            cursor.close();
            return total;
        }
        return 0;
    }
}
