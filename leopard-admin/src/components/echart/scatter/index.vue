<template>
  <div ref="scatterChartRef" :class="className" :style="{ height, width }" />
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount, markRaw } from 'vue'
import * as echarts from 'echarts'

defineOptions({
  name: 'ScatterChart',
})
const props = defineProps({
  className: {
    type: String,
    default: 'chart',
  },
  width: {
    type: String,
    default: '1000px',
  },
  height: {
    type: String,
    default: '500px',
  },
})

const scatterChartRef = ref(null)
const chartInstance = ref(null)

const initChart = () => {
  if (!scatterChartRef.value) return

  // 使用markRaw避免echarts实例被转为响应式
  chartInstance.value = markRaw(echarts.init(scatterChartRef.value, 'macarons'))

  chartInstance.value.setOption(
    //option start
    {
      xAxis: {
        data: Array.from({ length: 24 }, (_, i) => `${i}`), // 生成0-23小时的时间轴
      },
      yAxis: {},

      series: [
        {
          symbolSize: 20,
          data: [
            [1, 8.04],
            [1.01, 9.04],
            [1.3, 10.04],
            [2, 6.95],
            [3, 7.58],
            [4, 8.81],
            [5, 8.33],
            [6, 7.66],
            [14, 6.81],
            [19, 6.81],
            [22, 6.81],
          ],
          type: 'scatter',
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
