<template>
  <div class="message-panel">
    <div class="message-header">您有 {{ messages.length }} 条消息</div>
    <div class="message-content">
      <div v-for="item in messages" :key="item.key" class="message-item">
        <img :src="avatar" class="msg-avatar"/>
        <div class="msg-body">
          <span class="msg-sender">{{ item.sender }}</span>
          <span class="msg-text">{{ item.content }}</span>
        </div>
      </div>
    </div>
    <div class="message-footer">
      <el-button type="primary" size="small">查看全部消息</el-button>
    </div>
  </div>
</template>

<script>
import avatarImg from '@/assets/user.png'

export default {
  name: 'MessagePanel',
  data () {
    return {
      avatar: avatarImg,
      messages: [
        { key: 1, sender: '系统通知', content: '您的账号已成功登录' },
        { key: 2, sender: '管理员', content: '请及时处理待办任务' },
        { key: 3, sender: '系统通知', content: '系统将于今晚进行维护' }
      ]
    }
  }
}
</script>

<style scoped>
.message-panel {
  min-width: 280px;
}
.message-header {
  padding: 10px 0;
  font-size: 14px;
  font-weight: bold;
  border-bottom: 1px solid #eee;
  text-align: center;
}
.message-content {
  max-height: 250px;
  overflow-y: auto;
}
.message-item {
  display: flex;
  align-items: flex-start;
  padding: 10px;
  cursor: pointer;
  border-bottom: 1px solid #f5f5f5;
}
.message-item:hover {
  background: #f5f7fa;
}
.msg-avatar {
  width: 36px;
  height: 36px;
  border-radius: 50%;
  margin-right: 10px;
  flex-shrink: 0;
}
.msg-body {
  display: flex;
  flex-direction: column;
}
.msg-sender {
  font-size: 13px;
  font-weight: bold;
  color: #333;
  margin-bottom: 3px;
}
.msg-text {
  font-size: 12px;
  color: #999;
}
.message-footer {
  padding: 10px 0;
  text-align: center;
  border-top: 1px solid #eee;
}
</style>
