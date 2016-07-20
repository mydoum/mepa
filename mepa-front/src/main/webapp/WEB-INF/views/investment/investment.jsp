<%--
  Created by IntelliJ IDEA.
  User: Valentin ZHENG
  Date: 19/07/2016
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<div class="container investButton">
    <div class="investSlider">
        <c:if test="${montant != null}">
            <div class="text-center alert alert-success">${montant}</div>
        </c:if>
        <c:url var="investMoney" value="/invest/investMoney"/>
        <form:form role="form" action="${investMoney}" method="post" modelAttribute="investAmount">
            <div id="keypress" class="form-group noUi-target noUi-ltr noUi-horizontal noUi-background"></div>
            <form:label path="moneyAmount">
                <spring:message text="Montant :" />
            </form:label>
            <form:input path="moneyAmount" id="input-with-keypress"
                        class="form-control" type="text" required="required" value="1"/>
            <p align="center"><input type="submit" value="Investir"></p>
        </form:form>
    </div>
</div>

