<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="head" fragment="true" %>
<%@attribute name="title" fragment="true" %>

<html lang="en">
<head>
    <jsp:invoke fragment="head"/>
    <meta charset="utf-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><jsp:invoke fragment="title"/></title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
<head>
<body>


<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/home">Статистика</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="/soldiers">Солдаты</a></li>
                <li><a href="/naryadi">Наряды</a></li>
                <li><a href="/dezhurstva">Дежурства по комнатам</a></li>
                <li><a href="/pixiTest">Pixi.js test</a></li>
                <li><a href="/soldierGame">Игра</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</div>

<div class="container" style="padding-top: 60px">
    <jsp:doBody/>
</div>

</body>
</html>
