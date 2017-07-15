
package weight.manager;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import wheel_lib.OnWheelChangedListener;
import wheel_lib.WheelView;
import wheel_lib.adapters.ArrayWheelAdapter;
import wheel_lib.adapters.NumericWheelAdapter;
import jp.dip.sys1.android.drumpicker.lib.DateDrumPicker;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class chartActivity extends Activity {
//	Toast.makeText(this, quitFeedback + "", Toast.LENGTH_LONG).show();
    public static final int MIN_COUNT = 7;
    public static final int MAX_COUNT = 30;
    public static final int CRUSH_LABEL = 15;
    public static final int CRUSH_LABEL_1 = 100;
    public static final int CRUSH_LABEL_2 = 170;
    
    public int typeOfChart = 1, typeOfInterval = 7;
    
  //  private Pref mPref;
    private LineChart mLineChart;
  //  private CalorieDAO mDAO;
    
    SqlAdapter adapter;
    long dateForCheck;
    double weight1;
    int target, numaverage;
    private Button btn, btn1, btn2, btn3, btn4, btn5,
    			   button_weight, button_waist_size, button_chest, button_hip, button_neck;
    int quitFeedback;
  //  private AdView adView;

	private AdView mAdView;

    SharedPreferences preferences2;
    TextView textViewDate;
    long CarrentChartDateMilis;
	int mYear;
	int mMonth;
	int mDay;
	ViewFlipper viewFlipperChart;
	
	
    WheelView month;
    WheelView year;
    WheelView day;
	
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
/*        
        SharedPreferences prefs111 = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    //  prefs111.getString("Langg", "defolt")
      	String languageToLoad  = prefs111.getString("Langg", "defolt");
      	if(!languageToLoad.equals("defolt")){

      		try{
    		//    String languageToLoad  = "fa"; // your language
    		    Locale locale = new Locale(languageToLoad);
    		    Locale.setDefault(locale);
    		    Configuration config = new Configuration();
    		    config.locale = locale;
    		    getBaseContext().getResources().updateConfiguration(config,
    		    getBaseContext().getResources().getDisplayMetrics());
      		}catch (Exception e){
                Log.e("Error", "Cannot launch", e);
            }
      		
        }     
*/        
        setContentView(R.layout.chart);




		/////////////////	     Ү襠 adView
		// Initialize the Mobile Ads SDK.
		MobileAds.initialize(this, "ca-app-pub-5156952701690991~5532968064");

		// Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
		// values/strings.xml.
		mAdView = (AdView) findViewById(R.id.ad_view);
		AdRequest adRequest = new AdRequest.Builder()
				.build();
		mAdView.loadAd(adRequest);
/////////////////	    End Ү襠 adView










        adapter = new SqlAdapter(this);
        
     //   adapter = WeightManagerActivity.adapter;
        

   //     mPref = Pref.getInstance(this);
        
        mLineChart = (LineChart)findViewById(R.id.line_chart1);

   //     mDAO = CalorieDAO.getInstance(this);

    //    int typeOfChart1 = typeOfChart;
        
        
        btn = (Button)findViewById(R.id.button1);
        btn1 = (Button)findViewById(R.id.button11);
        btn2 = (Button)findViewById(R.id.button12);
        btn3 = (Button)findViewById(R.id.button13);
        btn4 = (Button)findViewById(R.id.button14);
        btn5 = (Button)findViewById(R.id.button15);
        textViewDate = (TextView) findViewById(R.id.textViewDate);
        
        button_weight = (Button)findViewById(R.id.button_weight);
        button_waist_size = (Button)findViewById(R.id.button_waist_size);
        button_chest = (Button)findViewById(R.id.button_chest);
        button_hip = (Button)findViewById(R.id.button_hip);
        button_neck = (Button)findViewById(R.id.button_neck);
        
        viewFlipperChart = (ViewFlipper)findViewById(R.id.viewFlipperChart);
        
		SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(this);
		quitFeedback = preferences1.getInt("quitFeedback", 3);
		
		preferences2 = PreferenceManager.getDefaultSharedPreferences(this);
       
		Calendar cal = Calendar.getInstance();
		CarrentChartDateMilis = System.currentTimeMillis();
		cal.setTimeInMillis(CarrentChartDateMilis);
		textViewDate.setText(cal.get(Calendar.DAY_OF_MONTH) + " " +
				new DateFormatSymbols().getMonths()[cal.get(Calendar.MONTH)] + " " + 
				cal.get(Calendar.YEAR) );
		
		
		
		
		
		/*
//		������� �� �����
/////////////////	     �������� adView
		
//		Random r = new Random();
//		int i1=r.nextInt(10);
//		if(i1 == 1){
		    LinearLayout layout = (LinearLayout)findViewById(R.id.mainAdViewLayout);		
			
		    adView = new AdView(this, AdSize.BANNER, "ca-app-pub-8661822564276571/1942851045");
	
		    // ����� � LinearLayout (��������������, ��� ��� ��������
		    // ������� android:id="@+id/mainLayout"
	
	
		    // ���������� adView
		    layout.addView(adView);
	
		    // ������������� ������ ������� �� �������� ������ � �����������
		    adView.loadAd(new AdRequest());
//		}
/////////////////	    End �������� adView	    
        */
		    
		    
		    
    }  // end onCreate
    
    @Override
    protected void onResume() {
        super.onResume();

		if (mAdView != null) {
			mAdView.resume();
		}

        setChart(MIN_COUNT, typeOfChart);
        
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) { //Back key pressed
           //Things to Do

//        	Toast.makeText(this, quitFeedback + "", Toast.LENGTH_LONG).show();
        	
            Intent i = new Intent(this, WeightManagerActivity.class);
            startActivity(i);
            SharedPreferences myPreference=PreferenceManager.getDefaultSharedPreferences(this);
            if(myPreference.getBoolean("animation", true)) {
            	if(myPreference.getString("interpolator","1").equals("3")){
            		overridePendingTransition(R.anim.push_right_in3, R.anim.push_right_out3);
            	}else if(myPreference.getString("interpolator","1").equals("2")){
            		overridePendingTransition(R.anim.push_right_in2, R.anim.push_right_out2);
            	}else{
            	//	overridePendingTransition(R.anim.push_right_in1, R.anim.push_right_out1);
            	}            	
            }
            
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }    
    
    protected void onDestroy() {
		if (mAdView != null) {
			mAdView.destroy();
		}
 	   
 	   if(adapter != null){
 		   adapter.onDestroy();
 	   }
 	  
     super.onDestroy();
 }
	@Override
	public void onStop() {
		WeightManagerActivity.onStartOnStopSumm = WeightManagerActivity.onStartOnStopSumm - 1;
		super.onStop();
	}
	
	@Override
	public void onStart() {
		WeightManagerActivity.onStartOnStopSumm = WeightManagerActivity.onStartOnStopSumm + 1;
		super.onStart();
	}
		

    
