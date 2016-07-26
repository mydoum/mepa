<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<div class="container">
    <h2>Admin</h2>

    <div>
        Le nombre de clicks total : <%= request.getAttribute("clicks") %>
    </div>
    <div>
        La quantité donnée total : <%= request.getAttribute("totalAmount") %>
    </div>
</div>
