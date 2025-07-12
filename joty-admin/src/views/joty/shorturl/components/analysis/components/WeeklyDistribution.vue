<script setup lang="ts">
import BarChart from "@/components/echart/bar-chart/index.vue";

defineOptions({
  name: 'WeeklyDistribution',
})
const props = defineProps<{
  shortCode: string
}>()
</script>

<template>
  <BarChart :key="shortCode"/>
</template>

<style scoped>

</style>
