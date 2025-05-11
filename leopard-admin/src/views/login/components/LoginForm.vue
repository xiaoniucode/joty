<script setup lang="ts">
import { ref } from 'vue'
import { UserOutlined, LockOutlined } from '@ant-design/icons-vue'
import { message } from 'ant-design-vue'
import { useUserStore } from '@/stores/modules/user.ts'
import { useAppStore } from '@/stores/modules/app.ts'

defineOptions({
  name: 'LoginForm',
})
const props = defineProps<{
  username?: string
}>()
const emit = defineEmits<{
  onRegister: [account?: string]
  onResetPassword: [account?: string]
}>()

interface FormState {
  username: string
  password: string
}

const formState = ref<FormState>({
  username: 'admin',
  password: '123456',
})

const loading = ref(false)
const userStore = useUserStore()
const onFinish = (values: FormState) => {
  loading.value = true
  userStore.login(values).then((res) => {
    loading.value = false
    message.success('登陆成功！')
  })
}

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo)
}
const appStore = useAppStore()
const enableRegister = appStore.config.enableRegister
</script>

<template>
  <a-form
    :model="formState"
    name="login"
    class="login-form"
    @finish="onFinish"
    @finishFailed="onFinishFailed"
  >
    <a-form-item name="username" :rules="[{ required: true, message: '请输入用户名!' }]">
      <a-input v-model:value="formState.username" placeholder="用户名/邮箱" size="large">
        <template #prefix>
          <UserOutlined />
        </template>
      </a-input>
    </a-form-item>

    <a-form-item name="password" :rules="[{ required: true, message: '请输入密码!' }]">
      <a-input-password v-model:value="formState.password" placeholder="密码" size="large">
        <template #prefix>
          <LockOutlined />
        </template>
      </a-input-password>
    </a-form-item>

    <a-form-item>
      <a-button type="primary" html-type="submit" size="large" :loading="loading" block>
        登录
      </a-button>
    </a-form-item>
  </a-form>
  <a-flex v-if="enableRegister" align="center" justify="center">
    <div>还没有帐号?<a @click="emit('onRegister', formState.username)"> 注册新帐号</a></div>
  </a-flex>
</template>

<style scoped></style>
