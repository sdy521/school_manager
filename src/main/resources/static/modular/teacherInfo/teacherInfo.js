 var TeacherInfo = {
    tableId:"#grid-table",
    table:null,
}
TeacherInfo.initJqGrid = function(){
    var tableInstance = $("#grid-table").jqGrid({
        url:'/teacher_info/grid',
        postData:{type:1},
        datatype: "json",
        caption: "老师信息",
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
        colNames:['编号','姓名','性别','创建时间','修改时间','操作'],
        colModel:[
            {name:'id',index:'id', width:80,align:'center',sortable:true,search:false,formatter:'integer',key:true},
            {name:'name',index:'name', width:80,align:'center',sortable:false,search:true, stype:'text'},
            {name:'sex',index:'sex', width:80,align:'center',sortable:false,search:true, stype:'text',formatter:function (cellValue) {
                    if(cellValue==1){
                        return '男';
                    }else {
                        return '女';
                    }
                }},
            {name:'create_time',index:'create_time', width:120,align:'center',sortable:false,search:false,formatter:function (cellValue) {
                    return dateFtt("yyyy-MM-dd hh:mm:ss",new Date(cellValue));
                }},
            {name:'update_time',index:'update_time', width:120,align:'center',sortable:false,search:false,formatter:function (cellValue) {
                    return dateFtt("yyyy-MM-dd hh:mm:ss",new Date(cellValue));
                }},
            {name:'operation',index:'operation', width:130, sortable:false,sortable:false,search:false,formatter:function (cellValue,index,rowObject) {
                    var id = rowObject['id'];
                    var deleted = rowObject['deleted'];
                    var str="";
                    str +="<button type=\"button\" class=\"btn btn-success btn-sm\" style='width: 80px;' onclick=\"TeacherInfo.detailInfo("+id+");\">详情</button>&nbsp;&nbsp;";
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
 TeacherInfo.search = function () {
    var name = $("#teacherName").val().trim();
    var sex = $("#sex").val();
    var params = {};
    params.name = name;
    params.sex = sex;
    params.type=1;
    TeacherInfo.table.setGridParam({
        url:"/teacher_info/grid",
        page:1,
        postData:params
    }).trigger("reloadGrid");
 }
 //重置
 TeacherInfo.reset = function(){
    $("#teacherName").val("");
    $("#sex").val("");
    var params = {};
    params.type=1;
    params.name="";
    params.sex="";
    TeacherInfo.table.setGridParam({
        postData:params
    }).trigger("reloadGrid");
 }
 //详情
 TeacherInfo.detailInfo = function(id){
    var elem = $("#detail-form");
    $.ajax({
        url:"/teacher_info/detail?userid="+id,
        type:"GET",
        dataType:"JSON",
        success:function (r) {
            if(r.code===0){
                var data = r.obj;
                if(data.img==undefined){
                    elem.find(".img-circle").attr('src','/static/img/5.jpg');
                }else{
                    elem.find(".img-circle").attr('src','/imgPath/'+data.img);
                }
                elem.find("input[name='name']").val(data.name);
                if(data.sex==0){
                    elem.find("input[name='sex']").val('女');
                }else {
                    elem.find("input[name='sex']").val('男');
                }
                elem.find("input[name='age']").val(data.age);
                elem.find("input[name='phone']").val(data.phone);
                elem.find("input[name='address']").val(data.address);
                $("#detailsModal").modal();
            }
        }
    });
 }
 //下载信息excel
 TeacherInfo.downloadExcel = function () {
    window.open("/excel/export");
 }
 $(function () {
    TeacherInfo.table = TeacherInfo.initJqGrid();
 });