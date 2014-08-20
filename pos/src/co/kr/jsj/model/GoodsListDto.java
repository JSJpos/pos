package co.kr.jsj.model;

public class GoodsListDto {
	private String Goods_No;
	private String Goods_Name;
	private String Goods_Amount;
	private String Goods_Note;
	
	public GoodsListDto(String Goods_No, String Goods_Name, String Goods_Amount, String Goods_Note){
		this.Goods_No = Goods_No;
		this.Goods_Name = Goods_Name;
		this.Goods_Amount = Goods_Amount;
		this.Goods_Note = Goods_Note;
	}
	
	public String getGoods_No() {
		return Goods_No;
	}
	public void setGoods_No(String goods_No) {
		Goods_No = goods_No;
	}
	public String getGoods_Name() {
		return Goods_Name;
	}
	public void setGoods_Name(String goods_Name) {
		Goods_Name = goods_Name;
	}
	public String getGoods_Amount() {
		return Goods_Amount;
	}
	public void setGoods_Amount(String goods_Amount) {
		Goods_Amount = goods_Amount;
	}
	public String getGoods_Note() {
		return Goods_Note;
	}
	public void setGoods_Note(String goods_Note) {
		Goods_Note = goods_Note;
	}
	
	
}
