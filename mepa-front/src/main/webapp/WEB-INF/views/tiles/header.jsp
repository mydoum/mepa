<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<header>
    <div class="container">
        <div class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <c:url var="homeUrl" value="/home"/>
                    <a class="navbar-brand" href="${homeUrl}"> LGIS</a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <c:url var="preinvestCoreUrl" value="/core/preinvest/projectCreate"/>
                        <li><a href="${preinvestCoreUrl}">Créer un projet</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <c:if test="${!isCo}">
                            <c:url var="signin" value="/authentification/signin"/>
                            <%-- <c:url var="signup" value="/authentification/signup"/>--%>
                            <li><a href="${signin}"><span class="glyphicon glyphicon-log-in"></span> Se connecter</a>
                            </li>
                            <%--<li><a href="${signup}"><span class="glyphicon glyphicon-user"></span>  S'inscrire</a></li>--%>
                        </c:if>
                        <c:if test="${isCo == true}">
                            <c:if test="${userCo.isAdmin}">
                                <c:url var="checkUsers" value="/authentification/checkUsers"/>
                                <li><a href="${checkUsers}"><span
                                        class="glyphicon glyphicon-th-list"></span> Liste des utilisateurs</a></li>
                            </c:if>
                            <c:url var="editUser" value="/authentification/editUser"/>
                            <c:choose>
                                <c:when test='${not empty userCo.firstName}'>
                                    <li><a href="${editUser}"><span
                                            class="glyphicon glyphicon-user"></span> ${userCo.firstName} ${userCo.lastName}
                                    </a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="${editUser}"><span
                                            class="glyphicon glyphicon-user"></span> ${userCo.login}</a></li>
                                </c:otherwise>
                            </c:choose>
                            <c:url var="deconnexion" value="/authentification/deconnexion"/>
                            <li><a href="${deconnexion}"><span class="glyphicon glyphicon-off"></span> Déconnexion</a>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</header>
