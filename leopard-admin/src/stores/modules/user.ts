import { defineStore } from 'pinia'
import { ref } from 'vue'
import api from '@/utils/api.ts'
import { user } from '@/api/system/user.ts'
import { message } from 'ant-design-vue'
import router from '@/router'

export const useUserStore = defineStore(
  'user',
  () => {
    const userinfo = ref({
      uid: '',
      username: '',
      email: '',
      avatar: '',
      token: '',
      menus: [],
      initialize: false,
    })
    const hasPerm = (perm: string): boolean => {
      return true
    }
    const login = async (loginForm: object) => {
      api.action(user.login, {}, loginForm).then((res: any) => {
        localStorage.setItem('token', res.tokenValue)
        message.success('Login Success')
        router.push({ path: '/dashboard' })
      })
    }

    const logout = async () => {
      api.action(user.logout).then(() => {
        localStorage.removeItem('token')
        router.push({ path: '/login' })
      })
    }
    return { logout, login, userinfo, hasPerm }
  },
  { persist: true },
)
