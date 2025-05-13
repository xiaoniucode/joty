


<template>
  <div ref="multiLineChartRef" :class="className" :style="{height, width}" />
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount, markRaw } from 'vue'
import * as echarts from 'echarts'
defineOptions({
  name: 'MultiLineChart',
})
const props = defineProps({
  className: {
    type: String,
    default: 'chart'
  },
  width: {
    type: String,
    default: '100%'
  },
  height: {
    type: String,
    default: '300px'
  }
})

const multiLineChartRef = ref(null)
const chartInstance = ref(null)

const initChart = () => {
  if (!multiLineChartRef.value) return

  // 使用markRaw避免echarts实例被转为响应式
  chartInstance.value = markRaw(echarts.init(multiLineChartRef.value, 'macarons'))

  chartInstance.value.setOption(
      //option start
      {
        // title: {
        //   text: 'Stacked Line'
        // },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['访问次数', '访问人数' ]
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: '访问次数',
            type: 'line',
            smooth: true,
            stack: 'Total',
            data: [320, 332, 301, 334, 390, 330, 320]
          },
          {
            name: '访问人数',
            type: 'line',
            smooth: true,
            stack: 'Total',
            data: [820, 932, 901, 934, 1290, 1330, 1320]
          }
        ]
      }

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
