<template>
  <a-row>
    <a-col :span="12"  class="login-bg">


    </a-col>
    <a-col :span="12">
      <div class="flex justify-center px-[5%] pt-[10%]">
        <a-form
            :model="formState"
            name="basic"
            autocomplete="off"
            @finish="onFinish"
            @finishFailed="onFinishFailed"
        >
          <a-form-item
              label="用户名"
              name="username"
              :rules="[{ required: true, message: 'Please input your username!' }]"
          >
            <a-input v-model:value="formState.username" />
          </a-form-item>

          <a-form-item
              label="密码"
              name="password"
              :rules="[{ required: true, message: 'Please input your password!' }]"
          >
            <a-input-password v-model:value="formState.password" />
          </a-form-item>

          <a-form-item name="remember">
            <a-checkbox v-model:checked="formState.remember">记住我</a-checkbox>
          </a-form-item>

          <a-form-item>
            <a-button type="primary" html-type="submit">登陆</a-button>
          </a-form-item>
        </a-form>
      </div>

    </a-col>
  </a-row>

</template>
<script lang="ts" setup>
import { reactive } from 'vue'

import {useUserStore} from "@/stores/modules/user.ts";
const userStore=useUserStore()
interface FormState {
  username: string
  password: string
  remember: boolean
}

const formState = reactive<FormState>({
  username: '1',
  password: '123456',
  remember: true,
})
const onFinish = (values: any) => {
  userStore.login(formState)

}

const onFinishFailed = (errorInfo: any) => {}
</script>

<style scoped>
 @import "tailwindcss";
 .login-bg{
   background-image: url("/login_bg.jpg");
   object-fit: contain;
   height: 100vh;
 }
</style>
