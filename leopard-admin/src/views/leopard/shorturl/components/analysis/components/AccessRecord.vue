<script setup lang="ts">
import api from '@/utils/api.ts'
import { stats } from '@/api/leopard/stats.ts'
import { reactive, ref } from 'vue'

const props = defineProps<{
  shortCode: string
}>()
const columns = [
  {
    title: '省份',
    name: 'name',
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: '访问次数',
    name: 'count',
    dataIndex: 'count',
    key: 'count',
  },
]
const pageQuery = reactive({
  page: 1,
  size: 5,
})
const data = ref([])
api
  .action(stats.getAccessCountByType, { shortCode: props.shortCode }, pageQuery)
  .then((res: any) => {
    data.value = res
  })
</script>

<template>
  <a-table :pagination="false" :columns="columns" :data-source="data" />
</template>
