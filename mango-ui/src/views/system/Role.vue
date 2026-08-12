<template>
  <div class="role-container">
    <!-- 搜索栏 -->
    <el-form :inline="true" :model="filters" size="small" class="search-bar">
      <el-form-item label="角色名称">
        <el-input v-model="filters.name" placeholder="请输入角色名称" clearable
                  @keyup.enter.native="findPage"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="fa fa-search" @click="findPage">查 询</el-button>
      </el-form-item>
      <el-form-item>
        <kt-button label="新增" icon="fa fa-plus" perms="sys:role:add" type="primary" @click="handleAdd"/>
      </el-form-item>
    </el-form>
    <!-- 数据表格 -->
    <kt-table :data="pageResult" :columns="columns" :loading="loading"
              :page-num="pageNum" :page-size="pageSize"
              perms-edit="sys:role:edit" perms-delete="sys:role:delete"
              perms-batch-delete="sys:role:delete"
              @findPage="findPage" @handleEdit="handleEdit" @handleDelete="handleDelete"
              @handleBatchDelete="handleBatchDelete"
              @sizeChange="handleSizeChange" @pageChange="handlePageChange">
    </kt-table>
    <!-- 新增/编辑弹窗 -->
    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="500px" :close-on-click-modal="false">
      <el-form :model="formData" :rules="formRules" ref="roleForm" label-width="80px" size="small">
        <el-form-item label="角色名称" prop="name">
          <el-input v-model="formData.name" placeholder="请输入角色名称"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="formData.remark" type="textarea" :rows="3" placeholder="请输入备注"></el-input>
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
  name: 'SysRole',
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
        { prop: 'name', label: '角色名称', width: '180' },
        { prop: 'remark', label: '备注', width: '250' },
        { prop: 'createTime', label: '创建时间', width: '160' }
      ],
      dialogVisible: false,
      dialogTitle: '',
      submitLoading: false,
      formData: {
        id: null,
        name: '',
        remark: ''
      },
      formRules: {
        name: [{ required: true, message: '请输入角色名称', trigger: 'blur' }]
      }
    }
  },
  mounted () {
    this.findPage()
  },
  methods: {
    findPage () {
      this.loading = true
      this.$api.role.findPage({
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
      this.dialogTitle = '新增角色'
      this.formData = { id: null, name: '', remark: '' }
      this.dialogVisible = true
      this.$nextTick(() => {
        this.$refs.roleForm && this.$refs.roleForm.clearValidate()
      })
    },
    handleEdit (row) {
      this.dialogTitle = '编辑角色'
      this.formData = {
        id: row.id,
        name: row.name,
        remark: row.remark || ''
      }
      this.dialogVisible = true
      this.$nextTick(() => {
        this.$refs.roleForm && this.$refs.roleForm.clearValidate()
      })
    },
    submitForm () {
      this.$refs.roleForm.validate(valid => {
        if (!valid) return
        this.submitLoading = true
        this.$api.role.save(this.formData).then(() => {
          this.$message.success(this.formData.id ? '修改成功' : '新增成功')
          this.dialogVisible = false
          this.findPage()
        }).catch(() => {}).finally(() => {
          this.submitLoading = false
        })
      })
    },
    handleDelete (row) {
      this.$confirm('确认删除角色 ' + row.name + '?', '提示', {
        type: 'warning'
      }).then(() => {
        this.$api.role.batchDelete({ ids: row.id }).then(() => {
          this.$message.success('删除成功')
          this.findPage()
        }).catch(() => {})
      }).catch(() => {})
    },
    handleBatchDelete (rows) {
      let names = rows.map(function (row) { return row.name }).join('、')
      this.$confirm('确认批量删除角色: ' + names + '?', '提示', {
        type: 'warning'
      }).then(() => {
        let ids = rows.map(function (row) { return row.id })
        this.$api.role.batchDelete({ ids: ids.join(',') }).then(() => {
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
.role-container {
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
