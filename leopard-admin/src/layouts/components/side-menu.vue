<script setup lang="ts">
import { ref } from 'vue'
import MenuTree from "@/layouts/components/menu-tree.vue";
 import {staticRoutes} from "@/router";

const selectedKeys = ref<string[]>(['0'])
</script>

<template>
  <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline">
     <menu-tree :menus="staticRoutes"/>
  </a-menu>
</template>

<style scoped></style>
