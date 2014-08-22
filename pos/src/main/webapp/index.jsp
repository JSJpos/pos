<%@page import="co.kr.jsj.mybatis.StoreManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%

	StoreManager sm = new StoreManager();
	List list = sm.getStoreList();
	out.print("list : "+ list.size());

%>