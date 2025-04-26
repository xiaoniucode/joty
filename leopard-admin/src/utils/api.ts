import axios from 'axios'

const baseApi = import.meta.env.VITE_APP_BASE_API
const instance = axios.create({
    baseURL: baseApi + '/',
    timeout: 1000,
    headers: { 'X-Custom-Header': 'foobar' },
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
        return Promise.reject(error)
    },
)

//响应拦截器
instance.interceptors.response.use(
    function (response) {
        const { code, msg, data } = response.data
        alert("data")
        return response
    },
    function (error) {
        return Promise.reject(error)
    },
)
 /*--------------------------------------------------------------------------------*/
export interface ApiType {
    url: string;
    method: 'get' | 'post' | 'delete' | 'put' | 'patch';
}

export interface ApiModule {
    [key: string]: ApiType;
}

class Api {
    action(apiConfig: ApiType, param: any = {}, body: any = {}) {
        const { url, method } = apiConfig;
        return instance({
            url,
            method,
            params: param,
            data: body,
        });
    }
}

const api = new Api();
export default api;
