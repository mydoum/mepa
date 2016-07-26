<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Valentin ZHENG
  Date: 24/07/2016
  Time: 00:31
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<script>
    $("#slideshow > div:gt(0)").hide();

    setInterval(function () {
        $('#slideshow > div:first')
                .fadeOut(1000)
                .next()
                .fadeIn(1000)
                .end()
                .appendTo('#slideshow');
    }, 3000);
</script>

<div class="container">
    <header class="title projectHeader">
        <h1 class="short">${project.name}</h1>
    </header>
    <c:if test="${amount != null}">
        <div class="col-md-12 text-center alert alert-success investFormInside">
            Merci pour votre don de ${amount}€! Un mail de notification vous a été envoyé.
        </div>
    </c:if>
    <c:if test="${errorInvest != null and !empty errorInvest}">
        <div class="col-md-12 text-center alert alert-danger investFormInside">
                ${errorInvest}
        </div>
    </c:if>
    <div class="col-md-8 investFormInside">
        <div class="row">
            <div class="col-md-12">
                <nav class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li class="active">
                            <a href="/core/preinvest/projectDisplay/${project.id}">Accueil de la page du projet
                                : ${project.name}</a>
                        </li>
                        <li>
                            <a href="/core/preinvest/projectDisplay/${project.id}/comment">Commentaires</a>
                        </li>
                    </ul>
                </nav>
            </div>
            <br/>
            <%-- SOCIAL DIV FOR ADDING COMMENT PART --%>
            <div class="col-md-12">
                <div class="col-md-12">
                    <!-- www.webtutoriaux.com Compteur de visiteurs -->
                    <script type='text/javascript' src='http://www.webtutoriaux.com/services/compteur-visiteurs/index.php?client=154864'></script>
                    <!-- End Compteur de visiteurs -->
                </div>
                <div class="col-md-12">
                    <c:choose>
                        <c:when test="${userCo == null}">
                            <div class="row">
                                <div class="col-md-2"></div>
                                <div class="col-md-10">
                                    <p align="center" class="glyphicon glyphicon-info-sign">
                                        Vous devez être identifié pour laisser un commentaire
                                    </p>
                                </div>
                                <div class="col-md-2"></div>
                            </div>
                            <div class="row">
                                <div class="col-md-4"></div>
                                <div class="col-md-4">
                                    <p align="center">
                                        <a class="btn btn-success loginButton" href="/authentification/signin" role="button">Connexion</a>
                                    </p>
                                </div>
                                <div class="col-md-4"></div>
                            </div>
                            <div class="row">
                                <div class="col-md-4"></div>
                                <div class="col-md-4">
                                    <p align="center">
                                        <a href="/authentification/signup">S'inscrire</a>
                                    </p>

                                </div>
                                <div class="col-md-4"></div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <%@ include file="/WEB-INF/views/comments/core/comment_form.jsp" %>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>

    <%-- Right side bar for investing for the project and the list of counterparts --%>
    <aside class="col-md-4">
        <div class="row">
            <%-- Part of the page where the box of the invest button part --%>
            <div class="col-md-12 investForm">
                <div class="col-md-12 InvestFormInside">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="progress">
                                <%--<div class="progress-bar progress-bar-success" role="progressbar"
                                aria-valuenow="${projectPercentage}" aria-valuemin="0" aria-valuemax="100"
                                style="width:${projectPercentage}%">
                                    ${projectPercentage}%
                                </div>--%>
                                <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="80"
                                     aria-valuemin="0" aria-valuemax="100" style="width:80%">
                                    80%
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <h4>Contribution totale : ${totalDonation}€</h4>
                    </div>
                    <div class="col-md-12">
                        <h4>Objectif : <%--${project.requestAmount}--%>500€</h4>
                    </div>
                    <div class="col-md-12">
                        <%-- POST INVEST --%>
                        <%-- PARTI POUR LE POST INVEST --%>
                        <%--<jsp:useBean id="todayDate" class="java.util.Date"/>
                        <c:choose>
                            <c:when test="${project.endDate <= todayDate}">
                               <div class="date">PostInvest -> Date de fin atteinte.</div>
                           </c:when>
                           <c:otherwise>
                               <div class="date">PostInvest -> Date de fin non atteinte.</div>
                           </c:otherwise>
                       </c:choose>--%>
                        <h4>Temps restant : ${projectLeftTime} jour(s)</h4>
                    </div>
                </div>
                <c:url var="investMoney" value="/invest/${project.id}/investMoney"/>
                <form:form role="form" action="${investMoney}" method="post" modelAttribute="User">
                    <div id="keypress"
                         class="InvestFormInside noUi-target noUi-ltr noUi-horizontal noUi-background col-md-12"></div>
                    <label class="investFormInside col-md-12">Montant (€):</label>
                    <div class="col-md-12 InvestFormInside">
                        <input name="investAmount" id="input-with-keypress"
                               class="form-control" type="text" required="required" readonly/>
                    </div>
                    <br/>
                    <div class="row">
                        <div class="col-md-4">
                            <label class="investFormInside col-md-12">Anonyme:</label>
                        </div>
                        <div class="col-md-4"></div>
                        <div class="col-md-1 checkbox">
                            <label><input type="checkbox" name="anonymous_id" value=""/></label>
                        </div>
                        <div class="col-md-7"></div>
                    </div>

                    <div class="row">
                        <div class="col-md-4"></div>
                        <div class="col-md-4">
                            <p align="center"><input type="submit" class="btn btn-primary InvestFormInside"
                                                     value="Contribuer">
                            </p>
                        </div>
                        <div class="col-md-4"></div>
                    </div>
                </form:form>
            </div>
        </div>
        <%-- Part of the page where the slideshow and the project date are printed --%>
        <div class="row">
            <div class="col-md-12 rewardSection">
                <h4 class="rewardHeader">Choisissez votre contrepartie</h4>
                <ol>
                    <c:if test="${project.rewards != null and project.rewards.size() > 0}">
                        <c:forEach items="${project.rewards}" var="reward" varStatus="status">
                            <li class="rewardItem" name="reward/${reward.id}">
                                <h4 class="rewardTitle"> <a href="/invest/${project.id}/rewardDisplay/${reward.id}"> ${reward.name} à partir de ${reward.costStart}€</a></h4>
                                <div class="rewardDescription">
                                    <p>${reward.description}</p>
                                </div>
                            </li>
                        </c:forEach>
                    </c:if>
                </ol>
            </div>
        </div>
    </aside>
</div>

<c:url var="investSliderJs" value="/js/investment/nouislider.min.js"/>
<script src="${investSliderJs}"></script>
<c:url var="investSliderPersoJs" value="/js/investment/slider.js"/>
<script src="${investSliderPersoJs}"></script>


