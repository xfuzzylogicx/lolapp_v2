<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="js/test.js" type="text/javascript"></script>
    <link href="<c:url value="/style/homepage.css" />" rel="stylesheet">
    <title>Title</title>
</head>
<body style="background-color:black";>

<div id="hold">
    <div id="menu" style="background-color:#080808" ;>

        <!--Navbar -->
        <nav class="mb-1 navbar navbar-expand-lg navbar-dark info-color">
            <a class="navbar-brand" href="#">Mobster</a>
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Spikes</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Free Rotation</a>
                </li>
            </ul>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent-4"
                    aria-controls="navbarSupportedContent-4" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent-4">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">
                            <i class="fab fa-facebook-f"></i> Facebook
                            <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <i class="fab fa-instagram"></i> Instagram</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink-4" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-user"></i> Profile </a>
                        <div class="dropdown-menu dropdown-menu-right dropdown-info" aria-labelledby="navbarDropdownMenuLink-4">
                            <a class="dropdown-item" href="#">My account</a>
                            <a class="dropdown-item" href="#">Log out</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
    <div id="menubar" style="height:20px; width:100%; background-color:#ec5659; clear:both;" >

    </div>
    <div class="innerHold1">

        <div class="inner col1">
            <c:forEach items="${championList}" var="entry">
             <a href="/championComp?champion=${entry.value}"><img alt="image"  src="<c:url value="championImage/${entry.value}.png"/>" width="45" height="45" ></a>
            </c:forEach>
        </div>
    </div>
    <div class="innerHold2">
        <div class="wrapper">
            <div class="row">
                <img id="leftSplash" alt="image"  src="<c:url value="currentPatch/${champ1splash}"/>" >
            </div>

            <div class="row">
                ${champion1.stats.attackDamage}
                ${champion1.stats.armor}
                ${champion1.stats.hp}
            </div>
        </div>

    </div>
    <div class="innerHold3">
        <div class="inner col2">

         <img id="rigthSplash" style="float: right" alt="image" src="<c:url value="currentPatch/${champ2splash}"/>" >

        </div>
    </div>
    <div class="clear">
        
    </div>
</div>


</body>

</html>
