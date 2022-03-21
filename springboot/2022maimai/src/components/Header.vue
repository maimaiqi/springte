<template>
  <div style="font-size: 12px;line-height:60px;display:flex">
    <div style="flex:1;font-size:18px">
      <span :class="collapseBtnClass" style="cursor:pointer" @click="collapse"></span>

      <el-breadcrumb separator="/" style="margin:20px 0 0 20px;display: inline-block">
        <el-breadcrumb-item :to="'/'">首页</el-breadcrumb-item>
        <el-breadcrumb-item>{{ currentPathName }}</el-breadcrumb-item>
      </el-breadcrumb>
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
  // props: {
  //   collapseBtnClass: String,
  //   Collapse:Boolean
  //   //isCollapse: Boolean
  //   //collapse:Boolean
  // },
  props: {
    collapseBtnClass: String,
    isCollapse: Boolean,
    sideWidth:String,
    logoTextShow:Boolean
    //pathName:String,
  },
  computed:{
    currentPathName(){
      //return this.$store.state.currentPathName(); //需要监听的数据
      return this.store.state.currentPathName();
    }
  },
  watch:{ //百度搜索的，监听路由变化
    '$route':function (){
      this.currentPathName = localStorage.getItem("currentPathName") //取出route里面设置的当前路由信息
    }
  },
  data() {
    return {
      collapseTmp: this.isCollapse,
      btnClassTmp: this.collapseBtnClass,
      sideWidthTmp: this.sideWidth,
      currentPathName: this.currentPathName,
      logoTextShowTmp: this.logoTextShow
      //currentPathName:localStorage.getItem("currentPathName")
    }
  },
  methods:{
    collapse(){ //点击收缩按钮触发
      this.collapseTmp = !this.collapseTmp
      if (this.collapseTmp){//收缩
        this.sideWidthTmp = 64
        this.btnClassTmp='el-icon-s-unfold'
        this.logoTextShowTmp = false
      }else{//展开
        this.sideWidthTmp = 200
        this.btnClassTmp = 'el-icon-s-fold'
        this.logoTextShowTmp = true
      }

      this.$emit('statusChange', this.collapseTmp, this.btnClassTmp,this.sideWidthTmp,this.logoTextShowTmp)
    }

  }
}
</script>

<style scoped>

</style>