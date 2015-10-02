<%@page import="oracle.net.ns.RedirectPacket"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
   window.history.forward(1);
</script>

<script language="javascript" type="text/javascript">
	var secs = 300;
	var URL;
	
	function Load(url)
	{
		URL = url;
		
		for(var i=secs ; i>=0 ; i=i-1)
		{
			window.setTimeout('doUpdate(' + i + ')', (secs - i) * 1000);			
		}
	}
	
	function doUpdate(num)
	{
		document.getElementById('ShowDiv').innerHTML = '將於' + num + '秒後退出到登入頁面！';

		if(num == 0)
			{
				window.location = URL;
			}
	}
</script>

<title>SRF系統 - 需求單資料</title>
</head>

<body>
<div id = "ShowDiv">
</div>
<script language="javascript">
	Load("http://192.168.0.49:8080/SRF/");
</script>

	<s:fielderror cssStyle="color:red" />
	
	<!-- 清空session紀錄，開放其他使用者存取此需求單 -->
	<s:if test='%{approveTran == "下一步" || updateTran == "更新" || rejectTran == "退件"}'>
	<% 
		String tranNo = request.getParameter("tranNo");
		session.setAttribute(tranNo, null);
	%>
	</s:if>
	
	<%
		String tranNo = request.getParameter("tranNo");
		String loginNo = request.getParameter("loginNo");
		if(tranNo.equals((String) session.getAttribute(tranNo)))
		{
	%>
	此需求單<%=tranNo%>目前由<%=loginNo%>開啟中！
	
	<table align="center" width="60%" border=1>
		<s:form action="menuAction" theme="simple">
			<s:hidden name="loginNo" value="%{loginNo}"/>
			<s:hidden name="tranNo" value="%{tranNo}"/>
			<td width="50%" align="center"><s:submit name="search" value="查詢需求單" align="left"/></td>
			<td width="50%" align="center"><s:submit name="menu" value="主選單" align="left"/></td>
		</s:form>
	</table>
	<%
		}
			
		if(!tranNo.equals((String) session.getAttribute(tranNo)))		// 紀錄session紀錄，封鎖其他使用者存取此需求單
		{
			session.setAttribute(tranNo, tranNo);
	%>		
	<s:form action="detailAction" enctype="multipart/form-data">
	
		<s:hidden name="loginNo" value="%{loginNo}"/>
		<s:hidden name="itOwnerNo" value="%{itOwnerNo}"/>
		<s:hidden name="applyUser" value="%{applyUser}"/>
		<s:hidden name="tranClass" value="%{tranClass}"/>
		<s:hidden name="lastStatus" value="%{lastStatus}"/>
		<table align="center" width="60%" border=1>
			<td colspan=3 align="center"><b>需求單資料</b></td>
			<tr>
			<td width="34%" bgcolor="#CFCFCF">更新日期：<s:textfield name="updateDate" theme="simple" value="%{updateDate}" readonly="true"/></td>
			<td width="33%" bgcolor="#CFCFCF">更新人員：<s:textfield name="updateUser" theme="simple" value="%{updateUser}" readonly="true"/></td>
			<td width="33%" bgcolor="#CFCFCF">狀態：<s:textfield name="status" theme="simple" value="%{status}" readonly="true"/></td>
			<tr>
			<td bgcolor="#CFCFCF">需求單單號：<s:textfield name="tranNo" theme="simple" value="%{tranNo}" readonly="true"/></td>
			<s:action name="findClassList" id="CreateTranAction" />
			<td colspan=2 bgcolor="#CFCFCF">需求單分類：<s:select name = "tranClass" id="tranClass" theme="simple" value="%{tranClass}" list="#CreateTranAction.classList" listKey="classNo" listValue="className" disabled="true"/></td>
			<tr>
			<td width="34%" bgcolor="#CFCFCF">收件日期：<s:textfield name="receiveDate" theme="simple" value="%{receiveDate}" readonly="true"/></td>
			<td width="33%" bgcolor="#CFCFCF">主管審核：<s:textfield name="supervisorCheck" theme="simple" value="%{supervisorCheck}" readonly="true"/></td>
			<td width="33%" bgcolor="#CFCFCF">資訊處審核：<s:textfield name="itCheck" theme="simple" value="%{itCheck}" readonly="true"/></td>
			<tr>
			<td width="33%" bgcolor="#CFCFCF">退件日期：<s:textfield name="rejectDate" theme="simple" value="%{rejectDate}" readonly="true"/></td>
			<td width="33%" bgcolor="#CFCFCF">退件人：<s:textfield name="rejectUser" theme="simple" value="%{rejectUser}" readonly="true"/></td>
			<s:if test='%{((loginNo.equals(applyUser) && status == "主管審核") || ("0001537".equals(loginNo) && (status == "實作者退件" || status == "資訊處審核")) || (loginNo.equals(itOwnerNo) && status == "實作中") || (loginNo.equals(itOwnerNo) && status == "驗收者退件") || (loginNo.equals(supervisorNo) && status == "主管審核") || loginNo.equals(applyUser) && status == "驗收中") && !(updateTran == "更新" || approveTran == "下一步" || rejectTran == "退件")}'>
				<td width="33%">退件原因：<s:textfield name="rejectReason" theme="simple" value="%{rejectReason}"/></td>
			</s:if>
			<s:else>
				<td width="33%" bgcolor="#CFCFCF">退件原因：<s:textfield name="rejectReason" theme="simple" value="%{rejectReason}" readonly="true"/></td>
			</s:else>
		</table>
			
		<p>
		
		<table align="center" width="60%" border=1>
			<td colspan=4 align="center"><b>申請資訊</b></td>
			<tr>
			<td width="25%" bgcolor="#CFCFCF">申請日期：<s:textfield name="applyDate" theme="simple" value="%{applyDate}" readonly="true"/></td>
			<td width="25%" bgcolor="#CFCFCF">申請部門：<s:textfield name="userDept" theme="simple" value="%{userDept}" readonly="true"/></td>
			<td width="25%" bgcolor="#CFCFCF">申請人：<s:textfield name="userName" theme="simple" value="%{userName}" readonly="true"/></td>
			<td width="25%" bgcolor="#CFCFCF">申請人主管：<s:textfield name="supervisor" theme="simple" value="%{supervisor}" readonly="true"/></td>
		</table>
		
		<p>
		
		<s:if test='%{videoNo != null)}'>
			<s:if test='%{loginNo.equals(applyUser) && (status = "主管審核" || status == "驗收中"} && !(updateTran == "更新" || approveTran == "下一步" || rejectTran == "退件")'>
				<table align="center" width="60%" border=1>
					<td colspan=4 align="center"><b>視訊會議資訊</b></td>
					<tr>
					<td width="25%" bgcolor="#CFCFCF">會議號碼：<s:textfield name="videoNo" theme="simple" value="%{videoNo}" readonly="true"/></td>
					<td width="25%">會議日期：<s:textfield name="videoDate" theme="simple" value="%{videoDate}"/></td>
					<s:action name="findPlaceList" id="CreateTranAction" />
					<td width="30%">會議地點：<s:select name = "videoPlace" id="videoPlace" theme="simple" value="%{videoPlace}" list="#CreateTranAction.placeList" listKey="placeNo" listValue="placeName"/></td>
					<td width="25%" bgcolor="#CFCFCF">會議聯絡人：<s:textfield name="videoContact" theme="simple" value="%{videoContact}" readonly="true"/></td>
				</table>
			</s:if>
			<s:else>
				<table align="center" width="60%" border=1>
					<td colspan=4 align="center"><b>視訊會議資訊</b></td>
					<tr>
					<td width="25%" bgcolor="#CFCFCF">會議號碼：<s:textfield name="videoNo" theme="simple" value="%{videoNo}" readonly="true"/></td>
					<td width="25%" bgcolor="#CFCFCF">會議日期：<s:textfield name="videoDate" theme="simple" value="%{videoDate}" readonly="true"/></td>
					<s:action name="findPlaceList" id="CreateTranAction" />
					<td width="30%" bgcolor="#CFCFCF">會議地點：<s:select name = "videoPlace" id="videoPlace" theme="simple" value="%{videoPlace}" list="#CreateTranAction.placeList" listKey="placeNo" listValue="placeName" disabled="true"/></td>
					<td width="25%" bgcolor="#CFCFCF">會議聯絡人：<s:textfield name="videoContact" theme="simple" value="%{videoContact}" readonly="true"/></td>
				</table>
			</s:else>
		</s:if>
		
		<p>
		
		<s:if test='%{phoneNo != null}'>
			<s:if test='%{loginNo.equals(applyUser) && (status == "主管審核" || status == "驗收中") && !(updateTran == "更新" || approveTran == "下一步" || rejectTran == "退件")}'>
				<table align="center" width="60%" border=1>
					<td colspan=3 align="center"><b>電話會議資訊</b></td>
					<tr>
					<td width="33%" bgcolor="#CFCFCF">會議號碼：<s:textfield name="phoneNo" theme="simple" value="%{phoneNo}" readonly="true"/></td>
					<td width="34%">會議日期：<s:textfield name="phoneDate" theme="simple" value="%{phoneDate}"/></td>
					<td width="33%">會議聯絡人：<s:textfield name="phoneContact" theme="simple" value="%{phoneContact}"/></td>
				</table>
			</s:if>
			
			<s:else>
				<table align="center" width="60%" border=1>
					<td colspan=3 align="center"><b>電話會議資訊</b></td>
					<tr>
					<td width="33%" bgcolor="#CFCFCF">會議號碼：<s:textfield name="phoneNo" theme="simple" value="%{phoneNo}" readonly="true"/></td>
					<td width="34%" bgcolor="#CFCFCF">會議日期：<s:textfield name="phoneDate" theme="simple" value="%{phoneDate}" readonly="true"/></td>
					<td width="33%" bgcolor="#CFCFCF">會議聯絡人：<s:textfield name="phoneContact" theme="simple" value="%{phoneContact}" readonly="true"/></td>
				</table>
			</s:else>
		</s:if>
		
		<s:if test='%{loginNo.equals(applyUser) && ("主管審核".equals(status) || "主管退件".equals(status))}'>
		<table align="center" width="60%" border=1>
			<td colspan=2 align="center"><b>需求單內容</b></td>
			<tr>
			<td width="100%" colspan=2>主旨：<s:textfield name="title" theme="simple" value="%{title}" size="50%"/></td>
			<tr>
			<td width="100%" colspan=2>格式說明：<br><s:textarea name="format" theme="simple" value="%{format}" cols="100%" rows="10" wrap="soft"/></td>
			<tr>
			<td width="100%" colspan=2>備註：<s:textfield name="memo" theme="simple" value="%{memo}" size="50%"/></td>
			<tr>
			<s:if test='%{(attach == "Y") && (!"".equals(attachName) || !"".equals(fileUpload))}'>
				<td width="50%">附檔：<s:submit name="downloadAttach" value="%{attachName}" align="left" theme="simple"/><s:submit name="deleteAttach" value="刪除附檔" align="left" theme="simple"></s:submit></td>
				<s:if test='%{deleteAttach == "刪除附檔"}'>
					<s:hidden name="deleteAttach" value="%{attachName}"/>			<!-- 給予刪除檔案功能檔名 -->
				</s:if>
			</s:if>
			<s:elseif test='%{attach == "N" && !"".equals(attachName)}'>
				<td width="50%" bgcolor="#CFCFCF">附檔：<s:textfield name="attach" theme="simple" value="%{attach}" readonly="true"/></td>
			</s:elseif>
			<s:else>
				<td width="50%">附檔：<s:file name="fileUpload" theme="simple"/></td>
			</s:else>
			<td width="50%">需求日期：<s:textfield name="requestDate" theme="simple" value="%{requestDate}"/></td>
		</table>
		</s:if>

		<s:else>
		<table align="center" width="60%" border=1>
			<td colspan=2 align="center"><b>需求單內容</b></td>
			<tr>
			<td width="100%" colspan=2 bgcolor="#CFCFCF">主旨：<s:textfield name="title" theme="simple" value="%{title}" size="50%" readonly="true"/></td>
			<tr>
			<td width="100%" colspan=2 bgcolor="#CFCFCF">格式說明：<br><s:textarea name="format" theme="simple" value="%{format}" cols="100%" rows="10" wrap="soft" readonly="true"/></td>
			<tr>
			<td width="100%" colspan=2 bgcolor="#CFCFCF">備註：<s:textfield name="memo" theme="simple" value="%{memo}" size="50%" readonly="true"/></td>
			<tr>
			<s:if test='%{attach == "Y" && !"".equals(attachName)}'>
				<td width="50%">附檔：<s:submit name="downloadAttach" value="%{attachName}" align="left" theme="simple"/><s:submit name="deleteAttach" value="刪除附檔" align="left" theme="simple"></s:submit></td>
				<s:if test='%{deleteAttach == "刪除附檔"}'>
					<s:hidden name="deleteAttach" value="%{attachName}"/>			<!-- 給予刪除檔案功能檔名 -->
				</s:if>
			</s:if>
			<s:elseif test='%{attach == "N" && !"".equals(attachName)}'>
				<td width="50%" bgcolor="#CFCFCF">附檔：<s:textfield name="attach" theme="simple" value="%{attach}" readonly="true"/></td>
			</s:elseif>
			<s:else>
				<td width="50%">附檔：<s:file name="fileUpload" theme="simple"/></td>
			</s:else>
			<td width="50%" bgcolor="#CFCFCF">需求日期：<s:textfield name="requestDate" theme="simple" value="%{requestDate}" readonly="true"/></td>
		</table>
		</s:else>
		
		<p>
		
		<s:if test='%{loginNo != "0001537" && status != "資訊處審核" && (status == "實作中" && !loginNo.equals(itOwnerNo))}'>		<!-- 查詢使用 -->
		<table align="center" width="60%" border=1>
			<td colspan=4 align="center"><b>資訊處處理資訊</b></td>
			<tr>
			<td width="25%" bgcolor="#CFCFCF">處理人員：<s:textfield name="itOwner" theme="simple" value="%{itOwner}" readonly="true"/></td>
			<td width="25%" bgcolor="#CFCFCF">處理人員主管：<s:textfield name="itManager" theme="simple" value="%{itManager}" readonly="true"/></td>
			<td width="25%" bgcolor="#CFCFCF">完成日期：<s:textfield name="doneDate" theme="simple" value="%{doneDate}" readonly="true"/></td>
			<td width="25%" bgcolor="#CFCFCF">完成時數：<s:textfield name="doneHour" theme="simple" value="%{doneHour}" readonly="true"/></td>
		</table>
		</s:if>
		
		<s:if test='%{loginNo == "0001537" && status == "資訊處審核"}'>															<!-- 資訊處審核用(指派工作給IT同仁) -->
		<table align="center" width="60%" border=1>
			<td colspan=4 align="center"><b>資訊處處理資訊</b></td>
			<tr>
			<s:action name="findItOwnerList" id="CreateTranAction" />
			<td width="25%" bgcolor="#ABFFAB">處理人員：<s:select name = "itOwner" id="itOwner" theme="simple" headerKey="-1" headerValue="(請選擇 )" list="#CreateTranAction.itList" listKey="userNo" listValue="userName" /></td>
			<td width="25%" bgcolor="#CFCFCF">處理人員主管：<s:textfield name="itManager" theme="simple" value="%{itManager}" readonly="true"/></td>
			<td width="25%" bgcolor="#CFCFCF">完成日期：<s:textfield name="doneDate" theme="simple" value="%{doneDate}" readonly="true"/></td>
			<td width="25%" bgcolor="#CFCFCF">完成時數：<s:textfield name="doneHour" theme="simple" value="%{doneHour}" readonly="true"/></td>
		</table>
		</s:if>

		<s:if test='%{loginNo.equals(itOwnerNo) && "實作中".equals(status)}'>													<!-- 實作IT用(Key完成日期、完成時數) -->
		<table align="center" width="60%" border=1>
			<td colspan=4 align="center"><b>資訊處處理資訊</b></td>
			<tr>
			<td width="25%" bgcolor="#CFCFCF">處理人員：<s:textfield name="itOwner" theme="simple" value="%{itOwner}" readonly="true"/></td>
			<td width="25%" bgcolor="#CFCFCF">處理人員主管：<s:textfield name="itManager" theme="simple" value="%{itManager}" readonly="true"/></td>
			<td width="25%" bgcolor="#ABFFAB">完成日期：<s:textfield name="doneDate" theme="simple" value="%{doneDate}"/></td>
			<td width="25%" bgcolor="#ABFFAB">完成時數：<s:textfield name="doneHour" theme="simple" value="%{doneHour}"/></td>
		</table>
		</s:if>
		
		<p>
		
		<s:if test='%{loginNo.equals(applyUser) && status == "驗收中"}'>		<!-- 驗收用 -->
		<table align="center" width="60%" border=1>
			<td colspan=3 align="center"><b>驗收資訊</b></td>
			<tr>
			<td width="25%" bgcolor="#ABFFAB">驗收結果：<s:textfield name="checkResult" theme="simple" value="%{checkResult}"/></td>
			<td width="25%" bgcolor="#ABFFAB">驗收結果：<s:select name = "checkResult" id="checkResult" value="%{checkResult}" theme="simple" list="{'Y','N'}"/></td>
			<td width="25%" bgcolor="#CFCFCF">驗收人員：<s:textfield name="checkUser" theme="simple" value="%{checkUser}" readonly="true"/></td>
			<td width="50%" bgcolor="#ABFFAB">驗收結果說明：<s:textfield name="checkMemo" theme="simple" value="%{checkMemo}"/></td>
		</table>
		</s:if>
		
		<s:if test='%{status != "驗收中"}'>									<!-- 一般用 -->
			<table align="center" width="60%" border=1>
				<td colspan=3 align="center"><b>驗收資訊</b></td>
				<tr>
				<td width="25%" bgcolor="#CFCFCF">驗收結果：<s:textfield name="checkResult" theme="simple" value="%{checkResult}" readonly="true"/></td>
				<td width="25%" bgcolor="#CFCFCF">驗收人員：<s:textfield name="checkUser" theme="simple" value="%{checkUser}" readonly="true"/></td>
				<td width="50%" bgcolor="#CFCFCF">驗收結果說明：<s:textfield name="checkMemo" theme="simple" value="%{checkMemo}" readonly="true"/></td>
			</table>
		</s:if>
		
		<p>
		
		<s:if test='%{!flowList.isEmpty()}'>
		<table align="center" width="60%" border=1>
			<td colspan=3 align="center"><b>流程進度<b></b></td>
			<s:iterator value="flowList" id='flowDate'>
				<tr width="100%" border=1>
					<td width="50%"><s:property value='lastStatusName'/> → <s:property value='statusName'/></td>
					<td width="50%"><s:property value='flowDate'/></td>
				</tr>
			</s:iterator>
		</table>
		</s:if>
		
		<s:if test='%{!(updateTran == "更新" || approveTran == "下一步" || rejectTran == "退件")}'>
		<p>
		
		<table align="center" width="60%" border=1>
			<td width="33%" align="center"><s:submit name="updateTran" value="更新" align="left" theme="simple"/></td>
			<td width="33%" align="center"><s:submit name="rejectTran" value="退件" align="left" theme="simple"/></td>
			<td width="34%" align="center"><s:submit name="approveTran" value="下一步" align="left" theme="simple"/></td>
		</table>
		</s:if>
	</s:form>
	
	<p>
	<table align="center" width="60%" border=1>
		<s:form action="menuAction" theme="simple">
			<s:hidden name="loginNo" value="%{loginNo}"/>
			<s:hidden name="tranNo" value="%{tranNo}"/>
			<td width="50%" align="center"><s:submit name="search" value="查詢需求單" align="left"/></td>
			<td width="50%" align="center"><s:submit name="menu" value="主選單" align="left"/></td>
		</s:form>
	</table>
	
	<%
		}
	%>
<p>
</body>
</html>