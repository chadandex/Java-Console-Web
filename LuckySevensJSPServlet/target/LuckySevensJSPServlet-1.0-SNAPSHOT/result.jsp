<%-- 
    Document   : result
    Created on : Jul 16, 2018, 12:47:06 PM
    Author     : Chad
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lucky Sevens Results</title>
    </head>
    <body>
        <div align="center">
            <h1>Dice Roll Results</h1>
            <hr/>
        </div>
        
        <div align="center">
            <ul class="list-group" id="errorMessages"><b><p style="color:#FF0000">${message}</style></b></ul>

            <p>
                You bet $${initialBet}0
            </p>
            <p>
                You are broke after ${diceRolls} rolls
            </p>
            <p>
                You should have quit after ${bestRoll} rolls when you had $${highestWinnings}0
            </p>

            <!--Array Visual Tracking-->
            <p hidden>${diceTracking}</p>
            <p hidden>${moneyTracking}</p>

            
            <form action="index.jsp">
                <input type="submit" value="Bet Again!">
            </form>
           </div>
</body>
</html>
