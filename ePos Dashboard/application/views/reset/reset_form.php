<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ePOS</title>   
<link rel="shortcut icon" href="<?php echo base_url(); ?>assets/images/icon.ico" />
 
<script type="text/javascript" src="<?php echo base_url(); ?>assets/js/jquery.js"></script>
<script type="text/javascript" src="<?php echo base_url(); ?>assets/js/bootstrap.js"></script>
<script>
</script>
 
<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>assets/css/bootstrap.css"/>
<style>
body{background: #eee url('/assets/images/resto.png');}
html,body{
    position: relative;
    height: 100%;
}
            
.login-container{
    position: relative;
    width: 300px;
    margin: 80px auto;
    padding: 20px 40px 40px;
    text-align: center;
    border: 1px solid #ccc;
    box-shadow:
        0 0 1px rgba(0, 0, 0, 0.3),
        0 3px 7px rgba(0, 0, 0, 0.3),
        inset 0 1px rgba(255,255,255,1),
        inset 0 -3px 2px rgba(0,0,0,0.25);
    border-radius: 5px;
    background: linear-gradient(#ffffff, #eeefef 75%);
}

#output{
  margin-bottom: 10px;
  color: rgb(228, 105, 105);
}

#output.alert-success{
    color: rgb(25, 204, 25);
    background: none;
}

#output.alert-danger{
    color: rgb(228, 105, 105);    
    background: none;
}


.login-container::before,.login-container::after{
    content: "";
    position: absolute;
    width: 100%;height: 100%;
    top: 3.5px;left: 0;
    background: #fff;
    z-index: -1;
    -webkit-transform: rotateZ(4deg);
    -moz-transform: rotateZ(4deg);
    -ms-transform: rotateZ(4deg);
    border: 1px solid #ccc;

}

.login-container::after{
    top: 5px;
    z-index: -2;
    -webkit-transform: rotateZ(-2deg);
     -moz-transform: rotateZ(-2deg);
      -ms-transform: rotateZ(-2deg);

}

.form-box input{
    width: 100%;
    padding: 10px;
    height:40px;
    border: 1px solid #ccc;
    transition:0.2s ease-in-out;     
    box-shadow: 1px 0 0 rgba(255, 255, 255, 0.7);
}

.form-box input:focus{
    outline: 0;
    background: #f7f7f7;
}

.form-box input[type="text"]{
    border-radius: 5px;
}

.form-box input[type="password"]{
    border-radius: 5px;
}

.form-box button.login{
    margin-top:15px;
    padding: 10px 20px;
}

.animated {
  -webkit-animation-duration: 1s;
  animation-duration: 1s;
  -webkit-animation-fill-mode: both;
  animation-fill-mode: both;
}

.profile-img
{
    width: 100px;
    margin: 0 auto 10px;
    display: block;
    margin-top: 10px;
    margin-bottom: 30px;
}
.mt10
{
    margin-top: 10px;
}           
</style>
  
</head>
 
<body>
 
<div class="container"> 
	<div class="login-container"> 
    <img class="profile-img grayscale" src="<?php echo base_url(); ?>assets/images/logo3d.png" alt="ePOS">  
    <div class="form-box">      
    <div id="output"></div>
    <?php 
	   echo validation_errors(); 
	   $attributes = array('class' => 'form-signin', 'id' => 'myform', 'role' => 'form');
	   echo form_open('reset/'.$code, $attributes); 
	  ?>    
      <div class="form-group">
        <div class="input-group">
          <div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
          <input name="password1" id="password1" class="form-control" type="password" placeholder="Fill In New Password" required>
        </div>
      </div>
      <div class="form-group">
        <div class="input-group">
          <div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
          <input name="password2" id="password2" class="form-control" type="password" placeholder="Retype New Password" required>
        </div>
      </div>
      <input id="resetbutt" name="reset" type="submit" value="Reset Your Password" class="btn btn-lg btn-primary btn-block" />
      <a href="/" class="pull-right mt10">Nope, not this time </a><span class="clearfix"></span>
    <?=form_close()?>
    </div>
  </div>
</div> 
<script>
 $(document).ready(function () {
   $("#password2").keyup(function(){checkPasswordMatch("#password1","#password2","#output","#resetbutt");});
});

function checkPasswordMatch(ale,ela,luv,vul) {
    var password = $(ale).val();
    var confirmPassword = $(ela).val();

    if (password != confirmPassword){       
        $(vul).prop("disabled", true);
        $(luv).html("Passwords do not match!").addClass("alert-danger").removeClass("alert-success");
    } else {                                
        $(vul).prop("disabled", false);
        $(luv).html("Passwords match.").addClass("alert-success").removeClass("alert-danger");
    }
}
</script>
</body>
</html>