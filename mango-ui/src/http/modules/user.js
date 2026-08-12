import axios from '../axios'
/* 用户管理模块 */

// 保存
export const save = data => {
  return axios({
    url: '/user/save',
    method: 'post',
    data
  })
}

// 删除
export const batchDelete = data => {
  return axios({
    url: '/user/delete',
    method: 'post',
    data
  })
}

// 分页查询
export const findPage = data => {
  return axios({
    url: '/user/findPage',
    method: 'post',
    data
  })
}

// 查询所有
export const findAll = () => {
  return axios({
    url: '/user/findAll',
    method: 'get'
  })
}

// 查询用户权限标识集合
export const findPermissions = params => {
  return axios({
    url: '/user/findPermissions',
    method: 'get',
    params
  })
}
