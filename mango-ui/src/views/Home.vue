<template>
  <div class="container">
    <nav-bar></nav-bar>
    <head-bar></head-bar>
    <main-content>
      <transition name="fade" mode="out-in">
        <router-view></router-view>
      </transition>
    </main-content>
  </div>
</template>

<style scoped>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.2s;
}
.fade-enter,
.fade-leave-to {
  opacity: 0;
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
