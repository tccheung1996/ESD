<%@page import="ict.bean.AssessmentAssignBean"%>
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

            <jsp:include page="student_nav_bar.jsp"/>  
            <%

                int assID = Integer.parseInt(request.getParameter("assID"));
                ArrayList<AssessmentBean> abList = (ArrayList<AssessmentBean>)request.getAttribute("abList");
                AssessmentBean ab = abList.get(0);

                int studID = Integer.parseInt(request.getParameter("studID"));
                ArrayList<AssessmentAssignBean> aabList = (ArrayList<AssessmentAssignBean>)request.getAttribute("aabList");
                if (aabList.size() != 0) {
                    AssessmentAssignBean aab = aabList.get(aabList.size() - 1);

                    if (aab.getNumOfAttem() == ab.getNumOfAttem() ||aab.getNumOfAttem() > ab.getNumOfAttem()) {
                        out.print("<script> alert(\"You are over the number of attempt of this assessment!\"); </script>");
                        out.print("<script> window.location.replace(\"/assignment/studentAssessment?action=list&studID=" + studID + "&moduleID=" + request.getParameter("moduleID") + "\"); </script>");
                    }
                }


            %>
            <!-- Page Content -->
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header"><%= ab.getName()%></h1>
                            <div>Time left = <span id="timer"></span></div>

                            <br>
                            <br>
                            <center>
                                <form id="myForm" action="assignAssessment" method="post">
                                    <table >


                                        <input type="hidden" name="usedTime" id="usedTime" value="">

                                        <%

                                     
                                            out.print("<input type=\"hidden\" name=\"assID\" value=\"" + request.getParameter("assID") + "\">");
                                            out.print("<input type=\"hidden\" name=\"studID\" value=\"" + request.getParameter("studID") + "\">");
                                            out.print("<input type=\"hidden\" name=\"moduleID\" value=\"" + request.getParameter("moduleID") + "\">");
                                            
                                            ArrayList<QuestionBean> qbList = (ArrayList<QuestionBean>)request.getAttribute("qbList");
                                            
                                            
                                            for (int i = 0; i < qbList.size(); i++) {
                                                
                                                QuestionBean qb = qbList.get(i);
                                                String isTrue1 = "";
                                                String isTrue2 = "";
                                                String isTrue3 = "";
                                                String isTrue4 = "";

                                                if (String.valueOf(qb.getTrueAnswer()).equals("1")) {
                                                    isTrue1 = "true";
                                                }
                                                if (String.valueOf(qb.getTrueAnswer()).equals("2")) {
                                                    isTrue2 = "true";
                                                }
                                                if (String.valueOf(qb.getTrueAnswer()).equals("3")) {
                                                    isTrue3 = "true";
                                                }
                                                if (String.valueOf(qb.getTrueAnswer()).equals("4")) {
                                                    isTrue4 = "true";
                                                }
                                                out.print("<tr  >");
                                                out.print("<td colspan=\"2\" width=\"1000px\" style=\"padding-left:300px\">");
                                                out.print("Question:" + qb.getQuestion() + "<br>");
                                                out.print("<input type=\"hidden\" value=\"\" name=\"numOfQuestion\" >");
                                                out.print("</td>");

                                                out.print("</tr>");
                                                out.print("<tr  >");
                                                out.print("<td align=\"right\" width=\"50%\">");
                                                out.print("<input type=\"radio\" value=\"" + isTrue1 + "\" name=\"" + i + "\" >");
                                                out.print("</td>");
                                                out.print("<td align=\"left\" width=\"50%\">");
                                                out.print(qb.getAnswer1() + "<br>");
                                                out.print("</td>");
                                                out.print("</tr>");
                                                out.print("<tr  >");
                                                out.print("<td align=\"right\" width=\"50%\">");
                                                out.print("<input type=\"radio\" value=\"" + isTrue2 + "\" name=\"" + i + "\" >");
                                                out.print("</td>");
                                                out.print("<td align=\"left\" width=\"50%\">");
                                                out.print(qb.getAnswer2() + "<br>");
                                                out.print("</td>");
                                                out.print("</tr>");
                                                out.print("<tr >");
                                                out.print("<td align=\"right\" width=\"50%\">");
                                                out.print("<input type=\"radio\" value=\"" + isTrue3 + "\"  name=\"" + i + "\" >");
                                                out.print("</td>");
                                                out.print("<td align=\"left\" width=\"50%\">");
                                                out.print(qb.getAnswer3() + "<br>");
                                                out.print("</td>");
                                                out.print("</tr>");
                                                out.print("<tr  >");
                                                out.print("<td align=\"right\" width=\"50%\">");
                                                out.print("<input type=\"radio\" value=\"" + isTrue4 + "\" name=\"" + i + "\" >");
                                                out.print("</td>");
                                                out.print("<td align=\"left\" width=\"50%\">");
                                                out.print(qb.getAnswer4() + "<br>");
                                                out.print("</td>");
                                                out.print("</tr>");
                                            }

                                        %>
                                    </table>
                                    <input type="submit" class="button" value="SUBMIT">
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
            <script>
                var myVar = setInterval(myTimer, 1000);
                var d = 0;
                function myTimer() {
                    d += 1;
                    document.getElementById("usedTime").value = d;
                }

                document.getElementById('timer').innerHTML =
                <%= ab.getTimeLimit() %> + ":" + 00;
                startTimer();

                function startTimer() {
                    var presentTime = document.getElementById('timer').innerHTML;
                    var timeArray = presentTime.split(/[:]+/);
                    var m = timeArray[0];
                    var s = checkSecond((timeArray[1] - 1));
                    if (s == 59) {
                        m = m - 1
                    }
                    if (m < 0) {
                        alert('times up')
                        document.getElementById("myForm").submit();
                    }

                    document.getElementById('timer').innerHTML =
                            m + ":" + s;
                    setTimeout(startTimer, 1000);
                }

                function checkSecond(sec) {
                    if (sec < 10 && sec >= 0) {
                        sec = "0" + sec
                    }
                    ; // add zero in front of numbers < 10
                    if (sec < 0) {
                        sec = "59"
                    }
                    ;
                    return sec;
                }
            </script>
    </body>

</html>
