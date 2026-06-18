<template>
  <div class="notice-panel">
    <div class="header">您有 {{ notices.length }} 条通知</div>
    <div class="notice-content">
      <div v-for="item in notices" :key="item.key" class="notice-item">
        <span class="notice-icon">
          <i :class="item.icon"></i>
        </span>
        <span class="notice-text">{{ item.content }}</span>
      </div>
    </div>
    <div class="notice-footer">查看所有通知</div>
  </div>
</template>

<script>
export default {
  name: 'NoticePanel',
  data () {
    return {
      notices: [
        { key: 1, icon: 'fa fa-envelope-o', content: '你有3条新消息' },
        { key: 2, icon: 'fa fa-tasks', content: '你有5个待处理任务' },
        { key: 3, icon: 'fa fa-comment-o', content: '你有2条新评论' },
        { key: 4, icon: 'fa fa-shopping-cart', content: '你有1个新订单' }
      ]
    }
  }
}
</script>

<style scoped>
.notice-panel {
  min-width: 250px;
}
.header {
  padding: 10px 0;
  font-size: 14px;
  font-weight: bold;
  border-bottom: 1px solid #eee;
  text-align: center;
}
.notice-content {
  max-height: 250px;
  overflow-y: auto;
}
.notice-item {
  display: flex;
  align-items: center;
  padding: 10px;
  cursor: pointer;
  border-bottom: 1px solid #f5f5f5;
}
.notice-item:hover {
  background: #f5f7fa;
}
.notice-icon {
  margin-right: 10px;
  color: #14889A;
  font-size: 16px;
}
.notice-text {
  font-size: 13px;
  color: #333;
}
.notice-footer {
  padding: 10px 0;
  text-align: center;
  font-size: 13px;
  color: #14889A;
  cursor: pointer;
  border-top: 1px solid #eee;
}
.notice-footer:hover {
  color: #0d6d7e;
}
</style>
