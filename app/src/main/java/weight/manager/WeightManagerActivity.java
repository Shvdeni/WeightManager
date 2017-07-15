package weight.manager;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;
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
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdListener;

public class WeightManagerActivity extends Activity{
    /** Called when the activity is first created. */
	//Toast.makeText(this, "onStop", Toast.LENGTH_LONG).show();
	
	private static final String TAG = "WeightManagerActivity";
	
//	private InterstitialAd interstitialAds = null;

	private AdView mAdView;

	private InterstitialAd mInterstitialAd;
	
	SqlAdapter adapter;
//	private DatePicker dpDate;
//	private DateDrumPicker dPicker;
//	EditText WeightEdit;
	TextView textView1, textView5, textView3, textView4, BMI, BRM, Fat, TextViewchest2, TextViewchest3,
			 TextViewHip2, TextViewHip3, TextViewNeck2, TextViewNeck3;
	
	public static int value1 = -1;
	
	final Context context = this;
	
	private int ScreenSizeWidth, ScreenSizeHeight;

	double weighttt;
	private volatile boolean mStartFlag = false;
	
	private Button BtnInrement, BtnDecrement, BtnDecrementWaistSize, BtnInrementWaistSize,
				   BtnDecrementChest, BtnInrementChest, BtnDecrementHip, BtnInrementHip, BtnDecrementNeck, BtnInrementNeck;

	private final int MINIMUM = 0;
	private final int MAXIMUM = 999;
	private final long REPEAT_DELAY = 50;
	private Handler repeatUpdateHandler = new Handler();
	private boolean autoIncrement = false;
	private boolean autoDecrement = false;
	
	
	private final int MINIMUMWaistSize = 0;
	private final int MAXIMUMWaistSize  = 999;

//	private final long REPEAT_DELAY_WaistSize = 50;
	private Handler repeatUpdateHandlerWaistSize = new Handler();
	private boolean autoIncrementWaistSize = false;
	private boolean autoDecrementWaistSize = false;

	
	private final int MINIMUMChest = 0;
	private final int MAXIMUMChest = 999;
	private Handler repeatUpdateHandlerChest = new Handler();
	private boolean autoIncrementChest = false;
	private boolean autoDecrementChest = false;
	
	private final int MINIMUMHip = 0;
	private final int MAXIMUMHip = 999;
	private Handler repeatUpdateHandlerHip = new Handler();	
	private boolean autoIncrementHip = false;
	private boolean autoDecrementHip = false;
	
	private final int MINIMUMNeck = 0;
	private final int MAXIMUMNeck = 999;
	private Handler repeatUpdateHandlerNeck = new Handler();		
	private boolean autoIncrementNeck = false;
	private boolean autoDecrementNeck = false;	
	
	
	String ListPrefWeight, ListPrefLength;
	
	public static String publicWeight = "", publicWeight1 = "";
	public static String publicWaistSize = "", publicWaistSize1 = "";
	int quitFeedback;
	SharedPreferences preferences2;
//	private AdView adView;
	
	private int password_entered = 0;
	public static int onStartOnStopSumm = 0;
	Dialog dialog1;
	
	int mYear;
	int mMonth;
	int mDay;
	
	
    WheelView month;
    WheelView year;
    WheelView day;	


    int i;
	
//	public static int DateFormat111 = 1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Display display = getWindowManager().getDefaultDisplay(); 
        ScreenSizeWidth = display.getWidth();
        ScreenSizeHeight = display.getHeight();           
        

        

/*        
        
	//		������� �� �����
	/////////////////	     �������� adView
	
	Random r = new Random();
	int i1=r.nextInt(10);
	//if(i1 == 1){
	    LinearLayout layout = (LinearLayout)findViewById(R.id.mainAdViewLayout);		
		
	    adView = new AdView(this, AdSize.BANNER, "a1502b4baa4b66f");
	
	    // ����� � LinearLayout (��������������, ��� ��� ��������
	    // ������� android:id="@+id/mainLayout"
	
	
	    // ���������� adView
	    layout.addView(adView);
	
	    // ������������� ������ ������� �� �������� ������ � �����������
	    adView.loadAd(new AdRequest());
	//}
	/////////////////	    End �������� adView	    
*/      
        
       
        

        
        SharedPreferences mSettings1 = PreferenceManager.getDefaultSharedPreferences(this);
		
		// ����� ��������
		int CountsOfStart = mSettings1.getInt("CountsOfStart", 0);
		CountsOfStart = CountsOfStart + 1;
		SharedPreferences.Editor editor = mSettings1.edit();
	    editor.putInt("CountsOfStart", CountsOfStart);
	    editor.commit();
	    // ����� ����� ��������        

		SharedPreferences prefs555 = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		if(prefs555.getString("DateFormat", "0").equals("0")){
			if(Locale.getDefault().getLanguage().equals("ru")){
				SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(this); 
				SharedPreferences.Editor editor1 = mSettings.edit();
				editor1.putString("DateFormat", "2");
    	        editor1.commit();
			}else{
				SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(this); 
				SharedPreferences.Editor editor1 = mSettings.edit();
				editor1.putString("DateFormat", "1");
    	        editor1.commit();				
			}
		}

		
//		SharedPreferences prefs555 = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		try{
			DateDrumPicker.DateFormat111 = Integer.parseInt(prefs555.getString("DateFormat", "1"));
		}catch (Exception e){
           Log.e("Error", "Cannot parseInt", e);
            DateDrumPicker.DateFormat111 = 1;
        }		
		
		
//		DateDrumPicker.DateFormat111 = 3;
        
/* 
    SharedPreferences prefs111 = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
//  prefs111.getString("Lang", "defolt")
  	String languageToLoad  = prefs111.getString("Lang", "defolt");
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
    setContentView(R.layout.main);



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


		//start Interstitial Ad
		mInterstitialAd = new InterstitialAd(this);
		// Defined in res/values/strings.xml
		mInterstitialAd.setAdUnitId("ca-app-pub-5156952701690991/8486434466");

		mInterstitialAd.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				if (mInterstitialAd.isLoaded()) {
					mInterstitialAd.show();
				}
			}
		});

		AdRequest adRequest1 = new AdRequest.Builder()
				.build();

		mInterstitialAd.loadAd(adRequest1);

		//end Interstitial Ad


        
/*
        dPicker = (DateDrumPicker) findViewById(R.id.datepicker);
      //  dPicker.set
        
//        dPicker.setYear(mYear);
//        dPicker.setMonth(mMonth);
//        dPicker.setDay(mDay);
        
		dPicker.setOnDateChangedListener(new OnDateChangedListener() {
			public void onDateChanged(DatePicker view, int year,
					int monthOfYear, int dayOfMonth) {
				mYear=year;
				mMonth=monthOfYear - 1;
			//	mMonth=monthOfYear;
				mDay=dayOfMonth;
			}
		});        
        
*/        

    
    
    
    
    
    
    

    
    
    
    
    
    
    
        
        

        
        
        
        
		
//		dpDate = (DatePicker) findViewById(R.id.datePicker1);
	//	WeightEdit = (EditText) findViewById(R.id.editText1);
		textView1 = (TextView) findViewById(R.id.textView1);
		textView5 = (TextView) findViewById(R.id.textView5);
		
		textView3 = (TextView) findViewById(R.id.textView3);
		textView4 = (TextView) findViewById(R.id.textView4);
		TextViewchest3 = (TextView) findViewById(R.id.TextViewchest3);
		TextViewHip3 = (TextView) findViewById(R.id.TextViewHip3);
		
		TextViewchest2 = (TextView) findViewById(R.id.TextViewchest2);
		TextViewchest3 = (TextView) findViewById(R.id.TextViewchest3);
		
		TextViewHip2 = (TextView) findViewById(R.id.TextViewHip2);
		TextViewNeck2 = (TextView) findViewById(R.id.TextViewNeck2);
		TextViewNeck3 = (TextView) findViewById(R.id.TextViewNeck3);
		
		
		BMI = (TextView) findViewById(R.id.textView7);
		BRM = (TextView) findViewById(R.id.textView8);
		Fat = (TextView) findViewById(R.id.textViewFat);
		
		
		BtnInrement = (Button) findViewById(R.id.button25);
		BtnDecrement = (Button) findViewById(R.id.button24);
		BtnDecrementWaistSize  = (Button) findViewById(R.id.button26);
		BtnInrementWaistSize = (Button) findViewById(R.id.button27);
		
		BtnDecrementChest  = (Button) findViewById(R.id.Buttonchest1);
		BtnInrementChest = (Button) findViewById(R.id.Buttonchest2);
		BtnDecrementHip  = (Button) findViewById(R.id.ButtonHip1);
		BtnInrementHip = (Button) findViewById(R.id.ButtonHip2);
		
		BtnDecrementNeck  = (Button) findViewById(R.id.ButtonNeck1);
		BtnInrementNeck = (Button) findViewById(R.id.ButtonNeck2);
		

		SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(this);
		quitFeedback = preferences1.getInt("quitFeedback", 3);		
	//	Toast.makeText(this, quitFeedback + "", Toast.LENGTH_LONG).show();
		
		preferences2 = PreferenceManager.getDefaultSharedPreferences(this);
		
	//	ListPreference listPreference = (ListPreference) findPreference("lpBirim");
	//	CharSequence currText = listPreference.getEntry();
		
	



