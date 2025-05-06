<template>
  <a-modal :footer="null" width="50%" v-model:open="open" title="数据分析"> </a-modal>
</template>
<script lang="ts" setup>
import { ref } from 'vue'

const open = ref<boolean>(false)
const showModal = (data = null) => {
  open.value = true
}
defineExpose({
  showModal,
})
</script>
