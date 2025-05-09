import { createRouter, createWebHistory } from 'vue-router'
import { staticRoutes } from '@/router/static.ts'
import { asyncRoutes } from '@/router/async-routes.ts'
//@ts-ignore
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import Layout from '@/layouts/index.vue'
import { useUserStore } from '@/stores/modules/user.ts'
//合并路由 静态路由 异步路由
export const constantRoutes = [...staticRoutes, ...asyncRoutes]
export const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: constantRoutes,
})
const LOGIN_PATH='/login'
router.beforeEach(async (to, from, next) => {
  //开启进度条
  NProgress.start()

  const token = useUserStore().getToken()
  if (!token) {
    await useUserStore().logout()
    if (to.path === LOGIN_PATH) {
      next()
    } else {
      next({ path: LOGIN_PATH })
    }
    return
  }

  // 登录页，则跳转到首页
  if (to.path === LOGIN_PATH) {
    next({
      path: useUserStore().isAdmin() ? '/dashboard' : '/stats',
    })
    return
  }

  next()
})
router.afterEach(() => {
  //关闭进度条
  NProgress.done()
})
// todo 测试动态构建路由

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
