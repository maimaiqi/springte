<template>
  <div style="height:100%">
    <el-container style="height:100%;">
      <el-aside :width="sideWidth+'px'" style="background-color: rgb(238, 241, 246);height:auto;box-shadow:rgba(0,21,41,0.35) 2px 0px 6px">
        <el-menu :default-openeds="['1', '3']" style="min-height:100%; overflow-x:hidden"
                  background-color="rgb(48,65,86)" text-color="#fff"
                  active-text-color="#ffd04b"
                  :collapse-transition='false'
                  :collapse="isCollapse"   
        >
          <div style="height:60px;line-height:60px;text-align:center;">
            <img src='../assets/logo.png' alt='' style="width:20px; position:relative; top:3px;right:2px">
            <b style='color:white' v-show="logoTextShow">后台管理系统</b>
          </div>
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-message"></i>
              <span slot="title">导航一</span>
            </template>
            <el-menu-item-group>
              <template slot="title">
                <i class="el-icon-message"></i>
                <span slot="title">导航一</span>
              </template>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title">选项4</template>
              <el-menu-item index="1-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span slot="title">导航二</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="2-1">选项1</el-menu-item>
              <el-menu-item index="2-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="2-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="2-4">
              <template slot="title">选项4</template>
              <el-menu-item index="2-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-setting"></i>
              <span slot="title">导航三</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="3-1">选项1</el-menu-item>
              <el-menu-item index="3-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="3-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="3-4">
              <template slot="title">选项4</template>
              <el-menu-item index="3-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
        </el-menu>
      </el-aside>
      
      <el-container>
        <el-breadcrumb separator="/" style="margin:20px 0 0 20px">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item><a href="/">用户管理</a></el-breadcrumb-item>
        </el-breadcrumb>
        <el-header style="font-size: 12px;border-bottom:1px solid #ccc;line-height:60px;display:flex">
          <div style="flex:1;font=size:18px">
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
        </el-header>
        
        <el-main>
          <!--搜索框-->
          <div style="padding:10px 0">
            <el-input style="width:200px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="username"></el-input>
            <el-input style="width:200px" placeholder="请输入邮箱" suffix-icon="el-icon-message" v-model="email"></el-input>
            <el-input style="width:200px" placeholder="请输入地址" suffix-icon="el-icon-position" v-model="address"></el-input>
            <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
          </div>
          <div style="margin:10px 0">
            <el-button type="primary">新增<i class="el-icon-circle-plus-outline"></i></el-button>
            <el-button type="danger">批量删除<i class="el-icon-remove-outline"></i></el-button>
            <el-button type="primary">导入<i class="el-icon-upload2"></i></el-button>
            <el-button type="primary">导出<i class="el-icon-download"></i></el-button>
          </div>

          <el-table :data="tableData" border stripe :header-cell-class-name="headerBg">
            <el-table-column prop="id" label="ID" width="80"></el-table-column>
            <el-table-column prop="username" label="用户名" width="140"></el-table-column>
            <el-table-column prop="nickname" label="昵称" width="120"></el-table-column>
            <el-table-column prop="email" label="邮箱"></el-table-column>
            <el-table-column prop="phone" label="电话"></el-table-column>
            <el-table-column prop="address" label="地址"></el-table-column>
            <el-table-column label="操作">
              <template scope="scope">
                <el-button type="success">编辑 <i class="el-icon-edit"></i></el-button>
                <el-button type="danger">删除 <i class="el-icon-delete"></i></el-button>
              </template>  
            </el-table-column>  
          </el-table>
          <div style="padding:10px 0">
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="pageNum"
              :page-sizes="[3, 5, 10, 20]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total">
            </el-pagination>
          </div>
        </el-main>

      </el-container>
    </el-container>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  data(){
    return {
      tableData: [],
      total:0,
      pageNum:1,
      pageSize:3,
      collapseBtnClass:'el-icon-s-fold',
      isCollapse:false,
      sideWidth:200,
      logoTextShow:true,
      headerBg:'headerBg',
      username:"",
      email:"",
      address:""
    }
  },
  created(){
    //请求分页查询数据
    this.load()
  },
  methods:{
    collapse(){ //点击收缩按钮触发
      this.isCollapse = !this.isCollapse
      if (this.isCollapse){//收缩
        this.sideWidth = 64
        this.collapseBtnClass='el-icon-s-unfold'
        this.logoTextShow = false
      }else{//展开
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }

    },
    load(){
      fetch("http://localhost:9090/user/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize
          +"&username="+this.username+"&email="+this.email+"&address="+this.address)
          //+"&email="+this.email+"&address="+this.address
          .then(res => res.json()).then(res =>{
        console.log(res)
        this.tableData = res.data
        this.total = res.total
      })
    },
    handleSizeChange(pageSize){
      this.pageSize = pageSize
      console.log(pageSize)
      this.load()
    },
    handleCurrentChange(pageNum){
      this.pageNum = pageNum
      console.log(pageNum)
      this.load()
    }
  }
}
</script>
<style>
.headerBg {
  background: #eee!important;
}

</style>