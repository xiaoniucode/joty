import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import pinia from '@/stores'
import router from './router'
import { directives } from '@/directives'
import * as antIcons from '@ant-design/icons-vue'
import api from "@/utils/api.ts";

const app = createApp(App)

directives(app)

// 注册图标组件
Object.keys(antIcons).forEach((key) => {
  //@ts-ignore
  app.component(key, antIcons[key])
})
//全局属性配置
app.config.globalProperties.$antIcons = antIcons
app.provide('api', api)

app.use(pinia)
app.use(router)
app.mount('#app')
