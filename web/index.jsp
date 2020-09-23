<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty idUser}">
    <c:redirect url="./Home"/>
</c:if>

<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="utf-8" />
  <link rel="apple-touch-icon" sizes="76x76" href="assets/img/sara/sa.png">
  <link rel="icon" type="image/png" href="assets/img/sara/sa.png">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <title>Inicio</title>
  <meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no' name='viewport' />

  <link rel="stylesheet" type="text/css" href="assets/css/fonts.css" />
  <link href="assets/css/material-dashboard.css?v=2.1.2" rel="stylesheet" />
  <link href="assets/css/index.css" rel="stylesheet" />
</head>

<body class="" id="bodyLogin">
  <hr class="hiden">
  <hr class="hiden">
  <hr class="hiden">
  <div class="container h-100">
          
          
  <div class="row h-100 justify-content-center align-items-center">

    <div class="col-lg-4 col-md-4">
      <div class="card">
        <div class="card-header card-header-info">
          <h4 class="card-title">Acceder </h4>
          <p class="card-category"></p>
        </div>
        <div class="card-body table-responsive">
      
          <form class="form-signin" action="Ingresar?url=user">
            <img class="mb-4 img-fluid" src="assets/img/sara/saraazul.png" alt="" >
            <label for="inputEmail" class="sr-only">Correo</label>
            <input type="email" id="inputEmail" class="form-control" placeholder="usuario@misena.edu.co" required autofocus>
            <label for="inputPassword" class="sr-only">Contraseña</label>
            <input type="password" id="inputPassword" class="form-control" placeholder="*********" required>
            <div class="checkbox mb-3">
              <label>
               
              </label>
            </div>
            <button id="buttonEnter" class="btn btn-lg btn-primary btn-block" type="button">Ingresar</button>
            <p class="mt-5 mb-3 text-muted text-center">&copy; 2020 Públicado en el Datacenter</p>
          </form>
        

        </div>
      </div>
    </div>
  </div>
</div>



</body>

  <script src="assets/js/core/jquery-3.5.1.min.js"></script>
  <script src="assets/js/core/popper.min.js"></script>
  <script src="assets/js/core/bootstrap-material-design.min.js"></script>
  <script src="assets/js/plugins/sweetalert2.js"></script>
  <script src="assets/js/material-dashboard.js?v=2.1.2" type="text/javascript"></script>
  <script src="assets/js/plugins/bootstrap-notify.js"></script>
  <script src="assets/js/proyect/login/login.js" charset="UTF-8"></script>


</html>