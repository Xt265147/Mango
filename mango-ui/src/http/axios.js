import axios from 'axios'
import config from './config'
import Cookies from 'js-cookie'
import router from '@/router'

export default function $axios (options) {
  return new Promise((resolve, reject) => {
    const instance = axios.create({
      baseURL: config.baseUrl,
      headers: config.headers,
      timeout: config.timeout,
      withCredentials: config.withCredentials
    })
    // 请求拦截器
    instance.interceptors.request.use(
      config => {
        let token = Cookies.get('token')
        if (token && config.url !== '/login') { // 非登录请求携带token
          config.headers['Authorization'] = 'Bearer ' + token
        } else if (!token && config.url !== '/login') { // 无token且非登录页则跳转
          router.push('/login')
        }
        return config
      },
      error => {
        return Promise.reject(error)
      }
    )
    // 响应拦截器
    instance.interceptors.response.use(
      response => {
        return response.data
      },
      err => {
        return Promise.reject(err)
      }
    )
    // 请求处理
    instance(options).then(res => {
      resolve(res)
      return false
    }).catch(error => {
      reject(error)
    })
  })
}
