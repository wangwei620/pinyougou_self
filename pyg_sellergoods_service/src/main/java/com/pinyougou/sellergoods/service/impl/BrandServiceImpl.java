package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.enity.PageResult;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.sellergoods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    @Autowired
    private TbBrandMapper brandMapper;
    //查询所有
    @Override
    public List<TbBrand> findAll() {
        return brandMapper.selectByExample(null);
    }
    //分页+条件查询
    @Override
    public PageResult findPage(TbBrand brand, Integer pageNum, Integer pageSize) {
        //分页的今天初始化
        PageHelper.startPage(pageNum,pageSize);

        TbBrandExample example = new TbBrandExample();
        TbBrandExample.Criteria criteria = example.createCriteria();
        if (brand!=null){
            String brandName = brand.getName();
            if (brandName!=null&&!"".equals(brandName)){
                //模糊查询
                criteria.andNameLike(brandName);
            }
            String firstChar = brand.getFirstChar();
            if (firstChar!=null&&!"".equals(firstChar)){
                //等值查询
                criteria.andFirstCharEqualTo(firstChar);
            }
        }
        Page pageResult = (Page) brandMapper.selectByExample(example);

        return new PageResult(pageResult.getTotal(),pageResult.getResult());
    }
    //添加
    @Override
    public void add(TbBrand brand) {
        brandMapper.insert(brand);
    }
    //通过id查找
    @Override
    public TbBrand findOne(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }
    //修改
    @Override
    public void update(TbBrand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }
    //删除
    @Override
    public void delete(Long[] ids) {
        if (ids!=null&&ids.length>0) {
            for (Long id : ids) {
                brandMapper.deleteByPrimaryKey(id);
            }
        }
    }

}
