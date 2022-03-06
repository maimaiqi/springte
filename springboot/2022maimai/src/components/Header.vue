<template>
  <div style="font-size: 12px;line-height:60px;display:flex">
    <div style="flex:1;font-size:18px">
      <span :class="collapseBtnClass" style="cursor:pointer" @click="collapse"></span>
    </div>
    <el-dropdown style="width:70px;cursor:pointer;">
      <span>张买买
      <i class="el-icon-arrow-down" style="margin-left:5px"></i>
      </span>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item>个人信息</el-dropdown-item>
        <el-dropdown-item>退出</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "Header",
  props: {
    collapseBtnClass: String,
    isCollapse: Boolean
    //collapse:Boolean
  },
  data() {
    return {
      collapseTmp: this.isCollapse,
      btnClassTmp: this.collapseBtnClass
    }
  },
  methods:{
    collapse(){ //点击收缩按钮触发
      this.collapseTmp = !this.collapseTmp
      if (this.isCollapse){//收缩
        this.sideWidth = 64
        this.btnClassTmp='el-icon-s-unfold'
        this.logoTextShow = false
      }else{//展开
        this.sideWidth = 200
        this.btnClassTmp = 'el-icon-s-fold'
        this.logoTextShow = true
      }

      this.$emit('statusChange', this.collapseTmp, this.btnClassTmp)
    }

  }
}
</script>

<style scoped>

</style>