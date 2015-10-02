<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript">
		   window.history.forward(1);
		</script>
		<title>SRF系統 - 註冊</title>
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

<s:fielderror cssStyle="color:red" />

	<table>
		<s:form action="registerAction" method='post' name="frm1" id="frm1">
			<table align="center" width="15%" border=1>
				<td colspan=2 align="center"><b>註冊</b></td><tr>
				<td width="50%">工號：</td><td width="50%"><s:textfield name="userNo" theme="simple"/></td><tr>
				<td width="50%">密碼：</td><td width="50%"><s:password name="userPwd" theme="simple"/></td><tr>
				<td width="50%">重複密碼：</td width="50%"><td><s:password name="reUserPwd" theme="simple"/></td><tr>
				<td width="50%">姓名：</td><td width="50%"><s:textfield name="userName" theme="simple"/></td><tr>
				<s:action name="findDeptList" id="RegisterAction"/>
				<td width="50%">部門：</td><td width="50%"><s:select name = "userDept" id="userDept" theme="simple" headerKey="-1" headerValue="(請選擇 )" list="#RegisterAction.deptList" listKey="deptNo" listValue="deptName" onchange="javascript:getUserDept(this)"/></td><tr>
				<td width="50%">公司郵件：</td><td width="50%"><s:textfield name="userMail" theme="simple"/></td><tr>
				<td width="50%">主管：</td><td width="50%"><s:select name="supervisor" id="supervisor" theme="simple" headerKey="-1" headerValue="(請選擇 )" list="#RegisterAction.supervisorList" listKey="userNo" listValue="userName" /></td><tr>
				<td width="50%" align=center><s:submit name="register" value="送出" align="left" theme="simple"/></td>
				<td width="50%" align=center><input type="button" value="回首頁" onclick="window.location.href='/SRF/Login.jsp'"/></td>
			</table>
		</s:form>
	</table>

		<SCRIPT type="text/javascript">
			function getUserDept(select)
			{
				$.ajax
				({
					contentType: 'application/x-www-form-urlencoded;charset=UTF-8;',
					type:'POST',
					url:'findSupervisorList?userDept=' + select.value,
		
					success : function(data)
					{
						var obj = document.forms['frm1'].supervisor;
						obj.length = data.length;
		
						for(var i=0 ; i<data.length ; i++)
						{
							if(data[i] != undefined)
							{
								obj[i].text=data[i];
								obj[i].value=data[i];
							}
						}
					},
					
					error : function (xhr, textStatus, errorThrown)
					{
						alert('Ajax request 發生錯誤' + xhr + '\n' + textStatus + '\n' + errorThrown);
						document.getElementById("frm1").reset();
					}
				});
			}
		</SCRIPT>
	</body>
</html>