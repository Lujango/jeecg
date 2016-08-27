package com.jeecg.testorder.service;
import com.jeecg.testorder.entity.BusiOrderEntity;
import com.jeecg.order.entity.BusiOrderSalerEntity;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;

public interface BusiOrderServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(BusiOrderEntity busiOrder,
	        List<BusiOrderSalerEntity> busiOrderSalerList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(BusiOrderEntity busiOrder,
	        List<BusiOrderSalerEntity> busiOrderSalerList);
	public void delMain (BusiOrderEntity busiOrder);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(BusiOrderEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(BusiOrderEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(BusiOrderEntity t);
}
