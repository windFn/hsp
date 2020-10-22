<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}/testMap">test map </a>
	<br/>
	<a href="${pageContext.request.contextPath}/testModelAndView">test modelAndView </a>
	<br/>
	<a href="${pageContext.request.contextPath}/testPathVariable/ganf/10">test pathVariable </a>
	<br/>
	<form action="${pageContext.request.contextPath}/testPojo" method="post">
		名称:<input type="text" name="name"/>
		年龄:<input type="text" name="age"/>
		<input type="submit" value=" submit"/>
	</form>
</body>
</html>