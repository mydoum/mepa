<%--
  Created by IntelliJ IDEA.
  User: patrickear
  Date: 22/7/2016
  Time: 4:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<%--<nav class="navbar navbar-default">--%>
    <%--<div class="container-fluid">--%>

        <%--<div class="navbar-header">--%>
            <%--<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">--%>
                <%--<span class="sr-only">Toggle navigation</span>--%>
                <%--<span class="icon-bar"></span>--%>
                <%--<span class="icon-bar"></span>--%>
                <%--<span class="icon-bar"></span>--%>
            <%--</button>--%>
            <%--<a class="navbar-brand" href="#">Brand</a>--%>
        <%--</div>--%>


        <%--<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">--%>
            <%--<ul class="nav navbar-nav">--%>
                <%--<li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>--%>
                <%--<li><a href="#">Link</a></li>--%>
                <%--<li class="dropdown">--%>
                    <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span class="caret"></span></a>--%>
                    <%--<ul class="dropdown-menu" role="menu">--%>
                        <%--<li><a href="#">Action</a></li>--%>
                        <%--<li><a href="#">Another action</a></li>--%>
                        <%--<li><a href="#">Something else here</a></li>--%>
                        <%--<li class="divider"></li>--%>
                        <%--<li><a href="#">Separated link</a></li>--%>
                        <%--<li class="divider"></li>--%>
                        <%--<li><a href="#">One more separated link</a></li>--%>
                    <%--</ul>--%>
                <%--</li>--%>
            <%--</ul>--%>


        <%--</div>--%>
    <%--</div>--%>
<%--</nav>--%>

<div class="container">
    <div class="row">
        <div class="col-md-6">
            <c:url var="addCustomUserFormActionUrl" value="/authentification/addUser"/>
            <form id="eventForm" class="form-horizontal" action="${addCustomUserFormActionUrl}" modelAttribute="addCustomUserFormBean"
                  method="POST">
                <fieldset>
                    <div id="legend">
                        <legend class="">Connexion</legend>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="email">Adresse e-mail</label>
                        <div class="controls">
                            <input id="email" name="email" placeholder="" class="form-control input-lg" type="email">
                            <p class="help-block">Please provide your e-mail</p>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="password">Mot de passe</label>
                        <div class="controls">
                            <input id="password" name="password" placeholder="" class="form-control input-lg" type="password">
                            <p class="help-block">Password should be at least 6 characters</p>
                        </div>
                    </div>

                    <div class="control-group">
                        <!-- Button -->
                        <div class="controls">
                            <button type="submit" class="btn btn-default">Connexion</button>
                        </div>
                    </div>

                </fieldset>

            </form>
        </div>
    </div>
</div>