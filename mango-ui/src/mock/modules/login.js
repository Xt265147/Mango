/* 登录模块 */
export function login () {
  return {
    isOpen: true,
    url: 'login',
    type: 'get',
    data: {
      'token': 'mock-token-123456'
    }
  }
}
