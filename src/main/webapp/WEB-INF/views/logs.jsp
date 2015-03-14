<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ include file="includes/header.jsp" %>

<body>

<%@ include file="includes/logo.jsp" %>

<div class="box justify" style="text-align: center">
    <h1>Logs</h1>

    <p>&nbsp;</p>

    <p>&nbsp;</p>

    <p>&nbsp;</p>

    <div class="box" align="center">
        <table class="tsc_tables2_14" style="width:80%;">
            <thead>
            <tr>
                <th scope="col">Message</th>
                <th scope="col">Date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${logs}" var="logging">
                <tr>
                    <td><c:out value="${logging.message}"/></td>
                    <td><c:out value="${logging.date}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div style="height:50px; width:100%;">&nbsp;</div>

<div class="box" align="center">
    <input id="send" name="submit" type="submit" value="Back" onclick="loadDashboard()" class="form_send"/>
</div>

<%@ include file="includes/footer.jsp" %>

</body>
</html>