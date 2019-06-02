<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="/style/homepage.css" />" rel="stylesheet">
    <title>Title</title>
</head>
<body>

<div id="hold">
    <div id="menu">
        piekne menu
    </div>
    <div class="innerHold1">
        <div class="inner col1">
            <c:forEach items="${championList}" var="entry">
             <a href="/championComp/${entry.value}"><img alt="image"  src="<c:url value="championImage/${entry.value}.png"/>" width="45" height="45" ></a>
            </c:forEach>
        </div>
    </div>
    <div class="innerHold2"><div class="inner col2">

       ${champ1}

    </div></div>
    <div class="innerHold3"><div class="inner col2">
        Col 3
    </div></div>
    <div class="clear"></div>
</div>


</body>
</html>
