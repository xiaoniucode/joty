<script setup lang="ts">
import { ref } from 'vue'
import MenuTree from '@/layouts/components/menu-tree.vue'
import { staticRoutes } from '@/router/static.ts'

const selectedKeys = ref<string[]>(['1'])
</script>

<template>
  <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline">
    <menu-tree :menus="staticRoutes" />
  </a-menu>
</template>

<style scoped>
.side-tree {
  min-height: 100vh;
  overflow-y: auto;
  z-index: 10;
}
</style>
