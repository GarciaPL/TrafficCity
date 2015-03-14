<%@ include file="includes/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <title>TrafficCity</title>
    <meta name="description" content="TrafficCity"/>
    <meta name="robots" content="index, follow"/>
    <meta name="distribution" content="global"/>
    <meta name="language" content="en,pl"/>
    <meta name="expires" content="never"/>

    <link href='<spring:url value="/resources/css/googlefont.css"/>' rel="stylesheet" type="text/css"/>
    <link href='<spring:url value="/resources/css/mobile.css"/>' rel="stylesheet" type="text/css"/>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.3, user-scalable=yes"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>

    <script src='<spring:url value="/resources/js/jquery-1.8.2.min.js"/>' type="text/javascript"></script>
    <script src='<spring:url value="/resources/js/accordion.js"/>' type="text/javascript"></script>
    <script type="text/javascript">
        var contextPath = "${pageContext.request.contextPath}";
    </script>

    <!-- Obsluga i parsowanie formularzy -->
    <script type="text/javascript" src='<spring:url value="/resources/js/user.js"/>'></script>

    <style type="text/css">
        #map {
        <!-- width : 300 px;
        height : 300 px;
        -->
        }

        #map_wrap {
            position: relative;
            width: 100%;
            height: 280px;
            padding-top: 0px;
            border: 1px solid #aaa;
        }
    </style>

    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
    <script type="text/javascript">
        var map;
        var markers = [];

        function initialize() {
            var options = {
                mapTypeControlOptions: {
                    mapTypeIds: [google.maps.MapTypeId.ROADMAP, google.maps.MapTypeId.TERRAIN],
                    style: google.maps.MapTypeControlStyle.DROPDOWN_MENU
                }
            };

            var markerBounds = new google.maps.LatLngBounds();
            <c:forEach items="${markersList}" var="koordynaty">
                var randomPoint = new google.maps.LatLng(${koordynaty.latitude}, ${koordynaty.longitude});
                markerBounds.extend(randomPoint);
            </c:forEach>

            map = new google.maps.Map(document.getElementById('map'));
            map.setCenter(new google.maps.LatLng(${centerLongitude}, ${centerLatitude}));
            map.setZoom(14);
            map.fitBounds(markerBounds);
            map.setMapTypeId(google.maps.MapTypeId.ROADMAP);

            <c:forEach items="${markersList}" var="koordynaty">''
                addMarker(new google.maps.LatLng(${koordynaty.latitude}, ${koordynaty.longitude}), "TrafficCity", "${koordynaty.transportType}");
            </c:forEach>

        }
        function addMarker(latlng, myTitle, transportType) {
            if (transportType == 'Bus') {
                markers.push(new google.maps.Marker({
                    position: latlng,
                    map: map,
                    title: myTitle,
                    icon: '<spring:url value="/resources/images/icons/{transport}.png"><spring:param name="transport" value="bus" /></spring:url>'
                }));
            } else if (transportType == 'Car') {
                markers.push(new google.maps.Marker({
                    position: latlng,
                    map: map,
                    title: myTitle,
                    icon: '<spring:url value="/resources/images/icons/{transport}.png"><spring:param name="transport" value="car" /></spring:url>'
                }));
            } else {
                markers.push(new google.maps.Marker({
                    position: latlng,
                    map: map,
                    title: myTitle,
                    icon: '<spring:url value="/resources/images/icons/{transport}.png"><spring:param name="transport" value="car" /></spring:url>'
                }));
            }
        }
    </script>

</head>
<body>

<%@ include file="includes/logo.jsp" %>

<div class="box justify" style="text-align: center">
    <h1>User Markers</h1>

    <p>&nbsp;</p>
</div>

<p>&nbsp;</p>

<div id="map_wrap" align="center">
    <div id="map"></div>
</div>

<div style="height:70px; width:100%;">&nbsp;</div>

<div class="box" align="center">
    <input id="send" name="submit" type="submit" value="Return" onclick="loadDashboard()" class="form_send"/>
</div>

<%@ include file="includes/footer.jsp" %>

<script type="text/javascript">
    initialize();
</script>
</body>
</html>