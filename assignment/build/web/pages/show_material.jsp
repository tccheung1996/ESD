<%-- 
    Document   : show_material
    Created on : 2017年11月20日, 下午09:26:51
    Author     : user
--%>
<%@page import="ict.bean.Material, java.util.*, java.sql.Timestamp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
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

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>

    <body>

        <div id="wrapper">
            <%
                pageContext.setAttribute("role_id", Integer.parseInt(session.getAttribute("role_id").toString()));
            %>
            <!-- Navigation -->
            <c:if test="${role_id == 1}">
                <jsp:include page="teacher_nav_bar.jsp" flush="true"/>
            </c:if>
            <c:if test="${role_id == 2}">
                <jsp:include page="student_nav_bar.jsp" flush="true"/>
            </c:if>

            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <%
                            if (!(Integer.parseInt(session.getAttribute("role_id").toString()) == 2)) {
                                out.println("<h1 class=\"page-header\">Material Management</h1>");
                            } else {
                                out.println("<h1 class=\"page-header\">Download Materials</h1>");
                            }
                        %>

                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-12">
                        <center><p>${param.message}</p></center>
                                <%
                                    if (!(Integer.parseInt(session.getAttribute("role_id").toString()) == 2)) {
                                        out.println("<button id=\"add\" type=\"button\" class=\"btn btn-primary\" style=\"float:right\" onclick=\"location.href = 'MaterialController?action=upload'\">Add New Material</button><br><br>");
                                    }
                                %>
                        <div class="panel panel-default"><br>

                            <!-- /.panel-heading -->
                            <div class="panel-body">

                                <!--                            -->
                                <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>Material ID</th>
                                            <th>Module ID</th>
                                            <th>Name</th>
                                            <th>Description</th>
                                            <th>Tag</th>
                                            <th>Restricted</th>
                                            <th>Expiry Date</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <jsp:useBean id="materials" scope="request" class="java.util.ArrayList" />
                                        <%!
                                            public String convert(int num) {
                                                return (num == 0) ? "No" : "Yes";
                                            }
                                        %>

                                        <%
                                            Date date= new Date();
                                            //getTime() returns current time in milliseconds
                                            long time = date.getTime();
                                            //Passed the milliseconds to constructor of Timestamp class 
                                            Timestamp ts = new Timestamp(time);
                                            for (int i = 0; i < materials.size(); i++) {
                                                Material m = (Material) materials.get(i);
                                                out.println("<tr>");
                                                out.println("<td>" + m.getMaterialID() + "</td>");
                                                out.println("<td>" + m.getModuleID() + "</td>");
                                                out.println("<td>" + m.getName() + "</td>");
                                                out.println("<td>" + m.getDes() + "</td>");
                                                out.println("<td>" + m.getTag() + "</td>");
                                                out.println("<td>" + convert(m.getIsRestricted()) + "</td>");
                                                out.println("<td>" + m.getRestrictedDur() + "</td>");
                                                if ((Integer.parseInt(session.getAttribute("role_id").toString()) == 2)) {
                                                    if(m.getRestrictedDur() == null || ts.before(m.getRestrictedDur())){
                                                    out.println("<td><a href='MaterialController?action=dl&id=" + m.getMaterialID() + "'>Download</a></td>");
                                                    }else{
                                                    out.println("<td><a>Restricted</a></td>");
                                                    }
                                                } else {
                                                    out.println("<td><a href='MaterialController?action=dl&id=" + m.getMaterialID() + "'>Download</a><a href='MaterialController?action=edit&id=" + m.getMaterialID() + "'> <font color='green'>Edit</font></a> <a href='#' onclick='confirmDel(" +m.getMaterialID() + ")'><font color='red'>Delete</font></a></td>");
                                                }
                                                out.println("</tr>");
                                            }
                                        %>
                                </table><br>
                                
                                <script>
                                    function confirmDel(id){
                                        if(confirm("Are you sure to delete material ID: "+id+"?"))
                                            location.href = "MaterialController?action=del&id="+id;
                                    }
                                </script>

                                <!--                            -->
                                <!-- /.table-responsive -->
                            </div>
                            <!-- /.panel -->
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                    <!-- /.row -->

                    <!-- /.row -->

                    <!-- /.row -->
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