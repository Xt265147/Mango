<template>
  <div class="container">
    <nav-bar></nav-bar>
    <head-bar></head-bar>
    <main-content></main-content>
  </div>
</template>

<style scoped>
.container {
  height: 100%;
  background: var(--neu-bg);
}
</style>

<script>
import NavBar from '@/views/NavBar.vue'
import HeadBar from '@/views/HeadBar.vue'
import MainContent from '@/views/MainContent.vue'

export default {
  name: 'Home',
  components: { NavBar, HeadBar, MainContent }
}
</script>
