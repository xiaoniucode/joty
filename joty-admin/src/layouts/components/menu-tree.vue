<script setup lang="ts">
import MenuTree from '@/layouts/components/menu-tree.vue'
import { router } from '@/router'

defineProps(['menus'])
const onClickMenu = (menu: any) => {
  router.push({ path: menu.path })
}
</script>

<template>
  <template v-for="menu in menus">
    <!--隐藏的菜单不展示-->
    <template v-if="!menu.hidden">
      <!--有子菜单且子菜单数量为1-->
      <template v-if="menu.children && menu.children.length === 1 && !menu.alwaysShow">
        <!--只渲染唯一的子菜单-->
        <menu-tree :menus="menu.children" />
      </template>

      <!--有多个子菜单-->
      <template v-else-if="menu.children && menu.children.length > 0 && menu.alwaysShow">
        <a-sub-menu :key="menu.name">
          <template #title>
            <component v-if="menu.meta && menu.meta.icon" :is="menu.meta.icon" />
            <span>{{ menu.meta.title }}</span>
          </template>
          <!--递归渲染子菜单-->
          <menu-tree :menus="menu.children" />
        </a-sub-menu>
      </template>
      <!--如果不总是展示，递归渲染子菜单-->
      <template v-else-if="menu.children && menu.children.length > 0 && !menu.alwaysShow">
        <menu-tree :menus="menu.children" />
      </template>
      <!--没有子菜单-->
      <template v-else>
        <a-menu-item @click="onClickMenu(menu)" :key="menu.name">
          <component v-if="menu.meta && menu.meta.icon" :is="menu.meta.icon" />
          <span>{{ menu.meta.title }}</span>
        </a-menu-item>
      </template>
    </template>
  </template>
</template>
