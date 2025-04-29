<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="path.jsp"%>
<title>欢迎使用CMS606后台管理系统</title>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<!-- easyui-->
<script type="text/javascript" src="${demoPath}static/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${demoPath}static/plugins/easyui/jquery.easyui.min.js"></script>
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="${demoPath}static/plugins/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="${demoPath}static/plugins/easyui/themes/icon.css" />
<!-- UEditor -->
<!--  
<script type="text/javascript" charset="utf-8" src="${demoPath}static/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${demoPath}static/ueditor/ueditor.all.js"></script>
<link rel="stylesheet" type="text/css" href="${demoPath}static/ueditor/themes/default/dialogbase.css"/>
-->
<!-- common-->
<script type="text/javascript" src="${demoPath}static/js/util.js"></script>
<!-- cookie插件 -->
<script type="text/javascript" src="${demoPath}static/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${demoPath}static/js/jquery.form.js"></script>
<!-- markitup 插件 -->
<script type="text/javascript" src="${demoPath}static/plugins/markitup/jquery.markitup.js"></script>
<script type="text/javascript" src="${demoPath}static/plugins/markitup/sets/default/set.js"></script>
<link rel="stylesheet" type="text/css" href="${demoPath}static/plugins/markitup/skins/markitup/style.css" />
<link rel="stylesheet" type="text/css" href="${demoPath}static/plugins/markitup/sets/default/style.css" />
<!-- uploadify 上传 -->
<script src="${demoPath}static/plugins/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="${demoPath}static/plugins/uploadify/uploadify.css">
<!-- Highcharts 统计图-->
<script src="${demoPath}static/plugins/Highcharts-3.0.1/js/highcharts.js"></script>
<script src="${demoPath}static/plugins/Highcharts-3.0.1/js/highcharts-more.js"></script>
<script src="${demoPath}static/plugins/Highcharts-3.0.1/js/modules/exporting.js"></script>
<script src="${demoPath}static/plugins/Highcharts-3.0.1/js/themes/grid.js"></script>
<!-- 
<script src="${demoPath}static/js/Highstock-1.3.1/js/highstock.js"></script>
<script src="${demoPath}static/js/Highstock-1.3.1/js/modules/exporting.js"></script>
-->
<script type="text/javascript">
	//tabs
	function tabs(title, url,type) {
		if ($('#tab').tabs('exists', title)) {
			$('#tab').tabs('select', title);
		} else {
			if(type==2){ 
				//	iframe 加载方式
				$('#tab').tabs('add', {
					border : false,
					title : title,
					content : '<iframe /*scrolling="no"*/ frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>',
					closable : true
				});
			}else{ 
				//	href 加载方式
				$('#tab').tabs('add', {
					border : false,
					title : title,
					href : url,
					closable : true
				});
			}
		}
	}
	function exit() {
		$.messager.confirm('Confirm', '确定要退出吗?', function(r) {
			if (r) {
				window.location.href = "${demoPath}admin/exit.html?t=" + Math.random();
			}
		});
	}
