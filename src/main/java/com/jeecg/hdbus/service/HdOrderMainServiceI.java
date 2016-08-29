package com.jeecg.hdbus.service;
import com.jeecg.hdbus.entity.HdOrderMainEntity;
import com.jeecg.buy.entity.HdOrderBuyEntity;
import com.jeecg.sell.entity.HdOrderSellEntity;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;

public interface HdOrderMainServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(HdOrderMainEntity hdOrderMain,
	        List<HdOrderBuyEntity> hdOrderBuyList,List<HdOrderSellEntity> hdOrderSellList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(HdOrderMainEntity hdOrderMain,
	        List<HdOrderBuyEntity> hdOrderBuyList,List<HdOrderSellEntity> hdOrderSellList);
	public void delMain (HdOrderMainEntity hdOrderMain);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(HdOrderMainEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(HdOrderMainEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(HdOrderMainEntity t);
}
