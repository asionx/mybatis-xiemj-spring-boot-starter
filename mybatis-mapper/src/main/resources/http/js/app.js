(function($) {
  'use strict';

  $(function() {
    var $fullText = $('.admin-fullText');
    $('#admin-fullscreen').on('click', function() {
      $.AMUI.fullscreen.toggle();
    });

    $(document).on($.AMUI.fullscreen.raw.fullscreenchange, function() {
      $fullText.text($.AMUI.fullscreen.isFullscreen ? '退出全屏' : '开启全屏');
    });

    var getWindowHeight = $(window).height(),
        myappLoginBg    = $('.myapp-login-bg');
    myappLoginBg.css('min-height',getWindowHeight + 'px');
    $.ajax({
        url:'/mybatis/generator/pros',
        type:'GET', //GET
        async:true,    //或false,是否异步
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){
            if(data!=null){
                $("#connectionURL").val(data.connectionURL);
                $("#userId").val(data.userId);
                $("#password").val(data.password);
                $("#javaModel").val(data.javaModel);
                $("#sqlMap").val(data.sqlMap);
                $("#javaClient").val(data.javaClient);
                $("#tableName").val(data.tableName);
                $("#tableAlis").val(data.tableAlis);
            }
        }
    })
  });
  $("#tkgeneratorbtn").on("click",function(){
     bulidfile(1);
  });
  $("#generatorbtn").on("click",function(){
     bulidfile(2);
  });
  function bulidfile(type){
    var data1={};
    if($("#connectionURL").val()==null||$("#connectionURL").val()==''){
       alert("数据库链接不能为空");
       return;
    }
    if($("#userId").val()==null||$("#userId").val()==''){
       alert("用户名不能为空");
       return;
    }
    if($("#password").val()==null||$("#password").val()==''){
       alert("密码不能为空");
       return;
    }
    if($("#tableName").val()==null||$("#tableName").val()==''){
       alert("生成表名不能为空");
       return;
    }
    data1.connectionURL=$("#connectionURL").val();
    data1.userId=$("#userId").val();
    data1.password=$("#password").val();
    data1.javaModel=$("#javaModel").val();
    data1.sqlMap=$("#sqlMap").val();
    data1.javaClient=$("#javaClient").val();
    data1.tableName=$("#tableName").val();
    data1.tableAlis=$("#tableAlis").val();
    data1.type=type;
    var datastr=JSON.stringify(data1);
    $.ajax({
        url:'/mybatis/generator/build',
        type:'get', //GET
        async:true,    //或false,是否异步
        data:{datastr:datastr},
        timeout:5000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){
            if(data.code==0){
               alert("生成成功，请查看target目录");
            }else{
                alert("生成失败，失败原因:"+data.message);
            }
        }
    })
  }

})(jQuery);
