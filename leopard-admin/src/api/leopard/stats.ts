import type { ApiModule } from '@/utils/api'

export const stats: ApiModule = {
    singleUrl: { url: '/api/stats/get-by-url', method: 'get' },
    dashboard: { url: '/api/stats/dashboard', method: 'get' },
    getTopAccessIp: { url: '/api/stats/get-top-access-ip', method: 'get' },
    getOsAccessCount: { url: '/api/stats/get-os-access-count', method: 'get' },

} as const
