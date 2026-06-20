<template>
  <div id="main-container" class="main-container" :class="collapse ? 'position-collapse-left' : 'position-left'">
    <div class="main-content">
      <slot></slot>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: 'MainContent',
  computed: {
    ...mapState({
      collapse: state => state.app.collapse
    })
  }
}
</script>

<style scoped>
.main-container {
  color: #42b983;
  position: fixed;
  top: 60px;
  right: 0;
  bottom: 0;
  overflow: auto;
  transition: left 0.3s;
}
.position-left {
  left: 200px;
}
.position-collapse-left {
  left: 64px;
}
.main-content {
  padding: 20px;
}
</style>
