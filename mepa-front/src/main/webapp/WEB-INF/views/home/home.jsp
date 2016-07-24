<%@ page import="java.util.ArrayList" %>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<c:if test="${isCo == true}">
    <div class="col-md-12 text-center alert alert-success investFormInside">
        Bienvenue ${userCo.firstName} ${userCo.lastName}! :)
    </div>
</c:if>

<c:import url="/core/preinvest/projectListInclude"/>