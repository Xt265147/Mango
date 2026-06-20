export default {
  state: {
    appName: 'Mango Platform', // 应用名称
    appVersion: '1.0.0', // 应用版本
    themeColor: '#14889A', // 主题颜色
    oldThemeColor: '#14889A', // 旧主题颜色
    collapse: false, // 折叠
    menuRouteLoaded: false // 菜单路由是否已加载
  },
  getters: {
    collapse (state) { // 侧边栏折叠
      return state.collapse
    }
  },
  mutations: {
    onCollapse (state) { // 侧边栏折叠
      state.collapse = !state.collapse
    },
    setThemeColor (state, color) { // 设置主题颜色
      state.oldThemeColor = state.themeColor
      state.themeColor = color
    },
    menuRouteLoaded (state, menuRouteLoaded) { // 菜单路由是否已加载
      state.menuRouteLoaded = menuRouteLoaded
    }
  },
  actions: {}
}
