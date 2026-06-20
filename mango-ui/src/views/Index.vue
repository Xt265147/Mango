<template>
  <div class="index-page">
    <h2>{{ $t('common.welcome') }}，{{ username }}</h2>
    <p class="sub-text">{{ $t('common.systemName') }}</p>
  </div>
</template>

<script>
export default {
  name: 'Index',
  data () {
    return {
      username: ''
    }
  },
  mounted () {
    this.username = sessionStorage.getItem('user') || '用户'
  }
}
</script>

<style scoped>
.index-page {
  text-align: center;
  padding: 60px 20px;
}
.index-page h2 {
  color: #333;
  font-size: 24px;
  margin-bottom: 10px;
}
.sub-text {
  color: #999;
  font-size: 14px;
}
</style>
