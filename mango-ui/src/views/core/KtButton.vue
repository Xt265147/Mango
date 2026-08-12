<template>
  <el-button :size="size" :type="type" :icon="icon" :loading="loading"
             :disabled="!hasPerms(perms)" @click="handleClick">{{ label }}
  </el-button>
</template>

<script>
import { hasPermission } from '@/permission/index'

export default {
  name: 'KtButton',
  props: {
    label: {
      type: String,
      default: 'Button'
    },
    icon: {
      type: String,
      default: ''
    },
    size: {
      type: String,
      default: 'mini'
    },
    type: {
      type: String,
      default: null
    },
    loading: {
      type: Boolean,
      default: false
    },
    disabled: {
      type: Boolean,
      default: false
    },
    perms: {
      type: String,
      default: null
    }
  },
  methods: {
    handleClick () {
      this.$emit('click')
    },
    hasPerms (perms) {
      return hasPermission(perms) && !this.disabled
    }
  }
}
</script>

<style scoped>
.el-button {
  border-radius: var(--neu-radius-sm) !important;
  box-shadow:
    3px 3px 7px var(--neu-shadow-dark),
    -3px -3px 7px var(--neu-shadow-light) !important;
  transition: all var(--neu-transition);
}
.el-button:hover {
  box-shadow:
    4px 4px 10px var(--neu-shadow-dark),
    -4px -4px 10px var(--neu-shadow-light) !important;
}
.el-button:active {
  box-shadow:
    inset 2px 2px 5px var(--neu-shadow-dark),
    inset -2px -2px 5px var(--neu-shadow-light) !important;
  transform: scale(0.97);
}
</style>
