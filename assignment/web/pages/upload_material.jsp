<%-- 
    Document   : upload_material
    Created on : 2017年11月20日, 下午05:48:14
    Author     : user
--%>
<%@page import="ict.bean.Module"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("role_id") == "2")
        response.sendRedirect("pages/no_permission.jsp");
%>
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
<body>

    <div id="wrapper">

        <!-- Navigation -->
        <jsp:include page="teacher_nav_bar.jsp" flush="true"/>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Add New Material</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->

            <div class="container">
                <div class="row">
                    <form class="form-horizontal" method="POST" action="upload" enctype="multipart/form-data" >
                        <fieldset>
                            <input type="hidden" name="action" value="add">
                            <!-- Form Name -->
                            <legend>Material Information</legend>
                            <jsp:useBean id="modules" scope="request" class="java.util.ArrayList" />
                            <div class="form-group" id="class_name" >
                                <label class="col-md-4 control-label" for="textinput">Module</label>
                                <div class="col-md-4">
                                    <select class="form-control" required name="module">
                                        <%
                                            for(int i=0; i<modules.size(); i++){
                                                Module m = (Module)modules.get(i);
                                                out.println("<option value="+m.getModuleID()+">"+m.getName()+"</option>");
                                            }
                                        %>
                                    </select>
                                </div>
                            </div>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="textinput">Material Name</label>
                                <div class="col-md-4">
                                    <input id="textinput"  type="text" class="form-control input-md" name="mName" required>
                                </div>
                            </div>
                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="textinput">Description</label>
                                <div class="col-md-4">
                                    <input id="textinput"  type="text" class="form-control input-md" name="des" required>
                                </div>
                            </div>
                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="textinput">Tag</label>
                                <div class="col-md-4">
                                    <input id="textinput"  type="text" class="form-control input-md" name="tag" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label" for="textinput">Restricted</label>
                                <div class="col-md-4">
                                    <input type="checkbox" class="form-control input-md" name="isR" value="yes" onclick="tend()">
                                </div>
                            </div>


                            <div class="form-group" id="end" style="display:none">
                                <label class="col-md-4 control-label" for="textinput">Expiry date</label>
                                <div class="col-md-4">
                                    <input type="datetime-local" class="form-control input-md" name="rDate" id="rDate">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label" for="textinput">File</label>
                                <div class="col-md-4">
                                    <input type="file" id="file" class="input-file" name="file" required>
                                </div>
                            </div>

                            <br>
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="textinput"> </label>
                                <div class="col-md-4">
                                    <button id="btnsave" type="submit" name="btnsave" class="btn btn-primary">Submit</button>
                                    <button id="btncancel" type="reset" name="btncancel" class="btn btn-danger">Reset</button>
                                </div>
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
        
        function tend(){
        if($("#end").css("display") == "none")
        $("#end").css("display", "block");
        else{
        $("#end").css("display", "none");
        $("#rDate").val(null);
        }
        }
    </script>
</body>
</body>
</html>