//////////menu options start
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
		  MenuInflater inflater = getMenuInflater();
		  inflater.inflate(R.menu.menu, menu);
		  return true;
		}
		
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.options:
            	
            	startActivity(new Intent(this, ShowSettingsActivity.class));

            	
   //         	Toast.makeText(this, "You pressed the icon!", Toast.LENGTH_LONG).show();
                break;
            case R.id.quit:

            	AlertDialog.Builder alert = new AlertDialog.Builder(this);
                // 	alert.setTitle("Title");
                 	alert.setMessage(getString(R.string.quit_mesage));        	  

                 	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                 		public void onClick(DialogInterface dialog, int whichButton) {
             	          	Intent startMain = new Intent(Intent.ACTION_MAIN);
             	          	startMain.addCategory(Intent.CATEGORY_HOME);
             	          	startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             	          	startActivity(startMain);        			
                 		  }
                 		});

                 		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                 		  public void onClick(DialogInterface dialog, int whichButton) {
                 		    // Canceled.
                 		  }
                 		});

                 	alert.show();            	
            	
            	
          	  break;
          	  
            case R.id.feedback:

	        		quitFeedback = - 10;
	        		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
	        		SharedPreferences.Editor editor = preferences.edit();
	        		editor.putInt("quitFeedback", quitFeedback);
	        		editor.commit();	            	
            	
    			String appPackageName="market://details?id=weight.manager";
    			Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(appPackageName));
    			marketIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
    			startActivity(marketIntent);
                break;
                
            case R.id.weight_diary_plus:
            	
    			String appPackageName1="market://details?id=weight.manager.plus";
    			Intent marketIntent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(appPackageName1));
    			marketIntent1.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
    			startActivity(marketIntent1);
            	
            break;            
               
            case R.id.statistics:

                Intent i = new Intent(this, statistics.class);
                startActivity(i);
                
                break;             
            
        }
        return true;
    }    
		

