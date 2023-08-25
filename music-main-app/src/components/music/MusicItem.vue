<template>
  <el-card class="me-area" :body-style="{ padding: '16px' }">
    <div class="me-article-header">
        <!-- <a @click="download(singFileName)" class="me-article-title">{{title}}</a> -->
        <a :href="singFileName" class="me-article-title">{{title}}</a>
        <el-button v-if="weight > 0" class="me-article-icon" type="text">置顶</el-button>
        <span class="me-pull-right me-article-count">
            <i class="el-icon-view"></i>&nbsp;{{loadCounts}}
        </span>
    </div>

    <div class="me-article-footer">
        <span class="me-article-author">
            <i class="me-icon-author"></i>&nbsp;{{singer}}
        </span>
        <span class="me-pull-right me-article-count">
            <i class="el-icon-time"></i>&nbsp;{{createDate}}
        </span>
    </div>
  </el-card>
</template>

<script>
  // import {downloadMusic} from '@/api/download'
  export default {
    name: 'MusicItem',
    props: {
      id: String,
      weight: Number,
      title: String,
      loadCounts: Number,
      singer: String,
      createDate: String
    },
    data() {
      return{

      }
    },
    computed: { //和data还有props是同级别的！
      singFileName (){
        return 'http://124.220.15.95:8888/download/'+ this.id +'.mp3'
      }
    }
    // methods: {
    //   download(singFileName) {
    //     // this.$router.push({path: `/view/${id}`})
    //     let that = this
    //     downloadMusic(singFileName).then(res => {

    //     }).catch(error => {
    //       if (error !== 'error') {
    //         that.$message({type: 'error', message: '下载失败！', showClose: true})
    //       }
    //     })
    //   }
    // }
  }
</script>

<style scoped>

  .me-article-header {
    /*padding: 10px 18px;*/
    padding-bottom: 10px;
  }

  .me-article-title {
    font-weight: 600;
    color:#232b25;

  }
  .me-article-title:hover{
    color:#5FB878;
  }

  .me-article-icon {
    padding: 3px 8px;
  }

  .me-article-count {
    color: #a6a6a6;
    padding-left: 14px;
    font-size: 13px;
  }

  .me-pull-right {
    float: right;

  }

  .me-artile-description {
    font-size: 13px;
    line-height: 24px;
    margin-bottom: 10px;
  }

  .me-article-author {
    color: #a6a6a6;
    padding-right: 18px;
    font-size: 13px;
  }

  .el-tag {
    margin-left: 6px;
  }
</style>
