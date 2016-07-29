<%--
  Created by IntelliJ IDEA.
  User: Guillaume
  Date: 25/07/2016
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<div class="container">
    <h1>Liste des projets terminés</h1>

    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th style="width: 100px"></th>
                <th>Nom du projet</th>
                <th>Date de fin</th>
                <th>Description</th>
                <th>Status</th>
                <th>Objectif</th>
                <th>Montant</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${project_list}" var="project" varStatus="loop">
                <tr>
                    <td><img src="${project.imagesLinks.get(0)}" alt="Illustration" style="height: 80px;"></td>
                    <td><a href="<c:url value='/postinvest/project-end/${project.id}'/>" >${project.name}</a></td>
                    <td>${project.dateFormat("dd/MM/yyyy",project.endDate)}</td>
                    <td>${project.description}</td>
                    <c:if test="${project.goalAmount <= project.totalAmountFinal}">
                            <td  style="background-color:#98f098">Projet financé </td>
                    </c:if>
                    <c:if test="${project.goalAmount > project.totalAmountFinal}">
                        <td style="background-color:#FFCCCC">Projet non financé </td>
                    </c:if>

                    <td>${project.goalAmount} €</td>
                    <td>${project.totalAmountFinal} €</td>
                    <!--<td>
                        Rewards:
                        <c:forEach items="${project.rewards}" var="reward" varStatus="loop">
                            - ${reward.name} :${reward.description} <br />
                        </c:forEach>
                    </td>-->
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>