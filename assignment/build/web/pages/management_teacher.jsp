<!DOCTYPE html>
<%@ page import="java.util.ArrayList, ict.bean.*" %>

<html lang="en">

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

        <!-- DataTables CSS -->
        <link href="vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">

        <!-- DataTables Responsive CSS -->
        <link href="vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">
        <link href="vendor/morrisjs/morris.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="dist/css/sb-admin-2.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>

    <body>

        <div id="wrapper">

            <jsp:include page="admin_nav_bar.jsp"/>      

            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Teacher Management</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default"><br>



                            <!-- /.panel-heading -->
                            <div class="panel-body">

                                <!--                            -->


                                <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>Teacher ID</th>
                                            <th>Name</th>
                                            <th>Position</th>
                                            <th>Email</th>
                                            <th>Password</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                ArrayList<Teacher> teachers
                                   = (ArrayList<Teacher>) request.getAttribute("teachers");
                                  ArrayList<Account> accounts
                                   = (ArrayList<Account>) request.getAttribute("accounts");
                                                  
              for (int i = 0; i < teachers.size(); i++) {
                  Teacher t = teachers.get(i);
                   Account a = accounts.get(i);
                  out.println("<tr  class='gradeC'>");
                  out.println("<td>" +t.getTeacher_id() + "</td>");
                  out.println("<td>" +t.getName() + "</td>");
                  out.println("<td>" +t.getPosition() + "</td>");
                  out.println("<td>" +a.getEmail() + "</td>");
                  out.println("<td>********</td>");
       out.println(" <td><button type='button' class='btn btn-primary'"
                          + "  onclick=\"location.href = 'handleTeacherManagement?action=edit&id="+t.getTeacher_id() +"&aid="+a.getAccount_id()+"';\" >Edit</button>"
                                             +"<button type='button' onclick=\"location.href = 'handleTeacherManagement?action=delete&id="+t.getTeacher_id() +"&aid="+a.getAccount_id()+"';\""
                                                     + ""
                                                     + "  class='btn btn-danger'>Delete</button></td>");
                  out.println("</tr>");
              } %> 


                                    </tbody>
                                </table><br>


                                <!--                            -->
                                <!-- /.table-responsive -->
                            </div>
                            <!-- /.panel -->
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                    <!-- /.row -->

                    <!-- /.row -->

                    <!-- /.row -->
                    <!-- /.row -->
                </div>
                <!-- /#page-wrapper -->

            </div>
            <!-- /#wrapper -->

            <!-- jQuery -->
            <script src="vendor/jquery/jquery.min.js"></script>

            <!-- Bootstrap Core JavaScript -->
            <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

            <!-- Metis Menu Plugin JavaScript -->
            <script src="vendor/metisMenu/metisMenu.min.js"></script>

            <!-- Flot Charts JavaScript -->
            <script src="vendor/flot/excanvas.min.js"></script>
            <script src="vendor/flot/jquery.flot.js"></script>
            <script src="vendor/flot/jquery.flot.pie.js"></script>
            <script src="vendor/flot/jquery.flot.resize.js"></script>
            <script src="vendor/flot/jquery.flot.time.js"></script>
            <script src="vendor/flot-tooltip/jquery.flot.tooltip.min.js"></script>
            <script src="data/flot-data.js"></script>


            <!-- DataTables JavaScript -->
            <script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
            <script src="vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
            <script src="vendor/datatables-responsive/dataTables.responsive.js"></script>

            <!-- Custom Theme JavaScript -->
            <script src="dist/js/sb-admin-2.js"></script>

            <!-- Page-Level Demo Scripts - Tables - Use for reference -->
            <script>
                $(document).ready(function () {
                    $('#dataTables-example').DataTable({
                        responsive: true
                    });
                });

            </script>

    </body>

</html>
