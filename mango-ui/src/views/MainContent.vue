<template>
  <div id="main-container" class="main-container" :class="collapse ? 'position-collapse-left' : 'position-left'">
    <!-- 标签页 -->
    <div class="tab-container">
      <el-tabs v-model="mainTabsActiveName" type="card" @tab-click="handleTabClick" @tab-remove="handleTabRemove">
        <el-tab-pane v-for="tab in mainTabs" :key="tab.path" :label="tab.name" :name="tab.path"
                     :closable="tab.path !== '/'"></el-tab-pane>
      </el-tabs>
    </div>
    <!-- 主内容区域 -->
    <div class="main-content">
      <transition name="fade" mode="out-in">
        <router-view></router-view>
      </transition>
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
    }),
    mainTabs: {
      get () { return this.$store.state.tab.mainTabs },
      set (val) { this.$store.commit('updateMainTabs', val) }
    },
    mainTabsActiveName: {
      get () { return this.$store.state.tab.mainTabsActiveName },
      set (val) { this.$store.commit('updateMainTabsActiveName', val) }
    }
  },
  watch: {
    '$route': function (to) {
      this.addTab(to)
    }
  },
  mounted () {
    this.addTab(this.$route)
  },
  methods: {
    addTab (route) {
      if (!route || route.path === '/login') return
      let exists = this.mainTabs.some(function (tab) {
        return tab.path === route.path
      })
      if (!exists) {
        let name = (route.meta && route.meta.title) || route.name || route.path
        this.mainTabs = this.mainTabs.concat({ path: route.path, name: name })
      }
      this.mainTabsActiveName = route.path
    },
    handleTabClick (tab) {
      this.$router.push(tab.name)
    },
    handleTabRemove (path) {
      let tabs = this.mainTabs
      let activeTab = this.mainTabsActiveName
      if (activeTab === path) {
        tabs.forEach(function (tab, index) {
          if (tab.path === path) {
            let nextTab = tabs[index + 1] || tabs[index - 1]
            if (nextTab) activeTab = nextTab.path
          }
        })
      }
      this.mainTabs = this.mainTabs.filter(function (tab) {
        return tab.path !== path
      })
      this.$router.push(activeTab)
    },
    // tabs, 选中tab
    selectedTabHandle (tab) {
      let matchTabs = this.mainTabs.filter(item => item.name === tab.name)
      if (matchTabs.length >= 1) {
        this.$router.push({ name: matchTabs[0].name })
      }
    },
    // tabs, 删除tab
    removeTabHandle (tabName) {
      this.mainTabs = this.mainTabs.filter(item => item.name !== tabName)
      if (this.mainTabs.length >= 1) {
        // 当前选中tab被删除
        if (tabName === this.mainTabsActiveName) {
          this.$router.push({ name: this.mainTabs[this.mainTabs.length - 1].name })
          this.mainTabsActiveName = this.$route.name
        }
      } else {
        this.$router.push('/')
      }
    },
    // tabs, 关闭当前
    tabsCloseCurrentHandle () {
      this.removeTabHandle(this.mainTabsActiveName)
    },
    // tabs, 关闭其他
    tabsCloseOtherHandle () {
      this.mainTabs = this.mainTabs.filter(item => item.name === this.mainTabsActiveName)
    },
    // tabs, 关闭全部
    tabsCloseAllHandle () {
      this.mainTabs = []
      this.$router.push('/')
    },
    // tabs, 刷新当前
    tabsRefreshCurrentHandle () {
      let tempTabName = this.mainTabsActiveName
      this.removeTabHandle(tempTabName)
      this.$nextTick(() => {
        this.$router.push({ name: tempTabName })
      })
    }
  }
}
</script>

<style scoped>
.main-container {
  position: fixed;
  top: 60px;
  right: 0;
  bottom: 0;
  overflow: hidden;
  transition: left 0.3s;
  display: flex;
  flex-direction: column;
}
.position-left {
  left: 200px;
}
.position-collapse-left {
  left: 64px;
}
.tab-container {
  background: var(--neu-bg);
  border-bottom: 1px solid var(--neu-shadow-dark);
  padding: 0 10px;
}
.tab-container >>> .el-tabs__header {
  margin: 0;
}
.tab-container >>> .el-tabs__item {
  height: 40px;
  line-height: 40px;
  font-size: 13px;
}
.tab-container >>> .el-tabs--card > .el-tabs__header .el-tabs__nav {
  border: none;
}
.tab-container >>> .el-tabs--card > .el-tabs__header .el-tabs__item {
  border: none;
  border-bottom: 1px solid var(--neu-shadow-dark);
  margin-right: 3px;
  border-radius: var(--neu-radius-sm) var(--neu-radius-sm) 0 0;
  box-shadow:
    2px 0 4px var(--neu-shadow-dark),
    -1px -1px 3px var(--neu-shadow-light);
  background: var(--neu-bg-alt);
}
.tab-container >>> .el-tabs--card > .el-tabs__header .el-tabs__item.is-active {
  border-bottom: none;
  background: var(--neu-bg);
  box-shadow:
    3px -2px 8px var(--neu-shadow-dark),
    -3px -2px 8px var(--neu-shadow-light);
}
.main-content {
  flex: 1;
  padding: 20px;
  overflow: auto;
}
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.2s;
}
.fade-enter,
.fade-leave-to {
  opacity: 0;
}
</style>
