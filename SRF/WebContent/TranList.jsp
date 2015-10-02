<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%-- <%@ page import="action.SearchTranAction"%>
<%SearchTranAction form = (SearchTranAction)pageContext.findAttribute("SearchTranAction");%> --%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
   window.history.forward(1);
</script>
<title>SRF系統 - 清單</title>
	<script src="http://code.jquery.com/jquery-1.9.0.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.0.0.js"></script>
		
	<link href="css/layout.css" rel="stylesheet" type="text/css" />
	<script src="js/jscal2.js"></script>
	<script src="js/lang/en.js"></script>
	<link rel="stylesheet" type="text/css" href="css/jscal2.css" />
	<link rel="stylesheet" type="text/css" href="css/border-radius.css" />
	<link rel="stylesheet" type="text/css" href="css/steel/steel.css" />
	<SCRIPT LANGUAGE="JavaScript" src="js/chkform.js" ></SCRIPT>
</head>
<body>
<s:if test='%{tranNo != null}'>
<% 
	String tranNo = request.getParameter("tranNo");
	tranNo = request.getParameter("tranNo");
	session.setAttribute(tranNo, null);
%>
</s:if>

	<s:form action="tranDetail" theme="simple">
		<s:hidden name="loginNo" value="%{loginNo}"/>
		<table align="center" width="75%" border="1" cellpadding="1" cellspacing="1">
			<td colspan=11 align="center"><b>需求單清單<b></td>
			<tbody>
				<tr align="center" valign="top" >
					<td width="5%" align="center" valign="middle" rowspan="2"><b>單號</b></td>
					<td width="11%" align="center" valign="middle" rowspan="2"><b>主旨</b></td>
					<td width="8%" align="center" valign="middle" rowspan="2"><b>分類</b></td>
					<td width="5%" align="center" valign="middle" rowspan="2"><b>申請人</b></td>
					<td width="5%" align="center" valign="middle" rowspan="2"><b>申請部門</b></td>
					<td width="13%" align="center" valign="middle" rowspan="2"><b>申請日期</b></td>
					<td width="5%" align="center" valign="middle" rowspan="2"><b>更新人員</b></td>
					<td width="13%" align="center" valign="middle" rowspan="2"><b>更新日期</b></td>
					<td width="5%" align="center" valign="middle" rowspan="2"><b>簽核主管</b></td>
					<td width="5%" align="center" valign="middle" rowspan="2"><b>狀態</b></td>
					<td width="5%" align="center" valign="middle" rowspan="2"><b>檢視</b></td>
				</tr>
			</tbody>
			
			<tfoot>
				<s:iterator value="tranList" id='tranNo'>
				<tr>
					<td align="center"><s:property value='tranNo'/></td>
					<td align="left"><s:property value='title'/></td>
					<td align="center"><s:property value='tranClassName'/></td>
					<td align="center"><s:property value='userName'/></td>
					<td align="center"><s:property value='deptName'/></td>
					<td align="center"><s:property value='applyDate'/></td>
					<td align="center"><s:property value='updateUser'/></td>
					<td align="center"><s:property value='updateDate'/></td>
					<td align="center"><s:property value='supervisor'/></td>
					<td align="center"><s:property value='statusName'/></td>
					<td align="center"><input type="submit" name="tranNo" value="<s:property value='tranNo'/>"></td>
				</tr>
				</s:iterator>
			</tfoot>
		</table>
	</s:form>
	
	<p>
	
	<table align="center" width="75%" border=1>
		<s:form action="menuAction" theme="simple">
			<s:hidden name="loginNo" value="%{loginNo}"/>
			<td width="50%" align="center"><s:submit name="search" value="查詢需求單"/></td>
			<td width="50%" align="center"><s:submit name="menu" value="主選單"/></td>
		</s:form>
	</table>

</body>
</html>