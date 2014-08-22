package co.kr.jsj.test2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends Activity {
	private Button loginbtn;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_login);
	    
	    init();
	    functionClick();
	    
	}
	
	public void init(){
		loginbtn = (Button)findViewById(R.id.loginbtn);
	}

	public void functionClick(){
		loginbtn.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), MainActivity.class);	
				startActivity(intent);				
			}
		});
	}
}
  