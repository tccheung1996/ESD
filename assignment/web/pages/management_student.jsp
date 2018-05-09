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



    </head>

    <body>

        <div id="wrapper">

            <jsp:include page="admin_nav_bar.jsp"/>      

            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Student Management</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default"><br>
                            <label style="margin-left:1%;display:none;" id="add_class">
                                <form style="border:solid; border-style:ridge; padding:10px;margin-top:3px;padding-right:0px;">

                                    New Class Name:
                                    <input type="text" placeholder="" class="form-control input-md" required="" style="width:80px;">
                                    Class Level:<br>
                                    <select class="form-control" style="display:inline; text-align:none;color:black;text-decoration:none;font-weight:normal;width:80px;">

                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>


                                    </select>
                                    <button type="button" class="btn btn-success" style="margin-left:1%;margin-top:6px;">Add New Class</button>

                                </form>

                            </label>






                            <!-- /.panel-heading -->
                            <div class="panel-body">

                                <!--                            -->


                                <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>Student ID</th>
                                            <th>Class Name</th>

                                            <th>Name</th>

                                            <th>Email</th>
                                            <th>Password</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <%
                                                ArrayList<Student> students
                                                   = (ArrayList<Student>) request.getAttribute("students");
                                                  ArrayList<Account> accounts
                                                   = (ArrayList<Account>) request.getAttribute("accounts");
                                                    ArrayList<StudentClass> studentClasses
                                                   = (ArrayList<StudentClass>) request.getAttribute("studentClasses");
               for (int i = 0; i < students.size(); i++) {
                   Student s = students.get(i);
                    Account a = accounts.get(i);
                     StudentClass sc = studentClasses.get(i);
       
                   out.println("<tr  class='gradeC'>");
                   out.println("<td>" +s.getStudentID() + "</td>");
                   out.println("<td>" +sc.getClass_name() + "</td>");
                   out.println("<td>" +s.getName() + "</td>");
                   out.println("<td>" +a.getEmail() + "</td>");
                   out.println("<td>********</td>");
                  out.println(" <td><button type='button' class='btn btn-primary'"
                          + "  onclick=\"location.href = 'handleStudentManagement?action=edit&id="+s.getStudentID() +"&aid="+a.getAccount_id()+"';\" >Edit</button>"
                                             +"<button type='button' onclick=\"location.href = 'handleStudentManagement?action=delete&id="+s.getStudentID() +"&aid="+a.getAccount_id()+"';\" "
                                                     + "class='btn btn-danger'>Delete</button></td>");
                   out.println("</tr>");
               } %>

                                    </tbody>
                                </table><br>

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


            <script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
            <script src="vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
            <script src="vendor/datatables-responsive/dataTables.responsive.js"></script>

            <!-- Custom Theme JavaScript -->
            <script src="dist/js/sb-admin-2.js"></script>

            <script>
                $(document).ready(function () {
                    $('#dataTables-example').DataTable({
                        responsive: true
                    });

                    var i = 0;
                    $('#add_class_btn').click(function () {
                        i++;

                        if (i % 2 == 0) {

                            $('#add_class').hide(300);


                        } else {
                            $('#add_class').show(300);


                        }


                    });
                });

            </script>

    </body>

</html>
