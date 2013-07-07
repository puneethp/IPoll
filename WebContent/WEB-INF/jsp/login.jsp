<%@ page isELIgnored="false"%>

<form action="logmein.action" method="post">
	<fieldset>
		<legend>Login</legend>
		<div><label class="label label-important"><h4>${requestScope.error}</h4></label></div>
		<label>User</label> <input type="text" name="user" placeholder="Registered user name..." /> <label>Password</label>
		<input type="password" name="pswd" />
		<br />
		<button class="btn btn-primary"
			type="submit">Login</button>
	</fieldset>
</form>
<p>
	Not a member ? For registration <a href="">click here</a>.
</p>