//////////menu options end        
    
    
    
 
    protected void setChart(final int count, int typeOfChart2){
    	
    	SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    	     	
    	
        mLineChart.clear();

        mLineChart.setXAixsLabeler(new LineChart.AxisLabeler() {
            public String getLabel(float axisNum) {

            	int n = (int)(count - axisNum - 1);
            	
       	
            	
            	Calendar calendar = Calendar.getInstance();
            	
            	calendar.setTimeInMillis(CarrentChartDateMilis);
            	
            //	SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");
            	SimpleDateFormat format = new SimpleDateFormat("dd.MM");
            //	SimpleDateFormat format1 = new SimpleDateFormat("dd:MM:yyyy");
            //	SimpleDateFormat format = new SimpleDateFormat("EEEE");
            	calendar.add(Calendar.DAY_OF_YEAR, -1 * n);
            	Date newDate = calendar.getTime();
            	String date = format.format(newDate);
            	
            //	Calendar calendar = Calendar.getInstance();
            	
            //	int day = calendar.get(Calendar.DAY_OF_WEEK);
            	
            	SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
            	String dayOfTheWeek = sdf.format(newDate);            	

          	

                if(count == 7){
                    if(n == 0){
                        return "" + date;
                    }else{
                    	return dayOfTheWeek.substring(0, 3);
                    }
                }else if(count > 30){
                        return "" + date;
                }
                
            	
/*            	
                if(count <= 20){
                    if(n % 1 == 0){
                        return "" + date;
                    }
                    return "";
                }else if(count >= 20 && count < 50){
                    if(n % 1 == 0){
                        return "" + date;
                    }
                    return "";
                }else if(count >= 50 && count < 100){
                    if(n % 1 == 0){
                        return "" + date;
                    }
                    return "";                    
                }else if(count >= 100 && count < 250){
                    if(n % 1 == 0){
                        return "" + date;
                    }
                    return "";
                }else if(count >= 250){
                    if(n % 1 == 0){
                        return "" + date;
                    }
                    return "";                	
                }
*/                
                return "" + date;
                
                
            }
        });
        

    //    List<List<CalorieInfo>> history = mDAO.getHistory(count);
        
   //     mPref = Pref.getInstance(this);
/*        
        int num = Math.min(count, adapter.getCount());
        numaverage = num;
        
        if(num <= 0){
            Toast.makeText(this, getString(R.string.chart_no_data), Toast.LENGTH_SHORT).show();
            finish();
        }
*/        
        int num = count;
        
        
     //   int target = mPref.getInt(C.config.target, C.config.target_def_value);
        

        
		if (typeOfChart == 1){
	        double target1 =  Double.parseDouble(SP.getString("targetWeight", "0"));
	        target = (int) target1;
		}else if (typeOfChart == 2){
	        double target1 =  Double.parseDouble(SP.getString("targetWaistSize", "0"));
	        target = (int) target1;
	    }else if (typeOfChart == 3){
	        double target1 =  Double.parseDouble(SP.getString("targetchest", "0"));
	        target = (int) target1;
	    }else if (typeOfChart == 4){
	        double target1 =  Double.parseDouble(SP.getString("targethip", "0"));
	        target = (int) target1;
	    }else if (typeOfChart == 5){
	    //    double target1 =  Double.parseDouble(SP.getString("targethip", "0"));
	        target = (int) 0;
	    }
        
        
        int min = Integer.MAX_VALUE;
        int max = target;
        float sum = 0;
        int average;
//        int BDi = adapter.getCount();
        
        for(int i = 0; i <= num; i++){
//        for(int i = num; i > 0; i--){
//        	BDi = BDi - 1;
        	
          //  List<CalorieInfo> calorieInfoList = history.get(i);
          //  int total = getTotal(calorieInfoList);
        	

//        	Calendar calendar = Calendar.getInstance();
//        	SimpleDateFormat format = new SimpleDateFormat("dd:MM:yyyy");
//        	calendar.add(Calendar.DAY_OF_YEAR, -1 * i);
//        	Date newDate = calendar.getTime();
//        	dateForCheck = format.format(newDate);

        	
        	Calendar calendar = Calendar.getInstance();
      //  	Date newDate = calendar.getTime();
      //  	calendar.setTime(newDate);
        	calendar.setTimeInMillis(CarrentChartDateMilis);
        	
        	calendar.set(Calendar.HOUR_OF_DAY, 13);
        	calendar.set(Calendar.MINUTE, 59);
        	calendar.set(Calendar.SECOND, 59);
        	calendar.set(Calendar.MILLISECOND, 59);
        	
        	calendar.add(Calendar.DAY_OF_YEAR, -1 * i);
        	dateForCheck = calendar.getTimeInMillis();

			
        	
		 //   final Cursor Exists = adapter.checkExists(dateForCheck);
		    
		    final weight Exists = adapter.checkExists(dateForCheck);

			if (Exists == null){
				// takoi daty nety
//				Toast.makeText(this, "weight1111 " + dateForCheck, Toast.LENGTH_SHORT).show();
			}else{

				if (typeOfChart == 1){
				//	weight1 =  Exists.getDouble(2);
					weight1 =  Double.parseDouble(Exists.getWeight());
				}else if (typeOfChart == 2){
				//	weight1 =  Exists.getDouble(3);
					weight1 =  Exists.getWaistSize();
					weight1 = weight1 / 10;
			    }else if (typeOfChart == 3){
				//	weight1 =  Exists.getDouble(4);
					weight1 =  Exists.getChest();
					weight1 = weight1 / 10;
			    }else if (typeOfChart == 4){
				//	weight1 =  Exists.getDouble(5);
					weight1 =  Exists.getHip();
					weight1 = weight1 / 10;
			    }else if (typeOfChart == 5){
				//	weight1 =  Exists.getDouble(5);
					weight1 =  Exists.getNeck();
					weight1 = weight1 / 10;
			    }
				
				
				
	//				Toast.makeText(this, "weight1" + weight1, Toast.LENGTH_SHORT).show();				
	
				//    double weight1 =  Double.parseDouble(adapter.getItem(BDi).getWeight());
				    int total1 = (int) (weight1);
		        	
				    float total = (float) weight1;
				    
		            min = Math.min(min, total1);
		            max = Math.max(max, total1);
		            sum += total;
		            
		            if(total > 0){
			            if(count < CRUSH_LABEL){
			                mLineChart.addPoint("" + total, count - i - 1, total);
			            }
			            else{
			                mLineChart.addPoint("", count - i - 1, total);
			            }
		            }
            
            
			}
        }

        min -= 10;
        min = (min / 10) * 10;
        if(min < 0){
            min = 0;
        }
        

        max += 10;
        max = (max / 10) * 10;
        
        average = (int)(sum / numaverage);

//        mLineChart.setXAxis(0, count - 1, 1);

        
        
        
        if(count == 7){
            mLineChart.setXAxis(0, count - 1, 1);
        }else if(count == 30){
            mLineChart.setXAxis(0, count - 1, 4);
        }else if(count == 90){
            mLineChart.setXAxis(0, count - 1, 10);
        }else if(count == 180){
            mLineChart.setXAxis(0, count - 1, 20);
        }else if(count == 365){
            mLineChart.setXAxis(0, count - 1, 40);
        }
        
        
        
/*        
        if(count < 20){
            mLineChart.setXAxis(0, count - 1, 1);
        }else if(count >= 20 && count < 80){
            mLineChart.setXAxis(0, count - 1, 4);
        }else if(count >= 80 && count < 150){
            mLineChart.setXAxis(0, count - 1, 10);
        }else if(count >= 150 && count < 300){
            mLineChart.setXAxis(0, count - 1, 20);                        
        }else if(count >= 300){
            mLineChart.setXAxis(0, count - 1, 40);            
//        }else if(count >= CRUSH_LABEL_1){
//            mLineChart.setXAxis(0, count - 1, 3);            
        }
*/
//        mLineChart.setXAxis(0, count - 1, 1);
        mLineChart.setyAxis(min, max, 10);
        if (target > 0){
        	mLineChart.addLine(getString(R.string.config_general_target) + target, 0, target, count - 1, target, null);
        }
        if(average > 0){
        	mLineChart.addLine(getString(R.string.summary_average) + average, 0, average, count - 1, average, null);
        }
    }
    
   
	public void settingsClick(View view) {
		openOptionsMenu();
	}    
    
	public void addActivity(View view) {
        Intent i = new Intent(this, WeightManagerActivity.class);
        startActivity(i);
        SharedPreferences myPreference=PreferenceManager.getDefaultSharedPreferences(this);
        if(myPreference.getBoolean("animation", true)) {
        	if(myPreference.getString("interpolator","1").equals("3")){
        		overridePendingTransition(R.anim.push_right_in3, R.anim.push_right_out3);
        	}else if(myPreference.getString("interpolator","1").equals("2")){
        		overridePendingTransition(R.anim.push_right_in2, R.anim.push_right_out2);
        	}else{
        	//	overridePendingTransition(R.anim.push_right_in1, R.anim.push_right_out1);
        	}            	
        }

	}
	public void listActivity(View view) {
        Intent i = new Intent(this, listActivity.class);
        startActivity(i);
        SharedPreferences myPreference=PreferenceManager.getDefaultSharedPreferences(this);
        if(myPreference.getBoolean("animation", true)) {
        	if(myPreference.getString("interpolator","1").equals("3")){
        		overridePendingTransition(R.anim.push_left_in3, R.anim.push_left_out3);
        	}else if(myPreference.getString("interpolator","1").equals("2")){
        		overridePendingTransition(R.anim.push_left_in2, R.anim.push_left_out2);
        	}else{
        		overridePendingTransition(R.anim.push_left_in1, R.anim.push_left_out1);
        	}             	
        }
	}
	
	public void alarm(View view) {
        Intent i = new Intent(this, AlarmclockActivity.class);
        startActivity(i);
        SharedPreferences myPreference=PreferenceManager.getDefaultSharedPreferences(this);
        if(myPreference.getBoolean("animation", true)) {
        	if(myPreference.getString("interpolator","1").equals("3")){
        		overridePendingTransition(R.anim.push_left_in3, R.anim.push_left_out3);
        	}else if(myPreference.getString("interpolator","1").equals("2")){
        		overridePendingTransition(R.anim.push_left_in2, R.anim.push_left_out2);
        	}else{
        		overridePendingTransition(R.anim.push_left_in1, R.anim.push_left_out1);
        	}         	
        }
	}	

	public void share(View view) {
		
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "" + getString(R.string.app_name));
		intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=weight.manager");
		startActivity(Intent.createChooser(intent, getString(R.string.share)));			
		
