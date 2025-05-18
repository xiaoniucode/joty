<template>
  <a-flex>
    <a-flex vertical gap="small" style="width: 100%">
      <a-flex justify="space-between">
        <div style="font-size: 16px">短链分组</div>
        <PlusSquareOutlined @click="onOpenGroupModal" />
      </a-flex>
      <a-flex vertical gap="small">
        <DocTree @onDelete="onDeleteGroup" @onSelect="onSelect"   :tree-data="groupList"/>
      </a-flex>
    </a-flex>
    <a-divider type="vertical" style="height: 100vh" />
  </a-flex>

  <group-form-modal ref="groupFormModalRef" @onSaveSuccess="onSaveSuccess" />
</template>
<script lang="ts" setup>
import { ref } from 'vue'
import api from '@/utils/api.ts'
import { group } from '@/api/leopard/group.ts'

import GroupFormModal from '@/views/leopard/shorturl/components/group-form-modal/index.vue'
import { message, Modal } from 'ant-design-vue'
import { PlusSquareOutlined } from '@ant-design/icons-vue'
import DocTree from "@/components/doc-tree/index.vue";

const emit = defineEmits(['onSelectGroup', 'onChange'])
const groupList = ref([])
const selectedKeys = ref<string[]>(['1'])
const loadGroups = async () => {
  const res = await (<any>api.action(group.list))
  groupList.value = res.records || []
}
loadGroups()
//选中节点
const onSelect = (id: string) => {
  emit('onSelectGroup', id)
}
const onSaveSuccess = async () => {
  await loadGroups()
  emit('onChange')
}
//删除分组
const onDeleteGroup = ( id: string) => {
  if (id == '1') {
    message.warn('默认分组不能编辑和删除')
    return
  }
    Modal.confirm({
      title: '你确定删除该分组?',
      content: '删除后短链接自动迁移到默认分组',
      okText: '确认',
      okType: 'danger',
      cancelText: '取消',
      onOk() {
        api.action(group.del, {}, [id]).then((res) => {
          message.success('删除成功')
          loadGroups()
        })
      },
    })
}

const groupFormModalRef = ref()
const onOpenGroupModal = () => {
  groupFormModalRef.value.showModal()
}
const onOpenGroupEdit = (data: object) => {
  groupFormModalRef.value.showModal(data)
}
</script>
