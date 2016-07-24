<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<div class="container">
    <h1>Liste des projets</h1>

    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th style="width: 100px"></th>
                <th>Nom du projet</th>
                <th>Date de fin</th>
                <th>Description</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${project_list}" var="project" varStatus="loop">
                <tr>
                    <td><img src="${project.imagesLinks.get(0)}" alt="Illustration" style="height: 80px;"></td>
                    <td><a href="<c:url value='/core/preinvest/projectDisplay/${project.id}'/>" >${project.name}</a></td>
                    <td>${project.dateFormat("dd/MM/yyyy",project.endDate)}</td>
                    <td>${project.description}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>