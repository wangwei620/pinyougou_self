package com.pinyougou.sellergoods.service;

import com.pinyougou.enity.PageResult;
import com.pinyougou.groupEntity.Specification;
import com.pinyougou.pojo.TbSpecification;

public interface SpecificationService {
   //分页+条件查询
    PageResult findPage(TbSpecification specification, Integer pageNum, Integer pageSize);
    //新增
   void add(Specification specification);
    //查找根据id
    Specification findOne(Long id);
    //修改
    void update(Specification specification);
    //删除
    void delete(Long[] ids);
}
