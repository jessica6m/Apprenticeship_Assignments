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
    </head>
    <body>
        <div class="container">
            <h1 class="text-center" >Vending Machine</h1>
            <hr/>

            <div class="row">
                <div class="col-md-8">
                    <!--                <h2>Items</h2>-->
                    <div class="row" id="content-rows"></div>
                    <div class="col-md inventory-item">
                        <c:forEach var="currentItem" items="${itemList}">
                            <a href="${pageContext.request.contextPath}/displayItemsPage/${currentItem.itemNumber}">  
                                <div class="col-md-4" style="border-style:solid;">
                                    <h2 style="color:red;"><c:out value="${currentItem.itemNumber}"/></h2>
                                    <p><c:out value="${currentItem.itemName}"/></p>
                                    <p><c:out value="${currentItem.itemPrice}"/></p>
                                    <p><c:out value="${currentItem.itemQuantity}"/></p>
                            </a>
                        </div>
                    </c:forEach>
                </div> 
            </div>
            <div class="col-md-4" id="total"> 
                <h3>Total $ In</h3>
                <form action="/addChange" class="form-group">
                    <input type="text" value="${amountTendered}" disabled/>
                    <div class="row">
                        <div class="col-sm">
                            <a href="${pageContext.request.contextPath}/addMoney/dollar" class="btn btn-default">Add Dollar</a>
                            <a href="${pageContext.request.contextPath}/addMoney/quarter" class="btn btn-default"> Add Quarter</a>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm">
                            <a href="${pageContext.request.contextPath}/addMoney/nickel" class="btn btn-default">Add Nickel</a>
                            <a href="${pageContext.request.contextPath}/addMoney/dime" class="btn btn-default">Add Dime</a>
                        </div>
                    </div>
                </form>
                <h3>Messages</h3>
                <form method="GET" action="/messages" class="form-group">
                    <input type="textarea" class="form-control" value="${message}" disabled/>
                </form>
                <h3>Item:</h3>
                <form method="POST" action="itemNumber" class="form-group">
                    <input type="textarea" class="form-control" value="${itemNumber}"disabled/><br>
                    <a href="${pageContext.request.contextPath}/purchase" class="btn btn-default">Make Purchase</a>
                </form>
                <h3>Change</h3>
                <form method="GET" action="change" class="form-group">
                    <input type="textarea" class="form-control" value="${change}" disabled/><br>
                    <a href="${pageContext.request.contextPath}/change/return" class="btn btn-default">Change Return</a>
                </form>

            </div> 
        </div>
    </div>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>