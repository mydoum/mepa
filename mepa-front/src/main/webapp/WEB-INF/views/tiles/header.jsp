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
                    <a class="navbar-brand" href="/home">MEPA</a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <c:url var="homeUrl" value="/home"/>
                        <li><a href="${homeUrl}">Accueil</a></li>
                        <c:url var="signup" value="/authentification/signup"/>
                        <li><a href="${preinvestCoreUrl}">Créer un projet</a></li>
                        <c:url var="investUrl" value="/invest"/>
                        <li><a href="${investUrl}">Investissements</a></li>
                        <c:if test="${!isCo}">
                            <c:url var="signin" value="/authentification/signin"/>
                            <div class="nav navbar-nav navbar-right">
                                <li><a href="${signin}">Se connecter</a></li>
                            <div class="nav navbar-nav navbar-right">
                        </c:if>
                        <c:if test="${isCo == true}">
                            <div class="nav navbar-nav navbar-right">
                                <c:url var="editUser" value="/authentification/editUser"/>
                            </div>
                            <c:choose>
                                <c:when test='${not empty userCo.firstName}'>
                                    <div class="nav navbar-nav navbar-right">
                                        <li><a href="${editUser}">${userCo.firstName} ${userCo.lastName}</a></li>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="nav navbar-nav navbar-right">
                                        <li><a href="${editUser}">${userCo.login}</a></li>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                            <c:url var="deconnexion" value="/authentification/deconnexion"/>
                            <div class="nav navbar-nav navbar-right">
                                <li><a href="${deconnexion}">Déconnexion</a></li>
                            </div>
                        </c:if>
                        <c:if test="${isCo == false}">
                            <div class="nav navbar-nav navbar-right">
                                <li><a href="${signup}">S'inscrire</a></li>
                            </div>
                            <c:url var="preinvestCoreUrl" value="/core/preinvest/projectCreate"/>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</header>
