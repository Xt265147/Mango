import Vue from 'vue'
import vuex from 'vuex'
import app from './modules/app'
import user from './modules/user'
import menu from './modules/menu'

Vue.use(vuex)

const store = new vuex.Store({
  modules: {
    app,
    user,
    menu
  }
})
export default store
