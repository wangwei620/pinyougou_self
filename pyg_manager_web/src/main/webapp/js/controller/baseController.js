//分页展示条
//分页配置
app.controller("baseController",function ($scope) {
    $scope.paginationConf = {
        currentPage:1,  				//当前页
        totalItems:10,					//总记录数
        itemsPerPage:10,				//每页记录数
        perPageOptions:[10,20,30,40,50], //分页选项，下拉选择一页多少条记录
        onChange:function(){			//页面变更后触发的方法
            $scope.reloadList();		//启动就会调用分页组件
        }
    };

    $scope.reloadList=function () {
        $scope.search($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
    }
//定义复选框ids数组值
    $scope.selectIds=[];
//跟新复选框状态
    $scope.updateSelection=function ($event,id) {
        //判断  选中的状态
        if($event.target.checked){
            //选中状态
            $scope.selectIds.push(id);
        }else{
            //取消勾选,移除当前的id值,//参数一:移除当前位置的索引值,参数二:从该处移除几个值
            var index = $scope.selectIds.indexOf(id);
            $scope.selectIds.splice(index,1);
        }
    }
})
