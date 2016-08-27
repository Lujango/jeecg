<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>业务单</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  $(document).ready(function(){
	$('#tt').tabs({
	   onSelect:function(title){
	       $('#tt .panel-body').css('width','auto');
		}
	});
	$(".tabs-wrap").css('width','100%');
  });
 </script>
 </head>
 <body style="overflow-x: hidden;">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="busiOrderController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${busiOrderPage.id }">
					<input id="bpmStatus" name="bpmStatus" type="hidden" value="${busiOrderPage.bpmStatus }">
					<input id="createName" name="createName" type="hidden" value="${busiOrderPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${busiOrderPage.createBy }">
					<input id="updateName" name="updateName" type="hidden" value="${busiOrderPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${busiOrderPage.updateBy }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${busiOrderPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${busiOrderPage.sysCompanyCode }">
					<input id="createDate" name="createDate" type="hidden" value="${busiOrderPage.createDate }">
					<input id="updateDate" name="updateDate" type="hidden" value="${busiOrderPage.updateDate }">
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">负责人:</label>
			</td>
			<td class="value">
		     	 <input id="leader" name="leader" type="text" style="width: 150px" class="inputxt" value='${busiOrderPage.leader}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">负责人</label>
			</td>
			<td align="right">
				<label class="Validform_label">接单日:</label>
			</td>
			<td class="value">
					  <input id="orderGetDate" name="orderGetDate" type="text" style="width: 150px" 
		      						 class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" datatype="*" value='<fmt:formatDate value='${busiOrderPage.orderGetDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">接单日</label>
			</td>
		</tr>
			</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:800px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="busiOrderController.do?busiOrderSalerList&id=${busiOrderPage.id}" icon="icon-search" title="业务单测试" id="busiOrderSaler"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 附表明细 模版 -->
		<table style="display:none">
		<tbody id="add_busiOrderSaler_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
					  	<input name="busiOrderSalerList[#index#].selerName" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					  		datatype="*">
					  <label class="Validform_label" style="display: none;">卖方姓名</label>
				  </td>
				  <td align="left">
					  	<input name="busiOrderSalerList[#index#].selerTel" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"
					  		>
					  <label class="Validform_label" style="display: none;">卖方电话</label>
				  </td>
			</tr>
		 </tbody>
		</table>
 </body>
 <script src = "webpage/com/jeecg/testorder/busiOrder.js"></script>	
