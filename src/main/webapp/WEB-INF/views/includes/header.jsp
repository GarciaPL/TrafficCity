<%@ include file="taglib.jsp" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <title>TrafficCity</title>
    <meta name="description" content="TrafficCity - find and eliminate traffic jams in your city!"/>
    <meta name="robots" content="index, follow"/>
    <meta name="distribution" content="global"/>
    <meta name="language" content="en,pl"/>
    <meta name="expires" content="never"/>

    <!--<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300' rel='stylesheet' type='text/css' />-->
    <link href='<spring:url value="/resources/css/googlefont.css"/>' rel='stylesheet' type='text/css'/>
    <link href='<spring:url value="/resources/css/mobile.css"/>' rel="stylesheet" type="text/css"/>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.3, user-scalable=yes"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>

    <script src='<spring:url value="/resources/js/jquery-1.8.2.min.js"/>' type="text/javascript"></script>
    <script src='<spring:url value="/resources/js/accordion.js"/>' type="text/javascript"></script>
    <script type="text/javascript">
        var contextPath = "${pageContext.request.contextPath}";
    </script>

    <script type="text/javascript" src='<spring:url value="/resources/js/user.js"/>' type="text/javascript"></script>

</head>