登录-->登录验证-->跳转到pages/index.jsp--->index.jsp--->选择不同的iframe---->默认加载product/serviceHome(服务制作）


问题？？？
  用户登录的过程给忽略了？？
  直接做的是用户通过验证的流程？？-------------》加上用户通过验证前的流程。
  
  及springMVC，用户请求通过
  
  
  即目前没有做到的是：
      将请求通过framefilter之后到进入springMVC的dispatcher的流程没有做出来
      
      
      
  ## 用户登录
  现在开始做
    第一步：先了解springMVC的流程。
      
   ![Image of Yaktocat](http://terasolunaorg.github.io/guideline/1.0.1.RELEASE/en/_images/RequestLifecycle.png)
      
   第二步：当请求通过最后一个过滤器，这个时候如上图请求就进入了springMVC的dispatcher中;
   
   第三步：dispatcher进行处理：（源码）
            1. 在handlerMapping（处理器映射器）中，查找处理器是哪一个？？
                1. springMVC

checkLogin----> index.jsp?moduleid=fwzz====>加载iframe=====》product/serviceH
