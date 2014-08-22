<%@page import="co.kr.jsj.mybatis.GoodsManager"%>
<%@page import="co.kr.jsj.model.GoodsDto"%>
<%@page import="co.kr.jsj.mybatis.StoreManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	
	System.out.println("test");

	int goods_no = Integer.parseInt(request.getParameter("goods_no"));
	int store_no = Integer.parseInt(request.getParameter("store_no"));
	String goods_name = request.getParameter("goods_name");
	int goods_price = Integer.parseInt(request.getParameter("goods_price"));
	String goods_barcode = request.getParameter("goods_barcode");
	int goods_amount = Integer.parseInt(request.getParameter("goods_amount"));
	String goods_note = request.getParameter("goods_note");
	
	System.out.println("test2");
	
	GoodsDto dto = new GoodsDto();
	
	dto.setStore_no(store_no);
	dto.setGoods_name(goods_name);
	dto.setGoods_price(goods_price);
	dto.setGoods_barcode(goods_barcode);
	dto.setGoods_amount(goods_amount);
	dto.setGoods_note(goods_note);
	dto.setGoods_no(goods_no);
	
	System.out.println("상품 등록 : " + store_no + ", " + goods_name + ", "  + goods_barcode + ", " + goods_note);
	
	GoodsManager goodsmanager = new GoodsManager();
	goodsmanager.updateGoods(dto);
	
%>

