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
                        <li role="presentation"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                        <li role="presentation" class="active">
                            <a href="${pageContext.request.contextPath}/displayVending">Vending Machine</a></li>
                    </ul>    
                </div>

            </div>

            <ul class="list-group" id="errorMessages"></ul>

            <!--Product "Menu"-->
            <div class="col-md-8 machine" id="productMenu" style="background-color:#000099;" > <!-- spiced up the colour scheme -->
                <div class="col-md-8 productInfo" >
                    <ul id="products">
                        <c:forEach var="vending" items="${vendingList}">
                            <div class="square">
                                <div class="content">
                                    <form class="form-inline" role="form" action="${pageContext.request.contextPath}/currentItem${vending.itemId}" method="GET">

                                        <button type="submit" id="product${vending.itemId}" class="btn btn-default">
                                            <p align=left>${vending.itemId}</p>
                                            <center>${vending.productName}</center>
                                            <center><fmt:formatNumber value="${vending.productPrice}" type="currency"/></center>
                                            <br>
                                            <center>Quantity Left: ${vending.productQuantity}</center>
                                        </button> 

                                    </form>
                                </div>
                            </div>
                        </c:forEach>
                    </ul>
                </div>

            </div>


            <!--User Money Menu-->
            <div class="col-md-4" id="userOptions">
                <div align="center">
                    <label for="totalMoney" class="control-label">
                        Total $ In
                    </label>

                    <input type="text"
                           class="form-control"
                           id="totalMoney"
                           placeholder="$"
                           value="<fmt:formatNumber value="${currentMoney}" type="currency"/>" readonly>

                    <br/>

                    <form class="form-inline" role="form" action="${pageContext.request.contextPath}/addDollar" method="POST">
                        <div class="col-md-6">
                            <button type="submit"
                                    id="addDollar"
                                    class="button buttonDo">
                                Add Dollar
                            </button>
                        </div>
                    </form>

                    <form class="form-inline" role="form" action="${pageContext.request.contextPath}/addQuarter" method="POST">
                        <div class="col-md-6">
                            <button type="submit"
                                    id="addQuarter"
                                    class="button buttonQu">
                                Add Quarter
                            </button>
                        </div>
                    </form>

                    <form class="form-inline" role="form" action="${pageContext.request.contextPath}/addDime" method="POST">
                        <div class="col-md-6">
                            <button type="submit"
                                    id="addDime"
                                    class="button buttonDi">
                                Add Dime
                            </button>
                        </div>
                    </form>

                    <form class="form-inline" role="form" action="${pageContext.request.contextPath}/addNickel" method="POST">
                        <div class="col-md-6">
                            <button type="submit"
                                    id="addNickel"
                                    class="button buttonNi">
                                Add Nickel
                            </button>
                        </div>
                    </form>

                    <br/>
                </div>

                <div id="line">
                    <hr/>
                </div>

                <!--Message/Item Section-->
                <div align="center">
                    <label for="machMessage" class="control-label">
                        Messages
                    </label>
                </div>
                
                <form class="form-horizontal" role="form" id="item-form" action="${pageContext.request.contextPath}/purchaseProduct" method="POST">
                    <input type="text"
                           class="form-control"
                           id="machMessage"
                           placeholder="" 
                           value="${systemMessage}" readonly>
                    <br/>

                    <div class="form-group">
                        <label for="item"  class="col-md-4 control-label">
                            Item: 
                        </label>

                        <div class="col-md-8">
                            <input type="text"
                                   class="form-control"
                                   name="item"
                                   placeholder="No Item Currently"
                                   value="${currentItem}" readonly>
                        </div>
                    </div>

                    <div align="center">
                        <button type="submit"
                                id="getPurchase"
                                class="button buttonPurchase">
                            Make Purchase
                        </button>
                    </div>
                </form>
                <div id="line">
                    <hr/>
                </div>

                <!-- Change Return Section -->
                <div align="center">
                    <label for="changeLeft" class="control-label">
                        Change
                    </label>

                    <input type="text"
                           class="form-control"
                           id="changeLeft"
                           placeholder="$"
                           value="${changeMessage}" readonly>
                    <br/> 
                    <form class="form-inline" role="form" action="${pageContext.request.contextPath}/returnChange" method="POST">
                        <button type="submit"
                                id="getChange"
                                class="button buttonChRtrn">
                            Change Return
                        </button>
                    </form>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>