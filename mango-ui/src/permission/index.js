import store from '@/store'

/**
 * 判断是否有权限
 * @param {String} perms 权限标识
 * @returns {Boolean}
 */
export function hasPermission (perms) {
  let hasPerms = false
  let permsList = store.state.user.perms
  for (let i = 0; i < permsList.length; i++) {
    if (permsList[i] === perms) {
      hasPerms = true
      break
    }
  }
  return hasPerms
}
