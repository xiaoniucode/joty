<script setup lang="ts">

import {UploadOutlined, UserOutlined, VideoCameraOutlined} from "@ant-design/icons-vue";
import {ref} from "vue";
const selectedKeys = ref<string[]>(['0'])
</script>

<template>
  <a-menu  v-model:selectedKeys="selectedKeys" theme="dark" mode="inline">
    <a-menu-item key="0">
      <video-camera-outlined />
      <span>监控面板</span>
    </a-menu-item>
    <a-sub-menu key="1">
      <template #title>
            <span>
              <user-outlined />
              <span>系统管理</span>
            </span>
      </template>
      <a-menu-item key="3-1">用户管理</a-menu-item>
      <a-menu-item key="4-2">角色管理</a-menu-item>
      <a-menu-item key="5-5">菜单管理</a-menu-item>
    </a-sub-menu>
    <a-menu-item key="2">
      <video-camera-outlined />
      <span>短链管理</span>
    </a-menu-item>
    <a-menu-item key="3">
      <upload-outlined />
      <span>数据监控</span>
    </a-menu-item>
  </a-menu>
</template>

<style scoped>

</style>