/*		
		//create the send intent  
		Intent shareIntent =   
		 new Intent(android.content.Intent.ACTION_SEND);  
		  
		//set the type  
		shareIntent.setType("text/plain");  
		  
		//add a subject  
	//	shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,   
	//	 "Insert Subject Here");  
		  
		//build the body of the message to be shared  
	//	String shareMessage = "Insert message body here.";  
		  
		//add the message  
	//	shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,   
	//	 shareMessage);  
		  
		//start the chooser for sharing  
		startActivity(Intent.createChooser(shareIntent,   
		 "Share"));
*/		
	}
	
	public void Week(View view) {
		setChart(7, typeOfChart);
		typeOfInterval = 7;
	//	btn2.setBackgroundResource(0x11000000);
		
		btn1.setBackgroundResource(R.drawable.custom_dialog_button_date_pressed);
		btn2.setBackgroundResource(R.drawable.custom_dialog_button_date);
		btn3.setBackgroundResource(R.drawable.custom_dialog_button_date);
		btn4.setBackgroundResource(R.drawable.custom_dialog_button_date);
		btn5.setBackgroundResource(R.drawable.custom_dialog_button_date);
	}	
	public void Month(View view) {
		btn1.setBackgroundResource(R.drawable.custom_dialog_button_date);
		btn2.setBackgroundResource(R.drawable.custom_dialog_button_date_pressed);
		btn3.setBackgroundResource(R.drawable.custom_dialog_button_date);
		btn4.setBackgroundResource(R.drawable.custom_dialog_button_date);
		btn5.setBackgroundResource(R.drawable.custom_dialog_button_date);		
		setChart(30, typeOfChart);
		typeOfInterval = 30;
	}	
	public void Month3(View view) {
		btn1.setBackgroundResource(R.drawable.custom_dialog_button_date);
		btn2.setBackgroundResource(R.drawable.custom_dialog_button_date);
		btn3.setBackgroundResource(R.drawable.custom_dialog_button_date_pressed);
		btn4.setBackgroundResource(R.drawable.custom_dialog_button_date);
		btn5.setBackgroundResource(R.drawable.custom_dialog_button_date);
		setChart(90, typeOfChart);
		typeOfInterval = 90;
	}	
	public void Month6(View view) {
		btn1.setBackgroundResource(R.drawable.custom_dialog_button_date);
		btn2.setBackgroundResource(R.drawable.custom_dialog_button_date);
		btn3.setBackgroundResource(R.drawable.custom_dialog_button_date);
		btn4.setBackgroundResource(R.drawable.custom_dialog_button_date_pressed);
		btn5.setBackgroundResource(R.drawable.custom_dialog_button_date);
		setChart(180, typeOfChart);
		typeOfInterval = 180;
	}	
	public void Year(View view) {
		btn1.setBackgroundResource(R.drawable.custom_dialog_button_date);
		btn2.setBackgroundResource(R.drawable.custom_dialog_button_date);
		btn3.setBackgroundResource(R.drawable.custom_dialog_button_date);
		btn4.setBackgroundResource(R.drawable.custom_dialog_button_date);
		btn5.setBackgroundResource(R.drawable.custom_dialog_button_date_pressed);		
		setChart(365, typeOfChart);
		typeOfInterval = 365;
	}
	
	public void Weight(View view) {
/*		
		if (typeOfChart == 1){
			btn.setBackgroundResource(R.drawable.switche1);
//			btn1.setBackgroundResource(R.drawable.custom_dialog_button_date_pressed);	
//			btn2.setBackgroundResource(R.drawable.custom_dialog_button_date);
//			btn3.setBackgroundResource(R.drawable.custom_dialog_button_date);
//			btn4.setBackgroundResource(R.drawable.custom_dialog_button_date);
//			btn5.setBackgroundResource(R.drawable.custom_dialog_button_date);
			typeOfChart = 0;
			setChart(typeOfInterval, typeOfChart);
		}else{
			btn.setBackgroundResource(R.drawable.switche);
//			btn1.setBackgroundResource(R.drawable.custom_dialog_button_date_pressed);	
//			btn2.setBackgroundResource(R.drawable.custom_dialog_button_date);
//			btn3.setBackgroundResource(R.drawable.custom_dialog_button_date);
//			btn4.setBackgroundResource(R.drawable.custom_dialog_button_date);
//			btn5.setBackgroundResource(R.drawable.custom_dialog_button_date);
			typeOfChart = 1;
			setChart(typeOfInterval, typeOfChart);
		}
*/		
		
			
			button_weight.setBackgroundResource(R.drawable.custom_dialog_button_date_pressed);
			button_waist_size.setBackgroundResource(R.drawable.custom_dialog_button_date);
			button_chest.setBackgroundResource(R.drawable.custom_dialog_button_date);
			button_hip.setBackgroundResource(R.drawable.custom_dialog_button_date);
			button_neck.setBackgroundResource(R.drawable.custom_dialog_button_date);
			
			typeOfChart = 1;
			setChart(typeOfInterval, typeOfChart);
			

		
		

	}
	
	public void Waist_size(View view) {
		button_weight.setBackgroundResource(R.drawable.custom_dialog_button_date);
		button_waist_size.setBackgroundResource(R.drawable.custom_dialog_button_date_pressed);
		button_chest.setBackgroundResource(R.drawable.custom_dialog_button_date);
		button_hip.setBackgroundResource(R.drawable.custom_dialog_button_date);
		button_neck.setBackgroundResource(R.drawable.custom_dialog_button_date);
		
		typeOfChart = 2;
		setChart(typeOfInterval, typeOfChart);
	}
	public void Chest(View view) {
		button_weight.setBackgroundResource(R.drawable.custom_dialog_button_date);
		button_waist_size.setBackgroundResource(R.drawable.custom_dialog_button_date);
		button_chest.setBackgroundResource(R.drawable.custom_dialog_button_date_pressed);
		button_hip.setBackgroundResource(R.drawable.custom_dialog_button_date);
		button_neck.setBackgroundResource(R.drawable.custom_dialog_button_date);
		
		typeOfChart = 3;
		setChart(typeOfInterval, typeOfChart);
	}
	public void Hip(View view) {
		button_weight.setBackgroundResource(R.drawable.custom_dialog_button_date);
		button_waist_size.setBackgroundResource(R.drawable.custom_dialog_button_date);
		button_chest.setBackgroundResource(R.drawable.custom_dialog_button_date);
		button_hip.setBackgroundResource(R.drawable.custom_dialog_button_date_pressed);
		button_neck.setBackgroundResource(R.drawable.custom_dialog_button_date);
		
		typeOfChart = 4;
		setChart(typeOfInterval, typeOfChart);
	}
	
	public void Neck(View view) {
		button_weight.setBackgroundResource(R.drawable.custom_dialog_button_date);
		button_waist_size.setBackgroundResource(R.drawable.custom_dialog_button_date);
		button_chest.setBackgroundResource(R.drawable.custom_dialog_button_date);
		button_hip.setBackgroundResource(R.drawable.custom_dialog_button_date);
		button_neck.setBackgroundResource(R.drawable.custom_dialog_button_date_pressed);
		
		typeOfChart = 5;
		setChart(typeOfInterval, typeOfChart);
	}	
	
	

	public void Prev(View view) {
		
		
    	Calendar calendar = Calendar.getInstance();
  //  	Date newDate = calendar.getTime();
  //  	calendar.setTime(newDate);
    	calendar.setTimeInMillis(CarrentChartDateMilis);
    	
    	calendar.set(Calendar.HOUR_OF_DAY, 13);
    	calendar.set(Calendar.MINUTE, 59);
    	calendar.set(Calendar.SECOND, 59);
    	calendar.set(Calendar.MILLISECOND, 59);
    	calendar.add(Calendar.DAY_OF_YEAR, -1 * typeOfInterval);
		CarrentChartDateMilis = calendar.getTimeInMillis();    	
    	
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(CarrentChartDateMilis);
		textViewDate.setText(cal.get(Calendar.DAY_OF_MONTH) + " " +
				new DateFormatSymbols().getMonths()[cal.get(Calendar.MONTH)] + " " + 
				cal.get(Calendar.YEAR) );		
		

		
		SharedPreferences myPreference=PreferenceManager.getDefaultSharedPreferences(this);
        if(myPreference.getBoolean("animation", true)) {			
			viewFlipperChart.setInAnimation(this, R.anim.push_right_in1);
			viewFlipperChart.setOutAnimation(this, R.anim.push_right_out1);
			viewFlipperChart.showPrevious();
        }

//   		if(viewFlipperChart.getDisplayedChild() == 1){
 //  			mLineChart = (LineChart)findViewById(R.id.line_chart1);
//   		}else{
//   			mLineChart = (LineChart)findViewById(R.id.line_chart2);
//   		}		
		
    	setChart(typeOfInterval, typeOfChart);


    	
//    	LinearLayout LayoutChart = (LinearLayout) findViewById(R.id.anim_chart);
//    	Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_chart);
//    	LayoutChart.startAnimation(anim);    	
    	
   // 	Toast.makeText(this, "Prev" + calendar.get(Calendar.DAY_OF_MONTH), Toast.LENGTH_LONG).show();	
	}
	public void dateClick(View view) {
		setChart(typeOfInterval, typeOfChart);
        try{


			
			// custom dialog
			final Dialog dialog = new Dialog(this);
			dialog.setContentView(R.layout.custom_dialog_date);
		//	dialog.setTitle(getString(R.string.Waist_size));
			
			dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
 
			Button dialogButton = (Button) dialog.findViewById(R.id.button22);
			
			
/*			
			DateDrumPicker dPicker = (DateDrumPicker) dialog.findViewById(R.id.datepicker1);

		
			
//			DatePicker dpDateChart = (DatePicker) dialog.findViewById(R.id.ChartdatePicker1);
		
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(CarrentChartDateMilis);
//			dpDateChart.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);
///	        dPicker.setYear(cal.get(Calendar.YEAR));
///	        dPicker.setMonth(cal.get(Calendar.MONTH) + 1);
///	        dPicker.setDay(cal.get(Calendar.DAY_OF_MONTH));
	        
	        dPicker.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
	        
	        
			dPicker.setOnDateChangedListener(new OnDateChangedListener() {
				public void onDateChanged(DatePicker view, int year,
						int monthOfYear, int dayOfMonth) {
					mYear=year;
					mMonth=monthOfYear - 1;
					mDay=dayOfMonth;

				}
			}); 	        
*/	        

			
			

			
		    Calendar calendar = Calendar.getInstance();

		    if(DateDrumPicker.DateFormat111 == 1){
		    //	formatter = new SimpleDateFormat("MM-dd-yyyy");
		    	
			    month = (WheelView) dialog.findViewById(R.id.Wheel_1);
				year = (WheelView) dialog.findViewById(R.id.Wheel_3);
				day = (WheelView) dialog.findViewById(R.id.Wheel_2);
				
		    }else if(DateDrumPicker.DateFormat111 == 2){
		    //	formatter = new SimpleDateFormat("dd-MM-yyyy");
		    	
			    month = (WheelView) dialog.findViewById(R.id.Wheel_2);
				year = (WheelView) dialog.findViewById(R.id.Wheel_3);
				day = (WheelView) dialog.findViewById(R.id.Wheel_1);
				
		    }else{
		    //	formatter = new SimpleDateFormat("yyyy-MM-dd");
		    	
			    month = (WheelView) dialog.findViewById(R.id.Wheel_2);
				year = (WheelView) dialog.findViewById(R.id.Wheel_1);
				day = (WheelView) dialog.findViewById(R.id.Wheel_3);	    	
		    	
		    }	    
		    
		    

		    
		    OnWheelChangedListener listener = new OnWheelChangedListener() {
		        public void onChanged(WheelView wheel, int oldValue, int newValue) {
		            updateDays(year, month, day);
		        }
		    };

		    // month
		    int curMonth = calendar.get(Calendar.MONTH);
//		    String months[] = new String[] {"January", "February", "March", "April", "May",
//		            "June", "July", "August", "September", "October", "November", "December"};
		    
			final String[] months = {
				new DateFormatSymbols().getMonths()[0],
				new DateFormatSymbols().getMonths()[1],
				new DateFormatSymbols().getMonths()[2],
				new DateFormatSymbols().getMonths()[3],
				new DateFormatSymbols().getMonths()[4],
				new DateFormatSymbols().getMonths()[5],
				new DateFormatSymbols().getMonths()[6],
				new DateFormatSymbols().getMonths()[7],
				new DateFormatSymbols().getMonths()[8],
				new DateFormatSymbols().getMonths()[9],
				new DateFormatSymbols().getMonths()[10],
				new DateFormatSymbols().getMonths()[11]
			};    
		    
		    
		    month.setViewAdapter(new DateArrayAdapter(this, months, curMonth));
		    month.setCurrentItem(curMonth);
		    month.addChangingListener(listener);

		    // year
		    int curYear = calendar.get(Calendar.YEAR);
		    year.setViewAdapter(new DateNumericAdapter(this, 1970 , 2030, 0));
//		    year.setCurrentItem(curYear);
		    year.setCurrentItem(calendar.get(Calendar.YEAR) - 1970);
		    year.addChangingListener(listener);
		    
		    //day
		    updateDays(year, month, day);
		    day.setCurrentItem(calendar.get(Calendar.DAY_OF_MONTH) - 1);			
		    day.addChangingListener(listener);
			
			

		    
		    
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		        

			// Edit
			dialogButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					dialog.dismiss();

					updateDays(year, month, day);
					
//					DatePicker dpDateChart = (DatePicker) dialog.findViewById(R.id.ChartdatePicker1);

					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.YEAR, mYear);
					cal.set(Calendar.MONTH, mMonth);
					cal.set(Calendar.DAY_OF_MONTH, mDay);
					cal.set(Calendar.HOUR_OF_DAY, 13);
					cal.set(Calendar.MINUTE, 59);
					cal.set(Calendar.SECOND, 59);
					cal.set(Calendar.MILLISECOND, 59);
				
	            //	cal.getFirstDayOfWeek();
					CarrentChartDateMilis = cal.getTimeInMillis();
					
					
					textViewDate.setText(mDay + " " +
							new DateFormatSymbols().getMonths()[mMonth] + " " + 
							mYear );

					setChart(typeOfInterval, typeOfChart);
					
				}
			});
			// delete
			Button dialogButton1 = (Button) dialog.findViewById(R.id.button23);
			dialogButton1.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					
					dialog.dismiss();
				}
			});
			dialog.show();
        }catch (Exception e){
            Log.e("Error", "Cannot launch", e);
        }
    		
	}
	
	
	
	
	
	
	
	
	
	
	
