<template>
  <a-layout style="height: 100vh; margin: 0; padding: 0">
    <a-layout-sider
      v-model:collapsed="collapsed"
      :trigger="null"
      collapsible
      style="margin: 0; padding: 0">
      <div class="flex items-center">
        <div class="logo">
          <img src="@/assets/logo.svg" alt="蜂鸟短链"/>
        </div>
        <div v-show="!collapsed" style="color: white">蜂鸟短链</div>
      </div>
      <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline">
        <a-menu-item key="1">
          <user-outlined />
          <span>系统管理</span>
        </a-menu-item>
        <a-menu-item key="2">
          <video-camera-outlined />
          <span>短链管理</span>
        </a-menu-item>
        <a-menu-item key="3">
          <upload-outlined />
          <span>数据监控</span>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <a-layout-header style="background: #fff; padding: 0">
        <menu-unfold-outlined
          v-if="collapsed"
          class="trigger"
          @click="() => (collapsed = !collapsed)"
        />
        <menu-fold-outlined v-else class="trigger" @click="() => (collapsed = !collapsed)" />
      </a-layout-header>
      <a-layout-content
        :style="{ margin: '24px 0', padding: '24px', background: '#fff', minHeight: '280px' }">
        <router-view/>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>
<script lang="ts" setup>
import { ref } from 'vue'
import {
  UserOutlined,
  VideoCameraOutlined,
  UploadOutlined,
  MenuUnfoldOutlined,
  MenuFoldOutlined,
} from '@ant-design/icons-vue'

const selectedKeys = ref<string[]>(['1'])
const collapsed = ref<boolean>(false)
</script>
<style>
.trigger {
  font-size: 18px;
  line-height: 64px;
  padding: 0 24px;
  cursor: pointer;
  transition: color 0.3s;
}

.trigger:hover {
  color: #1890ff;
}

.logo {
  height: 32px;
  width: 32px;
  margin: 16px;
}

.site-layout .site-layout-background {
  background: #fff;
}
</style>
