<script setup lang="ts">
import { ref } from 'vue'
import { message } from 'ant-design-vue'
import api from '@/utils/api.ts'
import { short_url } from '@/api/leopard/shorturl.ts'

const url = ref('')
const result = ref({})
const onCreate = async () => {
  if (url.value.trim() == '') {
    message.warning('请输入链接')
    return
  }
  api.action(short_url.fastCreate, {}, { url: url.value.trim() }).then((res: any) => {
    result.value = res
    showResult.value=true
    message.success('创建成功')
  })
}
const showResult=ref(false)
const onCopy=async ()=>{
  try {
    await navigator.clipboard.writeText(result.value.shortUrl)
    message.success('复制成功')
  } catch (err) {
    message.error('复制失败')
  }
}
</script>

<template>
  <a-flex style="padding: 10% 5%" vertical gap="50">
    <a-flex gap="8" align="center">
      <a-input
        allowClear
        size="large"
        v-model:value="url"
        placeholder="输入一个http或https开头的链接"
      />
      <a-button @click="onCreate" type="primary" size="large">立即创建</a-button>
    </a-flex>

    <a-card v-if="showResult">
      <a-flex gap="12">
        <a-image :width="128" :src="result.qrUrl" fallback="/error_image.png" />
        <a-flex vertical justify="space-around">
          <div class="original-url text-size">{{ result.originalUrl }}</div>
          <a-flex align="center" gap="12">
            <span class="text-size">{{ result.shortUrl }}</span>
            <a-button @click="onCopy" size="small" type="primary">复制短链</a-button>
          </a-flex>
        </a-flex>
      </a-flex>
    </a-card>
  </a-flex>
</template>

<style scoped>
:deep(.ant-card):hover {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  transition: all 0.3s;
}

.original-url {
  flex: 1;
  color: rgba(0, 0, 0, 0.65);
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  word-break: break-all;
  max-width: 500px;
}

.text-size {
  font-size: 20px;
}
</style>
