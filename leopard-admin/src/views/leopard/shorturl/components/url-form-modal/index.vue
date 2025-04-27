<template>
  <a-modal
    ok-text="创建"
    cancel-text="取消"
    width="40%"
    v-model:open="open"
    title="新增短链接"
    @ok="handleOk"
  >
    <a-form ref="formRef" :model="formState" :rules="rules" :label-col="{ span: 3 }">
      <a-form-item label="标题" name="title">
        <a-input v-model:value="formState.title" />
      </a-form-item>
      <a-form-item label="长链接" name="originalUrl">
        <a-input v-model:value="formState.originalUrl" />
      </a-form-item>
      <a-form-item label="分组" name="region">
        <a-select style="width: 40%" v-model:value="formState.groupId" placeholder="选择分组">
          <a-select-option value="1">默认分组</a-select-option>
          <a-select-option value="2">电商系统</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="有效期" name="expireAt">
        <a-date-picker
          v-model:value="formState.expireAt"
          type="date"
          placeholder="默认永久"
          style="width: 30%"
        />
      </a-form-item>
      <a-form-item label="状态" name="status">
        <a-switch v-model:checked="formState.status" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>
<script lang="ts" setup>
import { ref } from 'vue'

const open = ref<boolean>(false)

const showModal = (data: object) => {
  open.value = true
  Object.assign(formState, data)
}
defineExpose({
  showModal,
})
const handleOk = (e: MouseEvent) => {
  console.log(e)
  open.value = false
}
import { Dayjs } from 'dayjs'
import { reactive, toRaw } from 'vue'
import type { UnwrapRef } from 'vue'
import type { Rule } from 'ant-design-vue/es/form'

interface FormState {
  id?: undefined
  title?: string
  originalUrl: string
  expireAt?: Dayjs | undefined
  shortUrl: string
  status: number
  groupId: string
  remark: string
}

const formRef = ref()

const formState: UnwrapRef<FormState> = reactive({
  id: undefined,
  title: '',
  originalUrl: '',
  expireAt: undefined,
  shortUrl: '',
  status: 1,
  groupId: '',
  remark: '',
})
const rules: Record<string, Rule[]> = {
  title: [
    { required: false, message: 'Please input Activity name', trigger: 'change' },
    { min: 3, max: 5, message: 'Length should be 3 to 5', trigger: 'blur' },
  ],
  originalUrl: [{ required: true, message: 'Please select Activity zone', trigger: 'change' }],
  expireAt: [{ required: true, message: 'Please pick a date', trigger: 'change', type: 'object' }],
  status: [
    {
      type: 'array',
      required: true,
      message: 'Please select at least one activity type',
      trigger: 'change',
    },
  ],
  groupId: [{ required: true, message: 'Please select activity resource', trigger: 'change' }],
  remark: [{ required: true, message: 'Please input activity form', trigger: 'blur' }],
}
const onSubmit = () => {
  formRef.value
    .validate()
    .then(() => {
      console.log('values', formState, toRaw(formState))
    })
    .catch((error) => {
      console.log('error', error)
    })
}
const resetForm = () => {
  formRef.value.resetFields()
}
</script>
