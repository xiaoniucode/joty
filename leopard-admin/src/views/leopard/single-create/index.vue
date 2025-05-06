<script setup lang="ts">
import { ref } from 'vue'
import {message} from "ant-design-vue";

const url = ref('')
const onCreate=async ()=>{
  if (url.value.trim()==''){
    message.warning("请输入链接")
    return
  }
}
</script>

<template>
  <a-flex gap="8" style="padding: 10% 5%" align="center">
    <a-input allowClear size="large" v-model:value="url" placeholder="输入一个http或https开头的链接" />
    <a-button @click="onCreate" type="primary" size="large">立即创建</a-button>
  </a-flex>
</template>

<style scoped></style>
