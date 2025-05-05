<template>
  <a-layout style="height: 100vh; margin: 0; padding: 0">
    <a-layout-sider
      v-model:collapsed="collapsed"
      :trigger="null"
      collapsible
      :style="{ overflow: 'auto', height: '100vh', position: 'fixed', left: 0, top: 0, bottom: 0 }"
    >
      <div class="flex items-center">
        <div class="logo">
          <img src="@/assets/logo.jpeg" alt="蜂鸟短链" />
        </div>
        <div v-show="!collapsed" style="color: white">蜂鸟短链</div>
      </div>
      <side-menu />
    </a-layout-sider>
    <a-layout :style="{ marginLeft: '200px' }">
      <a-layout-header :style="{ background: '#fff', padding: 0, height: '56px' }">
        <div class="flex justify-between items-center p-6">
          <div>
            <menu-unfold-outlined
              v-if="collapsed"
              class="trigger"
              @click="() => (collapsed = !collapsed)"
            />
            <menu-fold-outlined v-else class="trigger" @click="() => (collapsed = !collapsed)" />
          </div>
          <div style="padding-right: 15px">
            <a-dropdown>
              <img style="height: 32px" src="@/assets/logo.jpeg" class="ant-dropdown-link" alt="" />

              <template #overlay>
                <a-menu>
                  <a-menu-item>
                    <a href="/account">个人设置</a>
                  </a-menu-item>
                  <a-menu-divider />
                  <a-menu-item>
                    <a @click="userStore.logout()">退出登陆</a>
                  </a-menu-item>
                </a-menu>
              </template>
            </a-dropdown>
          </div>
        </div>
      </a-layout-header>
      <a-layout-content
        :style="{ margin: '15px 0', overflow: 'initial', padding: '15px', background: '#fff' }"
      >
        <router-view />
      </a-layout-content>

    </a-layout>
  </a-layout>
</template>
<script lang="ts" setup>
import { ref } from 'vue'
import { MenuUnfoldOutlined, MenuFoldOutlined } from '@ant-design/icons-vue'
import SideMenu from '@/layouts/components/side-menu.vue'
import { useUserStore } from '@/stores/modules/user.ts'

const collapsed = ref<boolean>(false)
const userStore = useUserStore()
</script>
<style scoped>
@import 'tailwindcss';

.trigger {
  font-size: 18px;
  line-height: 56px;
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
