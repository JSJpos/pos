package co.kr.jsj.test2;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class GoodsActivity extends Fragment {
	Button btn_sales;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View V = inflater.inflate(R.layout.goods_select, container, false);

		btn_sales = (Button) V.findViewById(R.id.btn_sales);

		btn_sales.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), PayActivity.class);
				startActivity(intent);

			}
		});

		return V;

	}

}
