import type { ApiModule } from '@/utils/api'

export const group: ApiModule = {
  list: { url: '/api/group/list', method: 'post' },
  get: { url: '/api/group/get', method: 'get' },
  del: { url: '/api/group/del', method: 'delete' },
  save: { url: '/api/group/save', method: 'post' },
} as const
