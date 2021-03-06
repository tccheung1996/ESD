<!DOCTYPE html>
<%@ page import="java.util.ArrayList, ict.bean.*" %>
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
        <jsp:useBean id="t" scope="request" class="ict.bean.Teacher"/>
        <jsp:useBean id="a" scope="request" class="ict.bean.Account"/>

        <div id="wrapper">

            <jsp:include page="admin_nav_bar.jsp"/>      

            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Edit Teacher</h1>
                    </div>
                </div>

                <div class="container">
                    <div class="row">
                        <form class="form-horizontal" method="post" action="/assignment/handleEdit">
                            <fieldset>
                                <input type="hidden" name="action" value="edit">
                                <input type="hidden" name="role" value="Teacher">
                                <input type="hidden" name="account_id" value="<%
                int account_id = a.getAccount_id();
                out.print(account_id);    %>">

                                <input type="hidden" name="tid" value="<%
      int tid = t.getTeacher_id();
      out.print(tid+"");    %>">



                                <!-- Form Name -->
                                <legend>User Information</legend>


                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="textinput">User Name</label>
                                    <div class="col-md-4">
                                        <input id="textinput"  type="text" placeholder="your name" class="form-control input-md" name="name" value="<%
                String name = t.getName();
                out.print(name);    %>"  required>

                                    </div>
                                </div>
                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="textinput">Email</label>
                                    <div class="col-md-4">
                                        <input id="textinput"  type="text" placeholder="your email address" class="form-control input-md" name="email" value="<%String email = a.getEmail() ;
                out.print(email);
                                               %>" required>

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


                                <div class="form-group" id="position" >
                                    <label class="col-md-4 control-label" for="textinput">Position</label>
                                    <div class="col-md-4">
                                        <select class="form-control" required name="position" >
                                            <option
                                                <%String position = t.getPosition() ;
               if(position.equals("Full-time")){
                   out.print("selected");
               }
                                                %>



                                                >Full-Time</option>
                                            <option     <%
                                                
                                             
               if(position.equals("Part-Time")){
                   out.print("selected");
               }
                                                %>>Part-Time</option>

                                        </select>

                                    </div>

                                </div>







                                <div class="form-group">
                                    <label class="col-md-4 control-label">Upload Icon</label>

                                    <div class="col-md-4">

                                        <img class="img-responsive picture" src="http://www.kusalimika.ch/file/2017/02/avaar.jpg" style="height:280px;border:solid; ">
                                        <div class=" col-xs-6 " style="padding:0%"> <input id="Upload photo" name="Upload photo" class="input-file" type="file">
                                        </div>
                                        <div>
                                            <h5 class="text-bold"></h5>
                                        </div>

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
