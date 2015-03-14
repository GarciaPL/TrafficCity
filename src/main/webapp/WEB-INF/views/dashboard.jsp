<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ include file="includes/header.jsp" %>

<body>

<%@ include file="includes/logo.jsp" %>

<div class="box justify">
    <h1>Dashboard of <%=session.getAttribute("user_login")%>
    </h1>

    <p>&nbsp;</p>
</div>

<div style="height:20px; width:100%;">&nbsp;</div>

<ul class="h_nav">
    <a href="#" onclick="loadProjects()">
        <li><img src='<spring:url value="/resources/images/icons/calendar_alt_stroke_16x16.png"/>' width="16"
                 height="16"/>

            <h2>Projects</h2></li>
    </a>
    <a href="#" onclick="loadUserMarkers()">
        <li><img src='<spring:url value="/resources/images/icons/target_16x16.png"/>' width="16" height="16"/>

            <h2>Markers</h2></li>
    </a>
    <a href="#" onclick="loadFileUploader()">
        <li><img src='<spring:url value="/resources/images/icons/arrow_up_16x16.png"/>' width="16" height="16"/>

            <h2>Upload OSM</h2></li>
    </a>
    <a href="#" onclick="loadSettings()">
        <li><img src='<spring:url value="/resources/images/icons/equalizer_16x16.png"/>' width="16" height="16"/>

            <h2>Settings</h2></li>
    </a>
    <a href="#" onclick="loadLogs()">
        <li><img src='<spring:url value="/resources/images/icons/book_16x16.png"/>' width="16" height="16"/>

            <h2>Logs</h2></li>
    </a>
    <a href="#" onclick="loadIndex()">
        <li><img src='<spring:url value="/resources/images/icons/arrow_left_16x16.png"/>' width="16" height="16"/>

            <h2>Return</h2></li>
    </a>
    <li class="last"></li>
</ul>

<%@ include file="includes/footer.jsp" %>

</body>
</html>