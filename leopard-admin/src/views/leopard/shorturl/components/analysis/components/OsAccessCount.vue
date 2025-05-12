<script setup lang="ts">
import PieChart from '@/components/echart/pie-chart/index.vue'
</script>

<template>
<pie-chart :id="new Date().getMilliseconds()"/>
</template>

<style scoped>

</style>
