<template>
  <div>
     {{usePermission.getMenus()}}
  </div>
</template>
<script lang="ts" setup>
import {usePermissionStore} from "@/stores/modules/permission.ts";
const usePermission=usePermissionStore()
usePermission.getMenus()
</script>
