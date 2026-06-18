<template>
  <div class="personal-panel">
    <div class="personal-desc" :style="{ backgroundColor: themeColor }">
      <el-avatar :src="avatar" :size="60"></el-avatar>
    </div>
    <div class="name-role">
      <span>{{ user.name }} ~ {{ user.role }}</span>
    </div>
    <div class="registe-info">
      <span><i class="fa fa-clock-o"></i> {{ user.registerInfo }}</span>
    </div>
    <div class="person-relation">
      <span class="relation-item"><b>1</b><br>followers</span>
      <span class="relation-item"><b>1</b><br>watches</span>
      <span class="relation-item"><b>1</b><br>friends</span>
    </div>
    <div class="operation-list">
      <el-button size="small" icon="fa fa-male" @click="handleProfile">个人中心</el-button>
      <el-button size="small" icon="fa fa-key" @click="handlePassword">修改密码</el-button>
      <el-button size="small" icon="fa fa-eraser" @click="handleClearCache">清除缓存</el-button>
    </div>
    <div class="logout-area">
      <el-button type="danger" size="small" icon="fa fa-sign-out" @click="handleLogout" plain>退出登录</el-button>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import avatarImg from '@/assets/user.png'
import Cookies from 'js-cookie'

export default {
  name: 'PersonalPanel',
  data () {
    return {
      avatar: avatarImg,
      user: {
        name: '',
        role: '管理员',
        registerInfo: '注册于 2026-01-01'
      }
    }
  },
  mounted () {
    this.user.name = sessionStorage.getItem('user') || 'admin'
  },
  computed: {
    ...mapState({
      themeColor: state => state.app.themeColor
    })
  },
  methods: {
    handleProfile () {
      this.$emit('close')
      this.$router.push('/profile')
    },
    handlePassword () {
      this.$emit('close')
      this.$message.info('修改密码')
    },
    handleClearCache () {
      this.$emit('close')
      this.$message.success('缓存已清除')
    },
    handleLogout () {
      this.$emit('close')
      Cookies.remove('token')
      sessionStorage.removeItem('user')
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>
.personal-panel {
  text-align: center;
}
.personal-desc {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
  border-radius: 4px 4px 0 0;
}
.name-role {
  padding: 10px 0 5px;
  font-size: 16px;
  font-weight: bold;
}
.registe-info {
  color: #999;
  font-size: 13px;
  padding-bottom: 10px;
}
.person-relation {
  display: flex;
  justify-content: space-around;
  padding: 10px 0;
  border-top: 1px solid #eee;
  border-bottom: 1px solid #eee;
}
.relation-item {
  font-size: 12px;
  color: #999;
}
.relation-item b {
  display: block;
  font-size: 16px;
  color: #333;
  margin-bottom: 3px;
}
.operation-list {
  padding: 10px 0;
}
.operation-list .el-button {
  display: block;
  width: 100%;
  margin: 5px 0;
  text-align: left;
}
.logout-area {
  padding: 5px 0 0;
  border-top: 1px solid #eee;
}
.logout-area .el-button {
  width: 100%;
}
</style>
