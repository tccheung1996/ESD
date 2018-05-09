<!DOCTYPE html>
<%@ page import="java.util.ArrayList, ict.bean.*" %>

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

        <!-- DataTables CSS -->
        <link href="vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">

        <!-- DataTables Responsive CSS -->
        <link href="vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">
        <link href="vendor/morrisjs/morris.css" rel="stylesheet">

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
                        <h1 class="page-header">Student Performance in Assessment</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default"><br>
                            <div class="form-group" style="width:30%;display:inline;  ">                                
                                <form method="get" action="ShowStudentPerformance">

                                    <label style="margin-left:1%;">Assessment

                                        <select class="form-control" style="display:inline; text-align:none;color:black;text-decoration:none;font-weight:normal;" name="aid">

                                            <%
    
        String output= (String)request.getAttribute("assessmentChoices");
    out.print(output);
    System.out.print(output);
                                            %>

                                        </select></label>
                                    <input type="submit" class="btn btn-primary" value="view">
                                </form>

                            </div>

                            <br>

                            <div class="form-group" style="width:30%;display:inline;  ">



                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>Class Name</th>

                                            <th>Student ID</th>
                                            <th>Student Name</th>

                                            <th>Result</th>
                                            <th>Score</th>


                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<StudentPerformance> studentPers
                                               = (ArrayList<StudentPerformance>) request.getAttribute("studentPers");
                                              int maxMark=-1;
                                               StudentPerformance studWithMax=new StudentPerformance();
                                                studWithMax.setClassName("");
                studWithMax.setResult("");
                studWithMax.setScire("");
                studWithMax.setScore(0);
                studWithMax.setSid(0);
                studWithMax.setStudName("");
                                               
                                               
                                               
                                                  int minMark=1000;
                                               StudentPerformance studWithMin=new StudentPerformance();
                                                                          studWithMin.setClassName("");
                studWithMin.setResult("");
                studWithMin.setScire("");
                studWithMin.setScore(0);
                studWithMin.setSid(0);
                studWithMin.setStudName("");
                                               
                                               
                                               int[] allMarks = new int[studentPers.size()];
                                                     for (int i = 0; i < studentPers.size(); i++) {
               StudentPerformance sp = studentPers.get(i);
               out.println("<tr  class='gradeC'>");
                              out.println("<td>" +sp.getClassName() + "</td>");

               out.println("<td>" +sp.getSid() + "</td>");
               out.println("<td>" +sp.getStudName() + "</td>");
               out.println("<td>" +sp.getResult() + "</td>");
               String color="black";
               allMarks[i]=sp.getScore();
               if(sp.getScore()<40){
                  color="red";
               }
               if(sp.getScore()>maxMark){
                   maxMark=sp.getScore();
                   studWithMax=sp;
               }
                if(sp.getScore()<minMark){
                   minMark=sp.getScore();
                   studWithMin=sp;
               }
               out.println(" <td><span style='color:"+color+"'>" +sp.getScire() + "</span></td>");

               out.println("</tr>");
           }
                                                     double avg_mark=0;
                                                     double sum=0;
                                               for(int i=0;i<allMarks.length;i++){
                                                   sum+=allMarks[i];
                                               }
                                               avg_mark=sum/allMarks.length;
                                        %>



                                    </tbody>
                                </table><br>
                                <!-- /.table-responsive -->
                                <div class="well">
                                    <h3 style="margin-top:0px;">Summary</h3>
                                    <div class="panel panel-default">

                                        <!-- /.panel-heading -->
                                        <div class="panel-body">
                                            <div class="table-responsive">
                                                <h4>The Average Mark is <span style="color:green"></span><%=avg_mark%></h4>
                                                <br><br>
                                                <table class="table table-striped table-bordered table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th>Mark</th>
                                                            <th>Class Name</th>

                                                            <th>Student ID</th>
                                                            <th>Student Name</th>

                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <th>The Highest Mark</th>
                                                    <td style="color:green"> <%=studWithMax.getScore()+"" == null ? "" : studWithMax.getScore()+""%>

                                                    </td>
                                                    <td>
                                                        <%=studWithMax.getClassName()+"" == null ? "" : studWithMax.getClassName()+""%>

                                                    </td>

                                                    <td>
                                                        <%=studWithMax.getSid()+"" == null ? "" : studWithMax.getSid()+""%>

                                                    </td>

                                                    <td>
                                                        <%=studWithMax.getStudName()+"" == null ? "" : studWithMax.getStudName()+""%>
                                                    </td>

                                                    </tr>
                                                    <tr>
                                                        <th>The Lowest Mark</th>
                                                        <td style="color:red">
                                                            <%=studWithMin.getScore()+"" == null ? "" : studWithMin.getScore()+""%>

                                                        </td>
                                                        <td>
                                                            <%=studWithMin.getClassName()+"" == null ? "" : studWithMin.getClassName()+""%>
                                                        </td>

                                                        <td>
                                                            <%=studWithMin.getSid()+"" == null ? "" : studWithMin.getSid()+""%>
                                                        </td>
                                                        <td>
                                                            <%=studWithMin.getStudName()+"" == null ? "" : studWithMin.getStudName()+""%>
                                                        </td>

                                                    </tr>

                                                    </tbody>
                                                </table>
                                                <div class="row" style="justify-content: center;">


<!--                                                    <div class="panel panel-default">
                                                        <div class="panel-heading">
                                                            The Rate of Pass
                                                        </div>
                                                         /.panel-heading 
                                                        <div class="panel-body">
                                                            <div class="flot-chart">
                                                                <div class="flot-chart-content" id="flot-pie-chart"></div>
                                                            </div>
                                                        </div>






                                                    </div>-->


                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.panel-body -->
                            </div>
                            <!-- /.panel -->
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                    <!-- /.row -->


                </div>

            </div>
            <!-- /#wrapper -->

            <script src="vendor/jquery/jquery.min.js"></script>

            <!-- Bootstrap Core JavaScript -->
            <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

            <!-- Metis Menu Plugin JavaScript -->
            <script src="vendor/metisMenu/metisMenu.min.js"></script>

            <!-- Flot Charts JavaScript -->
            <script src="vendor/flot/excanvas.min.js"></script>
            <script src="vendor/flot/jquery.flot.js"></script>
            <script src="vendor/flot/jquery.flot.pie.js"></script>
            <script src="vendor/flot/jquery.flot.resize.js"></script>
            <script src="vendor/flot/jquery.flot.time.js"></script>
            <script src="vendor/flot-tooltip/jquery.flot.tooltip.min.js"></script>
            <script src="data/flot-data.js"></script>


            <!-- DataTables JavaScript -->
            <script src="vendor/datatables/js/jquery.dataTables.min.js"></script>
            <script src="vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
            <script src="vendor/datatables-responsive/dataTables.responsive.js"></script>

            <!-- Custom Theme JavaScript -->
            <script src="dist/js/sb-admin-2.js"></script>

            <!-- Page-Level Demo Scripts - Tables - Use for reference -->
            <script>
                $(document).ready(function () {
                    $('#dataTables-example').DataTable({
                        responsive: true
                    });
                });

            </script>

    </body>

</html>
