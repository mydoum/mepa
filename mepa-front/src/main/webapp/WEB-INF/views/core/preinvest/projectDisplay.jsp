<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Valentin ZHENG
  Date: 24/07/2016
  Time: 00:31
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<%
    Integer hitsCount = (Integer) application.getAttribute("hitCounter");
    ArrayList<String> visits = (ArrayList<String>) application.getAttribute("visits");
    if (visits == null)
        visits = new ArrayList<>();
    String id = request.getSession().getId();
    if (hitsCount == null || hitsCount == 0) {
        hitsCount = 1;
        visits.add(id);
    } else {
        if (!visits.contains(id)) {
            visits.add(id);
            ++hitsCount;
        }
    }
    application.setAttribute("hitCounter", hitsCount);
    System.out.println(visits.size());
    application.setAttribute("visits", visits);
%>

<div class="container">
    <header class="title projectHeader">
        <h1 class="short">${project.name}</h1>
        <jsp:useBean id="now" class="java.util.Date"/>
        <c:if test="${project.goalAmount <= totalDonation && project.endDate gt now}">
            <h1 class="short">88888888888888888</h1>
        </c:if>
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
                            <a href="/core/preinvest/projectDisplay/${project.id}">${project.name}</a>
                        </li>
                        <li>
                            <a href="/core/preinvest/projectDisplay/${project.id}/comment">Commentaires</a>
                        </li>
                    </ul>
                </nav>
                <%-- Part of the page where the slideshow and the project date are printed --%>
                <div class="well bs-component">
                <div class="row">
                    <div class="col-md-4" id="slideshow">
                        <c:forEach items="${project.imagesLinks}" var="image" varStatus="loop">
                            <div>
                                <img src="${image}" style="width:100%"
                                     class="projectImageInvest img-responsive img-rounded"/>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="col-md-8">
                        <div class="date-display firstDate">Date de début:</div>
                        <div class="date-display">${project.dateFormat("dd/MM/yyyy", project.startDate)}</div>
                        <div class="date-display">Date de fin:</div>
                        <div class="date-display">${project.dateFormat("dd/MM/yyyy", project.endDate)}</div>
                        <div class="date-display">Nombre de contributeurs : ${nbrContributos}</div>
                    </div>
                </div>
                </div>
                <%-- Part of the page for Social buttons --%>
                <div class="row">
                    <div class="col-md-6">
                        <div id="social-box" class="row">
                            <div class="col-md-12">
                                <%-- Facebook share button --%>
                                <div class="fb-share-button"
                                     data-href="https://mepa.herokuapp.com/core/preinvest/projectDisplay/${project.id}"
                                     data-layout="button_count"
                                     data-size="large"
                                <%-- Open the iframe --%>
                                     data-mobile-iframe="true">
                                    <a class="fb-xfbml-parse-ignore" target="_blank"
                                       href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fmepa.herokuapp.com%2Fcore%2Fpreinvest%2F${project.id}&amp;src=sdkpreparse">
                                        Partager
                                    </a>
                                </div>
                                <br/>
                            </div>
                            <div class="col-md-12">
                                <a href="https://twitter.com/share" class="twitter-share-button" data-size="large"
                                   data-text="Découvrez le projet ${project.name} :" data-hashtags="LGIS"
                                   data-lang="fr" data-show-count="false">Tweet</a>
                                <script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <c:if test="${isAdmin == true}">
                            <p>Nombre de visiteurs: <%= hitsCount%>
                            </p>
                        </c:if>
                    </div>
                </div>
                <%-- Part of the page for the project description --%>
                <div class="panel panel-primary projectDescriptionInvest col-md-12">
                    <!-- Default panel contents -->
                    <div class="panel-heading">Description</div>
                    <div class="panel-body">
                        <p>${project.description}</p>
                    </div>
                </div>
            </div>
            <br/>

            <%-- Part of the page where the list of the investors are printed --%>
            <div class="col-md-12 investFormInside">
                <tr/>
                <div class="col-md-12 investFormInside">
                    <div class="col-md-12"><h2>Liste des investisseurs</h2></div>
                </div>
                <table class="col-md-12 table table-striped">
                    <thead>
                    <tr>
                        <th>Investisseur</th>
                        <th>Contribution</th>
                    </tr>
                    </thead>
                    <tbody id="infiniteInvestorsList">
                    <c:if test="${investorsList.size() > 0}">
                        <c:forEach items="${investorsList}" var="investor" varStatus="status">
                            <tr>
                                <c:choose>
                                    <c:when test="${investor.anonymous}">
                                        <td>Anonyme</td>
                                    </c:when>
                                    <c:when test="${investor.firstname == null or empty investor.firstname}">
                                        <td>${investor.email}</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>${investor.firstname}</td>
                                    </c:otherwise>
                                </c:choose>
                                <td>${investor.moneyAmount} €</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
            <br/>
            <c:if test="${isAdmin != null && isAdmin == true && amountSize != null && amountSize == true}">
                <div class="col-md-12 download investFormInside">
                    <p align="center">
                        <a href="/invest/download/${project.id}"><span class="btn btn-primary">Download</span></a>
                    </p>
                </div>
            </c:if>
            <c:if test="${isAdmin != null && isAdmin == true && amountSize != null && amountSize == false}">
                <div class="col-md-12 download investFormInside disabled">
                    <p align="center">
                        <a href="/invest/download/${project.id}"><span class="btn btn-primary">Download</span></a>
                    </p>
                </div>
            </c:if>
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
                                <div class="progress-bar progress-bar-success" role="progressbar"
                                     aria-valuenow="${projectPercentage}" aria-valuemin="0" aria-valuemax="100"
                                     style="width:${projectPercentageBar}%">
                                    ${projectPercentage}%
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <h4>Contribution totale : ${totalDonation}€</h4>
                    </div>
                    <div class="col-md-12">
                        <h4>Objectif : ${project.goalAmount}€</h4>
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
                        <c:forEach items="${project.rewards}" begin="0" end="25" var="reward" varStatus="status">
                            <li class="rewardItem" name="reward/${reward.id}">
                                <h4 class="rewardTitle"><a
                                        href="/invest/${project.id}/rewardDisplay/${reward.id}"> ${reward.name} à partir
                                    de ${reward.costStart}€</a></h4>
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

<script>
    var startStep = 0;
    startStep = Number(startStep).toFixed();
    var stepSlider = ${project.goalAmount} * 0.1;
    stepSlider = Number(stepSlider).toFixed();
    var maxSlider = ${project.goalAmount};
    var infiniteListSize = ${investorsList.size()};

    var infiniteScrollerElements = [
        <c:forEach items="${investorsList}" begin="10" end="${investorsList.size()}" var="investor" varStatus="status">
            [
                <c:choose>
                    <c:when test="${investor.anonymous}">
                        "Anonyme",
                    </c:when>
                    <c:when test="${investor.firstname == null or empty investor.firstname}">
                        "${investor.email}",
                    </c:when>
                    <c:otherwise>
                        "${investor.firstname}",
                    </c:otherwise>
                    </c:choose>
                        "${investor.moneyAmount}€"
            ]<c:if test="${not status.last}">,</c:if>
        </c:forEach>
    ];

    console.log("InvestorsList Size :" + infiniteListSize + "\n");
    console.log("InvestorsList Content :" + infiniteScrollerElements + "\n");

</script>

<c:url var="investSliderJs" value="/js/investment/nouislider.min.js"/>
<script src="${investSliderJs}"></script>
<c:url var="investSliderPersoJs" value="/js/investment/slider.js"/>
<script src="${investSliderPersoJs}"></script>


