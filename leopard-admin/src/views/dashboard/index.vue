<script setup lang="ts">
import {useCounterStore} from "@/stores/counter.ts";

const counter=useCounterStore()
</script>

<template>
 {{counter.increment()}}
</template>

<style scoped>

</style>
