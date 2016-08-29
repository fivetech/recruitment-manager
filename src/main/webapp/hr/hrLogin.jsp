<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HR Login</title>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/bootstrap-social.css" rel="stylesheet">
<link href="../css/styles.css" rel="stylesheet">
<link href="../css/hrStyles.css" rel="stylesheet">
<link href="../css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<!--login modal-->
<div id="loginModal" class="modal show" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
  <div class="modal-content">
      <div class="modal-header">
          <h1 class="text-center">HR Login</h1>
      </div>
      <div class="modal-body">
          <form class="form col-md-12 center-block" action="/LoginServlet?action=hr_login" method="post">
            <div class="form-group">
              <input type="text" name="hrEmail" class="form-control input-lg" placeholder="Email">
            </div>
            <div class="form-group">
              <input type="password" name="hrPass" class="form-control input-lg" placeholder="Password">
            </div>
            <div class="form-group">
              <button class="btn btn-primary btn-lg btn-block">Sign In</button>
            </div>
          </form>
      </div>
      <div class="modal-footer">
          
      </div>
  </div>
  </div>
</div>
<script src="../js/jquery-2.2.4.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/custom.js"></script>
</body>
</html>