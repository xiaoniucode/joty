import type { ApiModule } from '@/utils/api'

export const stats: ApiModule = {
    singleUrl: { url: '/api/stats/get-by-url', method: 'get' },
    dashboard: { url: '/api/stats/dashboard', method: 'get' },
    getAccessCountByType: { url: '/api/stats/get-access-count-by-type', method: 'post' },
    records: { url: '/api/stats/records', method: 'post' },

} as const
