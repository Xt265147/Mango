<template>
  <div class="menu-container">
    <!-- 搜索栏 -->
    <el-form :inline="true" :model="filters" size="small" class="search-bar">
      <el-form-item label="菜单名称">
        <el-input v-model="filters.name" placeholder="请输入菜单名称" clearable
                  @keyup.enter.native="findPage"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="fa fa-search" @click="findPage">查 询</el-button>
      </el-form-item>
      <el-form-item>
        <kt-button label="新增" icon="fa fa-plus" perms="sys:menu:add" type="primary" @click="handleAdd"/>
      </el-form-item>
    </el-form>
    <!-- 数据表格 -->
    <kt-table :data="pageResult" :columns="columns" :loading="loading"
              :page-num="pageNum" :page-size="pageSize"
              perms-edit="sys:menu:edit" perms-delete="sys:menu:delete"
              perms-batch-delete="sys:menu:delete"
              @findPage="findPage" @handleEdit="handleEdit" @handleDelete="handleDelete"
              @handleBatchDelete="handleBatchDelete"
              @sizeChange="handleSizeChange" @pageChange="handlePageChange">
      <template slot="icon" slot-scope="scope">
        <i :class="scope.row.icon"></i>
      </template>
      <template slot="type" slot-scope="scope">
        <el-tag v-if="scope.row.type === 0" size="mini">目录</el-tag>
        <el-tag v-else-if="scope.row.type === 1" type="success" size="mini">菜单</el-tag>
        <el-tag v-else type="info" size="mini">按钮</el-tag>
      </template>
    </kt-table>
    <!-- 新增/编辑弹窗 -->
    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="550px" :close-on-click-modal="false">
      <el-form :model="formData" :rules="formRules" ref="menuForm" label-width="100px" size="small">
        <el-form-item label="菜单名称" prop="name">
          <el-input v-model="formData.name" placeholder="请输入菜单名称"></el-input>
        </el-form-item>
        <el-form-item label="上级菜单">
          <el-select v-model="formData.parentId" placeholder="无" clearable style="width: 100%">
            <el-option label="无" :value="0"></el-option>
            <el-option v-for="item in allMenus" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="菜单类型">
          <el-radio-group v-model="formData.type">
            <el-radio :label="0">目录</el-radio>
            <el-radio :label="1">菜单</el-radio>
            <el-radio :label="2">按钮</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="图标">
          <el-input v-model="formData.icon" placeholder="请输入图标类名"></el-input>
        </el-form-item>
        <el-form-item label="URL" v-if="formData.type !== 2">
          <el-input v-model="formData.url" placeholder="请输入URL路径"></el-input>
        </el-form-item>
        <el-form-item label="权限标识" v-if="formData.type === 2">
          <el-input v-model="formData.perms" placeholder="例如 sys:user:add"></el-input>
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
  name: 'SysMenu',
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
        { prop: 'name', label: '菜单名称', width: '180' },
        { prop: 'icon', label: '图标', width: '100', align: 'center' },
        { prop: 'type', label: '类型', width: '80', align: 'center' },
        { prop: 'orderNum', label: '排序', width: '80', align: 'center' },
        { prop: 'url', label: 'URL', width: '200' },
        { prop: 'perms', label: '权限标识', width: '180' }
      ],
      allMenus: [],
      dialogVisible: false,
      dialogTitle: '',
      submitLoading: false,
      formData: {
        id: null,
        name: '',
        parentId: 0,
        type: 0,
        icon: '',
        url: '',
        perms: '',
        orderNum: 0
      },
      formRules: {
        name: [{ required: true, message: '请输入菜单名称', trigger: 'blur' }]
      }
    }
  },
  mounted () {
    this.findPage()
    this.loadAllMenus()
  },
  methods: {
    findPage () {
      this.loading = true
      this.$api.menu.findPage({
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
    loadAllMenus () {
      this.$api.menu.findAll().then(res => {
        this.allMenus = res.data || []
      }).catch(() => {})
    },
    handleAdd () {
      this.dialogTitle = '新增菜单'
      this.formData = { id: null, name: '', parentId: 0, type: 0, icon: '', url: '', perms: '', orderNum: 0 }
      this.dialogVisible = true
      this.$nextTick(() => {
        this.$refs.menuForm && this.$refs.menuForm.clearValidate()
      })
    },
    handleEdit (row) {
      this.dialogTitle = '编辑菜单'
      this.formData = {
        id: row.id,
        name: row.name,
        parentId: row.parentId || 0,
        type: row.type || 0,
        icon: row.icon || '',
        url: row.url || '',
        perms: row.perms || '',
        orderNum: row.orderNum || 0
      }
      this.dialogVisible = true
      this.$nextTick(() => {
        this.$refs.menuForm && this.$refs.menuForm.clearValidate()
      })
    },
    submitForm () {
      this.$refs.menuForm.validate(valid => {
        if (!valid) return
        this.submitLoading = true
        this.$api.menu.save(this.formData).then(() => {
          this.$message.success(this.formData.id ? '修改成功' : '新增成功')
          this.dialogVisible = false
          this.findPage()
          this.loadAllMenus()
        }).catch(() => {}).finally(() => {
          this.submitLoading = false
        })
      })
    },
    handleDelete (row) {
      this.$confirm('确认删除菜单 ' + row.name + '?', '提示', {
        type: 'warning'
      }).then(() => {
        this.$api.menu.batchDelete({ ids: row.id }).then(() => {
          this.$message.success('删除成功')
          this.findPage()
          this.loadAllMenus()
        }).catch(() => {})
      }).catch(() => {})
    },
    handleBatchDelete (rows) {
      let names = rows.map(function (row) { return row.name }).join('、')
      this.$confirm('确认批量删除菜单: ' + names + '?', '提示', {
        type: 'warning'
      }).then(() => {
        let ids = rows.map(function (row) { return row.id })
        this.$api.menu.batchDelete({ ids: ids.join(',') }).then(() => {
          this.$message.success('批量删除成功')
          this.findPage()
          this.loadAllMenus()
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
.menu-container {
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
