<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ include file="includes/header.jsp" %>

<body>

<%@ include file="includes/logo.jsp" %>

<div class="box justify" style="text-align: center">
    <h1>Settings</h1>

    <p>&nbsp;</p>

    <p>&nbsp;</p>

    <p>&nbsp;</p>

    <div class="box" align="center">
        <table class="tsc_tables2_14" style="width:80%;">
            <thead>
            <tr>
                <th scope="col">Property</th>
                <th scope="col">Value</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${settings}" var="setting">
                <tr>
                    <td><c:out value="${setting.propertyName}"/></td>
                    <td><input id="propValue_${setting.propertyName}" type="text" value="<c:out value="${setting.propertyValue}"/>" size="100" style="background-color: gainsboro" readonly></td>
                    <td><input id="change_${setting.propertyName}" name="submit" type="submit" value="Change" onclick="changeProperty('${setting.propertyName}', '${setting.propertyValue}')" class="form_send"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<p>&nbsp;</p>

<div id="propertyMessage" class="error"></div>
<p>&nbsp;</p>


<div style="height:50px; width:100%;">&nbsp;</div>

<div class="box" align="center">
    <input id="send" name="submit" type="submit" value="Back" onclick="loadDashboard()" class="form_send"/>
</div>

<%@ include file="includes/footer.jsp" %>

</body>
</html>