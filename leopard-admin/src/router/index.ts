import { createRouter, createWebHistory } from 'vue-router'
import Layout from '@/layouts/index.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: () => import('@/views/login/index.vue'),
    },
    {
      path: '/503',
      name: '503',
      component: () => import('@/views/error/503.vue'),
    },
    {
      path: '/',
      name: 'layout',
      component: Layout,
      children: [
        {
          path: '/dashboard',
          name: 'dashboard',
          meta:{
          },
          component: () => import('@/views/dashboard/index.vue'),
        },
      ]
    },

  ],
})

export default router
