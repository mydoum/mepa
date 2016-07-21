<%--
  Created by IntelliJ IDEA.
  User: patrickear
  Date: 21/7/2016
  Time: 2:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-6">

            <form class="form-horizontal" action="" method="POST">
                <fieldset>
                    <div id="legend">
                        <legend class="">Register</legend>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="lastname">Nom</label>
                        <div class="controls">
                            <input id="lastname" name="lastname" placeholder="" class="form-control input-lg" type="text">
                            <p class="help-block">Nom can contain any letters or numbers, without spaces</p>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="firstname">Prénom</label>
                        <div class="controls">
                            <input id="firstname" name="firstname" placeholder="" class="form-control input-lg" type="text">
                            <p class="help-block">Prénom can contain any letters or numbers, without spaces</p>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="email">E-mail</label>
                        <div class="controls">
                            <input id="email" name="email" placeholder="" class="form-control input-lg" type="email">
                            <p class="help-block">Please provide your E-mail</p>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="password">Password</label>
                        <div class="controls">
                            <input id="password" name="password" placeholder="" class="form-control input-lg" type="password">
                            <p class="help-block">Password should be at least 6 characters</p>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="password_confirm">Password (Confirm)</label>
                        <div class="controls">
                            <input id="password_confirm" name="password_confirm" placeholder="" class="form-control input-lg" type="password">
                            <p class="help-block">Please confirm password</p>
                        </div>
                    </div>

                    <div class="control-group">
                        <!-- Button -->
                        <div class="controls">
                            <button class="btn btn-success">Register</button>
                        </div>
                    </div>
                </fieldset>
            </form>

        </div>
    </div>
</div>