<template>
  <div ref="pieChartRef" :class="className" :style="{height, width}" />
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount, markRaw } from 'vue'
import * as echarts from 'echarts'

const props = defineProps({
  className: {
    type: String,
    default: 'chart'
  },
  width: {
    type: String,
    default: '500px'
  },
  height: {
    type: String,
    default: '300px'
  }
})

const pieChartRef = ref(null)
const chartInstance = ref(null)

const initChart = () => {
  if (!pieChartRef.value) return

  // 使用markRaw避免echarts实例被转为响应式
  chartInstance.value = markRaw(echarts.init(pieChartRef.value, 'macarons'))

  chartInstance.value.setOption(
      {
        title: {
          text: 'Referer of a Website',
          subtext: 'Fake Data',
          left: 'center',
          show: false
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left',
          show: false
        },
        series: [
          {
            name: 'Access From',
            type: 'pie',
            radius: '50%',
            data: [
              { value: 1048, name: 'IOS' },
              { value: 735, name: 'Windows' },
              { value: 580, name: 'MAC IOS' }
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      }
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
