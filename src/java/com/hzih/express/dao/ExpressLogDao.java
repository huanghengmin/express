package com.hzih.express.dao;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;
import com.hzih.express.domain.ExpressLog;
import com.hzih.express.domain.Region;

import java.util.Date;
import java.util.List;

public interface ExpressLogDao extends BaseDao {
	/**
	 * 分页查询
	 * 
	 */
	public PageResult listLogsByParams(int pageIndex, int pageLength,Date startDate, Date endDate, String idCard, String name,String shapeCode,String type,String phone,String express_code);

	public PageResult listLogsByParams(int pageIndex, int pageLength,Date startDate, Date endDate, String idCard, String name,String shapeCode,String type,String phone,long id);

	public void add(ExpressLog expressLog);

	void remove(ExpressLog expressLog);

	ExpressLog find(String type_ocr);

	ExpressLog findById(long id);

	PageResult listLogsByParamsIDCardDistinct(int i, int limit, Date startDate, Date endDate, String idCard, String name, String shapeCode, String type, String phone, String express_code);

	boolean modifyAll();

	List<String> findByDistinctIdCard();

	boolean updateByIdCard(int status,String code,String xq,String idCard);

	PageResult listLogsByGAParams(int i, int limit, Date startDate, Date endDate, String idCard, String name, String shapeCode, String type, String phone, Region region,String express_code);














}
