/**
 * 为了复习起见，笔者尽量详细说明
 */

function delF(id){
    if(confirm("是否确认删除？")){
        window.location.href='del?id='+id;
    }
}

function page(pageNO){
    window.location.href='index?pageNo='+pageNO;
}

//window是整个浏览器页面，其中的document指的是我们看到的地址下的部分，history为历史记录，lication地址栏
window.onload = function () {//页面加载即可调用方法
var tbl = document.getElementById("tbl_fruit");
var r = tbl.rows; //获取表中的所有行，js是弱类型，故var可以是任何类型，这里是数组类型

for (var i =1;i < r.length ;i++){
     var tr = r[i];//每一行中的每一列
     r[r.length -1].cells[1].onclick = showForm;//form是隐藏的，点击添加才能显示
     tr.onmouseover = showColor;//鼠标经过每一行 每一行都会变色，这里注意，等号右边只写了方法名，不带括号，只起了绑定事件的作用
     tr.onmouseout = goOut;//鼠标离开，变色的行恢复原来的颜色
     var cells = tr.cells;
     var weight = cells[2];
     weight.onmouseover = showHand;//变光标的形状为手势
     weight.onclick = editWeight; //点击即可编辑
     cells[4].onclick = del;//删除某一行
     form_fruit.submit.onclick = insertData;//提交表单后，将数据插入表格
}
}
function showForm() {
    if (event && event.srcElement && event.srcElement.tagName == "TD"){
   document.getElementById("div_add").style.display = "block";//点击添加即可显示表单

}
}
function insertData() {
    var tbl =  document.getElementById("tbl_fruit")
    var row = tbl.insertRow(tbl.rows.length - 3);//表table中的insertRow()用来动态插入一行

    for(var i = 0;i < 5;i++){
        row.insertCell(i);//给新增的行插入列
    }
    var fruitName =  form_fruit.fruitName.value;//根据form的Name属性获取元素的value
    var fruitPrice =  form_fruit.fruitPrice.value;
    var fruitWeight =  form_fruit.fruitWeight.value;
    row.cells[0].innerText = fruitName;
    row.cells[1].innerText = fruitPrice;
    row.cells[2].innerText = fruitWeight;
    var img = document.createElement("img");//插入图片，为了用户良好的体验，图片是摆设，点击最后一个表格就会生效
    img.src = "imgs/del.jpg";
    row.cells[4].appendChild(img);
    updateP(row);//更新小计以及总计
    row.cells[4].onclick = del;//是否删除
}

function del(){
    if (window.confirm('确定删除'+event.srcElement.parentElement.cells[0].innerText+'了吗？亲亲~~')){

        // if (event && event.srcElement && event.srcElement.tagName=="IMG"){
            var tr =  event.srcElement.parentElement;
            var tbl = document.getElementById("tbl_fruit");
            tbl.deleteRow(tr.rowIndex);//删除某一行
            updateZJ();
        //}


    }

}


function editWeight() {
if (event && event.srcElement && event.srcElement.tagName == "TD"){
    var td = event.srcElement;
    if(td.firstChild && td.firstChild.nodeType == 3){ //必须判断，否则第二次判断的时候，firstChild是innerHtml并没有innerText 就不会出现结果，因为拿到的东西是空的
        var text = td.innerText ;
        td.innerHTML = "<input type='text' size='4'>"
        var input = td.firstChild;
        if (input.tagName == "INPUT"){
            input.value = text;
            input.select();//默认选中数据，方便用户修改
            input.onblur = update;//失去焦点时调用update()
        }

    }

}
}

function update() {
    if (event && event.srcElement && event.srcElement.tagName == "INPUT"){
      var input = event.srcElement;
      var value = input.value;
      var td = input.parentElement;
      td.innerText = value;
      updateP(td.parentElement);
    }
}

function updateP(tr) {
if (tr && tr.tagName == "TR"){
    var tds = tr.cells;
   var price =  tds[1].innerText;
   var weight =  tds[2].innerText;

   var sum = (parseFloat(price) * parseFloat(weight)).toFixed(2);

   tds[3].innerText = sum;
   updateZJ();
}
}

function updateZJ() {
var tbl = document.getElementById("tbl_fruit");
var sum = 0;
var rows = tbl.rows;
for (var i = 1;i<rows.length - 2;i++){
    var price = rows[i].cells[3].innerText;
    sum += parseFloat(price);
}
rows[rows.length - 2].cells[1].innerText = sum.toFixed(2);
}


function showColor() {
if (event && event.srcElement && event.srcElement.tagName == "TD"){
    var td = event.srcElement;
    var tr = td.parentElement;
    tr.style.backgroundColor = "#808080";
}
}

function goOut() {
    if (event && event.srcElement && event.srcElement.tagName == "TD") {
        var td = event.srcElement;
        var tr = td.parentElement;
        tr.style.backgroundColor = "transparent";
    }
}

function showHand() {
    if (event && event.srcElement && event.srcElement.tagName == "TD"){
        var td = event.srcElement;
       td.style.cursor = "hand";
    }
}
