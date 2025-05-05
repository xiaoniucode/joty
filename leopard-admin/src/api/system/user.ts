import type { ApiModule } from '@/utils/api'
//用户接口
export const user: ApiModule = {
  get: { url: '/user/get', method: 'post' },
  list: { url: '/user/list', method: 'post' },
  add: { url: '/user/add', method: 'post' },
  del: { url: '/user/del', method: 'delete' },
  login: { url: '/user/login', method: 'post' },
  logout: { url: '/user/logout', method: 'post' },
} as const
