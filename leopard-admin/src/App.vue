<script setup lang="ts">
import { RouterView } from 'vue-router'
</script>
<template>
  <a-app>
  <a-config-provider>
    <transition name="fade-transform" mode="out-in">
      <RouterView />
    </transition>
  </a-config-provider>
  </a-app>
</template>

<style scoped>

</style>