//		ListPreference listPreference = (ListPreference) prefs.findPreference("weightUnit");
//		CharSequence currText = listPreference.getEntry();
//		String currValue = listPreference.getValue();
		
		

      ///////////////	start	menu weight				
		textView1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try{

    				// custom dialog
    				final Dialog dialog = new Dialog(context);
    				dialog.setContentView(R.layout.custom_dialog_weight);
    			//	dialog.setTitle(getString(R.string.Weight));
    				
    				dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
    	 
    				Button dialogButton = (Button) dialog.findViewById(R.id.button22);
    				TextView TextView1 = (TextView) dialog.findViewById(R.id.textView1);
    				
    				if(publicWeight.equals("1")){
    					TextView1.setText(getString(R.string.pound_short));
    				}else{
    					TextView1.setText(getString(R.string.kilogram_short));
    				}
    					
    				// Edit
    				dialogButton.setOnClickListener(new OnClickListener() {
    					public void onClick(View v) {
    						dialog.dismiss();
    						
    	    				EditText text = (EditText) dialog.findViewById(R.id.editText1);
    	    				String text1 = text.getText().toString().trim() + "";
    	    				
    	    				if (text1 == ""){
    	    					text1 = "0.0";
    	    				}
    	    				if (Double.parseDouble(text1) > MAXIMUM){
    	    					text1 = MAXIMUM + "";
    	    				}
    	    					

    					    double weight1 =  Double.parseDouble(text1);
    					    weight1 = weight1 * 10;
    					    int weight11 = (int) Math.round(weight1);
    					    double weight111 = weight11;
    					    weight111 = weight111 / 10;

    					    textView1.setText(weight111 + "");
    						CalculateBMI();
    						CalculateBMR();
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
        });
	      ///////////////	end	menu weight
		
	      ///////////////	start	menu WaistSize				
			textView5.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                try{


	    				
	    				// custom dialog
	    				final Dialog dialog = new Dialog(context);
	    				dialog.setContentView(R.layout.custom_dialog_waistsize);
	    			//	dialog.setTitle(getString(R.string.Waist_size));
	    				
	    				dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
	    	 
	    				Button dialogButton = (Button) dialog.findViewById(R.id.button22);
	    				TextView TextView1 = (TextView) dialog.findViewById(R.id.textView1);
	    				
	    				if(publicWaistSize.equals("2")){
	    					TextView1.setText(getString(R.string.inch_short));
	    				}else{
	    					TextView1.setText(getString(R.string.centimetre_short));
	    				}
	    				
	    				
	    				
	    				// Edit
	    				dialogButton.setOnClickListener(new OnClickListener() {
	    					public void onClick(View v) {
	    						dialog.dismiss();
	    						
	    	    				EditText text = (EditText) dialog.findViewById(R.id.editText1);
	    	    				String text1 = text.getText().toString().trim() + "";
	    	    				
	    	    				if (text1 == ""){
	    	    					text1 = "0.0";
	    	    				}
	    	    				
	    	    				if(Double.parseDouble(text1) > MAXIMUMWaistSize){
	    	    					text1 = MAXIMUMWaistSize + "";
	    	    				}
	    	    				
	    	    			    double WaistSize1 =  Double.parseDouble(text1);
	    	    			    WaistSize1 = WaistSize1 * 10;
	    	    			    int WaistSize11 = (int) Math.round(WaistSize1);
	    	    			    double WaistSize111 = WaistSize11;
	    	    			    WaistSize111 = WaistSize111 / 10;
	    	    			    
	    	    				textView5.setText( WaistSize111 + "" );
	    	    				CalculateFat();
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
	        });
		      ///////////////	end	menu WaistSize						
	
			
			
		      ///////////////	start	menu chest				
			TextViewchest2.setOnClickListener(new View.OnClickListener() {
		            public void onClick(View v) {
		                try{


		    				
		    				// custom dialog
		    				final Dialog dialog = new Dialog(context);
		    				dialog.setContentView(R.layout.custom_dialog_waistsize);
		    			//	dialog.setTitle(getString(R.string.Waist_size));
		    				
		    				dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
		    	 
		    				Button dialogButton = (Button) dialog.findViewById(R.id.button22);
		    				TextView TextView1 = (TextView) dialog.findViewById(R.id.textView1);
		    				
		    				if(publicWaistSize.equals("2")){
		    					TextView1.setText(getString(R.string.inch_short));
		    				}else{
		    					TextView1.setText(getString(R.string.centimetre_short));
		    				}
		    				
		    				
		    				
		    				// Edit
		    				dialogButton.setOnClickListener(new OnClickListener() {
		    					public void onClick(View v) {
		    						dialog.dismiss();
		    						
		    	    				EditText text = (EditText) dialog.findViewById(R.id.editText1);
		    	    				String text1 = text.getText().toString().trim() + "";
		    	    				
		    	    				if (text1 == ""){
		    	    					text1 = "0.0";
		    	    				}

		    	    				if(Double.parseDouble(text1) > MAXIMUMChest){
		    	    					text1 = MAXIMUMChest + "";
		    	    				}
		    	    				
		    	    			    double Chest1 =  Double.parseDouble(text1);
		    	    			    Chest1 = Chest1 * 10;
		    	    			    int Chest11 = (int) Math.round(Chest1);
		    	    			    double Chest111 = Chest11;
		    	    			    Chest111 = Chest111 / 10;
		    	    			    
		    	    			    TextViewchest2.setText( Chest111 + "" );
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
		        });
			      ///////////////	end	menu chest
			
			
		      ///////////////	start	menu hip				
			TextViewHip2.setOnClickListener(new View.OnClickListener() {
		            public void onClick(View v) {
		                try{


		    				
		    				// custom dialog
		    				final Dialog dialog = new Dialog(context);
		    				dialog.setContentView(R.layout.custom_dialog_waistsize);
		    			//	dialog.setTitle(getString(R.string.Waist_size));
		    				
		    				dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
		    	 
		    				Button dialogButton = (Button) dialog.findViewById(R.id.button22);
		    				TextView TextView1 = (TextView) dialog.findViewById(R.id.textView1);
		    				
		    				if(publicWaistSize.equals("2")){
		    					TextView1.setText(getString(R.string.inch_short));
		    				}else{
		    					TextView1.setText(getString(R.string.centimetre_short));
		    				}
		    				
		    				
		    				
		    				// Edit
		    				dialogButton.setOnClickListener(new OnClickListener() {
		    					public void onClick(View v) {
		    						dialog.dismiss();
		    						
		    	    				EditText text = (EditText) dialog.findViewById(R.id.editText1);
		    	    				String text1 = text.getText().toString().trim() + "";
		    	    				
		    	    				if (text1 == ""){
		    	    					text1 = "0.0";
		    	    				}

		    	    				if(Double.parseDouble(text1) > MAXIMUMHip){
		    	    					text1 = MAXIMUMHip + "";
		    	    				}
		    	    				
		    	    			    double Chest1 =  Double.parseDouble(text1);
		    	    			    Chest1 = Chest1 * 10;
		    	    			    int Chest11 = (int) Math.round(Chest1);
		    	    			    double Chest111 = Chest11;
		    	    			    Chest111 = Chest111 / 10;
		    	    			    
		    	    			    TextViewHip2.setText( Chest111 + "" );
		    	    			    CalculateFat();
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
		        });
			      ///////////////	end	menu hip												
			
		      ///////////////	start	menu Neck				
			TextViewNeck2.setOnClickListener(new View.OnClickListener() {
		            public void onClick(View v) {
		                try{


		    				
		    				// custom dialog
		    				final Dialog dialog = new Dialog(context);
		    				dialog.setContentView(R.layout.custom_dialog_waistsize);
		    			//	dialog.setTitle(getString(R.string.Waist_size));
		    				
		    				dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
		    	 
		    				Button dialogButton = (Button) dialog.findViewById(R.id.button22);
		    				TextView TextView1 = (TextView) dialog.findViewById(R.id.textView1);
		    				
		    				if(publicWaistSize.equals("2")){
		    					TextView1.setText(getString(R.string.inch_short));
		    				}else{
		    					TextView1.setText(getString(R.string.centimetre_short));
		    				}
		    				
		    				
		    				
		    				// Edit
		    				dialogButton.setOnClickListener(new OnClickListener() {
		    					public void onClick(View v) {
		    						dialog.dismiss();
		    						
		    	    				EditText text = (EditText) dialog.findViewById(R.id.editText1);
		    	    				String text1 = text.getText().toString().trim() + "";
		    	    				
		    	    				if (text1 == ""){
		    	    					text1 = "0.0";
		    	    				}

		    	    				if(Double.parseDouble(text1) > MAXIMUMNeck){
		    	    					text1 = MAXIMUMNeck + "";
		    	    				}
		    	    				
		    	    			    double Chest1 =  Double.parseDouble(text1);
		    	    			    Chest1 = Chest1 * 10;
		    	    			    int Chest11 = (int) Math.round(Chest1);
		    	    			    double Chest111 = Chest11;
		    	    			    Chest111 = Chest111 / 10;
		    	    			    
		    	    			    TextViewNeck2.setText( Chest111 + "" );
		    	    			    CalculateFat();
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
		        });
			      ///////////////	end	menu Neck							
			
			
			
		
///////////////start Inrement weight
		
		
//		BtnInrement
		BtnInrement.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	increment();
            }
        });

		// Auto increment for a long click
		BtnInrement.setOnLongClickListener( 
				new View.OnLongClickListener(){
					public boolean onLongClick(View arg0) {
						autoIncrement = true;
						repeatUpdateHandler.post( new RepetetiveUpdater() );
						return false;
					}
				}
		);
		
		// When the button is released, if we're auto incrementing, stop
		BtnInrement.setOnTouchListener( new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if( (event.getAction() == MotionEvent.ACTION_UP && autoIncrement) || (event.getAction() == MotionEvent.ACTION_CANCEL && autoIncrement) ){
					autoIncrement = false;
				}
				return false;
			}
		});
		
		
		// Decrement once for a click
		BtnDecrement.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	decrement();
            }
        });		
		// Auto Decrement for a long click
		BtnDecrement.setOnLongClickListener( 
				new View.OnLongClickListener(){
					public boolean onLongClick(View arg0) {
						autoDecrement = true;
						repeatUpdateHandler.post( new RepetetiveUpdater() );
						return false;
					}
				}
		);
		
		// When the button is released, if we're auto decrementing, stop
		BtnDecrement.setOnTouchListener( new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if( (event.getAction() == MotionEvent.ACTION_UP && autoDecrement) || (event.getAction() == MotionEvent.ACTION_CANCEL && autoDecrement) ){
					autoDecrement = false;
				}
				return false;
			}
		});
    

	    	
///////////////end Inrement weight

///////////////start Inrement WaistSize
		
		
//	BtnInrement
		BtnInrementWaistSize.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
      	incrementWaistSize();
      }
  });

	// Auto increment for a long click
		BtnInrementWaistSize.setOnLongClickListener( 
			new View.OnLongClickListener(){
				public boolean onLongClick(View arg0) {
					autoIncrementWaistSize = true;
					repeatUpdateHandlerWaistSize.post( new RepetetiveUpdaterWaistSize() );
					return false;
				}
			}
	);
	
		
		
		
	// When the button is released, if we're auto incrementing, stop
		BtnInrementWaistSize.setOnTouchListener( new View.OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) {
			if( (event.getAction() == MotionEvent.ACTION_UP && autoIncrementWaistSize) || (event.getAction() == MotionEvent.ACTION_CANCEL && autoIncrementWaistSize) ){
				autoIncrementWaistSize = false;
			}
			return false;
		}
	});
	
	
	// Decrement once for a click
		BtnDecrementWaistSize.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
      	decrementWaistSize();
      }
  });		
	// Auto Decrement for a long click
		BtnDecrementWaistSize.setOnLongClickListener( 
			new View.OnLongClickListener(){
				public boolean onLongClick(View arg0) {
					autoDecrementWaistSize = true;
					repeatUpdateHandlerWaistSize.post( new RepetetiveUpdaterWaistSize() );
					return false;
				}
			}
	);
	
	// When the button is released, if we're auto decrementing, stop
		BtnDecrementWaistSize.setOnTouchListener( new View.OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) {
			if( (event.getAction() == MotionEvent.ACTION_UP && autoDecrementWaistSize) || (event.getAction() == MotionEvent.ACTION_CANCEL && autoDecrementWaistSize) ){
				autoDecrementWaistSize = false;
			}
			return false;
		}
	});


  	
