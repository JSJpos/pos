package co.kr.jsj.test2;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import co.kr.jsj.model.GoodsListDto;

public class GoodsListActivity extends Fragment {
private ListView goods_listView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View V = inflater.inflate(R.layout.goods_list, container, false);
		ArrayList Goods_List = new ArrayList<GoodsListDto>();
		
//		DB에서 값 받아와서 for문으로 ArrayList에 저장해야됨
		GoodsListDto goodsListDto1 = new GoodsListDto("1", "아메리카노", "5", "-");
		GoodsListDto goodsListDto2 = new GoodsListDto("2", "카푸치노", "10", "ca");
		Goods_List.add(goodsListDto1);
		Goods_List.add(goodsListDto2);
		
		goods_listView = (ListView)V.findViewById(R.id.goods_list_listview);
		
		GoodsListAdapter goodsListAdapter = new GoodsListAdapter(getActivity(), R.layout.goods_list_values, Goods_List);
		goods_listView.setAdapter(goodsListAdapter);
		goods_listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		
		return V;
	}
	
	private class GoodsListAdapter extends ArrayAdapter{
		private ArrayList<GoodsListDto> Goods_List;
		private GoodsListDto goodsList;
		
		public GoodsListAdapter(Context context, int resource, ArrayList Goods_List) {
			super(context, resource, Goods_List);
			Log.i("GoodsListActivity", "test3");
			this.Goods_List = Goods_List;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Log.i("GoodsListActivity", "test4");
			View v = convertView;
			
			if(v == null){
				 LayoutInflater vi = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				 v = vi.inflate(R.layout.goods_list_values, null);
			}
			goodsList = Goods_List.get(position);
			
//			Log.i("GoodsListActivity", "position : " + position);
			
			if(goodsList != null){
				TextView Goods_No = (TextView)v.findViewById(R.id.goods_list_no);
				TextView Goods_Name = (TextView)v.findViewById(R.id.goods_list_name);
				TextView Goods_Amount = (TextView)v.findViewById(R.id.goods_list_amount);
				TextView Goods_Note = (TextView)v.findViewById(R.id.goods_list_note);
				if(Goods_No != null){
					Goods_No.setText(goodsList.getGoods_No());
//					Log.i("GoodsListActivity", "No : " + goodsList.getGoods_No());
				}
				if(Goods_Name != null){
					Goods_Name.setText(goodsList.getGoods_Name());
//					Log.i("GoodsListActivity", "Name : " + goodsList.getGoods_Name());
				}
				if(Goods_Amount != null){
					Goods_Amount.setText(goodsList.getGoods_Amount() + "개");
//					Log.i("GoodsListActivity", "Amount : " + goodsList.getGoods_Amount());
				}
				if(Goods_Note != null){
					Goods_Note.setText(goodsList.getGoods_Note());
//					Log.i("GoodsListActivity", "Note : " + goodsList.getGoods_Note());
				}

			}
			
			return v;
		}
		
	}
	

}
