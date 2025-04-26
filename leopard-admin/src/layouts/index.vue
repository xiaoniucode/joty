<template>
  <a-layout style="height: 100vh; margin: 0; padding: 0">
    <a-layout-sider
      v-model:collapsed="collapsed"
      :trigger="null"
      collapsible
      style="margin: 0; padding: 0"
    >
      <div class="flex items-center">
        <div class="logo">
          <img src="@/assets/logo.svg" alt="蜂鸟短链" />
        </div>
        <div v-show="!collapsed" style="color: white">蜂鸟短链</div>
      </div>
      <side-menu/>
    </a-layout-sider>
    <a-layout>
      <a-layout-header
        class="flex justify-between items-center"
        style="background: #fff; padding: 0">
        <div>
          <menu-unfold-outlined
            v-if="collapsed"
            class="trigger"
            @click="() => (collapsed = !collapsed)"
          />
          <menu-fold-outlined v-else class="trigger" @click="() => (collapsed = !collapsed)" />
        </div>
        <div>
          <a-button type="primary" style="margin-right: 10px">退出</a-button>
        </div>
      </a-layout-header>
      <a-layout-content
        :style="{ margin: '24px 0', padding: '24px', background: '#fff', minHeight: '280px' }">
        <router-view />
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>
<script lang="ts" setup>
import { ref } from 'vue'
import {
  MenuUnfoldOutlined,
  MenuFoldOutlined,
} from '@ant-design/icons-vue'
import SideMenu from "@/layouts/components/side-menu.vue";

const collapsed = ref<boolean>(false)

</script>
<style scoped>
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
