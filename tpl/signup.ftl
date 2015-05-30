<#include "header.ftl">

<form class="form-signin" method="post" role="form" action="confirmsignup">
<h2 class="form-signin-heading">Register User</h2><br/>
            <fieldset>
            <!-- Sign Up Form -->
            <!-- Text input-->
            <div class="control-group">
              <label class="control-label" for="uname">Username:</label>
              <div class="controls">
                <input id="uname" name="uname" class="form-control" type="text" placeholder="susheel" class="input-large" required />
              </div>
            </div>
            
            <!-- Text input
            <div class="control-group">
              <label class="control-label" for="userid">Alias:</label>
              <div class="controls">
                <input id="userid" name="userid" class="form-control" type="text" placeholder="JoeSixpack" class="input-large" required="">
              </div>
            </div> -->
            
            <!-- Password input-->
            <div class="control-group">
              <label class="control-label" for="passwd">Password:</label>
              <div class="controls">
                <input id="passwd" name="passwd" class="form-control" type="password" placeholder="********" class="input-large" required="">
              </div>
            </div>
            
            <!-- Re-enter Password-->
            <div class="control-group">
              <label class="control-label" for="confirm">Re-Enter Password:</label>
              <div class="controls">
                <input id="confirm" class="form-control" name="confirm" type="password" placeholder="********" class="input-large" required="">
              </div>
              <!--<em id="matchpassword" name="matchpassword" >Passwords don't match</em>-->
            </div>
            
            <!-- Multiple Radios (inline)-->
            <br>
            <div class="control-group">
              <label class="control-label" for="humancheck">Humanity Check:</label>
              <div class="controls">
                <!--<label class="radio inline" for="humancheck-0">
                  <input type="radio" class="radioBtnClass" name="humancheck" id="humancheck-0" value="robot" checked="checked">I'm a Robot</label> -->
                <label class="radio inline" for="humancheck-1">
                  <input required type="radio" name="humancheck" id="humancheck-1" required value="human">I'm Human</label>
              </div>
            </div> 
            
            <!-- Button -->
            <div class="control-group">
              <label class="control-label" for="confirmsignup"></label>
              <div class="controls">
                <button id="confirmsignup" name="confirmsignup" class="btn btn-success">Sign Up</button>
              </div>
            </div>
            </fieldset>
            </form>
            
<#include "footer.ftl">