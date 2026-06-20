import Vue from 'vue'
import Router from 'vue-router'
import Cookies from 'js-cookie'
import Login from '@/views/Login'
import Home from '@/views/Home'
import Index from '@/views/Index'
import User from '@/views/system/User'
import NotFound from '@/views/404.vue'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/',
      name: 'Home',
      component: Home,
      children: [
        {
          path: '',
          name: 'Index',
          component: Index
        },
        {
          path: 'system/user',
          name: 'SysUser',
          component: User,
          meta: { title: '用户管理', breadcrumb: ['系统管理', '用户管理'] }
        }
      ]
    },
    {
      path: '/404',
      name: 'notFound',
      component: NotFound
    },
    {
      path: '*',
      redirect: '/404'
    }
  ]
})

router.beforeEach((to, from, next) => {
  let token = Cookies.get('token')
  if (to.path === '/login') {
    next()
  } else {
    if (!token) {
      next({ path: '/login' })
    } else {
      next()
    }
  }
})

export default router
