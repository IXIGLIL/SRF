<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
   window.history.forward(1);
</script> 
<title>SRF系統 - 主選單</title>
</head>
<body>

<s:if test='%{tranNo != null}'>
<% 
	String tranNo = request.getParameter("tranNo");
	tranNo = request.getParameter("tranNo");
	session.setAttribute(tranNo, null);
%>
</s:if>

<s:fielderror cssStyle="color:red" />
<s:form action="menuAction">
	<table align="center" width="20%" border=1>
	<s:if test="%{loginNo != null}">
		<s:hidden name="loginNo" value="%{loginNo}"/>
	</s:if>
	
	<s:else>
		<s:hidden name="loginNo" value="%{userNo}"/>
	</s:else>
	
		<td colspan=3 align="center"><b>SRF主選單</b></td><tr>
		<td width="33%" align="center"><s:submit name="create" value="申請需求單" align="left" theme="simple"/></td>
		<td width="33%" align="center"><s:submit name="search" value="查詢需求單" align="left" theme="simple"/></td>
		<td width="33%" align="center"><input type="button" value="回首頁" onclick="window.location.href='/SRF/Login.jsp'"/></td>
	</table>
</s:form>

<s:form action="searchTran">
	<table align="center" width="20%" border=1>
	<s:if test="%{loginNo != null}">
		<s:hidden name="loginNo" value="%{loginNo}"/>
	</s:if>
	
	<s:else>
		<s:hidden name="loginNo" value="%{userNo}"/>
	</s:else>
		<td align="center" width="50%">已申請：</td><td width="50%" align="center"><s:submit name="hadApply" align="left" theme="simple" value="%{hadApply}"/></td><tr>
		<td align="center" width="50%">待審核：</td><td width="50%" align="center"><s:submit name="waitApprove" align="left" theme="simple" value="%{waitApprove}"/></td><tr>
		<td align="center" width="50%">待完成：</td><td width="50%" align="center"><s:submit name="waitDone" align="left" theme="simple" value="%{waitDone}"/></td><tr>
		<td align="center" width="50%">待驗證：</td><td width="50%" align="center"><s:submit name="waitCheck" align="left" theme="simple" value="%{waitCheck}"/></td><tr>
		<td align="center" width="50%">退件：</td><td width="50%" align="center"><s:submit name="beenReject" align="left" theme="simple" value="%{beenReject}"/></td><tr>
	</table>
</s:form>

</body>
</html>