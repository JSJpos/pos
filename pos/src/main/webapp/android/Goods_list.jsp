<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="co.kr.jsj.mybatis.GoodsManager"%>
<%@page import="co.kr.jsj.model.GoodsDto"%>
<%@page import="co.kr.jsj.mybatis.StoreManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	
	System.out.println("test");
	
	int store_no = Integer.parseInt(request.getParameter("store_no"));
	
	GoodsManager goodsmanager = new GoodsManager();
	List list = goodsmanager.getGoodsList(store_no);
	
	GoodsDto dto = new GoodsDto();
	
	JSONArray GoodsArray = new JSONArray();
	
	for(int i=0; i<list.size(); i++){
		JSONObject GoodsObj = new JSONObject();
		dto = (GoodsDto)list.get(i);
		
		GoodsObj.put("goods_no", dto.getGoods_no());
		GoodsObj.put("goods_name", dto.getGoods_name());
		GoodsObj.put("goods_price", dto.getGoods_price());
		GoodsObj.put("goods_amount", dto.getGoods_amount());
		GoodsObj.put("goods_note", dto.getGoods_note());
		GoodsObj.put("goods_barcode", dto.getGoods_barcode());
		
		GoodsArray.add(GoodsObj);
	}
	
	JSONObject GoodsObj2 = new JSONObject();
	GoodsObj2.put("GoodsObj2", GoodsArray);
	
	System.out.println("GoodsArray size : " + GoodsArray.size());
	out.println(GoodsObj2);

%>
