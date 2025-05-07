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
    const getToken = () => {
      return userinfo.value.token
    }
    const isAdmin=()=>{
        return userinfo.value.roles.includes('admin')
    }
    const login = async (loginForm: object) => {
      api.action(user.login, {}, loginForm).then((res: any) => {
        userinfo.value = { ...INIT_USERINFO, token: res.tokenValue }
        message.success('登陆成功')
        //登陆成功以后获取用户信息
        api.action(user.get).then((loginUser: any) => {
          userinfo.value = { ...loginUser, roles: [loginUser.role], token: res.tokenValue }
          //根据不同的角色进行跳转
          if (loginUser.role == 'admin') {
            router.push({ path: '/dashboard' })
          } else {
            router.push({ path: '/stats' })
          }
        })
      })
    }
    const resetUserState = () => {
      userinfo.value = { ...INIT_USERINFO }
    }
    const logout = async () => {
      try {
        await api.action(user.logout)
        resetUserState()
        usePermissionStore().resetPermissionState()
        message.success('退出成功')
        await router.push({ path: '/login' })
      } catch (err) {
        resetUserState()
        usePermissionStore().resetPermissionState()
      }
    }
    return { logout, login, userinfo, hasPerm, getRoles, getToken ,isAdmin}
  },
  { persist: true },
)
