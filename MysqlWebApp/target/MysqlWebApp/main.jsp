<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>富士施乐云印量管理主界面</title>
<link type="text/css" rel="Stylesheet" href="http://keleyi.com/keleyi/phtml/jqmenu/2/keleyidock.css" />
<script type="text/javascript" src="http://keleyi.com/keleyi/pmedia/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<ul id="dock">
<li id="links">
<ul class="free">
<li class="header"><a href="#" class="dock-keleyi-com">固定</a><a href="#" class="undock">隐藏</a>链接</li>
<li><a href="http://keleyi.com/menu/cms/" >CMS</a></li><li><a href="http://keleyi.com/menu/net/" >.NET</a></li><li><a href="http://keleyi.com/menu/javascript/" >Javascript</a></li><li><a href="http://keleyi.com/menu/jquery/" >jQuery</a></li><li><a href="http://keleyi.com/menu/csharp/" >C#</a></li><li><a href="http://keleyi.com/menu/aspnet/" >ASP.NET</a></li><li><a href="http://keleyi.com/menu/mvc/" >MVC</a></li><li><a href="http://keleyi.com/menu/html5/" >HTML5</a></li>
<li><a href="http://keleyi.com/menu/webqd/" >web前端</a></li><li><a href="http://keleyi.com/menu/sqlserver/" >SQL Server</a></li><li><a href="http://keleyi.com/menu/cpp/" >C++</a></li><li><a href="http://keleyi.com/menu/hbyy/" >汇编语言</a></li><li><a href="http://keleyi.com/menu/flhz/" >分类汇总</a></li><li><a href="http://keleyi.com/menu/other/" >其他</a></li><li><a href="http://keleyi.com/list/">最新文章</a></li><li><a href="http://keleyi.com/ziliao/js/zzbds.htm">JS正则表达式</a></li>
</ul>
</li>
<li id="files">
<ul class="free">
<li class="header"><a href="#" class="dock-keleyi-com">固定</a><a href="#" class="undock">隐藏</a>游戏</li>
<li><a href="http://keleyi.com/game/1/">HTML5捕鱼游戏</a></li>
<li><a href="http://keleyi.com/game/2/">不上不下</a></li>
<li><a href="http://keleyi.com/game/3/">打地鼠</a></li>
<li><a href="http://keleyi.com/keleyi/phtml/silverlight/">猜数字</a></li>
<li></li>
</ul>
</li>
</ul>
<div id="content">
<h1>keleyi.com</h1>
<h2>jQuery高级可停靠侧边栏</h2>
<p>
把光标移动到左侧按钮上，相应的侧边栏会自动弹出。
</p>
<p>
当光标离开栏目区域时，侧边栏自动隐藏。
</p>
<p>
侧边栏弹出时，点击“固定”链接可以使相应的栏目固定，不自动隐藏。
</p>
<p>
点击“隐藏”链接可以是相应栏目隐藏。
</p>
</div>

</body>




<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
<head>
    <title>Model</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="Content-language" content="zh-CN" />
    <style type="text/css" media="all">
        div { width:80%;margin:auto; }
        table { margin:15px 0; }
        th, tr { width:20%; }/* 修改这里，如果表格有三列就是33.333%，四列就是25% */
        span { display:block;line-height:100%; }/* 使点击整个单元格都有效果 */
        table input { width:98%; }
        table a { float:left; margin:0 3px; }
    </style>
