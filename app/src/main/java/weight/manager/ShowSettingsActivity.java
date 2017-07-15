package weight.manager;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShowSettingsActivity extends PreferenceActivity implements
OnSharedPreferenceChangeListener{
	
	private EditTextPreference targetWeightt;
	private EditTextPreference targetWaistSizee;
	
	private EditTextPreference targetchest;
	private EditTextPreference targethip;
	
	private EditTextPreference heigh;
	private EditTextPreference age;
	private CheckBoxPreference Password;
	
	private ListPreference weightUnit;
	private ListPreference lengthUnit;
	private ListPreference Gender;
	
	private ListPreference DateFormat;
	
	private Preference Ver;
	

	SharedPreferences preferences;
	
//	WeightManagerActivity.DateFormat111
	
 
    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        
        targetWeightt = (EditTextPreference) findPreference("targetWeight");
        targetWaistSizee = (EditTextPreference) findPreference("targetWaistSize");
        
        targetchest = (EditTextPreference) findPreference("targetchest");
        targethip = (EditTextPreference) findPreference("targethip");
        
        heigh = (EditTextPreference) findPreference("heigh");
        age = (EditTextPreference) findPreference("age");
        
        Password = (CheckBoxPreference) findPreference("Password");
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        
        weightUnit = (ListPreference)getPreferenceScreen().findPreference("weightUnit");
        lengthUnit = (ListPreference)getPreferenceScreen().findPreference("lengthUnit");
        
        Gender = (ListPreference)getPreferenceScreen().findPreference("Gender");
        DateFormat = (ListPreference)getPreferenceScreen().findPreference("DateFormat");
        
        Ver = (Preference)getPreferenceScreen().findPreference("ver");
        
        age.setDialogTitle(age.getTitle() + " (" + getString(R.string.years) + ")");
        
        if(weightUnit.getValue().equals("1")){
        	targetWeightt.setDialogTitle(targetWeightt.getTitle() + " (" + getString(R.string.pound_short) + ")");
        	targetWeightt.setSummary(targetWeightt.getSummary() +   " (" + targetWeightt.getText().toString().trim() + " " + getString(R.string.pound_short) + ")");
        	weightUnit.setSummary(weightUnit.getSummary() + " (" + getString(R.string.pound_short) + ")");
        }else{
        	targetWeightt.setDialogTitle(targetWeightt.getTitle() + " (" + getString(R.string.kilogram_short) + ")");
        	targetWeightt.setSummary(targetWeightt.getSummary() +   " (" + targetWeightt.getText().toString().trim() + " " + getString(R.string.kilogram_short) + ")");
        	weightUnit.setSummary(weightUnit.getSummary() + " (" + getString(R.string.kilogram_short) + ")");
        }
        
        if(lengthUnit.getValue().equals("1")){
            targetWaistSizee.setDialogTitle(targetWaistSizee.getTitle() + " (" + getString(R.string.centimetre_short) + ")");
            targetchest.setDialogTitle(targetchest.getTitle() + " (" + getString(R.string.centimetre_short) + ")");
            targethip.setDialogTitle(targethip.getTitle() + " (" + getString(R.string.centimetre_short) + ")");
        	heigh.setDialogTitle(heigh.getTitle() + " (" + getString(R.string.centimetre_short) + ")");
        	
        	targetWaistSizee.setSummary(targetWaistSizee.getSummary() +   " (" + targetWaistSizee.getText().toString().trim() + " " + getString(R.string.centimetre_short) + ")");
        	targetchest.setSummary(targetchest.getSummary() +   " (" + targetchest.getText().toString().trim() + " " + getString(R.string.centimetre_short) + ")");
        	targethip.setSummary(targethip.getSummary() +   " (" + targethip.getText().toString().trim() + " " + getString(R.string.centimetre_short) + ")");
        	heigh.setSummary(heigh.getText().toString().trim() + " " + getString(R.string.centimetre_short));
        	
        	lengthUnit.setSummary(lengthUnit.getSummary() + " (" + getString(R.string.centimetre_short) + ")");
        }else{
            targetWaistSizee.setDialogTitle(targetWaistSizee.getTitle() + " (" + getString(R.string.inch_short) + ")");
            targetchest.setDialogTitle(targetchest.getTitle() + " (" + getString(R.string.inch_short) + ")");
            targethip.setDialogTitle(targethip.getTitle() + " (" + getString(R.string.inch_short) + ")");
        	heigh.setDialogTitle(heigh.getTitle() + " (" + getString(R.string.inch_short) + ")");
        	
        	targetWaistSizee.setSummary(targetWaistSizee.getSummary() +   " (" + targetWaistSizee.getText().toString().trim() + " " + getString(R.string.inch_short) + ")");
        	targetchest.setSummary(targetchest.getSummary() +   " (" + targetchest.getText().toString().trim() + " " + getString(R.string.inch_short) + ")");
        	targethip.setSummary(targethip.getSummary() +   " (" + targethip.getText().toString().trim() + " " + getString(R.string.inch_short) + ")");
        	heigh.setSummary(heigh.getText().toString().trim() + " " + getString(R.string.inch_short));
        	
        	lengthUnit.setSummary(lengthUnit.getSummary() + " (" + getString(R.string.inch_short) + ")");
        }
        age.setSummary(age.getText().toString().trim() + " " + getString(R.string.years));
        
        if(Gender.getValue().equals("1")){
        	Gender.setSummary(getString(R.string.male));
        }else{
        	Gender.setSummary(getString(R.string.female));
        }
        
        if(DateFormat.getValue().equals("1")){
        	DateFormat.setSummary(getString(R.string.MM_DD_YYYY));
        }else if(DateFormat.getValue().equals("2")){
        	DateFormat.setSummary(getString(R.string.DD_MM_YYYY));
        }else{
        	DateFormat.setSummary(getString(R.string.YYYY_MM_DD));
        }
        

   		PackageInfo pInfo = null;
		try {
			pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			Toast.makeText(this, "" + e, Toast.LENGTH_LONG).show();
		}
        
        Ver.setTitle("Ver: " + pInfo.versionName);
        
        
       // targetWeightt.setDialogTitle(targetWeightt.getTitle() + " (" + weightUnit.getValue() + ")");
       // targetWaistSizee.setDialogTitle(targetWaistSizee.getTitle() + " (" + lengthUnit.getValue() + ")");
        
       // targetchest.setDialogTitle(targetchest.getTitle() + " (" + lengthUnit.getValue() + ")");
       // targethip.setDialogTitle(targethip.getTitle() + " (" + lengthUnit.getValue() + ")");
        
    //	heigh.setDialogTitle(heigh.getTitle() + " (" + lengthUnit.getValue() + ")");        

    	
    	
 	   Preference button = (Preference)getPreferenceManager().findPreference("EnterPassword");      
 	   if(button != null) 
 	   {
 	        button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
 	                    public boolean onPreferenceClick(Preference arg0) {

 	                    	
 	                    	
 	                    	
 	                       try{

 	          				// custom dialog
 	          				final Dialog dialog = new Dialog(ShowSettingsActivity.this);
 	          				dialog.setContentView(R.layout.custom_dialog_password);
 	          			//	dialog.setTitle(getString(R.string.Weight));
 	          				
 	          				dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
 	          	 
 	          				Button dialogButton = (Button) dialog.findViewById(R.id.button22);
 	          			//	TextView TextView1 = (TextView) dialog.findViewById(R.id.textView1);

 	          				
 	          				
 	          				
 	          			//	TextView1.setText(publicWeight);
 	          				// Edit
 	          				dialogButton.setOnClickListener(new OnClickListener() {
 	          					public void onClick(View v) {
 	          						
 	          						TextView TextView2 = (TextView) dialog.findViewById(R.id.textView2);
 	          	    				EditText Password1 = (EditText) dialog.findViewById(R.id.Password1);
 	          	    				EditText Password2 = (EditText) dialog.findViewById(R.id.Password2);    						
 	          						
 	          	    				String P1 = Password1.getText().toString();
 	          	    				String P2 = Password2.getText().toString();
 	          	    				
 	          	    				if(P1.equals(P2)){	
 	          				//		if(Password1.equals(Password2.getText())){
 	          							dialog.dismiss();
 	          							
 	          		  	        		
 	          		  	        		SharedPreferences.Editor editor = preferences.edit();
 	          		  	        		editor.putString("PasswordText", P1);
 	          		  	        		editor.commit();	    							
 	          							
 	          						}else{
 	          							TextView2.setText(getText(R.string.passwords_do_not_match));
 	          						}
 	          						
 	          						

 	          					}
 	          				});
 	          				// delete
 	          				Button dialogButton1 = (Button) dialog.findViewById(R.id.button23);
 	          				dialogButton1.setOnClickListener(new OnClickListener() {
 	          					public void onClick(View v) {
 	          						dialog.dismiss();
 	          					//	Password.setChecked(false);
 	          					}
 	          				});				
 	          				dialog.show();				
 	                      }catch (Exception e){
 	                          Log.e("Error", "Cannot launch", e);
 	                      }  	                    	
 	                    	
 	                    	
 	                    	
 	                    	
 	                    	
 	                        return true;
 	                    }
 	                });     	
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
    
    
    @Override
    protected void onResume() {
        super.onResume();
        // Set up a listener whenever a key changes
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the listener whenever a key changes
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
        
        
    	if(targetWeightt.getText().toString().trim().length()==0){
    		targetWeightt.setText("0");
    	}else{
	        double target1 =  Double.parseDouble(targetWeightt.getText().toString().trim());
	        targetWeightt.setText("" + (int) target1);
    	}
    	if(targetWaistSizee.getText().toString().trim().length()==0){
    		targetWaistSizee.setText("0");
    	}else{
	        double temp =  Double.parseDouble(targetWaistSizee.getText().toString().trim());
	        targetWaistSizee.setText("" + (int) temp);
    	}
    	
    	if(targetchest.getText().toString().trim().length()==0){
    		targetchest.setText("0");	
    	}else{
	        double temp =  Double.parseDouble(targetchest.getText().toString().trim());
	        targetchest.setText("" + (int) temp);
    	}
    	if(targethip.getText().toString().trim().length()==0){
    		targethip.setText("0");	
    	}else{
	        double temp =  Double.parseDouble(targethip.getText().toString().trim());
	        targethip.setText("" + (int) temp);
    	}

    	if(heigh.getText().toString().trim().length()==0){
    		heigh.setText("0");	
    	}else{
	        double temp =  Double.parseDouble(heigh.getText().toString().trim());
	        heigh.setText("" + (int) temp);
    	}
    	if(age.getText().toString().trim().length()==0){
    		age.setText("0");	
    	}else{
	        double temp =  Double.parseDouble(age.getText().toString().trim());
	        age.setText("" + (int) temp);
    	}
    	
    	
//    	targetWeightt.setText(Integer.parseInt(targetWeightt.getText()) + "");
//    	targetWaistSizee.setText(Integer.parseInt(targetWaistSizee.getText()) + "");
//    	targetchest.setText(Integer.parseInt(targetchest.getText()) + "");
//    	targethip.setText(Integer.parseInt(targethip.getText()) + "");
//    	heigh.setText(Integer.parseInt(heigh.getText()) + "");
//    	age.setText(Integer.parseInt(age.getText()) + "");
    	
        
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,String key) 
    {
        if (key.equals("weightUnit")) {
        //	targetWeightt.setDialogTitle(targetWeightt.getTitle() + " (" + sharedPreferences.getString(key, "") + ")");
        	
            if(sharedPreferences.getString(key, "").equals("1")){
            	
            	targetWeightt.setDialogTitle(targetWeightt.getTitle() + " (" + getString(R.string.pound_short) + ")");
            	
            }else{
            	targetWeightt.setDialogTitle(targetWeightt.getTitle() + " (" + getString(R.string.kilogram_short) + ")");
            }        	
        	
        }else if (key.equals("DateFormat")) {        
        
        	Toast.makeText(this, "" + getString(R.string.need_restart), Toast.LENGTH_LONG).show();

        }else if (key.equals("lengthUnit")) {
        //	targetWaistSizee.setDialogTitle(targetWaistSizee.getTitle() + " (" + sharedPreferences.getString(key, "") + ")");
        	
        //	targetchest.setDialogTitle(targetchest.getTitle() + " (" + sharedPreferences.getString(key, "") + ")");
        //	targethip.setDialogTitle(targethip.getTitle() + " (" + sharedPreferences.getString(key, "") + ")");

        //	heigh.setDialogTitle(heigh.getTitle() + " (" + sharedPreferences.getString(key, "") + ")");
        	
        	
        	
            if(sharedPreferences.getString(key, "").equals("1")){
                targetWaistSizee.setDialogTitle(targetWaistSizee.getTitle() + " (" + getString(R.string.centimetre_short) + ")");
                
                targetchest.setDialogTitle(targetchest.getTitle() + " (" + getString(R.string.centimetre_short) + ")");
                targethip.setDialogTitle(targethip.getTitle() + " (" + getString(R.string.centimetre_short) + ")");
                
            	heigh.setDialogTitle(heigh.getTitle() + " (" + getString(R.string.centimetre_short) + ")");
            }else{
                targetWaistSizee.setDialogTitle(targetWaistSizee.getTitle() + " (" + getString(R.string.inch_short) + ")");
                
                targetchest.setDialogTitle(targetchest.getTitle() + " (" + getString(R.string.inch_short) + ")");
                targethip.setDialogTitle(targethip.getTitle() + " (" + getString(R.string.inch_short) + ")");
                
            	heigh.setDialogTitle(heigh.getTitle() + " (" + getString(R.string.inch_short) + ")");
            }        	
        	
        	
        	
        }else if (key.equals("Password")) {
        	
        //	CheckBoxPreference Password = (CheckBoxPreference)getPreferenceScreen().findPreference("Password");
        	
        	if(sharedPreferences.getBoolean(key, false) == true){

        		
                try{

    				// custom dialog
    				final Dialog dialog = new Dialog(this);
    				dialog.setContentView(R.layout.custom_dialog_password);
    			//	dialog.setTitle(getString(R.string.Weight));
    				
    				dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
    	 
    				Button dialogButton = (Button) dialog.findViewById(R.id.button22);
    			//	TextView TextView1 = (TextView) dialog.findViewById(R.id.textView1);

    				
    				
    				
    			//	TextView1.setText(publicWeight);
    				// Edit
    				dialogButton.setOnClickListener(new OnClickListener() {
    					public void onClick(View v) {
    						
    						TextView TextView2 = (TextView) dialog.findViewById(R.id.textView2);
    	    				EditText Password1 = (EditText) dialog.findViewById(R.id.Password1);
    	    				EditText Password2 = (EditText) dialog.findViewById(R.id.Password2);    						
    						
    	    				String P1 = Password1.getText().toString();
    	    				String P2 = Password2.getText().toString();
    	    				
    	    				if(P1.equals(P2)){	
    				//		if(Password1.equals(Password2.getText())){
    							dialog.dismiss();
    							
    		  	        		
    		  	        		SharedPreferences.Editor editor = preferences.edit();
    		  	        		editor.putString("PasswordText", P1);
    		  	        		editor.commit();	    							
    							
    						}else{
    							TextView2.setText(getText(R.string.passwords_do_not_match));
    						}
    						
    						

    					}
    				});
    				// delete
    				Button dialogButton1 = (Button) dialog.findViewById(R.id.button23);
    				dialogButton1.setOnClickListener(new OnClickListener() {
    					public void onClick(View v) {
    						
    						dialog.dismiss();
    						Password.setChecked(false);
    						
    					}
    				});				
    	 
    				dialog.show();				
    				
                	

                }catch (Exception e){
                    Log.e("Error", "Cannot launch", e);
                }        		
        	}
        }
        
        
        
        
        if(lengthUnit.getValue().equals("1")){
        	targetWaistSizee.setSummary(getString(R.string.please_provide_target_waist_size) +   " (" + targetWaistSizee.getText().toString().trim() + " " + getString(R.string.centimetre_short) + ")");
        	targetchest.setSummary(getString(R.string.please_provide_target_chest) +   " (" + targetchest.getText().toString().trim() + " " + getString(R.string.centimetre_short) + ")");
        	targethip.setSummary(getString(R.string.please_provide_target_hip) +   " (" + targethip.getText().toString().trim() + " " + getString(R.string.centimetre_short) + ")");
        	heigh.setSummary(heigh.getText().toString().trim() + " " + getString(R.string.centimetre_short));
        	
        	lengthUnit.setSummary(getString(R.string.Select_unit_of_length) + " (" + getString(R.string.centimetre_short) + ")");
        }else{
        	targetWaistSizee.setSummary(getString(R.string.please_provide_target_waist_size) +   " (" + targetWaistSizee.getText().toString().trim() + " " + getString(R.string.inch_short) + ")");
        	targetchest.setSummary(getString(R.string.please_provide_target_chest) +   " (" + targetchest.getText().toString().trim() + " " + getString(R.string.inch_short) + ")");
        	targethip.setSummary(getString(R.string.please_provide_target_hip) +   " (" + targethip.getText().toString().trim() + " " + getString(R.string.inch_short) + ")");
        	heigh.setSummary(heigh.getText().toString().trim() + " " + getString(R.string.inch_short));
        	
        	lengthUnit.setSummary(getString(R.string.Select_unit_of_length) + " (" + getString(R.string.inch_short) + ")");
        }        
        if(weightUnit.getValue().equals("1")){
        	targetWeightt.setSummary(getString(R.string.please_provide_target_weight) +   " (" + targetWeightt.getText().toString().trim() + " " + getString(R.string.pound_short) + ")");
        	weightUnit.setSummary(getString(R.string.Select_unit_of_weight) + " (" + getString(R.string.pound_short) + ")");
        }else{
        	targetWeightt.setSummary(getString(R.string.please_provide_target_weight) +   " (" + targetWeightt.getText().toString().trim() + " " + getString(R.string.kilogram_short) + ")");
        	weightUnit.setSummary(getString(R.string.Select_unit_of_weight) + " (" + getString(R.string.kilogram_short) + ")");
        }        
        age.setSummary(age.getText().toString().trim() + " " + getString(R.string.years));
        if(Gender.getValue().equals("1")){
        	Gender.setSummary(getString(R.string.male));
        }else{
        	Gender.setSummary(getString(R.string.female));
        }
        
        if(DateFormat.getValue().equals("1")){
        	DateFormat.setSummary(getString(R.string.MM_DD_YYYY));
        }else if(DateFormat.getValue().equals("2")){
        	DateFormat.setSummary(getString(R.string.DD_MM_YYYY));
        }else{
        	DateFormat.setSummary(getString(R.string.YYYY_MM_DD));
        }
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
}
