<script setup lang="ts">

import MultiLine from "@/components/echart/multi-line/index.vue";
import MultiLinePie from "@/components/echart/multi-line-pie/index.vue";
const props = defineProps<{
  shortCode: string
}>()
</script>

<template>
<multi-line-pie :key="shortCode"/>
</template>

<style scoped>

</style>
