package co.kr.jsj.test2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import co.kr.jsj.model.GoodsListDto;

public class GoodsActivity extends Fragment {
	private Button btn_sales, btn_reset;
	private Button btn_barcode;
	private ListView goods_select_listview, selected_listview;
	private String Store_no;
	private TextView selected_price, selected_price_edit;
	private int price=0;

	private GoodsButtonAsyncTask gt = new GoodsButtonAsyncTask();
	private ArrayList selected_list = new ArrayList();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View V = inflater.inflate(R.layout.goods_select, container, false);

		init(V);
		functionClick();

		gt.execute();
		
		
		return V;

	}

	public void init(View v) {

		btn_sales = (Button) v.findViewById(R.id.btn_sales);
		btn_barcode = (Button) v.findViewById(R.id.btn_barcode);
		goods_select_listview = (ListView) v
				.findViewById(R.id.goods_select_listview);
		selected_listview = (ListView) v.findViewById(R.id.selected_listview);
		Store_no = "2";
		btn_reset = (Button) v.findViewById(R.id.btn_reset);
		selected_price = (TextView) v.findViewById(R.id.selected_price);
		selected_price_edit = (TextView)v.findViewById(R.id.selected_price_edit);
	}

	public void functionClick() {
		btn_sales.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), PayActivity.class);
				startActivity(intent);

			}
		});

		btn_barcode.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(
						"com.google.zxing.client.android.SCAN");
				startActivityForResult(intent, 0);
				
			}
		});
		btn_reset.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				selected_list.clear();

				GoodsSelectAdapter goodsListAdapter = new GoodsSelectAdapter(
						getActivity(), R.layout.sales_goods_list, selected_list);
				selected_listview.setAdapter(goodsListAdapter);
				selected_listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
				
			}
		});
		
		
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		String content = data.getStringExtra("SCAN_RESULT");

		for (int i = 0; i < gt.Goods_List.size(); i++) {
			GoodsListDto dto = (GoodsListDto) gt.Goods_List.get(i);
			if (content.equals(dto.getGoods_Barcode())) {
				selected_list.add(dto);
				GoodsSelectAdapter goodsListAdapter = new GoodsSelectAdapter(
						getActivity(), R.layout.sales_goods_list, selected_list);
				selected_listview.setAdapter(goodsListAdapter);
				selected_listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		/*		int select_price = Integer.parseInt(dto.getGoods_Price());
				price += select_price;
				
				Log.d("tag", "가격 : "+ price);
				Log.d("tag", "가격1 : "+ price);
				if(price != 0){
					selected_price_edit.setText(price);
				}*/
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	private class GoodsSelectAdapter extends ArrayAdapter {
		private ArrayList<GoodsListDto> Goods_List;
		private GoodsListDto goodsList;

		public GoodsSelectAdapter(Context context, int resource,
				ArrayList Goods_List) {
			super(context, resource, Goods_List);
			this.Goods_List = Goods_List;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;

			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getActivity()
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.sales_goods_list, null);
			}
			goodsList = Goods_List.get(position);

			// Log.i("GoodsListActivity", "position : " + position);

			if (goodsList != null) {
				TextView Selected_name = (TextView) v
						.findViewById(R.id.selected_name);
				Selected_name.setSelected(true);
				TextView Selected_price = (TextView) v
						.findViewById(R.id.selected_price);
				Selected_price.setSelected(true);
				TextView Selected_amount = (TextView) v
						.findViewById(R.id.selected_amount);
				Selected_amount.setSelected(true);

				if (Selected_name != null) {
					Selected_name.setText(goodsList.getGoods_Name());
					// Log.i("GoodsListActivity", "No : " +
					// goodsList.getGoods_No());
				}
				if (Selected_price != null) {
					Selected_price.setText(goodsList.getGoods_Price());
					// Log.i("GoodsListActivity", "Name : " +
					// goodsList.getGoods_Name());
				}
				if (Selected_amount != null) {
					Selected_amount.setText("1");
					// Log.i("GoodsListActivity", "Name : " +
					// goodsList.getGoods_Name());
				}

			}
			return v;
		}
	}

	private class GoodsListAdapter extends ArrayAdapter {
		private ArrayList<GoodsListDto> Goods_List;
		private GoodsListDto goodsList;

		public GoodsListAdapter(Context context, int resource,
				ArrayList Goods_List) {
			super(context, resource, Goods_List);
			this.Goods_List = Goods_List;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;

			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getActivity()
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.goods_select_button, null);
			}
			goodsList = Goods_List.get(position);

			// Log.i("GoodsListActivity", "position : " + position);

			if (goodsList != null) {
				TextView Goods_select_name = (TextView) v
						.findViewById(R.id.goods_select_name);
				TextView Goods_select_price = (TextView) v
						.findViewById(R.id.goods_select_price);

				if (Goods_select_name != null) {
					Goods_select_name.setText(goodsList.getGoods_Name());
				}
				if (Goods_select_price != null) {
					Goods_select_price.setText(goodsList.getGoods_Price());
					// Log.i("GoodsListActivity", "Name : " +
					// goodsList.getGoods_Name());
				}

			}
			return v;
		}
	}

	public class GoodsButtonAsyncTask extends AsyncTask<Void, String, Void> {
		ArrayList Goods_List;

		@Override
		protected void onPostExecute(Void result) {

			GoodsListAdapter goodsListAdapter = new GoodsListAdapter(
					getActivity(), R.layout.goods_select_button, Goods_List);
			goods_select_listview.setAdapter(goodsListAdapter);
			goods_select_listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

			super.onPostExecute(result);
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				HttpClient client = new DefaultHttpClient();
				String url = "http://54.250.149.94:8080/pos/android/Goods_list.jsp";

				HttpPost post = new HttpPost(url);
				ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();
				param.add(new BasicNameValuePair("store_no", Store_no));
				UrlEncodedFormEntity ent = new UrlEncodedFormEntity(param,
						HTTP.UTF_8);
				post.setEntity(ent);

				HttpResponse respPost = client.execute(post);
				HttpEntity reEntity = respPost.getEntity();

				BufferedReader bufreader = new BufferedReader(
						new InputStreamReader(reEntity.getContent(), HTTP.UTF_8));

				String line = null;
				String page = "";

				// 버퍼의 웹문서 소스를 줄단위로 읽어 (line), page에 저장함
				while ((line = bufreader.readLine()) != null) {
					page += line;
				}

				Log.i("jsonpage", page);

				JSONObject json = new JSONObject(page);
				JSONArray GoodsArray = json.getJSONArray("GoodsObj2");
				Goods_List = new ArrayList<GoodsListDto>();

				String no = null, name = null, price = null, amount = null, note = null, barcode = null;

				for (int i = 0; i < GoodsArray.length(); i++) {
					json = GoodsArray.getJSONObject(i);

					no = json.getString("goods_no");
					name = json.getString("goods_name");
					price = json.getString("goods_price");
					amount = json.getString("goods_amount");
					note = json.getString("goods_note");
					barcode = json.getString("goods_barcode");

					GoodsListDto dto = new GoodsListDto(no, name, price,
							amount, note, barcode);
					Goods_List.add(dto);

				}
				Log.i("json", no + name + price + amount + note);

			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

	}

}
