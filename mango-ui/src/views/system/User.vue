<template>
  <div class="user-container">
    <!-- 搜索栏 -->
    <div class="search-bar">
      <el-form :inline="true" :model="filters" size="small">
        <el-form-item label="用户名">
          <el-input v-model="filters.name" placeholder="请输入用户名" clearable
                    @keyup.enter.native="search"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="fa fa-search" @click="search">搜 索</el-button>
        </el-form-item>
      </el-form>
    </div>
    <!-- 工具栏 -->
    <div class="toolbar">
      <el-button type="primary" size="small" icon="fa fa-plus" @click="handleAdd">新 增</el-button>
    </div>
    <!-- 数据表格 -->
    <el-table :data="pageResult.rows" style="width: 100%" stripe border size="small"
              v-loading="loading" element-loading-text="加载中...">
      <el-table-column type="selection" width="50"></el-table-column>
      <el-table-column prop="name" label="用户名" width="120"></el-table-column>
      <el-table-column prop="email" label="邮箱" width="180"></el-table-column>
      <el-table-column prop="mobile" label="手机" width="140"></el-table-column>
      <el-table-column prop="status" label="状态" width="80" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status === 1 ? 'success' : 'danger'" size="mini">
            {{ scope.row.status === 1 ? '正常' : '禁用' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="160"></el-table-column>
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button size="mini" icon="fa fa-edit" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" icon="fa fa-trash" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="pagination">
      <el-pagination
        background
        layout="total, sizes, prev, pager, next, jumper"
        :page-sizes="[10, 20, 50, 100]"
        :page-size="pageSize"
        :current-page="pageNum"
        :total="pageResult.total"
        @size-change="handleSizeChange"
        @current-change="handlePageChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SysUser',
  data () {
    return {
      filters: {
        name: ''
      },
      pageNum: 1,
      pageSize: 10,
      pageResult: {
        total: 0,
        rows: []
      },
      loading: false
    }
  },
  mounted () {
    this.search()
  },
  methods: {
    search () {
      this.loading = true
      this.$api.user.findPage({
        pageNum: this.pageNum,
        pageSize: this.pageSize,
        name: this.filters.name
      }).then(res => {
        this.pageResult = res.data || { total: 0, rows: [] }
        this.loading = false
      }).catch(() => {
        this.loading = false
      })
    },
    handleAdd () {
      this.$message.info('新增用户')
    },
    handleEdit (row) {
      this.$message.info('编辑用户: ' + row.name)
    },
    handleDelete (row) {
      this.$confirm('确认删除用户 ' + row.name + '?', '提示', {
        type: 'warning'
      }).then(() => {
        this.$message.success('删除成功')
      }).catch(() => {})
    },
    handleSizeChange (val) {
      this.pageSize = val
      this.search()
    },
    handlePageChange (val) {
      this.pageNum = val
      this.search()
    }
  }
}
</script>

<style scoped>
.user-container {
  padding: 10px;
}
.search-bar {
  margin-bottom: 15px;
}
.toolbar {
  margin-bottom: 15px;
}
.pagination {
  margin-top: 15px;
  text-align: right;
}
</style>
