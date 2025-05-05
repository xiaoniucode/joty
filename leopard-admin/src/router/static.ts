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
                name: 'dashboard',
                meta: {title:'控制面板',icon:'DashboardFilled'},
                component: () => import('@/views/system/dashboard/index.vue'),
            },
        ],
    },
    {
        path: '/',
        name: 'ShortUrl',
        component: Layout,
        meta:{title:'短链管理',icon:'SlackCircleFilled'},
        children: [
            {
                path: 'short-url',
                name: 'LinkList',
                component: () => import('@/views/leopard/shorturl/index.vue'),
                hidden: false,
                meta: {title:'短链列表',icon:'IeCircleFilled'}
            },

            {
                path: 'stats',
                name: 'Stats',
                meta: {title: '数据统计',icon:'PieChartFilled'},
                component: () => import('@/views/leopard/stats/index.vue'),
            },
        ],
    },
]
