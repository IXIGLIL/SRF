<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
   window.history.forward(1);
</script>
<title>SRF系統 - 建單詳細</title>
</head>
<body>
<body>   
	<table align="center" width="20%" border=1>
		<td colspan=2 align="center"><b>申請成功</b></td><tr>
		<td width="10%">需求單單號：</td><td width="10%"><s:property value="tranNo"/></td><tr>
		<td width="10%">需求單分類：</td><td width="10%"><s:property value="tranClassName"/></td><tr>
		<td width="10%">申請日期：</td><td width="10%"><s:property value="applyDate"/></td><tr>
		<td width="10%">申請部門：</td><td width="10%"><s:property value="userDept"/></td><tr>
		<td width="10%">申請人：</td><td width="10%"><s:property value="userName"/></td><tr>
		<td width="10%">申請人主管：</td><td width="10%"><s:property value="supervisor"/></td><tr>
		<s:if test='%{videoNo != null}'>
			<td width="10%">視訊會議號碼：</td><td width="10%"><s:property value="videoNo"/></td><tr>
			<td width="10%">視訊會議日期：</td><td width="10%"><s:property value="videoDate"/></td><tr>
			<td width="10%">視訊會議地點：</td><td width="10%"><s:property value="videoPlace"/></td><tr>
			<td width="10%">視訊會議聯絡人：</td><td width="10%"><s:property value="videoContact"/></td><tr>
		</s:if>
		<s:if test='%{phoneNo != null}'>
			<td width="10%">電話會議號碼：</td><td width="10%"><s:property value="phoneNo"/></td><tr>
			<td width="10%">電話會議日期：</td><td width="10%"><s:property value="phoneDate"/></td><tr>
			<td width="10%">電話會議聯絡人：</td><td width="10%"><s:property value="phoneContact"/></td><tr>
		</s:if>
		<td width="10%">主旨：</td><td width="10%"><s:property value="title"/></td><tr>
		<td width="10%">格式說明：</td><td width="10%"><s:property value="format"/></td><tr>
		<td width="10%">備註：</td><td width="10%"><s:property value="memo"/></td><tr>
		<td width="10%">附檔：</td><td width="10%"><s:property value="fileUploadFileName"/></td><tr>
		<td width="10%">需求日期：</td><td width="10%"><s:property value="requestDate"/></td>
	</table>
	
	<p>
	
	<table align="center" width="20%" border=1>
		<s:form action="menuAction" theme="simple">
			<s:hidden name="loginNo" value="%{loginNo}"/>
			<td align="center"><s:submit name="menu" value="主選單" align="center" theme="simple"/></td>
		</s:form>
	</table>
</body>
</html>