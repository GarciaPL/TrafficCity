<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ include file="includes/header.jsp" %>

<body>

<%@ include file="includes/logo.jsp" %>

<div class="box justify" style="text-align: center">
    <a id="dbError"></a><br/><br/>

    <h1>Cannot connect to MongoDB database!</h1>

    <br/>

    <h1>Host : <c:out value="${mongo_host}"/></h1>

    <h1>Port : <c:out value="${mongo_port}"/></h1>

    <br/><br/><br/>

    <h1>Check your settings in config.properties</h1>
</div>

<%@ include file="includes/footer.jsp" %>

</body>
</html>