import { defineStore } from 'pinia'
import { ref } from 'vue'
import { staticRoutes } from '@/router/static.ts'
//菜单类型接口定义
interface Menu {}

export const usePermissionStore = defineStore(
  'permission',
  () => {
    //权限菜单数据
    const menu = ref<Menu[]>([])
    //权限菜单初始化状态
    const initializedMenu = ref(false)

    // 获取权限菜单
    const getMenus = async (): Promise<Menu[]> => {
      try {
        // 仅在未初始化时加载静态路由
        if (!initializedMenu.value) {
          console.log('初始化权限菜单...')
          menu.value = staticRoutes
          initializedMenu.value = true
        }
        return menu.value
      } catch (error) {
        console.error('初始化菜单失败:', error)
        return []
      }
    }

    // 重置菜单状态
    const resetMenus = () => {
      menu.value = []
      initializedMenu.value = false
      console.log('菜单状态已重置')
    }

    return {
      menu,
      initializedMenu,
      getMenus,
      resetMenus,
    }
  },
  {
    persist: {
      storage: localStorage,
    },
  },
)
