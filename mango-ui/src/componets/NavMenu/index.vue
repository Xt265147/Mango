<template>
  <el-submenu v-if="menu.children && menu.children.length >= 1" :index="menu.id + ''">
    <template slot="title">
      <i :class="menu.icon"></i>
      <span>{{ menu.name }}</span>
    </template>
    <nav-menu v-for="item in menu.children" :key="item.id" :menu="item"/>
  </el-submenu>
  <el-menu-item v-else :index="menu.url" @click="handleRoute(menu)">
    <i :class="menu.icon"></i>
    <span slot="title">{{ menu.name }}</span>
  </el-menu-item>
</template>

<script>
export default {
  name: 'NavMenu',
  props: {
    menu: {
      type: Object,
      required: true
    }
  },
  methods: {
    handleRoute (menu) {
      if (menu.url) {
        this.$router.push('/' + menu.url)
      }
    }
  }
}
</script>
