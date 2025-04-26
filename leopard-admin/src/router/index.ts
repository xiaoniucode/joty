import { createRouter, createWebHistory } from 'vue-router'
import Layout from '@/layouts/index.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Layout,
      children: [
        {
          path: '/about',
          name: 'about',
          component: () => import('../views/AboutView.vue'),
        },
      ]
    },

  ],
})

export default router
