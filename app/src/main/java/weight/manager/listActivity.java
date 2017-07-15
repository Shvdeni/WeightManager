package weight.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;
import android.view.View.OnClickListener;

public class listActivity extends ListActivity{
	
	ListView listView;
	SqlAdapter adapter;
	
	final Context context = this;
	
	private int carentToRemove = 0; 
	
//	private Cursor cursor;
    int quitFeedback;
    SharedPreferences preferences2;
 //   private AdView adView;

	private AdView mAdView;
	
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
		setContentView(R.layout.list);

		/////////////////	     Ү襠 adView
		// Initialize the Mobile Ads SDK.
		MobileAds.initialize(this, "ca-app-pub-5156952701690991~5532968064");

		// Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
		// values/strings.xml.
		mAdView = (AdView) findViewById(R.id.ad_view);
		com.google.android.gms.ads.AdRequest adRequest = new com.google.android.gms.ads.AdRequest.Builder()
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
		
		
		
		
		listView = getListView();
		adapter = new SqlAdapter(this);
	//	adapter = WeightManagerActivity.adapter;
		
		setListAdapter(adapter);

		listView.setSelection(adapter.getCount() - 1);
		
		SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(this);
		quitFeedback = preferences1.getInt("quitFeedback", 3);
		
		preferences2 = PreferenceManager.getDefaultSharedPreferences(this);
		
/*
/////////////////////		
        cursor = SqlAdapter.getAllEntries();
        
      ///	Cursor cursor = db.query(...);
      ///	while (cursor.moveToNext()) {
      ///	    // use cursor
      ///	}	        
        
        startManagingCursor(cursor);
 
        String[] from = new String[] { "date", "weight", "waistsize" };
        int[] to = new int[] { R.id.textView2, R.id.textView1, R.id.textView3 };

        // ������ �������� ������� ������� � ��������� ��� ��� ����������� ����� ������

        SimpleCursorAdapter notes = new SimpleCursorAdapter(this,

                R.layout.linearlayout_list_item, cursor, from, to);

        setListAdapter(notes);		
		
/////////////////////
*/		
		
		
		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				carentToRemove = position;

		//		Toast.makeText(listActivity.this, "Itemmmmmm in position " + position + " clicked",
		//				Toast.LENGTH_LONG).show();
				view.setSelected(true);
				
				
///////////////			start dialog
				
				// custom dialog
				final Dialog dialog = new Dialog(context);
				dialog.setContentView(R.layout.custom_dialog);
				
				dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
				
				weight selectedWeight = adapter.getItem(carentToRemove);
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(selectedWeight.getDate());
			    Date date = cal.getTime();
			   // SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			    java.text.DateFormat formatter =
			    	    android.text.format.DateFormat.getDateFormat(context);
			    String dateStr = formatter.format(date);
			    
				
		//		dialog.setTitle(dateStr);
	 
				// set the custom dialog components - text, image and button
				TextView text = (TextView) dialog.findViewById(R.id.textView1);
				text.setText(dateStr);
			//	ImageView image = (ImageView) dialog.findViewById(R.id.image);
			//	image.setImageResource(R.drawable.ic_launcher);
	 
				Button dialogButton = (Button) dialog.findViewById(R.id.button22);
				// Edit
				dialogButton.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						dialog.dismiss();
						
						EditActivity();	
				        
					}
				});
				// delete
				Button dialogButton1 = (Button) dialog.findViewById(R.id.button23);
				dialogButton1.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						
						weight selectedWeight = adapter.getItem(carentToRemove);

						adapter.removeItem(selectedWeight);
						
						dialog.dismiss();
						
						setListAdapter(adapter);						
						
					}
				});				
	 
				dialog.show();				
				
///////////////			end dialog	
				return true;
			}
		});		
		
	}

	
	
