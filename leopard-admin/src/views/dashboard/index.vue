<script setup lang="ts">
import api from '@/utils/api.ts'
import { user } from '@/api/system/user.ts'

const onLogin = async () => {
  const res = await api.action(user.login, {}, { username: '1', password: '123456' })
}
</script>

<template>
  <a-button @click="onLogin">login</a-button>
</template>

<style scoped></style>
