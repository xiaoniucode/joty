<script setup lang="ts">
import api from '@/utils/api.ts'
import { stats } from '@/api/leopard/stats.ts'
import { ref } from 'vue'
const props = defineProps<{
  shortCode: string
}>()
const columns = [
  {
    title: 'IP地址',
    name: 'ip',
    dataIndex: 'ip',
    key: 'ip',
  },
  {
    title: 'IP访问次数',
    name: 'count',
    dataIndex: 'count',
    key: 'count',
  },
]
const data = ref([])
api.action(stats.getTopAccessIp, { shortCode: props.shortCode }).then((res: any) => {
  data.value = res
})
</script>

<template>
  <a-table :pagination="false" :columns="columns" :data-source="data"/>
</template>
