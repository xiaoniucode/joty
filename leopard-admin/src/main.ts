import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import pinia from "@/stores";
import router from './router'
import { directives } from '@/directives'
const app = createApp(App)

directives(app)
app.use(pinia)
app.use(router)

app.mount('#app')




