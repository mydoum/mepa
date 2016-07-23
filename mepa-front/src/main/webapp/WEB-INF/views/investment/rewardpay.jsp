<%--
  Created by IntelliJ IDEA.
  User: Simon MACE
  Date: 23/07/2016
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<div class="container">
    <div class="page-header col-md-12"><h1>${rewardName}</h1></div>
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
                        ${description}
                    </p>
                </div>
            </div>
        </div>
        <div class="col-md-4 investForm">
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
    </div>
</div>