</script>
</head>
<body id="body" class="easyui-layout">

	<!-- 上北  start-->
	<div data-options="region:'north'" style="height: 30px;overflow: visible;">
		<div style="float: left;padding: 5px;">
		  <font size="4"></font>
		</div>
		<div style="float: right;">
		   <strong style="color: red;">${sessionScope.tbsUserBean.username} 欢迎光临!</strong>  
		   <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_Menu_0'">更换皮肤</a> 
		    <div id="layout_north_Menu_0">
				<div>black</div>
				<div>bootstrap</div>
				<div>default</div>
				<div>gray</div>
				<div>metro</div>
				<div>cupertino</div>
				<div>sunny</div>
			</div>
			<a href="javascript:exit();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-back'">退出</a>
		</div>
	</div>

	<!-- 左西 start -->
	<div data-options="region:'west'" style="width: 200px;" title="功能导航">
		<div class="easyui-accordion" data-options="fit : true,border : false">
			<c:forEach var="tbsMenuModel" items="${listTbsMenuModel}">
				<c:choose>
					<c:when test="${tbsMenuModel.type==1}">
						<div title="${tbsMenuModel.name}"
							data-options="
			                 isonCls:'icon-save',
			                 tools:[{
			                     iconCls : 'icon-reload',
			                     handler : function() {
			                        $('#tree').tree('reload');
			                     }
			                 },{
			                    iconCls : 'icon-redo',
			                    handler : function() {
			                       var node = $('#tree').tree('getSelected');
			                       if(node){
			                          $('#tree').tree('expandAll', node.target);
			                       }else{
			                          $('#tree').tree('expandAll');
			                       }
			                    }
			                 },{
			                    iconCls : 'icon-undo',
			                    handler : function() {
				                    var node = $('#tree').tree('getSelected');
				                    if (node) {
				                      $('#tree').tree('collapseAll', node.target);
				                    }else{
				                      $('#tree').tree('collapseAll');
				                    }
			                    }
			                 }]
			             ">
			             	<ul id="tree" class="easyui-tree" data-options="
								url:'${tbsMenuModel.url}',
								onSelect : function(node) {
									var type = node.attributes.type;
									if (type == 'folder') {
										return;
									}
									var src = node.attributes.url;
									var args = node.attributes.path;
									var text = node.attributes.text;
									var id = node.id;
									var url = src + '?path=' + args + '&id=' + id + '&t=' + Math.random();
									tabs(text, url,${tbsMenuModel.type});
								}
							" />
			             </div>
					</c:when>
					<c:otherwise>
						<div title="${tbsMenuModel.name}">
							<c:forEach var="tbsMenuModelChild" items="${tbsMenuModel.listTbsMenuModel}">
								<ul style="margin:0px; padding:0px;">
									<li style="line-height: 160%;text-decoration: none;list-style-type:none;" onmouseover="this.style.backgroundColor='#E0ECFF'" onmouseout="this.style.backgroundColor='white'"><a onclick="tabs('${tbsMenuModelChild.name}','${tbsMenuModelChild.url}?id=${tbsMenuModelChild.id}',${tbsMenuModelChild.type})" onmouseover="this.style.color='red'" onmouseout="this.style.color='black'" style="cursor: pointer;display:block;color:black;text-decoration: none;border-bottom :1px dotted #D8D8D8;padding:3px;padding-left: 5px;">${tbsMenuModelChild.name}</a></li>
								</ul>
							</c:forEach>
						</div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
	</div>


	<!-- 中心 start-->
	<div data-options="region:'center'" title="欢迎使用CMS606管理系统" style="overflow:hidden;">
		<div id="tab" class="easyui-tabs" data-options="fit:true,border:false">
			<div title="欢迎页">
				<div style="padding-left: 10px;">
					<h2>简介</h2>
					<p><span style="padding-left: 30px;" />JeeTemp（J2ee  template）是由（<a onclick="javascript:window.open('http://www.yy606.com');" href="javascript:void(0)">咿呀网</a> 跟 <a onclick="javascript:window.open('http://www.mn606.com')" href="javascript:void(0)">美女图片</a>）开发的一款在线代码生成器。生成的代码可提供下载，可与本系统无缝集成，还提供了自定义采集，源码编辑等功能，为你节约时间
					   可帮助你解决所有的模板功能 一键生成源码JSP页面,无需你再为这里功能浪费时间，生成的代码规范，易修改，  简单方便，实用。
					   <br><span style="padding-left: 30px;" />JeeTemp 宗旨：让复杂事情简单化;抽象的功能留给你自己，提供给你简洁易修改的源码，让开发变的简单，快速，易操作。
					</p>					 	
					<h3>其他</h3>
					<ul>
					    <li>JeeTemp 采用J2EE技术 SpringMVC+Mybatis+Easyui+Mysql+velocity 框架开发而成 </li>
					    <li>JeeTemp 1.0正式版本六大技术点: <b>1.在线代码生成器</b> <b>2.自定义采集</b> <b>3.数据管理(模板)</b> <b>4.在线源码修改</b></li>
					    <li>技术点一：在线代码生成器，是基于解析SQL生成， 暂时是对mysql做了解析集成 ,根据创建的SQL语句生成对应的 entity,service,dao,controller,jsp 等模板功能一键生成直接使用</li> 
					    <li>技术点二：模板库，可以指定模板生成</li> 
					    <li>技术点三：自定义模板采集爬虫，是一款自定义采集抓取程序 ，由 httpclient 与 jsoup 开发而成，可以任意抓取， 地图页 ,列表页，内容页，的网站  </li> 
					    <li>技术点四：源码编辑可编辑 web-apps下面的任何文件，对不可编辑的扩展名进行了过滤</li> 
						<li>支持多种浏览器: IE, 火狐, Google</li> 
						<li>支持 Mysql</li>
						<li>基础权限: 用户，角色，菜单权限，按钮权限，数据权限</li>
						<li>运行环境 JDK6 Tomcat6～7</li>
					</ul>
				</div>
				<div style="padding-left: 10px;">
					<h2>技术交流</h2>
					<ul>
						<li>作者：WOLF</li>
						<li>邮箱：405645010@qq.com</li> 
						<li>交流群: 22160972, 82260139</li> 
					</ul>
				</div>
			</div>
		</div>
	</div>


	<!-- 右东 start -->
	<!-- <div data-options="region:'east',split:true" style="width:180px;">east</div> -->
	<!--     <div data-options="region:'east',iconCls:'icon-reload',split:true"
		title="Tree Menu" style="width:180px;">
		<ul class="easyui-tree" data-options="url:'tree_data.json'"></ul>
	</div> -->

	<!-- 下南 start-->
	<div data-options="region:'south'" style="overflow:hidden;">
		<!-- class="panel-header panel-header-noborder" -->
		<div class="panel-header panel-header-noborder" style="text-align: center;">
			版权所有 @ <a href="mailto:405645010@qq.com">WOLF</a>
		</div>
	</div>

</body>
</html>