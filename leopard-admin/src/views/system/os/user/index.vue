<template>
  <a-flex gap="small">


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
          :pagination="false"
          :data-source="tableData"
          :scroll="{ y: 500 }"
      >
        <template #bodyCell="{ column, record }">
          <template v-if="column.key === 'avatar'">
            <a-image :width="64" :src="record.avatar" fallback="/error_image.png" />
          </template>
          <template v-else-if="column.key === 'status'">
            <span>
              <a-tag v-if="record.status == 0" color="volcano"> 禁用 </a-tag>
              <a-tag v-if="record.status == 1" color="green">正常</a-tag>
            </span>
          </template>
          <template v-else-if="column.key === 'action'">
            <span>
              <a :href="record.shortUrl" target="_blank">预览</a>
              <a-divider type="vertical" />
              <a @click="onDelete(record.id)">删除</a>
              <a-divider type="vertical" />
              <a @click="onEdit(record)">编辑</a>
            </span>
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
    </a-flex>
  </a-flex>

</template>
<script lang="ts" setup>
import { onMounted, reactive, ref, watch } from 'vue'
import api from '@/utils/api.ts'
import { short_url } from '@/api/leopard/shorturl.ts'
import PageHeader from '@/components/page-header.vue'

import { message, Modal } from 'ant-design-vue'
import {user} from "@/api/system/user.ts";

const pageQuery = reactive({
  page: 1,
  size: 5,
})
const urlFormModalRef = ref()

const onOpenCreateModal = () => {
  urlFormModalRef.value.showModal(null)
}
const onEdit = (data: object) => {
  urlFormModalRef.value.showModal(data)
}
const total = ref(0)
const tableData = ref([])
const onLoadTableData = async () => {
  api.action(user.list, {}, pageQuery).then((res: any) => {
    tableData.value = res.records
    total.value = res.total
  })
}
onLoadTableData()
onMounted(() => {})
watch(
    pageQuery,
    (newPage) => {
      onLoadTableData()
    },
    { deep: true },
)
const onDelete = async (id: string) => {
  Modal.confirm({
    title: '你确定删除该短链接?',
    content: '删除后不可恢复',
    okText: '确认',
    okType: 'danger',
    cancelText: '取消',
    onOk() {
      api.action(short_url.del, {}, [id]).then((res: any) => {
        message.success('删除成功')
        onLoadTableData()
      })
    },
  })
}
const onSelectGroup = async (groupId: any) => {
  pageQuery.groupId = groupId
  pageQuery.page = 1
  pageQuery.size = 5
  await onLoadTableData()
}
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
    title: '用户名',
    dataIndex: 'username',
    key: 'username',
    width: 150,
    ellipsis: true,
  },
  {
    title: '昵称',
    dataIndex: 'nickname',
    key: 'nickname',
    ellipsis: true,
  },
  {
    title: '邮箱',
    dataIndex: 'email',
    key: 'email',
    ellipsis: true,
  },
  {
    title: '头像',
    dataIndex: 'avatar',
    key: 'avatar',
  },
  {
    title: '状态',
    key: 'status',
    dataIndex: 'status',
  },
  {
    title: '注册时间',
    key: 'createdAt',
    dataIndex: 'createdAt',
    ellipsis: true,

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
