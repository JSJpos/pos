package co.kr.jsj.test2;

import java.io.IOException;
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

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class BarcodeActivity extends Activity {
	private Button insert_btn, insert_goods, insert_goods_reset;
	private EditText goods_barcode, goods_name,goods_price,goods_amount,goods_note;
	private String name, barcode, note,price, amount, store_no;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_goods_insert);
	    init();
	    
	    functionGoods();
	}
	
	public void init(){
	    insert_btn = (Button)findViewById(R.id.insert_btn);
	    insert_goods = (Button)findViewById(R.id.insert_goods);
	    insert_goods_reset = (Button)findViewById(R.id.insert_goods_reset);
	    
	    goods_barcode = (EditText)findViewById(R.id.goods_barcode);
	    goods_note = (EditText) findViewById(R.id.goods_note);
	    goods_name = (EditText) findViewById(R.id.goods_name);
	    goods_price = (EditText) findViewById(R.id.goods_price);
	    goods_amount = (EditText) findViewById(R.id.goods_amount);
	}
	
	public void getEditText(){
		name = goods_name.getText().toString().trim();
		price = goods_price.getText().toString().trim();
		amount = goods_amount.getText().toString().trim();
		barcode = goods_barcode.getText().toString().trim();
		note = goods_note.getText().toString().trim();
		store_no ="2";
	}

	public void functionGoods(){
		  insert_btn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
				//Intent intent = new Intent(getBaseContext(), CaptureActivity.class);
				Intent intent = new Intent("com.google.zxing.client.android.SCAN");
				startActivityForResult(intent, 0);
			
				}
			});  
		  insert_goods.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getEditText();
				InsertGoods ig = new InsertGoods();
				ig.execute();
				finish();
			}
		});
		  
		  insert_goods_reset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();			
			}
		});
	}
		
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		String content = data.getStringExtra("SCAN_RESULT");
		goods_barcode.setText(content);
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	public class InsertGoods extends AsyncTask<Void, String, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			HttpClient client = new DefaultHttpClient();
			
			HttpPost post = new HttpPost(
					"http://54.250.149.94:8080/pos/android/Goods_insert.jsp");
			// 파라미터 값을 셋팅합니다.
			List<NameValuePair> param = new ArrayList<NameValuePair>();
			param.add(new BasicNameValuePair("goods_name", name));
			param.add(new BasicNameValuePair("goods_price", price));
			param.add(new BasicNameValuePair("goods_amount", amount));
			param.add(new BasicNameValuePair("goods_barcode", barcode));
			param.add(new BasicNameValuePair("goods_note", note));
			param.add(new BasicNameValuePair("store_no", store_no));

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
