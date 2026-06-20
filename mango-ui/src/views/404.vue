<template>
<div class="site-wrapper site-page--not-found">
  <h2>404</h2>
  <el-button @click="$router.go(-1)">返回上一页</el-button>
  <el-button type="primary" class="not-found-btn-go">主要按钮</el-button>
</div>
</template>

<style scoped lang="scss">
 .site-wrapper.site-page--not-found{
   position: absolute;
   top: 60px;
 }

</style>

<script>
export default {
  name: 'NotFound'
}
</script>
