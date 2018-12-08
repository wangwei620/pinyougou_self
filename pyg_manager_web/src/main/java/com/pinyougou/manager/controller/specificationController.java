package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.enity.PageResult;
import com.pinyougou.enity.Result;
import com.pinyougou.groupEntity.Specification;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.sellergoods.service.SpecificationService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//这个是Controller和ResponseBody
@RequestMapping("/specification")
public class specificationController {

    @Reference
    private SpecificationService specificationService;

    //查询分页
    @RequestMapping("/search")
    public PageResult findPage(@RequestBody TbSpecification specification, Integer pageNum, Integer pageSize){
        return specificationService.findPage(specification,pageNum,pageSize);

    }
    //添加
    @RequestMapping("/add")
    public Result add(@RequestBody Specification specification){
        try {
            specificationService.add(specification);
            return new Result(true,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
    }
    //通过id查找
    @RequestMapping("/findOne")
    public Specification findOne(Long id){
        return specificationService.findOne(id);
    }
    //修改
    @RequestMapping("/update")
    public Result update(@RequestBody Specification specification){
        try {
            specificationService.update(specification);
            return new Result(true,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"修改失败");
        }
    }
    //删除
    @RequestMapping("/delete")
    public Result delete(Long[] ids){
        try {
            specificationService.delete(ids);
            return new Result(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }
}
