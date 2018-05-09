<%@page import="ict.bean.ModuleBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.AssessmentBean"%>
<%@page import="ict.bean.QuestionBean"%>
<%@page import="ict.db.AssessmentDB"%>


<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>SB Admin 2 - Bootstrap Admin Theme</title>

        <!-- Bootstrap Core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href="vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

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

            <!-- Navigation -->
                     <jsp:include page="teacher_nav_bar.jsp"/>   

            <!-- Page Content -->
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Edit Assessment</h1>
                            <div align="right" style="margin-right:70px;">

                            </div>
                            <br>



                            <div>
                                <center>
                                    <form method="post" action="handleQuestion">
                                        <%
                                            String trueAnswer1 ="";
                                            String trueAnswer2 ="";
                                            String trueAnswer3 ="";
                                            String trueAnswer4 ="";
                                            
                                            QuestionBean qb =  (QuestionBean)request.getAttribute("qbList");
                                            out.print("<center>");
                                            out.print("");
                                            out.print("<table >");
                                                
                                          

                                            if(qb.getTrueAnswer() ==1){
                                                trueAnswer1 = "selected";
                                            }  
                                            else if(qb.getTrueAnswer() ==2){
                                                trueAnswer2 = "selected";
                                            }
                                            else if(qb.getTrueAnswer() ==3){
                                                trueAnswer3 = "selected";
                                            }
                                            else
                                                trueAnswer4 = "selected";
                                                out.print("<input type=\"hidden\" name=\"action\" value=\"editQuestion\">");
                                                out.print("<input type=\"hidden\" name=\"moduleID\" value=\"" + qb.getModuleID() + "\"required=\"true\">");
                                                out.print("<input type=\"hidden\" name=\"questID\" value=\"" + qb.getQuestID() + "\"required=\"true\">");
                                                out.print("<tr  style=\"border-style:solid ;border-width:1px;\">");
                                                out.print("<td align=\"center\" width=\"1000px\" style=\"padding: 10px 30px 10px 20px\"required=\"true\">");
                                                out.print("Question:<input type=\"text\" name=\"quest\" value=\""+ qb.getQuestion() +"\"required=\"true\"><br>");
                                                out.print("1.<input type=\"text\" name=\"answer1\" value=\""+ qb.getAnswer1() +"\"required=\"true\">");
                                                out.print("2.<input type=\"text\" name=\"answer2\" value=\""+ qb.getAnswer2() +"\"required=\"true\">");
                                                out.print("3.<input type=\"text\" name=\"answer3\" value=\""+ qb.getAnswer3() +"\"required=\"true\">");
                                                out.print("4.<input type=\"text\" name=\"answer4\" value=\""+ qb.getAnswer4() +"\"required=\"true\"><br>");
                                                out.print("True Answer<br>");
                                                out.print("<select name=\"trueAnswer\">");
                                                out.print("<option value=\"1\"" + trueAnswer1 +">1</option>");
                                                out.print("<option value=\"2\"" + trueAnswer2 +">2</option>");
                                                out.print("<option value=\"3\"" + trueAnswer3 +">3</option>");
                                                out.print("<option value=\"4\"" + trueAnswer4 +">4</option>");
                                                out.print("</select><br>");
                                                out.print("</td>");
                                                out.print("</tr>");
                                            
                                            out.print("</table>");
                                            out.print("</center>");
                                        %>
                                        
                                        
                                        <input type="submit" class="button1" value="EDIT">
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
        <script src="vendor/jquery/jquery.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

        <!-- Metis Menu Plugin JavaScript -->
        <script src="vendor/metisMenu/metisMenu.min.js"></script>

        <!-- Custom Theme JavaScript -->
        <script src="dist/js/sb-admin-2.js"></script>
        <script>
 
        </script>
    </body>

</html>
