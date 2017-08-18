<h3>pending :(-)</h3>
<a href="TestServlet">click for photo :)</a>  


 <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
 <script>
        $(document).on("click", "#somebutton", function() {        // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
            /*$.get("ER_PendingServlet", function(responseJson) {       // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response JSON...
                var $body = $("<tr>").appendTo($("#body")); // Create HTML <table> element and append it to HTML DOM element with ID "somediv".
                $.each(responseJson, function(index, rmbsts) {    // Iterate over the JSON array.
                    $("<td>").appendTo($body)                     // Create HTML <tr> element, set its text content with currently iterated item and append it to the <table>.
                        .append($("</td><td>").text(rmbsts.r_description))        // Create HTML <td> element, set its text content with id of currently iterated product and append it to the <tr>.
                        .append($("</td><td>").text(rmbsts.rt_type))      // Create HTML <td> element, set its text content with name of currently iterated product and append it to the <tr>.
                        .append($("</td></tr>").text(rmbsts.rs_status));    // Create HTML <td> element, set its text content with price of currently iterated product and append it to the <tr>.
                });
            });*/
            
            
        });
        
       function apend(){
    	   var $body = $("<tr>").appendTo($('#tdiv'));
    	   $body.append($("<td>").text("SomeDummyText"))
       		.append($("<td>").text("SomeDummyText2"))
       		.append($("<td>").text("SomeDummyText3"))
       		.append($("<td>").text("SomeDummyText4"))
  			.append($("</tr>"));
       }
        
  </script>

    <div>
        <button id="somebutton">press here</button>
        <div id="somediv"></div>
    </div>
    
    
    <table>
		<thead>
			<tr>
				<th>Description</th>
				<th>Resolve</th>
				<th>Request</th>
			</tr>
		</thead>
		<tbody id="tdiv">
		
		</tbody>
	</table>
    