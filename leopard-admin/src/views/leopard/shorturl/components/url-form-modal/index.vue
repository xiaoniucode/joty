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
      <a-form-item label="分组" name="groupId">
        <a-select style="width: 40%" v-model:value="formState.groupId" placeholder="选择分组">
          <a-select-option v-for="item in groupData" :value="item.id"
            >{{ item.name }}
          </a-select-option>
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
import { onMounted, ref } from 'vue'

const open = ref<boolean>(false)
const groupData = ref([])
const loadGroupData = async () => {
  const res = await (<any>api.action(group.list, {}, {}))
  groupData.value = res.records
  formState.groupId = groupData.value[0]?.id || '' // 数据加载后更新默认值
}
onMounted(() => {
  loadGroupData()
})

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
import api from '@/utils/api.ts'
import { group } from '@/api/leopard/group.ts'

interface FormState {
  id?: undefined
  title?: string
  originalUrl: string
  expireAt?: Dayjs | undefined
  shortUrl: string
  status: boolean
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
  status: true,
  groupId: '',
  remark: '',
})
const rules: Record<string, Rule[]> = {
  title: [
    { required: false, trigger: 'change' },
    { min: 1, max: 30, message: '长度在1-30内', trigger: 'blur' },
  ],
  originalUrl: [{ required: true, message: '请输入长链接', trigger: 'change' }],
  status: [{ required: true }],
  remark: [{ max: 50, message: '长度在50内', trigger: 'blur' },],
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
