<script setup lang="ts">

import Scatter from "@/components/echart/scatter/index.vue";
defineOptions({
  name: 'HourDistribution',
})
const props = defineProps<{
  shortCode: string
}>()
</script>

<template>
<Scatter :key="shortCode"/>
</template>

<style scoped>

</style>
