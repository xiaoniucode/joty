<script setup lang="ts">
import api from '@/utils/api.ts'
import { stats } from '@/api/leopard/stats.ts'
import { reactive, ref } from 'vue'

const props = defineProps<{
  shortCode: string
}>()
const columns = [
  {
    title: '访问时间',
    name: 'accessTime',
    dataIndex: 'accessTime',
    key: 'accessTime',
    width: 180,
  },
  {
    title: '访问IP',
    name: 'ipAddress',
    dataIndex: 'ipAddress',
    key: 'ipAddress',
    width: 150,
  },
  {
    title: '访客地区',
    name: 'area',
    dataIndex: 'area',
    key: 'area',
  },
  {
    title: '访客来源',
    name: 'referer',
    dataIndex: 'referer',
    key: 'referer',
    width: 150,
    ellipsis: true,
  },
  {
    title: '设备信息',
    name: 'device',
    dataIndex: 'device',
    key: 'device',
  },
  {
    title: '访客类型',
    name: 'visitorType',
    dataIndex: 'visitorType',
    key: 'visitorType',
  },
]
const pageQuery = reactive({
  page: 1,
  size: 5,
})
const data = ref([])
api.action(stats.records, { shortCode: props.shortCode }, pageQuery).then((res: any) => {
  data.value = res.records
})
import googleIcon from '@/assets/icon/browser/google32.svg'
import macOsIcon from '@/assets/icon/browser/mac-os.svg'
import diannao from '@/assets/icon/browser/diannao.svg'
import wifi from '@/assets/icon/browser/wifi.svg'
</script>

<template>
  <a-table :pagination="false" :columns="columns" :data-source="data">
    <template #bodyCell="{ column, record }">
      <template v-if="column.key === 'area'">
        <a-flex vertical>
          <span>{{ record.country }}-{{ record.province }}-{{ record.city }}</span>
          <span class="su-gray" style="color: grey">{{ record.createdAt }}</span>
        </a-flex>
      </template>
      <template v-if="column.key === 'device'">
        <a-flex :gap="10">
          <a-image :preview="false" :width="20" :src="googleIcon" />
          <a-image :preview="false" :width="20" :src="macOsIcon" />
          <a-image :preview="false" :width="20" :src="diannao" />
          <a-image :preview="false" :width="20" :src="wifi" />
        </a-flex>
      </template>
    </template>
  </a-table>
</template>
