<template>
  <div class="headbar" :class="collapse ? 'position-collapse-left' : 'position-left'">
    <div class="left-area">
      <hamburger/>
      <el-menu mode="horizontal" class="nav-menu" :default-active="activeMenu"
               background-color="#fff" text-color="#333" active-text-color="#14889A">
        <nav-menu v-for="menu in navTree" :key="menu.id" :menu="menu"/>
      </el-menu>
    </div>
    <div class="right-area">
      <theme-picker class="theme-picker"/>
      <el-dropdown @command="changeLanguage" class="lang-dropdown">
        <span class="lang-btn">
          <i class="fa fa-language"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="zh_cn">简体中文</el-dropdown-item>
          <el-dropdown-item command="en_us">English</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <el-popover ref="popover-notice" placement="bottom-end" trigger="click" append-to-body :width="280">
        <notice-panel/>
        <span slot="reference" class="notice-btn">
          <el-badge :value="4" :max="99" type="danger" class="notice-badge">
            <i class="fa fa-bell-o"></i>
          </el-badge>
        </span>
      </el-popover>
      <el-popover ref="popover-message" placement="bottom-end" trigger="click" append-to-body :width="300">
        <message-panel/>
        <span slot="reference" class="notice-btn">
          <el-badge :value="5" :max="99" type="danger" class="notice-badge">
            <i class="fa fa-envelope-o"></i>
          </el-badge>
        </span>
      </el-popover>
      <el-popover ref="popover-user" placement="bottom-end" :width="300" trigger="click" append-to-body>
        <personal-panel @close="closePopover"/>
        <span slot="reference" class="user-info">
          <img v-if="user.avatar" :src="user.avatar" class="avatar"/>
          <i v-else class="fa fa-user-circle avatar-icon"></i>
          {{ user.username }}
          <i class="fa fa-caret-down"></i>
        </span>
      </el-popover>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import Hamburger from '@/componets/Hamburger/index.vue'
import ThemePicker from '@/componets/ThemePicker/index.vue'
import NavMenu from '@/componets/NavMenu/index.vue'
import PersonalPanel from '@/views/core/PersonalPanel.vue'
import NoticePanel from '@/views/NoticePanel.vue'
import MessagePanel from '@/views/core/MessagePanel.vue'

export default {
  name: 'HeadBar',
  components: { MessagePanel, NoticePanel, Hamburger, ThemePicker, PersonalPanel, NavMenu },
  data () {
    return {
      user: {
        avatar: '',
        username: ''
      }
    }
  },
  mounted () {
    var account = sessionStorage.getItem('user')
    if (account) {
      this.user.username = account
    }
  },
  computed: {
    ...mapState({
      collapse: state => state.app.collapse,
      navTree: state => state.menu.navTree
    }),
    activeMenu () {
      return this.$route.path
    }
  },
  methods: {
    changeLanguage (lang) {
      this.$i18n.locale = lang
    },
    closePopover () {
      this.$refs['popover-user'].doClose()
    }
  }
}
</script>

<style scoped>
.headbar {
  position: fixed;
  top: 0;
  right: 0;
  height: 60px;
  line-height: 60px;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
  display: flex;
  align-items: center;
  justify-content: space-between;
  transition: left 0.3s;
  z-index: 1000;
  padding: 0 20px 0 0;
}
.position-left {
  left: 200px;
}
.position-collapse-left {
  left: 64px;
}
.left-area {
  display: flex;
  align-items: center;
  flex: 1;
  overflow: hidden;
}
.nav-menu {
  border-bottom: none;
  margin-left: 10px;
}
.nav-menu.el-menu--horizontal > .el-menu-item,
.nav-menu.el-menu--horizontal > .el-submenu {
  height: 60px;
  line-height: 60px;
}
.right-area {
  display: flex;
  align-items: center;
}
.lang-dropdown {
  margin-right: 15px;
}
.lang-btn {
  cursor: pointer;
  font-size: 20px;
  color: #333;
}
.user-info {
  display: flex;
  align-items: center;
  cursor: pointer;
  color: #333;
  font-size: 14px;
}
.user-info .avatar {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  margin-right: 8px;
}
.user-info .avatar-icon {
  font-size: 28px;
  color: #14889A;
  margin-right: 8px;
}
.user-info .fa-caret-down {
  margin-left: 5px;
}
.theme-picker {
  margin-right: 15px;
}
.notice-btn {
  cursor: pointer;
  margin-right: 15px;
  font-size: 20px;
  color: #333;
}
.notice-badge {
  display: inline-flex;
  line-height: 1;
}
</style>
