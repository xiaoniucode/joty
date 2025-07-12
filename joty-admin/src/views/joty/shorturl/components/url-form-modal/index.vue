<template>
  <a-modal
    ok-text="保存"
    cancel-text="取消"
    width="50%"
    v-model:open="open"
    :title="isEdit ? '编辑' : '新增'"
    @ok="handleOk"
  >
    <a-form ref="formRef" :model="formState" :rules="rules" :label-col="{ span: 3 }">
      <a-form-item label="标题" name="title">
        <a-input v-model:value="formState.title" />
      </a-form-item>
      <a-form-item label="长链接" name="originalUrl">
        <a-input :disabled="isEdit" v-model:value="formState.originalUrl" />
      </a-form-item>
      <a-form-item v-if="isEdit" label="短链接" name="shortUrl">
        <a target="_blank" :href="formState.shortUrl">{{ formState.shortUrl }}</a>
      </a-form-item>
      <a-form-item v-if="isEdit" label="二维码" name="shortUrl">
        <a-image :width="80" :height="80" :src="formState.qrUrl" fallback="/error_image.png" />
      </a-form-item>
      <a-form-item v-if="isEdit" label="域名" name="domain">
        <a-radio-group v-model:value="formState.domain" name="radioGroup">
          <a-radio :value="formState.domain">{{formState.domain}}</a-radio>
        </a-radio-group>
      </a-form-item>

      <a-form-item label="分组" name="groupId">
        <a-select style="width: 40%" v-model:value="formState.groupId" placeholder="选择分组">
          <a-select-option v-for="item in groupData" :value="item.id"
            >{{ item.name }}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="有效期" name="expiredAt">
        <a-date-picker
          :disabled="isEdit"
          v-model:value="formState.expiredAt"
          type="date"
          :valueFormat="'YYYY-MM-DD'"
          placeholder="永久"
          style="width: 30%"
        />
      </a-form-item>
      <a-form-item label="备注" name="remark">
        <a-textarea v-model:value="formState.remark" />
      </a-form-item>
      <a-form-item label="状态" name="status">
        <a-radio-group v-model:value="formState.status" name="statusGroup">
          <a-radio :value="1">正常</a-radio>
          <a-radio :value="0">禁用</a-radio>
        </a-radio-group>
      </a-form-item>
    </a-form>
  </a-modal>
</template>
<script lang="ts" setup>
import { onMounted, ref } from 'vue'
import dayjs, { Dayjs } from 'dayjs'
import { reactive, toRaw } from 'vue'
import type { UnwrapRef } from 'vue'
import type { Rule } from 'ant-design-vue/es/form'
import api from '@/utils/api.ts'
import { group } from '@/api/joty/group.ts'
import { short_url } from '@/api/joty/shorturl.ts'
import { message } from 'ant-design-vue'

const open = ref<boolean>(false)
const formRef = ref()
const emit = defineEmits(['onSaveSuccess'])
const groupData = ref([])
const loadGroupData = async () => {
  const res = await (<any>api.action(group.list, {}, {}))
  groupData.value = res.records
  formState.groupId = groupData.value[0]?.id || '' // 数据加载后更新默认值
}
onMounted(() => {
  loadGroupData()
})
const isEdit = ref(false)
const showModal = (data: object) => {
  open.value = true
  if (data == null) {
    isEdit.value = false
    resetForm()
  } else {
    isEdit.value = true
    Object.assign(formState, { ...data, expiredAt: data.expiredAt ? dayjs(data.expiredAt) : null })
  }
}
const flushData=async ()=>{
  await loadGroupData()
}
defineExpose({
  showModal,
  flushData
})

const handleOk = (e: MouseEvent) => {
  formRef.value.validate().then(() => {
    const payload = {
      ...toRaw(formState),
      // 只有当expiredAt有值时才格式化
      expiredAt: formState.expiredAt ? dayjs(formState.expiredAt).format('YYYY-MM-DD') : null
    }
    if (!isEdit.value) {
      delete payload.shortUrl
    }
    api
      .action(isEdit.value ? short_url.update : short_url.create, {}, payload)
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
// 定义初始状态常量
const INITIAL_STATE = {
  id: undefined,
  title: '',
  originalUrl: '',
  expiredAt: null,
  status: 1,
  groupId: '',
  remark: '',
}

interface FormState {
  id?: undefined
  title?: string
  originalUrl: string
  shortUrl?: string
  qrUrl?: string
  expiredAt?: string | Dayjs|null
  status: number
  groupId: string
  remark: string
}

const formState: UnwrapRef<FormState> = reactive({
  ...INITIAL_STATE,
  shortUrl: '',
  qrUrl: '',
})
const rules: Record<string, Rule[]> = {
  title: [
    { required: true, trigger: 'change' },
    { min: 1, max: 50, message: '长度在1-50内', trigger: 'blur' },
  ],
  originalUrl: [{ required: true, message: '请输入长链接', trigger: 'change' }],
  status: [{ required: true }],
  groupId: [{ required: true, message: '必须指定一个分组', trigger: 'change' }],
  domain: [{ required: true, message: '必须选择一个域名', trigger: 'change' }],
  remark: [{ max: 50, message: '长度在50内', trigger: 'blur' }],
  expiredAt: [
    {
      validator: (_, value) => {
        if (value && dayjs(value).isBefore(dayjs(), 'day')) {
          return Promise.reject('过期时间不能早于当前时间')
        }
        return Promise.resolve()
      }
    }
  ]
}
const resetForm = () => {
  formRef.value?.resetFields()
  Object.assign(formState, {
    ...INITIAL_STATE,
    groupId: groupData.value[0]?.id || '',
    expiredAt: null
  })
}
</script>
