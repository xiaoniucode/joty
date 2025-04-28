import Layout from "@/layouts/index.vue";

export const staticRoutes = [
    {
        path: '/login',
        name: 'login',
        component: () => import('@/views/system/login/index.vue'),
        hidden: true
    },
    {
        path: '/',
        name: 'layout',
        component: Layout,
        redirect:'/dashboard',
        children: [
            {
                path: 'dashboard',
                name: '控制面板',
                meta: {},
                component: () => import('@/views/system/dashboard/index.vue'),
            },
        ],
    },
    {
        path: '/',
        name: '短链',
        component: Layout,
        children: [
            {
                path: 'short-url',
                name: '短链列表',
                component: () => import('@/views/leopard/shorturl/index.vue'),
                hidden: false
            },

            {
                path: 'stats',
                name: '数据统计',
                meta: {},
                component: () => import('@/views/leopard/stats/index.vue'),
            },
        ],
    },
]
