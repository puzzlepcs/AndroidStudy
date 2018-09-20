package org.techtown.database.query;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 데이터베이스를 조회하는 방법을 알 수 있습니다.
 *
 * @author Mike
 *
 */
public class MainActivity extends AppCompatActivity {

    private TextView status;

    public static final String TAG = "MainActivity";

    private static String DATABASE_NAME = null;
    private static String TABLE_NAME = "employee";
    private static int DATABASE_VERSION = 1;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        status = (TextView) findViewById(R.id.status);
        final EditText input01 = (EditText) findViewById(R.id.input01);

        Button queryBtn = (Button) findViewById(R.id.queryBtn);
        queryBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                DATABASE_NAME = input01.getText().toString();
                boolean isOpen = openDatabase();
                if (isOpen) {
                    executeRawQuery();
                    executeRawQueryParam();
                }

            }
        });

    }

    private boolean openDatabase() {
        println("opening database [" + DATABASE_NAME + "].");

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        return true;
    }

    private void executeRawQuery() {
        println("\nexecuteRawQuery called.\n");

        Cursor c1 = db.rawQuery("select count(*) as Total from " + TABLE_NAME, null);
        println("cursor count : " + c1.getCount());

        c1.moveToNext();
        println("record count : " + c1.getInt(0));

        c1.close();

    }

    private void executeRawQueryParam() {
        println("\nexecuteRawQueryParam called.\n");

        String SQL = "select name, age, phone "
                + " from " + TABLE_NAME
                + " where age > ?";
        String[] args= {"30"};

        Cursor c1 = db.rawQuery(SQL, args);
        int recordCount = c1.getCount();
        println("cursor count : " + recordCount + "\n");

        for (int i = 0; i < recordCount; i++) {
            c1.moveToNext();
            String name = c1.getString(0);
            int age = c1.getInt(1);
            String phone = c1.getString(2);

            println("Record #" + i + " : " + name + ", " + age + ", " + phone);
        }

        c1.close();
    }

    private void executeRawQueryParam2() {
        println("\nexecuteRawQueryParam2 called.\n");

        String[] columns = {"name", "age", "phone"};
        String whereStr = "where age > ?";
        String[] whereParams = {"30"};

        // query() 메소드를 호출하여 데이터 쿼리
        Cursor c1 = db.query(TABLE_NAME, columns,
                whereStr, whereParams,
                null, null, null);

        int recordCount = c1.getCount();      // 커서의 getCount() 메소드를 이용해 레코드 개수 확인
        println("cursor count : " + recordCount + "\n");

        for (int i = 0; i < recordCount; i++) {
            c1.moveToNext();                // 다음 레코드로 이동
            String name = c1.getString(0);  //현재 레코드의 첫번째 문자열 확인
            int age = c1.getInt(1);
            String phone = c1.getString(2);

            println("Record #" + i + " : " + name + ", " + age + ", " + phone);
        }

        c1.close();                         // 커서 닫기
        

    }

    private void println(String msg) {
        Log.d(TAG, msg);
        status.append("\n" + msg);

    }


    private class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            println("creating table [" + TABLE_NAME + "].");

            try {
                String DROP_SQL = "drop table if exists " + TABLE_NAME;
                db.execSQL(DROP_SQL);
            } catch(Exception ex) {
                Log.e(TAG, "Exception in DROP_SQL", ex);
            }

            String CREATE_SQL = "create table " + TABLE_NAME + "("
                    + " _id integer PRIMARY KEY autoincrement, "
                    + " name text, "
                    + " age integer, "
                    + " phone text)";

            try {
                db.execSQL(CREATE_SQL);
            } catch(Exception ex) {
                Log.e(TAG, "Exception in CREATE_SQL", ex);
            }

            println("inserting records.");

            try {
                db.execSQL( "insert into " + TABLE_NAME + "(name, age, phone) values ('John', 20, '010-7788-1234');" );
                db.execSQL( "insert into " + TABLE_NAME + "(name, age, phone) values ('Mike', 35, '010-8888-1111');" );
                db.execSQL( "insert into " + TABLE_NAME + "(name, age, phone) values ('Sean', 26, '010-6677-4321');" );
            } catch(Exception ex) {
                Log.e(TAG, "Exception in insert SQL", ex);
            }

        }

        public void onOpen(SQLiteDatabase db) {
            println("opened database [" + DATABASE_NAME + "].");

        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ".");

        }
    }

}

// ContentValues: put()메소드 - 필드의 이름과 값을 넣을 수 있음.

private int insertRecordParam(String name) {
  println("inserting records using parameters");

  int count =1;

  ContentValues recordValues = new ContentValues(); // 객체 생성

  // ContentValues 객체에 파라미터 값 추가
  recordValues.put("name", "Rice");
  recordValues.put("age", 43);
  recordValues.put("phone", "010-3322-9876");

  int rowPosition = (int)db.insert(name, null, recordValues);   // insert()메소드 호출하여 레코드 추가

  return count;
}

private int updateRecordParam(String name) {
  println("updating record using parameters.");

  ContentValues recordValues = new ContentValues();

  recordValues.put("age", 43);
  String[] whereArgs = {"Rice"};
  // name 힐드의 값이 Rice인 경우만을 찾아 수정하도록 조건을 지정
  int rowAffected = db.update("employee", recordValues, "name = ?", whereArgs); // update() 메소드 호출하여 레코드 수정

  return rowAffected;
}

private int deleteRecordParam(String name) {
  println("delete records using parameters.");

  String whreArgs = {"Rice"};

  //  name 힐드의 값이 Rice인 경우만을 찾아 삭제하도록 조건을 지정
  int rowAffected = db.delete(name, "name=?", whereArgs);     // delete()메소드를 호출하여 레코드 삭제

  return rowAffected;
}
