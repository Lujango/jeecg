
package com.jeecg.hdbus.page;
import com.jeecg.hdbus.entity.HdOrderMainEntity;
import com.jeecg.buy.entity.HdOrderBuyEntity;
import com.jeecg.sell.entity.HdOrderSellEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;

/**   
 * @Title: Entity
 * @Description: 业务单主表
 * @author onlineGenerator
 * @date 2016-08-29 21:20:42
 * @version V1.0   
 *
 */
public class HdOrderMainPage implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	private java.util.Date updateDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**流程状态*/
	private java.lang.String bpmStatus;
	/**接单时间*/
    @Excel(name="接单时间",format = "yyyy-MM-dd")
	private java.util.Date orderGetTime;
	/**业务单主管*/
    @Excel(name="业务单主管")
	private java.lang.String leader;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人名称
	 */
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司
	 */
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程状态
	 */
	public java.lang.String getBpmStatus(){
		return this.bpmStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程状态
	 */
	public void setBpmStatus(java.lang.String bpmStatus){
		this.bpmStatus = bpmStatus;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  接单时间
	 */
	public java.util.Date getOrderGetTime(){
		return this.orderGetTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  接单时间
	 */
	public void setOrderGetTime(java.util.Date orderGetTime){
		this.orderGetTime = orderGetTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务单主管
	 */
	public java.lang.String getLeader(){
		return this.leader;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务单主管
	 */
	public void setLeader(java.lang.String leader){
		this.leader = leader;
	}

	/**保存-买方信息*/
    @ExcelCollection(name="买方信息")
	private List<HdOrderBuyEntity> hdOrderBuyList = new ArrayList<HdOrderBuyEntity>();
		public List<HdOrderBuyEntity> getHdOrderBuyList() {
		return hdOrderBuyList;
		}
		public void setHdOrderBuyList(List<HdOrderBuyEntity> hdOrderBuyList) {
		this.hdOrderBuyList = hdOrderBuyList;
		}
	/**保存-卖方信息*/
    @ExcelCollection(name="卖方信息")
	private List<HdOrderSellEntity> hdOrderSellList = new ArrayList<HdOrderSellEntity>();
		public List<HdOrderSellEntity> getHdOrderSellList() {
		return hdOrderSellList;
		}
		public void setHdOrderSellList(List<HdOrderSellEntity> hdOrderSellList) {
		this.hdOrderSellList = hdOrderSellList;
		}
}
