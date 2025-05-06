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
    name: 'layout',
    component: Layout,
    redirect: '/dashboard',
    meta: { title: 'root' },
    children: [
      {
        path: 'dashboard',
        name: 'dashboard',
        meta: { title: '控制面板', icon: 'DashboardFilled' },
        component: () => import('@/views/system/dashboard/index.vue'),
      },
    ],
  },
  {
    path: '/',
    name: 'accountLayout',
    component: Layout,
    redirect: '/account',
    meta: { title: 'account' },
    hidden: true,
    children: [
      {
        path: 'account',
        name: 'account',
        meta: { title: '控制面板', icon: 'DashboardFilled' },
        component: () => import('@/views/system/account/index.vue'),
      },
    ],
  },
  {
    path: '/',
    name: 'member',
    component: Layout,
    meta: { title: '会员管理', icon: 'SlackCircleFilled' },
    alwaysShow: true,
    children: [
      {
        path: 'member-list',
        name: 'MemberList',
        component: () => import('@/views/system/member/member-list/index.vue'),
        hidden: false,
        meta: { title: '会员用户', icon: 'IeCircleFilled' },
      },
    ],
  },
  {
    path: '/',
    name: 'ShortUrl',
    component: Layout,
    alwaysShow: false,
    meta: { title: '短链管理', icon: 'SlackCircleFilled' },
    children: [
      {
        path: 'short-url',
        name: 'LinkList',
        component: () => import('@/views/leopard/shorturl/index.vue'),
        meta: { title: '短链列表', icon: 'IeCircleFilled' },
      },
      {
        path: 'fast-create',
        name: 'FastCreate',
        meta: { title: '快速创建', icon: 'PlusSquareOutlined' },
        component: () => import('@/views/leopard/fast-create/index.vue'),
      },
      {
        path: 'batch-create',
        name: 'BatchCreate',
        meta: { title: '批量创建', icon: 'AppstoreAddOutlined' },
        component: () => import('@/views/leopard/batch-create/index.vue'),
      },
      {
        path: 'restore-url',
        name: 'RestoreUrl',
        meta: { title: '短链还原', icon: 'AppstoreAddOutlined' },
        component: () => import('@/views/leopard/restore-url/index.vue'),
      },
      {
        path: 'open-api',
        name: 'OpenApi',
        meta: { title: '开放接口', icon: 'UngroupOutlined' },
        component: () => import('@/views/leopard/stats/index.vue'),
      },
      {
        path: 'stats',
        name: 'Stats',
        meta: { title: '数据统计', icon: 'DotChartOutlined' },
        component: () => import('@/views/leopard/stats/index.vue'),
      },
    ],
  },
]
