<#include "header.ftl">

<form style="max-width: 380px;padding: 15px;margin: 0 auto;">
        <h2 class="form-signin-heading">Please choose any app</h2><br/><br/>
        <#if data??> <#if data.appList??>
        <select class="form-control" id="appId" name="appId" required>
        	<#list data.appList as app> 
        		<option value="${app.id}">${app.name}</option>
        	</#list>
        </select>
        </#if></#if><br/><br/>
        <button class="btn btn-lg btn-primary btn-block" onclick="onSelect()" type="button">Go</button>
      </form>

<script>
var TAG = "home page";

function onSelect() {
	var appId = $( "#appId" ).val()
	console.log("App Selected : "+appId);
	if(appId==null || appId=="") {
		bootbox.alert("Please select any app");
		return;
	}
	window.location = "/experimentmob/admin/"+appId+"/home"
}

</script>
<#include "footer.ftl">