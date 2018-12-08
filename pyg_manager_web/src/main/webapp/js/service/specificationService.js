//自定义服务
app.service('specificationService',function ($http) {
    ////定义查询所有品牌列表的方法
    this.findAll=function () {
        return $http.get("../specification/findAll.do");
    }
    //条件查询和分页展示
    this.search=function (searchEntity,pageNum,pageSize) {
        return $http.post("../specification/search.do?pageNum="+pageNum+"&pageSize="+pageSize,searchEntity);
    }
    //添加和修改
    this.add=function (entity) {
        return $http.post("../specification/add.do",entity);
    }
    //修改
    this.update=function (entity) {
        return $http.post("../specification/update.do",entity);
    }
    //通过id查找
    this.findOne=function (id) {
        return $http.get("../specification/findOne.do?id="+id);
    }
    //删除
    this.delete=function (selectIds) {
        return $http.get("../specification/delete.do?ids="+selectIds);
    }


})