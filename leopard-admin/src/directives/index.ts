import type { App } from 'vue'
import { useUserStore } from '@/stores/modules/user.ts'

export function directives(app: App) {
  authDirective(app)
}

function authDirective(app: App) {
  app.directive('hasPerm', {
    mounted(el, binding) {
      if (!binding.value) return false
      if (!useUserStore().hasPerm(binding.value)) el.parentNode.removeChild(el)
    },
  })
}
