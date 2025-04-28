<template>
  <a-flex gap="small">
    <a-flex class="w-[180px]" justify="space-between">
      <group-list />
      <a-divider type="vertical" style="height: 100%;" />
    </a-flex>

    <a-flex style="flex: 1" vertical>
      <page-header>
        <a-button danger @click="onOpenCreateModal" v-hasPerm="'del'" type="primary"
          >批量删除
        </a-button>
        <a-button @click="onOpenCreateModal" v-hasPerm="'add'" type="primary">新增</a-button>
      </page-header>
      <a-table
        :row-selection="rowSelection"
        :columns="columns"
        :pagination="pagination"
        :data-source="tableData"
      >
        <template #bodyCell="{ column, record }">
          <template v-if="column.key === 'qrUrl'">
            <a-image :src="record.qrUrl" />
          </template>
          <template v-if="column.key === 'name'">
            <a>
              {{ record.name }}
            </a>
          </template>
          <template v-else-if="column.key === 'status'">
            <span>
              <a-tag v-if="record.status == 0" color="volcano"> 禁用 </a-tag>
              <a-tag v-if="record.status == 1" color="green">正常</a-tag>
            </span>
          </template>
          <template v-else-if="column.key === 'action'">
            <span>
              <a>预览</a>
              <a-divider type="vertical" />
              <a>删除</a>
              <a-divider type="vertical" />
              <a @click="onEdit(record)">编辑</a>
            </span>
          </template>
        </template>
      </a-table>
    </a-flex>
  </a-flex>

  <url-form-modal ref="urlFormModalRef" />
</template>
<script lang="ts" setup>
import { computed, onMounted, reactive, ref } from 'vue'
import api from '@/utils/api.ts'
import { short_url } from '@/api/leopard/shorturl.ts'
import PageHeader from '@/components/page-header.vue'
import UrlFormModal from './components/url-form-modal/index.vue'
import GroupList from './components/group-list/index.vue'

const pageQuery = reactive({
  page: 1,
  size: 2,
})
const urlFormModalRef = ref()

const onOpenCreateModal = () => {
  urlFormModalRef.value.showModal()
}
const onEdit = (data: object) => {
  urlFormModalRef.value.showModal(data)
}
const total = ref(0)
const tableData = ref([])
const onLoadTableData = async () => {
  api.action(short_url.list, {}, pageQuery).then((res: any) => {
    tableData.value = res.records
    total.value = res.total
  })
}
onLoadTableData()
onMounted(() => {
  
})

const pagination = computed(() => ({
  total: total.value,
  current: pageQuery.page,
  pageSize: pageQuery.size,
}))
const rowSelection = ref({
  checkStrictly: false,
  onChange: (selectedRowKeys: (string | number)[], selectedRows: any) => {
    console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows)
  },
  onSelect: (record: any, selected: boolean, selectedRows: any) => {
    console.log(record, selected, selectedRows)
  },
  onSelectAll: (selected: boolean, selectedRows: any, changeRows: any) => {
    console.log(selected, selectedRows, changeRows)
  },
})
const columns = [
  {
    name: '标题',
    dataIndex: 'title',
    key: 'title',
  },
  {
    title: '短链接',
    dataIndex: 'shortUrl',
    key: 'shortUrl',
  },
  {
    title: '长链接',
    dataIndex: 'originalUrl',
    key: 'originalUrl',
  },
  {
    title: '二维码',
    dataIndex: 'qrUrl',
    key: 'qrUrl',
  },
  {
    title: '状态',
    key: 'status',
    dataIndex: 'status',
  },
  {
    title: '操作',
    key: 'action',
  },
]
</script>
<style scoped>
@import 'tailwindcss';
</style>
