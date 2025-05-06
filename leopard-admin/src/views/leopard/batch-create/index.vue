<script setup lang="ts">
import { onMounted, reactive, ref, watch } from 'vue'
import api from '@/utils/api.ts'
import { group } from '@/api/leopard/group.ts'
import { message, type TableProps } from 'ant-design-vue'
import { short_url } from '@/api/leopard/shorturl.ts'

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
    title: '有效期',
    dataIndex: 'expiredAt',
    key: 'expiredAt',
    ellipsis: true,
  },
  {
    title: '二维码',
    dataIndex: 'qrUrl',
    key: 'qrUrl',
  },
]
const page = reactive({
  current: 1,
  pageSize: 5,
  total: 0,
})
const handleTableChange = (pag: { pageSize: number; current: number }) => {
  page.pageSize = pag.pageSize
  page.current = pag?.current
}
const tableData = ref([])
const showCreateResult = ref(false)
const onBatchCreate = () => {
  if (formState.urls.trim() == '') {
    message.warning('至少输入一个链接')
    return
  }
  // 按换行符拆分字符串为数组
  const urlArray = formState.urls.split('\n')
  // 去除每项的空格并过滤空项
  const processedUrls = urlArray.map((url) => url.trim()).filter((url) => url !== '')
  const uniqueUrls = [...new Set(processedUrls)];
  api.action(short_url.createBatch, {}, { ...formState, urls: uniqueUrls }).then((res: any) => {
    tableData.value = res || []
    page.total = tableData.value.length
    message.success('创建成功')
  })
  showCreateResult.value = true
}
</script>

<template>
  <div style="padding: 0 10%">
    <a-flex vertical :gap="30">
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
        <div style="color: green">创建结果：</div>
        <a-table
          @change="handleTableChange"
          :pagination="page"
          :columns="columns"
          :data-source="tableData"
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
            <template v-if="column.key === 'expiredAt'">
              <a-tag v-if="!record.expiredAt" :bordered="false" color="processing">永久</a-tag>
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
