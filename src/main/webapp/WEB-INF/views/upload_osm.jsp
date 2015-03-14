<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ include file="includes/header.jsp" %>

<body>

<%@ include file="includes/logo.jsp" %>

<div class="box" align="center">
    <h1>Upload OSM file</h1><br/><br/>

    Streets4MPI requires local OpenStreetMap data in OSM format.<br/>
    This data may be acquired e.g. here: <br/><br/>
    <ul>
        <li><a href="http://download.geofabrik.de/osm/" target="_blank">http://download.geofabrik.de/osm/</a><br/></li>
        <li><a href="http://www.openstreetmap.org/export" target="_blank">www.openstreetmap.org/export</a></li>
    </ul>


    <p>&nbsp;</p>

    <p>&nbsp;</p>
    Make sure that in your system are installed :<br/><br/>
    <ul>
        <li>mpi4py - <a href="https://pypi.python.org/pypi/mpi4py">https://pypi.python.org/pypi/mpi4py</a></li>
        <li>imposm.parser - <a href="http://imposm.org/docs/imposm.parser/1.0.2/install.html">http://imposm.org/docs/imposm.parser/1.0.2/install.html</a>
        </li>
        <li>python-graph - <a href="https://pypi.python.org/pypi/python-graph-core/">https://pypi.python.org/pypi/python-graph-core/</a>
        </li>
        <li>Python Imaging Library - <a
                href="http://www.pythonware.com/products/pil/">http://www.pythonware.com/products/pil/</a></li>
    </ul>

    <p>&nbsp;</p>

    <p>&nbsp;</p>

    <form style="text-align: center;" method="POST" action="uploadfile" enctype="multipart/form-data">
        File OSM : <input type="file" id="file" name="file"><br/>
        Project Name for file: <input type="text" id="name" name="name"><br/> <br/>
        <input id="upload" type="submit" class="form_send" value="Upload">
        <input id="send" name="submit" type="submit" value="Back" onclick="loadDashboard()" class="form_send"/>
    </form>

    <br/><br/><br/>
    ${uploadMessage}

</div>

<%@ include file="includes/footer.jsp" %>

</body>
</html>