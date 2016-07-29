<!-- Include Bootstrap Datepicker -->
<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css"/>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css"/>
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>

<div class="container">
    <div class="row">
        <div class="col-md-6">
            <div class="tab-content">
                <form id="eventForm" class="form-horizontal">
                    <h1 style="text-align:center">Statistiques</h1>
                    <fieldset>
                        <div class="control-group">
                            <label class="control-label" for="firstname">Nombre de vues sur la page d'Inscription</label>
                            <div class="controls">
                                <input id="firstname" name="firstname" placeholder="" class="form-control input-lg"
                                       type="text" value="${nbViewInscription}" disabled>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="lastname">Nombre d'Inscription</label>
                            <div class="controls">
                                <input id="lastname" name="lastname" placeholder="" class="form-control input-lg"
                                       type="text" value="${nbInscription}" disabled>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="email">Nombre de vue sur la page de Connexion</label>
                            <div class="controls">
                                <input id="email" name="email" placeholder="" class="form-control input-lg"
                                       type="email"
                                       value="${nbViewLogin}" disabled>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="address">Nombre de connexions</label>
                            <div class="controls">
                                <input id="address" name="address" placeholder="" class="form-control input-lg"
                                       type="text"
                                       value="${nbLogin}" disabled>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="description">Nombre de vue sur la page de renvoi de mot de passe</label>
                            <div class="controls">
                                <input id="description" name="description" placeholder=""
                                       class="form-control input-lg" type="text"
                                       value="${nbViewForget}" disabled>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="description">Nombre de renvoi de mot de passe</label>
                            <div class="controls">
                                <input id="lol" name="description" placeholder=""
                                       class="form-control input-lg" type="text"
                                       value="${nbForget}" disabled>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="description">Nombre de vue sur l'édition de profil</label>
                            <div class="controls">
                                <input id="loli" name="description" placeholder=""
                                       class="form-control input-lg" type="text"
                                       value="${nbViewEditUser}" disabled>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="description">Nombre d'édition de profil</label>
                            <div class="controls">
                                <input id="lola" name="description" placeholder=""
                                       class="form-control input-lg" type="text"
                                       value="${nbEditUser}" disabled>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>
