<template>
  <div class="login-container">
    <!-- 左侧图片背景 -->
    <div class="login-bg">
      <img src="/login_bg.jpg" alt="Login Background" />
    </div>

    <!-- 右侧登录表单 -->
    <div class="login-form-wrapper">
      <h2 class="form-title">{{siteTitle}}</h2>
        <LoginFrom
          :username
          v-if="mode == 'login'"
          @on-register="(val) => { mode = 'register';username=val}"
        />
        <RegisterForm
          :username
          v-else-if="mode == 'register'"
          @on-login="(val) => { mode = 'login';username=val}"
        />
    </div>
  </div>
</template>

<script setup lang="ts">
import LoginFrom from './components/LoginForm.vue'
import RegisterForm from './components/RegisterForm.vue'
import { ref } from 'vue'
import {useAppStore} from "@/stores/modules/app.ts";

const mode = ref<'login' | 'register'>('login')
const username = ref<string>()
const appStore =  useAppStore()
const siteTitle=appStore.config.siteName

</script>

<style scoped>
.login-container {
  display: flex;
  width: 100%;
  height: 100vh;
  overflow: hidden;
}

.login-bg {
  flex: 1;
  background: #f0f2f5;
  display: flex;
  align-items: center;
  justify-content: center;
}

.login-bg img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.login-form-wrapper {
  width: 420px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding: 0 40px;
  background: #fff;
  box-shadow: -5px 0 15px rgba(0, 0, 0, 0.05);
}

.login-form {
  max-width: 320px;
  margin: 0 auto;
  width: 100%;
}

.form-title {
  margin-bottom: 24px;
  text-align: center;
  color: rgba(0, 0, 0, 0.85);
  font-weight: 500;
  font-size: 24px;
}

@media (max-width: 768px) {
  .login-container {
    flex-direction: column;
  }

  .login-bg {
    display: none;
  }

  .login-form-wrapper {
    width: 100%;
    padding: 40px 24px;
  }
}
</style>
