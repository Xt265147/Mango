<template>
  <div class="dept-container">
    <!-- 搜索栏 -->
    <el-form :inline="true" :model="filters" size="small" class="search-bar">
      <el-form-item label="机构名称">
        <el-input v-model="filters.name" placeholder="请输入机构名称" clearable
                  @keyup.enter.native="findPage"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="fa fa-search" @click="findPage">查 询</el-button>
      </el-form-item>
      <el-form-item>
        <kt-button label="新增" icon="fa fa-plus" perms="sys:dept:add" type="primary" @click="handleAdd"/>
      </el-form-item>
    </el-form>
    <!-- 数据表格 -->
    <kt-table :data="pageResult" :columns="columns" :loading="loading"
              :page-num="pageNum" :page-size="pageSize"
              perms-edit="sys:dept:edit" perms-delete="sys:dept:delete"
              perms-batch-delete="sys:dept:delete"
              @findPage="findPage" @handleEdit="handleEdit" @handleDelete="handleDelete"
              @handleBatchDelete="handleBatchDelete"
              @sizeChange="handleSizeChange" @pageChange="handlePageChange">
    </kt-table>
    <!-- 新增/编辑弹窗 -->
    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="500px" :close-on-click-modal="false">
      <el-form :model="formData" :rules="formRules" ref="deptForm" label-width="100px" size="small">
        <el-form-item label="机构名称" prop="name">
          <el-input v-model="formData.name" placeholder="请输入机构名称"></el-input>
        </el-form-item>
        <el-form-item label="上级机构">
          <el-select v-model="formData.parentId" placeholder="无" clearable style="width: 100%">
            <el-option label="无" :value="0"></el-option>
            <el-option v-for="item in allDepts" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="排序">
          <el-input-number v-model="formData.orderNum" :min="0" :max="999"></el-input-number>
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
  name: 'SysDept',
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
        { prop: 'name', label: '机构名称', width: '200' },
        { prop: 'orderNum', label: '排序', width: '100', align: 'center' },
        { prop: 'createTime', label: '创建时间', width: '160' }
      ],
      allDepts: [],
      dialogVisible: false,
      dialogTitle: '',
      submitLoading: false,
      formData: {
        id: null,
        name: '',
        parentId: 0,
        orderNum: 0
      },
      formRules: {
        name: [{ required: true, message: '请输入机构名称', trigger: 'blur' }]
      }
    }
  },
  mounted () {
    this.findPage()
    this.loadAllDepts()
  },
  methods: {
    findPage () {
      this.loading = true
      this.$api.dept.findPage({
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
    loadAllDepts () {
      this.$api.dept.findAll().then(res => {
        this.allDepts = res.data || []
      }).catch(() => {})
    },
    handleAdd () {
      this.dialogTitle = '新增机构'
      this.formData = { id: null, name: '', parentId: 0, orderNum: 0 }
      this.dialogVisible = true
      this.$nextTick(() => {
        this.$refs.deptForm && this.$refs.deptForm.clearValidate()
      })
    },
    handleEdit (row) {
      this.dialogTitle = '编辑机构'
      this.formData = {
        id: row.id,
        name: row.name,
        parentId: row.parentId || 0,
        orderNum: row.orderNum || 0
      }
      this.dialogVisible = true
      this.$nextTick(() => {
        this.$refs.deptForm && this.$refs.deptForm.clearValidate()
      })
    },
    submitForm () {
      this.$refs.deptForm.validate(valid => {
        if (!valid) return
        this.submitLoading = true
        this.$api.dept.save(this.formData).then(() => {
          this.$message.success(this.formData.id ? '修改成功' : '新增成功')
          this.dialogVisible = false
          this.findPage()
          this.loadAllDepts()
        }).catch(() => {}).finally(() => {
          this.submitLoading = false
        })
      })
    },
    handleDelete (row) {
      this.$confirm('确认删除机构 ' + row.name + '?', '提示', {
        type: 'warning'
      }).then(() => {
        this.$api.dept.batchDelete({ ids: row.id }).then(() => {
          this.$message.success('删除成功')
          this.findPage()
          this.loadAllDepts()
        }).catch(() => {})
      }).catch(() => {})
    },
    handleBatchDelete (rows) {
      let names = rows.map(function (row) { return row.name }).join('、')
      this.$confirm('确认批量删除机构: ' + names + '?', '提示', {
        type: 'warning'
      }).then(() => {
        let ids = rows.map(function (row) { return row.id })
        this.$api.dept.batchDelete({ ids: ids.join(',') }).then(() => {
          this.$message.success('批量删除成功')
          this.findPage()
          this.loadAllDepts()
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
.dept-container {
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
