package co.kr.jsj.model;

public class GoodsListDto {
	private String Goods_No;
	private String Goods_Name;
	private String Goods_Amount;
	private String Goods_Note;
	private String Goods_Price;
	private String Goods_Barcode;
	
	public String getGoods_Barcode() {
		return Goods_Barcode;
	}

	public void setGoods_Barcode(String goods_Barcode) {
		Goods_Barcode = goods_Barcode;
	}

	public GoodsListDto(String Goods_No, String Goods_Name, String Goods_Price,
			String Goods_Amount, String Goods_Note, String Goods_Barcode) {
		this.Goods_No = Goods_No;
		this.Goods_Name = Goods_Name;
		this.Goods_Price = Goods_Price;
		this.Goods_Amount = Goods_Amount;
		this.Goods_Note = Goods_Note;
		this.Goods_Barcode = Goods_Barcode;
	}

	public String getGoods_Price() {
		return Goods_Price;
	}

	public void setGoods_Price(String goods_Price) {
		Goods_Price = goods_Price;
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
