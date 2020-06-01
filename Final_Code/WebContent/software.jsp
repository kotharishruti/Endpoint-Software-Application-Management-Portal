<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
 
<!DOCTYPE html>
<html lang="en">
<head>s
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<title>try</title>
<script   src="https://code.jquery.com/jquery-1.12.2.min.js"   integrity="sha256-lZFHibXzMHo3GGeehn1hudTAP3Sc0uKXBXAzHX1sjtk="   crossorigin="anonymous"></script>
<script type="text/javascript">
	function showPopup(curObj) {
		$('#pop').toggle();
		$(curObj).parent().append($('#pop'));
	}
	
	function submitData() {
		$.ajax({url: "demo_test.txt", success: function(result){
	        $("#div1").html(result);
	    }});
	}
</script>

</head>
<body>

 <div class="container ">
	<div style="width:90%;" class="navbar navbar-default row">
		<div style="float:left">Logo</div>
		<div style="float:right">sLogo</div>
	</div>

	<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
	     url="jdbc:mysql://localhost/mydb"
	     user="root"  password="root"/>



	<sql:query dataSource="${snapshot}" var="result">
	SELECT * from software;
	</sql:query>

	<div class="row"> 
		<c:forEach var="row" items="${result.rows}">
	
			<div class="col-sm-4" style="background-color:#eee; margin:3px; padding:10px">
		    	<table cellpadding="10">
		    		<tr><td><a href=""><img src="${row.name}.jpg" alt=""/></a></td></tr>
					<tr><td><a href="javascript:void(0)" onclick="showPopup(this)"><c:out value="${row.name}" /></a></td></tr>
				</table>
			</div>
		</c:forEach>
	</div>

	<div id="pop" style="position:absolute; display:none; background-color:#efe; border: 1px solid #ccc; z-index:9999;">
		<table cellpadding="10">
	   		<tr><td>IP</td><td><input type="text" name="ip" id="ip" /></td></tr>
			<tr><td>Root Password</td><td><input type="text" name="pass" id="pass" /></td></tr>
			<tr><td>&nbsp;</td><td><input type="button" name="Submit" id="Submit" value="Submit" onclick="submitData()"/></td></tr>
		</table>
	</div>	


</div>

</body>
</html>