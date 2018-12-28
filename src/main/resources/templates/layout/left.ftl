<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav metismenu" id="side-menu">
            <li class="nav-header">
                <div class="dropdown profile-element"> <span>
                            <img alt="image" class="img-circle" src="/static/img/a1.jpg" width="80px;"/>
                             </span>
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">管理员<b class="caret"></b></strong>
                             </span></span> </a>
                    <ul class="dropdown-menu animated fadeInRight m-t-xs">
                        <li><a href="mailbox.html">修改</a></li>
                        <li class="divider"></li>
                        <li><a href="login.ftl">退出</a></li>
                    </ul>
                </div>
                <div class="logo-element">
                    <#--<small>学生管理</small>-->
                        <i class="fa fa-paper-plane" aria-hidden="true" style="font-size: 35px;"></i>
                </div>
            </li>
            <#--<li class="active">
                <a><i class="fa fa-th-large"></i> <span class="nav-label">教师管理</span> <span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li><a href="/teacher_nameList/list">教师名单</a></li>
                </ul>
            </li>-->
            <#list menus as menu>
                <#assign children = menu.children />
                    <li class="active">
                        <a><i class="fa fa-th-large"></i> <span class="nav-label">${menu.name}</span> <span class="fa arrow"></span></a>
                        <#if children??&&(children?size>0)>
                            <#list children as child>
                                <ul class="nav nav-second-level">
                                    <li><a href="${child.url}">${child.name}</a></li>
                                </ul>
                            </#list>
                        </#if>
                    </li>
            </#list>
            <#--<li>
                <a><i class="fa fa-sun-o"></i> <span class="nav-label">系统设置</span> <span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li><a href="/menu/list">菜单设置</a></li>
                </ul>
            </li>-->
        </ul>

    </div>
</nav>