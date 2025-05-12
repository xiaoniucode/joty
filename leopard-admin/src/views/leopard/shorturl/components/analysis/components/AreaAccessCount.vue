<script setup lang="ts">
import api from '@/utils/api.ts'
import { stats } from '@/api/leopard/stats.ts'
import { ref } from 'vue'

const props = defineProps<{
  shortCode: string
}>()
const countryColumns = [
  {
    title: '国家',
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
const data = ref([])
api
  .action(stats.getAccessCountByType, {}, { type: 'PROVINCE', shortCode: props.shortCode })
  .then((res: any) => {
    data.value = res
  })
const countryData = ref([])
api
  .action(stats.getAccessCountByType, {}, { type: 'COUNTRY', shortCode: props.shortCode })
  .then((res: any) => {
    countryData.value = res
  })
</script>

<template>
  <a-row :gutter="15">
    <a-col :span="12">
      <h4 align="center">中国省份</h4>
      <a-table :pagination="false" :columns="columns" :data-source="data" />
    </a-col>
    <a-col :span="12">
      <h4 align="center">世界地图</h4>
      <a-table :pagination="false" :columns="countryColumns" :data-source="countryData" />
    </a-col>
  </a-row>
</template>
