<script setup lang="ts">

import * as echarts from "echarts";
import {onMounted} from "vue";
defineOptions({
  name: 'MultiLine',
})
const props = defineProps<{
  id?: string
}>()
onMounted(()=>{
  var chartDom = document.getElementById('multi-main');
  var myChart = echarts.init(chartDom);
  var option;

  option = {
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
  };

  option && myChart.setOption(option);
})
</script>

<template>
  <div id="multi-main" style="width: 900px;height:400px;"></div>
</template>

<style scoped>

</style>
