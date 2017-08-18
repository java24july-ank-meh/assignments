

<h3>All Employees</h3>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
 <script>
        $(document).on("click", "#somebutton", function() {        // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
            $.get("ER_EmployeeInfoServlet", function(responseJson) {       // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response JSON...
                 var $body = $("<tr>").appendTo($('#tdiv')); // Create HTML <table> element and append it to HTML DOM element with ID "somediv".
                $.each(responseJson, function(index, employees) { 
                	$body.append($("<td>").text(employees.u_username))
	               		.append($("<td>").text(employees.u_password))
	               		.append($("<td>").text(employees.u_firstname))
	               		.append($("<td>").text(employees.u_lastname))
	          			.append($("</tr>"));
                	
        		});
            });
        });
        
  </script>

    <div>
        <button id="somebutton">press here</button>
        <div id="somediv"></div>
    </div>
    
    
    <table>
		<thead>
			<tr>
				<th>UserName</th>
				<th>Password</th>
				<th>FirstName</th>
				<th>LastName</th>
			</tr>
		</thead>
		<tbody id="tdiv">
		
		</tbody>
	</table>
    