package weight.manager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import jp.dip.sys1.android.drumpicker.lib.DateDrumPicker;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SqlAdapter extends BaseAdapter {

	private static final String DB_NAME = "body_weights.sqlite3";
	private static final String TABLE_NAME = "weights";
	private static final int DB_VESION = 3;
	// Для удобства выполнения sql-запросов
	// создадим константы с именами полей таблицы
	// и номерами соответсвующих столбцов
	private static final String KEY_ID = "_id";
	private static final int ID_COLUMN = 0;
	private static final String KEY_NAME = "date";
	private static final String KEY_NAME1 = "weight";
	private static final String KEY_NAME2 = "waistsize";
	private static final String KEY_NAME3 = "Chest";
	private static final String KEY_NAME4 = "Hip";
	private static final String KEY_NAME5 = "Neck";
	private static final int NAME_COLUMN = 1;
	private static final int NAME_COLUMN2 = 2;
	private static final int NAME_COLUMN3 = 3;
	private static final int NAME_COLUMN4 = 4;
	private static final int NAME_COLUMN5 = 5;
	private static final int NAME_COLUMN6 = 6;

	private Cursor cursor;
	private static SQLiteDatabase database;
	private DbOpenHelper dbOpenHelper;
	private Context context;
	
	private SimpleDateFormat formatter;
    

	//Далее следуют обязательные к перегрузке методы адаптера
	
	public SqlAdapter(Context context) {
		super();
		this.context = context;
		init();
	}

	public long getItemId(int position) {
		weight weightOnPosition = getItem(position);
		return weightOnPosition.getId();
	}

	static class ViewHolder {
		protected TextView text;
		protected TextView text1;
	}
	
/////////////////////
	public View getView(int position, View convertView, ViewGroup parrent) {
	
		View row;

		if (null == convertView) {
			row = View.inflate(context, R.layout.linearlayout_list_item, null);
		} else {
			row = convertView;
		}
 
//		TextView tv = (TextView) row.findViewById(android.R.id.text1);
//		tv.setText(getItem(position));
		
		
		TextView textView1 = (TextView) row.findViewById(R.id.textView1);   //Weight
		TextView textView2 = (TextView) row.findViewById(R.id.textView2);   //date
		TextView textView3 = (TextView) row.findViewById(R.id.textView3);   //Waist_size
		TextView textView4 = (TextView) row.findViewById(R.id.textView4);   //After Weight
		TextView textView7 = (TextView) row.findViewById(R.id.textView7);   //After Waist_size
		
		TextView textView9 = (TextView) row.findViewById(R.id.textView9);    //Chest
		TextView textView10 = (TextView) row.findViewById(R.id.textView10);  //Hip
		
		TextView textView12 = (TextView) row.findViewById(R.id.textView12);  //After Chest
		TextView textView14 = (TextView) row.findViewById(R.id.textView14);  //After Hip
		
		TextView textView15 = (TextView) row.findViewById(R.id.textView15);  //Neck
		TextView textView16 = (TextView) row.findViewById(R.id.textView16);  //After Neck
		
		
		
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(getItem(position).getDate());
	    Date date = cal.getTime();
//	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	    

	    if(DateDrumPicker.DateFormat111 == 1){
	    	formatter = new SimpleDateFormat("MM-dd-yyyy");
	    }else if(DateDrumPicker.DateFormat111 == 2){
	    	formatter = new SimpleDateFormat("dd-MM-yyyy");
	    }else{
	    	formatter = new SimpleDateFormat("yyyy-MM-dd");
	    }
	    
//	    java.text.DateFormat formatter =
//	    	    android.text.format.DateFormat.getDateFormat(context);
	    
	    String dateStr = formatter.format(date);
		
		textView1.setText(getItem(position).getWeight() + "");
//		textView.setText(getItem(position).getWeight() + " " + WeightManagerActivity.publicWeight1);
//		textView1.setText(getItem(position).getDate() + "");
		textView2.setText(dateStr + "");
		
		
		double Waist = getItem(position).getWaistSize();
	    int Waist1 = (int) Math.round(Waist);
	    double Waist11 = Waist1;
	    Waist11 = Waist11 / 10;
		textView3.setText(Waist11 + "");
//		textView2.setText(Waist11 + " " + WeightManagerActivity.publicWaistSize1);
		
		
		
		
		double Chest = getItem(position).getChest();
	    int Chest1 = (int) Math.round(Chest);
	    double Chest11 = Chest1;
	    Chest11 = Chest11 / 10;
		textView9.setText(Chest11 + "");
		
		
		
		double Hip = getItem(position).getHip();
	    int Hip1 = (int) Math.round(Hip);
	    double Hip11 = Hip1;
	    Hip11 = Hip11 / 10;		
		textView10.setText(Hip11 + "");
		
		
		double Neck = getItem(position).getNeck();
	    int Neck1 = (int) Math.round(Neck);
	    double Neck11 = Neck1;
	    Neck11 = Neck11 / 10;		
		textView15.setText(Neck11 + "");		
		
		
		if(position > 0){
		    double weight1 =  Double.parseDouble(getItem(position).getWeight());
		    weight1 = weight1 * 10;
		    int weight11 = (int) weight1;
		    double weight2 =  Double.parseDouble(getItem(position - 1).getWeight());
		    weight2 = weight2 * 10;
		    int weight22 = (int) weight2;
		    double weight3 =  weight11 - weight22;
		    weight3 = weight3 / 10;
		//    int weight111 = (int) Math.round(weight3);
		    if (weight3 > 0){
		    	textView4.setText("  (+" + weight3 + ") " + WeightManagerActivity.publicWeight1);
		    	textView4.setTextColor(Color.RED);
		    }else{
		    	textView4.setText("  (" + weight3 + ") " + WeightManagerActivity.publicWeight1);
		    	textView4.setTextColor(Color.argb(255, 0, 110, 30));
		    }

		    
		    double WaistSize1 =  getItem(position).getWaistSize();
		    double WaistSize2 =  getItem(position - 1).getWaistSize();
		    double WaistSize3 =  WaistSize1 - WaistSize2;
		    
		    int WaistSize33 = (int) Math.round(WaistSize3);
		    double WaistSize333 = WaistSize33;
		    WaistSize333 = WaistSize333 / 10;		    
		    WaistSize3 = WaistSize333;
		    
		    if (WaistSize3 > 0){
		    	textView7.setText("  (+" + WaistSize3 + ") " + WeightManagerActivity.publicWaistSize1);
		    	textView7.setTextColor(Color.RED);
		    }else{
		    	textView7.setText("  (" + WaistSize3 + ") " + WeightManagerActivity.publicWaistSize1);
		    	textView7.setTextColor(Color.argb(255, 0, 110, 30));
		    }
		    

		    
		    double Chest9 = getItem(position).getChest() - getItem(position - 1).getChest();
		    int Chest93 = (int) Math.round(Chest9);
		    double Chest933 = Chest93;
		    Chest933 = Chest933 / 10;		    
		    
		    if (Chest933 > 0){
		    	textView12.setText("  (+" + Chest933 + ") " + WeightManagerActivity.publicWaistSize1);
		    	textView12.setTextColor(Color.RED);
		    }else{
		    	textView12.setText("  (" + Chest933 + ") " + WeightManagerActivity.publicWaistSize1);
		    	textView12.setTextColor(Color.argb(255, 0, 110, 30));
		    }
		    
		    
		    double Hip9 = getItem(position).getHip() - getItem(position - 1).getHip();
		    int Hip93 = (int) Math.round(Hip9);
		    double Hip933 = Hip93;
		    Hip933 = Hip933 / 10;
		    
		    
		    if (Hip933 > 0){
		    	textView14.setText("  (+" + Hip933 + ") " + WeightManagerActivity.publicWaistSize1);
		    	textView14.setTextColor(Color.RED);
		    }else{
		    	textView14.setText("  (" + Hip933 + ") " + WeightManagerActivity.publicWaistSize1);
		    	textView14.setTextColor(Color.argb(255, 0, 110, 30));
		    }		    
		    
		    double Neck9 = getItem(position).getNeck() - getItem(position - 1).getNeck();
		    int Neck93 = (int) Math.round(Neck9);
		    double Neck933 = Neck93;
		    Neck933 = Neck933 / 10;
		    
		    
		    if (Neck933 > 0){
		    	textView16.setText("  (+" + Neck933 + ") " + WeightManagerActivity.publicWaistSize1);
		    	textView16.setTextColor(Color.RED);
		    }else{
		    	textView16.setText("  (" + Neck933 + ") " + WeightManagerActivity.publicWaistSize1);
		    	textView16.setTextColor(Color.argb(255, 0, 110, 30));
		    }			    
		    
		    
			
		}else if (position == 0){
			textView4.setText("  ");
			textView7.setText("  ");
		}
 
		return row;
		
		
		
		
		
/*		
	//	TextView textView;
		LinearLayout LinearLayout; 
		if (convertView == null) {
	//		textView = (TextView) View.inflate(context, R.layout.list_item,
	//				null);
			LinearLayout = (LinearLayout) View.inflate(context, R.layout.linearlayout_list_item, null);
			TextView textView = (TextView) LinearLayout.findViewById(R.id.textView1);
			TextView textView1 = (TextView) LinearLayout.findViewById(R.id.textView2);
			TextView textView2 = (TextView) LinearLayout.findViewById(R.id.textView3);
			
			
			
///////
//			Calendar cal = Calendar.getInstance();
//			cal.setTimeInMillis(getItem(position).getDate());
//		    Date date = cal.getTime();
//		    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//		    String dateStr = formatter.format(date);
///////

			textView.setText(getItem(position).getWeight() + " - " + position);
			textView1.setText(getItem(position).getDate() + "");
//			textView1.setText(dateStr + "");
			textView2.setText(getItem(position).getWaistSize() + "");
			
		} else {
	//		textView = (TextView) convertView;
			LinearLayout = (LinearLayout) convertView;
		}
		
		
		
	//	textView.setText(getItem(position).getWeight() + "---" + getItem(position).getDate() + "");
		
	//	return textView;
		return LinearLayout;
		
*/		

		
		
	}
	
////////////////////////////////////	

	public int getCount() {
		return cursor.getCount();
	}

	public weight getItem(int position) {
		if (cursor.moveToPosition(position)) {
			long id = cursor.getLong(ID_COLUMN);
			long date = cursor.getLong(NAME_COLUMN);
			String weight = cursor.getString(NAME_COLUMN2);
			long waistsize = cursor.getLong(NAME_COLUMN3);
			long Chest = cursor.getLong(NAME_COLUMN4);
			long Hip = cursor.getLong(NAME_COLUMN5);
			long Neck = cursor.getLong(NAME_COLUMN6);
			weight weightOnPositon = new weight(id, date, weight, waistsize, Chest, Hip, Neck);
			
			
			return weightOnPositon;
		} else {
			throw new CursorIndexOutOfBoundsException(
					"Cant move cursor on postion");
		}
	}
	
	//Методы для работы с базой данных
	
	
///	Cursor cursor = db.query(...);
///	while (cursor.moveToNext()) {
///	    // use cursor
///	}	
	
	
	public static void deleteAll()
	{
	    database.delete(TABLE_NAME, null, null);

	}
	
	

	public static Cursor getAllEntries() {
		//Список колонок базы, которые следует включить в результат
		String[] columnsToTake = { KEY_ID, KEY_NAME, KEY_NAME1, KEY_NAME2, KEY_NAME3, KEY_NAME4, KEY_NAME5};
		// составляем запрос к базе
		return database.query(TABLE_NAME, columnsToTake,
				null, null, null, null, KEY_NAME);
	}

	public long addItem(weight weight) {
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, weight.getDate());
		values.put(KEY_NAME1, weight.getWeight());
		values.put(KEY_NAME2, weight.getWaistSize());
		values.put(KEY_NAME3, weight.getChest());
		values.put(KEY_NAME4, weight.getHip());
		values.put(KEY_NAME5, weight.getNeck());
		long id = database.insert(TABLE_NAME, null, values);
		refresh();
		return id;
	}

	public boolean removeItem(weight weightToRemove) {
		boolean isDeleted = (database.delete(TABLE_NAME, KEY_NAME + "=?",
				new String[] { weightToRemove.getDate() + "" })) > 0;
		refresh();
		return isDeleted;
	}
	

	public boolean updateItem(weight weightToEdit, String newWeight, int newWaistSize, int newChest, int newHip, int newNeck) {
		
		ContentValues values = new ContentValues();
		values.put(KEY_NAME1, newWeight);
		values.put(KEY_NAME2, newWaistSize);
		values.put(KEY_NAME3, newChest);
		values.put(KEY_NAME4, newHip);
		values.put(KEY_NAME5, newNeck);
		boolean isUpdated = (database.update(TABLE_NAME, values, KEY_NAME + "=?",
				new String[] { weightToEdit.getDate() + "" })) > 0;
		
		refresh();
		return isUpdated;
	}
	
	public boolean updateItem1(long dateToEdit, String newWeight, int newWaistSize, int newChest, int newHip, int newNeck) {
		
		ContentValues values = new ContentValues();
		values.put(KEY_NAME1, newWeight);
		values.put(KEY_NAME2, newWaistSize);
		values.put(KEY_NAME3, newChest);
		values.put(KEY_NAME4, newHip);
		values.put(KEY_NAME5, newNeck);
		boolean isUpdated = (database.update(TABLE_NAME, values, KEY_NAME + "=?",
				new String[] { dateToEdit + "" })) > 0;
		
		refresh();
		return isUpdated;
	}
