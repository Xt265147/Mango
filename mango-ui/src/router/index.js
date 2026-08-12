import Vue from 'vue'
import Router from 'vue-router'
import Cookies from 'js-cookie'
import Login from '@/views/Login'
import Home from '@/views/Home'
import Intro from '@/views/Intro/Intro'
import User from '@/views/system/User'
import Dept from '@/views/system/Dept'
import Role from '@/views/system/Role'
import Menu from '@/views/system/Menu'
import NotFound from '@/views/404.vue'
import store from '@/store'
import api from '@/http/api'

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
          path: '/',
          name: '首页',
          component: Home
        },
        {
          path: '',
          name: '系统介绍',
          component: Intro,
          meta: {
            icon: 'fa fa-home fa-lg',
            index: '0'
          }
        },
        {
          path: 'system/user',
          name: 'SysUser',
          component: User,
          meta: { title: '用户管理', breadcrumb: ['系统管理', '用户管理'] }
        },
        {
          path: 'system/dept',
          name: 'SysDept',
          component: Dept,
          meta: { title: '机构管理', breadcrumb: ['系统管理', '机构管理'] }
        },
        {
          path: 'system/role',
          name: 'SysRole',
          component: Role,
          meta: { title: '角色管理', breadcrumb: ['系统管理', '角色管理'] }
        },
        {
          path: 'system/menu',
          name: 'SysMenu',
          component: Menu,
          meta: { title: '菜单管理', breadcrumb: ['系统管理', '菜单管理'] }
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
      if (!store.state.app.menuRouteLoaded) {
        loadNavTree(next)
      } else {
        next()
      }
    }
  }
})

// 默认菜单树（后端不可用时兜底）
var defaultNavTree = [
  {
    id: 1,
    name: '系统管理',
    icon: 'fa fa-cog',
    type: 0,
    parentId: 0,
    children: [
      {
        id: 11,
        name: '用户管理',
        icon: 'fa fa-user',
        type: 1,
        parentId: 1,
        url: 'system/user'
      },
      {
        id: 12,
        name: '机构管理',
        icon: 'fa fa-sitemap',
        type: 1,
        parentId: 1,
        url: 'system/dept'
      },
      {
        id: 13,
        name: '角色管理',
        icon: 'fa fa-users',
        type: 1,
        parentId: 1,
        url: 'system/role'
      },
      {
        id: 14,
        name: '菜单管理',
        icon: 'fa fa-th-list',
        type: 1,
        parentId: 1,
        url: 'system/menu'
      }
    ]
  }
]

function loadNavTree (next) {
  let userName = sessionStorage.getItem('user')
  api.menu.findNavTree({ userName: userName }).then(res => {
    let navTree = (res.data && res.data.length > 0) ? res.data : defaultNavTree
    // 保存菜单树到store
    store.commit('menu/setNavTree', navTree)
    // 添加动态路由
    let dynamicRoutes = buildRoutes(navTree)
    router.options.routes[1].children = router.options.routes[1].children.concat(dynamicRoutes)
    router.addRoutes(router.options.routes)
    // 标记已加载
    store.commit('menuRouteLoaded', true)
    // 加载用户权限
    loadPermissions(userName)
    next()
  }).catch(() => {
    // API失败时使用默认菜单
    store.commit('menu/setNavTree', defaultNavTree)
    store.commit('menuRouteLoaded', true)
    next()
  })
}

function loadPermissions (userName) {
  api.user.findPermissions({ name: userName }).then(res => {
    store.commit('setPerms', res.data)
  }).catch(() => {})
}

function buildRoutes (menuList) {
  let routes = []
  for (let i = 0; i < menuList.length; i++) {
    let menu = menuList[i]
    if (menu.children && menu.children.length >= 1) {
      routes = routes.concat(buildRoutes(menu.children))
    } else if (menu.url) {
      let url = menu.url
      let componentPath = url.split('/').map(function (item) {
        return item.substring(0, 1).toUpperCase() + item.substring(1)
      }).join('/')
      let route = {
        path: '/' + url,
        name: menu.name,
        meta: { icon: menu.icon, index: menu.id },
        component: function (resolve) {
          require(['@/views/' + componentPath + '.vue'], resolve)
        }
      }
      routes.push(route)
    }
  }
  return routes
}

export default router
