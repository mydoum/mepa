<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: claebo_c
  Date: 26/07/16
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<style type="text/css">
    <%@include file="../../../../resources/css/preinvestment/projectCreate.css"%>
</style>

<div class="container">
    <div class="container" id="step2">
        <div class="wizard">
            <div class="wizard-inner">
                <div class="connecting-line"></div>
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="disabled">
                        <a href="#step1" data-toggle="tab" aria-controls="step1" role="tab" title="Step 1">
                            <span class="round-tab">
                                <i class="glyphicon glyphicon-pencil" style="padding-top: 30%"></i>
                            </span>
                        </a>
                    </li>

                    <li role="presentation" class="active">
                        <a href="#step2" data-toggle="tab" aria-controls="step2" role="tab" title="Step 2">
                            <span class="round-tab">
                                <i class="glyphicon glyphicon-ok" style="padding-top: 30%"></i>
                            </span>
                        </a>
                    </li>
                </ul>
            </div>
            <header class="title projectHeader">
                <h1 class="short">${project.name}</h1>
            </header>
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
                    <div class="date-display firstDate">Date de
                        début: ${project.dateFormat("dd/MM/yyyy", project.startDate)}</div>
                    <div class="date-display">Date de fin: ${project.dateFormat("dd/MM/yyyy", project.endDate)}</div>
                    <div class="date-display">Objectif : ${project.goalAmount} ${project.getCurrencyString()}</div>
                </div>
            </div>
            <p align="center">
                <c:url var="addRewardUrl" value="/core/preinvest/rewardAdd/add/${newProject}"/>
                <a href="${addRewardUrl}">
                    <button type="button" class="btn btn-primary">Ajouter une contrepartie</button>
                </a>
                <c:url var="listProjectUrl" value="/core/preinvest/rewardAdd/projectList"/>
                <a href="${listProjectUrl}">
                    <button type="button" class="btn btn-success">Valider le projet</button>
                </a>
            </p>
            <div class="row">
                <div class="col-md-12 rewardSection">
                    <h4 class="rewardHeader">Les contreparties</h4>
                    <ol>
                        <c:if test="${project.rewards != null and project.rewards.size() > 0}">
                            <c:forEach items="${project.rewards}" var="reward" varStatus="status">
                                <li class="rewardItem" name="reward/${reward.id}">
                                    <h4 class="rewardTitle"> ${reward.name} à partir
                                        de ${reward.costStart} ${project.getCurrencyString()}</a></h4>
                                    <div class="rewardDescription">
                                        <p>${reward.description}</p>
                                    </div>
                                </li>
                            </c:forEach>
                        </c:if>
                    </ol>
                </div>
            </div>
        </div>
    </div>
</div>


