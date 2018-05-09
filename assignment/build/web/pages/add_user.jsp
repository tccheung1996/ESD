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
            <jsp:include page="admin_nav_bar.jsp"/>      

            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Add New User</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>

                <div class="container">
                    <div class="row">
                        <form class="form-horizontal" method="get" action="/assignment/handleEdit">
                            <fieldset>
                                <input type="hidden" name="action" value="add">
                                <!-- Form Name -->
                                <legend>User Information</legend>

                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="textinput">User Name</label>
                                    <div class="col-md-4">
                                        <input id="textinput"  type="text" placeholder="your name" class="form-control input-md" name="name"  required>

                                    </div>
                                </div>
                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="textinput">Email</label>
                                    <div class="col-md-4">
                                        <input id="textinput"  type="email" placeholder="your email address" class="form-control input-md" name="email"  required>

                                    </div>
                                </div>
                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="textinput">Password</label>
                                    <div class="col-md-4">
                                        <input id="textinput"  type="password" placeholder="enter your password" class="form-control input-md" name="password" required>

                                    </div>
                                </div>

                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="textinput">Confirm Password</label>
                                    <div class="col-md-4">
                                        <input id="textinput"  type="password" placeholder="confirm password" class="form-control input-md" name="password_again" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="textinput">Role</label>
                                    <div class="col-md-4">
                                        <select class="form-control" id="role" required name="role"  >
                                            <option selected>Student</option>
                                            <option>Teacher</option>
                                            <option>Administrator</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group" id="position" style="display:none;">
                                    <label class="col-md-4 control-label" for="textinput">Position</label>
                                    <div class="col-md-4">
                                        <select class="form-control" required name="position">
                                            <option>Full-Time</option>
                                            <option>Part-Time</option>

                                        </select>

                                    </div>

                                </div>
                                <div class="form-group" id="class_name" >
                                    <label class="col-md-4 control-label" for="textinput">Class Name</label>
                                    <div class="col-md-4">
                                        <select class="form-control" required name="class_id">
                                            <option value="1">1A</option>
                                            <option value="2">1B</option>
                                            <option value="3">2A</option>
                                            <option value="4">2B</option>

                                        </select>

                                    </div>

                                </div>




                                <br>

                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="textinput"> </label>
                                    <div class="col-md-4">
                                        <button id="btnsave" name="btnsave" class="btn btn-primary">Submit</button>
                                        <button id="btncancel" name="btncancel" class="btn btn-danger">Reset</button>
                                    </div>

                                </div>




                            </fieldset>

                        </form>


                    </div>

                </div>




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
