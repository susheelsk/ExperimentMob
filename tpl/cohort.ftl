<#include "header.ftl">
<div class="row">
        <div class="col-md-12">
            <table class="table">
                <thead>
                <tr>
                    <th style="border: none;" class="col-md-3">Cohort Name</th>
                    <th style="border: none;" class="col-md-3">Cohort Description</th>
                    <th style="border: none;" class="col-md-3">Cohort File</th>
                    <th style="border: none;" class="col-md-3"></th>
                </tr>
                </thead>
                <tbody>
                
                <#if data??><#if data.cohortList??>
                <#list data.cohortList as cohort>
                <form class="form-horizontal" method="post" onsubmit="return confirm('Are you sure?');" enctype="multipart/form-data" action="removeCohort" role="form">
                <tr>
                    <td style="border: none;">
                        <input readonly="readonly" type="text" class="form-control" name="name" value="${cohort.name}">
                    </td>
                    <td style="border: none;">
                        <input readonly="readonly" type="text" class="form-control" name="description" value="${cohort.description}">
                    </td>
                    <td style="border: none;">
                        <input readonly="readonly" type="hidden" class="form-control" name="file" value="${cohort.file}">
                    </td>
                    <td style="border: none;">
                        <input style="width: 100px;" class="btn btn-large btn-danger" type="submit" value="Remove">
                    </td>
                </tr>
                </form>
                </#list>
                </#if></#if>
                
                <form class="form-horizontal" method="post" enctype="multipart/form-data" action="createCohort" role="form">
                <tr>
                    <td style="border: none;">
                        <input type="text" id="name" class="form-control" name="name" required="true">
                    </td>
                    <td style="border: none;">
                        <input type="text" id="description" class="form-control" name="description" required="true">
                    </td>
                    <td style="border: none;">
                        <input type="file" id="file" class="form-control" name="file" required="true">
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
    <br/><br/>
    <h3>Sample Cohorts file</h3>
    <div class="col-md-4">
    <textarea disabled cols="10" rows="30" class="form-control" id="exprDesc"
                                      name="expresstiontext">9oLiVjenIs
bz0ygwsXNx
bnItPkModK
KsomaxTW4j
PrOEhxx10m
fXZUTw5prG
DgZ1rTxHUb
JTRnpI52H6
WaD2c5QmqM
xnElPuMKB2
piwFFdyJDA
umH51xQCeE
pICzlbuCzR
CQKqFFwPPl
omMZxN940n
U2OLSq3cMn
jAyw30E7N3
AwETT6WWdI
WqPvS1njAK
hpX78mqYs7
g4kwuchnS9
LN4lj58XXk
IKlM7F1mma
PBIr2Rs0z1
d03ve14RLW
BCL8UDaoj7
F73AEoTw6F
OLLlhtCk8u
i6Dwzz9JTE
LAbuZgdUBF
RCPb8USDhI
PFaAbI0hJV
8qaI8gkvA4
H9g9tEcsrf
pxXxoicIPU
V4LGBBy0Hu
t9i6chuOCP
kJl4m2zYpe
90HX3q6uIB
PmFNbL84rb
L1Xz31ym1A</textarea>
</div>
<#include "footer.ftl">