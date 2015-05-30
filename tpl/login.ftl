<#include "header.ftl">

 <form class="form-signin" method="post" action="confirm" role="form">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input style="height: auto;" type="text" name="uname" class="form-control" placeholder="Username" required autofocus>
        <input style="height: auto;" type="password" name="passwd" class="form-control" placeholder="Password" required>
        
        <#if data??> <#if data.appList??>
        <select class="form-control" id="appId" name="appId" required>
        	<#list data.appList as app> 
        		<option value="${app.id}">${app.name}</option>
        	</#list>
        </select>
        </#if></#if><br/><br/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>

<#include "footer.ftl">