/*	
	public Cursor checkExists(long checkExistsWeight) {
		//Список колонок базы, которые следует включить в результат
		String[] columnsToTake = { KEY_ID, KEY_NAME, KEY_NAME1, KEY_NAME2, KEY_NAME3, KEY_NAME4 };
		// составляем запрос к базе
		
		Cursor data = database.query(TABLE_NAME, columnsToTake,
				KEY_NAME + "=" + "?", new String[]{checkExistsWeight + ""}, null, null, null);
		if (data.moveToFirst()) {
			// record exists
			return data;
		} else {
			// record not found
			return null;
		}
	}
*/
	
	public weight checkExists(long checkExistsWeight) {
		//Список колонок базы, которые следует включить в результат
		String[] columnsToTake = { KEY_ID, KEY_NAME, KEY_NAME1, KEY_NAME2, KEY_NAME3, KEY_NAME4, KEY_NAME5 };
		// составляем запрос к базе
		
		Cursor data = database.query(TABLE_NAME, columnsToTake,
				KEY_NAME + "=" + "?", new String[]{checkExistsWeight + ""}, null, null, null);
		if (data.moveToFirst()) {
			long id = data.getLong(ID_COLUMN);
			long date = data.getLong(NAME_COLUMN);
			String weight = data.getString(NAME_COLUMN2);
			long waistsize = data.getLong(NAME_COLUMN3);
			long Chest = data.getLong(NAME_COLUMN4);
			long Hip = data.getLong(NAME_COLUMN5);
			long Neck = data.getLong(NAME_COLUMN6);
			weight weightOnPositon = new weight(id, date, weight, waistsize, Chest, Hip, Neck);
			
			data.close();
			return weightOnPositon;
		} else {
			// record not found
			data.close();
			return null;
		}
		
		
		
	}	
	
	
	
	public boolean checkExists1(long checkExistsWeight) {
		//Список колонок базы, которые следует включить в результат
		String[] columnsToTake = { KEY_ID, KEY_NAME, KEY_NAME1, KEY_NAME2, KEY_NAME3, KEY_NAME4, KEY_NAME5 };
		// составляем запрос к базе
		
		Cursor data = database.query(TABLE_NAME, columnsToTake,
				KEY_NAME + "=" + "?", new String[]{checkExistsWeight + ""}, null, null, null);
		if (data.moveToFirst()) {
			// record exists
		//	return data;
			data.close();
			return true;
		} else {
			// record not found
		//	return null;
			data.close();
			return false;
		}
		
		
		
	}	
	
	
	//Прочие служебные методы

	public void onDestroy() {
		
		if (cursor != null && !cursor.isClosed()) {
		//	  cursor.close();
		}		
		
		if (dbOpenHelper != null) {
		//	dbOpenHelper.close();
		}

		if (database != null) {
		//	database.close();
		}
	}
	


	//Вызывает обновление вида
	public void refresh() {
		cursor = getAllEntries();
		notifyDataSetChanged();
	}
	


	// Инициализация адаптера: открываем базу и создаем курсор
	private void init() {
		dbOpenHelper = new DbOpenHelper(context, DB_NAME, null, DB_VESION);
		try {
			database = dbOpenHelper.getWritableDatabase();
		} catch (SQLException e) {
			// /Если база не открылась, то дальше нам дороги нет
			// но это особый случай
			Log.e(this.toString(), "Error while getting database");
			throw new Error("The end");
		}
		cursor = getAllEntries();
	}

	// Класс-помошник отвечающий за создание/отктрытие
	// базы и осуществляющий контроль ее версий
	private static class DbOpenHelper extends SQLiteOpenHelper {

		public DbOpenHelper(Context context, String weight,
				CursorFactory factory, int version) {
			super(context, weight, factory, version);
		}

		@Override
		// Вызывается при создании базы на устройстве
		public void onCreate(SQLiteDatabase db) {
			// Посроим стандартный sql-запрос для создания таблицы
			final String CREATE_DB = "CREATE TABLE " + TABLE_NAME + " ("
					+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ KEY_NAME + " LONG NOT NULL, "
					+ KEY_NAME1 + " TEXT NOT NULL, "
					
					+ KEY_NAME2 + " LONG NOT NULL, "
					+ KEY_NAME3 + " LONG NOT NULL, "
					+ KEY_NAME4 + " LONG NOT NULL, "
					
					+ KEY_NAME5 + " LONG NOT NULL);";
//					+ KEY_NAME1 + " LONG NOT NULL);";
			db.execSQL(CREATE_DB);
		}

		@Override
		// Метод будет вызван, если изменится версия базы
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// Тут можно организовать миграцию данных из старой базы в новую
			// или просто "выбросить" таблицу и создать заново
		//	db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		//	onCreate(db);
			
			   if ((newVersion == 2) && (oldVersion == 1)) {
			        db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + KEY_NAME3 + " LONG DEFAULT 0");
			        db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + KEY_NAME4 + " LONG DEFAULT 0");
			   }else if((newVersion == 3) && (oldVersion == 1)){
			        db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + KEY_NAME3 + " LONG DEFAULT 0");
			        db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + KEY_NAME4 + " LONG DEFAULT 0");				   
			        db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + KEY_NAME5 + " LONG DEFAULT 0");
			   }else if((newVersion == 3) && (oldVersion == 2)){
				   db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + KEY_NAME5 + " LONG DEFAULT 0");
			   }
			
		}
	}



}