//////////menu options start
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
		  MenuInflater inflater = getMenuInflater();
		  inflater.inflate(R.menu.menu_list_activity, menu);
		  return true;
		}    
		
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.im_port:

         //   	final CharSequence[] items = {"Foo", "Bar", "Baz", "Bar", "Baz", "Bar", "Baz", "Bar", "Baz", "Bar", "Baz", "Bar", "Baz"};
            	
            	ArrayList myList = new ArrayList<String>();
            	
            	try {
            	    File file = new File( "/sdcard/WeightManager" ) ;       
            	    File list[] = file.listFiles();

            	    for( int i=0; i< list.length; i++)
            	    {
            	    	if(list[i].getName().endsWith(".csv")){
            	            myList.add( list[i].getName() );
            	    	}
            	    }
                }
                catch (Exception e){
                    Toast.makeText(listActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            	
            	final CharSequence[] items = (CharSequence[]) myList.toArray(new CharSequence[myList.size()]);

            	AlertDialog.Builder builder = new AlertDialog.Builder(this);
            	builder.setTitle(getString(R.string.select_import_file));
            	builder.setItems(items, new DialogInterface.OnClickListener() {
            	    public void onClick(DialogInterface dialog, int item) {
            	         // Do something with the selection
            	    //	Toast.makeText(listActivity.this, items[item] + "",Toast.LENGTH_SHORT).show();
            	    	
            	    	

            	    	
            	    	
            	    	
            	        File file = new File("/sdcard/WeightManager",items[item] + "");
            	        // i have kept text.txt in the sd-card

            	        if(file.exists())   // check if file exist
            	        {
            	        	  //Read text from file
            	        //    StringBuilder text = new StringBuilder();

            	            try {
            	                BufferedReader br = new BufferedReader(new FileReader(file));
            	                String line;
            	                int LineNamber = 0;
            	                int LineNamber1 = 0;
            	                int dbver = 0;
            	                while ((line = br.readLine()) != null) {
            	                	
            	                	LineNamber1++;
            	                	if((LineNamber1 == 1) && (line.length() >= 7)){
            	                		float dbver1 = Float.valueOf(line.substring(6, 7).trim());
            	                		dbver = (int) dbver1;
            	                	//	Toast.makeText(listActivity.this, dbver + "",Toast.LENGTH_SHORT).show();
            	                	}            	                	
            	                	
            	                	if((line.length() >= 34) && (line.substring(10, 11).equals(";")) && (line.substring(16, 17).equals(";")) && (line.substring(22, 23).equals(";")) && (line.substring(28, 29).equals(";"))          ){
            	                //	if(line.length() == 34){
            	                		
            	                		
            	                	
            	                		LineNamber++;
            	                	
	            	                	String Date = line.substring(0, 10);
	            	                	
	            	                	String YEAR = line.substring(0, 4);
	            	                	String MONTH = line.substring(5, 7);
	            	                	String DAY_OF_MONTH = line.substring(8, 10);
	            	                	
	            	                	String weight = line.substring(11, 16).trim();
	            	                	String Waist = line.substring(17, 22).trim();
	            	                	String Chest = line.substring(23, 28).trim();
	            	                	String Hip = line.substring(29, 34).trim();
	            	                	
	            	                	float WaistF = Float.valueOf(Waist);
	            	                	float ChestF = Float.valueOf(Chest);
	            	                	float HipF = Float.valueOf(Hip);
	            	                	float NeckF = 0;
	            	                	
	            	                	WaistF = WaistF * 10;
	            	                	ChestF = ChestF * 10;
	            	                	HipF = HipF * 10;
	            	                	

	            	                	if(dbver == 2){
		            	                	try{
			            	                	if(line.length() >= 40){
			            	                		String Neck = line.substring(35, 40).trim();
			            	                		NeckF = Float.valueOf(Neck);
			            	                		NeckF = NeckF * 10;
			            	                	}	        
		            	                	}catch (Exception e){
		            	                        
		            	                    }
	            	                	}	            	                	

	            	                	
	            	        			Calendar cal = Calendar.getInstance();
	            	        			//	cal.set(Calendar.YEAR, dpDate.getYear());
	            	        			//	cal.set(Calendar.MONTH, dpDate.getMonth());
	            	        			//	cal.set(Calendar.DAY_OF_MONTH, dpDate.getDayOfMonth());

            	        				cal.set(Calendar.YEAR, Integer.parseInt(YEAR));
            	        				cal.set(Calendar.MONTH, Integer.parseInt(MONTH) - 1);
            	        				cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(DAY_OF_MONTH));			
            	        				cal.set(Calendar.HOUR_OF_DAY, 13);
            	        				cal.set(Calendar.MINUTE, 59);
            	        				cal.set(Calendar.SECOND, 59);
            	        				cal.set(Calendar.MILLISECOND, 59);
            	        				long date = cal.getTimeInMillis();		            	                	
	            	                	
	            	                	

	            	                	boolean Exists = adapter.checkExists1(date);
	            	                	
	            						if (Exists == false){
	            							// takoi daty nety
	            						//	Log.i(TAG, "checkExists - not Exists");
	            							
	            						    weight newWeight = new weight(date, weight + "", (long)WaistF, (long)ChestF, (long)HipF, (long)NeckF);
	            						    adapter.addItem(newWeight);
	            						}else{
	            							adapter.updateItem1(date, weight + "", (int)WaistF, (int)ChestF, (int)HipF, (int)NeckF);
	            						}
	            	                	
	            	                	
	            	                	
	            	                	
	            	                //	Toast.makeText(listActivity.this, line.substring(10, 11).equals(";") + "",Toast.LENGTH_SHORT).show();
            	                	
            	                	}
            	                	//////////////////////////////////////////////////////////////
            	                	
            	                	
            	                	
            	                	
            	                	
            	                //    text.append(line);
            	                //    text.append('n');
            	                }
            	                
            	                Toast.makeText(listActivity.this, getString(R.string.lines_imported) + LineNamber,Toast.LENGTH_SHORT).show();
            	                
            	            }
            	            catch (IOException e) {
            	                //You'll need to add proper error handling here
            	            }
            	            //Set the text
            	          //  tv.setText(text);
            	        }
            	        else
            	        {
            	        //	tv.setText("Sorry file doesn't exist!!");
            	        }
            	    	
            	    	
            	    	
            	    	
            	    	
            	    	
            	    	
            	    	
            	    	
            	    	
            	    	
            	    }
            	});
            	AlertDialog alert1 = builder.create();
            	alert1.show();
            	
            	
            	

                break;
            case R.id.save_database:
            	
                try {
                	
                	File mediaDir = new File("/sdcard/WeightManager");
                	if (!mediaDir.exists()){
                	    mediaDir.mkdir();
                	}
                	
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();

                	
                    File myFile = new File("/sdcard/WeightManager/" + dateFormat.format(date) + ".csv");
                    myFile.createNewFile();
                    FileOutputStream fOut = new FileOutputStream(myFile);
                    OutputStreamWriter myOutWriter =new OutputStreamWriter(fOut);
                    
                    
                    
                    
                    Cursor  cursor = SqlAdapter.getAllEntries();
                    
                    
        			myOutWriter.append("dbver:2");
                    myOutWriter.append("\r\n");
        			myOutWriter.append("mode:1");
                    myOutWriter.append("\r\n");                     
                    
                    if (cursor.moveToFirst()) {
                        while (cursor.isAfterLast() == false) {
                        	
                        	
                        	
                			long date1 = cursor.getLong(1);
                			
                            DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                            Date date2 = new Date();
                            date2.setTime(date1);
                            
                            String dat = dateFormat2.format(date2);
                			String weight = cursor.getString(2);

                			
                			
                			double Waist = (cursor.getLong(3));
                			Waist = Waist / 10;
                			
                			double Chest = cursor.getLong(4);
                			Chest = Chest / 10;
                			
                			double Hip = cursor.getLong(5);
                			Hip = Hip / 10;
                			
                			double Neck = cursor.getLong(6);
                			Neck = Neck / 10;                			
                			
                			
                			
                			
                			while (weight.length() < 5) weight = " " + weight;
                			
                			String Waist1 = Waist + "";
                			while (Waist1.length() < 5) Waist1 = " " + Waist1;
                			
                			String Chest1 = Chest + "";
                			while (Chest1.length() < 5) Chest1 = " " + Chest1;
                			
                			String Hip1 = Hip + "";
                			while (Hip1.length() < 5) Hip1 = " " + Hip1;
                			
                			String Neck1 = Neck + "";
                			while (Neck1.length() < 5) Neck1 = " " + Neck1;                			
                			
                        	
                		//	myOutWriter.append(dat + ";" + weight + ";" + Waist + ";" + Chest + ";" + Hip);
                			myOutWriter.append(dat + ";" + weight + ";" + Waist1 + ";" + Chest1 + ";" + Hip1 + ";" + Neck1);
                			
                            myOutWriter.append("\r\n");
                        	
                            cursor.moveToNext();
                        }
                    }                    

                    
                    
                    
                    myOutWriter.close();
                    fOut.close();
                    cursor.close();
                    
                    Toast.makeText(listActivity.this, getString(R.string.done_writing_sd) + ": /sdcard/WeightManager/" + dateFormat.format(date) + ".csv", Toast.LENGTH_LONG).show();

                }
                catch (Exception e){
                    Toast.makeText(listActivity.this, "error:" + e.getMessage(),Toast.LENGTH_SHORT).show();
                }

          	  break;
          	  
            case R.id.delete_all:
            	
            	
            	
  	        	AlertDialog.Builder alert = new AlertDialog.Builder(this);
  	       // 	alert.setTitle("Title");
  	        	alert.setMessage(getString(R.string.delete_all_data));        	  
  	
  	        	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
  	        		public void onClick(DialogInterface dialog, int whichButton) {

  	        			deleteAll();
  	        			
  	        		  }
  	        		});
  	
  	        		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
  	        		  public void onClick(DialogInterface dialog, int whichButton) {
  	        		    // Canceled.
  	        		  }
  	        		});

  	        	alert.show();            	
            	


                break;          	  
        }
        return true;
    }


