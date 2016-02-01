<#include "header.ftl">
<div class="row">
        <div class="col-md-12">
            <table class="table">
                <thead>
                <tr>
                    <th style="border: none;" class="col-md-3">Field Name</th>
                    <th style="border: none;" class="col-md-3">Field Value</th>
                    <th style="border: none;" class="col-md-3">Field Type</th>
                    <th style="border: none;" class="col-md-3"></th>
                </tr>
                </thead>
                <tbody>
                
                <#if data??><#if data.fieldList??>
                <#list data.fieldList as field>
                <form class="form-horizontal" method="post" onsubmit="return confirm('Are you sure?');" enctype="multipart/form-data" action="removedefaultfields" role="form">
                <tr>
                    <td style="border: none;">
                        <input readonly="readonly" type="text" class="form-control" name="name" value="${field.name}">
                    </td>
                    <td style="border: none;">
                        <input readonly="readonly" type="text" class="form-control" name="value" value="${field.value}">
                    </td>
                    <td style="border: none;">
                        <select readonly="readonly" id="type" name="type" class="form-control">
                        	<#if field.type?contains("INTEGER")>
                            	<option value="INTEGER">Integer</option>
                            <#elseif field.type?contains("NUMBER")>
                            	<option value="NUMBER">Number</option>
                            <#elseif field.type?contains("BOOLEAN")>
                            	<option value="BOOLEAN">Boolean</option>
                            <#elseif field.type?contains("STRING")>
                            	<option value="STRING">String</option>
                            <#elseif field.type?contains("FILE")>
                            	<option value="FILE">FILE</option>
                            </#if>
                        </select>
                    </td>
                    <td style="border: none;">
                        <input style="width: 100px;" class="btn btn-large btn-danger" type="submit" value="Remove">
                    </td>
                </tr>
                </form>
                </#list>
                </#if></#if>
                
                <form class="form-horizontal" method="post" enctype="multipart/form-data" action="setdefaultfields" role="form">
                <tr>
                    <td style="border: none;">
                        <input type="text" id="name" class="form-control" name="name" required="true">
                    </td>
                    <td style="border: none;">
                        <input type="text" id="inputValue" class="form-control" name="value" required="true">
                    </td>
                    <td style="border: none;">
                        <select id="fieldtypeselectedit" class="form-control" name="type"
                                required="true">

                        <option value="" disabled>Select a type</option>
                        <option value="INTEGER">Integer</option>
                        <option value="NUMBER">Number</option>
                        <option value="BOOLEAN">Boolean</option>
                        <option value="STRING">String</option>
                        <option value="FILE">File</option>
                        </select>
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

<script language="javascript" type="text/javascript">
var TAG = "defaultfields page";
$('#fieldtypeselectedit').on('change', function() {
	    console.log(TAG+"field type changed");
	    if(this.value == "FILE") {
	        console.log(TAG+"field type is FILE");
	        document.getElementById("inputValue").type="file";
	        document.getElementById("inputValue").name="filestream"; 
	    }else {
	        document.getElementById("inputValue").type="text";
	        document.getElementById("inputValue").name="value";
	    }
});
</script>
<#include "footer.ftl">