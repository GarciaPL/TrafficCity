<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ include file="includes/header.jsp" %>

<body>

<%@ include file="includes/logo.jsp" %>

<div class="box justify" style="text-align: center">
    <br/><br/>

    <h1>Cannot find Streets4PMI files!</h1><br/>

    <h1>Path : <c:out value="${streets4mpi_path}"/></h1><br/><br/><br/>

    <h1>More information about Streets4PMI under : </h1><br/>

    <h1>1. <a href="http://jfietkau.github.io/Streets4MPI/" target="_blank">http://jfietkau.github.io/Streets4MPI/</a>
    </h1>

    <h1>2. <a href="https://github.com/jfietkau/Streets4MPI" target="_blank">https://github.com/jfietkau/Streets4MPI</a>
    </h1>

    <h1>3. <a href="http://www.slideshare.net/jfietkau/streets4mpi-parallel-programming-project" target="_blank">http://www.slideshare.net/jfietkau/streets4mpi-parallel-programming-project</a>
    </h1><br/>

    <br/><br/><br/>

    <h1>Check your settings in config.properties</h1>
</div>

<%@ include file="includes/footer.jsp" %>

</body>
</html>