//start wheel
    

    
    /**
     * Updates day wheel. Sets max days according to selected month and year
     */
    void updateDays(WheelView year, WheelView month, WheelView day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + year.getCurrentItem());
        calendar.set(Calendar.MONTH, month.getCurrentItem());
        
        int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        day.setViewAdapter(new DateNumericAdapter(this, 1, maxDays, calendar.get(Calendar.DAY_OF_MONTH) - 1));
        int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
        day.setCurrentItem(curDay - 1, true);
        
        
        mYear = 1970 + year.getCurrentItem();
        mMonth = month.getCurrentItem();
        mDay = curDay;
    }
    
    /**
     * Adapter for numeric wheels. Highlights the current value.
     */
    private class DateNumericAdapter extends NumericWheelAdapter {
        // Index of current item
        int currentItem;
        // Index of item to be highlighted
        int currentValue;
        
        /**
         * Constructor
         */
        public DateNumericAdapter(Context context, int minValue, int maxValue, int current) {
            super(context, minValue, maxValue);
            this.currentValue = current;
            setTextSize(20);
        }
        
        @Override
        protected void configureTextView(TextView view) {
            super.configureTextView(view);
            if (currentItem == currentValue) {
                view.setTextColor(0xFF0000F0);
            }
            view.setTypeface(Typeface.SANS_SERIF);
        }
        
        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            currentItem = index;
            return super.getItem(index, cachedView, parent);
        }
    }
    
    /**
     * Adapter for string based wheel. Highlights the current value.
     */
    private class DateArrayAdapter extends ArrayWheelAdapter<String> {
        // Index of current item
        int currentItem;
        // Index of item to be highlighted
        int currentValue;
        
        /**
         * Constructor
         */
        public DateArrayAdapter(Context context, String[] items, int current) {
            super(context, items);
            this.currentValue = current;
            setTextSize(20);
        }
        
        @Override
        protected void configureTextView(TextView view) {
            super.configureTextView(view);
            if (currentItem == currentValue) {
                view.setTextColor(0xFF0000F0);
            }
            view.setTypeface(Typeface.SANS_SERIF);
        }
        
        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            currentItem = index;
            return super.getItem(index, cachedView, parent);
        }
    }    
    
    
