<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<script>
    $("#slideshow > div:gt(0)").hide();

    setInterval(function() {
        $('#slideshow > div:first')
                .fadeOut(1000)
                .next()
                .fadeIn(1000)
                .end()
                .appendTo('#slideshow');
    },  3000);
</script>

<div class="container">
    <h2>${project.name}</h2>
    <div>
        <table>
            <tr>
                <td>
                    <div id="slideshow">
                        <c:forEach items="${project.imagesLinks}" var="image" varStatus="loop">
                            <div>
                                <img src="${image}" style="width:100%"/>
                            </div>
                        </c:forEach>
                    </div>
                </td>
                <td>
                    <div class="date">Date de début:</div>
                    <div class="date">${project.dateFormat("dd/MM/yyyy", project.startDate)}</div>
                    <div class="date">Date de fin:</div>
                    <div class="date">${project.dateFormat("dd/MM/yyyy", project.endDate)}</div>
                </td>
            </tr>
        </table>
        <div class="panel panel-primary" id="description">
            <div class="panel-heading">Description</div>
            <div class="panel-body">
                ${project.description}
            </div>
        </div>
    </div>
</div>
