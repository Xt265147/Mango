<template>
  <div>
    <!-- 工具栏：批量删除 -->
    <div class="table-toolbar" v-if="showBatchDelete">
      <kt-button label="批量删除" icon="fa fa-trash" type="danger" size="small"
                 :perms="permsBatchDelete" @click="handleBatchDelete"/>
    </div>
    <!-- 数据表格 -->
    <el-table :data="data.rows" style="width: 100%" stripe border size="small"
              v-loading="loading" element-loading-text="加载中..."
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="50"></el-table-column>
      <el-table-column v-for="col in columns" :key="col.prop"
                       :prop="col.prop" :label="col.label" :width="col.width"
                       :align="col.align || 'left'">
        <template slot-scope="scope">
          <slot :name="col.prop" :row="scope.row">
            {{ scope.row[col.prop] }}
          </slot>
        </template>
      </el-table-column>
      <el-table-column label="操作" :width="operationWidth" align="center" v-if="showOperation">
        <template slot-scope="scope">
          <slot name="operation" :row="scope.row">
            <kt-button label="编辑" icon="fa fa-edit" size="mini" :perms="permsEdit"
                        @click="handleEdit(scope.row)"/>
            <kt-button label="删除" icon="fa fa-trash" type="danger" size="mini" :perms="permsDelete"
                        @click="handleDelete(scope.row)"/>
          </slot>
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
        :total="data.total"
        @size-change="handleSizeChange"
        @current-change="handlePageChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import KtButton from '@/views/core/KtButton.vue'

export default {
  name: 'KtTable',
  components: { KtButton },
  props: {
    data: {
      type: Object,
      default: function () { return { total: 0, rows: [] } }
    },
    columns: {
      type: Array,
      default: function () { return [] }
    },
    loading: {
      type: Boolean,
      default: false
    },
    pageNum: {
      type: Number,
      default: 1
    },
    pageSize: {
      type: Number,
      default: 10
    },
    showOperation: {
      type: Boolean,
      default: true
    },
    showBatchDelete: {
      type: Boolean,
      default: true
    },
    operationWidth: {
      type: String,
      default: '200'
    },
    permsEdit: {
      type: String,
      default: null
    },
    permsDelete: {
      type: String,
      default: null
    },
    permsBatchDelete: {
      type: String,
      default: null
    }
  },
  data () {
    return {
      selections: []
    }
  },
  methods: {
    handleSelectionChange (val) {
      this.selections = val
    },
    handleBatchDelete () {
      if (this.selections.length === 0) {
        this.$message.warning('请选择要删除的记录')
        return
      }
      this.$emit('handleBatchDelete', this.selections)
    },
    handleEdit (row) {
      this.$emit('handleEdit', row)
    },
    handleDelete (row) {
      this.$emit('handleDelete', row)
    },
    handleSizeChange (val) {
      this.$emit('sizeChange', val)
    },
    handlePageChange (val) {
      this.$emit('pageChange', val)
    }
  }
}
</script>

<style scoped>
.table-toolbar {
  margin-bottom: 10px;
}
.pagination {
  margin-top: 15px;
  text-align: right;
  padding: 10px 0;
}
.el-table {
  box-shadow:
    6px 6px 14px var(--neu-shadow-dark),
    -6px -6px 14px var(--neu-shadow-light);
  border-radius: var(--neu-radius);
}
</style>
