//iframe的页面路径获取
var mytitle;
function getsrc(node){
    mytitle = node.title;
    var myiframe = document.getElementById("myiframe");
    myiframe.src = mytitle;
    console.log(myiframe.src)
}