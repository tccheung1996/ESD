<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.AssessmentBean"%>
<%@page import="ict.db.AssessmentDB"%>


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
        <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href="../vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="../dist/css/sb-admin-2.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="../vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
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
                background-color: #04B431;
                border: none;
                color: white;
                padding: 15px 32px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
            }
            .button2 {
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

        </style>
    </head>

    <body>

        <div id="wrapper">

           <jsp:include page="teacher_nav_bar.jsp"/>  

            <!-- Page Content -->
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Add Question</h1>
                            <div align="right" style="margin-right:70px;">

                            </div>
                            <br>
                            <div>
                                <center>
                                    <form method="post" action="../addQuestion">
                                        <%
                                        String moduleID = request.getParameter("moduleID");
                                        out.print("<input type=\"hidden\" name=\"moduleID\" value=\"" + moduleID + "\">");
                                        %>
                                        <table id="quest">


                                            <tr  style="border-style:solid ;border-width:1px;">
                                                <td align="center" width="1000px" style="padding: 10px 30px 10px 20px">
                                                    Question:<input type="text" name="quest"><br>
                                                    1.<input type="text" name="answer1" required="true">
                                                    2.<input type="text" name="answer2" required="true">
                                                    3.<input type="text" name="answer3" required="true">
                                                    4.<input type="text" name="answer4" required="true"><br>
                                                    True Answer<br>
                                                    <select name="trueAnswer">
                                                        <option value="1">1</option>
                                                        <option value="2">2</option>
                                                        <option value="3">3</option>
                                                        <option value="4">4</option>
                                                    </select><br>
                                                    <button type="button"  class="button2">CANCEL</button><br>
                                                </td>
                                            </tr>

                                        </table>

                                        <button type="button" class="button" id="moreQuest">ADD QEUSTION</button><br>

                                        <input type="submit" class="button1" value="SUBMIT">
                                    </form>

                                </center>
                            </div>
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
        <script src="../vendor/jquery/jquery.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="../vendor/bootstrap/js/bootstrap.min.js"></script>

        <!-- Metis Menu Plugin JavaScript -->
        <script src="../vendor/metisMenu/metisMenu.min.js"></script>

        <!-- Custom Theme JavaScript -->
        <script src="../dist/js/sb-admin-2.js"></script>
        <script>
            $("#moreQuest").click(function () {
                var text = "<tr  style=\"border-style:solid ;border-width:1px;\">\n\
                                <td align=\"center\" width=\"1000px\" style=\"padding:10px 30px 10px 20px\">Question:\n\
                                    <input type=\"text\" name=\"quest\"required=\"true\"><br>1.<input type=\"text\" name=\"answer1\"required=\"true\">\n\
                                    2.<input type=\"text\" name=\"answer2\"required=\"true\">3.\n\
                                    <input type=\"text\" name=\"answer3\"required=\"true\">\n\
                                    4.<input type=\"text\" name=\"answer4\"required=\"true\">\n\
                                    <br>True Answer<br>\n\
                                    <select name=\"trueAnswer\">\n\
                                        <option value=\"1\">1</option>\n\
                                        <option value=\"2\">2</option>\n\
                                        <option value=\"3\">3</option>\n\
                                        <option value=\"4\">4</option>\n\
                                    </select><br>\n\
                                    <button type=\"button\" class=\"button2\">CANCEL</button>\n\
                                    <br>\n\
                                </td>\n\
                            </tr>"
                $("#quest").append(text);
            });
           
            $(document).on("click",".button2",function(){
                $(this).parent().parent().remove();
            });

        </script>
    </body>

</html>
