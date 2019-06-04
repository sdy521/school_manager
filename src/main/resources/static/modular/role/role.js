 var Role = {
    tableId:"#grid-table",
    pagerId:"#grid-pager",
    table:null,
}
Role.initJqGrid = function(){
    var tableInstance = $("#grid-table").jqGrid({
        url:'/role/grid',
        datatype: "json",
        caption: "名单",
        jsonReader:{
            root: function (data) {
                return data.obj.rows;
            },   // 实际模型数据
            page: function (data) {
                return data.obj.page;
            },   // 当前页码
            total: function (data) {
                return data.obj.total;
            }, // 页码总数
            records: function (data) {
                return data.obj.records;
            }, // 数据总数
            repeatitems:false
        },
        colNames:['编号','姓名','创建时间','修改时间','操作'],
        colModel:[
            {name:'id',index:'id', width:80,align:'center',sortable:false,search:false,formatter:'integer',key:true},
            {name:'name',index:'name', width:80,align:'center',sortable:false,search:true, stype:'text'},
            {name:'createTime',index:'createTime', width:120,align:'center',sortable:false,search:false,formatter:function (cellValue) {
                    return dateFtt("yyyy-MM-dd hh:mm:ss",new Date(cellValue));
                }},
            {name:'updateTime',index:'updateTime', width:120,align:'center',sortable:false,search:false,formatter:function (cellValue) {
                    return dateFtt("yyyy-MM-dd hh:mm:ss",new Date(cellValue));
                }},
            {name:'operation',index:'operation', width:100, sortable:false,align:'center',sortable:false,search:false,formatter:function (cellValue,index,rowObject) {
                    var id = rowObject['id'];
                    var str="";
                    str +="<button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"Role.role("+id+");\">授权</button>";
                    return str;
                }}
        ],
        page: 1,    //初始页码
        rowNum:10,
        rowList:[10,20,30],
        pager: '#grid-pager',
        autowidth: true,
        height:380,
        viewrecords: true,
    });
    return tableInstance;
}
//搜索
 Role.search = function () {
    var name = $("#name").val().trim();
    var params = {};
    params.name = name;
    Role.table.setGridParam({
        url:"/role/grid",
        page:1,
        postData:params
    }).trigger("reloadGrid");
 }
 //重置
 Role.reset = function(){
    var elem = $("#name");
    elem.val("");
    var params = {};
    params.name="";
    Role.table.setGridParam({
        url:"/role/grid",
        page:1,
        postData:params
    }).trigger("reloadGrid");
 }
 //授权
 Role.role = function(id){
     $("#radiolist").empty();
   $.ajax({
       url:"/role/getRoleId?userid="+id,
       type:"GET",
       dataType:"JSON",
       success:function (r) {
           if(r.code===0){
               var data = r.obj;
               var elem = "role"+data;
               $("#radiolist").append("<div class=\"col-xs-3\" id=\"role1\">\n" +
                   "                            <input type=\"radio\" value=\"1\" name=\"roleid\">管理员\n" +
                   "                        </div>\n" +
                   "                        <div class=\"col-xs-3\" id=\"role2\">\n" +
                   "                            <input type=\"radio\" value=\"2\" name=\"roleid\" >教师\n" +
                   "                        </div>\n" +
                   "                        <div class=\"col-xs-3\" id=\"role3\">\n" +
                   "                            <input type=\"radio\" value=\"3\" name=\"roleid\" >学生\n" +
                   "                        </div>");
               $("#"+elem).find("input[type='radio']").attr("checked",true);
               // $("#"+elem).addClass('active');
               $("#userid").val(id);
               $("#roleModal").modal();
           }
       }
   });
 }
 Role.update = function(){
    var params = getFormJson($("#role-form"));
    params.userid = $("#userid").val();
    $.ajax({
        url:"/role/update",
        data:JSON.stringify(params),
        type:"POST",
        dataType:"JSON",
        contentType:"application/json;charset=utf8",
        success:function (r) {
            if(r.code===0){
                $("#roleModal").modal('hide');
                success("授权成功");
            }
        }
    });
 }
 $(function () {
    Role.table = Role.initJqGrid();
 });