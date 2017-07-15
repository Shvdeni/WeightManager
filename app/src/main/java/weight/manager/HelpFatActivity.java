package weight.manager;

import android.app.Activity;
import android.os.Bundle;

public class HelpFatActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.help_fat);
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
}
