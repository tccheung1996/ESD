<%@page import="ict.bean.AssessmentQuestionBean"%>
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
                                    <form method="get" action="edit">



                                        <%
                                            String type1 ="";
                                            String type2 ="";
                                            String type3 ="";
                                            ArrayList<AssessmentBean> abList = (ArrayList<AssessmentBean>)request.getAttribute("abList");
                                            out.print("<center>");
                                            out.print("<table align=\"center\">");

                                            for (int i = 0; i < abList.size(); i++) {
                                                AssessmentBean ab = abList.get(i);
                                                
                                                if (ab.getType() == 1) {
                                                    type1 = "selected";
                                                } else if (ab.getType() == 2) {
                                                    type2 = "selected";
                                                } else {
                                                    type3 = "selected";
                                                }
                                                out.print("<input type=\"hidden\" name=\"assID\" value=\"" + ab.getAssID() + "\">");
                                                out.print("<tr style=\"border-style:solid ;border-width:1px;\">");
                                                out.print("<td width=\"1000px\" style=\"padding: 10px 30px 10px 20px\">");
                                                out.print("<b>");
                                                out.print("<p>Assessment Name:<input type=\" text \" name=\"name\" value=\""+ ab.getName() +"\"required=\"true\"></p>");
                                                out.print("<p>Assessment Type:<select name=\"type\"><option value=\"exercise\"" + type1 +"required=\"true\">Excercise</option></p>");
                                                out.print("<option value=\"test\"" + type2 +">Test</option>");
                                                out.print("<option value=\"quiz\"" + type3 +">Quiz</option></select>");
                               
                                                out.print("<p>Time Limit(MIN):<input type=\"number\" name=\"time\" value=\""+ ab.getTimeLimit() +"\"required=\"true\"></p>");
                                                
                                                
                                                out.print("<p>Max Number of Attempt:<input type=\"number\" name=\"attempt\" value=\""+ ab.getNumOfAttem() +"\"required=\"true\"></p>");
                                                out.print("<p>From Date:<input type=\"datetime-local\" required=\"true\" name=\"fDate\" value=\""+ ab.getFromDate().substring(0,10) +"T"+ ab.getFromDate().substring(11) +"\"required=\"true\"></p>");
                                                out.print("<p>To Date:<input type=\"datetime-local\" required=\"true\" name=\"tDate\" value=\""+ ab.getToDate().substring(0,10)+"T"+ ab.getToDate().substring(11) +"\"required=\"true\"></p>");
                                                out.print("<textarea name=\"des\" rows=\"4\" cols=\"50\"  placeholder=\"Description\" >"+ab.getDes()+"</textarea><br>  ");
                                                out.print("</td>");
                                                out.print("</center>");
                                                out.print("</tr>");

                                            }
                                            out.print("</table>");
                                            out.print("</center><br>");
                                        %>
                                        <table>
                                            <%
                                                ArrayList<QuestionBean> mbList = (ArrayList<QuestionBean>)request.getAttribute("mbList");
                                                ArrayList<AssessmentQuestionBean> aqbList = (ArrayList<AssessmentQuestionBean>)request.getAttribute("aqbList");
                                                String check = null;
                                            
                                                for (int i = 0; i < mbList.size(); i++) {
                                                    QuestionBean qb = mbList.get(i);
                                                    for(int j = 0 ; j < aqbList.size();j++){
                                                        AssessmentQuestionBean aqb = aqbList.get(j);
                                                    
                                                        if(aqb.getQuestionID() == qb.getQuestID()){
                                                            check = "checked";
                                                            break;
                                                        }
                                                        else
                                                            check = "";
                                                    }                                               
                                                
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
                                                    out.print("<input type=\"checkbox\" value=\"" + qb.getQuestID() + "\" "+ check +" name=\"question\"  >");
                                                    out.print("<span class=\"checkmark\"></span>");
                                                    out.print("</label>");
                                                
                                                    out.print("</td>");

                                                    out.print("</tr>");
                                                }
                                            %>
                                        </table>

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
        <script src="vendor/jquery/jquery.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

        <!-- Metis Menu Plugin JavaScript -->
        <script src="vendor/metisMenu/metisMenu.min.js"></script>

        <!-- Custom Theme JavaScript -->
        <script src="dist/js/sb-admin-2.js"></script>


    </body>

</html>
