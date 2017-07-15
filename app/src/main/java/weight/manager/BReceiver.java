package weight.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.PowerManager;
import android.os.Vibrator;



public class BReceiver extends BroadcastReceiver {
 @Override
 public void onReceive(Context context, Intent intent) {
//  Toast.makeText(context, "Don't panik but your time is up!!!!.",
//    Toast.LENGTH_SHORT).show();
  

  
  // Vibrate the mobile phone
  Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
  vibrator.vibrate(4000);
  
  
  	Intent i = new Intent(context, AlarmclockActivity.class);  
  	i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
  	context.startActivity(i);
  
	MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.alarm);
	mediaPlayer.start();
	
	
	
    PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
    PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "DoNjfdhotDimScreen");
    wl.acquire();
  
 }
 

}
