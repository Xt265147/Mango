import Vue from 'vue'
import vuex from 'vuex'
import app from './modules/app'
import user from './modules/user'
import menu from './modules/menu'
import tab from './modules/tab'

Vue.use(vuex)

const store = new vuex.Store({
  modules: {
    app,
    user,
    menu,
    tab
  }
})
export default store
