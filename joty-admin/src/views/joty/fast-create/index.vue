<script setup lang="ts">
import { ref } from 'vue'
import { message } from 'ant-design-vue'
import api from '@/utils/api.ts'
import { short_url } from '@/api/joty/shorturl.ts'
import common from '@/utils/common.ts'

const url = ref('')
const result = ref({})
const onCreate = async () => {
  if (url.value.trim() == '') {
    message.warning('请输入链接')
    return
  }
  api.action(short_url.fastCreate, {}, { url: url.value.trim() }).then((res: any) => {
    result.value = res
    showResult.value = true
    message.success('创建成功')
  })
}
const showResult = ref(false)
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
        <a-flex vertical justify="space-between" class="url-container">
          <a-tooltip placement="top" :title="result.originalUrl">
            <div class="original-url text-size">{{ result.originalUrl }}</div>
          </a-tooltip>
          <a-flex align="center" gap="12">
            <span class="text-size">{{ result.shortUrl }}</span>
            <a-button @click="common.copy(result.shortUrl, true)" size="small" type="primary"
              >复制短链</a-button
            >
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

.url-container {
  max-width: calc(100% - 140px); /* 留出图片和间隙的宽度 */
  flex: 1;
}

.original-url {
  color: rgba(0, 0, 0, 0.65);
  white-space: nowrap;
  overflow: hidden;
  word-break: break-all;
  text-overflow: ellipsis;
  max-width: 100%; /* 限制在父容器内 */
  box-sizing: border-box;
}

.text-size {
  font-size: 20px;
}
</style>
