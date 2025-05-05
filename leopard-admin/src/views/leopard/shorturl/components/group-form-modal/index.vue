<template>
  <a-modal
    ok-text="保存"
    cancel-text="取消"
    width="40%"
    v-model:open="open"
    :title="isEdit ? '编辑' : '新增'"
    @ok="handleOk"
  >
    <a-form ref="formRef" :model="formState" :rules="rules" :label-col="{ span: 3 }">
      <a-form-item label="名字" name="name">
        <a-input v-model:value="formState.name" />
      </a-form-item>
      <a-form-item label="备注" name="remark">
        <a-textarea v-model:value="formState.remark" />
      </a-form-item>
      <a-form-item label="排序" name="sort">
        <a-input-number id="inputNumber" v-model:value="formState.sort" :min="0" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>
<script lang="ts" setup>
import { ref } from 'vue'
import { reactive, toRaw } from 'vue'
import type { UnwrapRef } from 'vue'
import type { Rule } from 'ant-design-vue/es/form'
import { message } from 'ant-design-vue'
import api from '@/utils/api.ts'
import { group } from '@/api/leopard/group.ts'
const emit=defineEmits(['onSaveSuccess'])
const open = ref<boolean>(false)
const formRef = ref()
const showModal = (data = null) => {
  open.value = true
  if (data == null) {
    isEdit.value = false
    resetForm()
  } else {
    isEdit.value = true
    api.action(group.get, { id: data.id }).then((res: any) => {
      Object.assign(formState, res)
    })
  }
}
defineExpose({
  showModal,
})
const isEdit = ref(false)
const handleOk = (e: MouseEvent) => {
  formRef.value.validate().then(() => {
    api
      .action(group.save, {}, toRaw(formState))
      .then((res: any) => {
        open.value = false
        message.success(isEdit.value ? '更新成功' : '已创建')
        emit('onSaveSuccess')
      })
      .finally(() => {
        open.value = false
      })
  })
}

interface FormState {
  id: undefined
  name: string
  remark?: string
  sort: number
}

const formState: UnwrapRef<FormState> = reactive({
  id: undefined,
  name: '',
  remark: '',
  sort: 0,
})
const rules: Record<string, Rule[]> = {
  name: [
    { required: true, message: '不能为空', trigger: 'change' },
    { min: 1, max: 10, message: '长度在1-10个字范围', trigger: 'blur' },
  ],
}

const resetForm = () => {
  formRef.value.resetFields()
}
</script>
