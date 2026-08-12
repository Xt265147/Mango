<script setup lang="ts">

</script>

<template>
  <div class="pade-container" style="width: 99%;margin-top: 15px">
    <el-carousel :interval="3000" type="card" height="450px" class="carousel">
      <el-carousel-item class="carousel-item-intro">
        <h2>项目介绍</h2>
        <ul>
          <li>基于Vue3.0 + Springboot 的Java EE 开发平台</li>
          <li>基于Element Plus 的UI 组件库</li>
        </ul>
        <div><img src="@/assets/logo.png" style="width: 120px;
height: 120px;padding-top:15px;"/></div>
      </el-carousel-item>
      <el-carousel-item class="carousel-item-func">
        <h2>功能介绍</h2>
        <ul>
          <li>系统登录：基于 Token 方式的登录认证</li>
          <li>用户管理：新建用户、修改用户、删除用户、查询用户</li>
          <li>机构管理：新建机构、修改机构、删除机构、查询机构</li>
          <li>角色管理：新建角色、修改角色、删除角色、查询角色</li>
          <li>菜单管理：新建菜单、修改菜单、删除菜单、查询菜单</li>
        </ul>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<style scoped>
.pade-container {
  background: var(--neu-bg);
}
.carousel >>> .el-carousel__item {
  background: var(--neu-bg);
  border-radius: var(--neu-radius-lg);
  box-shadow:
    8px 8px 18px var(--neu-shadow-dark),
    -8px -8px 18px var(--neu-shadow-light);
  padding: 30px;
}
.carousel >>> .el-carousel__item h2 {
  color: var(--neu-primary);
  font-size: 24px;
  margin-bottom: 16px;
  text-align: center;
}
.carousel >>> .el-carousel__item ul {
  list-style: none;
  padding: 0;
}
.carousel >>> .el-carousel__item li {
  padding: 10px 16px;
  margin: 6px 0;
  border-radius: var(--neu-radius-sm);
  box-shadow:
    inset 2px 2px 4px var(--neu-shadow-dark),
    inset -2px -2px 4px var(--neu-shadow-light);
  color: var(--neu-text);
  font-size: 14px;
}
.carousel >>> .el-carousel__item img {
  display: block;
  margin: 0 auto;
}
</style>
