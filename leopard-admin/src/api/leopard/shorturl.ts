import type { ApiModule } from '@/utils/api'

export const short_url: ApiModule = {
  list: { url: '/api/short-url/list', method: 'post' },
  get: { url: '/api/short-url/get', method: 'get' },
  del: { url: '/api/short-url/del', method: 'delete' },
  create: { url: '/api/short-url/create', method: 'post' },
  createBatch: { url: '/api/short-url/create-batch', method: 'post' },
  update: { url: '/api/short-url/update', method: 'put' },
  fastCreate: { url: '/api/short-url/fast-create', method: 'post' },
  restoreUrl: { url: '/api/short-url/restore-url', method: 'post' },
} as const
