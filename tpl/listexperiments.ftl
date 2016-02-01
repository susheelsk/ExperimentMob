<#include "header.ftl">
<h2>Experiments</h2><br/>
    <div class="row">
        <div class="col-md-12">
            <table class="table">
                <thead>
                <tr>
                    <th style="text-align: center" class="col-md-2">Name</th>
                    <th style="text-align: center" class="col-md-2">Description</th>
                    <th style="text-align: center" class="col-md-2">Id</th>
                    <th style="text-align: center" class="col-md-3">Fields</th>
                    <th style="text-align: center" class="col-md-2">Created On</th>
                    <th style="text-align: center" class="col-md-2">Unpublish</th>
                </tr>
                </thead>
                <tbody>
                <#if data??><#if data.exprList??>
                <#list data.exprList as expr>
                    <tr id="${expr.id}">
                        <td style="vertical-align: middle;">
                            <input type="text" value="${expr.name}" disabled cols="20" class="form-control" id="exprName"
                                      name="expresstiontext">
                        </td>
                        <td style="vertical-align: middle;">
                            <textarea disabled cols="40" class="form-control" id="exprDesc"
                                      name="expresstiontext">${expr.desc}</textarea>
                        </td>
                        <td style="vertical-align: middle;">
                            <textarea disabled cols="30" class="form-control" id="expresstiontext"
                                      name="expresstiontext">${expr.id}</textarea>
                        </td>
                        <td>
                            <table style="border: none;background-color:#eee" class="table">
                                <thead>
                                    <th style="border: none;text-align: center">Name</th>
                                    <th style="border: none;text-align: center">Value</th>
                                </thead>
                                <tbody>
                                	<#if expr.fields??>
                					<#list expr.fields as field>
                                    <tr>
                                        <td style="border: none;" class="col-md-1">
                                            <select disabled id="fieldnameselect" name="fieldnameselect"
                                                    class="form-control">

                                                <option value="${field.name}">${field.name}</option>
                                            </select>
                                        </td>
                                        <td style="border: none;" class="col-md-1">
                                            <input disabled type="text" class="form-control"
                                                    value="${field.value}">
                                        </td>
                                    </tr>
                                    </#list>
                    				</#if>
                                </tbody>
                            </table>
                        </td>
                        <td style="vertical-align: middle;">
                            <input type="text" value="${expr.createDate}" disabled cols="20" class="form-control" id="exprcreateDate"
                                      name="exprcreateDate">
                        </td>
                        <td style="vertical-align: middle; text-align: center;">
                        	<form class="form-horizontal" id="removeForm" method="post" onsubmit="return confirm('Are you sure?');" enctype="multipart/form-data" action="removeExperiment" role="form">
	                        	<input readonly="readonly" type="hidden" class="form-control" name="exprId" value="${expr.id}">
	                            <input style="width: 100px;" class="btn btn-large btn-danger" type="submit" value="Unpublish">
	                        </form>
                        </td>
                    </tr>
                    </#list>
                    </#if></#if>
                </tbody>
            </table>
        </div>
    </div>
<#include "footer.ftl">