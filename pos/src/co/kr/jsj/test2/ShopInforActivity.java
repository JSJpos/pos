package co.kr.jsj.test2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class ShopInforActivity extends Fragment {
	public TextView sales_name, sales_addr, sales_tel1, sales_tel2, sales_type;
	public EditText sales_edit_name, sales_edit_addr, sales_edit_tel1,
			sales_edit_tel2;
	public Spinner sales_edit_type;
	public Button sales_update, sales_confirm, sales_reset;
	public ImageView sales_imageview;
	public ImageButton sales_imagebtn;
	private String[] type = { "카페", "도서", "의류", "기타" };
	public ArrayAdapter<String> typeAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View V = inflater.inflate(R.layout.shop_infor, container, false);
		edit(V);
		spinerAdapter();
		actionListener();
		return V;
	}

	public void edit(View v) {
		sales_name = (TextView) v.findViewById(R.id.sales_name);
		sales_addr = (TextView) v.findViewById(R.id.sales_addr);
		sales_tel1 = (TextView) v.findViewById(R.id.sales_tel1);
		sales_tel2 = (TextView) v.findViewById(R.id.sales_tel2);
		sales_type = (TextView) v.findViewById(R.id.sales_type);

		sales_edit_name = (EditText) v.findViewById(R.id.sales_edit_name);
		sales_edit_addr = (EditText) v.findViewById(R.id.sales_edit_addr);
		sales_edit_tel1 = (EditText) v.findViewById(R.id.sales_edit_tel1);
		sales_edit_tel2 = (EditText) v.findViewById(R.id.sales_edit_tel2);

		sales_edit_type = (Spinner) v.findViewById(R.id.sales_edit_type);

		sales_update = (Button) v.findViewById(R.id.sales_update);
		sales_confirm = (Button) v.findViewById(R.id.sales_confirm);
		sales_reset = (Button) v.findViewById(R.id.sales_reset);

		sales_imageview = (ImageView) v.findViewById(R.id.sales_imageview);
		sales_imagebtn = (ImageButton) v.findViewById(R.id.sales_imagebtn);
	}

	public void spinerAdapter() {
		typeAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_dropdown_item, type);
		sales_edit_type.setAdapter(typeAdapter);
	}

	public void actionListener() {

		sales_update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				sales_name.setVisibility(View.GONE);
				sales_addr.setVisibility(View.GONE);
				sales_tel1.setVisibility(View.GONE);
				sales_tel2.setVisibility(View.GONE);
				sales_type.setVisibility(View.GONE);
				sales_update.setVisibility(View.GONE);
				sales_imageview.setVisibility(View.GONE);

				sales_edit_name.setVisibility(View.VISIBLE);
				sales_edit_addr.setVisibility(View.VISIBLE);
				sales_edit_tel1.setVisibility(View.VISIBLE);
				sales_edit_tel2.setVisibility(View.VISIBLE);
				sales_edit_type.setVisibility(View.VISIBLE);
				sales_imageview.setVisibility(View.VISIBLE);

				sales_confirm.setVisibility(View.VISIBLE);
				sales_reset.setVisibility(View.VISIBLE);
			}
		});

		sales_reset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				sales_name.setVisibility(View.VISIBLE);
				sales_addr.setVisibility(View.VISIBLE);
				sales_tel1.setVisibility(View.VISIBLE);
				sales_tel2.setVisibility(View.VISIBLE);
				sales_type.setVisibility(View.VISIBLE);
				sales_update.setVisibility(View.VISIBLE);
				sales_imageview.setVisibility(View.VISIBLE);

				sales_edit_name.setVisibility(View.GONE);
				sales_edit_addr.setVisibility(View.GONE);
				sales_edit_tel1.setVisibility(View.GONE);
				sales_edit_tel2.setVisibility(View.GONE);
				sales_edit_type.setVisibility(View.GONE);
				sales_imageview.setVisibility(View.GONE);

				sales_confirm.setVisibility(View.GONE);
				sales_reset.setVisibility(View.GONE);

			}
		});

	}

}
