<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
   window.history.forward(1);
</script>
<title>SRF系統 - 搜尋</title>
</head>
<body>
<s:if test='%{tranNo != null}'>
<% 
	String tranNo = request.getParameter("tranNo");
	tranNo = request.getParameter("tranNo");
	session.setAttribute(tranNo, null);
%>
</s:if>
	<s:form action="searchTran">
	<s:hidden name="loginNo" value="%{loginNo}"/>
		<table width="20%" align="center" border=1>
				<td colspan=2 align="center"><b>搜尋</b></td><tr>
				<td>需求單單號：</td><td><s:textfield name="tranNo" theme="simple"/></td><tr>
				<td>主旨：</td><td><s:textfield name="title" theme="simple"/></td><tr>
				<td>申請人：</td><td><s:textfield name="applyUser" theme="simple"/></td><tr>
				<td>申請部門/分店：</td><td><s:textfield name="deptName" theme="simple"/></td><tr>
				<td>申請日期/起(yyyy-mm-dd)：</td><td><s:textfield name="fromDate" theme="simple"/></td><tr>
				<td>申請日期/結(yyyy-mm-dd)：</td><td><s:textfield name="toDate" theme="simple"/></td><tr>
				<td>審核主管：</td><td><s:textfield name="supervisor" theme="simple"/></td><tr>
				<td>狀態：</td><td><s:textfield name="status" theme="simple"/></td><tr>
				<td>含廢單：</td><td><s:select name = "reject" id="reject" theme="simple" list="{'N','Y'}"/></td>
		</table>
		
		<p>
		
		<table width="20%" align="center" border=1>
			<td colspan=2 align="left">
				申請人、審核主管可填工號或姓名<br><br>
				狀態參照如下(可輸入數字或文字)：<br>
				(1) 主管審核<br>
				(2) 資訊處審核<br>
				(3) 實作中<br>
				(4) 驗收中<br>
				(5) 結案<br>
				(6) 主管退件<br>
				(7) 資訊處退件<br>
				(8) 實作者退件<br>
				(9) 驗收者退件<br>
				(10) 申請人作廢
			</td>
		</table>
		
		<p>
		
		<table width="20%" align="center" border=1>
			<td colspan=2 align=center><s:submit name="search" value="搜尋" theme="simple"/></td>
		</table>
	</s:form>
	
	<p>
	
	<table width="20%" border=1 align="center">
		<td align="center">
			<s:form action="menuAction">
				<s:hidden name="loginNo" value="%{loginNo}"/>
				<s:submit name="menu" value="主選單" align="center"/>
			</s:form>
		</td>
	</table>
	
</body>
</html>