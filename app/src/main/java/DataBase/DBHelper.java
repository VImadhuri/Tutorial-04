package DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UserInfo.db";

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE" + UsersMaster.Users.TABLE_NAME+"(" +
                        UsersMaster.Users._ID + "INTEGER PRIMARY KEY,"+
                        UsersMaster.Users.COLUMN_NAME_USERNAME + "TEXT," +
                        UsersMaster.Users.COLUMN_NAME_PASSWORD + "TEXT)";
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //Add Info
    public long addInfo(String userName, String password){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UsersMaster.Users.COLUMN_NAME_USERNAME,userName);
        values.put(UsersMaster.Users.COLUMN_NAME_PASSWORD,password);

        long newRowId = db.insert(UsersMaster.Users.TABLE_NAME,null,values);
        return newRowId;
    }

    //Read All
    public List readAllInfo(){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                UsersMaster.Users._ID,
                UsersMaster.Users.COLUMN_NAME_USERNAME,
                UsersMaster.Users.COLUMN_NAME_PASSWORD
        };
        String selection =UsersMaster.Users.COLUMN_NAME_USERNAME + " = ?";
        String[] selectionArgs = {"Madu"};

        String sortOrder =UsersMaster.Users.COLUMN_NAME_USERNAME + "DESC";

        Cursor cursor = db.query(
                UsersMaster.Users.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        List userNames = new ArrayList<>();
        List passwords = new ArrayList<>();

        while(cursor.moveToNext()){
            String username = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_NAME_USERNAME));
            String passord = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_NAME_PASSWORD));
            userNames.add(username);
            passwords.add(passord);
        }
        cursor.close();
        return userNames;
    }

    public int deleteInfo(String userName){
        SQLiteDatabase db= getReadableDatabase();
        String selection =UsersMaster.Users.COLUMN_NAME_USERNAME + "LIKE ?";
        String[] selectionArgs = {userName};
        int deletedRows = db.delete(UsersMaster.Users.TABLE_NAME,selection,selectionArgs);
        return deletedRows;
    }
    public int updateInfo(String userName, String password){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(UsersMaster.Users.COLUMN_NAME_PASSWORD,password);

        String selection =UsersMaster.Users.COLUMN_NAME_USERNAME + "LIKE ?";
        String[] selectionArgs = {userName};

        int count = db.update(
                UsersMaster.Users.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
        return count;
    }
    public ArrayList signin (String username,String password){
        SQLiteDatabase db = getReadableDatabase();

        String selection =UsersMaster.Users.COLUMN_NAME_USERNAME+"LIKE'"+username+"'AND "+ UsersMaster.Users.COLUMN_NAME_PASSWORD+"LIKE'"+password+"'";

        Cursor cursor = db.query(
                UsersMaster.Users.TABLE_NAME,
                null,
                selection,
                null,
                null,
                null,
                null
        );
        ArrayList usernames = new ArrayList<>();
        while(cursor.moveToNext()){
            String user = cursor.getString(cursor.getColumnIndexOrThrow(UsersMaster.Users.COLUMN_NAME_USERNAME));
            usernames.add(user);
        }
        cursor.close();
        return usernames;
    }
}