///////////////end Inrement WaistSize
///////////////start Inrement Chest
		

//BtnInrement
	BtnInrementChest.setOnClickListener(new View.OnClickListener() {
public void onClick(View v) {
	incrementChest();
}
});

// Auto increment for a long click
	BtnInrementChest.setOnLongClickListener( 
		new View.OnLongClickListener(){
			public boolean onLongClick(View arg0) {
				autoIncrementChest = true;
				repeatUpdateHandlerChest.post( new RepetetiveUpdaterChest() );
				return false;
			}
		}
);

// When the button is released, if we're auto incrementing, stop
	BtnInrementChest.setOnTouchListener( new View.OnTouchListener() {
	public boolean onTouch(View v, MotionEvent event) {
		if( (event.getAction() == MotionEvent.ACTION_UP && autoIncrementChest) || (event.getAction() == MotionEvent.ACTION_CANCEL && autoIncrementChest) ){
			autoIncrementChest = false;
		}
		return false;
	}
});


// Decrement once for a click
BtnDecrementChest.setOnClickListener(new View.OnClickListener() {
public void onClick(View v) {
	decrementChest();
}
});		
// Auto Decrement for a long click
	BtnDecrementChest.setOnLongClickListener( 
		new View.OnLongClickListener(){
			public boolean onLongClick(View arg0) {
				autoDecrementChest = true;
				repeatUpdateHandlerChest.post( new RepetetiveUpdaterChest() );
				return false;
			}
		}
);

// When the button is released, if we're auto decrementing, stop
	BtnDecrementChest.setOnTouchListener( new View.OnTouchListener() {
	public boolean onTouch(View v, MotionEvent event) {
		if( (event.getAction() == MotionEvent.ACTION_UP && autoDecrementChest) || (event.getAction() == MotionEvent.ACTION_CANCEL && autoDecrementChest) ){
			autoDecrementChest = false;
		}
		return false;
	}
});



///////////////end Inrement Chest
	
///////////////start Inrement Hip
	

	//BtnInrement
	BtnInrementHip.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			incrementHip();
		}
	});
	
	//Auto increment for a long click
	BtnInrementHip.setOnLongClickListener( 
		new View.OnLongClickListener(){
			public boolean onLongClick(View arg0) {
				autoIncrementHip = true;
				repeatUpdateHandlerHip.post( new RepetetiveUpdaterHip() );
				return false;
			}
		}
	);
	
	//When the button is released, if we're auto incrementing, stop
	BtnInrementHip.setOnTouchListener( new View.OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) {
			if( (event.getAction() == MotionEvent.ACTION_UP && autoIncrementHip) || (event.getAction() == MotionEvent.ACTION_CANCEL && autoIncrementHip) ){
				autoIncrementHip = false;
			}
			return false;
		}
	});
	
	
	//Decrement once for a click
	BtnDecrementHip.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			decrementHip();
		}
	});		
	//Auto Decrement for a long click
	BtnDecrementHip.setOnLongClickListener( 
		new View.OnLongClickListener(){
			public boolean onLongClick(View arg0) {
				autoDecrementHip = true;
				repeatUpdateHandlerHip.post( new RepetetiveUpdaterHip() );
				return false;
			}
		}
	);
	
	//When the button is released, if we're auto decrementing, stop
	BtnDecrementHip.setOnTouchListener( new View.OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) {
			if( (event.getAction() == MotionEvent.ACTION_UP && autoDecrementHip) || (event.getAction() == MotionEvent.ACTION_CANCEL && autoDecrementHip) ){
				autoDecrementHip = false;
			}
			return false;
		}
	});



///////////////end Inrement Hip
	
	
///////////////start Inrement Neck
	

	//BtnInrement
	BtnInrementNeck.setOnClickListener(new View.OnClickListener() {
	public void onClick(View v) {
		incrementNeck();
	}
	});
	
	//Auto increment for a long click
	BtnInrementNeck.setOnLongClickListener( 
	new View.OnLongClickListener(){
		public boolean onLongClick(View arg0) {
			autoIncrementNeck = true;
			repeatUpdateHandlerNeck.post( new RepetetiveUpdaterNeck() );
			return false;
		}
	}
	);
	
	//When the button is released, if we're auto incrementing, stop
	BtnInrementNeck.setOnTouchListener( new View.OnTouchListener() {
	public boolean onTouch(View v, MotionEvent event) {
		if( (event.getAction() == MotionEvent.ACTION_UP && autoIncrementNeck) || (event.getAction() == MotionEvent.ACTION_CANCEL && autoIncrementNeck) ){
			autoIncrementNeck = false;
		}
		return false;
	}
	});
	
	
	//Decrement once for a click
	BtnDecrementNeck.setOnClickListener(new View.OnClickListener() {
	public void onClick(View v) {
		decrementNeck();
	}
	});		
	//Auto Decrement for a long click
	BtnDecrementNeck.setOnLongClickListener( 
	new View.OnLongClickListener(){
		public boolean onLongClick(View arg0) {
			autoDecrementNeck = true;
			repeatUpdateHandlerNeck.post( new RepetetiveUpdaterNeck() );
			return false;
		}
	}
	);
	
	//When the button is released, if we're auto decrementing, stop
	BtnDecrementNeck.setOnTouchListener( new View.OnTouchListener() {
	public boolean onTouch(View v, MotionEvent event) {
		if( (event.getAction() == MotionEvent.ACTION_UP && autoDecrementNeck) || (event.getAction() == MotionEvent.ACTION_CANCEL && autoDecrementNeck) ){
			autoDecrementNeck = false;
		}
		return false;
	}
	});



