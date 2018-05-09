<%@page import="ict.bean.QuestionBean"%>
<%@page import="ict.bean.AssessmentQuestionBean"%>
<%@page import="ict.bean.ModuleBean"%>
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
          <jsp:include page="student_nav_bar.jsp"/> 

            <!-- Page Content -->
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">RESULT</h1>

                            <br>
                            <br>
                            <center>
                                <form action="/assignment/studentAssessment" method="get">
                                    <table id="quest">



                                        <%
                                            int assID = Integer.parseInt(request.getParameter("assID"));
                                            int time = Integer.parseInt(request.getParameter("usedTime"));
                                            String msTime= (time/60) +":"+ (time%60);
                                            
                 
                                            ArrayList<AssessmentBean> abList = (ArrayList<AssessmentBean>)request.getAttribute("abList");
                                            AssessmentBean ab = abList.get(0);
                                            ArrayList<AssessmentQuestionBean> aqbList = (ArrayList<AssessmentQuestionBean>)request.getAttribute("aqbList");
                                            
                                            
                                            int studID = Integer.parseInt(request.getParameter("studID"));
                                            int moduleID = Integer.parseInt(request.getParameter("moduleID"));
                                            String[] numOfQuestion = request.getParameterValues("numOfQuestion");
                                            int correctAnswer = 0;
                                            for (int i = 0; i < numOfQuestion.length; i++) {
                                                if (request.getParameter(i + "") != null) {
                                                    if (request.getParameter(i + "").equals("true")) {
                                                        correctAnswer += 1;
                                                    }
                                                }
                                            }
                                            out.print("<input type=\"hidden\" name=\"assID\" value=\"" + assID + "\">");
                                            out.print("<input type=\"hidden\" name=\"studID\" value=\"" + studID + "\">");
                                            out.print("<input type=\"hidden\" name=\"moduleID\" value=\"" + moduleID + "\">");
                                            out.print("<tr style=\"border-style:solid ;border-width:1px;\">");
                                            out.print("<td width=\"700px\" align=\"center\" style=\"padding: 10px 30px 10px 20px\">");
                                            out.print("<p>Number Of Correct Answer:<h2>"+ correctAnswer+ "/"+ aqbList.size() +"</h2></p>");
                                            out.print("<p>Time Limit(MIN):"+ ab.getTimeLimit() +":00</p>");
                                            out.print("<p>Used Time:<h2>"+msTime+"</h2></p>");
                                            out.print("</td>");

                                            out.print("<input type=\"hidden\" name=\"action\" value=\"list\">");
                                            out.print("</center>");
                                            out.print("</tr>");
                                            

                                        %>
                                    </table>
                                    <input type="submit" class="button" value="CONFIRM">
                                </form>
                            </center>

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
