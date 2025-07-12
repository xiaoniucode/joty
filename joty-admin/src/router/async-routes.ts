import Layout from '@/layouts/index.vue'

export const asyncRoutes = [
  {
    path: '/',
    name: 'layout',
    component: Layout,
    redirect: '/dashboard',
    meta: { title: 'root', roles: ['admin'] },
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
    name: 'OS',
    component: Layout,
    meta: { title: '组织架构', icon: 'SlackCircleFilled', roles: ['admin'] },
    alwaysShow: false,
    children: [
      {
        path: 'user-list',
        name: 'UserList',
        component: () => import('@/views/system/os/user/index.vue'),
        meta: { title: '用户管理', icon: 'IeCircleFilled' },
      },
    ],
  },
  {
    path: '/',
    name: 'setting',
    component: Layout,
    meta: { title: '系统设置', icon: 'SlackCircleFilled', roles: ['admin'] },
    alwaysShow: true,
    children: [
      {
        path: 'cache',
        name: 'Cache',
        component: () => import('@/views/system/setting/cache/index.vue'),
        hidden: false,
        meta: { title: '缓存管理', icon: 'IeCircleFilled' },
      },
      {
        path: 'site-config',
        name: 'SiteConfig',
        component: () => import('@/views/system/setting/cache/index.vue'),
        hidden: false,
        meta: { title: '网站配置', icon: 'IeCircleFilled' },
      },
    ],
  },
  {
    path: '/',
    name: 'ShortUrl',
    component: Layout,
    alwaysShow: false,
    redirect: '/stats',
    meta: { title: '短链管理', icon: 'SlackCircleFilled', roles: ['user'] },
    children: [
      {
        path: 'stats',
        name: 'Stats',
        meta: { title: '数据统计', icon: 'DotChartOutlined' },
        component: () => import('@/views/joty/stats/index.vue'),
      },
      {
        path: 'short-url',
        name: 'LinkList',
        component: () => import('@/views/joty/shorturl/index.vue'),
        meta: { title: '短链列表', icon: 'IeCircleFilled' },
      },
      {
        path: 'fast-create',
        name: 'FastCreate',
        meta: { title: '快速创建', icon: 'PlusSquareOutlined' },
        component: () => import('@/views/joty/fast-create/index.vue'),
      },
      {
        path: 'batch-create',
        name: 'BatchCreate',
        meta: { title: '批量创建', icon: 'AppstoreAddOutlined' },
        component: () => import('@/views/joty/batch-create/index.vue'),
      },
      {
        path: 'restore-url',
        name: 'RestoreUrl',
        meta: { title: '短链还原', icon: 'AppstoreAddOutlined' },
        component: () => import('@/views/joty/restore-url/index.vue'),
      },
      {
        path: 'open-api',
        name: 'OpenApi',
        meta: { title: '开放接口', icon: 'UngroupOutlined' },
        component: () => import('@/views/joty/open-api/index.vue'),
      },
    ],
  },
]
