<#include "header.ftl">
<div class="row">
        <div class="col-md-12">
            <table class="table">
                <thead>
                <tr>
                    <th style="border: none;" class="col-md-3">App Id</th>
                    <th style="border: none;" class="col-md-3">App Name</th>
                    <th style="border: none;" class="col-md-3"></th>
                </tr>
                </thead>
                <tbody>
                
                <#if data??><#if data.appList??>
                <#list data.appList as app>
                <form class="form-horizontal" method="post" onsubmit="return confirm('Are you sure?');" enctype="multipart/form-data" action="removeApp" role="form">
                <tr>
                    <td style="border: none;">
                        <input readonly="readonly" type="text" class="form-control" name="id" value="${app.id}">
                    </td>
                    <td style="border: none;">
                        <input readonly="readonly" type="text" class="form-control" name="name" value="${app.name}">
                    </td>
                    <td style="border: none;">
                        <input style="width: 100px;" class="btn btn-large btn-danger" type="submit" value="Remove">
                    </td>
                </tr>
                </form>
                </#list>
                </#if></#if>
                
                <form class="form-horizontal" method="post" enctype="multipart/form-data" action="createApp" role="form">
                <tr>
                    <td style="border: none;">
                        <input type="text" readonly="readonly" id="appid" class="form-control" name="id" required="true">
                    </td>
                    <td style="border: none;">
                        <input type="text" class="form-control" name="name" required="true">
                    </td>
                    <td style="border: none;">
                        <input style="width: 100px" class="btn btn-large btn-primary" type="submit" value="Add">
                    </td>
                </tr>
                </form>
                
                </tbody>
            </table>
        </div>
    </div>
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