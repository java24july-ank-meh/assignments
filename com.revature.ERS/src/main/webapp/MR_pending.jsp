<h3>pending :(-)</h3>
<a href="TestServlet">click for photo :)</a>  


 <script src="http://code.jquery.com/jquery-latest.min.js"></script>
 <script>
        $(document).on("click", "#somebutton", function() {        // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
            $.get("PendingServlet", function(responseJson) {       // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response JSON...
                var $table = $("<table>").appendTo($("#somediv")); // Create HTML <table> element and append it to HTML DOM element with ID "somediv".
                $.each(responseJson, function(index, rmbsts) {    // Iterate over the JSON array.
                    $("<tr>").appendTo($table)                     // Create HTML <tr> element, set its text content with currently iterated item and append it to the <table>.
                        .append($("<td>").text(rmbsts.r_description))        // Create HTML <td> element, set its text content with id of currently iterated product and append it to the <tr>.
                        .append($("<td>").text(rmbsts.rt_type))      // Create HTML <td> element, set its text content with name of currently iterated product and append it to the <tr>.
                        .append($("<td>").text(rmbsts.rs_status));    // Create HTML <td> element, set its text content with price of currently iterated product and append it to the <tr>.
                });
            });
        });
  </script>

    <div>
        <button id="somebutton">press here</button>
        <div id="somediv"></div>
    </div>