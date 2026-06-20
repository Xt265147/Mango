import api from './api' // 导入所有接口

const install = Vue => {
  if (install.installed) return
  install.installed = true
  Object.defineProperties(Vue.prototype, {
    // 注意这里，将api挂载在Vue.prototype上，通过Vue.prototype.$api即可访问所有接口
    $api: {
      get () {
        return api
      }
    }
  })
}

export default install
