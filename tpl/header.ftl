<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="icon" href="https://cdn0.iconfinder.com/data/icons/cosmo-medicine/40/test-tube_2-128.png" >
    <title>ExperimentMob</title>

    <!-- Bootstrap core CSS -->
    <!-- <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet"> -->
    
    <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="http://nitinhayaran.github.io/jRange/jquery.range.css">

<script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="//cdn.jsdelivr.net/bootbox/4.4.0/bootbox.js"></script>
<script src="http://nitinhayaran.github.io/jRange/jquery.range.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.18/angular.js"></script>


<!-- Optional theme -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

    <!-- Custom styles for this template -->
    <style type="text/css">
    body {
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #eee;
}

#cropContainerDiv {
	width: 480px;
	height: 220px;
	position:relative; /* or fixed or absolute */
}

.form-signin {
  max-width: 330px;
  padding: 15px;
  margin: 0 auto;
}
.form-signin .form-signin-heading,
.form-signin .checkbox {
  margin-bottom: 10px;
}
.form-signin .checkbox {
  font-weight: normal;
}
.form-signin .form-control {
  position: relative;
  -webkit-box-sizing: border-box;
     -moz-box-sizing: border-box;
          box-sizing: border-box;
  padding: 10px;
  font-size: 16px;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
.alert-message
{
    margin: 20px 0;
    padding: 20px;
    border-left: 3px solid #eee;
}
.alert-message h4
{
    margin-top: 0;
    margin-bottom: 5px;
}
.alert-message p:last-child
{
    margin-bottom: 0;
}
.alert-message code
{
    background-color: #fff;
    border-radius: 3px;
}
.alert-message-success
{
    background-color: #F4FDF0;
    border-color: #3C763D;
}
.alert-message-success h4
{
    color: #3C763D;
}
.alert-message-danger
{
    background-color: #fdf7f7;
    border-color: #d9534f;
}
.alert-message-danger h4
{
    color: #d9534f;
}
.alert-message-warning
{
    background-color: #fcf8f2;
    border-color: #f0ad4e;
}
.alert-message-warning h4
{
    color: #f0ad4e;
}
.alert-message-info
{
    background-color: #f4f8fa;
    border-color: #5bc0de;
}
.alert-message-info h4
{
    color: #5bc0de;
}
.alert-message-default
{
    background-color: #EEE;
    border-color: #B4B4B4;
}
.alert-message-default h4
{
    color: #000;
}
.alert-message-notice
{
    background-color: #FCFCDD;
    border-color: #BDBD89;
}
.alert-message-notice h4
{
    color: #444;
}

.vcenter {
    display: inline-block;
    vertical-align: middle;
    float: none;
}

.filterable {
    margin-top: 15px;
}
.filterable .panel-heading .pull-right {
    margin-top: -20px;
}
.filterable .filters input[disabled] {
    background-color: transparent;
    border: none;
    cursor: auto;
    box-shadow: none;
    padding: 0;
    height: auto;
}
.filterable .filters input[disabled]::-webkit-input-placeholder {
    color: #333;
}
.filterable .filters input[disabled]::-moz-placeholder {
    color: #333;
}
.filterable .filters input[disabled]:-ms-input-placeholder {
    color: #333;
}

.correctOption {
background-color: green;
color: white;
font-weight: bold;
}

</style>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <div class="container">
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
   <div class="navbar-cust">
       <div class="container">
           <div class="navbar-header">
               <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                       data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                   <span class="sr-only">Toggle navigation</span>
                   <span class="icon-bar"></span>
                   <span class="icon-bar"></span>
                   <span class="icon-bar"></span>
               </button>
               <#if data??>
               		<#if data.appId??>
               			<a class="navbar-brand" href="/experimentmob/admin/${data.appId}/home"><font color="white">ExperimentMob Home</font></a>
               		<#else>
               			<a class="navbar-brand" href="#"><font color="white">ExperimentMob</font></a>
               		</#if>
               <#else>
               		<a class="navbar-brand" href="#"><font color="white">ExperimentMob</font></a>
               </#if>
           </div>
           <div id="navbar" class="navbar-collapse collapse">
               <ul class="nav navbar-nav navbar-right">
               	   <#if data??><#if data.appId??>
                   <li>
                       <a href="/experimentmob/admin/${data.appId}/defaultfields" class="btn-link">
                           <span style="color: white;">Fields</span>
                       </a>
                   </li>
                   </#if></#if>
                   <#if data??><#if data.appId??>
                   <li>
                       <a href="/experimentmob/admin/${data.appId}/addexperiment" class="btn-link">
                           <span style="color: white;">Add Experiment</span>
                       </a>
                   </li>
                   </#if></#if>
                   <#if data??><#if data.appId??>
                   <li>
                       <a href="/experimentmob/admin/${data.appId}/cohorts" class="btn-link">
                           <span style="color: white;">Cohorts</span>
                       </a>
                   </li>
                   </#if></#if>
                   <#if data??><#if data.appId??>
                   <li>
                       <a href="/experimentmob/admin/${data.appId}/history" class="btn-link">
                           <span style="color: white;">History</span>
                       </a>
                   </li>
                   </#if></#if>
                   <#if data??><#if data.appId??>
                   <li>
                       <a href="/experimentmob/admin/${data.appId}/apps" class="btn-link">
                           <span style="color: white;">Apps</span>
                       </a>
                   </li>
                   </#if></#if>
                   <#if data??><#if data.appId??>
                   <li>
                       <a href="/experimentmob/admin/${data.appId}/help" class="btn-link">
                           <span style="color: white;">Help</span>
                       </a>
                   </li>
                   <#elseif page.loggedIn>
                   <li>
                       <a href="/experimentmob/admin/help" class="btn-link">
                           <span style="color: white;">Help</span>
                       </a>
                   </li>
                   </#if></#if>
                   <#if data??><#if data.appId??>
                   <li>
                       <a href="/experimentmob/auth/${data.appId}/adduser" class="btn-link">
                           <span style="color: white;">Add User</span>
                       </a>
                   </li>
                   </#if></#if>
                   <#if page.loggedIn>
                   <li>
                       <a href="/experimentmob/auth/logout" class="btn-link">
                           <span style="color: white;">Logout</span>
                       </a>
                   </li>
                   </#if>
               </ul>
           </div>
       </div>
   </div>
</nav>
    <br/><br/>
    <div id="flash-wrap">
    <!-- 
	
	-->
	
	<#list flash.warning as message>
	<div class="alert-message alert-message-warning">
                <h4>
                    Warning</h4>
                <p>${message}</p>
            </div>
	</#list>
	<#list flash.error as message>
	<div class="alert-message alert-message-danger">
                <h4>
                    Error</h4>
                <p>${message}</p>
            </div>
	</#list>
	<#list flash.success as message>
	<div class="alert-message alert-message-success">
                <h4>
                    Success</h4>
                <p>
                    ${message}</p>
            </div>
	</#list>
</div>