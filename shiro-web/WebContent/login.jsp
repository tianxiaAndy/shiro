<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>login   Page</h1>
     <form action="shiro/login"  method="post">
        UserName:<input type="text"  name="name"> <br>
        Password: <input type="password" name="pwd"> <br>
        <input type="submit" value="登录"> 
     </form>
</body>
</html>