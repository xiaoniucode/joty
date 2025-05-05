<script setup lang="ts">

import {short_url} from "@/api/leopard/shorturl.ts";
import api from "@/utils/api.ts";
import {reactive, ref} from "vue";
const data=ref([])
const pageQuery=reactive({
  page:1,
  size:2
})

api.action(short_url.list, {}, pageQuery).then((res: any) => {
  data.value = res.records

})

</script>

<template>
<h1>账户信息</h1>
  {{data}}
</template>

<style scoped>

</style>
