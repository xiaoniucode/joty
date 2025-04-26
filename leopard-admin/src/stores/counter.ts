import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import api from '@/utils/api.ts'
import { user } from '@/api/system/user.ts'

export const useCounterStore = defineStore(
  'counter',
  () => {
    const count = ref(0)
    const doubleCount = computed(() => count.value * 2)

    async function increment() {
      count.value++

    }

    return { count, doubleCount, increment }
  },
  {
    persist: true,
  },
)
