import type { ApiModule } from '@/utils/api'

export const group: ApiModule = {
  list: { url: '/group/list', method: 'post' },
  get: { url: '/group/get', method: 'get' },
  del: { url: '/group/del', method: 'delete' },
  save: { url: '/group/save', method: 'post' },
} as const
