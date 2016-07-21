<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<div class="container">
        <c:url var="preinvestCoreUrl" value="/core/preinvest/projectCreate"/>
        <a class="btn btn-lg btn-primary" href="${preinvestCoreUrl}" role="button">Create a new project</a>

    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th></th>
                <th>Project Name</th>
                <th>Ending date</th>
                <th>Project Description</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${project_list}" var="project" varStatus="loop">
                <tr>
                    <td></td>
                    <td><a href="projectDisplay.jsp">${project.name}</a></td>
                    <td><fmt:formatDate value="${project.endDate}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                    <td>${project.description}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
