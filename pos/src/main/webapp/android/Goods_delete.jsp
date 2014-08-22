<%@page import="co.kr.jsj.mybatis.GoodsManager"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");

	int goods_no = Integer.parseInt(request.getParameter("goods_no"));

	GoodsManager goodsmanager = new GoodsManager();
	goodsmanager.deleteGoods(goods_no);
%>
