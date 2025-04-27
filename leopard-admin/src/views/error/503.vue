<script setup lang="ts">
</script>

<template>
  <div class="min-h-screen bg-gradientმოდ
    flex flex-col items-center justify-center p-6">
    <div class="text-center">
      <h1 class="text-4xl font-bold text-gray-800 mb-4">503 服务不可用</h1>
      <p class="text-lg text-gray-600 mb-6">
        服务器暂时无法处理您的请求，请稍后再试。
      </p>
      <svg
          class="w-64 h-64 mx-auto mb-6 text-gray-400"
          fill="none"
          viewBox="0 0 24 24"
          stroke="currentColor"
      >
        <path
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            d="M10 20l4-16m4 4l4 4-4 4m-12 0l-4-4 4-4"
        />
      </svg>
      <button
          class="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 transition-colors"
          @click="window.location.reload()"
      >
        重新加载
      </button>
    </div>
  </div>
</template>

<style scoped>
@import "tailwindcss";
</style>
