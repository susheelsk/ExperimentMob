<#include "header.ftl">
<h3>Experiment Details</h3><br/>
<div class="row" ng-app="" ng-controller="exprController">
<div>
        <div class="col-md-9">
            <table class="table">
                <tbody>
                    <tr>
                        <th style="border: none;">EXPERIMENT NAME</th>
                        <td style="border: none;"><input type="text" class="form-control" ng-model="expr_name"></td>
                    </tr>
                    <tr>
                        <th style="border: none;">EXPERIMENT DESCRIPTION</th>
                        <td style="border: none;"><textarea class="form-control" ng-model="expr_desc"> </textarea></td>
                    </tr>
                    <tr>
                        <th style="border: none;">OS VERSION</th>
                        <td style="border: none;"> 
                            <select class="form-control" ng-model="expr_os_version">
                                <option value="">Select OS Version</option>
                                <option value="GINGERBREAD">GINGERBREAD</option>
                                <option value="GINGERBREAD_MR1">GINGERBREAD_MR1</option>
                                <option value="HONEYCOMB">HONEYCOMB</option>
                                <option value="ICE_CREAM_SANDWICH">ICE_CREAM_SANDWICH</option>
                                <option value="ICECREAMSANDWICH">ICECREAMSANDWICH</option>
                                <option value="JELLYBEAN">JELLYBEAN</option>
                                <option value="KITKAT">KITKAT</option>
                                <option value="LOLLIPOP">LOLLIPOP</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th style="border: none;">APP VERSION</th>
                        <td style="border: none;"><input type="number" min="1" step="1" class="form-control" ng-model="expr_app_version"></td>
                    </tr>
                    <tr>
                        <th style="border: none;">PERCENTAGE OF USERS</th>
                        <td style="border: none;"><input class="slider-input" value="0"/></td>
                    </tr>
                    <tr>
                        <th style="border: none;">USER COHORTS</th>
                        <td style="border: none; align:left;">
                            <select class="form-control" ng-model="expr_cohort">
                                <option disabled value="">Select User Cohort</option>
                                <option ng-repeat="cohort in cohorts track by $index" value="{{cohort.file}}">{{cohort.name}}</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th style="border: none;">TARGET COUNTRY</th>
                        <td style="border: none; align:left;">
                        	<select class="form-control" ng-model="expr_country">
                        		<option disabled value="">Select User Cohort</option>
								<option value="AF">Afghanistan</option>
								<option value="AX">Åland Islands</option>
								<option value="AL">Albania</option>
								<option value="DZ">Algeria</option>
								<option value="AS">American Samoa</option>
								<option value="AD">Andorra</option>
								<option value="AO">Angola</option>
								<option value="AI">Anguilla</option>
								<option value="AQ">Antarctica</option>
								<option value="AG">Antigua and Barbuda</option>
								<option value="AR">Argentina</option>
								<option value="AM">Armenia</option>
								<option value="AW">Aruba</option>
								<option value="AU">Australia</option>
								<option value="AT">Austria</option>
								<option value="AZ">Azerbaijan</option>
								<option value="BS">Bahamas</option>
								<option value="BH">Bahrain</option>
								<option value="BD">Bangladesh</option>
								<option value="BB">Barbados</option>
								<option value="BY">Belarus</option>
								<option value="BE">Belgium</option>
								<option value="BZ">Belize</option>
								<option value="BJ">Benin</option>
								<option value="BM">Bermuda</option>
								<option value="BT">Bhutan</option>
								<option value="BO">Bolivia, Plurinational State of</option>
								<option value="BQ">Bonaire, Sint Eustatius and Saba</option>
								<option value="BA">Bosnia and Herzegovina</option>
								<option value="BW">Botswana</option>
								<option value="BV">Bouvet Island</option>
								<option value="BR">Brazil</option>
								<option value="IO">British Indian Ocean Territory</option>
								<option value="BN">Brunei Darussalam</option>
								<option value="BG">Bulgaria</option>
								<option value="BF">Burkina Faso</option>
								<option value="BI">Burundi</option>
								<option value="KH">Cambodia</option>
								<option value="CM">Cameroon</option>
								<option value="CA">Canada</option>
								<option value="CV">Cape Verde</option>
								<option value="KY">Cayman Islands</option>
								<option value="CF">Central African Republic</option>
								<option value="TD">Chad</option>
								<option value="CL">Chile</option>
								<option value="CN">China</option>
								<option value="CX">Christmas Island</option>
								<option value="CC">Cocos (Keeling) Islands</option>
								<option value="CO">Colombia</option>
								<option value="KM">Comoros</option>
								<option value="CG">Congo</option>
								<option value="CD">Congo, the Democratic Republic of the</option>
								<option value="CK">Cook Islands</option>
								<option value="CR">Costa Rica</option>
								<option value="CI">Côte d'Ivoire</option>
								<option value="HR">Croatia</option>
								<option value="CU">Cuba</option>
								<option value="CW">Curaçao</option>
								<option value="CY">Cyprus</option>
								<option value="CZ">Czech Republic</option>
								<option value="DK">Denmark</option>
								<option value="DJ">Djibouti</option>
								<option value="DM">Dominica</option>
								<option value="DO">Dominican Republic</option>
								<option value="EC">Ecuador</option>
								<option value="EG">Egypt</option>
								<option value="SV">El Salvador</option>
								<option value="GQ">Equatorial Guinea</option>
								<option value="ER">Eritrea</option>
								<option value="EE">Estonia</option>
								<option value="ET">Ethiopia</option>
								<option value="FK">Falkland Islands (Malvinas)</option>
								<option value="FO">Faroe Islands</option>
								<option value="FJ">Fiji</option>
								<option value="FI">Finland</option>
								<option value="FR">France</option>
								<option value="GF">French Guiana</option>
								<option value="PF">French Polynesia</option>
								<option value="TF">French Southern Territories</option>
								<option value="GA">Gabon</option>
								<option value="GM">Gambia</option>
								<option value="GE">Georgia</option>
								<option value="DE">Germany</option>
								<option value="GH">Ghana</option>
								<option value="GI">Gibraltar</option>
								<option value="GR">Greece</option>
								<option value="GL">Greenland</option>
								<option value="GD">Grenada</option>
								<option value="GP">Guadeloupe</option>
								<option value="GU">Guam</option>
								<option value="GT">Guatemala</option>
								<option value="GG">Guernsey</option>
								<option value="GN">Guinea</option>
								<option value="GW">Guinea-Bissau</option>
								<option value="GY">Guyana</option>
								<option value="HT">Haiti</option>
								<option value="HM">Heard Island and McDonald Islands</option>
								<option value="VA">Holy See (Vatican City State)</option>
								<option value="HN">Honduras</option>
								<option value="HK">Hong Kong</option>
								<option value="HU">Hungary</option>
								<option value="IS">Iceland</option>
								<option value="IN">India</option>
								<option value="ID">Indonesia</option>
								<option value="IR">Iran, Islamic Republic of</option>
								<option value="IQ">Iraq</option>
								<option value="IE">Ireland</option>
								<option value="IM">Isle of Man</option>
								<option value="IL">Israel</option>
								<option value="IT">Italy</option>
								<option value="JM">Jamaica</option>
								<option value="JP">Japan</option>
								<option value="JE">Jersey</option>
								<option value="JO">Jordan</option>
								<option value="KZ">Kazakhstan</option>
								<option value="KE">Kenya</option>
								<option value="KI">Kiribati</option>
								<option value="KP">Korea, Democratic People's Republic of</option>
								<option value="KR">Korea, Republic of</option>
								<option value="KW">Kuwait</option>
								<option value="KG">Kyrgyzstan</option>
								<option value="LA">Lao People's Democratic Republic</option>
								<option value="LV">Latvia</option>
								<option value="LB">Lebanon</option>
								<option value="LS">Lesotho</option>
								<option value="LR">Liberia</option>
								<option value="LY">Libya</option>
								<option value="LI">Liechtenstein</option>
								<option value="LT">Lithuania</option>
								<option value="LU">Luxembourg</option>
								<option value="MO">Macao</option>
								<option value="MK">Macedonia, the former Yugoslav Republic of</option>
								<option value="MG">Madagascar</option>
								<option value="MW">Malawi</option>
								<option value="MY">Malaysia</option>
								<option value="MV">Maldives</option>
								<option value="ML">Mali</option>
								<option value="MT">Malta</option>
								<option value="MH">Marshall Islands</option>
								<option value="MQ">Martinique</option>
								<option value="MR">Mauritania</option>
								<option value="MU">Mauritius</option>
								<option value="YT">Mayotte</option>
								<option value="MX">Mexico</option>
								<option value="FM">Micronesia, Federated States of</option>
								<option value="MD">Moldova, Republic of</option>
								<option value="MC">Monaco</option>
								<option value="MN">Mongolia</option>
								<option value="ME">Montenegro</option>
								<option value="MS">Montserrat</option>
								<option value="MA">Morocco</option>
								<option value="MZ">Mozambique</option>
								<option value="MM">Myanmar</option>
								<option value="NA">Namibia</option>
								<option value="NR">Nauru</option>
								<option value="NP">Nepal</option>
								<option value="NL">Netherlands</option>
								<option value="NC">New Caledonia</option>
								<option value="NZ">New Zealand</option>
								<option value="NI">Nicaragua</option>
								<option value="NE">Niger</option>
								<option value="NG">Nigeria</option>
								<option value="NU">Niue</option>
								<option value="NF">Norfolk Island</option>
								<option value="MP">Northern Mariana Islands</option>
								<option value="NO">Norway</option>
								<option value="OM">Oman</option>
								<option value="PK">Pakistan</option>
								<option value="PW">Palau</option>
								<option value="PS">Palestinian Territory, Occupied</option>
								<option value="PA">Panama</option>
								<option value="PG">Papua New Guinea</option>
								<option value="PY">Paraguay</option>
								<option value="PE">Peru</option>
								<option value="PH">Philippines</option>
								<option value="PN">Pitcairn</option>
								<option value="PL">Poland</option>
								<option value="PT">Portugal</option>
								<option value="PR">Puerto Rico</option>
								<option value="QA">Qatar</option>
								<option value="RE">Réunion</option>
								<option value="RO">Romania</option>
								<option value="RU">Russian Federation</option>
								<option value="RW">Rwanda</option>
								<option value="BL">Saint Barthélemy</option>
								<option value="SH">Saint Helena, Ascension and Tristan da Cunha</option>
								<option value="KN">Saint Kitts and Nevis</option>
								<option value="LC">Saint Lucia</option>
								<option value="MF">Saint Martin (French part)</option>
								<option value="PM">Saint Pierre and Miquelon</option>
								<option value="VC">Saint Vincent and the Grenadines</option>
								<option value="WS">Samoa</option>
								<option value="SM">San Marino</option>
								<option value="ST">Sao Tome and Principe</option>
								<option value="SA">Saudi Arabia</option>
								<option value="SN">Senegal</option>
								<option value="RS">Serbia</option>
								<option value="SC">Seychelles</option>
								<option value="SL">Sierra Leone</option>
								<option value="SG">Singapore</option>
								<option value="SX">Sint Maarten (Dutch part)</option>
								<option value="SK">Slovakia</option>
								<option value="SI">Slovenia</option>
								<option value="SB">Solomon Islands</option>
								<option value="SO">Somalia</option>
								<option value="ZA">South Africa</option>
								<option value="GS">South Georgia and the South Sandwich Islands</option>
								<option value="SS">South Sudan</option>
								<option value="ES">Spain</option>
								<option value="LK">Sri Lanka</option>
								<option value="SD">Sudan</option>
								<option value="SR">Suriname</option>
								<option value="SJ">Svalbard and Jan Mayen</option>
								<option value="SZ">Swaziland</option>
								<option value="SE">Sweden</option>
								<option value="CH">Switzerland</option>
								<option value="SY">Syrian Arab Republic</option>
								<option value="TW">Taiwan, Province of China</option>
								<option value="TJ">Tajikistan</option>
								<option value="TZ">Tanzania, United Republic of</option>
								<option value="TH">Thailand</option>
								<option value="TL">Timor-Leste</option>
								<option value="TG">Togo</option>
								<option value="TK">Tokelau</option>
								<option value="TO">Tonga</option>
								<option value="TT">Trinidad and Tobago</option>
								<option value="TN">Tunisia</option>
								<option value="TR">Turkey</option>
								<option value="TM">Turkmenistan</option>
								<option value="TC">Turks and Caicos Islands</option>
								<option value="TV">Tuvalu</option>
								<option value="UG">Uganda</option>
								<option value="UA">Ukraine</option>
								<option value="AE">United Arab Emirates</option>
								<option value="GB">United Kingdom</option>
								<option value="US">United States</option>
								<option value="UM">United States Minor Outlying Islands</option>
								<option value="UY">Uruguay</option>
								<option value="UZ">Uzbekistan</option>
								<option value="VU">Vanuatu</option>
								<option value="VE">Venezuela, Bolivarian Republic of</option>
								<option value="VN">Viet Nam</option>
								<option value="VG">Virgin Islands, British</option>
								<option value="VI">Virgin Islands, U.S.</option>
								<option value="WF">Wallis and Futuna</option>
								<option value="EH">Western Sahara</option>
								<option value="YE">Yemen</option>
								<option value="ZM">Zambia</option>
								<option value="ZW">Zimbabwe</option>
							</select>
						</td>
                    </tr>
                    <tr>
                        <th style="border: none;">TARGET CITY</th>
                        <td style="border: none;"><input type="text" placeholder="Bangalore (No need to explicitly choose country or region)" class="form-control" ng-model="expr_city"></td>
                    </tr>
                    <tr>
                        <th style="border: none;">TARGET REGION</th>
                        <td style="border: none;"><input type="text" placeholder="Karnataka (No need to explicitly choose country or city)" class="form-control" ng-model="expr_region"></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
        <h3>Select the fields to perform experiment on</h3><br/>
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
                <tr ng-repeat="field in selectedFields">
                    <td style="border: none;">
                        <input type="text" disabled id="fieldname" name="fieldvalue" class="form-control"
                               ng-model="selectedFields[$index].name">
                    </td>
                    <td style="border: none;">
                        <input type="text" disabled id="fieldvalue" name="fieldvalue" class="form-control"
                               ng-model="selectedFields[$index].value">
                    </td>
                    <td style="border: none;">
                        <input type="text" disabled id="fieldtype" name="fieldtype" class="form-control"
                               ng-model="selectedFields[$index].type">
                    </td>
                    <td style="border: none;">
                        <button style="width: 125px;" id="removeFieldButton" class="btn btn-danger"
                                ng-click="removeField($index)">Remove</button>
                    </td>
                </tr>
                <tr>
                    <td style="border: none;">
                        <select class="form-control"  id="addfieldname" ng-model="selected_name" ng-change="showFieldValues()">
                            <option disabled value="">Select Field Name</option>
                            <option ng-repeat="df in defaultFields" value="{{df.name}}">{{df.name}}</option>
                    </select>
                    </td>
                    <td style="border: none;">
                        <input type="text" id="addfieldvalue" name="fieldvalue" class="form-control"
                               ng-model="selected_value">
                    </td>
                    <td style="border: none;">
                        <input type="text" disabled id="addfieldtype" name="fieldtype" class="form-control"
                               ng-model="selected_type">
                    </td>
                    <td style="border: none;">
                        <button style="width: 125px;" id="addFieldButton" class="btn btn-primary"
                                ng-click="addField()">Add</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table">
                <thead>
                <th class="col-md-3"></th>
                <th class="col-md-3"></th>
                </thead>
                <tbody>
                <tr>
                    <td>
                    	<#if data??>
               			<#if data.appId??>
                        <a id="discardExprButton" class="btn btn-danger col-md-offset-6"
                                href="/experimentmob/admin/${data.appId}/home">Discard</a>
                        </#if>
                        </#if>
                    </td>
                    <td>
                        <button id="saveExprButton" class="btn btn-primary col-md-offset-left"
                        ng-click="saveExperiment()">Publish Experiment</button>
                        <form class="form-horizontal" id="createExprForm" method="post" enctype="multipart/form-data" action="createExperiment" role="form">
                    		<input readonly="readonly"id="createExprFormInput"  type="hidden" class="form-control" ng-model="exprData" name="exprData" value="">
                        </form>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
