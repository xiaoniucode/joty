import type { ApiModule } from '@/utils/api'

export const short_url: ApiModule = {
  list: { url: '/short-url/list', method: 'post' },
  get: { url: '/short-url/get', method: 'get' },
  del: { url: '/short-url/del', method: 'delete' },
  create: { url: '/short-url/create', method: 'post' },
  createBatch: { url: '/short-url/create-batch', method: 'post' },
  update: { url: '/short-url/update', method: 'put' },
} as const
