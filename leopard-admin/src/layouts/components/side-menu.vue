<script setup lang="ts">
import {ref, watch} from 'vue'
import MenuTree from '@/layouts/components/menu-tree.vue'
import { staticRoutes } from '@/router/static.ts'
import {useRoute} from "vue-router";
const openKeys = ref<string[]>([]);
const selectedKeys = ref<string[]>([])
let currentRoute = useRoute();
watch(
    currentRoute,
    () => {
      selectedKeys.value = [currentRoute.name];
    },
    {
      immediate: true,
    }
);
</script>

<template>
  <a-menu v-model:open-keys="openKeys" v-model:selectedKeys="selectedKeys" theme="dark" mode="inline" >
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
