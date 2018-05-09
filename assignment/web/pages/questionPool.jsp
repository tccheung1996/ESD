<%@page import="ict.bean.QuestionBean"%>
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
            .container1 {
                display: block;
                position: relative;
                padding-left: 35px;
                margin-bottom: 12px;
                cursor: pointer;

                user-select: none;
            }

            /* Hide the browser's default checkbox */
            .container1 input {
                position: absolute;
                opacity: 0;
            }

            /* Create a custom checkbox */
            .checkmark {
                position: absolute;
                top: 0;
                left: 0;
                height: 25px;
                width: 25px;
                background-color: #eee;
            }

            /* On mouse-over, add a grey background color */
            .container1:hover input ~ .checkmark {
                background-color: #ccc;
            }

            /* When the checkbox is checked, add a blue background */
            .container1 input:checked ~ .checkmark {
                background-color: #2196F3;
            }

            /* Create the checkmark/indicator (hidden when not checked) */
            .checkmark:after {
                content: "";
                position: absolute;
                display: none;
            }

            /* Show the checkmark when checked */
            .container1 input:checked ~ .checkmark:after {
                display: block;
            }

            /* Style the checkmark/indicator */
            .container1 .checkmark:after {
                left: 9px;
                top: 5px;
                width: 5px;
                height: 10px;
                border: solid white;
                border-width: 0 3px 3px 0;
                -webkit-transform: rotate(45deg);
                -ms-transform: rotate(45deg);
                transform: rotate(45deg);
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

                            <h1 class="page-header">QUESTION POOL</h1>

                            <div align="right" style="margin-right:70px;">
                                <form action="pages/addQuestion.jsp">
                                    <%
                            
                                        String moduleID = request.getParameter("moduleID");
                                        out.print("<input type=\"hidden\" name=\"moduleID\" value=\"" + moduleID + "\">");
                                    %>
                                    <input type="submit" class="button" value="ADD QUESTION">
                                </form>

                            </div>

                            <br>
                            <br>

                            <center>
                                <form action="pages/addAssessment.jsp" >
                                    <table>
                                        <%
                                            ArrayList<QuestionBean> mbList = (ArrayList<QuestionBean>)request.getAttribute("mbList");
                                         
                                                
                                            for (int i = 0; i < mbList.size(); i++) {
                                                QuestionBean qb = mbList.get(i);
                                                
                                                out.print("<tr style=\"border-style:solid ;border-width:1px;\">");
                                                out.print("<td width=\"1000px\" style=\"padding: 10px 30px 10px 20px\">");
                                                out.print("<p>Question:" + qb.getQuestion() + "</p>");
                                                out.print("<p>Answer1:" + qb.getAnswer1() + "</p>");
                                                out.print("<p>Answer2:" + qb.getAnswer2() + "</p>");
                                                out.print("<p>Answer3:" + qb.getAnswer3() + "</p>");
                                                out.print("<p>Answer4:" + qb.getAnswer4() + "</p>");
                                                out.print("<p>Correct Answer:" + qb.getTrueAnswer() + "</p>");
                                                out.print("</td>");
                                                out.print("<td width=\"30%\">");
                                                out.print("<label class=\"container1\"> Tick for select question");
                                                out.print("<input type=\"checkbox\" value=\"" + qb.getQuestID() + "\"  name=\"question\"  >");
                                                out.print("<span class=\"checkmark\"></span>");
                                                out.print("</label>");
                                                out.print("</form>");
                                                out.print("<form action=\"handleQuestion\" method=\"post\" ><input type=\"hidden\" name=\"questID\" value=\"" + qb.getQuestID() + "\" ><input type=\"hidden\" name=\"action\" value=\"edit\" ><input type=\"submit\" class=\"button\" value=\"EDIT\" ></form>");
                                                out.print("<form action=\"handleQuestion\"><button class=\"button1\">DELETE</button><input type=\"hidden\" name=\"delete\"  value=\"" + qb.getQuestID() + "\"><input type=\"hidden\" name=\"action\" value=\"delQuestion\" ><input type=\"hidden\" name=\"moduleID\"  value=\"" + request.getParameter("moduleID") + "\"></form>");
                                                out.print("</td>");

                                                out.print("</tr>");
                                            }


                                        %>
                                    </table>
                                    <br>
                                    <% out.print("<input type=\"hidden\" name=\"moduleID\"  value=\"" + request.getParameter("moduleID") + "\">");%>
                                    <input type="submit" class="button" value="CREATE ASSESSMENT">
                                </form>

                            </center>

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
