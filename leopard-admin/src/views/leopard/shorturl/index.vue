<template>
  <a-flex gap="small">
    <a-flex style="min-width: 180px" justify="space-between">
      <group-list @onSelectGroup="onSelectGroup" @onChange="onGroupChange" />
      <a-divider type="vertical" style="height: 100%" />
    </a-flex>

    <a-flex style="flex: 1" vertical>
      <page-header>
        <a-button danger @click="onBatchDelete" type="primary">批量删除</a-button>
        <a-button @click="onOpenCreateModal" v-hasPerm="'add'" type="primary">新增</a-button>
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
          <template v-if="column.key === 'title'">
            <a-flex vertical>
              <span>{{ record.title }}</span>
              <span style="color:grey">{{ record.createdAt }}</span>
            </a-flex>
          </template>
          <template v-if="column.key === 'status'">
            <a-flex vertical>
              <span>今日  10</span>
              <span>累计  100</span>
            </a-flex>
          </template>
          <template v-if="column.key === 'shortUrl'">
            <span>
              <a-flex :gap="40" justify="space-between" align="center">
                <a-flex vertical :gap="1">
                  <a :href="record.shortUrl" target="_blank">
                    {{ record.shortUrl }}
                  </a>
                  <span class="truncate-url" style="color: gray" :href="record.originalUrl" target="_blank">

                  </span>
                </a-flex>
                <a-flex gap="10">
                  <a-popover trigger="focus" placement="bottom">
                    <template #content>
                      <a-flex vertical align="center" :gap="8">
                        <a-qrcode ref="qrcodeCanvasRef" :value="record.qrUrl" />
                        <a-button type="primary" @click="downloadChanger(record.shortCode)">下载二维码</a-button>
                      </a-flex>
                    </template>
                    <QrcodeOutlined />
                  </a-popover>
                  <CopyOutlined @click="common.copy(record.shortUrl, true)" />
                </a-flex>
              </a-flex>
            </span>
          </template>
          <template v-else-if="column.key === 'expiredAt'">
            <span>
              <a-tag color="orange" :bordered="false" v-if="!record.expiredAt">永不过期</a-tag>
              <span v-else> {{ record.expiredAt }}</span>
            </span>
          </template>
          <template v-else-if="column.key === 'action'">
            <span>
              <a @click="onShowAnalysis(record)">数据</a>
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

  <url-form-modal ref="urlFormModalRef" @onSaveSuccess="onSaveSuccess" />
  <Analysis ref="analysisRef" />
</template>
<script lang="ts" setup>
import { QrcodeOutlined, CopyOutlined } from '@ant-design/icons-vue'
import { computed, onMounted, reactive, ref, watch } from 'vue'
import api from '@/utils/api.ts'
import { short_url } from '@/api/leopard/shorturl.ts'
import PageHeader from '@/components/page-header.vue'
import UrlFormModal from './components/url-form-modal/index.vue'
import GroupList from './components/group-list/index.vue'
import Analysis from './components/analysis/index.vue'
import { message, Modal } from 'ant-design-vue'
import common from '@/utils/common.ts'

const pageQuery = reactive({
  page: 1,
  size: 5,
  groupId: '1',
})
const columns = [
  {
    title: '标题',
    dataIndex: 'title',
    key: 'title',
    width: 200,
    ellipsis: true,
  },
  {
    title: '短链接',
    dataIndex: 'shortUrl',
    key: 'shortUrl',
    ellipsis: true,
  },
  {
    title: '访问次数',
    dataIndex: 'status',
    key: 'status',
    width: 100,
    ellipsis: true,
  },
  {
    title: '访问人数',
    dataIndex: 'ip',
    key: 'ip',
    width: 100,
    ellipsis: true,
  },
  {
    title: '有效期',
    dataIndex: 'expiredAt',
    key: 'expiredAt',
    ellipsis: true,
    width: 120,
  },
  {
    title: '操作',
    key: 'action',
    width: 160,
  },
]
const qrcodeCanvasRef = ref();
const downloadChanger = async (shortCode:string) => {
  const url = qrcodeCanvasRef.value.toDataURL();
  const a = document.createElement('a');
  a.download = `${shortCode}.png`;
  a.href = url;
  document.body.appendChild(a);
  a.click();
  document.body.removeChild(a);
};
const urlFormModalRef = ref()
const analysisRef = ref()

const onOpenCreateModal = () => {
  urlFormModalRef.value.showModal(null)
}
const onGroupChange = async () => {
  await urlFormModalRef.value.flushData()
}
const onEdit = (data: object) => {
  urlFormModalRef.value.showModal(data)
}
const onShowAnalysis = (item: object) => {
  analysisRef.value.showDrawer(item)
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
      api.action(short_url.del, {}, selectedRowKeys.value).then((res: any) => {
        message.success('删除成功')
        onLoadTableData()
      })
    },
  })
}
</script>
<style scoped>
.truncate-url {
  display: inline-block;
  max-width: 100%;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>
