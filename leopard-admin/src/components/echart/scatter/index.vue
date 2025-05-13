<script setup lang="ts">
import * as echarts from 'echarts';
import {onMounted} from "vue";
defineOptions({
  name: 'Scatter',
})
onMounted(()=>{
  var chartDom = document.getElementById('main');
  var myChart = echarts.init(chartDom);
  var option;

  option = {
    xAxis: {
      data: Array.from({ length: 24 }, (_, i) => `${i}`) // 生成0-23小时的时间轴
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
          [22, 6.81]
        ],
        type: 'scatter'
      }
    ]
  };

  option && myChart.setOption(option);

})
</script>

<template>
  <div id="line" style="width: 600px;height:400px;"></div>
</template>

<style scoped>

</style>
