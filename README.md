项目名：“愉悦书馆”

该项目主要用到的技术有springboot、mysql、mybatis-plus

该项目数据库所用的建表语句皆位于sql目录

1.对user操作时:

登录：以post方式访问地址:/user/login

注册：以post方式访问地址:/user/register
注销：以post方式访问地址:/user/logout

分页查询：以get方式访问地址:/user/page

修改用户：以put方式访问地址:/user/update

修改用户时回显用户信息：以get方式访问地址:/user/{id}

删除：以delete方式访问地址:/user/delete

2.对book操作时:

新增图书：以post方式访问地址:/book/add

分页查询：以get方式访问地址:/book/page

修改图书：以put方式访问地址:/book/update

删除图书：以delete方式访问地址:/book/delete

3.对borrowingForm操作时:

借阅图书：以post方式访问地址:/borrowingForm/borrow

归还图书：以post方式访问地址:/borrowingForm/return

查看目前登录用户的借阅记录：以get方式访问地址:/borrowingForm/pageByUser

管理员查看借阅记录：以get方式访问地址:/borrowingFOrm/page

4.upload以及download

上传文件：以post方式访问地址:/common/upload

下载文件：以get方式请求地址:/common/download