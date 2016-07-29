<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<div class="container">
    <h1>Liste des projets en cours</h1>

    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th style="width: 100px"></th>
                <th>Nom du projet</th>
                <th>Date de fin</th>
                <th>Description</th>
                <th>Popularité</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${userco != null}">
            <button type="submit" id="sort" data-loading-text="Trier" class="btn btn-success">Trier par popularité</button><center/>
                <c:forEach items="${sortedlist}" var="sortlist" varStatus="loop">
                    <c:if test="${sortlist.projectId == project.id}">

                            <td>${sortlist.likes_}</td>
                            <td><fmt:formatDate value="${c_model.created}" pattern="HH:mm:ss"/></td>
                            <td>${c_model.data}</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </c:if>
            <c:forEach items="${project_list}" var="project" varStatus="loop">
                <tr>
                    <td><img src="${project.imagesLinks.get(0)}" alt="Illustration" style="height: 80px;"></td>
                    <td><a href="<c:url value='/core/preinvest/projectDisplay/${project.id}'/>" >${project.name} </a></td>
                    <td>${project.dateFormat("dd/MM/yyyy",project.endDate)}</td>
                    <td>${project.description}</td>
                <td>${(project.getlikeProject())}</td>

            </tr>

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
