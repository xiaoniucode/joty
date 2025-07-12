import type { App } from 'vue'
import { useUserStore } from '@/stores/modules/user.ts'

export function directives(app: App) {
  authDirective(app)
}

function authDirective(app: App) {
  //权限判断
  // app.directive('hasPerm', {
  //   mounted(el, binding) {
  //     if (!binding.value) return false
  //     if (!useUserStore().hasPerm(binding.value)) el.parentNode.removeChild(el)
  //   },
  // })
  //角色判断
  app.directive('hasRole', {
    mounted(el, binding) {
      if (!binding.value) return false
      if (!useUserStore().hasRole(binding.value)) el.parentNode.removeChild(el)
    },
  })
}
