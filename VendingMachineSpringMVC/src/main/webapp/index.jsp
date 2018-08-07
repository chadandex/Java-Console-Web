<!--
Chad Andexler
The Software Guild
-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
        <link href="${pageContext.request.contextPath}/css/styles.css" type="text/css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div align="center">
                <h1 id="mainHeading"><b>Vending Machine</b></h1>
                <hr/>
                <div class="navbar">
                    <ul class="nav nav-tabs">
                        <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/index">Home</a></li>
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/displayVending">Vending Machine</a></li>
                    </ul>    
                </div>
            </div>

            <p><b>Welcome to Vending Machine <i>(without Ajax calls)</i></b></p>
            <li>'Change Return' will finish the current vending session</li>
            <br>
            <div class="footer">
                <footer style="position: absolute; bottom: 0; width: 50%; height: 50px;">
                    <font size="1">
                    <p>Chad Andexler</p>
                    <p>For: The Software Guild</p>
                    </font>
                </footer>
            </div>

        </div>
            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

        </body>
</html>

