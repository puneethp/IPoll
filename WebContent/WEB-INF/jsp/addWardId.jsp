<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script language="javascript" type="text/javascript">
isValid = false;
function verify()
{
	verify_ward();
	return isValid;
}
function verify_ward()
{
	var xmlhttp;
	var txt,xx,x,i;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	 }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  x=xmlhttp.responseXML.documentElement.getElementsByTagName("msg");
		  if(x.length==1)
			{
			  
				xx=x[0].getElementsByTagName("Query");
				var temp = xx[0].firstChild.nodeValue;
				if(temp=="wardvalid")
				{
					
					xx=x[0].getElementsByTagName("value");
					if(xx[0].firstChild.nodeValue=="false")
					{
						var temp = document.getElementById("idError");
						temp.innerHTML="";
						isValid = true;
					}
					else if(xx[0].firstChild.nodeValue=="true"){
						var temp = document.getElementById("idError");
						temp.innerHTML = "<p>Error: Ward Exists try a different Wardno.</p>";
						isValid = false;
					}
					else
					{
						var temp = document.getElementById("idError");
						temp.innerHTML = "<p>Error: Internal Error please try later.</p>";
						isValid = false;
					}
				}
			}
	    }
	  }
	var pval = "city=" + document.getElementById("city").value+"&state="+document.getElementById("state").value+"&wardno="+document.getElementById("wardno").value;
	xmlhttp.open("GET","./WardExist?"+pval,true);
	xmlhttp.send();
}
</script>
<div class="main">
	<form onsubmit="return verify()" action="addWard.action">
	<fieldset>
		<legend>Add Wards</legend>
		<label id="idError" ></label>
		<label>State</label>
		<input type="text" label="State " name="state" id="state" size="15" />
		<label>City</label>
		<input type="text" label="City " name="city" id="city" size="15" />
		<label>Ward N.o.</label>
		<input type="text" label="WardNO " onblur="verify_ward()" name="wardno" id="wardno" cssClass="textBox" size="15" />
		<br />
		<input type="submit" class="btn btn-primary" value="Add" />
		</fieldset>
	</form>
</div>