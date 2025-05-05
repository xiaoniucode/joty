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
      <!--有子菜单且子菜单数量为1-->
      <template v-if="menu.children && menu.children.length === 1">
        <!--只渲染唯一的子菜单-->
        <menu-tree :menus="menu.children" />
      </template>

      <!--有多个子菜单-->
      <template v-else-if="menu.children && menu.children.length > 1">
        <a-sub-menu @click="onClickSubMenu(menu)" :key="menu.name">
          <template #title>
            <span>{{ menu.name }}</span>
          </template>
          <!--递归渲染子菜单-->
          <menu-tree :menus="menu.children" />
        </a-sub-menu>
      </template>

      <!--没有子菜单-->
      <template v-else>
        <a-menu-item @click="onClickMenu(menu)" :key="menu.name">
          <span>{{ menu.name }}</span>
        </a-menu-item>
      </template>
    </template>
  </template>
</template>
