## demo
个人学习Demo


1. 典型的ssm框架结构
2. 数据源动态切换，开发者仅仅需要使用DataSourceContext.DataSourceHolder(builder)语句就可以动态的切换数据源，对业务没有任何侵入性。
3. 定时任务的配置举例，代码中有关于如何基于spring配置定时任务的例子。
4. 统一的异常处理逻辑，代码中所有的方法一致采用抛出异常的逻辑，在最外层的拦截器中对异常进行统一的处理。
5. 数据库事务的配置距离，代码中有关于如何基于spring配置数据库事务的例子。
6. 支持认证的httpinvoker，传统的httpinvoker调用不支持认证的，demo里面给出的例子支持对调用者的身份认证。
7. 被简化的controller类，采用回调模式，让开发者只关心业务。
