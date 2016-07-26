<%--
  Created by IntelliJ IDEA.
  User: Valentin ZHENG
  Date: 19/07/2016
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<script type='text/javascript' src='http://www.webtutoriaux.com/services/compteur-visiteurs/index.php?client=154864'></script>

<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<div class="container">
    <div class="page-header col-md-12"><h1>Project Alpha</h1></div>
    <c:if test="${amount != null}">
        <div class="col-md-12 text-center alert alert-success investFormInside">
            Thank you for donating ${amount}€! An email to notify you has been sent.
        </div>
    </c:if>
    <c:if test="${errorInvest != null and !empty errorInvest}">
        <div class="col-md-12 text-center alert alert-danger investFormInside">
            ${errorInvest}
        </div>
    </c:if>
    <div class="col-md-12 investFormInside">
        <div class="col-md-8">
            <div class="col-md-4">
                <img src="https://i.ytimg.com/vi/7q33LWAjVcU/maxresdefault.jpg"
                     class="projectImageInvest img-responsive img-rounded"/>
            </div>
            <div class="panel panel-primary projectDescriptionInvest col-md-8">
                <!-- Default panel contents -->
                <div class="panel-heading">Description</div>
                <div class="panel-body">
                    <p>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut
                        labore
                        et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi
                        ut
                        aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
                        culpa qui officia deserunt mollit anim id est laborum.
                    </p>
                </div>
            </div>
        </div>
        <div class="col-md-4 investForm">
            <div class="col-md-12 InvestFormInside">
                <h3>Total Donation : ${totalDonation}<span class="glyphicon glyphicon-euro"></span></h3>
            </div>
            <c:url var="investMoney" value="/invest/investMoney"/>
            <form:form role="form" action="${investMoney}" method="post" modelAttribute="User">
                <div id="keypress"
                     class="InvestFormInside noUi-target noUi-ltr noUi-horizontal noUi-background col-md-12">
                </div>
                <label class="investFormInside col-md-12">Amount (€):</label>
                <div class="col-md-12 InvestFormInside">
                    <input name="investAmount" id="input-with-keypress"
                           class="form-control" type="text" required="required" readonly/>
                </div>
                <br/>
                <label class="investFormInside col-md-12">Anonyme: </label>
                <div class="col-md-12 InvestFormInside">
                    <input type="checkbox" id="anonymous_id" name="anonymous_id" value="anonymous_id_value"
                           class="form-control"/>
                </div>
                <br/>
                <div class="col-md-4">
                    <p align="center"><input type="submit" class="btn btn-primary InvestFormInside" value="Donate"></p>
                </div>
                <div class="col-md-4"></div>
            </form:form>
        </div>
        <br/>
        <div class="col-md-12 investFormInside">
            <tr/>
            <div class="col-md-12 investFormInside">
                <div class="col-md-8"><h2>List of donators</h2></div>
                <div class="col-md-4">
                    <div class="col-md-4"></div>
                    <div class="col-md-4">
                        <a href="/invest/filltables"><span class="btn btn-primary">Fill the table</span></a>
                    </div>
                    <div class="col-md-4"></div>
                </div>
            </div>
            <table class="col-md-12 table table-striped">
                <thead>
                <tr>
                    <th>Fistname</th>
                    <th>Lastname</th>
                    <th>Donation</th>
                    <th>Donation date</th>
                </tr>
                </thead>
                <tbody>
                <a href="https://twitter.com/share" class="twitter-share-button" data-lang="fr">Tweeter</a> <script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js,fjs);}}(document, 'script', 'twitter-wjs');</script>

                <c:if test="${investorsList.size() > 0}">
                    <c:forEach items="${investorsList}" var="investor" varStatus="status">
                        <tr>
                            <td>${investor.firstname}</td>
                            <td>${investor.lastname}</td>
                            <td>${investor.moneyAmount} €</td>
                            <td>${investor.stringDate}</td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
        </div>
        <br/>
        <div class="col-md-12 download investFormInside">
            <p align="center">
                <a href="/invest/download"><span class="btn btn-primary">Download</span></a>
            </p>
        </div>
    </div>
</div>

<c:url var="investSliderJs" value="/js/investment/nouislider.min.js"/>
<script src="${investSliderJs}"></script>
<c:url var="investSliderPersoJs" value="/js/investment/slider.js"/>
<script src="${investSliderPersoJs}"></script>

