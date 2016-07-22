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
                        <div>
                            <img src="http://www.thewrap.com/wp-content/uploads/2016/06/iron-throne.jpg" style="width:100%"/>
                        </div>

                        <div>
                            <img src="http://static2.techinsider.io/image/56ce049a2e5265b6008b955a-2297-1696/game-of-thrones-season-6-posters.jpg" style="width:100%"/>
                        </div>
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
