<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
   window.history.forward(1);
</script>
<title>SRF系統 - 派送成功</title>
</head>
<body>
	<s:fielderror cssStyle="color:red" />
	
	<table align="center" width="10%" border=1>
		<s:form action="menuAction" theme="simple">
			<s:hidden name="loginNo" value="%{loginNo}"/>
			<td width="50%" align="center"><s:submit name="search" value="查詢需求單" align="left"/></td>
			<td width="50%" align="center"><s:submit name="menu" value="主選單" align="left"/></td>
		</s:form>
	</table>
	
	<p>
	
</body>
</html>