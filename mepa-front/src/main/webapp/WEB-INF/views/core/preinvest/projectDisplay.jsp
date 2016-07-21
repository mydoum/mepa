<%@ include file="/WEB-INF/views/includes/common.jsp" %>


<div class="container">
    <h2>${project.name}</h2>
    <div>
        <table>
            <tr>
                <td><img src="http://www.gobadges.com/v/vspfiles/photos/CD0564-2.jpg" height="400" width="400"/></td>
                <td><span class="date">Date de début : ${project.dateFormat("dd/MM/yyyy", project.startDate)}</span></td>
                <td><span class="date">Date de fin : ${project.dateFormat("dd/MM/yyyy", project.endDate)}</span></td>
            </tr>
            <tr>
                <td>
                    <div class="panel panel-primary" id="description">
                        <div class="panel-heading">Description</div>
                        <div class="panel-body">
                            ${project.description}
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </div>

</div>
