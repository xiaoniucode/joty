import axios from 'axios'
import { message } from 'ant-design-vue'
import { router } from '@/router'
import { useAppStore } from '@/stores/modules/app.ts'
import { useUserStore } from '@/stores/modules/user.ts'

//const baseApi = import.meta.env.VITE_APP_BASE_API|| 'http://xnkfz.com'
const baseApi ='http://xnkfz.com'

const instance = axios.create({
  baseURL: baseApi + '/',
  timeout: 60 * 1000,
})
//请求拦截器
instance.interceptors.request.use(
  function (config) {
    config.headers['Accept-Language'] = useAppStore().config.language || 'zh-CN'
    const token = useUserStore().getToken()
    if (token) {
      config.headers[useUserStore().getTokenName()] = `Bearer ${token}`
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
    } else if (code == '401'||code == '403') {
      message.error(msg)
      useUserStore().resetUserState()
      router.push({ path: '/login' })
    } else {
      message.error(msg)
      return Promise.reject({ code: code, msg: msg })
    }
  },
  async function (error) {
    if (error.status === 401) {
      await useUserStore().logout()
      throw error
    }

    let message = error.message
    if (message === 'Network Error') {
      message = '服务端网络故障'
    } else if (message.includes('timeout')) {
      message = '接口请求超时'
    } else if (message.includes('Request failed with status code')) {
      message = `接口${message.substr(message.length - 3)}异常`
    }
    message.error(message)
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