<script>
var test;
var TAG = "ADD_EXPERIMENT"
function exprController($scope) {
	var defFields = [];
	defFields = ${data.fieldJSONArray};
	console.log("DefaultFields : "+angular.toJson(defFields));
	$scope.defaultFields = defFields;
	test = $scope;
	$('#addfieldname').on('change', function() {
            var name = $scope.selected_name;
            var defFields = $scope.defaultFields;
            console.log("Default Fields : "+defFields);
            defFields.forEach(function(dField){
                if(dField.name == name){
                    console.log(TAG+"field type changed");
                    console.log(TAG + "name : "+dField.name);
                    console.log(TAG + "value : "+dField.value.toString());
                    console.log(TAG + "type : "+dField.type);
                    $scope.selected_value = dField.value.toString();
                    $scope.selected_type = dField.type;
                    if(dField.value.toString().value == "FILE") {
                        console.log(TAG+"field type is FILE");
                        document.getElementById("addfieldvalue").type="file";
                    }else {
                        document.getElementById("addfieldvalue").type="text";
                    }
                    return;
                }
            });
        });
        
        $scope.cohorts = [];
        <#if data??><#if data.cohortList??>
        var cohortList = ${data.cohortJSONArray};
        $scope.cohorts = cohortList;
        </#if></#if>
        
        $scope.selectedFields = [];
    
        // show slider    
        $('.slider-input').jRange({
            from: 0,
            to: 100,
            step: 1,
            scale: [0,10,20,30,40,50,60,70,80,90,100],
            format: '%s',
            width: 300,
            showLabels: true
        });
        
        $scope.showFieldValues = function() {

            var name = $scope.selected_name;
            var defFields = $scope.defaultFields;
            defFields.forEach(function(dField){
                if(dField.name == name){
                    console.log(TAG+"field type changed");
                    console.log(TAG + "name : "+dField.name);
                    console.log(TAG + "value : "+dField.value.toString());
                    console.log(TAG + "type : "+dField.type);
                    $scope.selected_value = dField.value.toString();
                    $scope.selected_type = dField.type;
                    if(dField.type == "FILE") {
                        console.log(TAG+"field type is FILE");
                        document.getElementById("addfieldvalue").type="file";
                    }else {
                        document.getElementById("addfieldvalue").type="text";
                    }
                    return;
                }
            });
            
        }
        
        function isStringEmpty(str) {

            if(typeof str === "undefined" || typeof str === "null") {
                return true;
            }
            if( str.length === 0 || !str.trim()){                                                                                      
                return true;
            }
                
            return false;                                                                                                              
        }
        
        function createExpression(os_version, app_version, perc_user, cohort, city, country, region) {
            
            var expr_parts = [];
            
            if ( os_version != null ) {
                expr_parts.push("OS_VERSION== \""+os_version + "\"");
            }
            
            if ( app_version != null ) {
                expr_parts.push("APP_VERSION=="+app_version);
            }
            
            if ( perc_user != null ) {
                expr_parts.push("PERC_USERS<"+perc_user);
            }
            
            if ( cohort != null ) {
                expr_parts.push("COHORT==\""+cohort + "\"");
            }
            
            if ( city != null ) {
                expr_parts.push("CITY==\""+city + "\"");
            }
            
            if ( country != null ) {
                expr_parts.push("COUNTRY==\""+country + "\"");
            }
            
            if ( region != null ) {
                expr_parts.push("REGION==\""+region + "\"");
            }
            
            return "( " + expr_parts.join("&&") + " )";
            
        }
        
        function isInteger(val) {
            return (typeof val == "number") && (isFinite(val)) && ((val % 1) === 0);
        }

        function isNumber(val) {
            return (typeof val == "number");
        }

        function isBoolean(val) {
            console.log(TAG + " isBoolean " + val);
            return (typeof val == "boolean");
        }

        function isString(val) {
            return (typeof val == "string");
        }

        function validateValue() {

            switch ($scope.selected_type) {

                case "INTEGER":
                    console.log(TAG + " Type is INTEGER");
                    return isInteger(parseInt($scope.selected_value));
                    break;
                case "NUMBER":
                    console.log(TAG + " Type is NUMBER");
                    return isNumber(parseFloat($scope.selected_value));
                    break;
                case "BOOLEAN":
                    console.log(TAG + " Type is BOOLEAN " + $scope.selected_value);
                    return isBoolean(stringToBoolean($scope.selected_value));
                    break;
                case "STRING":
                    console.log(TAG + " Type is STRING");
                    return isString($scope.selected_value);
                    break;
            }
        }

        function stringToBoolean (str){
            console.log(TAG + " Converting strig to loawer case " + str);
            switch(str.toLowerCase()){
                case "true": case "yes": case "1": return true;
                case "false": case "no": case "0": case null: return false;
                default: return undefined;
            }
        }
        
        function getDate() {
        	var today = new Date();
			var dd = today.getDate();
			var mm = today.getMonth()+1; //January is 0!
			var yyyy = today.getFullYear();
			
			if(dd<10) {
			    dd='0'+dd
			} 
			
			if(mm<10) {
			    mm='0'+mm
			} 
			var todate = mm+'/'+dd+'/'+yyyy;
			return todate; 	
        }
        
        $scope.addField = function() {

            var name = $scope.selected_name;
            var value = $scope.selected_value;
            var type = $scope.selected_type;
            
            console.log($scope.expr_os_version +" ---- "+ $scope.expr_app_version +" ---- "+  $scope.expr_perc_user +" ---- "+  $scope.expr_cohort);

            if(!name || !value || !type) {
                bootbox.alert("Please check your input");
                return;
            }

            if(type=="FILE") {
                //write code to upload file
                var fileInput = $('#addfieldvalue');
                var myFormData = new FormData();
                myFormData.append('file', document.getElementById("addfieldvalue").files[0]);

                $.ajax({
                  url: '/experimentmob/admin/upload',
                  type: 'POST',
                  data: myFormData,
                  processData: false,
                  contentType: false,
                  success: function(response) {
                    console.log(response);
                    var jsonResponse = (response);
                    if(jsonResponse.status != "success"){
                        bootbox.alert("Problem uploading.Try again");
                        return;
                    }
                    console.log("URL of file uploaded is : "+jsonResponse.data);

                    $scope.selected_value = jsonResponse.data;
                    value = $scope.selected_value;

                    if(!value) {
                        bootbox.alert("Problem uploading.Try again");
                        return;
                    }

                    var push = true;

                    $scope.selectedFields.forEach(function(field) {
                        if(field.name == $scope.selected_name) {
                            push = false;
                            bootbox.alert("Please do not use duplicate fields");
                            return;
                        }
                    });

                    if(push) {
                        console.log("Adding field");
                        $scope.selectedFields.push({"name":name, "value":$scope.selected_value, "type":type});

                        $scope.selected_name = "";
                        $scope.selected_value = "";
                        $scope.selected_type = "";
                    }
                    console.log("Refreshing UI");
                    $scope.$apply();
                  }
                });
                return;
            }

            var validValue = validateValue();
            if(validValue) {

                switch ($scope.selected_type) {

                    case "INTEGER":
                        $scope.selected_value = parseInt($scope.selected_value);
                        if(parseFloat(value)!=$scope.selected_value) {
                            return;
                        }
                        break;
                    case "NUMBER":
                        $scope.selected_value = parseFloat($scope.selected_value);
                        break;
                    case "BOOLEAN":
                        $scope.selected_value = stringToBoolean($scope.selected_value);
                        break;
                    case "STRING":

                        break;
                }

                console.log(TAG + "Validity Status " + validValue+" Value : "+$scope.selected_value);

                var push = true;
                $scope.selectedFields.forEach(function(field) {
                    if(field.name == $scope.selected_name) {
                        push = false;
                        bootbox.alert("Please do not use duplicate fields");
                        return;
                    }
                });

                if(push) {
                    $scope.selectedFields.push({"name":name, "value":$scope.selected_value, "type":type});

                    $scope.selected_name = "";
                    $scope.selected_value = "";
                    $scope.selected_type = "";
                }
            }else {
                bootbox.alert("Please check your input");
            }
        }

        $scope.removeField = function(index) {
            console.log(TAG + "Removing Selected Field at index : " + index);
            $scope.selectedFields.splice(index, 1);
        }
        
        function generateUUID() {
		    var d = new Date().getTime();
		    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
		        var r = (d + Math.random()*16)%16 | 0;
		        d = Math.floor(d/16);
		        return (c=='x' ? r : (r&0x3|0x8)).toString(16);
		    });
		    return uuid;
		};
        
        
        $scope.saveExperiment = function() {
            var expression = $scope.expression;
            var id =  generateUUID();
            
            var expr_name    = (isStringEmpty($scope.expr_name)) ? null : $scope.expr_name ;
            var expr_desc    = (isStringEmpty($scope.expr_desc)) ? null : $scope.expr_desc ;
            var expr_os_version    = (isStringEmpty($scope.expr_os_version)) ? null : $scope.expr_os_version ;                         
            var expr_app_version   = parseInt($scope.expr_app_version) ? $scope.expr_app_version : null;                               
            var expr_perc_user     = parseInt($('.slider-input').val()) ? $('.slider-input').val() : null;
            var expr_cohort = (isStringEmpty($scope.expr_cohort)) ? null : $scope.expr_cohort;
            var expr_city = (isStringEmpty($scope.expr_city)) ? null : $scope.expr_city;
            var expr_country = (isStringEmpty($scope.expr_country)) ? null : $scope.expr_country;
            var expr_region = (isStringEmpty($scope.expr_region)) ? null : $scope.expr_region;                    
            
            /*  
            console.log(TAG + " Type of OS_VERSION : " + typeof expr_os_version + "  Value : " + expr_os_version);                     
            console.log(TAG + " Type of APP_VERSION : " + typeof expr_app_version + "  Value : " + expr_app_version);                  
            console.log(TAG + " Type of PERC_USERS : " + typeof expr_perc_user + "  Value : " + expr_perc_user);                       
            console.log(TAG + " Type of COHORT : " + typeof expr_cohort + "  Value : " + expr_cohort);
            console.log(TAG + " Type of CITY : " + typeof expr_city + "  Value : " + expr_city);
            console.log(TAG + " Type of COUNTRY : " + typeof expr_country + "  Value : " + expr_country);
            console.log(TAG + " Type of REGION : " + typeof expr_region + "  Value : " + expr_region);
            */       
            
            if(expr_app_version == null && expr_os_version == null && expr_cohort == null && expr_perc_user == null && expr_city == null && expr_country == null && expr_region == null ) {
                bootbox.alert("Please check you expression inputs");   
                return;
            }
            if(expr_name == null || expr_desc == null) {
            	bootbox.alert("Please check you expression inputs");   
                return;
            }
            
            //var expression = "APP_VERSION==" + expr_app_version + " && COHORT==" + expr_cohort + " && PERC_USERS<"+expr_perc_user;
            
            var expression = createExpression(expr_os_version, expr_app_version, expr_perc_user, expr_cohort, expr_city, expr_country, expr_region);
            
            var experiment = {"id":id,"name":expr_name,"desc":expr_desc, "expression":expression, "fields":$scope.selectedFields,"createDate":getDate()};

            console.log(TAG + " Adding experiment " + experiment);
            
            if(experiment.fields.length == 0 ) {
                bootbox.alert("Please do not act like a monkey");
                return;
            }
            
            if(!experiment.expression ) {
                bootbox.alert("Please check your input");
                return;
            }
            console.log("Experiment : "+angular.toJson(experiment));
			$scope.exprData = angular.toJson(experiment); 
			$("#createExprFormInput").val(angular.toJson(experiment));
			$("#createExprForm").submit();  
        }
}
</script>

<#include "footer.ftl">