import axios from 'axios'
import { message } from 'ant-design-vue'
import {router }from '@/router'

const baseApi = import.meta.env.VITE_APP_BASE_API
const instance = axios.create({
  baseURL: baseApi + '/',
  timeout: 1000,
  headers: {
    'Accept-Language': 'zh-CN', //zh-CN or en-US
  },
})
//请求拦截器
instance.interceptors.request.use(
  function (config) {
    const token = localStorage.getItem('token')
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`
    }
    return config
  },
  function (error) {
    message.error('error')
    return Promise.reject(error)
  },
)

//响应拦截器
instance.interceptors.response.use(
  function (response) {
    const { code, msg, data } = response.data
    if (code == '0') {
      return data
    }
    else if (code == '401') {
      message.error(msg)
      //清空本地cookie
      localStorage.removeItem('token')
      //跳转到登陆
      router.push({ path: '/login' })
    } else {
      message.error(msg)
      return Promise.reject({ code: code, msg: msg })
    }
  },
  function (error) {
    const { code } = error
    if (code == 'ERR_NETWORK') {
      message.error('服务暂不可用')
    }
    return Promise.reject(error)
  },
)

/*--------------------------------------------------------------------------------*/
export interface ApiType {
  url: string
  method: 'get' | 'post' | 'delete' | 'put' | 'patch'
}

export interface ApiModule {
  [key: string]: ApiType
}

class Api {
  action(apiConfig: ApiType, param: any = {}, body: any = {}) {
    const { url, method } = apiConfig
    return instance({
      url,
      method,
      params: param,
      data: body,
    })
  }
}

const api = new Api()
export default api
