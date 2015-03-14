<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ include file="includes/header.jsp" %>

<body>

<%@ include file="includes/logo.jsp" %>

<div class="box" align="center">

    <h1>Projects</h1><br/><br/>

    <c:if test="${empty heatmapprojects}">Cannot find projects!</c:if>

    <div class="box" align="center">
        <table class="tsc_tables2_14" style="width:40%;">
            <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${heatmapprojects}" var="projects">
                <tr>
                    <td><c:out value="${projects}"/></td>
                    <td><input id="galleryshow" name="submit" type="submit" value="Show"
                               onclick="loadGallery('${projects}')" class="form_send"/><br/>
                        <input id="gallerygenerate" name="submit" type="submit" value="Generate"
                               onclick="loadGenerateGallery('${projects}')" class="form_send"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <p>&nbsp;</p>

    <p>&nbsp;</p>

    <p>&nbsp;</p>

</div>

<div style="height:50px; width:100%;">&nbsp;</div>

<div class="box" align="center">
    <input id="send" name="submit" type="submit" value="Back" onclick="loadDashboard()" class="form_send"/>
</div>

<%@ include file="includes/footer.jsp" %>

</body>
</html>