








<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/assignment/MainPage?role=t">E-learning system</a>
    </div>

    <ul class="nav navbar-top-links navbar-right">




        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
                <li><a href="/assignment/showProfile"><i class="fa fa-user fa-fw"></i> User Profile</a>
                </li>

                <li class="divider"></li>
                <li><a href="/assignment/LogoutController"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                </li>
            </ul>
        </li>
    </ul>
    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse">
            <ul class="nav" id="side-menu">
                <li class="sidebar-search">
                    <div class="input-group custom-search-form">
                        <input type="text" class="form-control" placeholder="Search...">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                <i class="fa fa-search"></i>
                            </button>
                        </span>
                    </div>
                    <!-- /input-group -->
                </li>
                <li>
                    <a href="/assignment/MainPage?role=t"><i class="fa fa-dashboard fa-fw"></i> Main</a>
                </li>

                <li>
                    <a href="#"><i class="fa fa-wrench fa-fw"></i> Manage Material<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="MaterialController?action=list">Show all material</a>
                        </li>
                        <li>
                            <a href="MaterialController?action=listM">Show material by module</a>
                        </li>
                    </ul>
                </li>
               <li>
                    <a href="/assignment/ShowManageAssessment"><i class="fa fa-dashboard fa-fw"></i> Show assessment by module</a>
                </li>
                <li>
                    <a href="/assignment/ShowStudentPerformance"><i class="fa fa-dashboard fa-fw"></i> Student Performance</a>
                </li>
               <li>
                    <a href="/assignment/ShowManageQuestion"><i class="fa fa-dashboard fa-fw"></i> Manage Question Pool</a>
                </li>
                </li>


            </ul>
        </div>
    </div>

</nav>












