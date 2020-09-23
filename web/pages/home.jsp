<%@include file="/pages/template/head.jspf"%>

<%@include file="/pages/template/validation.jspf"%>

        <%@include file="/pages/template/menu.jspf"%>   

            <%@include file="/pages/template/nav.jspf"%>

            <div class="content">
                <div class="container-fluid">
                    <div class="row">

                        <div class="col-lg-12 col-md-12">
                            <div class="card">
                                <div class="card-header card-header-warning">
                                    <h4 class="card-title">Inicio ${idRol}</h4>
                                    <p class="card-category"></p>
                                </div>
                                <div class="card-body table-responsive">
                                    <table class="table table-hover">
                                        <thead class="text-warning">
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Salary</th>
                                        <th>Country</th>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>1</td>
                                                <td>Dakota Rice</td>
                                                <td>$36,738</td>
                                                <td>Niger</td>
                                            </tr>
                                        </tbody>
                                    </table>
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