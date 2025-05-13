<script setup lang="ts">
import * as echarts from 'echarts';
import {onMounted} from "vue";
defineOptions({
  name: 'BarChart',
})
onMounted(()=>{
  var chartDom = document.getElementById('BarChart');
  var myChart = echarts.init(chartDom);
  var option;

  option = {
    legend: {},
    tooltip: {},
    dataset: {
      source: [
        ['product', '访问量', '访问人数'],
        ['周一', 43.3, 85.8],
        ['周二', 83.1, 73.4],
        ['周三', 86.4, 65.2],
        ['周四', 72.4, 53.9],
        ['周五', 86.4, 65.2],
        ['周六', 86.4, 65.2],
        ['周日', 86.4, 65.2]
      ]
    },
    xAxis: { type: 'category' },
    yAxis: {},
    series: [{ type: 'bar' }, { type: 'bar' }]
  };

  option && myChart.setOption(option);

})
</script>

<template>
  <div id="BarChart" style="width: 1000px;height:400px;"></div>
</template>

<style scoped>

</style>
