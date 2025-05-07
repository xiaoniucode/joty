import { defineStore } from 'pinia'
import { ref } from 'vue'
import { constantRoutes } from '@/router'


//菜单类型接口定义
interface Menu {}

export const usePermissionStore = defineStore(
  'permission',
  () => {
    //权限菜单数据
    const menu = ref<Menu[]>([])
    //权限菜单初始化状态
    const initializedMenu = ref(false)
    // 获取权限菜单|可指定角色进行筛选
    const getMenus = async (roles: string[] = []): Promise<Menu[]> => {
      try {
        // 仅在未初始化时加载菜单路由
        if (!initializedMenu.value) {
          console.log('初始化权限菜单...')
          menu.value = filterAsyncRoutes(constantRoutes, roles)
          initializedMenu.value = true
        }
        return menu.value
      } catch (error) {
        console.error('初始化菜单失败:', error)
        return []
      }
    }
    /**
     * 根据角色过滤权限菜单
     * @param routes 路由列表
     * @param roles 用户角色列表
     */
    const filterAsyncRoutes = (routes: any[] = [], roles: string[] = []) => {
      const res: any = []
      routes.forEach((route) => {
        const tmp = { ...route }
        if (hasRole(roles, tmp)) {
          if (tmp.children) {
            tmp.children = filterAsyncRoutes(tmp.children, roles)
          }
          res.push(tmp)
        }
      })

      return res
    }
    //判断某一个路由是否有对应角色权限
    const hasRole = (roles: any[], route: any) => {
      if (route.meta && route.meta.roles) {
        return roles.some((role) => route.meta.roles.includes(role))
      } else {
        return true
      }
    }

    // 重置菜单状态
    const resetPermissionState = () => {
      menu.value = []
      initializedMenu.value = false
      console.log('菜单状态已重置')
    }

    return {
      menu,
      initializedMenu,
      getMenus,
        resetPermissionState,
    }
  },
  {
    persist: {
      storage: sessionStorage,
    },
  },
)
