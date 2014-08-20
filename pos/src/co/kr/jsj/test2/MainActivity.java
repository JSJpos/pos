package co.kr.jsj.test2;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;

public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentTransaction fragmentManager = getFragmentManager().beginTransaction();

    	switch (position) {
        	case 0:   		
        		 mTitle = getString(R.string.market_infor);    
        		fragmentManager.replace(R.id.container, new ShopInforActivity());   		
        		fragmentManager.commit();
                break;
            case 1:
            	 mTitle = getString(R.string.present_conditions);
            	fragmentManager.replace(R.id.container, new SalesListActivity());   		
                fragmentManager.commit();     
                break;
            case 2:
            	mTitle = getString(R.string.environment_setting);
            	break;
            case 3:         	
            	String title = "로그아웃";
				String msg = "정말로 로그아웃 하시겠습니까?";
				AlertDialog alert = logoutDialog(title, msg);
				alert.show();
            	
            	break;
            case 4:
            	mTitle = getString(R.string.app_ver);
            	fragmentManager.replace(R.id.container, new AppVerActivity());   		
                fragmentManager.commit();
            	break;
        }        
    }

    public AlertDialog logoutDialog(String title, String msg) {

		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle(title);
		alert.setMessage(msg);
		alert.setPositiveButton("예", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				/**
				 * 여기에 SharedPreferences를 사용한다
				 */
				
				/*SharedPreferences sp = getSharedPreferences("setLog",
						MODE_PRIVATE);
				SharedPreferences.Editor editor = sp.edit();
				editor.clear();
				editor.commit();*/
				finish();
			}
		});
		alert.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		AlertDialog dialog = alert.create();
		return dialog;
	}
   
    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }
    
}
