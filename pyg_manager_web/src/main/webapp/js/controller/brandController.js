//zidin
//定义控制器  参数一：控制器名称  参数二：控制器做的事情
//$scope angularjs内置对象，全局作用域对象  作用：html与js数据交换的桥梁
//$http angularjs发起请求的内置服务对象   全部都是异步ajax
app.controller("brandController",function ($scope,$controller,brandService) {

    //控制器继承代码,参数一:继承父控制器名称,  参数二:固定写法:共享$scope对象
    $controller("baseController",{$scope:$scope});
    //定义查询所有品牌列表的方法
    $scope.findAll=function () {
        //response接收响应结果
        brandService.findAll().success(function (response) {
            $scope.list=response
        });
    }

    //重新加载分页信息
    /*$scope.reloadList=function () {
        $scope.findPage($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
    }*/
    //条件查询和分页展示
    $scope.searchEntity={};//解决初始化参数为空的情况
    $scope.search=function (pageNum,pageSize) {
        brandService.search($scope.searchEntity,pageNum,pageSize).success(function (response) {
            $scope.paginationConf.totalItems=response.total;
            $scope.list=response.rows;
        })
    }
    //添加
    $scope.save=function () {
        //定义一个变量
        var method=null;
        if($scope.entity.id!=null){
            //修改
            method=brandService.update($scope.entity);
        }else{
            //新增
            method=brandService.add($scope.entity);
        }
        method.success(function (response) {
            if (response.success){
                //添加成功
                $scope.reloadList();
            }else{
                //不成功
                alert(response.message);
            }
        })
    }
    //通过id查找
    $scope.findOne=function (id) {
        brandService.findOne(id).success(function (response) {
            $scope.entity=response;
        })
    }

    //删除
    $scope.delete=function () {
        if (confirm("您确定要删除吗")){
            brandService.delete($scope.selectIds).success(function (response) {
                if (response.success){
                    //删除成功
                    $scope.reloadList();
                }else{
                    //删除失败
                    //给提示消息
                    alert(response.message);
                }

            })
        }
    }
});