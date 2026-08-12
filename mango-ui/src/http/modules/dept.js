import axios from '../axios'
/* 机构管理模块 */

// 保存
export const save = data => {
  return axios({
    url: '/dept/save',
    method: 'post',
    data
  })
}

// 删除
export const batchDelete = data => {
  return axios({
    url: '/dept/delete',
    method: 'post',
    data
  })
}

// 分页查询
export const findPage = data => {
  return axios({
    url: '/dept/findPage',
    method: 'post',
    data
  })
}

// 查询所有
export const findAll = () => {
  return axios({
    url: '/dept/findAll',
    method: 'get'
  })
}