//end wheel    	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void Next(View view) {
    	Calendar calendar = Calendar.getInstance();
  //  	Date newDate = calendar.getTime();
  //  	calendar.setTime(newDate);
    	calendar.setTimeInMillis(CarrentChartDateMilis);
    	
    	calendar.set(Calendar.HOUR_OF_DAY, 13);
    	calendar.set(Calendar.MINUTE, 59);
    	calendar.set(Calendar.SECOND, 59);
    	calendar.set(Calendar.MILLISECOND, 59);
    	calendar.add(Calendar.DAY_OF_YEAR, typeOfInterval);
		CarrentChartDateMilis = calendar.getTimeInMillis();
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(CarrentChartDateMilis);
		textViewDate.setText(cal.get(Calendar.DAY_OF_MONTH) + " " +
				new DateFormatSymbols().getMonths()[cal.get(Calendar.MONTH)] + " " + 
				cal.get(Calendar.YEAR) );
		
		SharedPreferences myPreference=PreferenceManager.getDefaultSharedPreferences(this);
        if(myPreference.getBoolean("animation", true)) {		
			viewFlipperChart.setInAnimation(this, R.anim.push_left_in1);
			viewFlipperChart.setOutAnimation(this, R.anim.push_left_out1);
			viewFlipperChart.showNext();
        }
		
//   		if(viewFlipperChart.getDisplayedChild() == 1){
//   			mLineChart = (LineChart)findViewById(R.id.line_chart1);
//   		}else{
//   			mLineChart = (LineChart)findViewById(R.id.line_chart2);
//   		}
		
    	setChart(typeOfInterval, typeOfChart);
	}
	
    
}
