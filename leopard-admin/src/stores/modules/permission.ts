import { defineStore } from 'pinia'
import { ref } from 'vue'
import { staticRoutes } from '@/router/static.ts'

interface Menu {}

export const usePermissionStore = defineStore(
  'permission',
  () => {
    const menu = ref<Menu[]>()

    const getMenus = async () => {
      if (!menu.value) {
        //init menu
        menu.value = staticRoutes
        console.log('初始化权限菜单...')
      }
      return menu.value || []
    }

    return { menu, getMenus }
  },
  { persist: true },
)
