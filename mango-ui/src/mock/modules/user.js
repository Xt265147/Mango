/* 用户管理模块 */
// 分页查询
export const findPage = () => {
  return {
    isOpen: true,
    url: 'user/findPage',
    type: 'post',
    data: {
      'code': 200,
      'msg': null,
      'data': {
        'total': 1,
        'rows|1-10': [{
          'id': '@increment',
          'name': '@name',
          'email': '@email',
          'age|1-100': 5
        }]
      }
    }
  }
}

// 保存
export const save = () => {
  return {
    isOpen: true,
    url: 'user/save',
    type: 'post',
    data: {
      'code': 200,
      'msg': '操作成功'
    }
  }
}

// 删除
export const batchDelete = () => {
  return {
    isOpen: true,
    url: 'user/delete',
    type: 'post',
    data: {
      'code': 200,
      'msg': '删除成功'
    }
  }
}

// 查找用户的菜单权限标识集合
export const findPermissions = () => {
  return {
    isOpen: true,
    url: 'user/findPermissions',
    type: 'get',
    data: {
      'code': 200,
      'msg': null,
      'data': []
    }
  }
}
