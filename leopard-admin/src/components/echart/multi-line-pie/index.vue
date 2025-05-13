<template>
  <div ref="multiLinePieRef" :class="className" :style="{ height, width }" />
</template>

<script setup lang="ts">
import * as echarts from "echarts";
import { onMounted, onBeforeUnmount, ref, markRaw } from "vue";

defineOptions({
  name: 'MultiLinePie',
})

const props = defineProps({
  className: {
    type: String,
    default: 'chart'
  },
  width: {
    type: String,
    default: '1000px'
  },
  height: {
    type: String,
    default: '800px'
  }
})

const multiLinePieRef = ref<HTMLDivElement | null>(null);
const chartInstance = ref<echarts.ECharts | null>(null);

const initChart = () => {
  if (!multiLinePieRef.value) return;

  chartInstance.value = markRaw(echarts.init(multiLinePieRef.value,'macarons'));

  const option = {
    legend: {},
    tooltip: {
      trigger: 'axis',
      showContent: false
    },
    dataset: {
      source: [
        ['product', '2012', '2013', '2014', '2015', '2016', '2017'],
        ['访问次数', 40.1, 62.2, 69.5, 36.4, 45.2, 32.5],
        ['访问人数', 25.2, 37.1, 41.2, 18, 33.9, 49.1]
      ]
    },
    xAxis: { type: 'category' },
    yAxis: { gridIndex: 0 },
    grid: { top: '55%' },
    series: [
      {
        type: 'line',
        smooth: true,
        seriesLayoutBy: 'row',
        emphasis: { focus: 'series' }
      },
      {
        type: 'line',
        smooth: true,
        seriesLayoutBy: 'row',
        emphasis: { focus: 'series' }
      },
      {
        type: 'line',
        smooth: true,
        seriesLayoutBy: 'row',
        emphasis: { focus: 'series' }
      },
      {
        type: 'line',
        smooth: true,
        seriesLayoutBy: 'row',
        emphasis: { focus: 'series' }
      },
      {
        type: 'pie',
        id: 'pie',
        radius: '30%',
        center: ['50%', '25%'],
        emphasis: {
          focus: 'self'
        },
        label: {
          formatter: '{b}: {@2012} ({d}%)'
        },
        encode: {
          itemName: 'product',
          value: '2012',
          tooltip: '2012'
        }
      }
    ]
  };

  chartInstance.value.setOption(option);

  chartInstance.value.on('updateAxisPointer', function (event: any) {
    const xAxisInfo = event.axesInfo[0];
    if (xAxisInfo) {
      const dimension = xAxisInfo.value + 1;
      chartInstance.value?.setOption({
        series: {
          id: 'pie',
          label: {
            formatter: '{b}: {@[' + dimension + ']} ({d}%)'
          },
          encode: {
            value: dimension,
            tooltip: dimension
          }
        }
      });
    }
  });
}

onMounted(() => {
  initChart();
});

onBeforeUnmount(() => {
  if (chartInstance.value) {
    chartInstance.value.dispose();
    chartInstance.value = null;
  }
});
</script>

<style scoped>
/* 可以添加自定义样式 */
</style>
