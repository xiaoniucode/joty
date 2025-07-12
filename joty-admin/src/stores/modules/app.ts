import { defineStore } from 'pinia'
import { setting } from '@/setting.ts'
import { reactive } from 'vue'

export const useAppStore = defineStore(
  'app',
  () => {
    const config = reactive({ ...setting })

      return {config}
  },
  { persist: true },
)
