<template>
  <a-drawer width="70%" title="数据报表" placement="right" :open="open" @close="onClose">
    <a-tabs v-model:activeKey="activeKey">
      <a-tab-pane key="1" tab="图表数据">
        <a-flex vertical :gap="15">
          <a-card title="24小时数据曲线"> ddd </a-card>
          <a-card title="地区分布"> ddd </a-card>
          <a-row :gutter="15">
            <a-col :span="12">
              <a-card style="width: 100%" title="高频访问IP">
              <TopAccessIp :shortCode="baseData.shortCode"/>
              </a-card>
            </a-col>
            <a-col :span="12">
              <a-card style="width: 100%" title="操作系统"> ddd </a-card>
            </a-col>
          </a-row>

          <a-row :gutter="15">
            <a-col :span="12">
              <a-card title="访问浏览器"> ddd </a-card>
            </a-col>
            <a-col :span="12">
              <a-card title="访问设备"> ddd </a-card>
            </a-col>
          </a-row>
          <a-row :gutter="15">
            <a-col :span="12">
              <a-card title="访问来源"> ddd </a-card>
            </a-col>
            <a-col :span="12">
              <a-card title="访客类型"> ddd </a-card>
            </a-col>
          </a-row>
        </a-flex>
      </a-tab-pane>
      <a-tab-pane key="2" tab="访问记录" force-render> </a-tab-pane>
    </a-tabs>
  </a-drawer>
</template>
<script lang="ts" setup>
import { ref } from 'vue'
import TopAccessIp from "@/views/leopard/shorturl/components/analysis/components/TopAccessIp.vue";

const activeKey = ref('1')
const open = ref<boolean>(false)
const baseData=ref<any>({})
const showDrawer = (data = null) => {
  baseData.value=data
  open.value = true
}
const onClose = () => {
  open.value = false
}
defineExpose({
  showDrawer,
})
</script>
