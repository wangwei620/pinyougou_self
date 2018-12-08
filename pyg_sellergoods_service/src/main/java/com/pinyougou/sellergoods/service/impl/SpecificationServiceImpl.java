package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.enity.PageResult;
import com.pinyougou.groupEntity.Specification;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationExample;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import com.pinyougou.sellergoods.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SpecificationServiceImpl implements SpecificationService {
    @Autowired
    private TbSpecificationMapper specificationMapper;
    @Autowired
    private TbSpecificationOptionMapper specificationOptionMapper;
    @Override
    public PageResult findPage(TbSpecification specification, Integer pageNum, Integer pageSize) {
        //分页的实现
        PageHelper.startPage(pageNum,pageSize);

        TbSpecificationExample example = new TbSpecificationExample();
        TbSpecificationExample.Criteria criteria = example.createCriteria();

        //判断
        if(specification!=null){
            String specName = specification.getSpecName();
            if(specName!=null&&!"".equals(specName)){
                //模糊查询
                criteria.andSpecNameLike("%"+specName+"%");
            }
        }
        Page<TbSpecification> pageResult = (Page) specificationMapper.selectByExample(example);
        return new PageResult(pageResult.getTotal(),pageResult.getResult());
    }
    //添加的实现
    @Override
    public void add(Specification specification) {
        //获取specification对象
        TbSpecification specification1 = specification.getSpecification();
        specificationMapper.insert(specification1);
        //获取specification的id 值
        Long id = specification1.getId();
        //循环添加
        List<TbSpecificationOption> specificationOptions = specification.getSpecificationOptions();
        for (TbSpecificationOption specificationOption : specificationOptions) {
            //关联id
            specificationOption.setSpecId(specification1.getId());
            specificationOptionMapper.insert(specificationOption);
        }
    }

    @Override
    public Specification findOne(Long id) {
        //首先获取组合实体类
        Specification specification = new Specification();
        //获取规格数据
        TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
        specification.setSpecification(tbSpecification);
        //获取规格id
        Long id1 = tbSpecification.getId();

        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id1);
        List<TbSpecificationOption> specificationOptions =  specificationOptionMapper.selectByExample(example);
        specification.setSpecificationOptions(specificationOptions);
        return specification;
    }

    @Override
    public void update(Specification specification) {
        //修改规格名称
        TbSpecification spec = specification.getSpecification();
        specificationMapper.updateByPrimaryKey(spec);
        Long id = spec.getId();
        //获取规格选项
        List<TbSpecificationOption> specificationOptions = specification.getSpecificationOptions();
        //先删除出,后提交数据
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        specificationOptionMapper.deleteByExample(example);
        for (TbSpecificationOption specificationOption : specificationOptions) {
            //设置id
            specificationOption.setSpecId(id);
            //添加
            specificationOptionMapper.insert(specificationOption);
        }
    }
    //删除
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            //删除id选项
            specificationMapper.deleteByPrimaryKey(id);
            //删除规格
            TbSpecificationOptionExample example = new TbSpecificationOptionExample();
            TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
            criteria.andSpecIdEqualTo(id);
            specificationOptionMapper.deleteByExample(example);
        }
    }
}
