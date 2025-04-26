<script setup lang="ts">
import api from '@/utils/api.ts'
import { user } from '@/api/system/user.ts'

const a = await api.action(
    user.login,
    {},
    {username:'admin',password:'123456'})
</script>

<template>
  {{a}}
</template>

<style scoped></style>
