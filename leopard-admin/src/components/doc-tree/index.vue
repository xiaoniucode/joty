<script setup lang="ts">
import { ref, watch } from 'vue'

defineOptions({ name: 'DocTree' })
const emit=defineEmits(['onDelete'])
const props = defineProps<{
  treeData?: any
}>()

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
    :fieldNames="{ title: 'name', key: 'id' }"
  >
    <template #title="{ name, id }">
      <span @mouseenter="handleMouseEnter(id)" @mouseleave="handleMouseLeave(id)">
        <a-flex justify="space-between" align="center" style="width: 160px; height: 40px">
          <span>{{ name }}</span>
          <div class="menu-container">
            <a-flex v-show="hoverStates[id]" :gap="8" class="menu fade" align="center">
              <div>编辑</div>
              <a-dropdown
                :visible="dropdownVisible[id]"
                @visibleChange="(visible) => handleDropdownVisibleChange(id, visible)"
              >
                <div
                  class="ant-dropdown-link"
                  @mouseenter="handleMouseEnter(id)"
                  @mouseleave="handleMouseLeave(id)"
                  @click.prevent
                >
                  ...
                </div>
                <template #overlay>
                  <a-menu @mouseenter="handleMouseEnter(id)" @mouseleave="handleMouseLeave(id)">
                    <a-menu-item>
                      <div @click="emit('onDelete',id)">删除</div>
                    </a-menu-item>
                  </a-menu>
                </template>
              </a-dropdown>
            </a-flex>
            <span v-show="!hoverStates[id]" class="placeholder">3</span>
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
