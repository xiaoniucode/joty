<!-- ShortLinkCard.vue -->
<template>
  <a-card hoverable class="short-link-card">
    <div class="card-content">
      <!-- 封面图片 -->
      <div class="cover-container">
        <img
            :src="cover"
            alt="封面"
            class="cover-image"
            v-if="cover"
            @error="handleImageError"
        />
        <a-empty v-else description="无封面" />
      </div>

      <!-- 标题和短链接 -->
      <div class="info-container">
        <h3 class="title">{{ title || '未设置标题' }}</h3>
        <div class="link-section">
          <a-typography-text copyable :content="shortLink" />
          <a-button
              type="primary"
              size="small"
              @click="common.copy(shortLink,true)"
              class="copy-btn"
          >
            复制
          </a-button>
        </div>
      </div>

      <!-- 二维码 -->
      <div class="qrcode-container">
        <a-qrcode
            :value="shortLink"
            :size="100"
            v-if="shortLink"
            color="#000"
            background="#fff"
        />
        <a-empty v-else description="无二维码" />
      </div>
    </div>
  </a-card>
</template>

<script setup lang="ts">
import common from "@/utils/common.js";
import html2canvas from 'html2canvas'
// Props 定义
const props = defineProps({
  title: {
    type: String,
    default: '',
  },
  shortLink: {
    type: String,
    default: '',
  },
  cover: {
    type: String,
    default: '',
  },
});

// 图片加载失败处理
const handleImageError = (e) => {
  e.target.src = '/path/to/placeholder-image.png'; // 替换为你的占位图路径
};
</script>

<style scoped>
.short-link-card {
  max-width: 400px;
  margin: 16px;
}

.card-content {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.cover-container {
  width: 100%;
  height: 150px;
  overflow: hidden;
  border-radius: 8px;
}

.cover-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.info-container {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.title {
  margin: 0;
  font-size: 18px;
  font-weight: 500;
}

.link-section {
  display: flex;
  align-items: center;
  gap: 8px;
}

.qrcode-container {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 8px;
  border: 1px solid #f0f0f0;
  border-radius: 4px;
}

.copy-btn {
  margin-left: 8px;
}
</style>