///////////////end Inrement Neck	
	
	
	
		
		
		
		

		CalculateBMI();
		CalculateBMR();
		CalculateFat();
    	
	/*
/////////////////	     �������� adView
		
	Random r = new Random();
//	int i1=r.nextInt(10);
	//if(i1 < 3){
	    LinearLayout layout = (LinearLayout)findViewById(R.id.mainAdViewLayout);		
		
	    adView = new AdView(this, AdSize.BANNER, "ca-app-pub-8661822564276571/1942851045");
	
	    // ����� � LinearLayout (��������������, ��� ��� ��������
	    // ������� android:id="@+id/mainLayout"
	
	
	    // ���������� adView
	    layout.addView(adView);
	
	    // ������������� ������ ������� �� �������� ������ � �����������
	    adView.loadAd(new AdRequest());
	//}
	/////////////////	    End �������� adView	   		
	*/
	
	///// pref
		
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		
///		Resources res = getResources();
//		String text = String.format(res.getString(R.string.welcome_messages), username, mailCount);
///		String Length =  res.getString(R.string.inch_short);
///		String weight =  res.getString(R.string.pound_short);
		
	//	ListPrefWeight = 
///		publicWeight = prefs.getString("weightUnit", weight);
	//	ListPrefLength = 
///		publicWaistSize = prefs.getString("lengthUnit", Length);
///		textView3.setText(prefs.getString("weightUnit", weight));
		
		
		if(prefs.getString("weightUnit", "").equals("")){   //first start
			if(Locale.getDefault().getLanguage().equals("ru")){
				SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(this); 
				SharedPreferences.Editor editor1 = mSettings.edit();

				editor1.putString("weightUnit", "2");
				editor1.putString("lengthUnit", "1");
    	        editor1.commit();
    	        
			}else{
				SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(this); 
				SharedPreferences.Editor editor1 = mSettings.edit();
				
				editor1.putString("weightUnit", "1");
				editor1.putString("lengthUnit", "2");
    	        editor1.commit();
    	        
			}
		}
		if((prefs.getString("weightUnit", "").equals("����")) || (prefs.getString("weightUnit", "").equals("lbs"))){
			SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(this); 
			SharedPreferences.Editor editor1 = mSettings.edit();
			editor1.putString("weightUnit", "1");
	        editor1.commit();			
		}
		
		if((prefs.getString("weightUnit", "").equals("��")) || (prefs.getString("weightUnit", "").equals("kg"))){
			SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(this); 
			SharedPreferences.Editor editor1 = mSettings.edit();
			editor1.putString("weightUnit", "2");
	        editor1.commit();
		}
		if((prefs.getString("lengthUnit", "").equals("����")) || (prefs.getString("lengthUnit", "").equals("in"))){
			SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(this); 
			SharedPreferences.Editor editor1 = mSettings.edit();
			editor1.putString("lengthUnit", "2");
	        editor1.commit();			
		}
		if((prefs.getString("lengthUnit", "").equals("��")) || (prefs.getString("lengthUnit", "").equals("cm"))){
			SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(this); 
			SharedPreferences.Editor editor1 = mSettings.edit();
			editor1.putString("lengthUnit", "1");
	        editor1.commit();			
		}
		if((prefs.getString("Gender", "").equals("�������")) || (prefs.getString("Gender", "").equals("male"))){
			SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(this); 
			SharedPreferences.Editor editor1 = mSettings.edit();
			editor1.putString("Gender", "1");
	        editor1.commit();			
		}
		if( (prefs.getString("Gender", "").equals("�������")) || (prefs.getString("Gender", "").equals("female")) ){
			SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(this); 
			SharedPreferences.Editor editor1 = mSettings.edit();
			editor1.putString("Gender", "2");
	        editor1.commit();			
		}		
		
		
		
		
///// end pref 


		
		
		
		
		
		
		
		/*
		
		
		this.interstitialAds = new InterstitialAd(this, "ca-app-pub-5156952701690991/8486434466");
		this.interstitialAds.setAdListener(this);
		
		AdRequest adr = new AdRequest();
		interstitialAds.loadAd(adr);
		
		
		*/
		
		
		
    }   // end  onCreate
    
    
    
    
    
    
    /*

	public void onDismissScreen(Ad arg0) {
		// TODO Auto-generated method stub

	}


	public void onFailedToReceiveAd(Ad ad, ErrorCode error) {

	}


	public void onLeaveApplication(Ad arg0) {
		// TODO Auto-generated method stub
	}


	public void onPresentScreen(Ad arg0) {
		// TODO Auto-generated method stub
	}

	public void onReceiveAd(Ad arg0) {
		if (interstitialAds.isReady()) {
			interstitialAds.show();
		} else {
			
		}
	}    
    
    */
    
    
    
    
    
    
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
        //    setTextSize(getResources().getDimensionPixelSize(R.dimen.WheelText));
            setTextSize(25);
            
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
        //    setTextSize(getResources().getDimensionPixelSize(R.dimen.WheelText));
            setTextSize(25);
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    



    
    
//////////menu options start
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
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
              adapter = new SqlAdapter(this);
             // adapter.getCount()
            	
          	  if ((adapter.getCount() > 10) && (quitFeedback > 0)){

          		Feedback();

          	  }else{
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
  	        	if (quitFeedback > 0){
  	        		quitFeedback = quitFeedback - 1;
  	        		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
  	        		SharedPreferences.Editor editor = preferences.edit();
  	        		editor.putInt("quitFeedback", quitFeedback);
  	        		editor.commit();	        		
  	        	}
  	        	alert.show();
          	  }        	
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
    
    
///////////////start Inrement weight
    
  
	public void increment(){
		
		String weight = textView1.getText().toString().trim();			
	    double weight1 =  Double.parseDouble(weight);
	    weight1 = weight1 * 10;
	    int weight11 = (int) Math.round(weight1);		
		
		if( weight1 < MAXIMUM * 10 ){
		    weight11 = weight11 + 1;
		    double weight111 = weight11;
		    weight111 = weight111 / 10;		    
		    textView1.setText( weight111 + "" );
		}
		CalculateBMI();
		CalculateBMR();
	}

	public void decrement(){
		String weight = textView1.getText().toString().trim();			
	    double weight1 =  Double.parseDouble(weight);
	    weight1 = weight1 * 10;
	    int weight11 = (int) Math.round(weight1);		
		
		if( weight1 > MINIMUM ){
		    weight11 = weight11 - 1;
		    double weight111 = weight11;
		    weight111 = weight111 / 10;		    
		    textView1.setText( weight111 + "" );			
		}
		CalculateBMI();
		CalculateBMR();
	}    

	class RepetetiveUpdater implements Runnable {
		public void run() {
			if( autoIncrement ){
				increment();
				repeatUpdateHandler.postDelayed( new RepetetiveUpdater(), REPEAT_DELAY );
			} else if( autoDecrement ){
				decrement();
				repeatUpdateHandler.postDelayed( new RepetetiveUpdater(), REPEAT_DELAY );
			}
		}
	}	

	
///////////////end Inrement weight
///////////////start Inrement WaistSize
    
	  
		public void incrementWaistSize(){
			String WaistSize = textView5.getText().toString().trim();			
		    double WaistSize1 =  Double.parseDouble(WaistSize);
		    WaistSize1 = WaistSize1 * 10;
		    int WaistSize11 = (int) Math.round(WaistSize1);
			if( WaistSize1 < MAXIMUMWaistSize * 10){
				WaistSize11 = WaistSize11 + 1;
				double WaistSize111 = WaistSize11;
				WaistSize111 = WaistSize111 / 10;
			    textView5.setText(WaistSize111 + "" );
			    CalculateFat();
			}			
		}
		
		public void decrementWaistSize(){
			String WaistSize = textView5.getText().toString().trim();			
		    double WaistSize1 =  Double.parseDouble(WaistSize);
		    WaistSize1 = WaistSize1 * 10;
		    int WaistSize11 = (int) Math.round(WaistSize1);		
			if( WaistSize1 > MINIMUMWaistSize  ){
				WaistSize11 = WaistSize11 - 1;
				double WaistSize111 = WaistSize11;
				WaistSize111 = WaistSize111 / 10;				
			    textView5.setText( WaistSize111 + "" );
			    CalculateFat();
			}
		}
		
		class RepetetiveUpdaterWaistSize implements Runnable {
		public void run() {
			if( autoIncrementWaistSize ){
				incrementWaistSize();
				repeatUpdateHandlerWaistSize.postDelayed( new RepetetiveUpdaterWaistSize(), REPEAT_DELAY );
			} else if( autoDecrementWaistSize ){
				decrementWaistSize();
				repeatUpdateHandlerWaistSize.postDelayed( new RepetetiveUpdaterWaistSize(), REPEAT_DELAY );
			}
		}
		}	


