<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
   window.history.forward(1);
</script>
<title>SRF系統 - 登入</title>
</head>
<body>

<s:fielderror cssStyle="color:red" />

<s:form action="loginAction">

<%
	String loginNo = request.getParameter("userNo");
	session.setAttribute("loginNo", loginNo);
	loginNo = (String) session.getAttribute("loginNo");
%>

<s:if test='%{tranNo != null}'>
<% 
	String tranNo = request.getParameter("tranNo");
	session.setAttribute(tranNo, null);
%>
</s:if>

	<table align="center" border=1>
			<td colspan=2 align="center"><b>首頁</b></td><tr>ABC
			<td colspan=2>工號：<s:textfield name="userNo" theme="simple"/></td><tr>
			<td colspan=2>密碼：<s:password name="userPwd" theme="simple"/></td><tr>
			<td width="50%" align=center><s:submit value="送出" align="left" theme="simple"/></td>
			<td width="50%" align=center><input type="button" name="register" value="註冊" onclick="window.location.href='/SRF/Register.jsp'"/></td>
	</table>
</s:form>

</body>
</html>