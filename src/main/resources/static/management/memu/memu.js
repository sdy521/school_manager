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
 $(function () {
//初始化tree grid
     var table = new BSTreeTable(Menu.id, "/menu/grid", Menu.initColumn());
     table.setExpandColumn(1);
     table.setIdField("id");
     table.setCodeField("code");
     table.setParentCodeField("pcode");
     table.setExpandAll(false);
     table.init();
     Menu.table = table;
 });