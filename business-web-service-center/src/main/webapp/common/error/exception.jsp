
<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link
			href="${ctx}/script/jquery/plugins/LigerUI/skins/Aqua/css/ligerui-all.css"
			rel="stylesheet" type="text/css" />
			<script src="${ctx}/script/jquery/jquery-1.3.2.min.js"
			type="text/javascript"></script>
		<script
			src="${ctx}/script/jquery/plugins/LigerUI/js/extends/ligerShowMessage.js"
			type="text/javascript"></script>
		<title>错误提示</title>
		<script type="text/javascript">
	$(function() {
		$("#eid").ligerShowMessage({
			message : '${exceptionMessage}'
		});
	});
</script>

	</head>

	<body>
		<div id="eid">
		</div>
	</body>
</html>
