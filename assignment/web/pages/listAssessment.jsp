<%@page import="ict.bean.*"%>
<%@page import="java.util.ArrayList"%>

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
                            <h1 class="page-header">ASSESSMENT</h1>

                            <br>
                            <br>

                            <center>




                                <%

                                    int moduleID = Integer.parseInt(request.getParameter("moduleID"));
                                    int times;
                                    ArrayList<AssessmentBean> abList = (ArrayList<AssessmentBean>)request.getAttribute("abList");
                                    ArrayList<ArrayList<AssessmentAssignBean>> aabList2 = (ArrayList<ArrayList<AssessmentAssignBean>>)request.getAttribute("aabList2");
                                    out.print("<input type=\"hidden\" name=\"moduleID\" value=\"" + moduleID + "\">");
                                    out.print("<center>");
                                    out.print("<table>");
                                    String type;
                                    for (int i = 0; i < abList.size(); i++) {
                                        AssessmentBean ab = abList.get(i);
                                        ArrayList<AssessmentAssignBean> aabList = aabList2.get(i);
                                        
                                        if(aabList.size() == 0){
                                            times =0;
                                        }
                                        else{
                                                
                                            AssessmentAssignBean aab = aabList.get(aabList.size()-1);
                                            times = aab.getNumOfAttem();
                                        }
                                                    
                                            
                                        if (ab.getType() == 1) {
                                            type = "Exercise";
                                        } else if (ab.getType() == 2) {
                                            type = "Test";
                                        } else {
                                            type = "Quiz";
                                        }

                                        out.print("<tr style=\"border-style:solid ;border-width:1px;\">");
                                        out.print("<td style=\"padding: 10px 30px 10px 20px\">");
                                        out.print("<b>");
                                        out.print("<p>Assessment Name:" + ab.getName() + "</p>");
                                        out.print("<p>Assessment Type:" + type + "</p>");
                                        out.print("<p>Time Limit(MIN):" + ab.getTimeLimit() + "</p>");
                                        out.print("<p>Max Number of Attempt:"+ times+"/" + ab.getNumOfAttem() + "</p>");
                                        out.print("<p>From Date:" + ab.getFromDate() + "</p>");
                                        out.print("<p>To Date:" + ab.getToDate() + "</p><br>");
                                        if(aabList.size() == 0 ){
                                            
                                            
                                            
                                        }
                                        else{
                                            out.print("<p>History:</p>");
                                            for(int j=0 ; j< aabList.size();j++){
                                                AssessmentAssignBean aab = aabList.get(j);
                                                String msTime= (aab.getCurrentTime()/60) +"m:"+ (aab.getCurrentTime()%60+"s");
                                                out.print("used time:"+msTime+"<br>");
                                            }
                                        }
                                        
                                        out.print("</b>");
                                        out.print("<center>");
                                        out.print("</form>");
                                        out.print("<form method=\"post\" action=\"studentAssessment\"><button class=\"button\">DO</button><input type=\"hidden\" name=\"assID\"  value=\"" + ab.getAssID() + "\"><input type=\"hidden\" name=\"moduleID\"  value=\"" + ab.getModuleID() + "\"><input type=\"hidden\" name=\"action\"  value=\"do\"><input type=\"hidden\" name=\"studID\"  value=\"" + request.getParameter("studID") + "\"></form>");
                                        out.print("</center>");
                                        out.print("</td>");
                                       
                                        out.print("</tr>");
                                            
                                            
                                            
                                    }
                                    out.print("</table>");
                                    out.print("</center>");
                                %>





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
