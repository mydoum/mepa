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

                    <%-- PostInvest Check EndDate --%>
                    <jsp:useBean id="todayDate" class="java.util.Date"/>
                    <c:choose>
                        <c:when test="${project.endDate <= todayDate}">
                            <div class="date">PostInvest -> Date de fin atteinte.</div>
                        </c:when>
                        <c:otherwise>
                            <div class="date">PostInvest -> Date de fin non atteinte.</div>
                        </c:otherwise>
                    </c:choose>
                    <%-- /PostInvest Check EndDate --%>
                </td>
            </tr>
        </table>

        <%-- Facebook share button --%>
        <div class="fb-share-button"
             data-href="https://mepa.herokuapp.com/core/preinvest/projectDisplay/${project.id}"
             data-layout="button_count"
             data-size="large"
            <%-- Open the iframe --%>
             data-mobile-iframe="true">

            <a class="fb-xfbml-parse-ignore" target="_blank"
               href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fmepa.herokuapp.com%2Fcore%2Fpreinvest%2F${project.id}&amp;src=sdkpreparse">
                Partager
            </a>
        </div>
        <div class="panel panel-primary" id="description">
            <div class="panel-heading">Description</div>
            <div class="panel-body">
                ${project.description}
            </div>
        </div>
    </div>
</div>