///////////////end Inrement WaistSize	
///////////////start Inrement WaistSize
		  
	public void incrementChest(){
		
		String ChestSize = TextViewchest2.getText().toString().trim();
	    double ChestSize1 =  Double.parseDouble(ChestSize);
	    ChestSize1 = ChestSize1 * 10;
	    int ChestSize11 = (int) Math.round(ChestSize1);
		if( ChestSize1 < MAXIMUMChest * 10){
			ChestSize11 = ChestSize11 + 1;
			double ChestSize111 = ChestSize11;
			ChestSize111 = ChestSize111 / 10;
			TextViewchest2.setText(ChestSize111 + "" );
		}
	}
	
	public void decrementChest(){
		String ChestSize = TextViewchest2.getText().toString().trim();			
	    double ChestSize1 =  Double.parseDouble(ChestSize);
	    ChestSize1 = ChestSize1 * 10;
	    int ChestSize11 = (int) Math.round(ChestSize1);		
		if( ChestSize1 > MINIMUMChest  ){
			ChestSize11 = ChestSize11 - 1;
			double ChestSize111 = ChestSize11;
			ChestSize111 = ChestSize111 / 10;				
			TextViewchest2.setText( ChestSize111 + "" );
		}		
		

	}
	
	class RepetetiveUpdaterChest implements Runnable {
		public void run() {
			if( autoIncrementChest ){
				incrementChest();
				repeatUpdateHandlerChest.postDelayed( new RepetetiveUpdaterChest(), REPEAT_DELAY );
			} else if( autoDecrementChest ){
				decrementChest();
				repeatUpdateHandlerChest.postDelayed( new RepetetiveUpdaterChest(), REPEAT_DELAY );
			}
		}
	}


///////////////end Inrement WaistSize
	
///////////////start Inrement Hip
		  
	public void incrementHip(){
	
		String HipSize = TextViewHip2.getText().toString().trim();
		double HipSize1 =  Double.parseDouble(HipSize);
		HipSize1 = HipSize1 * 10;
		int HipSize11 = (int) Math.round(HipSize1);
		if( HipSize1 < MAXIMUMHip * 10){
			HipSize11 = HipSize11 + 1;
			double HipSize111 = HipSize11;
			HipSize111 = HipSize111 / 10;
			TextViewHip2.setText(HipSize111 + "" );
			CalculateFat();
		}
	}
	
	public void decrementHip(){
		String HipSize = TextViewHip2.getText().toString().trim();			
		double HipSize1 =  Double.parseDouble(HipSize);
		HipSize1 = HipSize1 * 10;
		int HipSize11 = (int) Math.round(HipSize1);		
		if( HipSize1 > MINIMUMHip  ){
			HipSize11 = HipSize11 - 1;
			double HipSize111 = HipSize11;
			HipSize111 = HipSize111 / 10;				
			TextViewHip2.setText( HipSize111 + "" );
			CalculateFat();
		}		
	}
	
	class RepetetiveUpdaterHip implements Runnable {
	public void run() {
		if( autoIncrementHip ){
			incrementHip();
			repeatUpdateHandlerHip.postDelayed( new RepetetiveUpdaterHip(), REPEAT_DELAY );
		} else if( autoDecrementHip ){
			decrementHip();
			repeatUpdateHandlerHip.postDelayed( new RepetetiveUpdaterHip(), REPEAT_DELAY );
		}
	}
	}


///////////////end Inrement Hip	
	
///////////////start Inrement Neck
	  
	public void incrementNeck(){
	
	String NeckSize = TextViewNeck2.getText().toString().trim();
	double NeckSize1 =  Double.parseDouble(NeckSize);
	NeckSize1 = NeckSize1 * 10;
	int NeckSize11 = (int) Math.round(NeckSize1);
	if( NeckSize1 < MAXIMUMNeck * 10){
		NeckSize11 = NeckSize11 + 1;
		double NeckSize111 = NeckSize11;
		NeckSize111 = NeckSize111 / 10;
		TextViewNeck2.setText(NeckSize111 + "" );
		CalculateFat();
	}
	}
	
	public void decrementNeck(){
	String NeckSize = TextViewNeck2.getText().toString().trim();			
	double NeckSize1 =  Double.parseDouble(NeckSize);
	NeckSize1 = NeckSize1 * 10;
	int NeckSize11 = (int) Math.round(NeckSize1);		
	if( NeckSize1 > MINIMUMNeck  ){
		NeckSize11 = NeckSize11 - 1;
		double NeckSize111 = NeckSize11;
		NeckSize111 = NeckSize111 / 10;				
		TextViewNeck2.setText( NeckSize111 + "" );
		CalculateFat();
	}		
	}
	
	class RepetetiveUpdaterNeck implements Runnable {
	public void run() {
	if( autoIncrementNeck ){
		incrementNeck();
		repeatUpdateHandlerNeck.postDelayed( new RepetetiveUpdaterNeck(), REPEAT_DELAY );
	} else if( autoDecrementNeck ){
		decrementNeck();
		repeatUpdateHandlerNeck.postDelayed( new RepetetiveUpdaterNeck(), REPEAT_DELAY );
	}
	}
	}


