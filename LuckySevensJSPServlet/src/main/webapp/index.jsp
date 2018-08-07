<%-- 
    Document   : index
    Created on : Jul 16, 2018, 12:46:57 PM
    Author     : Chad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lucky Sevens</title>
    </head>
    <body>
        <div class="container">
            <div align="center">
                <h1 id="mainHeading">Lucky Sevens</h1>
                <hr/>
            </div>
            <div class="col-md-8">
                <p>
                <h2>Rules:</h2>
                <ul id="ruleList">
                    <li>Each round, the program rolls a virtual pair of dice</li>
                    <li>If the sum of the 2 dice is equal to 7, you wins $4; otherwise, you lose $1</li>
                </ul>
                </p>

                <!-- Input -->
                <div align="center">
                    <p>
                        Enter the amount of money you'd like to bet below
                    </p>
                    <form method="POST" action="LuckySevensServlet">
                        <input type="number" name="moneyBet">
                        <input type="submit" value="Roll Dice">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
