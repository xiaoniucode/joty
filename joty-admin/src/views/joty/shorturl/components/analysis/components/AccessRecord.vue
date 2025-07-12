<script setup lang="ts">
import api from '@/utils/api.ts'
import { stats } from '@/api/joty/stats.ts'
import {onMounted, onUpdated, reactive, ref, watch} from 'vue'

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
    name: 'userType',
    dataIndex: 'userType',
    key: 'userType',
  },
]
const pageQuery = reactive({
  page: 1,
  size: 10,
})
const total = ref(0)
// 数据获取逻辑
const data = ref([])
const fetchData = async () => {
  const res = await <any>api.action(stats.records,
      { shortCode: props.shortCode }, pageQuery)
  data.value = res.records
  total.value = res.total
}


const selectedRowKeys = ref<string[]>([])
const onSelectChange = (selectedKeys: string[]) => {
  selectedRowKeys.value = selectedKeys
}
watch(
    pageQuery,
    (newPage) => {
      fetchData()
    },
    { deep: true },
)
// 初始加载
onMounted(fetchData)
// 暴露刷新方法
defineExpose({ fetchData })
import googleIcon from '@/assets/icon/browser/google32.svg'
import macOsIcon from '@/assets/icon/browser/mac-os.svg'
import diannao from '@/assets/icon/browser/diannao.svg'
import wifi from '@/assets/icon/browser/wifi.svg'
</script>

<template>
  <a-table :pagination="false"   :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }" :columns="columns" :data-source="data">
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
      <template v-if="column.key === 'userType'">
         <a-tag  :bordered="false" :color="record.userType=='新用户'?'success':'orange'">{{record.userType}}</a-tag>
      </template>
    </template>
  </a-table>
  <div>
    <a-pagination
        style="float: right; margin: 15px"
        v-model:current="pageQuery.page"
        v-model:pageSize="pageQuery.size"
        show-size-changer
        :total="total"
    />
  </div>
</template>
