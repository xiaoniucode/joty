<script setup lang="ts">
import { RouterView } from 'vue-router'
</script>
<template>
  <a-app class="app-main">
  <a-config-provider>
    <transition name="fade-transform" mode="out-in">
      <RouterView />
    </transition>

  </a-config-provider>
  </a-app>
</template>

<style scoped>
.app-main {
  min-height: calc(100vh - 50px);
  width: 100%;
  position: relative;
  overflow: hidden;
}

.fixed-header+.app-main {
  padding-top: 50px;
}

</style>
