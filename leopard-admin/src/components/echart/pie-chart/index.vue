<script setup lang="ts">

import * as echarts from "echarts";
import {onMounted} from "vue";
const props = defineProps<{
  id?: string
}>()
onMounted(()=>{
  var chartDom = document.getElementById(props.id);
  var myChart = echarts.init(chartDom);
  var option;

  option = {
    title: {
      text: 'Referer of a Website',
      subtext: 'Fake Data',
      left: 'center',
      show:false
    },
    tooltip: {
      trigger: 'item',

    },

    legend: {
      orient: 'vertical',
      left: 'left',
      show:false
    },

    series: [
      {
        name: 'Access From',
        type: 'pie',
        radius: '50%',
        data: [
          { value: 1048, name: 'IOS' },
          { value: 735, name: 'Windows' },
          { value: 580, name: 'MAC IOS' },

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
  };

  option && myChart.setOption(option);

})
</script>

<template>
  <div :id="id" style="width: 400px;height:400px;"></div>
</template>

<style scoped>

</style>
