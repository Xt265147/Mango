<template>
  <div class="menu-bar-container" :class="collapse ? 'menu-bar-collapse' : 'menu-bar-width'" :style="{ backgroundColor: themeColor }">
    <div class="logo" :style="{ backgroundColor: themeColor }" @click="$router.push('/')">
      <img src="@/assets/logo.png"/>
      <div>{{ collapse ? '' : appName }}</div>
    </div>
    <el-menu
      :default-active="activeMenu"
      :class="collapse ? 'menu-bar-collapse-width' : 'menu-bar-width'"
      :collapse="collapse"
      :collapse-transition="false"
      :background-color="themeColor"
      text-color="#ffffffbf"
      active-text-color="#fff"
      :unique-opened="true"
      router
    >
      <menu-tree v-for="item in navTree" :key="item.id" :menu="item"/>
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
      appName: state => state.app.appName,
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
  box-shadow:
    4px 0 14px var(--neu-shadow-dark),
    0 0 0 transparent;
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
  color: #fff;
  font-weight: bold;
  font-size: 18px;
}
.logo img {
  width: 32px;
  height: 32px;
  margin-right: 8px;
}
.el-menu {
  border-right: none;
}
.el-menu:not(.el-menu--collapse) {
  width: 200px;
}
</style>
