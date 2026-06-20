/* 角色管理模块 */

// 保存
export const save = () => {
  return {
    isOpen: true,
    url: 'role/save',
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
    url: 'role/delete',
    type: 'post',
    data: {
      'code': 200,
      'msg': '删除成功'
    }
  }
}

// 分页查询
export const findPage = () => {
  return {
    isOpen: true,
    url: 'role/findPage',
    type: 'post',
    data: {
      'code': 200,
      'msg': null,
      'data': {
        'total': 1,
        'rows|1-5': [{
          'id': '@increment',
          'name': '@name',
          'remark': '@sentence'
        }]
      }
    }
  }
}

// 查询所有角色
export const findAll = () => {
  return {
    isOpen: true,
    url: 'role/findAll',
    type: 'get',
    data: {
      'code': 200,
      'msg': null,
      'data|3-8': [{
        'id': '@increment',
        'name': '@name'
      }]
    }
  }
}
