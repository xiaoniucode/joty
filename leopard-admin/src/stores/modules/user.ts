import { defineStore } from 'pinia'
import { ref } from 'vue'
import api from '@/utils/api.ts'
import { user } from '@/api/system/user.ts'
import { message } from 'ant-design-vue'
import { router } from '@/router'
import { usePermissionStore } from '@/stores/modules/permission.ts'

interface UserInfo {
  uid: undefined
  username: string
  nickname?: string
  email?: string
  avatar: string
  token: string
  roles: string[]
}

const INIT_USERINFO = {
  uid: undefined,
  username: '',
  nickname: '',
  email: '',
  avatar: '',
  token: '',
  roles: [],
}
export const useUserStore = defineStore(
  'user',
  () => {
    const userinfo = ref<UserInfo>({
      ...INIT_USERINFO,
    })
    const hasPerm = (perm: string): boolean => {
      return true
    }
    const getRoles = () => {
      return userinfo.value.roles
    }
    const login = async (loginForm: object) => {
      api.action(user.login, {}, loginForm).then((res: any) => {
        localStorage.setItem('token', res.tokenValue)
        message.success('Login Success')
        //登陆成功以后获取用户信息
        api.action(user.get).then((loginUser: any) => {
          userinfo.value = { ...loginUser, roles: [loginUser.role], token: res.tokenValue }
          //根据不同的角色进行跳转
          router.push({ path: '/dashboard' })
        })
      })
    }
    const resetUserState = () => {
      userinfo.value = { ...INIT_USERINFO }
      localStorage.removeItem('token')
      localStorage.removeItem('user')
    }
    const logout = async () => {
      resetUserState()
      usePermissionStore().resetPermissionState()
      await api.action(user.logout)
      await router.push({ path: '/login' })
    }
    return { logout, login, userinfo, hasPerm, getRoles }
  },
  { persist: true },
)