<script type="text/javascript">
//<![CDATA[
var JCRUD=function(tb,colnum,saveAllBtn,add,ajaxSaver,allAjaxSaver,ajaxDeler){
    var del = tb.getElementsByTagName('a');
    var span = tb.getElementsByTagName('span');
    var ctr=[];/* 保存修改的tr对象 */
    var delEvent = function(){
        var dder = this.parentNode.parentNode;
        this.data=[];
        for(var i=0; i<dder.children.length-1; i++)
            this.data[i] = dder.children[i].children[0].firstChild.nodeValue;
        var tag = 0;
        for(var j=0; j<this.data.length; j++){
            if(this.data[j]!=='null'){/* 如果修改了单元格的默认值，这里也做相应修改 */
                tag=1;
                break;
            }
        }
        for(var k=0; k<ctr.length; k++) if(ctr[k]===dder) ctr.splice(k,1);
        dder.parentNode.removeChild(dder);
        if(tag==1) ajaxDeler.call(this);
    };
    var spanEvent = function(){/* 点击生成修改框 */
        var value = this.firstChild.nodeValue;
        var input = document.createElement('input');
        input.value = value;
        this.parentNode.appendChild(input);
        this.parentNode.removeChild(this);
        input.focus();
        input.onblur = function(){/* 失去焦点移除修改框 */
            var span = document.createElement('span');
            span.appendChild(document.createTextNode(this.value?this.value:'null'));/* 如果修改了单元格的默认值，这里也做相应修改 */
            span.onclick =spanEvent;
            this.parentNode.appendChild(span);
            this.parentNode.removeChild(this);
            if(value!=this.value){/* 如果内容改变生成保存按钮 */
                var tr = span.parentNode.parentNode
                    tds = tr.children;
                    btns = tds[colnum-1].getElementsByTagName('a');
                for(var i=0; i<btns.length; i++){
                    if(btns[i].firstChild.nodeValue!='保存'){
                        var saver = document.createElement('a');
                        saver.href="javascript:;";
                        saver.appendChild(document.createTextNode('保存'));
                    }else{
                        var saver = btns[i];
                    }
                }
                tds[tds.length-1].appendChild(saver);
                var tag=0;
                for(var k=0; k<ctr.length; k++)
                    if(ctr[k]===tr) tag=1;
                if(tag==-0) ctr.push(tr);
                saver.onclick=function(){/* 添加保存处理事件 */
                    this.data = [];
                    for(var i=0; i<tds.length-1; i++)
                        this.data[i] = this.parentNode.parentNode.children[i].children[0].firstChild.nodeValue;
                    ajaxSaver.call(this);
                    for(var i=ctr.length-1; i>=0; i--){
                        if(this.parentNode.parentNode===ctr[i]){
                            ctr.splice(i,1);
                        }
                    }
                    this.parentNode.removeChild(this);
                };
            }
        }
    };
    for(var i in del) del[i].onclick = delEvent;/* 给现在有元素添加事件 */
    for(var j in span) span[j].onclick = spanEvent;
    add.onclick = function(){
        var tbody = tb.children[0];
        var tr = document.createElement('tr');
        for(var j=0; j<colnum; j++){
            var td = document.createElement('td');
            if(j==(colnum-1)){
                var del = document.createElement('a');
                del.href='javascript:;';
                del.appendChild(document.createTextNode('删除'));
                del.onclick = delEvent;/* 给新加元素添加事件 */
                td.appendChild(del);
            }else{
                var span = document.createElement('span');
                span.appendChild(document.createTextNode('null'));/* 如果在添加时修改默认值，在这里修改的 */
                td.appendChild(span);
                span.onclick =spanEvent;
            }
            tr.appendChild(td);
        }
        tbody.appendChild(tr);
    };
    var getAllData = function(){/* 保存全部的数据解析 */
        var allData=[];
        for(var i=0; i<ctr.length; i++){
            allData[i]=[];
            for(var j=0; j<ctr[i].children.length-1; j++)
                allData[i].push(ctr[i].children[j].children[0].firstChild.nodeValue);
            ctr[i].children[colnum-1].removeChild(ctr[i].children[colnum-1].children[1]);
        }
        ctr=[];
        return allData;
    };
    saveAllBtn.onclick = function(){/* 添加保存全部数据保存事件 */
        this.allData = getAllData();
        if(this.allData.length){
            allAjaxSaver.call(this);
        }else{
            alert('No data!');
        }
    };
    window.onbeforeunload = function(){/* 刷新提示保存数据 */
        if(ctr.length){
            var y = confirm('数据还未保存，是否保存数据？')
            if(y){
                saveAllBtn.click();
            }
        }
    };
};
window.onload = function(){
    var table = document.getElementById('tb'),/* 要操作的表格 */
        colnum = 5,/* 这里修改表格的列数 */
        saveAllBtn = document.getElementById('SaveAll'),/* 保存全部的按钮 */
        addBtn = document.getElementById('Add'),/* 添加的按钮 */
        saver = function(){
            /* 此处可以加上ajax效果与数据库交互 data是个数组，需要可以改成JSON */
            alert('要传的数据为data数据："'+this.data+'"此处调用ajax实现后台保存！实现略……');
        },
        allSaver = function(){
            /* 此处可以加上ajax效果与数据库交互 data是个数组，需要可以改成JSON */
            alert('要传的数据为allDtat数组："'+this.allData+'"此处调用ajax实现后台保存！实现略……');
        },
        deler = function(){
            /* 此处可以加上ajax效果与数据库交互 data是个数组，需要可以改成JSON */
            alert('要传的数据为data数据："'+this.data+'"此处调用ajax实现后台删除！实现略……');
        };
    window.JCRUD(table,colnum,saveAllBtn,addBtn,saver,allSaver,deler);
};
//]]>
</script>
</head>
<body>
    <div>
        <table width="100%" border="1" id="tb">
            <tr><!-- 修改这里有多少列就加多少个th元素 -->
                <th>A</th>
                <th>B</th>
                <th>C</th>
                <th>D</th>
                <th>操作</th>
            </tr>
            <!-- 下面为数据的格式的例子，只有这种格式才能保证与js的正确交互 -->
            <!--
            <tr>
                <td><span>null</span></td>
                <td><span>null</span></td>
                <td><span>null</span></td>
                <td><a href="javascript:;">Del</a></td>
            </tr>
            -->
        </table>
        <input type="button" id="SaveAll" value="保存全部" />
        <input type="button" id="Add" value="添加" />

    </div>
</body>
</html>







































</html>