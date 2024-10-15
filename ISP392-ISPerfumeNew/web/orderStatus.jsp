<%-- 
    Document   : orderStatus
    Created on : Sep 28, 2024, 8:18:28 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>MultiShop - Online Shop Website Template</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">  

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/animate/animate.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap"
              rel="stylesheet">
        <link href="css/styleOrderStatus.css" rel="stylesheet">
        <style>
            

        </style>
    </head>
    <body>
        <!-- Topbar Start -->
        <div class="container-fluid">
            <div class="row bg-secondary py-1 px-xl-5">
                <div class="col-lg-6 d-none d-lg-block">
                    <div class="d-inline-flex align-items-center h-100">
                        <a class="text-body mr-3" href="MGR_Dashboard.jsp">MANAGER</a>
                        <a class="text-body mr-3" href="">Contact</a>
                        <a class="text-body mr-3" href="">Help</a>
                        <a class="text-body mr-3" href="">FAQs</a>
                    </div>
                </div>
                <div class="col-lg-6 text-center text-lg-right">
                    <div class="d-inline-flex align-items-center">
                        <div class="btn-group">
                            <button type="button" class="btn btn-sm btn-light dropdown-toggle" data-toggle="dropdown">My Account</button>
                            <div class="dropdown-menu dropdown-menu-right">
                                <a class="dropdown-item btn" type="button" href="signin.jsp">Sign in</a>
                                <a class="dropdown-item btn" type="button" href="signup.jsp">Sign up</a>
                                <a class="dropdown-item btn" type="button" href="MainController?action=Signout">Sign out</a>                                
                            </div>
                        </div>                                            
                    </div>
                    <div class="d-inline-flex align-items-center d-block d-lg-none">
                        <a href="" class="btn px-0 ml-2">
                            <i class="fas fa-heart text-dark"></i>
                            <span class="badge text-dark border border-dark rounded-circle" style="padding-bottom: 2px;">0</span>
                        </a>
                        <a href="" class="btn px-0 ml-2">
                            <i class="fas fa-shopping-cart text-dark"></i>
                            <span class="badge text-dark border border-dark rounded-circle" style="padding-bottom: 2px;">0</span>
                        </a>
                    </div>
                </div>
            </div>
            <div class="row align-items-center bg-light py-3 px-xl-5 d-none d-lg-flex">
                <div class="col-lg-4">
                    <a href="home.jsp" class="text-decoration-none">
                        <span class="h1 text-uppercase text-primary bg-dark px-2">IS</span>
                        <span class="h1 text-uppercase text-dark bg-primary px-2 ml-n1">Perfume</span>
                    </a>
                </div>
                <div class="col-lg-4 col-6 text-left">
                    <form action="">
                        <div class="input-group">
                            <form action="MainController" method="POST">
                                <input type="text" class="form-control" placeholder="Enter orderID.." name="orderID">
                                <div class="input-group-append">
                                    <span class="input-group-text bg-transparent text-primary" style="padding-bottom: 5px ">                                 
                                        <button name="action" value="" type="submit" class="btn btn-block" style="padding: 0"><i class="fa fa-search"></i></button>
                                    </span>
                                </div>
                            </form>
                        </div>
                    </form>
                </div>
                <div class="col-lg-4 col-6 text-right">
                    <p class="m-0">Customer Service</p>
                    <h5 class="m-0">+012 345 6789</h5>
                </div>
            </div>
        </div>
        <!-- Topbar End -->


        <!-- Navbar Start -->
        <form action="MainController">
            <div class="container-fluid bg-dark mb-30">
                <div class="row px-xl-5">
                    <div class="col-lg-3 d-none d-lg-block">
                        <a class="btn d-flex align-items-center justify-content-between bg-primary w-100" data-toggle="collapse" href="#navbar-vertical" style="height: 65px; padding: 0 30px;">
                            <h6 class="text-dark m-0"><i class="fa fa-bars mr-2"></i>Category</h6>
                            <i class="fa fa-angle-down text-dark"></i>
                        </a>
                        <nav class="collapse position-absolute navbar navbar-vertical navbar-light align-items-start p-0 bg-light" id="navbar-vertical" style="width: calc(100% - 30px); z-index: 999;">
                            <div class="navbar-nav w-100">
                                <c:forEach var="Category" items="${sessionScope.LIST_CATEGORY}">
                                    <a href="MainController?action=Category&Category=${Category.categoryID}" class="nav-item nav-link">${Category.name}</a>
                                </c:forEach>
                                <!--                            <div class="nav-item dropdown dropright">
                                                                <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Dresses <i class="fa fa-angle-right float-right mt-1"></i></a>
                                                                <div class="dropdown-menu position-absolute rounded-0 border-0 m-0">
                                                                    <a type="submit" name="action" value="men" class="dropdown-item">Men's Dresses</a>
                                                                    <a href="" class="dropdown-item">Women's Dresses</a>
                                                                    <a href="" class="dropdown-item">Baby's Dresses</a>
                                                                </div>
                                                            </div>-->
                            </div>
                        </nav>
                    </div>
                    <div class="col-lg-9">
                        <nav class="navbar navbar-expand-lg bg-dark navbar-dark py-3 py-lg-0 px-0">
                            <a href="" class="text-decoration-none d-block d-lg-none">
                                <span class="h1 text-uppercase text-dark bg-light px-2">Multi</span>
                                <span class="h1 text-uppercase text-light bg-primary px-2 ml-n1">Shop</span>
                            </a>
                            <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                                <span class="navbar-toggler-icon"></span>
                            </button>
                            <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                                <div class="navbar-nav mr-auto py-0">
                                    <a href="home.jsp" class="nav-item nav-link ">Home</a>
                                    <a href="shopping.jsp" class="nav-item nav-link">Shop</a>
                                    <a href="productDetail.jsp" class="nav-item nav-link">Shop Detail</a>
                                    <div class="nav-item dropdown">
                                        <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Pages <i class="fa fa-angle-down mt-1"></i></a>
                                        <div class="dropdown-menu bg-primary rounded-0 border-0 m-0">
                                            <a href="cart.jsp" class="dropdown-item">Shopping Cart</a>
                                            <a href="checkout.jsp" class="dropdown-item">Checkout</a>
                                        </div>
                                    </div>
                                    <a href="blog.jsp" class="nav-item nav-link">Blog</a>
                                    <a href="orderStatus.jsp" class="nav-item nav-link active">Order Status</a>
                                </div>
                                <div class="navbar-nav ml-auto py-0 d-none d-lg-block">                            
                                    <a href="cart.jsp" class="btn px-0 ml-3">
                                        <i class="fas fa-shopping-cart text-primary"></i>
                                        <span class="badge text-secondary border border-secondary rounded-circle" style="padding-bottom: 2px;">0</span>
                                    </a>
                                </div>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
        </form>
        <!-- Navbar End -->


        <section class="step-wizard">
            <ul class="step-wizard-list">
                <li class="step-wizard-item">
                    <span class="progress-count">1</span>
                    <span class="progress-label">Billing Info</span>
                </li>
                <li class="step-wizard-item ">
                    <span class="progress-count">2</span>
                    <span class="progress-label">Payment Method</span>
                </li>
                <li class="step-wizard-item current-item">
                    <span class="progress-count">3</span>
                    <span class="progress-label">Checkout</span>
                </li>
                <li class="step-wizard-item">
                    <span class="progress-count">4</span>
                    <span class="progress-label">Success</span>
                </li>
            </ul>
        </section>
       
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var progressBar = document.getElementById("progress-bar");
                var progressValue = progressBar.getAttribute("aria-valuenow");

                // Đặt chiều rộng ban đầu của thanh tiến trình là 0%
                progressBar.style.width = "0%";

                // Sau đó, thay đổi chiều rộng tới giá trị đã đặt (progressValue)
                setTimeout(function () {
                    progressBar.style.width = progressValue + "%";
                }, 100); // Delay 100ms để bắt đầu hiệu ứng chuyển động
            });
        </script>
    </body>
</html>