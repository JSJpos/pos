package co.kr.jsj.test2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class IntroActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_intro);
	
			T_handler.sendEmptyMessageDelayed(0, 2000);
		
	}
	public Handler T_handler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			if (msg.what == 0) {
				Intent intent = new Intent(getBaseContext(),
						LoginActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				finish();
			}
		};
	};
}