///////////////end Inrement Neck		
	
	
	
    
    
	public void addWeight(View view) {
//		if (value1 == -1) { // add new
		Log.i(TAG, "addWeight");

		updateDays(year, month, day);
			

		//	String Day = String.format("%02d", dpDate.getDayOfMonth()).trim();
		//	String Month = String.format("%02d", ((int) dpDate.getMonth()) + 1);
		//	String date = Day + ":" + Month + ":" + dpDate.getYear();
			
			Calendar cal = Calendar.getInstance();
		//	cal.set(Calendar.YEAR, dpDate.getYear());
		//	cal.set(Calendar.MONTH, dpDate.getMonth());
		//	cal.set(Calendar.DAY_OF_MONTH, dpDate.getDayOfMonth());

			cal.set(Calendar.YEAR, mYear);
			cal.set(Calendar.MONTH, mMonth);
			cal.set(Calendar.DAY_OF_MONTH, mDay);			
			
			cal.set(Calendar.HOUR_OF_DAY, 13);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MILLISECOND, 59);
			long date = cal.getTimeInMillis();			
			

			
			String weight = textView1.getText().toString().trim();
			
			String WaistSize = textView5.getText().toString().trim();
			double WaistSize1 =  Double.parseDouble(WaistSize);
			WaistSize1 = WaistSize1 * 10;
			int WaistSize11 = (int) WaistSize1;
			
			String Chest = TextViewchest2.getText().toString().trim();
			double Chest1 =  Double.parseDouble(Chest);
			Chest1 = Chest1 * 10;
			int Chest11 = (int) Chest1;
			
			String Hip = TextViewHip2.getText().toString().trim();
			double Hip1 =  Double.parseDouble(Hip);
			Hip1 = Hip1 * 10;
			int Hip11 = (int) Hip1;
			
			String Neck = TextViewNeck2.getText().toString().trim();
			double Neck1 =  Double.parseDouble(Neck);
			Neck1 = Neck1 * 10;
			int Neck11 = (int) Neck1;			
			
			

			if (!"".equals(weight)) {
			//	try {
					
				    double weight1 =  Double.parseDouble(weight);
				    weight1 = weight1 * 10;
				    int weight11 = (int) Math.round(weight1);
				    double weight111 = weight11;
				    weight111 = weight111 / 10;
				    
				 //   final Cursor Exists = adapter.checkExists(date);

				    boolean Exists = adapter.checkExists1(date);
				    Log.i(TAG, Exists + "-" + Exists);
				  //  startManagingCursor(Exists);
					if (Exists == false){
						// takoi daty nety
						Log.i(TAG, "checkExists - not Exists");
						
					    weight newWeight = new weight(date, weight111 + "", WaistSize11, Chest11, Hip11, Neck11);
					    adapter.addItem(newWeight);
					    
					    Toast.makeText(WeightManagerActivity.this, getString(R.string.data_is_saved), Toast.LENGTH_LONG).show();
					    
					    ShowChartActivity();
					    
				        
					}else{
						// est takaya data
						Log.i(TAG, "checkExists - Exists");
						
					//	Toast.makeText(this, "---EST---" + Exists.,Toast.LENGTH_LONG).show();
						
			///////////////			start dialog	
						
						// custom dialog
						final Dialog dialog = new Dialog(context);
						dialog.setContentView(R.layout.custom_dialog_check_exists);
					//	dialog.setTitle(getString(R.string.Attention));
						
						dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
						
						Button dialogButton = (Button) dialog.findViewById(R.id.button22);
						// Edit
						dialogButton.setOnClickListener(new OnClickListener() {
							public void onClick(View v) {
								dialog.dismiss();

						//		String Day = String.format("%02d", dpDate.getDayOfMonth()).trim();
						//		String Month = String.format("%02d", ((int) dpDate.getMonth()) + 1);
						//		String date = Day + ":" + Month + ":" + dpDate.getYear();
								
								Calendar cal = Calendar.getInstance();
							//	cal.set(Calendar.YEAR, dpDate.getYear());
							//	cal.set(Calendar.MONTH, dpDate.getMonth());
							//	cal.set(Calendar.DAY_OF_MONTH, dpDate.getDayOfMonth());
								
								cal.set(Calendar.YEAR, mYear);
								cal.set(Calendar.MONTH, mMonth);
								cal.set(Calendar.DAY_OF_MONTH, mDay);								
								
								cal.set(Calendar.HOUR_OF_DAY, 13);
								cal.set(Calendar.MINUTE, 59);
								cal.set(Calendar.SECOND, 59);
								cal.set(Calendar.MILLISECOND, 59);								
								long date = cal.getTimeInMillis();
								
								
								String weight = textView1.getText().toString().trim();			
								String WaistSize = textView5.getText().toString().trim();								
								String Chest = TextViewchest2.getText().toString().trim();
								String Hip = TextViewHip2.getText().toString().trim();
								String Neck = TextViewNeck2.getText().toString().trim();
								
							    double weight1 =  Double.parseDouble(weight);
							    weight1 = weight1 * 10;
							    int weight11 = (int) Math.round(weight1);
							    double weight111 = weight11;
							    weight111 = weight111 / 10;

							    
							    double WaistSize1 =  Double.parseDouble(WaistSize);
							    WaistSize1 = WaistSize1 * 10;
							    int WaistSize11 = (int) WaistSize1;
							    
							    double Chest1 =  Double.parseDouble(Chest);
							    Chest1 = Chest1 * 10;
							    int Chest11 = (int) Chest1;
							    
							    double Hip1 =  Double.parseDouble(Hip);
							    Hip1 = Hip1 * 10;
							    int Hip11 = (int) Hip1;
							    
							    double Neck1 =  Double.parseDouble(Neck);
							    Neck1 = Neck1 * 10;
							    int Neck11 = (int) Neck1;							    
								
							//	weight selectedWeight = adapter.getItem(Exists.getPosition());
							//	adapter.updateItem(selectedWeight, weight111 + "", WaistSize11);
							    
							    adapter.updateItem1(date, weight111 + "", WaistSize11, Chest11, Hip11, Neck11);
								
							    Toast.makeText(WeightManagerActivity.this, getString(R.string.data_is_saved), Toast.LENGTH_LONG).show();
							    
							    ShowChartActivity();
							    
							    
							}
						});
						// cancel
						Button dialogButton1 = (Button) dialog.findViewById(R.id.button23);
						dialogButton1.setOnClickListener(new OnClickListener() {
							public void onClick(View v) {
								dialog.dismiss();
							}
						});				
			 
						dialog.show();				
						
		///////////////			end dialog							
						
						
					//	Toast.makeText(this, "---EST---",Toast.LENGTH_LONG).show();
					}				    
				    

				    
			//		Toast.makeText(this, "---" + weight11 + "---",Toast.LENGTH_LONG).show();
			//	} catch(NumberFormatException nfe) {

			//	}
				
				
				
//				int weight1 = Integer.parseInt(WeightEdit);
//				long weight1111 = weight1;
//					Toast.makeText(this, "---" + weight1 + "---",Toast.LENGTH_LONG).show();				
			
				
				

//				weight newWeight = new weight(date, weight1);
				
//				adapter.addItem(newWeight);
			//	WeightEdit.getText().clear();
			}else{
		    	AlertDialog LDialog = new AlertDialog.Builder(this)
		    	.setTitle("Weight")
		    	.setMessage("Enter Weight.")
		    	.setPositiveButton("Ok", null).create();
		    	LDialog.show();  			
			}
