 var Menu = {
    id:"menuTable",
     table:null
}
 /**
  * 初始化表格的列
  */
 Menu.initColumn = function () {
     var columns = [
         {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle',width:'50px'},
         {title: '菜单名称', field: 'name', align: 'center', valign: 'middle', sortable: true,width:'15%'},
         {title: '菜单编号', field: 'code', align: 'center', valign: 'middle', sortable: true,width:'12%'},
         {title: '菜单父编号', field: 'pcode', align: 'center', valign: 'middle', sortable: true,width:'15%'},
         {title: '请求地址', field: 'url', align: 'center', valign: 'middle', sortable: true,width:'15%'},
         // {title: '排序', field: 'sequence', align: 'center', valign: 'middle', sortable: true},
         {title: '操作', field: 'operation', align: 'center', valign: 'middle', sortable: true, width:'15%', formatter: function(cellValue, rowObject) {
                 var id = rowObject["id"];
                 var str = "";
                 str += '<input type="button" class="btn btn-sm btn-info" value="编辑" onclick="Menu.edit(' + id + ')"/>&nbsp;';
                 str += '<input type="button" class="btn btn-sm btn-danger" value="删除" onclick="Menu.delete(' + id + ')"/>';
                 return str;
             }}
     ];
     return columns;
 };
 /**
  * 搜索
  */
 Menu.search = function () {
     var queryData = {};
     Menu.table.refresh({query: queryData});
 };
 //新增弹框
 Menu.create = function () {
     $("#createModal").modal();
 };
 var jstreeOptions = {
     core: {
         data: [],
         themes: {
             "variant" : "large"
         }
     },
     checkbox: {
         "keep_selected_style": false
     }
 };
 //jstree
 Menu.jstree = function(){
     //初始化菜单下拉树
     $.ajax({
         url: "/menu/jstree",
         type: 'GET',
         dataType: "json",
         success: function (r) {
             if (r.code === 0) {
                 jstreeOptions.core.data = r.obj;
                 //绑定点击事件
                 $("#create-menu-panel").jstree(jstreeOptions).bind("changed.jstree", function (obj, e) {
                     //input赋值
                     $("#create-menu-input").val(e.node.text);
                     $("#create-menu-input").attr("data-code", e.node.id);
                     //隐藏div
                     $("#create-menu-panel").fadeOut("fast");
                 });
             }
         }
     });
 }
 //插入
 Menu.insert = function(){
     var params = getFormJson($("#create-form"));
     params.pcode = $("#create-menu-input").attr("data-code");
     $.ajax({
         url:"/menu/insert",
         type:"POST",
         data:JSON.stringify(params),
         dataType:"JSON",
         contentType:"application/json;charset=utf8",
         success:function (r) {
             if(r.code===0){
                 success("增加成功");
                 $("#createModal").modal('hide');
                 Menu.search();
                 $("#create-form")[0].reset();
             }
         }
     });
 }
 $(function () {
    //初始化tree grid
     var table = new BSTreeTable(Menu.id, "/menu/grid", Menu.initColumn());
     table.setExpandColumn(1);
     table.init();
     Menu.table = table;
     //菜单input添加click事件
     $(".tree-input").on("click", function () {
         var panel = $(".tree-panel");
         if ($(panel).is(":hidden")) {
             $(panel).css({
                 width: $(this).outerWidth(),//外部控件宽度
                 height: "200px"
             }).slideDown("fast");
         }
     });
     //初始化jstree
     Menu.jstree();
 });