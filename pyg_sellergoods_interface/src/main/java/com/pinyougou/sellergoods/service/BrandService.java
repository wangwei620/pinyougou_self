package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbBrand;

import java.util.List;

public interface BrandService {
    //查找所有品牌
    public List<TbBrand> findAll();
}
