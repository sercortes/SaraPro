<%@include file="/pages/template/head.jspf"%>

<%@include file="/pages/template/validation.jspf"%>

<%@include file="/pages/template/menu.jspf"%>   

<%@include file="/pages/template/nav.jspf"%>

<div class="content">
    <div class="container-fluid">
        <div class="row">

            <div class="col-lg-12 col-md-12">
                <div class="card">
                    <div class="card-header card-header-info">
                        <h4 class="card-title">Inicio</h4>
                        <p class="card-category"></p>
                    </div>
                    <div class="card-body table-responsive">

                        <div class="row">
                            
                            <div class="col-lg-3 col-md-6 col-sm-6">
                                <div class="card card-stats">
                                    <div class="card-header card-header-info card-header-icon">
                                        <a href="./Search">
                                            <div class="card-icon">
                                                <i class="material-icons">menu_book</i>
                                            </div>
                                            <p class="card-category">Ver productos virtuales</p>
                                        </a>
                                        <h3 class="card-title">
                                            <!--<small>GB</small>-->
                                        </h3>
                                    </div>
                                    <div class="card-footer">
                                        <div class="stats">
                                            <i class="material-icons">content_copy</i> Consultar
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-3 col-md-6 col-sm-6">
                                <div class="card card-stats">
                                    <div class="card-header card-header-success card-header-icon">
                                        <a href="./Upload">
                                            <div class="card-icon">
                                                <i class="material-icons">cloud_upload</i>
                                            </div>
                                            <p class="card-category">Subir producto virtual</p>
                                        </a>
                                    </div>
                                    <div class="card-footer">
                                        <div class="stats">
                                            <i class="material-icons">date_range</i> Cargar
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-3 col-md-6 col-sm-6">
                                <div class="card card-stats">
                                    <div class="card-header card-header-warning card-header-icon">
                                        <a href="./SendAgain">
                                            <div class="card-icon">
                                                <i class="material-icons">info_outline</i>
                                            </div>
                                            <p class="card-category">Productos a corregir</p>
                                        </a>
                                    </div>
                                    <div class="card-footer">
                                        <div class="stats">
                                            <i class="material-icons">local_offer</i>Enviar nuevamente
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="row">


                            <div class="col-lg-3 col-md-6 col-sm-6">
                                <div class="card card-stats">
                                    <div class="card-header card-header-light card-header-icon">
                                        <a href="./Listas">
                                            <div class="card-icon">
                                                <i class="material-icons">playlist_add_check</i>
                                            </div>
                                            <p class="card-category">Lista de chequeo</p>
                                        </a>
                                    </div>
                                    <div class="card-footer">
                                        <div class="stats">
                                            <i class="material-icons">playlist_add_check</i> cree una nueva lista
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                             <div class="col-lg-3 col-md-6 col-sm-6">
                                <div class="card card-stats">
                                    <div class="card-header card-header-warning card-header-icon">
                                        <a href="./getProductos">
                                            <div class="card-icon">
                                                <i class="material-icons">rate_review</i>
                                            </div>
                                            <p class="card-category">Productos x Calificar</p>
                                        </a>
                                    </div>
                                    <div class="card-footer">
                                        <div class="stats">
                                            <i class="material-icons">error</i> Califique los p.v pendientes
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                            
                            
                        </div>

                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<%@include file="/pages/template/foot.jspf"%>
<%@include file="/pages/template/footer.jspf"%>
<script>
    let menu = document.getElementById('Inicio')
    menu.classList.add('active')
</script>