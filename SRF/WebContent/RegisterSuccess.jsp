<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
   window.history.forward(1);
</script>
<title>SRF系統 - 註冊成功</title>
</head>
<body>
	<table align="center" border=1 width="20%">
		<td colspan=2 width="20%" align="center"><b>註冊成功</b></td><tr>
		<td width="5%">工號 ：</td><td width="15%"><s:property value="userNo"/></td><tr>
		<td width="5%">密碼 ：</td><td width="15%"><s:property value="userPwd"/></td><tr>
		<td width="5%">姓名 ：</td><td width="15%"><s:property value="userName"/></td><tr>
		<td width="5%">部門 ：</td><td width="15%"><s:property value="deptName"/></td><tr>
		<td width="5%">郵件 ：</td><td width="15%"><s:property value="userMail"/></td><tr>
		<td width="5%">主管 ：</td><td width="15%"><s:property value="supervisor"/></td><tr>
		<td colspan=2 width="20%" align="center"><input type="button" value="回首頁" onclick="window.location.href='/SRF/Login.jsp'"/></td>
	</table>
	
	<p>
	
</body>
</html>