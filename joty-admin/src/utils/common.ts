import { message } from 'ant-design-vue'
/**
 * 通用工具类
 */
const common = {
  copy: async (content: any, tip: boolean = false): Promise<boolean> => {
    try {
      // 转换为字符串，处理非字符串内容
      const text = String(content)
      await navigator.clipboard.writeText(text)
      if (tip) {
        message.success('已复制')
      }
      return true
    } catch (err) {
      if (tip) {
        message.error('复制失败')
      }
      console.error('复制失败:', err)
      return false
    }
  },
}

export default common
