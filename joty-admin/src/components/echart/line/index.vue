<template>
  <div ref="lineChartRef" :class="className" :style="{ height, width }" />
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount, markRaw } from 'vue'
import * as echarts from 'echarts'

defineOptions({
  name: 'LineChart',
})
const props = defineProps({
  className: {
    type: String,
    default: 'chart',
  },
  width: {
    type: String,
    default: '100%',
  },
  height: {
    type: String,
    default: '300px',
  },
})

const lineChartRef = ref(null)
const chartInstance = ref(null)

const initChart = () => {
  if (!lineChartRef.value) return

  // 使用markRaw避免echarts实例被转为响应式
  chartInstance.value = markRaw(echarts.init(lineChartRef.value, 'macarons'))

  chartInstance.value.setOption(
    //option start
    {
      xAxis: {
        type: 'category',
        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
      },
      yAxis: {
        type: 'value',
      },
      series: [
        {
          data: [820, 932, 901, 934, 1290, 1330, 1320],
          type: 'line',
          smooth: true,
        },
      ],
    },

    //option end
  )
}

onMounted(() => {
  initChart()
})

onBeforeUnmount(() => {
  if (chartInstance.value) {
    chartInstance.value.dispose()
    chartInstance.value = null
  }
})
</script>
