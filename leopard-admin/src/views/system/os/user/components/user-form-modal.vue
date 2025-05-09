<template>
  <a-modal
    ok-text="保存"
    cancel-text="取消"
    width="50%"
    v-model:open="open"
    :title="isEdit ? '编辑用户' : '新增用户'"
    @ok="handleOk"
  >
    <a-form ref="formRef" :model="formState" :rules="rules" :label-col="{ span: 3 }">
      <a-form-item label="用户名" name="username">
        <a-input v-model:value="formState.username" :disabled="isEdit" placeholder="请输入用户名" />
      </a-form-item>

      <a-form-item label="昵称" name="nickname">
        <a-input v-model:value="formState.nickname" placeholder="请输入昵称" />
      </a-form-item>

      <a-form-item label="邮箱" name="email">
        <a-input v-model:value="formState.email" placeholder="请输入邮箱" />
      </a-form-item>

      <a-form-item label="手机号" name="mobile">
        <a-input v-model:value="formState.mobile" placeholder="请输入手机号" />
      </a-form-item>

      <a-form-item label="头像" name="avatar">
        <a-upload
          v-model:file-list="fileList"
          list-type="picture-card"
          :show-upload-list="false"
          :before-upload="beforeUpload"
        >
          <a-image v-if="formState.avatar" :src="formState.avatar" :preview="false" />
          <div v-else>
            <plus-outlined />
            <div style="margin-top: 8px">上传</div>
          </div>
        </a-upload>
      </a-form-item>

      <a-form-item label="密码" name="password" v-if="!isEdit">
        <a-input-password v-model:value="formState.password" placeholder="请输入密码" />
      </a-form-item>

      <a-form-item label="确认密码" name="confirmPassword" v-if="!isEdit">
        <a-input-password v-model:value="formState.confirmPassword" placeholder="请再次输入密码" />
      </a-form-item>
      <a-form-item v-if="formState.role!='ADMIN'" label="状态" name="status">
        <a-radio-group v-model:value="formState.status">
          <a-radio :value="1">正常</a-radio>
          <a-radio :value="0">禁用</a-radio>
        </a-radio-group>
      </a-form-item>

      <a-form-item label="备注" name="remark">
        <a-textarea v-model:value="formState.remark" placeholder="请输入备注" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script lang="ts" setup>
import { ref, reactive } from 'vue'
import { message } from 'ant-design-vue'
import { PlusOutlined } from '@ant-design/icons-vue'
import type { UploadProps } from 'ant-design-vue'
import api from '@/utils/api.ts'

const open = ref(false)
const formRef = ref()
const emit = defineEmits(['onSaveSuccess'])

// 表单状态
interface FormState {
  id?: string | undefined
  username: string
  nickname: string
  email: string
  mobile: string
  avatar: string
  password: string
  confirmPassword: string
  status: number
  remark: string
  role: string
}

const INITIAL_STATE: FormState = {
  id: undefined,
  username: '',
  nickname: '',
  email: '',
  mobile: '',
  avatar: '',
  password: '',
  confirmPassword: '',
  status: 1,
  remark: '',
  role: '',
}

const formState = reactive({ ...INITIAL_STATE })

// 上传处理
const fileList = ref([])
const beforeUpload: UploadProps['beforeUpload'] = (file) => {
  const isImage = file.type.startsWith('image/')
  if (!isImage) {
    message.error('只能上传图片文件!')
    return false
  }
  const reader = new FileReader()
  reader.readAsDataURL(file)
  reader.onload = () => {
    formState.avatar = reader.result as string
  }
  return false
}

// 表单验证规则
const rules = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 2, max: 20, message: '用户名长度2-20个字符', trigger: 'blur' },
  ],
  nickname: [
    { required: true, message: '请输入昵称', trigger: 'blur' },
    { max: 30, message: '昵称最多30个字符', trigger: 'blur' },
  ],
  email: [{ type: 'email', message: '请输入有效的邮箱地址', trigger: 'blur' }],
  mobile: [{ pattern: /^1[3-9]\d{9}$/, message: '请输入有效的手机号', trigger: 'blur' }],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 20, message: '密码长度6-20个字符', trigger: 'blur' },
  ],
  confirmPassword: [
    { required: true, message: '请确认密码', trigger: 'blur' },
    {
      validator: (_, value) =>
        value === formState.password ? Promise.resolve() : Promise.reject('两次输入的密码不一致'),
    },
  ],
}

// 表单操作
const isEdit = ref(false)
const showModal = (data: object | null) => {
  open.value = true
  if (!data) {
    isEdit.value = false
    resetForm()
  } else {
    isEdit.value = true
    Object.assign(formState, data)
    // 编辑时不显示密码字段
    formState.password = ''
    formState.confirmPassword = ''
  }
}

const resetForm = () => {
  formRef.value?.resetFields()
  Object.assign(formState, INITIAL_STATE)
}

const handleOk = () => {
  formRef.value.validate().then(() => {
    const payload = { ...toRaw(formState) }
    // 编辑时不更新密码
    if (isEdit.value) {
      delete payload.password
      delete payload.confirmPassword
    }

    api
      .action(isEdit.value ? user.update : user.create, {}, payload)
      .then(() => {
        message.success(isEdit.value ? '更新成功' : '创建成功')
        open.value = false
        emit('onSaveSuccess')
      })
      .catch((err) => {
        message.error(err.message || '操作失败')
      })
  })
}

defineExpose({
  showModal,
})
</script>
