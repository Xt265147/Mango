<template>
  <div class="user-container">
    <!-- 搜索栏 -->
    <el-form :inline="true" :model="filters" size="small" class="search-bar">
      <el-form-item label="用户名">
        <el-input v-model="filters.name" placeholder="请输入用户名" clearable
                  @keyup.enter.native="findPage"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="fa fa-search" @click="findPage">查 询</el-button>
      </el-form-item>
      <el-form-item>
        <kt-button label="新增" icon="fa fa-plus" perms="sys:user:add" type="primary" @click="handleAdd"/>
      </el-form-item>
    </el-form>
    <!-- 数据表格 -->
    <kt-table :data="pageResult" :columns="columns" :loading="loading"
              :page-num="pageNum" :page-size="pageSize"
              perms-edit="sys:user:edit" perms-delete="sys:user:delete"
              perms-batch-delete="sys:user:delete"
              @findPage="findPage" @handleEdit="handleEdit" @handleDelete="handleDelete"
              @handleBatchDelete="handleBatchDelete"
              @sizeChange="handleSizeChange" @pageChange="handlePageChange">
      <template slot="status" slot-scope="scope">
        <el-tag :type="scope.row.status === 1 ? 'success' : 'danger'" size="mini">
          {{ scope.row.status === 1 ? '正常' : '禁用' }}
        </el-tag>
      </template>
    </kt-table>
    <!-- 新增/编辑弹窗 -->
    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="500px" :close-on-click-modal="false">
      <el-form :model="formData" :rules="formRules" ref="userForm" label-width="80px" size="small">
        <el-form-item label="用户名" prop="name">
          <el-input v-model="formData.name" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="formData.email" placeholder="请输入邮箱"></el-input>
        </el-form-item>
        <el-form-item label="手机" prop="mobile">
          <el-input v-model="formData.mobile" placeholder="请输入手机号"></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="formData.status">
            <el-radio :label="1">正常</el-radio>
            <el-radio :label="0">禁用</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button size="small" @click="dialogVisible = false">取 消</el-button>
        <el-button size="small" type="primary" :loading="submitLoading" @click="submitForm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import KtButton from '@/views/core/KtButton.vue'
import KtTable from '@/views/core/KtTable.vue'

export default {
  name: 'SysUser',
  components: { KtButton, KtTable },
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
      loading: false,
      columns: [
        { prop: 'name', label: '用户名', width: '120' },
        { prop: 'email', label: '邮箱', width: '180' },
        { prop: 'mobile', label: '手机', width: '140' },
        { prop: 'status', label: '状态', width: '80', align: 'center' },
        { prop: 'createTime', label: '创建时间', width: '160' }
      ],
      dialogVisible: false,
      dialogTitle: '',
      submitLoading: false,
      formData: {
        id: null,
        name: '',
        email: '',
        mobile: '',
        status: 1
      },
      formRules: {
        name: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        email: [{ required: true, message: '请输入邮箱', trigger: 'blur' }]
      }
    }
  },
  mounted () {
    this.findPage()
  },
  methods: {
    findPage () {
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
      this.dialogTitle = '新增用户'
      this.formData = { id: null, name: '', email: '', mobile: '', status: 1 }
      this.dialogVisible = true
      this.$nextTick(() => {
        this.$refs.userForm && this.$refs.userForm.clearValidate()
      })
    },
    handleEdit (row) {
      this.dialogTitle = '编辑用户'
      this.formData = {
        id: row.id,
        name: row.name,
        email: row.email,
        mobile: row.mobile,
        status: row.status
      }
      this.dialogVisible = true
      this.$nextTick(() => {
        this.$refs.userForm && this.$refs.userForm.clearValidate()
      })
    },
    submitForm () {
      this.$refs.userForm.validate(valid => {
        if (!valid) return
        this.submitLoading = true
        this.$api.user.save(this.formData).then(() => {
          this.$message.success(this.formData.id ? '修改成功' : '新增成功')
          this.dialogVisible = false
          this.findPage()
        }).catch(() => {}).finally(() => {
          this.submitLoading = false
        })
      })
    },
    handleDelete (row) {
      this.$confirm('确认删除用户 ' + row.name + '?', '提示', {
        type: 'warning'
      }).then(() => {
        this.$api.user.batchDelete({ ids: row.id }).then(() => {
          this.$message.success('删除成功')
          this.findPage()
        }).catch(() => {})
      }).catch(() => {})
    },
    handleBatchDelete (rows) {
      let names = rows.map(function (row) { return row.name }).join('、')
      this.$confirm('确认批量删除用户: ' + names + '?', '提示', {
        type: 'warning'
      }).then(() => {
        let ids = rows.map(function (row) { return row.id })
        this.$api.user.batchDelete({ ids: ids.join(',') }).then(() => {
          this.$message.success('批量删除成功')
          this.findPage()
        }).catch(() => {})
      }).catch(() => {})
    },
    handleSizeChange (val) {
      this.pageSize = val
      this.findPage()
    },
    handlePageChange (val) {
      this.pageNum = val
      this.findPage()
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
  padding: 16px;
  border-radius: var(--neu-radius);
  box-shadow:
    6px 6px 14px var(--neu-shadow-dark),
    -6px -6px 14px var(--neu-shadow-light);
}
</style>
