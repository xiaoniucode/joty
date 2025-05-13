<script setup lang="ts">
import { ref, watch } from 'vue'
import type { TreeProps } from 'ant-design-vue'

defineOptions({ name: 'DocTree' })

const treeData: TreeProps['treeData'] = [
  { title: '默认分组', key: '0-0' },
  { title: '人工智能', key: '0-1' },
  { title: '大数据', key: '0-2' },
  { title: '短链接分享', key: '0-3' },
]

const expandedKeys = ref<string[]>(['0-0'])
const selectedKeys = ref<string[]>(['0-0'])
const checkedKeys = ref<string[]>(['0-0',])
const hoverStates = ref<Record<string, boolean>>({})
const dropdownVisible = ref<Record<string, boolean>>({})
const hideTimeouts = ref<Record<string, number>>({})

const handleMouseEnter = (key: string) => {
  if (hideTimeouts.value[key]) {
    clearTimeout(hideTimeouts.value[key])
    delete hideTimeouts.value[key]
  }
  hoverStates.value[key] = true
}

const handleMouseLeave = (key: string) => {
  hideTimeouts.value[key] = setTimeout(() => {
    if (!dropdownVisible.value[key]) {
      hoverStates.value[key] = false
    }
    delete hideTimeouts.value[key]
  }, 200)
}

const handleDropdownVisibleChange = (key: string, visible: boolean) => {
  dropdownVisible.value[key] = visible
  if (visible) {
    hoverStates.value[key] = true
    if (hideTimeouts.value[key]) {
      clearTimeout(hideTimeouts.value[key])
      delete hideTimeouts.value[key]
    }
  } else if (!hoverStates.value[key]) {
    hoverStates.value[key] = false
  }
}
</script>

<template>
  <a-tree
    v-model:expandedKeys="expandedKeys"
    v-model:selectedKeys="selectedKeys"
    v-model:checkedKeys="checkedKeys"
    :tree-data="treeData"
  >
    <template #title="{ title, key }">
      <span @mouseenter="handleMouseEnter(key)" @mouseleave="handleMouseLeave(key)">
        <a-flex justify="space-between" align="center" style="width: 200px; height: 40px">
          <span>{{ title }}</span>
          <div class="menu-container">
            <a-flex v-if="hoverStates[key]" :gap="8" class="menu fade">
              <div>菜单</div>
              <a-dropdown
                :visible="dropdownVisible[key]"
                @visibleChange="(visible) => handleDropdownVisibleChange(key, visible)"
              >
                <div
                  class="ant-dropdown-link"
                  @mouseenter="handleMouseEnter(key)"
                  @mouseleave="handleMouseLeave(key)"
                  @click.prevent
                >
                  更多
                </div>
                <template #overlay>
                  <a-menu @mouseenter="handleMouseEnter(key)" @mouseleave="handleMouseLeave(key)">
                    <a-menu-item>
                      <a href="javascript:;">编辑</a>
                    </a-menu-item>
                  </a-menu>
                </template>
              </a-dropdown>
            </a-flex>
            <span v-else class="placeholder">3</span>
          </div>
        </a-flex>
      </span>
    </template>
  </a-tree>
</template>

<style scoped>
.menu-container {
  position: relative;
  min-width: 40px;
  display: flex;
  align-items: center;
  justify-content: flex-end;
}

.menu {
  transition: all 0.2s ease-in-out;
  transform-origin: right center;
}

.menu.fade-enter-active,
.menu.fade-leave-active {
  transition:
    opacity 0.2s ease-in-out,
    transform 0.2s ease-in-out;
}

.menu.fade-enter-from,
.menu.fade-leave-to {
  opacity: 0;
  transform: scale(0.95) translateX(5px);
}

.placeholder {
  transition: opacity 0.2s ease-in-out;
  opacity: 1;
}

.menu-container:hover .placeholder {
  opacity: 0;
}

.ant-dropdown-link {
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 4px;
}
</style>
