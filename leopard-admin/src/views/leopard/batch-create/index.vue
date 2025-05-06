<script setup lang="ts">
import {onMounted, reactive, ref, watch} from 'vue'
import api from '@/utils/api.ts'
import { group } from '@/api/leopard/group.ts'

const formState = reactive({
  urls: '',
  expiredAt: undefined,
  groupId: '1',
})
const groupData = ref([])
const loadGroupData = async () => {
  const res = await (<any>api.action(group.list, {}, {}))
  groupData.value = res.records
  formState.groupId = groupData.value[0]?.id || '' // 数据加载后更新默认值
}
onMounted(() => {
  loadGroupData()
})
const columns = [
  {
    title: '标题',
    dataIndex: 'title',
    key: 'title',
    ellipsis: true,
  },
  {
    title: '短链接',
    dataIndex: 'shortUrl',
    key: 'shortUrl',
    width: 224,
    ellipsis: true,
  },
  {
    title: '长链接',
    dataIndex: 'originalUrl',
    key: 'originalUrl',
    ellipsis: true,
  },
  {
    title: '二维码',
    dataIndex: 'qrUrl',
    key: 'qrUrl',
  },
]
const tableData = ref([])
const showCreateResult=ref(false)
const onBatchCreate=()=>{
  showCreateResult.value=true
}
</script>

<template>
  <div style="padding: 0 10%">
    <a-flex vertical :gap="50">
      <a-flex vertical :gap="12">
        <a-textarea
          placeholder="每行一个链接，回车换行"
          :rows="10"
          v-model:value="formState.urls"
        />
        <a-flex justify="space-between" align="center">
          <a-flex gap="15" align="center">
            <span>有效期:</span>
            <a-date-picker
              v-model:value="formState.expiredAt"
              type="date"
              size="small"
              :valueFormat="'YYYY-MM-DD'"
              placeholder="默认永久"
              style="width: 120px"
            />
            <span>分组:</span>
            <a-select
              size="small"
              style="min-width: 120px"
              v-model:value="formState.groupId"
              placeholder="选择分组"
            >
              <a-select-option v-for="item in groupData" :value="item.id"
                >{{ item.name }}
              </a-select-option>
            </a-select>
          </a-flex>
          <a-button @click="onBatchCreate" size="small" type="primary">立即创建</a-button>
        </a-flex>
      </a-flex>

      <a-flex :gap="4" vertical v-if="showCreateResult">
        <div style="color:green">创建结果：</div>
        <a-table
            :columns="columns"
            :pagination="false"
            :data-source="tableData"
            :scroll="{ y: 500 }"
        >
          <template #bodyCell="{ column, record }">
            <template v-if="column.key === 'shortUrl'">
              <a :href="record.shortUrl" target="_blank">
                {{ record.shortUrl }}
              </a>
            </template>
            <template v-if="column.key === 'qrUrl'">
              <a-image :width="64" :src="record.qrUrl" fallback="/error_image.png" />
            </template>
          </template>
        </a-table>
      </a-flex>
    </a-flex>
  </div>
</template>

<style scoped>
@import 'tailwindcss';
</style>
