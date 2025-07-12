<script setup lang="ts">
import PieChart from '@/components/echart/pie-chart/index.vue'
const props = defineProps<{
  shortCode: string
}>()
</script>

<template>
<pie-chart :key="shortCode"/>
</template>

<style scoped>

</style>
