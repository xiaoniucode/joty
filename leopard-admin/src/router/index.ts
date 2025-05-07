import { createRouter, createWebHistory } from 'vue-router'
import { staticRoutes } from '@/router/static.ts'
import { asyncRoutes } from '@/router/async-routes.ts'
//合并路由 静态路由 异步路由
export const constantRoutes = [...staticRoutes, ...asyncRoutes]
export const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: constantRoutes,
})

// todo 测试动态构建路由
import Layout from '@/layouts/index.vue'

export const buildRoute = () => {
  const modules = import.meta.glob('@/views/**/*.vue', { eager: false })
  console.log('模块列表:', modules)
  // 定义动态路由
  const dynamicRoutes = [
    {
      path: '/reg',
      name: 'Reg',
      component: modules['/src/views/register/index.vue'],
      meta: { title: '注册' },
    },
  ]
  // 添加父路由，包含 Layout 和子路由
  router.addRoute({
    path: '/',
    name: 'Layout',
    component: Layout,
    meta: { title: '布局' },
    children: dynamicRoutes,
  })
}
