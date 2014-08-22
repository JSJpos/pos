package co.kr.jsj.test2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import co.kr.jsj.model.GoodsListDto;

public class GoodsListActivity extends Fragment {
	private ListView goods_listView;
	private Button goods_insert, goods_delete, goods_update;
	private String Store_no;
	private String no = null, name = null, price = null, amount = null,
			note = null, barcode = null;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View V = inflater.inflate(R.layout.goods_list, container, false);
		init(V);
		functionClick();
		MemAsyncTask mt = new MemAsyncTask();
		mt.execute();
		return V;
	}

	@Override
	public void onResume() {
		MemAsyncTask mt = new MemAsyncTask();
		mt.execute();
		super.onResume();
	}

	public void init(View V) {
		goods_listView = (ListView) V.findViewById(R.id.goods_list_listview);
		goods_insert = (Button) V.findViewById(R.id.goods_insert);
		goods_delete = (Button) V.findViewById(R.id.goods_delete);
		goods_update = (Button) V.findViewById(R.id.goods_update);
		Store_no = "2";
	}

	public void functionClick() {

		goods_insert.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), BarcodeActivity.class);
				startActivity(intent);
			}
		});
		goods_delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String title = "상품 삭제";
				String msg = "상품을 하시겠습니까?";
				AlertDialog alert = deleteDialog(title, msg);
				alert.show();
			}
		});

		goods_update.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),
						GoodsUpdateActivity.class);
				startActivity(intent);
			}
		});

		goods_listView
				.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

					@Override
					public void onCreateContextMenu(ContextMenu menu, View v,
							ContextMenuInfo menuInfo) {
						menu.add("상품 수정");
						menu.add("상품 삭제");
					}
				});
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		if (item.getTitle().equals("상품 수정")) {
			Intent intent = new Intent(getActivity(), GoodsUpdateActivity.class);
			intent.putExtra("Goods_no", no);
			intent.putExtra("Goods_name", name);
			startActivity(intent);
		} else if (item.getTitle().equals("상품 삭제")) {
			String title = "상품 삭제";
			String msg = "상품을 하시겠습니까?";
			AlertDialog alert = deleteDialog(title, msg);
			alert.show();
		}

		return super.onContextItemSelected(item);
	}

	public AlertDialog deleteDialog(String title, String msg) {
		AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
		alert.setTitle(title);
		alert.setMessage(msg);
		alert.setPositiveButton("예", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				DeleteGoods dg = new DeleteGoods();
				dg.execute();
				onResume();
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
				v = vi.inflate(R.layout.goods_list_values, null);
			}
			goodsList = Goods_List.get(position);

			if (goodsList != null) {
				TextView Goods_No = (TextView) v
						.findViewById(R.id.goods_list_no);
				TextView Goods_Name = (TextView) v
						.findViewById(R.id.goods_list_name);
				Goods_Name.setSelected(true);
				TextView Goods_Price = (TextView) v
						.findViewById(R.id.goods_list_price);
				Goods_Price.setSelected(true);
				TextView Goods_Amount = (TextView) v
						.findViewById(R.id.goods_list_amount);
				Goods_Amount.setSelected(true);
				TextView Goods_Note = (TextView) v
						.findViewById(R.id.goods_list_note);
				Goods_Note.setSelected(true);
				if (Goods_No != null) {
					Goods_No.setText(goodsList.getGoods_No());
				}
				if (Goods_Name != null) {
					Goods_Name.setText(goodsList.getGoods_Name());
				}
				if (Goods_Price != null) {
					Goods_Price.setText(goodsList.getGoods_Price() + "원");
				}
				if (Goods_Amount != null) {
					Goods_Amount.setText(goodsList.getGoods_Amount() + "개");
				}
				if (Goods_Note != null) {
					Goods_Note.setText(goodsList.getGoods_Note());
				}
			}
			return v;
		}
	}

	public class MemAsyncTask extends AsyncTask<Void, String, Void> {
		ArrayList<GoodsListDto> Goods_List;

		@Override
		protected void onPostExecute(Void result) {

			GoodsListAdapter goodsListAdapter = new GoodsListAdapter(
					getActivity(), R.layout.goods_list_values, Goods_List);
			goods_listView.setAdapter(goodsListAdapter);
			goods_listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

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
	public class DeleteGoods extends AsyncTask<Void, String, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			HttpClient client = new DefaultHttpClient();
			
			HttpPost post = new HttpPost(
					"http://54.250.149.94:8080/pos/android/Goods_delete.jsp");
			// 파라미터 값을 셋팅합니다.
			List<NameValuePair> param = new ArrayList<NameValuePair>();
			Log.d("넘버", "넘버 : "+no);
			param.add(new BasicNameValuePair("goods_no", no));

			// 한글 깨짐 방지를 위해 UTF-8로 셋팅합니다. (특문은 안보내는게 정신건강에 이롭습니다.)
			UrlEncodedFormEntity ent;
			try {
				ent = new UrlEncodedFormEntity(param, HTTP.UTF_8);
				post.setEntity(ent);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// 결과를 받습니다.
			HttpResponse responsePOST;
			try {
				responsePOST = client.execute(post);
				HttpEntity resEntity = responsePOST.getEntity();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
	}
}
