<script setup lang="ts">
import MenuTree from '@/layouts/components/menu-tree.vue'
import router from '@/router'

defineProps(['menus'])

const onClickMenu = async (menu: object) => {
  router.push({ path: menu.path })
}
const onClickSubMenu = async (menu: object) => {}
</script>

<template>
  <template v-for="menu in menus">
    <!--隐藏的菜单不展示-->
    <template v-if="!menu.hidden">
      <template v-if="menu.children && menu.children.length > 0">
        <!--多级菜单-->
        <a-sub-menu @click="onClickSubMenu(menu)" :key="menu.name">
          <template #title>
            <span>{{ menu.name }}</span>
          </template>
          <!--递归渲染子菜单-->
          <menu-tree :menus="menu.children" />
        </a-sub-menu>
      </template>

      <template v-else>
        <!--一级菜单-->
        <a-menu-item @click="onClickMenu(menu)" :key="menu.name">
          <span>{{ menu.name }}</span>
        </a-menu-item>
      </template>
    </template>
  </template>
</template>

<style scoped></style>
