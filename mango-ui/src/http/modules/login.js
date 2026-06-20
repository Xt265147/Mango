import $axios from '../axios'

export function login (params) {
  return $axios({
    url: '/login',
    method: 'post',
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data: new URLSearchParams(params)
  })
}
