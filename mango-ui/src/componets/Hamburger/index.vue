<template>
  <div class="hamburger-container" @click="toggleCollapse">
    <i class="fa fa-bars hamburger" :class="{ 'is-active': collapse }"></i>
  </div>
</template>

<script>
import { mapState, mapMutations } from 'vuex'

export default {
  name: 'Hamburger',
  computed: {
    ...mapState({
      collapse: state => state.app.collapse
    })
  },
  methods: {
    ...mapMutations(['onCollapse']),
    toggleCollapse () {
      this.onCollapse()
    }
  }
}
</script>

<style scoped>
.hamburger-container {
  display: inline-block;
  padding: 0 15px;
  cursor: pointer;
  line-height: 60px;
}
.hamburger {
  font-size: 20px;
  color: var(--neu-text);
  transition: transform 0.3s;
}
.hamburger.is-active {
  transform: rotate(90deg);
}
</style>
