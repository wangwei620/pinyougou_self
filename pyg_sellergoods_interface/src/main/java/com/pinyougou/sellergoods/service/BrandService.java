package com.pinyougou.sellergoods.service;

import com.pinyougou.enity.PageResult;
import com.pinyougou.pojo.TbBrand;

import java.util.List;

public interface BrandService {
    //查找所有品牌
    public List<TbBrand> findAll();
    //条件查询+分页
    PageResult findPage(TbBrand brand, Integer pageNum, Integer pageSize);
    //添加
    void add(TbBrand brand);
    //通过id查找
    TbBrand findOne(Long id);
    //修改
    void update(TbBrand brand);
    //删除
    void delete(Long[] ids);
}
