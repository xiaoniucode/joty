import { createRouter, createWebHistory } from 'vue-router'
import Layout from '@/layouts/index.vue'

export const staticRoutes = [
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/login/index.vue'),
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
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: staticRoutes,
})

export default router
