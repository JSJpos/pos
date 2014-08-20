package co.kr.jsj.test2;

import android.app.Fragment;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class SalesListActivity extends Fragment {
	private LocalActivityManager mlam;
	TabHost tabHost;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View V = inflater.inflate(R.layout.sales_list, container, false);

		TabHost host = (TabHost) V.findViewById(R.id.tab_host);
		Resources res = getResources();
		mlam = new LocalActivityManager(getActivity(), false);
		mlam.dispatchCreate(savedInstanceState);
		tabHost = (TabHost) V.findViewById(R.id.tab_host);
		tabHost.setup(mlam);

		TabHost.TabSpec spec;
		spec = host.newTabSpec("tab01");
		Intent intent1 = new Intent(getActivity(), DayActivity.class);
		spec.setContent(intent1);
		spec.setIndicator("일");
		tabHost.addTab(spec);
		spec = host.newTabSpec("tab02");
		Intent intent2 = new Intent(getActivity(), WeekActivity.class);
		spec.setContent(intent2);
		spec.setIndicator("주");
		tabHost.addTab(spec);
		spec = host.newTabSpec("tab03");
		Intent intent3 = new Intent(getActivity(), MonthActivity.class);
		spec.setContent(intent3);
		spec.setIndicator("월");
		tabHost.addTab(spec);
		spec = host.newTabSpec("tab04");
		Intent intent4 = new Intent(getActivity(), YearActivity.class);
		spec.setContent(intent4);
		spec.setIndicator("년");
		tabHost.addTab(spec);

		tabHost.setCurrentTab(0);

		return V;
	}
}
