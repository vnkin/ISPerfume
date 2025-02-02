<%-- 
    Document   : SHIPPER_OrderDetailManagement
    Created on : Oct 19, 2024, 12:02:36 PM
    Author     : anhng
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Shipper | Order Detail</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Favicon -->
        <link href="img/fragrance.png" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="dashmin/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="dashmin/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

        <!-- Customized Bootstrap Stylesheet -->
        <link href="dashmin/css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="dashmin/css/style.css" rel="stylesheet">
    </head>
    <body>
        <div class="container-fluid position-relative bg-white d-flex p-0">
            <!-- Spinner Start -->
            <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
                <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                    <span class="sr-only">Loading...</span>
                </div>
            </div>
            <!-- Spinner End -->

            <!-- Sidebar Start -->
            <div class="sidebar pe-4 pb-3">
                <nav class="navbar bg-light navbar-light">
                    <a href="MainController?action=SHIPPER_SearchOrder" class="navbar-brand mx-4 mb-3">
                        <h3 class="text-primary"><i class="fa fa-hashtag me-2"></i>DASHMIN</h3>
                    </a>
                    <div class="d-flex align-items-center ms-4 mb-4">
                        <div class="position-relative">
                            <img class="rounded-circle" src="img/user.jpg" alt="" style="width: 40px; height: 40px;">
                            <div class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
                        </div>
                        <div class="ms-3">
                            <h6 class="mb-0">${sessionScope.LOGIN_USER.name}</h6>
                            <span>Shipper</span>
                        </div>
                    </div>
                </nav>
            </div>
            <!-- Sidebar End -->


            <!-- Content Start -->
            <div class="content">
                <!-- Navbar Start -->
                <nav class="navbar navbar-expand bg-light navbar-light sticky-top px-4 py-0">
                    <a href="MGR_Dashboard.jsp" class="navbar-brand d-flex d-lg-none me-4">
                        <h2 class="text-primary mb-0"><i class="fa fa-hashtag"></i></h2>
                    </a>
                    <a href="#" class="sidebar-toggler flex-shrink-0">
                        <i class="fa fa-bars"></i>
                    </a>
                    <div class="navbar-nav align-items-center ms-auto">


                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                                <img class="rounded-circle me-lg-2" src="img/user.jpg" alt="" style="width: 40px; height: 40px;">
                                <span class="d-none d-lg-inline-flex">${sessionScope.LOGIN_USER.name}</span>
                            </a>
                            <div class="dropdown-menu dropdown-menu-end bg-light border-0 rounded-0 rounded-bottom m-0">
                                <a href="MainController?action=Sign out" class="dropdown-item">Log Out</a>
                            </div>
                        </div>
                    </div>
                </nav>
                <!-- Navbar End -->


                <!-- Blank Start -->
                <div class="container-fluid pt-4 px-4">
                    <div class="row vh-100 bg-light rounded justify-content-center mx-0" style="height: auto !important" >
                        <!--                        <div class="col-md-6 text-center">-->
                        <!-- Recent Sales Start -->
                        <div class="container-fluid pt-4 px-4">
                            <div class="bg-light text-center rounded p-4">
                                <div class="d-flex align-items-center justify-content-between mb-4" >
                                    <h6 class="mb-0">Order's Shipper Detail</h6>
                                </div>

                                <div class="table-responsive" style="height: auto">
                                    <table class="table text-start align-middle table-bordered table-hover mb-0">
                                        <thead>
                                            <tr class="text-dark">
                                                <th scope="col">Product Name</th>
                                                <th scope="col">Size</th>
                                                <th scope="col">Quantity</th>
                                                <th scope="col">Price</th>
                                                <th scope="col">Total Unit Price</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="orderDetail" items="${requestScope.LIST_ORDER_DETAIL_SHIPPER}">
                                                <tr>
                                                    <!--                                                    productName-->
                                                    <td>
                                                        <c:forEach var="productDetail" items="${sessionScope.LIST_PRODUCT_DETAIL}">
                                                            <c:if test="${orderDetail.productDetailID == productDetail.productDetailID}">
                                                                <c:forEach var="product" items="${sessionScope.LIST_PRODUCT}">
                                                                    <c:if test="${product.productID == productDetail.productID}">
                                                                        ${product.name}
                                                                    </c:if>
                                                                </c:forEach>
                                                            </c:if>
                                                        </c:forEach>
                                                    </td>
                                                    <!--                                                    size-->
                                                    <td>
                                                        <c:forEach var="productDetail" items="${sessionScope.LIST_PRODUCT_DETAIL}">
                                                            <c:if test="${orderDetail.productDetailID == productDetail.productDetailID}">
                                                                <c:forEach var="size" items="${sessionScope.LIST_SIZE}">
                                                                    <c:if test="${size.sizeID == productDetail.sizeID}">
                                                                        ${size.name}
                                                                    </c:if>
                                                                </c:forEach>
                                                            </c:if>
                                                        </c:forEach>
                                                    </td>
                                                    <td>
                                                        ${orderDetail.quantity}
                                                    </td>
                                                    <td>
                                                        <fmt:formatNumber type="number" value="${orderDetail.unitPrice}"/>
                                                    </td>
                                                    <td>
                                                        <fmt:formatNumber type="number" value="${orderDetail.unitPrice* orderDetail.quantity}"/>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <div class="d-flex align-items-center justify-content-between mb-4" >
                                        <h6 class="mb-0">Total Price</h6>
                                        <p class="mb-0 ml-auto">
                                            <c:forEach var="orderDetail" items="${requestScope.LIST_ORDER_DETAIL_SHIPPER}" >
                                                <c:set var="unitTotal" value="${orderDetail.unitPrice * orderDetail.quantity}"/>
                                                <c:set var="Total" value="${unitTotal + Total}"/>
                                            </c:forEach>
                                            <fmt:formatNumber value="${Total}" type="number"/> VNĐ
                                        </p>
                                    </div>
                                    <c:choose>
                                        <c:when test="${requestScope.ORDER_SHIPPER.promotionID != 0}">
                                            <div class="d-flex justify-content-between align-items-center">
                                                <h6 class="mb-0">Promotion Applied:</h6>
                                                <c:forEach var="promotion" items="${sessionScope.LIST_PROMOTION}">
                                                    <c:if test="${requestScope.ORDER_SHIPPER.promotionID == promotion.promotionID}">
                                                        <p class="mb-0 ml-auto">${promotion.promotionName} - ${promotion.discountPer}% off</p>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                            <div class="d-flex justify-content-between align-items-center">
                                                <h6 class="mb-0">Final Price</h6>
                                                <c:forEach var="promotion" items="${sessionScope.LIST_PROMOTION}">
                                                    <c:if test="${requestScope.ORDER_SHIPPER.promotionID == promotion.promotionID}">
                                                        <p class="mb-0 ml-auto">
                                                            <fmt:formatNumber value="${Total * (1 - promotion.discountPer / 100.0)}" type="number"/> VNĐ
                                                        </p>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="d-flex justify-content-between align-items-center">
                                                <h6 class="mb-0">Final Price</h6>
                                                <p class="mb-0 ml-auto">
                                                    <fmt:formatNumber value="${Total}" type="number"/> VNĐ
                                                </p>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                    <a href="MainController?action=SHIPPER_SearchOrder" class="btn btn-primary">Back</a>
                                </div>
                            </div>
                        </div>
                        <!-- Recent Sales End -->
                        <!--</div>-->
                    </div>
                </div>
                <!-- Blank End -->


                <!-- Footer Start -->
                <div class="container-fluid pt-4 px-4">
                    <div class="bg-light rounded-top p-4">
                        <div class="row">
                            <div class="col-12 col-sm-6 text-center text-sm-start">
                                &copy; <a href="#">ISPERFUME</a>
                            </div>
                            <div class="col-12 col-sm-6 text-center text-sm-end">
                                <a href="#">Privacy Policy</a> | <a href="#">Terms of Use</a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Footer End -->
            </div>
            <!-- Content End -->


            <!-- Back to Top -->
            <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
        </div>

        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="dashmin/lib/chart/chart.min.js"></script>
        <script src="dashmin/lib/easing/easing.min.js"></script>
        <script src="dashmin/lib/waypoints/waypoints.min.js"></script>
        <script src="dashmin/lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="dashmin/lib/tempusdominus/js/moment.min.js"></script>
        <script src="dashmin/lib/tempusdominus/js/moment-timezone.min.js"></script>
        <script src="dashmin/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

        <!-- Template Javascript -->
        <script src="dashmin/js/main.js"></script>
    </body>
</html>