/*			
		}else{ // edit

			
			String date = dpDate.getDayOfMonth() + ":" + (dpDate.getMonth() + 1) + ":" + dpDate.getYear();
			String weight = textView1.getText().toString().trim();			
			String WaistSize = textView5.getText().toString().trim();
			
			if (!"".equals(weight)) {
		//		long weight1 = Long.parseLong(weight);
				
		//		int weight1 = Integer.parseInt(weight);
		//		weight1 = weight1;
				
  						
			    double weight1 =  Double.parseDouble(weight);
			    weight1 = weight1 * 10;
			    int weight11 = (int) Math.round(weight1);
			    double weight111 = weight11;
			    weight111 = weight111 / 10;

			    
			    double WaistSize1 =  Double.parseDouble(WaistSize);
			    WaistSize1 = WaistSize1 * 10;
			    int WaistSize11 = (int) WaistSize1;					    
		
				
				
				weight selectedWeight = adapter.getItem(value1);
				adapter.updateItem(selectedWeight, weight111 + "", WaistSize11);
				
		        Intent i = new Intent(this, listActivity.class);
		        startActivity(i);
				
			//	WeightEdit.getText().clear();
				value1 = -1;
			}else{
		    	AlertDialog LDialog = new AlertDialog.Builder(this)
		    	.setTitle("Weight")
		    	.setMessage("Enter Weight.")
		    	.setPositiveButton("Ok", null).create();
		    	LDialog.show();  			
			}
			
			
			
		}
*/		
	}
	
	public void Feedback(){

        try{

			// custom dialog
			final Dialog dialog = new Dialog(context);
			dialog.setContentView(R.layout.custom_dialog_feedback);
		//	dialog.setTitle(getString(R.string.Waist_size));
			
			dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
 
			Button dialogButton1 = (Button) dialog.findViewById(R.id.button1);

			dialogButton1.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					dialog.dismiss();
					

        			
        			quitFeedback = - 10;
  	        		
  	        		SharedPreferences.Editor editor = preferences2.edit();
  	        		editor.putInt("quitFeedback", quitFeedback);
  	        		editor.commit();    		        			
        			
        		//	String appPackageName=getResources().getString(R.string.app_package_name);
        			String appPackageName="market://details?id=weight.manager";
        			Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(appPackageName));
        			marketIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        			startActivity(marketIntent);
  	        		
        		  					

				}
			});

			Button dialogButton2 = (Button) dialog.findViewById(R.id.button2);
			dialogButton2.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					dialog.dismiss();


	    	        
	    	          	
        			quitFeedback = - 20;
  	        		
  	        		SharedPreferences.Editor editor = preferences2.edit();
  	        		editor.putInt("quitFeedback", quitFeedback);
  	        		editor.commit();      		    	          	
	    	          	
  	        	//	  Intent startMain = new Intent(Intent.ACTION_MAIN);
	    	     //   startMain.addCategory(Intent.CATEGORY_HOME);
	    	     //   startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    	     //   startActivity(startMain);
	    	          	
	    	          	finish();
        		  					
					
				}
			});
			
			Button dialogButton3 = (Button) dialog.findViewById(R.id.button3);
			dialogButton3.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					
					dialog.dismiss();
					
	    	       //   	Intent startMain = new Intent(Intent.ACTION_MAIN);
	    	       //   	startMain.addCategory(Intent.CATEGORY_HOME);
	    	       //   	startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    	       //   	startActivity(startMain);
	    	          	
	    	          	finish();
	    	          	
				}
			});				
			
			dialog.show();				
        }catch (Exception e){
            Log.e("Error", "Cannot launch", e);
        }
    		
		
	}
    
	public void settingsClick(View view) {
		openOptionsMenu();
	}
	
	public void ShowChartActivity() {
        Intent i = new Intent(this, chartActivity.class);
        startActivity(i);
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
	
	public void chartActivity(View view) {
		
		

		
		
        Intent i = new Intent(this, chartActivity.class);
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
	
	
	public void BMILinearLayoutClick(View view) {
        Intent i = new Intent(this, HelpBMIActivity.class);
        startActivity(i);
        
        SharedPreferences myPreference=PreferenceManager.getDefaultSharedPreferences(this);
        if(myPreference.getBoolean("animation", true)) {
	        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
        }
	}	
	public void BRMLinearLayoutClick(View view) {
        Intent i = new Intent(this, HelpBRMActivity.class);
        startActivity(i);
        
        SharedPreferences myPreference=PreferenceManager.getDefaultSharedPreferences(this);
        if(myPreference.getBoolean("animation", true)) {
	        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
        }
	}
	
	public void FatLinearLayoutClick(View view) {
        Intent i = new Intent(this, HelpFatActivity.class);
        startActivity(i);
        
        SharedPreferences myPreference=PreferenceManager.getDefaultSharedPreferences(this);
        if(myPreference.getBoolean("animation", true)) {
	        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
        }
	}		
	

	public void share(View view) {
		
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "" + getString(R.string.app_name));
		intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=weight.manager");
		startActivity(Intent.createChooser(intent, getString(R.string.share)));	
/*
 
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/png");
            String tmpFilename =  DistanceCalculatorUtilities.saveParentView(distanceText.getRootView(), this, true);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + tmpFilename));
            startActivity(Intent.createChooser(intent, getString(R.string.share)));
 		
 */
		
		
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
	
	public void CalculateBMI() {
		SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(this);
		
		String heigh = preferences1.getString("heigh", "0");
		if (heigh.equals("")){heigh = "0"; }
		double heigh1 =  Double.parseDouble(heigh);
		
		String age = preferences1.getString("age", "0");
		if (age.equals("")){age = "0"; }
		double age1 =  Double.parseDouble(age);		
		
		String weight = textView1.getText().toString().trim();
		if (weight.equals("")){weight = "0"; }
		double weight1 =  Double.parseDouble(weight);

	    if (heigh1 == 0 || age1 == 0){
	    	BMI.setText(getString(R.string.NoData));
	    //	BMI.setBackgroundColor(0x99000077);
	    	BMI.setTextColor(0x80ff0000);
	    }else{		
			if (publicWeight.equals("1") || publicWeight.equals("����")){
				weight1 = weight1 * 0.45359237;
			}
			if (publicWaistSize.equals("2") || publicWaistSize.equals("����")){
				heigh1 = heigh1 * 2.54 / 100;
			}else{ heigh1 = heigh1 / 100; }
			double I = weight1 / (heigh1 * heigh1);		
			I = I * 10;
		    int I1 = (int) Math.round(I);
		    I = I1;
		    I = I / 10;	

	    	
	    	if (I < 18.5){
	    		BMI.setTextColor(0xffff9900);
	    	//	BMI.setText(I + "\n" + getString(R.string.underweight)); }
	    		BMI.setText(I + ""); }
	    	if (I > 18.5 && I < 24.9){
	    		BMI.setTextColor(0xff00ff00);
	    	//	BMI.setText(I + "\n" + getString(R.string.normal)); }
	    		BMI.setText(I + ""); }
	    	if (I > 25 && I < 29.9){
	    		BMI.setTextColor(0xffff9900);
	    	//	BMI.setText(I + "\n" + getString(R.string.overweight)); }
	    		BMI.setText(I + ""); }
	    	if (I > 30){
	    		BMI.setTextColor(0xffff4000);
	    	//	BMI.setText(I + "\n" + getString(R.string.obese)); }
	    		BMI.setText(I + ""); }
	    }
	}
	public void CalculateBMR() {

		SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(this);
		
		String heigh = preferences1.getString("heigh", "0");
		if (heigh.equals("")){heigh = "0"; }
		double heigh1 =  Double.parseDouble(heigh);
		
		String age = preferences1.getString("age", "0");
		if (age.equals("")){age = "0"; }
		double age1 =  Double.parseDouble(age);		
		
		String weight = textView1.getText().toString().trim();
		double weight1 =  Double.parseDouble(weight);
		
		String Gender = preferences1.getString("Gender", "male");

	    if (heigh1 == 0 || age1 == 0){
	    	BRM.setText(getString(R.string.NoData));
	    //	BMI.setBackgroundColor(0x99000077);
	    	BRM.setTextColor(0x80ff0000);
	    }else{		
			if (publicWeight.equals("1") || publicWeight.equals("����")){
				weight1 = weight1 * 0.45359237;
			}
			if (publicWaistSize.equals("2") || publicWaistSize.equals("����")){
				heigh1 = heigh1 * 2.54 / 100;
			}
			
			if (Gender.equals("male") || Gender.equals("�������")|| Gender.equals("1")){
				double I = 66 + ( 13.7 * weight1) + (5 * heigh1) - ( 6.8 * age1);
				I = I * 10;
			    int I1 = (int) Math.round(I);
			    I = I1;
			    I = I / 10;
			    BRM.setText(I + "");
			    BRM.setTextColor(0xff000000);
			}else{
				double I = 655 + (9.6 * weight1) + (1.8 * heigh1) - (4.7* age1);
				I = I * 10;
			    int I1 = (int) Math.round(I);
			    I = I1;
			    I = I / 10;
			    BRM.setText(I + "");
			    BRM.setTextColor(0xff000000);
			}
	    }
	}
	public void CalculateFat() {
		SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(this);
		
		double koef = 1;
		
		if (publicWaistSize.equals("2") || publicWaistSize.equals("����")){
			koef = 2.54;
		}
		
		String heigh = preferences1.getString("heigh", "0");
		if (heigh.equals("")){heigh = "0"; }
		double heigh1 =  (Double.parseDouble(heigh)) * koef;
		
		
		String WaistSize = textView5.getText().toString().trim();
		double WaistSize1 =  (Double.parseDouble(WaistSize)) * koef;
//		WaistSize1 = WaistSize1 * 10;
//		int WaistSize11 = (int) WaistSize1; //�����
		
		
		String Hip = TextViewHip2.getText().toString().trim();
		double Hip1 =  (Double.parseDouble(Hip)) * koef;
//		Hip1 = Hip1 * 10;
//		int Hip11 = (int) Hip1;
		
		String Neck = TextViewNeck2.getText().toString().trim();
		double Neck1 =  (Double.parseDouble(Neck)) * koef;
//		Neck1 = Neck1 * 10;
//		int Neck11 = (int) Neck1;

		
	    if ( heigh1 == 0 || WaistSize1 == 0 || Hip1 == 0 || Neck1 == 0){
	    	Fat.setText(getString(R.string.NoData));
	    //	BMI.setBackgroundColor(0x99000077);
	    	Fat.setTextColor(0x80ff0000);
	    }else{		
		
		
			String Gender = preferences1.getString("Gender", "male");
			if (Gender.equals("male") || Gender.equals("�������")|| Gender.equals("1")){
				
//Men	Percentage of Fat = 495 / (1.0324 - 0.19077 x (LOG10(waist - neck)) + 0.15456 x (LOG10(height))) - 450
				
				double I = 495 / (1.0324 - 0.19077 * (java.lang.Math.log10(WaistSize1 - Neck1)) + 0.15456 * (java.lang.Math.log10(heigh1))) - 450;
				
				
				I = I * 10;
			    int I1 = (int) Math.round(I);
			    I = I1;
			    I = I / 10;
				
				
				if(I < 0){
					Fat.setText("0");
				}else{
					Fat.setText(I + "");
				}
			}else{
				
// Women	Percentage of Fat = 495 / (1.29579 - 0.35004 x (LOG10(waist + hip - neck)) + 0.22100 x (LOG10(height))) - 450
				
				double I = 495 / (1.29579 - 0.35004 * (java.lang.Math.log10(WaistSize1 + Hip1 - Neck1)) + 0.22100 * (java.lang.Math.log10(heigh1))) - 450;

				I = I * 10;
			    int I1 = (int) Math.round(I);
			    I = I1;
			    I = I / 10;
				
				if(I < 0){
					Fat.setText("0");
				}else{
					Fat.setText(I + "");
				}				
			}
	    }

		
	}
	
	
	@Override
	public void onDestroy() {
	//	password_entered = 0;
	//	Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show();

		if (mAdView != null) {
			mAdView.destroy();
		}

	 	  
	 	   if(adapter != null){
	 		   adapter.onDestroy();
	 	   }
		
		Log.i(TAG, "onDestroy");
		super.onDestroy();		
	}


	
	@Override
	public void onStop() {
		WeightManagerActivity.onStartOnStopSumm = WeightManagerActivity.onStartOnStopSumm - 1;
		SharedPreferences myPreference=PreferenceManager.getDefaultSharedPreferences(this);
		if(myPreference.getBoolean("Password", false) && (onStartOnStopSumm <= 1)) {
			if(dialog1 != null){
				dialog1.dismiss();
			}
		}
		Log.i(TAG, "onStop");
		super.onStop();
	}
	
	
	@Override
	public void onPause() {

		Log.i(TAG, "onPause");
		super.onPause();
	}
	
	
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) { //Back key pressed
           //Things to Do

//        	Toast.makeText(this, quitFeedback + "", Toast.LENGTH_LONG).show();
        	
        	  if ((adapter.getCount() > 10) && (quitFeedback > 0)){

        		Feedback();

        	  }else{        	
        	
		      //    	Intent startMain = new Intent(Intent.ACTION_MAIN);
		      //    	startMain.addCategory(Intent.CATEGORY_HOME);
		      //    	startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		      //    	startActivity(startMain);
          	
        		  finish();

        	  }
        	
//            Handler handler = new Handler(); 
//            handler.postDelayed(new Runnable() { 
//                 public void run() { 
//                 	finish(); 
//                 } 
//            }, 500);        	
        	

        	
          	
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
	
	
	@Override
	public void onStart() {
		
	WeightManagerActivity.onStartOnStopSumm = WeightManagerActivity.onStartOnStopSumm + 1;
//	Toast.makeText(this, "onStartOnStopSumm -"  + onStartOnStopSumm, Toast.LENGTH_LONG).show();

	SharedPreferences myPreference=PreferenceManager.getDefaultSharedPreferences(this);
		if(myPreference.getBoolean("Password", false) && (onStartOnStopSumm <= 1)) {
	         try{

				// custom dialog
			//	final Dialog dialog = new Dialog(this);
				dialog1 = new Dialog(this);
				dialog1.setContentView(R.layout.custom_dialog_check_password);
				dialog1.setCancelable(false);
				dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(0));
	 
				Button dialogButton = (Button) dialog1.findViewById(R.id.button22);

		//		ScreenSizeWidth = display.getWidth();
		//        ScreenSizeHeight
				LinearLayout rootLinearLayout = (LinearLayout) dialog1.findViewById(R.id.rootLinearLayout);
		//		rootLinearLayout.getLayoutParams().height = ScreenSizeHeight;
		//		rootLinearLayout.getLayoutParams().width = ScreenSizeWidth;
		//		rootLinearLayout.requestLayout();
				rootLinearLayout.setLayoutParams(new FrameLayout.LayoutParams(ScreenSizeWidth - 50, ScreenSizeHeight - 200));


				
			//	TextView1.setText(publicWeight);
				// Edit
				dialogButton.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						SharedPreferences myPreference=PreferenceManager.getDefaultSharedPreferences(WeightManagerActivity.this);					
						String P2 = myPreference.getString("PasswordText", "");
						
					//	TextView TextView2 = (TextView) dialog1.findViewById(R.id.textView2);
	    				EditText Password1 = (EditText) dialog1.findViewById(R.id.Password1);
						
	    				String P1 = Password1.getText().toString();
	    				
	    				if(P1.equals(P2)){	
	    					dialog1.dismiss();
							password_entered = 1;

						}
						

					}
				});

				
				dialog1.show();				
	        }catch (Exception e){
	            Log.e("Error", "Cannot launch", e);
	        }  		
		}
		super.onStart();
		
	}
	
	
	
	
	
	protected void onResume() {
		super.onResume();



		if (mAdView != null) {
			mAdView.resume();
		}
		
		
		
		
	    //  Wheels start
	    
	    Calendar calendar = Calendar.getInstance();

	    
	    
	    
	    
	    
	    if(DateDrumPicker.DateFormat111 == 1){
	    //	formatter = new SimpleDateFormat("MM-dd-yyyy");
	    	
		    month = (WheelView) findViewById(R.id.Wheel_1);
			year = (WheelView) findViewById(R.id.Wheel_3);
			day = (WheelView) findViewById(R.id.Wheel_2);
			
	    }else if(DateDrumPicker.DateFormat111 == 2){
	    //	formatter = new SimpleDateFormat("dd-MM-yyyy");
	    	
		    month = (WheelView) findViewById(R.id.Wheel_2);
			year = (WheelView) findViewById(R.id.Wheel_3);
			day = (WheelView) findViewById(R.id.Wheel_1);
			
	    }else{
	    //	formatter = new SimpleDateFormat("yyyy-MM-dd");
	    	
		    month = (WheelView) findViewById(R.id.Wheel_2);
			year = (WheelView) findViewById(R.id.Wheel_1);
			day = (WheelView) findViewById(R.id.Wheel_3);	    	
	    	
	    }	    
	    
	    

	    
	    OnWheelChangedListener listener = new OnWheelChangedListener() {
	        public void onChanged(WheelView wheel, int oldValue, int newValue) {
	            updateDays(year, month, day);
	        }
	    };

	    // month
	    int curMonth = calendar.get(Calendar.MONTH);
//	    String months[] = new String[] {"January", "February", "March", "April", "May",
//	            "June", "July", "August", "September", "October", "November", "December"};
	    
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
	    year.setCurrentItem(curYear);
	    year.addChangingListener(listener);
	    
	    //day
	    updateDays(year, month, day);
	    day.setCurrentItem(calendar.get(Calendar.DAY_OF_MONTH) - 1);
	    day.addChangingListener(listener);
	    
	    
	    //  Wheels end    		
		
		
		
		
		
		
		
		
		
		adapter = new SqlAdapter(this);
		

/////////
		if (value1 >= 0) {
			weight selectedWeight = adapter.getItem(value1);
			
		//	String Datee = selectedWeight.getDate() + "";
		//	Toast.makeText(this, "tion " + Datee.substring(6,10),
		//			Toast.LENGTH_LONG).show();
			
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(selectedWeight.getDate());

//	        dPicker.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
			

	        day.setCurrentItem(cal.get(Calendar.DAY_OF_MONTH) - 1);
	        month.setCurrentItem(cal.get(Calendar.MONTH));
	        year.setCurrentItem(cal.get(Calendar.YEAR) - 1970);
	        
//	        Toast.makeText(this, "-" + cal.get(Calendar.DAY_OF_MONTH), Toast.LENGTH_LONG).show();
	        
			textView1.setText(selectedWeight.getWeight() + "");

			double Waist = selectedWeight.getWaistSize();
		    int Waist1 = (int) Math.round(Waist);
		    double Waist11 = Waist1;
		    Waist11 = Waist11 / 10;			
			textView5.setText(Waist11 + "");
			
			double chest = selectedWeight.getChest();
		    int Chest1 = (int) Math.round(chest);
		    double Chest11 = Chest1;
		    Chest11 = Chest11 / 10;
		    TextViewchest2.setText(Chest11 + "");
			
			double Hip = selectedWeight.getHip();
		    int Hip1 = (int) Math.round(Hip);
		    double Hip11 = Hip1;
		    Hip11 = Hip11 / 10;
		    TextViewHip2.setText(Hip11 + "");
		    
			double Neck = selectedWeight.getNeck();
		    int Neck1 = (int) Math.round(Neck);
		    double Neck11 = Neck1;
		    Neck11 = Neck11 / 10;
		    TextViewNeck2.setText(Neck11 + "");			    
			
		//	dpDate.setEnabled(false);
		//	value1 = -1;
		}else{
			
			Calendar cal = Calendar.getInstance();

			
//	        dPicker.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
	        
	        
	        
	        day.setCurrentItem(cal.get(Calendar.DAY_OF_MONTH) - 1);
	        month.setCurrentItem(cal.get(Calendar.MONTH));
	        year.setCurrentItem(cal.get(Calendar.YEAR) - 1970);
	        
	        
//	        Toast.makeText(this, "else " + cal.get(Calendar.DAY_OF_MONTH), Toast.LENGTH_LONG).show();
	        
	        
			if (adapter.getCount() > 0) {
				weight selectedWeight = adapter.getItem(adapter.getCount() - 1);
				textView1.setText(selectedWeight.getWeight() + "");
	
				double Waist = selectedWeight.getWaistSize();
			    int Waist1 = (int) Math.round(Waist);
			    double Waist11 = Waist1;
			    Waist11 = Waist11 / 10;				
				textView5.setText(Waist11 + "");

				double chest = selectedWeight.getChest();
			    int Chest1 = (int) Math.round(chest);
			    double Chest11 = Chest1;
			    Chest11 = Chest11 / 10;				
			    TextViewchest2.setText(Chest11 + "");
				
				double Hip = selectedWeight.getHip();
			    int Hip1 = (int) Math.round(Hip);
			    double Hip11 = Hip1;
			    Hip11 = Hip11 / 10;				
			    TextViewHip2.setText(Hip11 + "");
			    
				double Neck = selectedWeight.getNeck();
			    int Neck1 = (int) Math.round(Neck);
			    double Neck11 = Neck1;
			    Neck11 = Neck11 / 10;				
			    TextViewNeck2.setText(Neck11 + "");			    
			}
		}
		
		
		value1 = -1;
		
		
////////		
		
		
		
		
		password_entered = 0;		
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		
		Resources res = getResources();
//		String text = String.format(res.getString(R.string.welcome_messages), username, mailCount);
//		String Length =  res.getString(R.string.inch_short);
//		String weight =  res.getString(R.string.pound_short);
		
	//	ListPrefWeight = 


		publicWeight = prefs.getString("weightUnit", "1");
		publicWaistSize = prefs.getString("lengthUnit", "2");

		if (publicWeight.equals("1")){
			textView3.setText(getString(R.string.pound_short));
			publicWeight1 = getString(R.string.pound_short);
		}else{
			textView3.setText(getString(R.string.kilogram_short));
			publicWeight1 = getString(R.string.kilogram_short);
		}

		if (publicWaistSize.equals("2")){
			textView4.setText(getString(R.string.inch_short));
			TextViewchest3.setText(getString(R.string.inch_short));
			TextViewHip3.setText(getString(R.string.inch_short));
			TextViewNeck3.setText(getString(R.string.inch_short));
			
			publicWaistSize1 = getString(R.string.inch_short);
		}else{
			textView4.setText(getString(R.string.centimetre_short));
			TextViewchest3.setText(getString(R.string.centimetre_short));
			TextViewHip3.setText(getString(R.string.centimetre_short));
			TextViewNeck3.setText(getString(R.string.centimetre_short));
			
			publicWaistSize1 = getString(R.string.centimetre_short);
		}
		
		

		
		CalculateBMI();
		CalculateBMR();
		CalculateFat();

	}



}