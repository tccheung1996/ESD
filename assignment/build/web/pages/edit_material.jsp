<%-- 
    Document   : upload_material
    Created on : 2017年11月20日, 下午05:48:14
    Author     : user
--%>
<%@page import="ict.bean.*, java.sql.Timestamp, java.util.Date, java.text.SimpleDateFormat,java.lang.StringBuilder"%>
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
                    <h1 class="page-header">Edit Material</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->

            <div class="container">
                <div class="row">
                    <form class="form-horizontal" method="GET" action="editm" enctype="multipart/form-data" >
                        <fieldset>
                            <input type="hidden" name="action" value="add">
                            <!-- Form Name -->
                            <legend>Material Information</legend>
                            <jsp:useBean id="modules" scope="request" class="java.util.ArrayList" />
                            <jsp:useBean id="material" scope="request" class="ict.bean.Material" />
                                                        <div class="form-group">
                                <label class="col-md-4 control-label" for="textinput">MaterialID</label>
                                <div class="col-md-4">
                                    <%
                                        out.print("<input type='text' readonly class='form-control input-md' name='mID' value='"+material.getMaterialID()+"'>");
                                    %>
                                </div>
                            </div>
                            
                            <div class="form-group" id="class_name" >
                                <label class="col-md-4 control-label" for="textinput">Module</label>
                                <div class="col-md-4">
                                    <select class="form-control" required name="module">
                                        <%
                                            for(int i=0; i<modules.size(); i++){
                                                Module m = (Module)modules.get(i);
                                                String se = (material.getModuleID() == m.getModuleID())?"selected":"";
                                                out.println("<option value='"+m.getModuleID()+"' "+se+">"+m.getName()+"</option>");
                                            }
                                        %>
                                    </select>
                                </div>
                            </div>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="textinput">Material Name</label>
                                <div class="col-md-4">
                                    <%
                                        out.println("<input id=\"textinput\"  type=\"text\" class=\"form-control input-md\" name=\"mName\" required value='"+material.getName()+"'/>");
                                    %>
                                </div>
                            </div>
                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="textinput">Description</label>
                                <div class="col-md-4">
                                    <%
                                    out.println("<input id=\"textinput\" type=\"text\" class=\"form-control input-md\" name=\"des\" required value='"+material.getDes()+"'/>");
                                    %>
                                </div>
                            </div>
                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="textinput">Tag</label>
                                <div class="col-md-4">
                                    <%
                                        out.println("<input id=\"textinput\"  type=\"text\" class=\"form-control input-md\" name=\"tag\" required value='"+material.getTag()+"'/>");
                                    %>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label" for="textinput">Restricted</label>
                                <div class="col-md-4">
                                    <%
                                    String ch = (material.getIsRestricted() == 1)?"checked":"";
                                    out.println("<input type=\"checkbox\" class=\"form-control input-md\" name=\"isR\" value=\"yes\" onclick='tend()'"+ch+">");     
                                    %>
                                </div>
                            </div>

                            <%
                            Timestamp ts = material.getRestrictedDur();
                            String dis = (ts == null)?"none":"block";
                            out.println("<div class=\"form-group\" id=\"end\" style=display:"+dis+">");
                            %>
                            <label class="col-md-4 control-label" for="textinput">Expiry date</label>
                            <div class="col-md-4">
                                <%if(ts!=null){
                                Date date = new Date();
                                date.setTime(ts.getTime());
                                String fd = new SimpleDateFormat("yyyy-MM-ddhh:mm").format(date);
                                StringBuilder str = new StringBuilder(fd);
                                str.insert(10,"T");
                                out.println("<input type=\"datetime-local\" class=\"form-control input-md\" name='rDate' id='rDate' value='"+str+"'>");
                                }else{
                                out.println("<input type=\"datetime-local\" class=\"form-control input-md\" name='rDate' id='rDate'>");
                                }
                                %>
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
