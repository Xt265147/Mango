import axios from '../axios'
/* 菜单管理模块 */

// 保存
export const save = data => {
  return axios({
    url: '/menu/save',
    method: 'post',
    data
  })
}

// 删除
export const batchDelete = data => {
  return axios({
    url: '/menu/delete',
    method: 'post',
    data
  })
}

// 分页查询
export const findPage = data => {
  return axios({
    url: '/menu/findPage',
    method: 'post',
    data
  })
}

// 查询所有
export const findAll = () => {
  return axios({
    url: '/menu/findAll',
    method: 'get'
  })
}
