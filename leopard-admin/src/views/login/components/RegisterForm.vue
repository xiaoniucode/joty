<script setup lang="ts">
import { ref } from 'vue'
import { UserOutlined, LockOutlined } from '@ant-design/icons-vue'
import { message } from 'ant-design-vue'
import { useUserStore } from '@/stores/modules/user.ts'
defineOptions({
  name: 'RegisterForm',
})
const props = defineProps<{
  username?: string
}>()
const emit = defineEmits<{
  onLogin: [username?: string]
}>()
interface FormState {
  username: string
  password: string
}

const formState = ref<FormState>({
  username: props.username ?? '',
  password: '',
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
        注册
      </a-button>
    </a-form-item>
  </a-form>
  <a-flex align="center" justify="center">
    <div>已经有帐号?<a @click="emit('onLogin', formState.username)">去登录</a></div>
  </a-flex>
</template>

<style scoped></style>
