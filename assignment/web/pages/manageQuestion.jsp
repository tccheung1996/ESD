<%@page import="ict.bean.ModuleBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.AssessmentBean"%>
<%@page import="ict.db.AssessmentDB"%>
<%@page errorPage="error_page" %>
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


        <style>
            .button {
                background-color: #008CBA;
                border: none;
                color: white;
                padding: 15px 32px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
            }

            .button1 {
                background-color: #DF0101;
                border: none;
                color: white;
                padding: 15px 32px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
            }
            #mainselection select {
                border: 0;
                color: #000;
                background: transparent;
                font-size: 20px;
                font-weight: bold;
                padding: 2px 10px;
                width: 378px;
                *width: 350px;
                *background: #58B14C;
                -webkit-appearance: none;
            }

            #mainselection {
                overflow:hidden;
                width:350px;
                -moz-border-radius: 9px 9px 9px 9px;
                -webkit-border-radius: 9px 9px 9px 9px;
                border-radius: 9px 9px 9px 9px;
                box-shadow: 1px 1px 11px #330033;
                background: #58B14C url("http://i62.tinypic.com/15xvbd5.png") no-repeat scroll 319px center;
            }
        </style>
    </head>

    <body>

        <div id="wrapper">

            <!-- Navigation -->
            <jsp:include page="teacher_nav_bar.jsp"/>  

            <!-- Page Content -->
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">

                            <h1 class="page-header">QUESTION POOL</h1>
                            <form action="/assignment/handleAssessment" method="get">
                                <br>
                                <br>

                                <center>
                                    <b>Please select the module</b>
                                    <div id="mainselection">
                                        <input type="hidden" name="action" value="listQuestion">
                                        <select name="moduleID">
                                            <%
                                                ArrayList<ModuleBean> mbList =    (ArrayList<ModuleBean>)(request.getAttribute("mbList"));
                                            
                                                for (int i = 0; i < mbList.size(); i++) {
                                                    ModuleBean mb = mbList.get(i);
                                                    out.print("<option  value=\"" + mb.getModuleID() + "\">" + mb.getName() + "</option>");
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <br>
                                    <input type="submit" class="button" value="SUBMIT">
                                </center>
                            </form>

                        </div>
                        <!-- /.col-lg-12 -->
                    </div>


                    <!-- /.row -->
                </div>
                <!-- /.container-fluid -->
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
