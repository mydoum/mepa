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
        <c:if test="${amount != null}">
            <div class="text-center alert alert-success">
                Merci d'avoir investi ${amount}€! Un email de notification a été envoyé.
            </div>
        </c:if>
        <c:url var="investMoney" value="/invest/investMoney"/>
        <form:form role="form" action="${investMoney}" method="post">
            <div id="keypress" class="form-group noUi-target noUi-ltr noUi-horizontal noUi-background"></div>
            <label>Montant :</label>
            <input name="investAmount" id="input-with-keypress"
                        class="form-control" type="text" required="required"/>
            <p align="center"><input type="submit" class="btn btn-primary" value="Investir"></p>
        </form:form>
    </div>
    <br />
    <div class="container">
        <tr />
        <h2>Liste des investisseurs</h2>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Prénom</th>
                <th>Nom</th>
                <th>Donation</th>
                <th>Date de donation</th>
            </tr>
            </thead>
            <tbody>
            <%-- <c:if test="!empty investorsList"> --%>

                <c:forEach items="${investorsList}" var="investor" varStatus="status">
                    <tr>
                        <td>${investor.firstname}</td>
                        <td>${investor.lastname}</td>
                        <td>${investor.moneyAmount}</td>
                        <td>${investor.dateOfInvestment}</td>
                        <%-- <td>${investor.getFirstname()}</td>
                        <td>${investor.getLastname()}</td>
                        <td>${investor.getMoneyAmount()}</td>
                        <td>${investor.getDateOfInvestment()}</td> --%>
                    </tr>
                </c:forEach>
            <%-- </c:if> --%>
            </tbody>
        </table>
    </div>
    <br />
    <div class="download">
        <p align="center">
            <a href="/invest/download"><span class="btn btn-primary">Download</span></a>
        </p>

    </div>
</div>

