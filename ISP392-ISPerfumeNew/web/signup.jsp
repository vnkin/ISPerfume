<%-- 
  Document   : signup
  Created on : Sep 28, 2024, 4:02:06 PM
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

    </head>
    <body>
        <!-- Topbar Start -->
        <div class="container-fluid">
            <div class="row align-items-center bg-light py-3 px-xl-5 d-none d-lg-flex">
                <div class="col-lg-4">
                    <a href="MainController?action=HomeController" class="text-decoration-none">
                        <span class="h1 text-uppercase text-primary bg-dark px-2">IS</span>
                        <span class="h1 text-uppercase text-dark bg-primary px-2 ml-n1">Perfume</span>
                    </a>
                </div>
            </div>
        </div>
        <!--Sigin in form Start-->
        <div class="container-fluid">
            <h2 class="section-title position-relative text-uppercase mx-xl-5 mb-4"><span class="bg-secondary pr-3">Sign
                    Up</span></h2>
            <div class="row px-xl-5">

                <div class="col-lg-12 mb-5">
                    <div class="row h-100 align-items-center justify-content-center">
                        <div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4">
                            <div class="bg-light rounded p-4 p-sm-5 my-4 mx-3" style="padding-bottom: 15px !important; width: 455px;">
                                <div class="d-flex align-items-center justify-content-between mb-3">
                                    <a href="index.html" class="">
                                        <h3 class="text-primary"><i class="fa fa-hashtag me-2"></i>DASHMIN</h3>
                                    </a>
                                    <h3>Sign Up</h3>
                                </div>
                                <form action="MainController" method="get">
                                    <div class="form-floating mb-3">
                                        <label for="floatingInput">Username*</label>
                                        <input type="text" class="form-control" id="floatingInput"
                                               placeholder="${requestScope.INSERT_USER_CUST_ERROR.nameError}" name="userName" required="" >
                                    </div>

                                    <div class="form-floating mb-3">
                                        <label for="floatingInput">Email*</label>
                                        <input type="email" class="form-control" id="floatingInput"
                                               placeholder="${requestScope.INSERT_USER_CUST_ERROR.emailError}" name="email" required="">
                                    </div>

                                    <div class="form-floating mb-3">
                                        <label for="floatingInput">Phone number*</label>
                                        <input type="text" class="form-control" id="floatingInput"
                                               placeholder="${requestScope.INSERT_USER_CUST_ERROR.phoneError}" name="phoneNum" required="">
                                    </div>

                                    <div class="form-floating mb-4">
                                        <label for="floatingPassword">Password*</label>
                                        <input type="password" class="form-control" id="floatingPassword"
                                               name="password" required="">
                                    </div>

                                    <div class="form-floating mb-4">
                                        <label for="floatingPassword">Confirm password*</label>
                                        <input type="password" class="form-control" id="floatingPassword"
                                               placeholder="${requestScope.INSERT_USER_CUST_ERROR.confirmPasswordError}" name="confirmPassword" required="">
                                    </div>

                                    <button type="submit" class="btn btn-primary py-3 w-100 mb-4" name="action" value="registerNewUser">Register</button>
                                    <p class="text-center mb-0">Do you already have an account ? <a href="signin.jsp">Sign In</a></p>
                                    <div class="d-flex align-items-center justify-content-between mb-4" style="margin: 28px 0 0 32px;">

                                    </div>
                                </form>
                                <!--                                <p class="text-center mb-0">Do you already have an account ? <a href="signin.jsp">Sign In</a></p>-->
                                <div class="d-flex align-items-center justify-content-between mb-4" style="margin: 28px 0 0 32px;">
                                    <button class="button-signin text-center" style="width: 310px; padding-left: 68px; ">
                                        <svg xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid" viewBox="0 0 256 262" class="svg">
                                        <path fill="#4285F4" d="M255.878 133.451c0-10.734-.871-18.567-2.756-26.69H130.55v48.448h71.947c-1.45 12.04-9.283 30.172-26.69 42.356l-.244 1.622 38.755 30.023 2.685.268c24.659-22.774 38.875-56.282 38.875-96.027" class="blue"></path>
                                        <path fill="#34A853" d="M130.55 261.1c35.248 0 64.839-11.605 86.453-31.622l-41.196-31.913c-11.024 7.688-25.82 13.055-45.257 13.055-34.523 0-63.824-22.773-74.269-54.25l-1.531.13-40.298 31.187-.527 1.465C35.393 231.798 79.49 261.1 130.55 261.1" class="green"></path>
                                        <path fill="#FBBC05" d="M56.281 156.37c-2.756-8.123-4.351-16.827-4.351-25.82 0-8.994 1.595-17.697 4.206-25.82l-.073-1.73L15.26 71.312l-1.335.635C5.077 89.644 0 109.517 0 130.55s5.077 40.905 13.925 58.602l42.356-32.782" class="yellow"></path>
                                        <path fill="#EB4335" d="M130.55 50.479c24.514 0 41.05 10.589 50.479 19.438l36.844-35.974C195.245 12.91 165.798 0 130.55 0 79.49 0 35.393 29.301 13.925 71.947l42.211 32.783c10.59-31.477 39.891-54.251 74.414-54.251" class="red"></path>
                                        </svg>
                                        <span class="text">Sign up with Google</span>
                                    </button>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!--Sigin in form End-->
        <!-- Footer Start -->
        <div class="container-fluid bg-dark text-secondary mt-5 pt-5">
            <div class="row px-xl-5 pt-5">
                <div class="col-lg-4 col-md-12 mb-5 pr-3 pr-xl-5">
                    <h5 class="text-secondary text-uppercase mb-4">Get In Touch</h5>
                    <p class="mb-2"><i class="fa fa-map-marker-alt text-primary mr-3"></i>Lô E2a-7, Đường D1, Đ. D1</p>
                    <p class="mb-2"><i class="fa fa-envelope text-primary mr-3"></i>isperfume1803@gmail.com</p>
                    <p class="mb-0"><i class="fa fa-phone-alt text-primary mr-3"></i>0384767323</p>
                </div>
            </div>
            <div class="row border-top mx-xl-5 py-4" style="border-color: rgba(256, 256, 256, .1) !important;">
                <div class="col-md-6 px-xl-0">
                    <p class="mb-md-0 text-center text-md-left text-secondary">
                        <a class="text-primary" href="">ISPerfume</a>
                    </p>
                </div>
                <div class="col-md-6 px-xl-0 text-center text-md-right">
                    <img class="img-fluid" src="img/payments.png" alt="">
                </div>
            </div>           
        </div>
        <!-- Footer End -->

        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>

        <!-- Contact Javascript File -->
        <script src="mail/jqBootstrapValidation.min.js"></script>
        <script src="mail/contact.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>
    </body>
</html>
