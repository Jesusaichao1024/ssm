<%--
  Created by IntelliJ IDEA.
  User: Jesusaichao
  Date: 18.10.18
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${pageContext.request.contextPath}/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>
                    <security:authentication property="principal.username"></security:authentication>
                </p>
                <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
            </div>
        </div>
        <!-- search form -->
        <!--<form action="#" method="get" class="sidebar-form">
    <div class="input-group">
        <input type="text" name="q" class="form-control" placeholder="搜索...">
        <span class="input-group-btn">
        <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
        </button>
      </span>
    </div>
</form>-->
        <!-- /.search form -->


        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">菜单</li>

            <li id="admin-index"><a href="${pageContext.request.contextPath}/index.jsp"><i class="fa fa-dashboard"></i>
                <span>首页</span></a></li>

            <%--数据管理--%>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-book"></i> <span>产品管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">

                    <%--在jsp页面添加标签进行控制菜单的控制--%>
                    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_ORDERS')">
                        <li id="travellog-manage">
                            <a href="${pageContext.request.contextPath}/product/findAllProduct">
                                <i class="fa fa-circle-o"></i> 产品数据列表
                            </a>
                        </li>
                    </security:authorize>
                    <security:authorize access="hasAnyRole('ROLE_PRODUCT','ROLE_ADMIN')">
                    <li id="travellog-menu">
                        <a href="${pageContext.request.contextPath}/productPage/findAllProductPage">
                            <i class="fa fa-circle-o"></i> 产品分页数据
                        </a>
                    </li>
                    </security:authorize>

                    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_ORDERS')">
                        <li id="travellog-setting">
                            <a href="${pageContext.request.contextPath}/orders/findAllOrders">
                                <i class="fa fa-circle-o"></i> 订单列表
                            </a>
                        </li>
                    </security:authorize>

                        <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_ORDERS')">
                    <li id="travellog-menu1">
                        <a href="${pageContext.request.contextPath}/orders/orderPage">
                            <i class="fa fa-circle-o"></i> 订单分页
                        </a>
                    </li>
                        </security:authorize>

                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-book"></i> <span>用户管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
                <ul class="treeview-menu">
                    <li id="travellog-">
                        <a href="${pageContext.request.contextPath}/user/userPage">
                            <i class="fa fa-circle-o"></i> 用户数据展示
                        </a>
                </ul>
                </security:authorize>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-book"></i> <span>角色管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
                    <li id="travellog">
                        <a href="${pageContext.request.contextPath}/role/rolePage">
                            <i class="fa fa-circle-o"></i> 角色数据展示
                        </a>
                    </li>
                    </security:authorize>
                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-book"></i> <span>权限列表</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li id="travellog--">
                        <a href="${pageContext.request.contextPath}/permission/permissionPage">
                            <i class="fa fa-circle-o"></i> 权限数据展示
                        </a>
                    </li>
                </ul>
            </li>
            <%--系统管理--%>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-cogs"></i> <span>系统管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">

                    <li id="system-setting">
                        <a href="all-system-setting-edit.html">
                            <i class="fa fa-circle-o"></i> 系统设置
                        </a>
                    </li>

                </ul>
            </li>

            <li id="admin-documentation"><a href="documentation.html" target="_blank"><i class="fa fa-book"></i> <span>AdminLTE汉化文档</span></a>
            </li>

        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
