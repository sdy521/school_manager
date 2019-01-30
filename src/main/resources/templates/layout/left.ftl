<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav metismenu" id="side-menu">
            <li class="nav-header">
                <div class="dropdown profile-element">
                    <span>
                        <a href="javascript:;" onclick="upImage();">
                            <img alt="image" class="img-circle" src="${userImg}" width="80px;"/>
                        </a>
                     </span>
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">${leftname}<b class="caret"></b></strong>
                             </span></span> </a>
                    <ul class="dropdown-menu animated fadeInRight m-t-xs">
                        <li><a href="javascripty:;" onclick="leftModel();"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a></li>
                        <li class="divider"></li>
                        <li><a href="/logout"><i class="fa fa-sign-out"></i>&nbsp;退出</a></li>
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
                    <li class="${menu.active?string('active','')}">
                        <a><i class="${menu.icon}"></i> <span class="nav-label">${menu.name}</span> <span class="fa arrow"></span></a>
                        <#if children??&&(children?size>0)>
                            <#list children as child>
                                <ul class="nav nav-second-level">
                                    <li class="${child.active?string('active','')}"><a href="${child.url}"><i class="${child.icon}"></i>${child.name}</a></li>
                                </ul>
                            </#list>
                        </#if>
                    </li>
            </#list>
        </ul>

    </div>
</nav>
<#--修改弹框-->
<div class="modal fade" id="leftModal" tabindex="-1" role="dialog" aria-labelledby="modalTitle" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modalTitle">修改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="left-form">
                    <#--<input type="hidden" name="userid" value="${userid}"/>-->
                    <div class="form-group">
                        <label class="col-sm-3 control-label">名称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="name">
                        </div>
                    </div>
                    <#--<div class="form-group">
                        <label class="col-sm-3 control-label">密码</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="password">
                        </div>
                    </div>-->
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-primary" onclick="update();">确定</button>
                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<#--上传图片-->
<div class="modal fade" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="modalTitle" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modalTitle">上传头像</h4>
            </div>
            <div class="modal-body">
               <form enctype="multipart/form-data">
                   <div class="file-loading">
                       <input id="uploadfile" type="file" multiple>
                   </div>
               </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-default" onclick="destroyDropZone();">关闭</button>
            </div>
        </div>
    </div>
</div>