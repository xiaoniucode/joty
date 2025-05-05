<script setup lang="ts">
import * as echarts from 'echarts';
import {onMounted} from "vue";
onMounted(()=>{

  var chartDom = document.getElementById('line');
  var myChart = echarts.init(chartDom);
  var option;

  option = {
    xAxis: {
      type: 'category',
      data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        data: [820, 932, 901, 934, 1290, 1330, 1320],
        type: 'line',
        smooth: true
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