//////////menu options end    	
	

		
		public void deleteAll() {
			
			
        	SqlAdapter.deleteAll();
        	
        	
        	Toast.makeText(listActivity.this, getString(R.string.all_data_deleted) + "", Toast.LENGTH_LONG).show();


			//go to add activity

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
	
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
//		weight selectedWeight = adapter.getItem(position);
//		currentName.setText(selectedName.getName());
		
//		Toast.makeText(this, "Item in position " + position + " clicked",
//				Toast.LENGTH_LONG).show();
//		v.setSelected(true);
		
        Intent i = new Intent(this, WeightManagerActivity.class);
        i.putExtra("position", position);
//        i.putExtra("Date", Day);
//        i.putExtra("Month", position);
//        i.putExtra("Year", position);
        
        WeightManagerActivity.value1 = position;        
        
        startActivity(i);		
		
	}
	
	public void settingsClick(View view) {
		openOptionsMenu();
	}	
	
	public void EditActivity() {
        Intent i = new Intent(this, WeightManagerActivity.class);
     //   Toast.makeText(this, "-" + carentToRemove, Toast.LENGTH_LONG).show();
        i.putExtra("position", carentToRemove);
        
        WeightManagerActivity.value1 = carentToRemove;
        
        startActivity(i);
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
	
	
	protected void onResume() {
		super.onResume();

		if (mAdView != null) {
			mAdView.resume();
		}
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		
		Resources res = getResources();
//		String text = String.format(res.getString(R.string.welcome_messages), username, mailCount);
		String Length =  res.getString(R.string.centimetre_short);
		String weight =  res.getString(R.string.pound_short);
		
	//	ListPrefWeight = 
		WeightManagerActivity.publicWeight = prefs.getString("weightUnit", weight);
	//	ListPrefLength = 
		WeightManagerActivity.publicWaistSize = prefs.getString("lengthUnit", Length);

	}
	@Override
	public void onStop() {
		WeightManagerActivity.onStartOnStopSumm = WeightManagerActivity.onStartOnStopSumm - 1;
		super.onStop();
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
	public void onStart() {
		WeightManagerActivity.onStartOnStopSumm = WeightManagerActivity.onStartOnStopSumm + 1;
		super.onStart();
	}	
	
	
}
