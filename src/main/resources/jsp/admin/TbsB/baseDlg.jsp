<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 弹出框 tbsBDlg -->
<div id="tbsBDlg">
	<form id="tbsBFm" method="post">
		<table>
			<tr>
				<td><label> 主键: </label></td>
    			<td >
					<input name="id" class="easyui-validatebox"  readonly="readonly" 
										/>
				</td>


				<td><label> 创建时间: </label></td>
    			<td >
				<input name="createTime"  class="easyui-datetimebox"
													 							/>
				</td>

	 		</tr>
	        <tr>

				<td><label> 名称: </label></td>
    			<td >
				<input name="name" class="easyui-validatebox" type="text"
			   					 />
				</td>

			</tr>

		</table>
	</form>
</div>
