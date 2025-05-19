<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { user } from '@/api/system/user.ts'
import api from '@/utils/api.ts'
import common from '@/utils/common.ts'
import { message, Modal } from 'ant-design-vue'

const apiKey = ref('')
const loadApiKey = async () => {
  const res = await (<any>api.action(user.getApiKey))
  apiKey.value = res || ''
}
onMounted(async () => {
  await loadApiKey()
})
const onResetOpenApiKey = async () => {
  Modal.confirm({
    title: '你确定要重置开放接口密钥?',
    content: '重置后之前的ApiKey将不可用，请谨慎重置！',
    okText: '确认',
    okType: 'danger',
    cancelText: '取消',
    onOk() {
      api.action(user.resetApikey).then((res: any) => {
        apiKey.value = res
        message.success('重置成功:' + res)
      })
    },
  })
}
</script>

<template>
 <div>
   <a-alert message="ApiKey密钥用于开放接口授权使用，每个用户拥有唯一的ApiKey！" type="success" />
   <a-flex :gap="12" align="center">
     <a-flex align="center" :gap="8">
       <span>ApiKey: </span>
       <span>{{ apiKey }}</span>
     </a-flex>
     <a-button type="primary" size="small" @click="common.copy(apiKey, true)">复制</a-button>
     <a-button type="ghost" @click="onResetOpenApiKey">重置</a-button>
   </a-flex>
 </div>
</template>

<style scoped></style>
