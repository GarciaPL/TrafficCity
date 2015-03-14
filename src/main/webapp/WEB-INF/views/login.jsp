<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ include file="includes/header.jsp" %>

<body>

<%@ include file="includes/logo.jsp" %>

<div class="box" align="center">
    <h1>Login client</h1>

    <p>&nbsp;</p>

    <p>&nbsp;</p>

    <p>&nbsp;</p>

    <p>Phone number :</p>
    <input id="telefon" name="telefon" type="text" class="form_input"/>
    <span id="telefonInfo"></span>

    <p>&nbsp;</p>
    <input id="zgoda" name="zgoda" type="checkbox"> I agree to processing of location of above mobile phone
    number </input>
    <span id="zgodaInfo"></span>

    <p>&nbsp;</p>

    <div id="loginMessage" class="error"></div>
    <p>&nbsp;</p>

    <div class="form_send_div">
        <input id="register" name="submit" type="submit" value="Sign up" onclick="loadRegister()" class="form_send"/>
        <input id="send" name="submit" type="submit" value="Sign in" onclick="loginClient()" class="form_send"/>
    </div>

    <script src='<spring:url value="/resources/js/validation_client_login.js"/>' type="text/javascript"></script>

</div>

<%@ include file="includes/footer.jsp" %>

</body>
</html>