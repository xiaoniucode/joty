import type { App } from 'vue'

export function directives(app: App) {
  authDirective(app)
}

function authDirective(app: App) {
  app.directive('hasPerm', {
    mounted(el, binding) {
      if (!binding.value) return false
      if (true) el.parentNode.removeChild(el)
    },
  })
}
