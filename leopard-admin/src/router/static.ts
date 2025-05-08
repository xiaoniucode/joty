import Layout from '@/layouts/index.vue'

export const staticRoutes = [
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/login/index.vue'),
    hidden: true,
  },
  {
    path: '/',
    name: 'accountLayout',
    component: Layout,
    meta: { title: 'account' },
    hidden: true,
    children: [
      {
        path: 'account',
        name: 'account',
        meta: { title: '账户信息', icon: 'DashboardFilled' },
        component: () => import('@/views/system/account/index.vue'),
      },
    ],
  },
]
