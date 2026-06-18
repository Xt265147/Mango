<template>
  <div class="menu-bar-container" :class="collapse ? 'menu-bar-collapse' : 'menu-bar-width'" :style="{ backgroundColor: themeColor }">
    <div class="logo" :style="{ backgroundColor: themeColor }" @click="$router.push('/')">
      <img src="@/assets/logo.png"/>
      <span v-if="!collapse" class="logo-text">Mango</span>
    </div>
    <el-menu
      :default-active="activeMenu"
      :collapse="collapse"
      :background-color="themeColor"
      text-color="#ffffffbf"
      active-text-color="#fff"
      :unique-opened="true"
      router
    >
      <el-menu-item index="/">
        <i class="fa fa-home"></i>
        <span slot="title">{{ $t('common.home') }}</span>
      </el-menu-item>

      <el-submenu index="monitor">
        <template slot="title">
          <i class="fa fa-tachometer"></i>
          <span>{{ $t('menu.monitor') }}</span>
        </template>
        <el-menu-item index="/monitor/online">{{ $t('menu.online') }}</el-menu-item>
        <el-menu-item index="/monitor/server">{{ $t('menu.server') }}</el-menu-item>
        <el-menu-item index="/monitor/cache">{{ $t('menu.cache') }}</el-menu-item>
      </el-submenu>

      <el-submenu index="system">
        <template slot="title">
          <i class="fa fa-cogs"></i>
          <span>{{ $t('menu.system') }}</span>
        </template>
        <el-menu-item index="/system/user">{{ $t('menu.user') }}</el-menu-item>
        <el-menu-item index="/system/role">{{ $t('menu.role') }}</el-menu-item>
        <el-menu-item index="/system/menu">{{ $t('menu.menu') }}</el-menu-item>
        <el-menu-item index="/system/dept">{{ $t('menu.dept') }}</el-menu-item>
        <el-menu-item index="/system/dict">{{ $t('menu.dict') }}</el-menu-item>
        <el-menu-item index="/system/config">{{ $t('menu.config') }}</el-menu-item>
      </el-submenu>

      <el-submenu index="tool">
        <template slot="title">
          <i class="fa fa-wrench"></i>
          <span>{{ $t('menu.tool') }}</span>
        </template>
        <el-menu-item index="/tool/backup">{{ $t('menu.backup') }}</el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import MenuTree from '@/componets/MenuTree/MenuTree.vue'

export default {
  name: 'NavBar',
  components: { MenuTree },
  computed: {
    ...mapState({
      collapse: state => state.app.collapse,
      themeColor: state => state.app.themeColor,
      navTree: state => state.menu.navTree
    }),
    activeMenu () {
      return this.$route.path
    }
  }
}
</script>

<style scoped>
.menu-bar-container {
  position: fixed;
  top: 0;
  left: 0;
  bottom: 0;
  z-index: 1001;
  overflow-x: hidden;
  overflow-y: auto;
  transition: width 0.3s;
}
.menu-bar-width {
  width: 200px;
}
.menu-bar-collapse {
  width: 64px;
}
.logo {
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  white-space: nowrap;
}
.logo img {
  width: 32px;
  height: 32px;
  margin-right: 8px;
}
.logo-text {
  font-size: 20px;
  font-weight: bold;
  color: #fff;
}
.el-menu {
  border-right: none;
}
.el-menu:not(.el-menu--collapse) {
  width: 200px;
}
</style>
