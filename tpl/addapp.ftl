<#include "header.ftl">

 <form class="form-signin" method="post" action="addappapi" role="form">
        <h2 class="form-signin-heading">Enter App details</h2>
        <input type="text" name="name" class="form-control" placeholder="App Name" required autofocus><br/>
        <input type="text" id="appid" readonly="readonly" name="id" class="form-control" placeholder="App Id" required><br/><br/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Add App</button>
      </form>
<script>
$(document).ready(function() {
	var randNum = getRandomInt(1,100000); 
	$('#appid').val(""+randNum);
});

function getRandomInt(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}
</script>
<#include "footer.ftl">
