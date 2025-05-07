<script setup lang="ts">
import { onMounted, ref, watch } from 'vue'
import MenuTree from '@/layouts/components/menu-tree.vue'
import { useRoute } from 'vue-router'
import { usePermissionStore } from '@/stores/modules/permission.ts'
import {useUserStore} from "@/stores/modules/user.ts";
import {message} from "ant-design-vue";

const openKeys = ref<string[]>([])
const selectedKeys = ref<string[]>([])
let currentRoute = useRoute()
watch(
  currentRoute,
  () => {
    //@ts-ignore
    selectedKeys.value = [currentRoute.name]
  },
  {
    immediate: true,
  },
)
const permissionStore=usePermissionStore()
const userStore = useUserStore()
const menus = ref<any>([])
onMounted(async () => {
  if (userStore.getRoles()&&userStore.getRoles().length>0){
    menus.value = await permissionStore.getMenus(userStore.getRoles())
  }else {
    message.error("菜单初始化失败！")
  }

})
</script>

<template>
  <a-menu
    v-model:open-keys="openKeys"
    v-model:selectedKeys="selectedKeys"
    theme="dark"
    mode="inline">
    <menu-tree :menus="menus" />
  </a-menu>
</template>

<style scoped>
.side-tree {
  min-height: 100vh;
  overflow-y: auto;
  z-index: 10;
}
</style>
