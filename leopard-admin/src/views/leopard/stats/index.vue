<template>
  <div>
    <ShareLink
      title="高并发系统实践"
      shortLink="https://short.link/abc123"
      cover="https://media.istockphoto.com/id/2195353667/zh/%E7%85%A7%E7%89%87/taj-mahal-with-gorgeous-landscape-at-sunset-in-agra-india.jpg?s=1024x1024&w=is&k=20&c=a_fzsAl0zBOpCTsDbjlnfo1ZtubPBcMiRfaA5sYpakw="
    />
  </div>
</template>
<script lang="ts" setup>
import { computed, reactive } from 'vue'
import ShareLink from '@/components/share-link/index.vue'

type Key = string

interface DataType {
  key: Key
  name: string
  age: number
  address: string
}

const columns = [
  {
    title: 'Name',
    dataIndex: 'name',
  },
  {
    title: 'Age',
    dataIndex: 'age',
  },
  {
    title: 'Address',
    dataIndex: 'address',
  },
]

const data: DataType[] = []
for (let i = 0; i < 46; i++) {
  data.push({
    key: i,
    name: `Edward King ${i}`,
    age: 32,
    address: `London, Park Lane no. ${i}`,
  })
}

const state = reactive<{
  selectedRowKeys: Key[]
  loading: boolean
}>({
  selectedRowKeys: [], // Check here to configure the default column
  loading: false,
})

const onSelectChange = (selectedRowKeys: Key[]) => {
  console.log('selectedRowKeys changed: ', selectedRowKeys)
  state.selectedRowKeys = selectedRowKeys
}
</script>
