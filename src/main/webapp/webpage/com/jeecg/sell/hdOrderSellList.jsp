<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addHdOrderSellBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delHdOrderSellBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addHdOrderSellBtn').bind('click', function(){   
 		 var tr =  $("#add_hdOrderSell_table_template tr").clone();
	 	 $("#add_hdOrderSell_table").append(tr);
	 	 resetTrNum('add_hdOrderSell_table');
	 	 return false;
    });  
	$('#delHdOrderSellBtn').bind('click', function(){   
      	$("#add_hdOrderSell_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_hdOrderSell_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
		//将表格的表头固定
	    $("#hdOrderSell_table").createhftable({
	    	height:'300px',
			width:'auto',
			fixFooter:false
			});
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addHdOrderSellBtn" href="#">添加</a> <a id="delHdOrderSellBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="hdOrderSell_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						姓名
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						电话
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						身份证号码
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						联系地址
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						征信情况
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						风险等级
				  </td>
	</tr>
	<tbody id="add_hdOrderSell_table">
	<c:if test="${fn:length(hdOrderSellList)  > 0 }">
		<c:forEach items="${hdOrderSellList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
					<input name="hdOrderSellList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="hdOrderSellList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
					<input name="hdOrderSellList[${stuts.index }].createBy" type="hidden" value="${poVal.createBy }"/>
					<input name="hdOrderSellList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
					<input name="hdOrderSellList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
					<input name="hdOrderSellList[${stuts.index }].updateBy" type="hidden" value="${poVal.updateBy }"/>
					<input name="hdOrderSellList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
					<input name="hdOrderSellList[${stuts.index }].sysOrgCode" type="hidden" value="${poVal.sysOrgCode }"/>
					<input name="hdOrderSellList[${stuts.index }].sysCompanyCode" type="hidden" value="${poVal.sysCompanyCode }"/>
					<input name="hdOrderSellList[${stuts.index }].bpmStatus" type="hidden" value="${poVal.bpmStatus }"/>
					<input name="hdOrderSellList[${stuts.index }].pId" type="hidden" value="${poVal.pId }"/>
				   <td align="left">
					  	<input name="hdOrderSellList[${stuts.index }].name" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.name }">
					  <label class="Validform_label" style="display: none;">姓名</label>
				   </td>
				   <td align="left">
					  	<input name="hdOrderSellList[${stuts.index }].tel" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.tel }">
					  <label class="Validform_label" style="display: none;">电话</label>
				   </td>
				   <td align="left">
					  	<input name="hdOrderSellList[${stuts.index }].idNo" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.idNo }">
					  <label class="Validform_label" style="display: none;">身份证号码</label>
				   </td>
				   <td align="left">
					  	<input name="hdOrderSellList[${stuts.index }].addr" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.addr }">
					  <label class="Validform_label" style="display: none;">联系地址</label>
				   </td>
				   <td align="left">
					  	<input name="hdOrderSellList[${stuts.index }].credit" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.credit }">
					  <label class="Validform_label" style="display: none;">征信情况</label>
				   </td>
				   <td align="left">
					  	<input name="hdOrderSellList[${stuts.index }].risk" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.risk }">
					  <label class="Validform_label" style="display: none;">风险等级</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
