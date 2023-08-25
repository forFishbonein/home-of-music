<template>
    <div v-title data-title="音乐之家">
    <el-container>
      <el-main class="me-articles">
        <el-card v-for="singer in hotSingers" :key="singer" class="box-card">
          <div slot="header" class="clearfix">
            <span>{{singer.singerName}}</span>
          </div>
            <div v-for="m in singer.singName" :key="m" class="text item">
            {{ m }}
          </div>
        </el-card>

      </el-main>

    </el-container>
  </div>
</template>

<script>
import {getHotSinger} from '@/api/singer'

export default {
    name:'HotSinger',
    data() {
      return {
        hotSingers:[
          // {
          //   singerId: String,
          //   singerName: String,
          //   singName:[]
          // }
        ]
      }
    },
    created() {
      this.getHotSinger()
    },
    methods: {
      getHotSinger() {
        let that = this
        getHotSinger().then(res => {
          that.hotSingers = res.data
        }).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '最热歌手加载失败!', showClose: true})
          }
        })
      }
    }
}

</script>
<style>
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 480px;
    margin-bottom: 20px;
  }
</style>