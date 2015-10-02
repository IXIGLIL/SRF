<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
   window.history.forward(1);
</script>
<title>SRF系統 - 建單</title>
</head>

<body>
	<s:fielderror cssStyle="color:red" />
	<s:form action="createTran" method="post" enctype="multipart/form-data">
		<s:hidden name="loginNo" value="%{loginNo}"/>
		<table width="60%" align="center" border=1>
			<td colspan=4 align=center><b>需求單申請資訊</b></td>
			<tr>
			<s:action name="findClassList" id="CreateTranAction" />
			<td width="25%" bgcolor="#ABFFAB">需求單分類：<s:select name = "tranClass" id="tranClass" theme="simple" headerKey="-1" headerValue="(請選擇 )" list="#CreateTranAction.classList" listKey="classNo" listValue="className" /></td>
			<td width="25%" bgcolor="#CFCFCF">申請部門：<s:textfield name="userDept" theme="simple" value="%{userDept}" readonly="true"/></td>
			<td width="25%" bgcolor="#CFCFCF">申請人：<s:textfield name="userName" theme="simple" value="%{userName}" readonly="true"/></td>
			<td width="25%" bgcolor="#CFCFCF">審核主管：<s:textfield name="supervisor" theme="simple" value="%{supervisor}" readonly="true"/></td>
		</table>
		
		<p>
		
		<table width="60%" align="center" border=1>
			<td colspan=3 align=center><b>視訊會議資訊</b></td>
			<tr>
			<td width="40%">視訊會議日期(yyyy-mm-dd hh24:mi:ss)：<s:textfield name="videoDate" theme="simple" value="%{videoDate}"/></td>
			<s:action name="findPlaceList" id="CreateTranAction" />
			<td width="30%">視訊會議地點：<s:select name = "videoPlace" id="videoPlace" theme="simple" headerKey="-1" headerValue="(請選擇 )" list="#CreateTranAction.placeList" listKey="placeNo" listValue="placeName" /></td>
			<td width="30%">視訊會議聯絡人：<s:textfield name="videoContact" theme="simple" value="%{videoContact}"/></td>
		</table>
		
		<p>
		
		<table width="60%" align="center" border=1>
			<td colspan=3 align=center><b>電話會議資訊</b></td>
			<tr>
			<td width="50%">電話會議日期(yyyy-mm-dd hh24:mi:ss)：<s:textfield name="phoneDate" theme="simple" value="%{phoneDate}"/></td>
			<td width="50%">電話會議聯絡人：<s:textfield name="phoneContact" theme="simple" value="%{phoneContact}"/></td>
		</table>
		
		<p>
		
		<table width="60%" align="center" border=1>
			<td colspan=2 align=center><b>需求單內容</b></td>
			<tr>
			<td width="100%" colspan=2 bgcolor="#ABFFAB">主旨：<s:textfield name="title" theme="simple" size="50%"/></td>
			<tr>
			<td width="100%" colspan=2>格式說明：<br><s:textarea name="format" theme="simple" cols="100%" rows="10" wrap="soft"/></td>
			<tr>
			<td width="100%" colspan=2>備註：<s:textfield name="memo" theme="simple" size="50%"/></td>
			<tr>
			<td width="50%">附檔：<s:file name="fileUpload" theme="simple"/></td>
			<td width="50%" bgcolor="#ABFFAB">需求日期(yyyy-mm-dd)：<s:textfield name="requestDate" theme="simple"/></td>			
		</table>
		
		<p>
		
		<table width="60%" align="center" border=1>
			<s:submit value="申請" align="center"/>
		</table>
	</s:form>
	
	<p>
	
	<table align="center" width="60%" border=1>
		<s:form action="menuAction" theme="simple">
			<s:hidden name="loginNo" value="%{loginNo}"/>
			<td width="50%" align="center"><s:submit name="search" value="查詢需求單"/></td>
			<td width="50%" align="center"><s:submit name="menu" value="主選單"/></td>
		</s:form>
	</table>
	
</body>
</html>
