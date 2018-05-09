<!DOCTYPE html>
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

        <!-- Custom CSS -->
        <link href="dist/css/sb-admin-2.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">


    </head>

    <body>

        <div id="wrapper">

            <jsp:include page="teacher_nav_bar.jsp"/>      

            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Your's Profile</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->

                <div class="container">
                    <div class="row">
                        <div class="col-xs-4 col-xs-offset-4 " style="">

                            <div>
                                <h5 class="text-bold"></h5>
                            </div>

                        </div>

                    </div>
                    <jsp:useBean id="t" scope="request" class="ict.bean.Teacher"/>
                    <jsp:useBean id="a" scope="request" class="ict.bean.Account"/>
                    <div class="row">
                        <div class="col-xs-4 col-xs-offset-4 text-left">
                         
                            <div class="clearfix"></div>
                            <div class="bot-border"></div>



                            <div class=" col-xs-6 tital" style="padding:0%">Name:</div>
                            <div class=" col-xs-6 " style="padding:0%"><%=t.getName()%></div>
                            <div class="clearfix"></div>
                            <div class="bot-border"></div>


                            <div class="col-xs-6 tital " style="padding:0%">Position:</div>
                            <div class="col-xs-6" style="padding:0%"><%=t.getPosition()%> Teacher</div>
                            <div class="clearfix"></div>
                            <div class="bot-border"></div>

                        </div>

                    </div>
                </div>


                <div class="container">
                    <div class="row">
                        <form class="form-horizontal" method="post" action="EditProfile">
                            <fieldset>
                                <input type="hidden" name="role" value="Teacher"/>
                                <input type="hidden" name="tid" value="<%=t.getTeacher_id()%>"/>
                                <input type="hidden" name="account_id" value="<%
                                       int account_id = t.getAccount_id();
                                       out.print(account_id);
                               
                                       %>"/>

                                <!-- Form Name -->
                                <legend>User Profile</legend>



                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="textinput">Name</label>
                                    <div class="col-md-4">
                                        <input id="textinput" type="text" placeholder="your name" name="name" class="form-control input-md" value="<%=t.getName()%>" required="">

                                    </div>
                                </div>
                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="textinput">Email</label>
                                    <div class="col-md-4">
                                        <input id="textinput" type="text" placeholder="your email address" name="email" value="<%=a.getEmail()%>" class="form-control input-md">

                                    </div>
                                </div>
                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="textinput">Password</label>
                                    <div class="col-md-4">
                                        <input id="textinput" type="password" placeholder="enter your password" name="password" class="form-control input-md">

                                    </div>
                                </div>

                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="textinput">Confirm Password</label>
                                    <div class="col-md-4">
                                        <input id="textinput"  type="password" placeholder="confirm password" name="password_again" class="form-control input-md">

                                    </div>

                                </div>

                                <br>

                                <div class="form-group">
                                    <label class="col-md-4 control-label" for="textinput"> </label>
                                    <div class="col-md-4">

                                        <input type="submit" id="btnsave"  class="btn btn-success" value="Save"/>

                                        <a href = '/assignment/showProfile' class="btn btn-danger">Reset to Default</a>





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

    </body>

</html>
