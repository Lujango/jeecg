<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!-- <h4>分类标题</h4> -->
	    <div class="row">
	      <div class="col-md-12 layout-header">
	        <button id="addBtn_HdOrderBuy" type="button" class="btn btn-default">添加</button>
	        <button id="delBtn_HdOrderBuy" type="button" class="btn btn-default">删除</button>
	        <script type="text/javascript"> 
			$('#addBtn_HdOrderBuy').bind('click', function(){   
		 		 var tr =  $("#add_hdOrderBuy_table_template tr").clone();
			 	 $("#add_hdOrderBuy_table").append(tr);
			 	 resetTrNum('add_hdOrderBuy_table');
			 	 return false;
		    });  
			$('#delBtn_HdOrderBuy').bind('click', function(){   
		       $("#add_hdOrderBuy_table").find("input:checked").parent().parent().remove();   
		        resetTrNum('add_hdOrderBuy_table');
		        return false;
		    });
		    $(document).ready(function(){
		    	if(location.href.indexOf("load=detail")!=-1){
					$(":input").attr("disabled","true");
					$(".datagrid-toolbar").hide();
				}
		    	resetTrNum('add_hdOrderBuy_table');
		    });
		</script>
	      </div>
	    </div>
<div style="margin: 0 15px; background-color: white;">    
	    <!-- Table -->
      <table id="hdOrderBuy_table" class="table table-bordered table-hover" style="margin-bottom: 0;">
		<thead>
	      <tr>
	        <th style="white-space:nowrap;width:50px;">序号</th>
	        <th style="white-space:nowrap;width:50px;">操作</th>
					  <th>
							姓名
					  </th>
					  <th>
							电话
					  </th>
					  <th>
							身份证号码
					  </th>
					  <th>
							联系地址
					  </th>
					  <th>
							征信情况
					  </th>
	      </tr>
	    </thead>
        
	<tbody id="add_hdOrderBuy_table">	
	<c:if test="${fn:length(hdOrderBuyList)  <= 0 }">
			<tr>
				<th scope="row"><div name="xh"></div></th>
				<td><input style="width:20px;" type="checkbox" name="ck"/></td>
					<input name="hdOrderBuyList[0].id" type="hidden"/>
					<input name="hdOrderBuyList[0].createName" type="hidden"/>
					<input name="hdOrderBuyList[0].createBy" type="hidden"/>
					<input name="hdOrderBuyList[0].createDate" type="hidden"/>
					<input name="hdOrderBuyList[0].updateName" type="hidden"/>
					<input name="hdOrderBuyList[0].updateBy" type="hidden"/>
					<input name="hdOrderBuyList[0].updateDate" type="hidden"/>
					<input name="hdOrderBuyList[0].sysOrgCode" type="hidden"/>
					<input name="hdOrderBuyList[0].sysCompanyCode" type="hidden"/>
					<input name="hdOrderBuyList[0].bpmStatus" type="hidden"/>
					<input name="hdOrderBuyList[0].pId" type="hidden"/>
				  <td>
					  	<input name="hdOrderBuyList[0].name" maxlength="32" 
					  		type="text" class="form-control"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">姓名</label>
					</td>
				  <td>
					  	<input name="hdOrderBuyList[0].tel" maxlength="32" 
					  		type="text" class="form-control"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">电话</label>
					</td>
				  <td>
					  	<input name="hdOrderBuyList[0].idNo" maxlength="32" 
					  		type="text" class="form-control"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">身份证号码</label>
					</td>
				  <td>
					  	<input name="hdOrderBuyList[0].addr" maxlength="32" 
					  		type="text" class="form-control"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">联系地址</label>
					</td>
				  <td>
					  	<input name="hdOrderBuyList[0].credit" maxlength="32" 
					  		type="text" class="form-control"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">征信情况</label>
					</td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(hdOrderBuyList)  > 0 }">
		<c:forEach items="${hdOrderBuyList}" var="poVal" varStatus="stuts">
			<tr>
				<th scope="row"><div name="xh">${stuts.index+1 }</div></th>
				<td><input style="width:20px;" type="checkbox" name="ck"/></td>
					<input name="hdOrderBuyList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="hdOrderBuyList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
					<input name="hdOrderBuyList[${stuts.index }].createBy" type="hidden" value="${poVal.createBy }"/>
					<input name="hdOrderBuyList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
					<input name="hdOrderBuyList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
					<input name="hdOrderBuyList[${stuts.index }].updateBy" type="hidden" value="${poVal.updateBy }"/>
					<input name="hdOrderBuyList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
					<input name="hdOrderBuyList[${stuts.index }].sysOrgCode" type="hidden" value="${poVal.sysOrgCode }"/>
					<input name="hdOrderBuyList[${stuts.index }].sysCompanyCode" type="hidden" value="${poVal.sysCompanyCode }"/>
					<input name="hdOrderBuyList[${stuts.index }].bpmStatus" type="hidden" value="${poVal.bpmStatus }"/>
					<input name="hdOrderBuyList[${stuts.index }].pId" type="hidden" value="${poVal.pId }"/>
				   <td align="left">
					  	<input name="hdOrderBuyList[${stuts.index }].name" maxlength="32" 
					  		type="text" class="form-control"  style="width:120px;"  value="${poVal.name }">
					  <label class="Validform_label" style="display: none;">姓名</label>
				   </td>
				   <td align="left">
					  	<input name="hdOrderBuyList[${stuts.index }].tel" maxlength="32" 
					  		type="text" class="form-control"  style="width:120px;"  value="${poVal.tel }">
					  <label class="Validform_label" style="display: none;">电话</label>
				   </td>
				   <td align="left">
					  	<input name="hdOrderBuyList[${stuts.index }].idNo" maxlength="32" 
					  		type="text" class="form-control"  style="width:120px;"  value="${poVal.idNo }">
					  <label class="Validform_label" style="display: none;">身份证号码</label>
				   </td>
				   <td align="left">
					  	<input name="hdOrderBuyList[${stuts.index }].addr" maxlength="32" 
					  		type="text" class="form-control"  style="width:120px;"  value="${poVal.addr }">
					  <label class="Validform_label" style="display: none;">联系地址</label>
				   </td>
				   <td align="left">
					  	<input name="hdOrderBuyList[${stuts.index }].credit" maxlength="32" 
					  		type="text" class="form-control"  style="width:120px;"  value="${poVal.credit }">
					  <label class="Validform_label" style="display: none;">征信情况</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
