<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<div class="container">
    <h2>Displaying a project</h2>
    <div class="alert alert-warning">
        Hello World ! :D

    </div>
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
                    <td>${project.name}</td>
                    <td><fmt:formatDate value="${project.endDate}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                    <td>${project.description}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
