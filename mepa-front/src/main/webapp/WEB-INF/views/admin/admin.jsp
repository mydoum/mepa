<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<div class="container">
    <h2>Admin</h2>

    <div>
        Le nombre de clicks total : <%= request.getAttribute("clicks") %>
    </div>
    <div>
        La quantité donnée total : <%= request.getAttribute("totalAmount") %>
    </div>

    <div class="container">
        <c:url var="addAmountUrl" value="/admin/addAmount"/>
        <form:form role="form" controller="AdminController" method="post"
                   action="${addAmountUrl}">
            <!--
            <input id="amount" type="text" maxlength="20" placeholder="20" name="amount"/>
            -->
            <label for="amount">Quantité d'argent que vous voudriez donner</label>
            <input id="amount" type="text" placeholder="20" name="amount"/>
            <button type="submit" class="btn btn-default">Soumettre</button>
        </form:form>
    </div>
</div>
