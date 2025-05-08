<template>
  <div style="font-size: 25px; font-weight: 800">
    <component
      @click="click"
      :is="isFullscreen ? 'FullscreenExitOutlined' : 'FullscreenOutlined'"
    />
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted } from 'vue'
import screenfull from 'screenfull'

// 定义全屏状态
const isFullscreen = ref(false)

// 点击切换全屏
const click = () => {
  if (!screenfull.isEnabled) {
    return
  }
  screenfull.toggle()
}

// 全屏状态变更处理
const change = () => {
  if (screenfull.isEnabled) {
    isFullscreen.value = screenfull.isFullscreen
  }
}

// 初始化全屏监听
const init = () => {
  if (screenfull.isEnabled) {
    screenfull.on('change', change)
  }
}

// 清理监听
const destroy = () => {
  if (screenfull.isEnabled) {
    screenfull.off('change', change)
  }
}

// 生命周期钩子
onMounted(init)
onUnmounted(destroy)
</script>

<style scoped></style>
