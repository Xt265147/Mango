<template>
  <div class="login-container">
    <el-card class="login-card" shadow="hover">
      <div class="login-header">
        <h2>Mango Platform</h2>
        <p>后台管理系统</p>
      </div>
      <el-form ref="loginForm" :model="loginForm" :rules="rules" label-width="0" size="medium">
        <el-form-item prop="account">
          <el-input
            v-model="loginForm.account"
            prefix-icon="fa fa-user"
            placeholder="请输入用户名"
            @keyup.enter.native="login()">
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            v-model="loginForm.password"
            prefix-icon="fa fa-lock"
            type="password"
            placeholder="请输入密码"
            @keyup.enter.native="login()">
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            class="login-btn"
            type="primary"
            :loading="loading"
            @click="login()">
            登 录
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(135deg, #14889A 0%, #2b6e8e 50%, #1a4f6e 100%);
}
.login-card {
  width: 400px;
  padding: 20px;
  border-radius: 8px;
}
.login-header {
  text-align: center;
  margin-bottom: 30px;
}
.login-header h2 {
  color: #14889A;
  font-size: 28px;
  margin-bottom: 8px;
}
.login-header p {
  color: #999;
  font-size: 14px;
}
.login-btn {
  width: 100%;
  margin-top: 10px;
}
</style>

<script>
import Cookies from 'js-cookie'
import router from '@/router'

export default {
  name: 'Login',
  data () {
    return {
      loginForm: {
        account: '',
        password: '',
        captcha: ''
      },
      rules: {
        account: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      },
      loading: false
    }
  },
  methods: {
    login () {
      this.$refs.loginForm.validate((valid) => {
        if (!valid) return
        this.loading = true
        let userInfo = {
          username: this.loginForm.account,
          password: this.loginForm.password,
          captcha: this.loginForm.captcha
        }
        this.$api.login.login(userInfo).then((res) => { // 调用登录接口
          if (res.code !== 200) {
            this.$message({ message: res.message, type: 'error' })
          } else {
            Cookies.set('token', res.data.token) // 设置token到 cookie
            sessionStorage.setItem('user', res.data.username)
            this.$message({ message: '登录成功', type: 'success' })
            router.push('/')
          }
          this.loading = false
        }).catch(() => {
          this.$message({ message: '登录失败', type: 'error' })
          this.loading = false
        })
      })
    }
  }
}
</script>
