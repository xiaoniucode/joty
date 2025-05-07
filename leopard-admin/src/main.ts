import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import pinia from '@/stores'
import {router} from './router'
import { directives } from '@/directives'
import * as antIcons from '@ant-design/icons-vue'


const app = createApp(App)
directives(app)
// 注册图标组件
Object.keys(antIcons).forEach((key) => {
  //@ts-ignore
  app.component(key, antIcons[key])
})

app.use(pinia)
app.use(router)
app.mount('#app')
