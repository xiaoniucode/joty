import type { ApiModule } from '@/utils/api'

export const stats: ApiModule = {
    singleUrl: { url: '/api/stats/get-by-url', method: 'get' },
    dashboard: { url: '/api/stats/dashboard', method: 'get' },

} as const
