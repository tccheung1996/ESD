<%-- 
    Document   : upload_material
    Created on : 2017年11月20日, 下午05:48:14
    Author     : user
--%>
<%@page import="ict.bean.Module"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
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

    <!-- Morris Charts CSS -->
    <link href="vendor/morrisjs/morris.css" rel="stylesheet">

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

        <!-- Navigation -->
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
                    <h1 class="page-header">Material</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <%
                if (!(Integer.parseInt(session.getAttribute("role_id").toString())==2)) {
                    out.println("<button id=\"add\" type=\"button\" class=\"btn btn-primary\" style=\"float:right\" onclick=\"location.href = 'MaterialController?action=upload'\">Add New Material</button><br><br>");
                }
            %>
            <div class="container">
                <div class="row">
                    <form class="form-horizontal" method="POST" action="../upload" enctype="multipart/form-data" >
                        <fieldset>
                            <input type="hidden" name="action" value="add">
                            <!-- Form Name -->
                            <legend>Select a module</legend>
                            <jsp:useBean id="modules" scope="request" class="java.util.ArrayList" />
                            <div class="form-group" id="class_name" >
                                <div class="col-md-4">
                                    <%
                                        for (int i = 0; i < modules.size(); i++) {
                                            Module m = (Module) modules.get(i);
                                            out.println("<center><br><a href=MaterialController?action=listMM&id=" + m.getModuleID() + ">" + m.getName() + "</a></center><hr><br>");
                                        }
                                    %>
                                </div>
                            </div>
                            <br>                        
                            </div>
                            <center><p>${param.message}</p></center>
                            <c:remove var="msg" scope="session" /> 
                        </fieldset>
                    </form>
                </div>
            </div>
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

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>
    <script type="application/javascript">
        $(document).ready(function() {
        $("#role").change(function() {
        if ($(this).val() == "Student") {
        $("#class_name").show();
        $("#position").hide();
        } else if ($(this).val() == "Teacher") {
        $("#class_name").hide();
        $("#position").show();
        } else if ($(this).val() == "Administrator") {
        $("#class_name").hide();
        $("#position").hide();
        }
        });
        });

    </script>
</body>
</html>
