<%@ page import="java.util.ArrayList" %>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<c:if test="${oneTime == true}">
    <div class="col-md-12 text-center alert alert-success investFormInside">
        Bienvenue ${userCo.firstName} ${userCo.lastName} :)
        <% session.setAttribute("oneTime", false); %>
    </div>
</c:if>
<%@ include file="/WEB-INF/views/home/slider.jsp" %>
<c:import url="/core/preinvest/projectListInclude"/>