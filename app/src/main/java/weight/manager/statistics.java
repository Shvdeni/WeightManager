package weight.manager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import jp.dip.sys1.android.drumpicker.lib.DateDrumPicker;
import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class statistics  extends Activity {
	
	
	
	TextView WeightWeek, WeightMonth, WeightMonth3, WeightMonth6, WeightYear, WeightSmolest, WeightLargest;
	TextView WaistWeek, WaistMonth, WaistMonth3, WaistMonth6, WaistYear, WaistSmolest, WaistLargest;
	TextView ChestWeek, ChestMonth, ChestMonth3, ChestMonth6, ChestYear, ChestSmolest, ChestLargest;
	TextView HipWeek, HipMonth, HipMonth3, HipMonth6, HipYear, HipSmolest, HipLargest;
	TextView NeckWeek, NeckMonth, NeckMonth3, NeckMonth6, NeckYear, NeckSmolest, NeckLargest;

	

	
	TextView Size_db, first_write, last_write, vsego;
	
	
	double BiggestWeight = 0, BiggestWaist = 0, BiggestChest = 0, BiggestHip = 0, BiggestNeck = 0;
	double SmolestWeight = 1000, SmolestWaist = 1000, SmolestChest = 1000, SmolestHip = 1000, SmolestNeck = 1000;

	long BiggestWeightMilis = 0, BiggestWaistMilis = 0, BiggestChestMilis = 0, BiggestHipMilis = 0, BiggestNeckMilis = 0;
	long SmolestWeightMilis = 0, SmolestWaistMilis = 0, SmolestChestMilis = 0, SmolestHipMilis = 0, SmolestNeckMilis = 0;
	
	SharedPreferences pref;
	private SimpleDateFormat formatter;
	
	long firstLong, LastLong;
	
	long firstYearLongWeight = 0, firstMonth6LongWeight = 0, firstMonth3LongWeight = 0, firstMonthLongWeight = 0, firstWeekLongWeight = 0;
	double firstYearWeight = 0, firstMonth6Weight = 0, firstMonth3Weight = 0, firstMonthWeight = 0, firstWeekWeight = 0;
	
	long firstYearLongWaist = 0, firstMonth6LongWaist = 0, firstMonth3LongWaist = 0, firstMonthLongWaist = 0, firstWeekLongWaist = 0;
	double firstYearWaist, firstMonth6Waist, firstMonth3Waist, firstMonthWaist, firstWeekWaist;
	
	long firstYearLongChest = 0, firstMonth6LongChest = 0, firstMonth3LongChest = 0, firstMonthLongChest = 0, firstWeekLongChest = 0;
	double firstYearChest, firstMonth6Chest, firstMonth3Chest, firstMonthChest, firstWeekChest;
	
	long firstYearLongHip = 0, firstMonth6LongHip = 0, firstMonth3LongHip = 0, firstMonthLongHip = 0, firstWeekLongHip = 0;
	double firstYearHip, firstMonth6Hip, firstMonth3Hip, firstMonthHip, firstWeekHip;
	
	long firstYearLongNeck = 0, firstMonth6LongNeck = 0, firstMonth3LongNeck = 0, firstMonthLongNeck = 0, firstWeekLongNeck = 0;
	double firstYearNeck, firstMonth6Neck, firstMonth3Neck, firstMonthNeck, firstWeekNeck;
	
	
	double lastWeight, lastWaist, lastChest, lastHip, lastNeck;
	
	long dateNowLong;
	
	
	long milisInDay = 86400000;
	
	Calendar cal;
	
	
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.statistics);
        
        cal = Calendar.getInstance();
        
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        
        
        WeightWeek = (TextView) findViewById(R.id.WeightWeek);
        WeightMonth = (TextView) findViewById(R.id.WeightMonth);
        WeightMonth3 = (TextView) findViewById(R.id.WeightMonth3);
        WeightMonth6 = (TextView) findViewById(R.id.WeightMonth6);
        WeightYear = (TextView) findViewById(R.id.WeightYear);
        WeightSmolest = (TextView) findViewById(R.id.WeightSmolest);
        WeightLargest = (TextView) findViewById(R.id.WeightLargest);        

        WaistWeek = (TextView) findViewById(R.id.WaistWeek);
        WaistMonth = (TextView) findViewById(R.id.WaistMonth);
        WaistMonth3 = (TextView) findViewById(R.id.WaistMonth3);
        WaistMonth6 = (TextView) findViewById(R.id.WaistMonth6);
        WaistYear = (TextView) findViewById(R.id.WaistYear);
        WaistSmolest = (TextView) findViewById(R.id.WaistSmolest);
        WaistLargest = (TextView) findViewById(R.id.WaistLargest);
        
        ChestWeek = (TextView) findViewById(R.id.ChestWeek);
        ChestMonth = (TextView) findViewById(R.id.ChestMonth);
        ChestMonth3 = (TextView) findViewById(R.id.ChestMonth3);
        ChestMonth6 = (TextView) findViewById(R.id.ChestMonth6);
        ChestYear = (TextView) findViewById(R.id.ChestYear);
        ChestSmolest = (TextView) findViewById(R.id.ChestSmolest);
        ChestLargest = (TextView) findViewById(R.id.ChestLargest);
        
        HipWeek = (TextView) findViewById(R.id.HipWeek);
        HipMonth = (TextView) findViewById(R.id.HipMonth);
        HipMonth3 = (TextView) findViewById(R.id.HipMonth3);
        HipMonth6 = (TextView) findViewById(R.id.HipMonth6);
        HipYear = (TextView) findViewById(R.id.HipYear);
        HipSmolest = (TextView) findViewById(R.id.HipSmolest);
        HipLargest = (TextView) findViewById(R.id.HipLargest);
        
        NeckWeek = (TextView) findViewById(R.id.NeckWeek);
        NeckMonth = (TextView) findViewById(R.id.NeckMonth);
        NeckMonth3 = (TextView) findViewById(R.id.NeckMonth3);
        NeckMonth6 = (TextView) findViewById(R.id.NeckMonth6);
        NeckYear = (TextView) findViewById(R.id.NeckYear);
        NeckSmolest = (TextView) findViewById(R.id.NeckSmolest);
        NeckLargest = (TextView) findViewById(R.id.NeckLargest);
        
        
        Size_db = (TextView) findViewById(R.id.Size_db);
        first_write = (TextView) findViewById(R.id.first_write);
        last_write = (TextView) findViewById(R.id.last_write);
        vsego = (TextView) findViewById(R.id.vsego);

        
        
        
		Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.HOUR_OF_DAY, 13);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 59);
		dateNowLong = cal.getTimeInMillis();        
        
        

        
    }
    
    
    
    
    
    
    
	protected void onResume() {
		super.onResume();

		
		

        
        
		
		
		
        Cursor  cursor = SqlAdapter.getAllEntries();
        
        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast() == false) {

    			long date1 = cursor.getLong(1);
    			
    			String weight = cursor.getString(2);
    			double weight1 =  Double.parseDouble(weight);
//    			weig1ht12 = weig1ht12 * 10;

    			
//    			int weig1ht1 = (int) weig1ht12;


    			double Waist = (cursor.getLong(3));
    			Waist = Waist / 10;
    			
    			double Chest = cursor.getLong(4);
    			Chest = Chest / 10;
    			
    			double Hip = cursor.getLong(5);
    			Hip = Hip / 10;
    			
    			double Neck = cursor.getLong(6);
    			Neck = Neck / 10;
    			

    			
    			
    			if(BiggestWeight < weight1){	BiggestWeight = weight1;	BiggestWeightMilis = date1;	}
    			if(BiggestWaist < Waist){		BiggestWaist = Waist;		BiggestWaistMilis = date1;		}
    			if(BiggestChest < Chest){		BiggestChest = Chest;		BiggestChestMilis = date1;		}
    			if(BiggestHip < Hip){			BiggestHip = Hip;			BiggestHipMilis = date1;			}
    			if(BiggestNeck < Neck){			BiggestNeck = Neck;			BiggestNeckMilis = date1;		}

    			
    			if(SmolestWeight > weight1 && weight1 > 0){	SmolestWeight = weight1;	SmolestWeightMilis = date1;	}
    			if(SmolestWaist > Waist && Waist > 0){		SmolestWaist = Waist;		SmolestWaistMilis = date1;	}
    			if(SmolestChest > Chest && Chest > 0){		SmolestChest = Chest;		SmolestChestMilis = date1;	}
    			if(SmolestHip > Hip && Hip > 0)	{			SmolestHip = Hip;			SmolestHipMilis = date1;		}
    			if(SmolestNeck > Neck && Neck > 0){			SmolestNeck = Neck;			SmolestNeckMilis = date1;	}

    			
    			
    			// Year
    			if(firstYearLongWeight == 0 && weight1 > 0 && date1 > (dateNowLong - (milisInDay * 365))&& date1 < (dateNowLong - (milisInDay * 180))){
    				firstYearLongWeight = date1;
    				firstYearWeight = weight1;
    			}
    			if(firstYearLongWaist == 0 && Waist > 0 && date1 > (dateNowLong - (milisInDay * 365))&& date1 < (dateNowLong - (milisInDay * 180))){
    				firstYearLongWaist = date1;
    				firstYearWaist = Waist;
    			}
    			if(firstYearLongChest == 0 && Chest > 0 && date1 > (dateNowLong - (milisInDay * 365))&& date1 < (dateNowLong - (milisInDay * 180))){
    				firstYearLongChest = date1;
    				firstYearChest = Chest;
    			}
    			if(firstYearLongHip == 0 && Hip > 0 && date1 > (dateNowLong - (milisInDay * 365))&& date1 < (dateNowLong - (milisInDay * 180))){
    				firstYearLongHip = date1;
    				firstYearHip = Hip;
    			}
    			if(firstYearLongNeck == 0 && Neck > 0 && date1 > (dateNowLong - (milisInDay * 365))&& date1 < (dateNowLong - (milisInDay * 180))){
    				firstYearLongNeck = date1;
    				firstYearNeck = Neck;
    			}
  			
    			

    			// Month6
    			if(firstMonth6LongWeight == 0 && weight1 > 0 && date1 > (dateNowLong - (milisInDay * 180))&& date1 < (dateNowLong - (milisInDay * 90))){
    				firstMonth6LongWeight = date1;
    				firstMonth6Weight = weight1;
    			}
    			if(firstMonth6LongWaist == 0 && Waist > 0 && date1 > (dateNowLong - (milisInDay * 180))&& date1 < (dateNowLong - (milisInDay * 90))){
    				firstMonth6LongWaist = date1;
    				firstMonth6Waist = Waist;
    			}
    			if(firstMonth6LongChest == 0 && Chest > 0 && date1 > (dateNowLong - (milisInDay * 180))&& date1 < (dateNowLong - (milisInDay * 90))){
    				firstMonth6LongChest = date1;
    				firstMonth6Chest = Chest;
    			}
    			if(firstMonth6LongHip == 0 && Hip > 0 && date1 > (dateNowLong - (milisInDay * 180))&& date1 < (dateNowLong - (milisInDay * 90))){
    				firstMonth6LongHip = date1;
    				firstMonth6Hip = Hip;
    			}
    			if(firstMonth6LongNeck == 0 && Neck > 0 && date1 > (dateNowLong - (milisInDay * 180))&& date1 < (dateNowLong - (milisInDay * 90))){
    				firstMonth6LongNeck = date1;
    				firstMonth6Neck = Neck;
    			}

    			
    			// Month3
    			if(firstMonth3LongWeight == 0 && weight1 > 0 && date1 > (dateNowLong - (milisInDay * 90))&& date1 < (dateNowLong - (milisInDay * 30))){
    				firstMonth3LongWeight = date1;
    				firstMonth3Weight = weight1;
    			}
    			if(firstMonth3LongWaist == 0 && Waist > 0 && date1 > (dateNowLong - (milisInDay * 90))&& date1 < (dateNowLong - (milisInDay * 30))){
    				firstMonth3LongWaist = date1;
    				firstMonth3Waist = Waist;
    			}
    			if(firstMonth3LongChest == 0 && Chest > 0 && date1 > (dateNowLong - (milisInDay * 90))&& date1 < (dateNowLong - (milisInDay * 30))){
    				firstMonth3LongChest = date1;
    				firstMonth3Chest = Chest;
    			}
    			if(firstMonth3LongHip == 0 && Hip > 0 && date1 > (dateNowLong - (milisInDay * 90))&& date1 < (dateNowLong - (milisInDay * 30))){
    				firstMonth3LongHip = date1;
    				firstMonth3Hip = Hip;
    			}
    			if(firstMonth3LongNeck == 0 && Neck > 0 && date1 > (dateNowLong - (milisInDay * 90))&& date1 < (dateNowLong - (milisInDay * 30))){
    				firstMonth3LongNeck = date1;
    				firstMonth3Neck = Neck;
    			}

    			
    			// Month
    			if(firstMonthLongWeight == 0 && weight1 > 0 && date1 > (dateNowLong - (milisInDay * 30))&& date1 < (dateNowLong - (milisInDay * 7))){
    				firstMonthLongWeight = date1;
    				firstMonthWeight = weight1;
    			}
    			if(firstMonthLongWaist == 0 && Waist > 0 && date1 > (dateNowLong - (milisInDay * 30))&& date1 < (dateNowLong - (milisInDay * 7))){
    				firstMonthLongWaist = date1;
    				firstMonthWaist = Waist;
    			}
    			if(firstMonthLongChest == 0 && Chest > 0 && date1 > (dateNowLong - (milisInDay * 30))&& date1 < (dateNowLong - (milisInDay * 7))){
    				firstMonthLongChest = date1;
    				firstMonthChest = Chest;
    			}
    			if(firstMonthLongHip == 0 && Hip > 0 && date1 > (dateNowLong - (milisInDay * 30))&& date1 < (dateNowLong - (milisInDay * 7))){
    				firstMonthLongHip = date1;
    				firstMonthHip = Hip;
    			}
    			if(firstMonthLongNeck == 0 && Neck > 0 && date1 > (dateNowLong - (milisInDay * 30))&& date1 < (dateNowLong - (milisInDay * 7))){
    				firstMonthLongNeck = date1;
    				firstMonthNeck = Neck;
    			}

    			
    			// Week
    			if(firstWeekLongWeight == 0 && weight1 > 0 && date1 > (dateNowLong - (milisInDay * 7))){
    				firstWeekLongWeight = date1;
    				firstWeekWeight = weight1;
    			}
    			if(firstWeekLongWaist == 0 && Waist > 0 && date1 > (dateNowLong - (milisInDay * 7))){
    				firstWeekLongWaist = date1;
    				firstWeekWaist = Waist;
    			}
    			if(firstWeekLongChest == 0 && Chest > 0 && date1 > (dateNowLong - (milisInDay * 7))){
    				firstWeekLongChest = date1;
    				firstWeekChest = Chest;
    			}
    			if(firstWeekLongHip == 0 && Hip > 0 && date1 > (dateNowLong - (milisInDay * 7))){
    				firstWeekLongHip = date1;
    				firstWeekHip = Hip;
    			}
    			if(firstWeekLongNeck == 0 && Neck > 0 && date1 > (dateNowLong - (milisInDay * 7))){
    				firstWeekLongNeck = date1;
    				firstWeekNeck = Neck;
    			}
		
    			
    			// find last pars
    			if(weight1 > 0 && date1 > (dateNowLong - (milisInDay * 7)) ){
    				lastWeight = weight1;
    			}
    			if(Waist > 0 && date1 > (dateNowLong - (milisInDay * 7)) ){
    				lastWaist = Waist;
    			}
    			if(Chest > 0 && date1 > (dateNowLong - (milisInDay * 7)) ){
    				lastChest = Chest;
    			}
    			if(Hip > 0 && date1 > (dateNowLong - (milisInDay * 7)) ){
    				lastHip = Hip;
    			}
    			if(Neck > 0 && date1 > (dateNowLong - (milisInDay * 7)) ){
    				lastNeck = Neck;
    			}
			
    			
    			
                cursor.moveToNext();
            }
        }
        
        Size_db.setText(cursor.getCount() + "");


        
        if(cursor.getCount() > 0){
        	cursor.moveToFirst();
        	
        	firstLong = cursor.getLong(1);
        	
			cal.setTimeInMillis(firstLong);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);
			first_write.setText(dateStr + "");
			
			
			cursor.moveToLast();
			
			LastLong = cursor.getLong(1);
			
			cal.setTimeInMillis(LastLong);
		    date = cal.getTime();
		    dateStr = formatter.format(date);
		    last_write.setText(dateStr + "");
		    
        }
        
        
        
        
        long DaysLong = LastLong - firstLong;
        if(DaysLong > 0){
        	vsego.setText((DaysLong / (24 * 60 * 60 * 1000)  ) + " " + getString(R.string.days));
        }else{
        	vsego.setText(0 + " " + getString(R.string.days));
        }
        

        
        
        

        if(SmolestWeight == 1000){	SmolestWeight = 0;	}
        if(SmolestWaist == 1000){	SmolestWaist = 0;	}
        if(SmolestChest == 1000){	SmolestChest = 0;	}
        if(SmolestHip == 1000)	{	SmolestHip = 0;	}
        if(SmolestNeck == 1000){	SmolestNeck = 0;	}

        
        if(SmolestWeight > 0){
        	
			cal.setTimeInMillis(SmolestWeightMilis);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);
        	
        	WeightSmolest.setText(SmolestWeight + "(" + dateStr + ")");	}
        if(BiggestWeight > 0){
        	
			cal.setTimeInMillis(BiggestWeightMilis);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);
        	
        	WeightLargest.setText(BiggestWeight + "(" + dateStr + ")");	}
        
        if(SmolestWaist > 0){
        	
			cal.setTimeInMillis(SmolestWaistMilis);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);        	
        	
        	WaistSmolest.setText(SmolestWaist + "(" + dateStr + ")");	}
        if(BiggestWaist > 0){
        	
			cal.setTimeInMillis(BiggestWaistMilis);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);        	
        	
        	WaistLargest.setText(BiggestWaist + "(" + dateStr + ")");	}
        
        if(SmolestChest > 0){
        	
			cal.setTimeInMillis(SmolestChestMilis);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);         	
        	
        	ChestSmolest.setText(SmolestChest + "(" + dateStr + ")");	}
        if(BiggestChest > 0){
        	
			cal.setTimeInMillis(BiggestChestMilis);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);        	
        	
        	ChestLargest.setText(BiggestChest + "(" + dateStr + ")");	}
        
        if(SmolestHip > 0){
        	
			cal.setTimeInMillis(SmolestHipMilis);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);         	
        	
        	HipSmolest.setText(SmolestHip + "(" + dateStr + ")");	}
        if(BiggestHip > 0){
        	
			cal.setTimeInMillis(BiggestHipMilis);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);        	
        	
        	HipLargest.setText(BiggestHip + "(" + dateStr + ")");	}
        
        if(SmolestNeck > 0){
        	
			cal.setTimeInMillis(SmolestNeckMilis);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);          	
        	
        	NeckSmolest.setText(SmolestNeck + "(" + dateStr + ")");	}
        if(BiggestNeck > 0){
        	
			cal.setTimeInMillis(BiggestNeckMilis);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);        	
        	
        	NeckLargest.setText(BiggestNeck + "(" + dateStr + ")");	}
        

        
        
        
        // Year
        if(firstYearLongWeight > 0){
			cal.setTimeInMillis(firstYearLongWeight);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);
        	
		    String StrlastWeight = "";
		    String StrDiffWeight = "";
		    
		    if(lastWeight > 0){
		    	StrlastWeight = "- " + lastWeight + "";
		    	
		    	double diffweight = firstYearWeight - lastWeight;
		    	diffweight = diffweight * 10;
			    int weight11 = (int) Math.round(diffweight);
			    double weight111 = weight11;
			    diffweight = weight111 / 10;		    	
		    	
			    if(diffweight > 0){
			    	StrDiffWeight = "( -" + diffweight + ")";
			    }else{
			    	StrDiffWeight = "( +" + Math.abs(diffweight) + ")";
			    }
			    
		    }
		    WeightYear.setText(firstYearWeight + "(" + dateStr + ") " + StrlastWeight + StrDiffWeight);
        }
        
        if(firstYearLongWaist > 0){
			cal.setTimeInMillis(firstYearLongWaist);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);
        	
		    String StrlastWaist = "";
		    String StrDiffWaist = "";
		    
		    if(lastWaist > 0){
		    	StrlastWaist = "- " + lastWaist + "";
		    	
		    	double diff = firstYearWaist - lastWaist;
		    	diff = diff * 10;
			    int diff11 = (int) Math.round(diff);
			    double diff111 = diff11;
			    diff = diff111 / 10;			    	
		    	
			    if(diff > 0){
			    	StrDiffWaist = "( -" + diff + ")";
			    }else{
			    	StrDiffWaist = "( +" + Math.abs(diff) + ")";
			    }
			    
		    }
		    WaistYear.setText(firstYearWaist + "(" + dateStr + ") " + StrlastWaist + StrDiffWaist);
        }
		
        if(firstYearLongChest > 0){
			cal.setTimeInMillis(firstYearLongChest);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);
        	
		    String StrlastChest = "";
		    String StrDiffChest = "";
		    
		    if(lastChest > 0){
		    	StrlastChest = "- " + lastChest + "";
		    	
		    	double diff = firstYearChest - lastChest;
		    	diff = diff * 10;
			    int diff11 = (int) Math.round(diff);
			    double diff111 = diff11;
			    diff = diff111 / 10;		    	
		    	
			    if(diff > 0){
			    	StrDiffChest = "( -" + diff + ")";
			    }else{
			    	StrDiffChest = "( +" + Math.abs(diff) + ")";
			    }
			    
		    }
		    ChestYear.setText(firstYearChest + "(" + dateStr + ") " + StrlastChest + StrDiffChest);
        }
        
        if(firstYearLongChest > 0){
			cal.setTimeInMillis(firstYearLongChest);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);
        	
		    String StrlastChest = "";
		    String StrDiffChest = "";
		    
		    if(lastChest > 0){
		    	StrlastChest = "- " + lastChest + "";
		    	
		    	double diff = firstYearChest - lastChest;
		    	diff = diff * 10;
			    int diff11 = (int) Math.round(diff);
			    double diff111 = diff11;
			    diff = diff111 / 10;		    	
		    	
			    if(diff > 0){
			    	StrDiffChest = "( -" + diff + ")";
			    }else{
			    	StrDiffChest = "( +" + Math.abs(diff) + ")";
			    }
			    
		    }
		    ChestYear.setText(firstYearChest + "(" + dateStr + ") " + StrlastChest + StrDiffChest);
        }        
        
        if(firstYearLongNeck > 0){
			cal.setTimeInMillis(firstYearLongNeck);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);
        	
		    String StrlastNeck = "";
		    String StrDiffNeck = "";
		    
		    if(lastNeck > 0){
		    	StrlastNeck = "- " + lastNeck + "";
		    	
		    	double diff = firstYearNeck - lastNeck;
		    	diff = diff * 10;
			    int diff11 = (int) Math.round(diff);
			    double diff111 = diff11;
			    diff = diff111 / 10;		    	
		    	
			    if(diff > 0){
			    	StrDiffNeck = "( -" + diff + ")";
			    }else{
			    	StrDiffNeck = "( +" + Math.abs(diff) + ")";
			    }
			    
		    }
		    NeckYear.setText(firstYearNeck + "(" + dateStr + ") " + StrlastNeck + StrDiffNeck);
        }        
        

        
        // Month6
        if(firstMonth6LongWeight > 0){
			cal.setTimeInMillis(firstMonth6LongWeight);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);
        	
		    String StrlastWeight = "";
		    String StrDiffWeight = "";
		    
		    if(lastWeight > 0){
		    	StrlastWeight = "- " + lastWeight + "";
		    	
		    	double diffweight = firstMonth6Weight - lastWeight;
		    	diffweight = diffweight * 10;
			    int weight11 = (int) Math.round(diffweight);
			    double weight111 = weight11;
			    diffweight = weight111 / 10;		    	
		    	
			    if(diffweight > 0){
			    	StrDiffWeight = "( -" + diffweight + ")";
			    }else{
			    	StrDiffWeight = "( +" + Math.abs(diffweight) + ")";
			    }
			    
		    }
		    WeightMonth6.setText(firstMonth6Weight + "(" + dateStr + ") " + StrlastWeight + StrDiffWeight);
        }
        
        if(firstMonth6LongWaist > 0){
			cal.setTimeInMillis(firstMonth6LongWaist);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);
        	
		    String StrlastWaist = "";
		    String StrDiffWaist = "";
		    
		    if(lastWaist > 0){
		    	StrlastWaist = "- " + lastWaist + "";
		    	
		    	double diff = firstMonth6Waist - lastWaist;
		    	diff = diff * 10;
			    int diff11 = (int) Math.round(diff);
			    double diff111 = diff11;
			    diff = diff111 / 10;		    	
		    	
			    if(diff > 0){
			    	StrDiffWaist = "( -" + diff + ")";
			    }else{
			    	StrDiffWaist = "( +" + Math.abs(diff) + ")";
			    }
			    
		    }
		    WaistMonth6.setText(firstMonth6Waist + "(" + dateStr + ") " + StrlastWaist + StrDiffWaist);
        }
		
        if(firstMonth6LongChest > 0){
			cal.setTimeInMillis(firstMonth6LongChest);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);
        	
		    String StrlastChest = "";
		    String StrDiffChest = "";
		    
		    if(lastChest > 0){
		    	StrlastChest = "- " + lastChest + "";
		    	
		    	double diff = firstMonth6Chest - lastChest;
		    	diff = diff * 10;
			    int diff11 = (int) Math.round(diff);
			    double diff111 = diff11;
			    diff = diff111 / 10;		    	
		    	
			    if(diff > 0){
			    	StrDiffChest = "( -" + diff + ")";
			    }else{
			    	StrDiffChest = "( +" + Math.abs(diff) + ")";
			    }
			    
		    }
		    ChestMonth6.setText(firstMonth6Chest + "(" + dateStr + ") " + StrlastChest + StrDiffChest);
        }
        
        if(firstMonth6LongChest > 0){
			cal.setTimeInMillis(firstMonth6LongChest);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);
        	
		    String StrlastChest = "";
		    String StrDiffChest = "";
		    
		    if(lastChest > 0){
		    	StrlastChest = "- " + lastChest + "";
		    	
		    	double diff = firstMonth6Chest - lastChest;
		    	diff = diff * 10;
			    int diff11 = (int) Math.round(diff);
			    double diff111 = diff11;
			    diff = diff111 / 10;		    	
		    	
			    if(diff > 0){
			    	StrDiffChest = "( -" + diff + ")";
			    }else{
			    	StrDiffChest = "( +" + Math.abs(diff) + ")";
			    }
			    
		    }
		    ChestMonth6.setText(firstMonth6Chest + "(" + dateStr + ") " + StrlastChest + StrDiffChest);
        }        
        
        if(firstMonth6LongNeck > 0){
			cal.setTimeInMillis(firstMonth6LongNeck);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);
        	
		    String StrlastNeck = "";
		    String StrDiffNeck = "";
		    
		    if(lastNeck > 0){
		    	StrlastNeck = "- " + lastNeck + "";
		    	
		    	double diff = firstMonth6Neck - lastNeck;
		    	diff = diff * 10;
			    int diff11 = (int) Math.round(diff);
			    double diff111 = diff11;
			    diff = diff111 / 10;
		    	
			    if(diff > 0){
			    	StrDiffNeck = "( -" + diff + ")";
			    }else{
			    	StrDiffNeck = "( +" + Math.abs(diff) + ")";
			    }
			    
		    }
		    NeckMonth6.setText(firstMonth6Neck + "(" + dateStr + ") " + StrlastNeck + StrDiffNeck);
        }        

     
     if(firstMonth3LongWaist > 0){
			cal.setTimeInMillis(firstMonth3LongWaist);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);
     	
		    String StrlastWaist = "";
		    String StrDiffWaist = "";
		    
		    if(lastWaist > 0){
		    	StrlastWaist = "- " + lastWaist + "";
		    	
		    	double diff = firstMonth3Waist - lastWaist;
		    	diff = diff * 10;
			    int diff11 = (int) Math.round(diff);
			    double diff111 = diff11;
			    diff = diff111 / 10;		    	
		    	
			    if(diff > 0){
			    	StrDiffWaist = "( -" + diff + ")";
			    }else{
			    	StrDiffWaist = "( +" + Math.abs(diff) + ")";
			    }
			    
		    }
		    WaistMonth3.setText(firstMonth3Waist + "(" + dateStr + ") " + StrlastWaist + StrDiffWaist);
     }
		
     if(firstMonth3LongChest > 0){
			cal.setTimeInMillis(firstMonth3LongChest);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);
     	
		    String StrlastChest = "";
		    String StrDiffChest = "";
		    
		    if(lastChest > 0){
		    	StrlastChest = "- " + lastChest + "";
		    	
		    	double diff = firstMonth3Chest - lastChest;
		    	diff = diff * 10;
			    int diff11 = (int) Math.round(diff);
			    double diff111 = diff11;
			    diff = diff111 / 10;		    	
		    	
			    if(diff > 0){
			    	StrDiffChest = "( -" + diff + ")";
			    }else{
			    	StrDiffChest = "( +" + Math.abs(diff) + ")";
			    }
			    
		    }
		    ChestMonth3.setText(firstMonth3Chest + "(" + dateStr + ") " + StrlastChest + StrDiffChest);
     }
     
     if(firstMonth3LongChest > 0){
			cal.setTimeInMillis(firstMonth3LongChest);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);
     	
		    String StrlastChest = "";
		    String StrDiffChest = "";
		    
		    if(lastChest > 0){
		    	StrlastChest = "- " + lastChest + "";
		    	
		    	double diff = firstMonth3Chest - lastChest;
		    	diff = diff * 10;
			    int diff11 = (int) Math.round(diff);
			    double diff111 = diff11;
			    diff = diff111 / 10;		    	
		    	
			    if(diff > 0){
			    	StrDiffChest = "( -" + diff + ")";
			    }else{
			    	StrDiffChest = "( +" + Math.abs(diff) + ")";
			    }
			    
		    }
		    ChestMonth3.setText(firstMonth3Chest + "(" + dateStr + ") " + StrlastChest + StrDiffChest);
     }        
     
     if(firstMonth3LongNeck > 0){
			cal.setTimeInMillis(firstMonth3LongNeck);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);
     	
		    String StrlastNeck = "";
		    String StrDiffNeck = "";
		    
		    if(lastNeck > 0){
		    	StrlastNeck = "- " + lastNeck + "";
		    	
		    	double diff = firstMonth3Neck - lastNeck;
		    	diff = diff * 10;
			    int diff11 = (int) Math.round(diff);
			    double diff111 = diff11;
			    diff = diff111 / 10;		    	
		    	
			    if(diff > 0){
			    	StrDiffNeck = "( -" + diff + ")";
			    }else{
			    	StrDiffNeck = "( +" + Math.abs(diff) + ")";
			    }
			    
		    }
		    NeckMonth3.setText(firstMonth3Neck + "(" + dateStr + ") " + StrlastNeck + StrDiffNeck);
     }        
 
     
        //  Month
        if(firstMonthLongWeight > 0){
			cal.setTimeInMillis(firstMonthLongWeight);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);
        	
		    String StrlastWeight = "";
		    String StrDiffWeight = "";
		    
		    if(lastWeight > 0){
		    	StrlastWeight = "- " + lastWeight + "";
		    	
		    	double diffweight = firstMonthWeight - lastWeight;
		    	
		    	diffweight = diffweight * 10;
			    int weight11 = (int) Math.round(diffweight);
			    double weight111 = weight11;
			    diffweight = weight111 / 10;
		    	
			    if(diffweight > 0){
			    	StrDiffWeight = "( -" + diffweight + ")";
			    }else{
			    	StrDiffWeight = "( +" + Math.abs(diffweight) + ")";
			    }
			    
		    }
		    WeightMonth.setText(firstMonthWeight + "(" + dateStr + ") " + StrlastWeight + StrDiffWeight);
        }
        
        if(firstMonthLongWaist > 0){
			cal.setTimeInMillis(firstMonthLongWaist);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);
        	
		    String StrlastWaist = "";
		    String StrDiffWaist = "";
		    
		    if(lastWaist > 0){
		    	StrlastWaist = "- " + lastWaist + "";
		    	
		    	double diff = firstMonthWaist - lastWaist;
		    	diff = diff * 10;
			    int diff11 = (int) Math.round(diff);
			    double diff111 = diff11;
			    diff = diff111 / 10;		    	
		    	
			    if(diff > 0){
			    	StrDiffWaist = "( -" + diff + ")";
			    }else{
			    	StrDiffWaist = "( +" + Math.abs(diff) + ")";
			    }
			    
		    }
		    WaistMonth.setText(firstMonthWaist + "(" + dateStr + ") " + StrlastWaist + StrDiffWaist);
        }

        if(firstMonthLongChest > 0){
			cal.setTimeInMillis(firstMonthLongChest);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);
        	
		    String StrlastChest = "";
		    String StrDiffChest = "";
		    
		    if(lastChest > 0){
		    	StrlastChest = "- " + lastChest + "";
		    	
		    	double diff = firstMonthChest - lastChest;
		    	diff = diff * 10;
			    int diff11 = (int) Math.round(diff);
			    double diff111 = diff11;
			    diff = diff111 / 10;		    	
		    	
			    if(diff > 0){
			    	StrDiffChest = "( -" + diff + ")";
			    }else{
			    	StrDiffChest = "( +" + Math.abs(diff) + ")";
			    }
			    
		    }
		    ChestMonth.setText(firstMonthChest + "(" + dateStr + ") " + StrlastChest + StrDiffChest);
        }
        
        if(firstMonthLongHip > 0){
			cal.setTimeInMillis(firstMonthLongHip);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);
        	
		    String StrlastHip = "";
		    String StrDiffHip = "";
		    
		    if(lastHip > 0){
		    	StrlastHip = "- " + lastHip + "";
		    	
		    	double diff = firstMonthHip - lastHip;
		    	diff = diff * 10;
			    int diff11 = (int) Math.round(diff);
			    double diff111 = diff11;
			    diff = diff111 / 10;		    	
		    	
			    if(diff > 0){
			    	StrDiffHip = "( -" + diff + ")";
			    }else{
			    	StrDiffHip = "( +" + Math.abs(diff) + ")";
			    }
			    
		    }
		    HipMonth.setText(firstMonthHip + "(" + dateStr + ") " + StrlastHip + StrDiffHip);
        }        
        
        if(firstMonthLongNeck > 0){
			cal.setTimeInMillis(firstMonthLongNeck);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);
        	
		    String StrlastNeck = "";
		    String StrDiffNeck = "";
		    
		    if(lastNeck > 0){
		    	StrlastNeck = "- " + lastNeck + "";
		    	
		    	double diff = firstMonthNeck - lastNeck;
		    	diff = diff * 10;
			    int diff11 = (int) Math.round(diff);
			    double diff111 = diff11;
			    diff = diff111 / 10;		    	
		    	
			    if(diff > 0){
			    	StrDiffNeck = "( -" + diff + ")";
			    }else{
			    	StrDiffNeck = "( +" + Math.abs(diff) + ")";
			    }
			    
		    }
		    NeckMonth.setText(firstMonthNeck + "(" + dateStr + ") " + StrlastNeck + StrDiffNeck);
        }        

        // Week
        if(firstWeekLongWeight > 0){
			cal.setTimeInMillis(firstWeekLongWeight);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);
        	
		    String StrlastWeight = "";
		    String StrDiffWeight = "";
		    
		    if(lastWeight > 0){
		    	StrlastWeight = "- " + lastWeight + "";
		    	
		    	double diffweight = firstWeekWeight - lastWeight;
		    	diffweight = diffweight * 10;
			    int weight11 = (int) Math.round(diffweight);
			    double weight111 = weight11;
			    diffweight = weight111 / 10;		    	
		    	
			    if(diffweight > 0){
			    	StrDiffWeight = "( -" + diffweight + ")";
			    }else{
			    	StrDiffWeight = "( +" + Math.abs(diffweight) + ")";
			    }
			    
		    }
		    WeightWeek.setText(firstWeekWeight + "(" + dateStr + ") " + StrlastWeight + StrDiffWeight);
        }
        
        if(firstWeekLongWaist > 0){
			cal.setTimeInMillis(firstWeekLongWaist);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);
        	
		    String StrlastWaist = "";
		    String StrDiffWaist = "";
		    
		    if(lastWaist > 0){
		    	StrlastWaist = "- " + lastWaist + "";
		    	
		    	double diff = firstWeekWaist - lastWaist;
		    	diff = diff * 10;
			    int diff11 = (int) Math.round(diff);
			    double diff111 = diff11;
			    diff = diff111 / 10;		    	
		    	
			    if(diff > 0){
			    	StrDiffWaist = "( -" + diff + ")";
			    }else{
			    	StrDiffWaist = "( +" + Math.abs(diff) + ")";
			    }
			    
		    }
		    WaistWeek.setText(firstWeekWaist + "(" + dateStr + ") " + StrlastWaist + StrDiffWaist);
        }
		
        if(firstWeekLongChest > 0){
			cal.setTimeInMillis(firstWeekLongChest);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);
        	
		    String StrlastChest = "";
		    String StrDiffChest = "";
		    
		    if(lastChest > 0){
		    	StrlastChest = "- " + lastChest + "";
		    	
		    	double diff = firstWeekChest - lastChest;
		    	diff = diff * 10;
			    int diff11 = (int) Math.round(diff);
			    double diff111 = diff11;
			    diff = diff111 / 10;		    	
		    	
			    if(diff > 0){
			    	StrDiffChest = "( -" + diff + ")";
			    }else{
			    	StrDiffChest = "( +" + Math.abs(diff) + ")";
			    }
			    
		    }
		    ChestWeek.setText(firstWeekChest + "(" + dateStr + ") " + StrlastChest + StrDiffChest);
        }
        
        if(firstWeekLongChest > 0){
			cal.setTimeInMillis(firstWeekLongChest);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);
        	
		    String StrlastChest = "";
		    String StrDiffChest = "";
		    
		    if(lastChest > 0){
		    	StrlastChest = "- " + lastChest + "";
		    	
		    	double diff = firstWeekChest - lastChest;
		    	diff = diff * 10;
			    int diff11 = (int) Math.round(diff);
			    double diff111 = diff11;
			    diff = diff111 / 10;		    	
		    	
			    if(diff > 0){
			    	StrDiffChest = "( -" + diff + ")";
			    }else{
			    	StrDiffChest = "( +" + Math.abs(diff) + ")";
			    }
			    
		    }
		    ChestWeek.setText(firstWeekChest + "(" + dateStr + ") " + StrlastChest + StrDiffChest);
        }        
        
        if(firstWeekLongNeck > 0){
			cal.setTimeInMillis(firstWeekLongNeck);
		    Date date = cal.getTime();
	    
		    if(DateDrumPicker.DateFormat111 == 1){
		    	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    }else{
		    	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    }
		    String dateStr = formatter.format(date);
        	
		    String StrlastNeck = "";
		    String StrDiffNeck = "";
		    
		    if(lastNeck > 0){
		    	StrlastNeck = "- " + lastNeck + "";
		    	
		    	double diff = firstWeekNeck - lastNeck;
		    	diff = diff * 10;
			    int diff11 = (int) Math.round(diff);
			    double diff111 = diff11;
			    diff = diff111 / 10;		    	
		    	
			    if(diff > 0){
			    	StrDiffNeck = "( -" + diff + ")";
			    }else{
			    	StrDiffNeck = "( +" + Math.abs(diff) + ")";
			    }
			    
		    }
		    NeckWeek.setText(firstWeekNeck + "(" + dateStr + ") " + StrlastNeck + StrDiffNeck);
        }        


        
        cursor.close();
        
	}
    
    
    
    
    

}
