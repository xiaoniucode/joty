<template>
  <a-layout style="height: 100vh; margin: 0; padding: 0">
    <a-layout-sider
      v-model:collapsed="collapsed"
      :trigger="null"
      collapsible
      :style="{
        zIndex: 1000,
        overflow: 'auto',
        height: '100vh',
        position: 'fixed',
        left: 0,
        top: 0,
        bottom: 0,
      }"
    >
      <router-link :to="userStore.isAdmin() ? '/dashboard' : '/stats'">
        <a-flex align="center">
          <div class="logo">
            <a-image
              :preview="false"
              v-if="appStore.config.sidebarLogo"
              :src="appStore.config.logo"
              :alt="appStore.config.siteName"
            />
          </div>
          <div v-show="!collapsed" style="color: #fff; font-weight: 700; font-size: 15px">
            {{ appStore.config.siteName }}
          </div>
        </a-flex>
      </router-link>
      <side-menu />
    </a-layout-sider>
    <a-layout :style="{ marginLeft: collapsed ? '80px' : '200px', zIndex: 1000 }">
      <a-layout-header
        class="flex justify-between items-center p-6"
        :style="{ background: '#fff', padding: 0, height: '56px' }"
      >
        <div>
          <menu-unfold-outlined
            v-if="collapsed"
            class="trigger"
            @click="() => (collapsed = !collapsed)"
          />
          <menu-fold-outlined v-else class="trigger" @click="() => (collapsed = !collapsed)" />
        </div>
        <a-flex align="center" style="padding-right: 15px" gap="10">
          <screen-full id="screenfull" />
          <a-dropdown>
            <span>
             <a-flex align="center">
                <a-avatar :src="userStore.userinfo.avatar" />
              <span>
                <span v-if="userStore.userinfo.nickname">{{ userStore.userinfo.nickname }}</span>
                <span v-else>{{ userStore.userinfo.username }}</span>
                <DownOutlined />
              </span>
             </a-flex>
            </span>
            <template #overlay>
              <a-menu>
                <a-menu-item>
                  <router-link to="/account"> 个人设置</router-link>
                </a-menu-item>
                <a-menu-divider />
                <a-menu-item>
                  <a @click="userStore.logout()">退出登陆</a>
                </a-menu-item>
              </a-menu>
            </template>
          </a-dropdown>
        </a-flex>
      </a-layout-header>
      <a-layout-content
        :style="{ margin: '15px 0', overflow: 'auto', padding: '15px', background: '#fff' }"
      >
        <router-view v-slot="{ Component }">
          <transition>
            <component :is="Component" />
          </transition>
        </router-view>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>
<script lang="ts" setup>
import { ref } from 'vue'
import { MenuUnfoldOutlined, MenuFoldOutlined, DownOutlined } from '@ant-design/icons-vue'
import SideMenu from '@/layouts/components/side-menu.vue'
import { useUserStore } from '@/stores/modules/user.ts'
import { useAppStore } from '@/stores/modules/app.ts'
import ScreenFull from '@/components/screen-full/index.vue'

const collapsed = ref<boolean>(false)
const userStore = useUserStore()
const appStore = useAppStore()
</script>
<style scoped>
@import 'tailwindcss';

.trigger {
  font-size: 18px;
  line-height: 56px;
  padding: 0 18px;
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
