<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<div class="container">
        <c:url var="preinvestCoreUrl" value="/core/preinvest/projectCreate"/>
        <a class="btn btn-lg btn-primary" href="${preinvestCoreUrl}" role="button">Create a new project</a>

    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th style="width: 100px"></th>
                <th>Project Name</th>
                <th>Ending date</th>
                <th>Project Description</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${project_list}" var="project" varStatus="loop">
                <tr>
                    <td><img src="${project.imagesLinks.get(0)}" alt="Illustration" style="height: 80px;"></td>
                    <td><a href="<c:url value='projectDisplay/${project.id}'/>" >${project.name}</a></td>
                    <td>${project.dateFormat("dd/MM/yyyy",project.endDate)}</td>
                    <td>${project.description}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
