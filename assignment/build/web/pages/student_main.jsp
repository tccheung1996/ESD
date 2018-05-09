<!DOCTYPE html>
<html lang="en">
    <%@page errorPage="error_page" %>
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>E-learning system</title>

        <!-- Bootstrap Core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href="vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="dist/css/sb-admin-2.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        
    </head>

    <body>
        
        <div id="wrapper">
           <jsp:include page="student_nav_bar.jsp"/>      

            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Welcome Student!</h1>
                    </div>
                </div>
 
         



            </div>

        </div>
        <!-- /#wrapper -->

        <!-- jQuery -->
        <script src="vendor/jquery/jquery.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

        <!-- Metis Menu Plugin JavaScript -->
        <script src="vendor/metisMenu/metisMenu.min.js"></script>

        <!-- Custom Theme JavaScript -->
        <script src="dist/js/sb-admin-2.js"></script>
        <script type="application/javascript">
            $(document).ready(function() {
            $("#role").change(function() {
            if ($(this).val() == "Student") {
            $("#class_name").show();
            $("#position").hide();


            } else if ($(this).val() == "Teacher") {
            $("#class_name").hide();
            $("#position").show();


            } else if ($(this).val() == "Administrator") {
            $("#class_name").hide();
            $("#position").hide();


            }




            });




            });

        </script>
    </body>

</html>
