import Layout from "@/layouts/index.vue";

export const staticRoutes = [
    {
        path: '/login',
        name: 'login',
        component: () => import('@/views/login/index.vue'),
        hidden: true
    },

    {
        path: '/',
        name: 'layout',
        component: Layout,
        children: [
            {
                path: 'account',
                name: 'account',
                component: () => import('@/views/account/index.vue'),
                hidden: true
            },
            {
                path: 'dashboard',
                name: 'dashboard',
                meta: {},
                component: () => import('@/views/dashboard/index.vue'),
            },
        ],
    },
]
