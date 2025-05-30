<template>
  <a-layout style="height: 100vh; margin: 0; padding: 0; background: #f0f2f5">
    <a-layout-sider
      v-model:collapsed="collapsed"
      :trigger="null"
      collapsible
      class="custom-sider"
      :style="{
        zIndex: 1000,
        overflow: 'auto',
        height: '100vh',
        position: 'fixed',
        left: 0,
        top: 0,
        bottom: 0,
        boxShadow: '2px 0 8px 0 rgba(29,35,41,.05)',
        transition: 'all 0.2s ease-in-out'
      }"
    >
      <router-link :to="userStore.isAdmin() ? '/dashboard' : '/stats'" class="logo-container">
        <a-flex align="center" class="logo-wrapper">
          <div class="logo">
            <a-image
              :preview="false"
              v-if="appStore.config.sidebarLogo"
              :src="appStore.config.logo"
              :alt="appStore.config.siteName"
            />
          </div>
          <div v-show="!collapsed" class="site-title">
            {{ appStore.config.siteName }}
          </div>
        </a-flex>
      </router-link>
      <side-menu />
    </a-layout-sider>
    <a-layout :style="{ marginLeft: collapsed ? '80px' : '200px', zIndex: 1000, transition: 'all 0.2s ease-in-out' }">
      <a-layout-header
        class="custom-header"
        :style="{ background: '#fff', padding: '0 24px', height: '56px', boxShadow: '0 1px 4px rgba(0,21,41,.08)' }"
      >
        <div class="header-left">
          <menu-unfold-outlined
            v-if="collapsed"
            class="trigger"
            @click="() => (collapsed = !collapsed)"
          />
          <menu-fold-outlined v-else class="trigger" @click="() => (collapsed = !collapsed)" />
        </div>
        <a-flex align="center" class="header-right" gap="16">
          <screen-full id="screenfull" class="header-item" />
          <a-dropdown>
            <span class="user-dropdown">
              <a-flex align="center" gap="8">
                <a-avatar :src="userStore.userinfo.avatar" class="user-avatar" />
                <span class="username">
                  <span v-if="userStore.userinfo.nickname">{{ userStore.userinfo.nickname }}</span>
                  <span v-else>{{ userStore.userinfo.username }}</span>
                  <DownOutlined class="dropdown-icon" />
                </span>
              </a-flex>
            </span>
            <template #overlay>
              <a-menu class="user-dropdown-menu">
                <a-menu-item key="account">
                  <router-link to="/account">
                    <user-outlined />
                    <span>个人设置</span>
                  </router-link>
                </a-menu-item>
                <a-menu-divider />
                <a-menu-item key="logout">
                  <a @click="userStore.logout()">
                    <logout-outlined />
                    <span>退出登录</span>
                  </a>
                </a-menu-item>
              </a-menu>
            </template>
          </a-dropdown>
        </a-flex>
      </a-layout-header>
      <a-layout-content
        class="custom-content"
        :style="{ margin: '16px', overflow: 'auto', padding: '24px', background: '#fff', borderRadius: '4px', minHeight: '280px' }"
      >
        <router-view v-slot="{ Component }">
            <component :is="Component" />
        </router-view>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>
<script lang="ts" setup>
import { ref } from 'vue'
import { MenuUnfoldOutlined, MenuFoldOutlined, DownOutlined, UserOutlined, LogoutOutlined } from '@ant-design/icons-vue'
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

.custom-sider {
  background: #001529;
}

.logo-container {
  display: block;
  padding: 16px 0;
  text-decoration: none;
}

.logo-wrapper {
  padding: 0 16px;
}

.logo {
  height: 32px;
  width: 32px;
  transition: all 0.3s;
}

.site-title {
  color: #fff;
  font-weight: 600;
  font-size: 16px;
  margin-left: 8px;
  white-space: nowrap;
  opacity: 1;
  transition: all 0.3s;
}

.custom-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 24px;
  background: #fff;
  position: sticky;
  top: 0;
  z-index: 999;
}

.header-left {
  display: flex;
  align-items: center;
}

.header-right {
  display: flex;
  align-items: center;
}

.header-item {
  padding: 0 12px;
  cursor: pointer;
  transition: all 0.3s;
}

.header-item:hover {
  color: #1890ff;
}

.trigger {
  font-size: 18px;
  cursor: pointer;
  transition: color 0.3s;
  padding: 0 12px;
}

.trigger:hover {
  color: #1890ff;
}

.user-dropdown {
  cursor: pointer;
  padding: 0 12px;
}

.user-avatar {
  background: #1890ff;
}

.username {
  color: rgba(0, 0, 0, 0.85);
  font-size: 14px;
}

.dropdown-icon {
  margin-left: 4px;
  font-size: 12px;
  color: rgba(0, 0, 0, 0.45);
}

.user-dropdown-menu {
  min-width: 160px;
}

.user-dropdown-menu :deep(.anticon) {
  margin-right: 8px;
}

.custom-content {
  background: #fff;
  border-radius: 4px;
  box-shadow: 0 1px 4px rgba(0,21,41,.08);
}

/* 路由切换动画 */
.fade-transform-enter-active,
.fade-transform-leave-active {
  transition: all 0.3s;
}

.fade-transform-enter-from {
  opacity: 0;
  transform: translateX(-20px);
}

.fade-transform-leave-to {
  opacity: 0;
  transform: translateX(20px);
}
</style>
