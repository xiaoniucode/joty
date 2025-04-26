import type { ApiModule } from '@/utils/api'

export const user: ApiModule = {
  get: { url: '/user/get', method: 'post' },
  add: { url: '/user/add', method: 'post' },
  del: { url: '/user/del', method: 'delete' },
  login: { url: '/user/login', method: 'post' },
} as const
