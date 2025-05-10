<template>
  <a-flex gap="small">
    <a-flex style="flex: 1" vertical>
      <page-header>
        <a-button danger @click="onBatchDelete" type="primary">批量删除</a-button>
        <a-button @click="onOpenCreateModal" type="primary">新增</a-button>
        <div v-if="selectedRowKeys.length > 0">选中: {{ selectedRowKeys.length }} 条</div>
      </page-header>
      <a-table
        :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }"
        :columns="columns"
        :pagination="false"
        :data-source="tableData"
        row-key="id"
        :scroll="{ y: 500 }"
      >
        <template #bodyCell="{ column, record }">
          <template v-if="column.key === 'avatar'">
            <a-image :width="64" :src="record.avatar" fallback="/error_image.png" />
          </template>
          <template v-else-if="column.key === 'role'">
            <span>
              <a-tag v-if="record.role == 'ADMIN'" color="volcano"> 管理员 </a-tag>
              <a-tag v-if="record.role == 'USER'" color="green">会员</a-tag>
            </span>
          </template>
          <template v-else-if="column.key === 'status'">
            <span>
              <a-tag v-if="record.status == 0" color="volcano"> 禁用 </a-tag>
              <a-tag v-if="record.status == 1" color="green">正常</a-tag>
            </span>
          </template>
          <template v-else-if="column.key === 'action'">
            <span>
              <a v-if="record.role != 'ADMIN'" @click="onDelete(record.id)">删除</a>
              <a-divider v-if="record.role != 'ADMIN'" type="vertical" />
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
  <UserForm ref="userFormModalRef" @onSaveSuccess="onSaveSuccess" />
</template>
<script lang="ts" setup>
import { onMounted, reactive, ref, watch } from 'vue'
import api from '@/utils/api.ts'
import PageHeader from '@/components/page-header.vue'
import { message, Modal } from 'ant-design-vue'
import { user } from '@/api/system/user.ts'
import UserForm from './components/user-form-modal.vue'

const pageQuery = reactive({
  page: 1,
  size: 5,
})
const userFormModalRef = ref()

const onOpenCreateModal = () => {
  userFormModalRef.value.showModal(null)
}
const selectedRowKeys = ref<string[]>([])
const onSelectChange = (selectedKeys: string[]) => {
  selectedRowKeys.value = selectedKeys
}
const onBatchDelete = async () => {
  if (selectedRowKeys.value.length < 1) {
    message.warning('请至少选择一条记录')
    return
  }
  Modal.confirm({
    title: '你确定要删除?',
    content: '删除后不可恢复',
    okText: '确认',
    okType: 'danger',
    cancelText: '取消',
    onOk() {
      api.action(user.del, {}, selectedRowKeys.value).then((res: any) => {
        message.success('删除成功')
        onLoadTableData()
      })
    },
  })
}
const onEdit = (data: object) => {
  userFormModalRef.value.showModal(data)
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
const onSaveSuccess = async () => {
  await onLoadTableData()
}
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
    title: '你确定删除改项?',
    content: '删除后不可恢复',
    okText: '确认',
    okType: 'danger',
    cancelText: '取消',
    onOk() {
      api.action(user.del, {}, [id]).then((res: any) => {
        message.success('删除成功')
        onLoadTableData()
      })
    },
  })
}

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
    title: '用户类型',
    key: 'role',
    dataIndex: 'role',
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
