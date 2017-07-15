package weight.manager;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import jp.dip.sys1.android.drumpicker.lib.TimeDrumPicker;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;

public class AlarmclockActivity extends Activity
{

//	private ToggleButton setAlarmButton;
//	private TimePicker timePicker;
	private TextView nextAlarmTimeTextView;
///	private int mHour;
///	private int mMinute;
	private static final Calendar c = Calendar.getInstance();
	private static final Calendar c1 = Calendar.getInstance();
	
    private PendingIntent mAlarmSender;
    
    private Button btn;
    int AlarmRun, quitFeedback;
    
    NotificationManager mNM;
    SharedPreferences preferences2;
    
    TimeDrumPicker tPicker;
    
 //   private AdView adView;

	private AdView mAdView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
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
		setContentView(R.layout.alarmclock);


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

		
		/*
		//		������� �� �����
		/////////////////	     �������� adView
		
		Random r = new Random();
		int i1=r.nextInt(10);
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
		
		
		
//		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);	
		
        // Create an IntentSender that will launch our service, to be scheduled
        // with the alarm manager.
//        mAlarmSender = PendingIntent.getService(AlarmclockActivity.this,
//                0, new Intent(AlarmclockActivity.this, AlarmService_Service.class), 0);
   
        mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        
        tPicker = (TimeDrumPicker) findViewById(R.id.datepicker);
	//	setAlarmButton = (ToggleButton) findViewById(R.id.toggleButton);
///		timePicker = (TimePicker) findViewById(R.id.timePicker);
///		timePicker.setIs24HourView(DateFormat.is24HourFormat(this));
		nextAlarmTimeTextView = (TextView) findViewById(R.id.nextAlarmTimeTextView);
        btn = (Button)findViewById(R.id.button6);

        
    	Calendar calendar = Calendar.getInstance();
		tPicker.setHour(calendar.get(Calendar.HOUR_OF_DAY));
		tPicker.setMinitue(calendar.get(Calendar.MINUTE) - 1);		
        
        
        
///        mHour = tPicker.getHour();
///        mMinute = tPicker.getMinitue();
///		mHour = timePicker.getCurrentHour();
///		mMinute = timePicker.getCurrentMinute();


		SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(this);
		AlarmRun = preferences1.getInt("AlarmRun", 0);
		long AlarmTimeMilis = preferences1.getLong("AlarmTimeMilis", 0);
		quitFeedback = preferences1.getInt("quitFeedback", 3);
//		Toast.makeText(this, quitFeedback + "", Toast.LENGTH_LONG).show();
		preferences2 = PreferenceManager.getDefaultSharedPreferences(this);
		
		if (AlarmRun == 1){
		//	setAlarmButton.setChecked(true);
		//	timePicker.setVisibility(View.GONE);
///			timePicker.setEnabled(false);
			btn.setBackgroundResource(R.drawable.switche_on);
			
			if (AlarmTimeMilis > 0){
				c1.setTimeInMillis(AlarmTimeMilis);
				
				Calendar tempCalendar = Calendar.getInstance();
				tempCalendar.setTimeInMillis(System.currentTimeMillis());
//				tempCalendar.set(Calendar.DAY_OF_YEAR, mHour);
//				tempCalendar.set(Calendar.MINUTE, mMinute);
				
				c1.set(Calendar.DAY_OF_YEAR, tempCalendar.get(Calendar.DAY_OF_YEAR));

				if (c1.compareTo(tempCalendar) < 0)
				{
					c1.add(Calendar.DAY_OF_YEAR, 1);
					
				}
				tPicker.setHour(c1.get(Calendar.HOUR_OF_DAY));
				tPicker.setMinitue(c1.get(Calendar.MINUTE));
///				timePicker.setCurrentHour(c1.get(Calendar.HOUR_OF_DAY));
///				timePicker.setCurrentMinute(c1.get(Calendar.MINUTE));
//				Toast.makeText(this, c1.get(Calendar.HOUR) + "!", Toast.LENGTH_LONG).show();
				nextAlarmTimeTextView.setText(c1.get(Calendar.DAY_OF_MONTH)
								+ " "
								+ new DateFormatSymbols().getMonths()[c1.get(GregorianCalendar.MONTH)]
								+ " " + getPrettyTime1());
			}
		}
		
		



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
    protected void onDestroy() {
  	  // if (adView != null) {
  	//	      adView.destroy();
	//   }

		if (mAdView != null) {
			mAdView.destroy();
		}

      super.onDestroy();
  }



	@Override
	protected void onResume(){
		System.gc();
		super.onResume();
		if (mAdView != null) {
			mAdView.resume();
		}
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
	
	public void settingsClick(View view) {
		openOptionsMenu();
	}    
	
	public void OnOffAlarm(View view) {
		if (AlarmRun == 1){
			btn.setBackgroundResource(R.drawable.switche_off);
			unsetAlarm();
			AlarmRun = 0;

		}else{
			btn.setBackgroundResource(R.drawable.switche_on);
			setAlarm();
			AlarmRun = 1;
		}
		
		

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
    	        //  	Intent startMain = new Intent(Intent.ACTION_MAIN);
    	        //  	startMain.addCategory(Intent.CATEGORY_HOME);
    	        //  	startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	        //  	startActivity(startMain);
    	          	
    	          	finish();
    	          	
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
	
	
	
	
	
	private void unsetAlarm()
	{
		

		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		SharedPreferences.Editor editor=preferences.edit();
		editor.putInt("AlarmRun", 0);
		editor.commit();		
		
       // timePicker.setVisibility(View.VISIBLE);
//        timePicker.setEnabled(true);
        mNM.cancel(0);
        
        
        Intent intentstop = new Intent(this, BReceiver.class);
        PendingIntent senderstop = PendingIntent.getBroadcast(this,
                    1234567, intentstop, 0);
        AlarmManager alarmManagerstop = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManagerstop.cancel(senderstop);        
        
        nextAlarmTimeTextView.setText("");
	
	}

    private void showNotification() {
        // In this sample, we'll use the same text for the ticker and the expanded notification
        CharSequence text = getText(R.string.alarm_service_started);

        // Set the icon, scrolling text and timestamp
        Notification notification = new Notification(R.drawable.ic_launcher, text,
                System.currentTimeMillis());

        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, AlarmclockActivity.class), 0);

        // Set the info for the views that show in the notification panel.
        notification.setLatestEventInfo(this, getText(R.string.weight_manager),
                       text, contentIntent);
        
        // Send the notification.
        // We use a layout id because it is a unique number.  We use it later to cancel.
        mNM.notify(0, notification);
    }	
	
	
	private void setAlarm()
	{

		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		SharedPreferences.Editor editor=preferences.edit();
		editor.putInt("AlarmRun", 1);
		editor.commit();
		
		
//		int AlarmRun = preferences.getInt("AlarmRun", 0); // 4 or default 2
		
		showNotification();
		
//		timePicker.setEnabled(false);
      //  timePicker.setVisibility(View.GONE);
       // timePicker.setVisibility(View.INVISIBLE);

        

	
		c.setTimeInMillis(System.currentTimeMillis());
		Calendar tempCalendar = Calendar.getInstance();
		tempCalendar.setTimeInMillis(System.currentTimeMillis());
		tempCalendar.set(Calendar.HOUR_OF_DAY, tPicker.getHour());
		tempCalendar.set(Calendar.MINUTE, tPicker.getMinitue());

		if (c.compareTo(tempCalendar) > 0)
		{
			c.add(Calendar.DAY_OF_YEAR, 1);
			
		}
		
		c.set(Calendar.HOUR_OF_DAY, tPicker.getHour());
		c.set(Calendar.MINUTE, tPicker.getMinitue());
		c.set(Calendar.SECOND, 0);

		editor.putLong("AlarmTimeMilis", c.getTimeInMillis());
		editor.commit();

        // We want the alarm to go off 30 seconds from now.
     //   long firstTime = SystemClock.elapsedRealtime();
     //   firstTime += 15 * 1000;
		
//		long firstTime = SystemClock.currentThreadTimeMillis();

        Intent intent = new Intent(this, BReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(this, 1234567, intent, 0);

        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 60 * 60 * 24 * 1000, sender);
        
        

//		String toastString = getPrettyTime();
//		Toast toast = Toast.makeText(getApplicationContext(), toastString,
//				Toast.LENGTH_SHORT);
//		toast.show();

//		nextAlarmTimeTextView.setText(c.getDisplayName(Calendar.MONTH,
//				Calendar.SHORT, Locale.US)
		nextAlarmTimeTextView.setText(c.get(Calendar.DAY_OF_MONTH)
//		nextAlarmTimeTextView.setText(c.getTimeInMillis()
				+ " "
				+ new DateFormatSymbols().getMonths()[c.get(GregorianCalendar.MONTH)]		
				+ " " + getPrettyTime());
//		timePicker.setVisibility(View.GONE);
		

		
	}

	private String getPrettyTime()
	{
		StringBuilder sb = new StringBuilder();

//		if (DateFormat.is24HourFormat(this))
			sb.append(tPicker.getHour());
//		else
//		{
//			if (c.get(Calendar.HOUR) == 12 || c.get(Calendar.HOUR) == 0)
//				sb.append(12);
//			else
//				sb.append(c.get(Calendar.HOUR));
//		}

		sb.append(":");
		if (Integer.toString(tPicker.getMinitue()).length() == 1)
			sb.append("0" + tPicker.getMinitue());
		else
			sb.append(tPicker.getMinitue());
		if (!DateFormat.is24HourFormat(this))
//			sb.append(c.getDisplayName(Calendar.AM_PM, Calendar.SHORT,
//					Locale.US));
			sb.append(new DateFormatSymbols().getAmPmStrings()[c.get(GregorianCalendar.AM_PM)]);		

		return sb.toString();
	}
	
	private String getPrettyTime1()
	{
		StringBuilder sb1 = new StringBuilder();

		if (DateFormat.is24HourFormat(this))
			sb1.append(c1.get(Calendar.HOUR_OF_DAY));
		else
		{
			if (c1.get(Calendar.HOUR) == 12 || c1.get(Calendar.HOUR) == 0)
				sb1.append(12);
			else
				sb1.append(c1.get(Calendar.HOUR));
		}

		sb1.append(":");
		if (Integer.toString(c1.get(Calendar.MINUTE)).length() == 1)
			sb1.append("0" + c1.get(Calendar.MINUTE));
		else
			sb1.append(c1.get(Calendar.MINUTE));
		if (!DateFormat.is24HourFormat(this))
//			sb.append(c.getDisplayName(Calendar.AM_PM, Calendar.SHORT,
//					Locale.US));
			sb1.append(new DateFormatSymbols().getAmPmStrings()[c1.get(GregorianCalendar.AM_PM)]);		

		return sb1.toString();
	}	
	
	
	
 	public void listActivity(View view) {
        Intent i = new Intent(this, listActivity.class);
        startActivity(i);
        SharedPreferences myPreference=PreferenceManager.getDefaultSharedPreferences(this);
        if(myPreference.getBoolean("animation", true)) {
        	if(myPreference.getString("interpolator","1").equals("3")){
        		overridePendingTransition(R.anim.push_right_in3, R.anim.push_right_out3);
        	}else if(myPreference.getString("interpolator","1").equals("2")){
        		overridePendingTransition(R.anim.push_right_in2, R.anim.push_right_out2);
        	}else{
        		overridePendingTransition(R.anim.push_right_in1, R.anim.push_right_out1);
        	}
        }
	}
	
	public void chartActivity(View view) {
        Intent i = new Intent(this, chartActivity.class);
        startActivity(i);
        SharedPreferences myPreference=PreferenceManager.getDefaultSharedPreferences(this);
        if(myPreference.getBoolean("animation", true)) {
        	if(myPreference.getString("interpolator","1").equals("3")){
        		overridePendingTransition(R.anim.push_right_in3, R.anim.push_right_out3);
        	}else if(myPreference.getString("interpolator","1").equals("2")){
        		overridePendingTransition(R.anim.push_right_in2, R.anim.push_right_out2);
        	}else{
        		overridePendingTransition(R.anim.push_right_in1, R.anim.push_right_out1);
        	}
        }
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
	
	
	
}