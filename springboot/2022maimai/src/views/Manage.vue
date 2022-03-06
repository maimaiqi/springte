<template>
  <div style="height:100%">
    <el-container style="min-height:100%;">
      <el-aside :width="sideWidth+'px'" style="background-color: rgb(238, 241, 246);height:auto;box-shadow:rgba(0,21,41,0.35) 2px 0px 6px">
        <Aside :isCollapse="isCollapse" :logoTextShow="logoTextShow"/>
      </el-aside>
      
      <el-container>
        <el-breadcrumb separator="/" style="margin:20px 0 0 20px">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item><a href="/">用户管理</a></el-breadcrumb-item>
        </el-breadcrumb>
        <el-header style="border-bottom:1px solid #ccc;">
          <Header :collapseBtnClass="collapseBtnClass" @statusChange="aaa" />
        </el-header>
        <el-main>
          <!--表示当前页面的子路由会在router-view里面展示-->
          <router-view />
        </el-main>

      </el-container>
    </el-container>
  </div>
</template>

<script>
import Aside from "@/components/Aside"
import Header from "@/components/Header";
import User from "@/views/User";
// @ is an alias to /src
export default {
  data(){
    return {
      collapseBtnClass:'el-icon-s-fold',
      isCollapse:false,
      sideWidth:200,
      logoTextShow:true
    }
  },
  //引入组件
  components:{
    Header,
    Aside
  },
  methods:{
      aaa(collapse, currClass) {
        this.isCollapse = collapse
        this.collapseBtnClass = currClass
      }

    }

  }

</script>
