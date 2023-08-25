<template>
  <scroll-page :loading="loading" :offset="offset" :no-data="noData" v-on:load="load">
    <music-item v-for="a in musics" :key="a.id" v-bind="a"></music-item>
  </scroll-page>
</template>

<script>
  import MusicItem from '@/components/music/MusicItem'
  import ScrollPage from '@/components/music/ScrollPage'
  import {getMusics} from '@/api/music'

  export default {
    name: "MusicScrollPage",
    props: {
      offset: {
        type: Number,
        default: 100
      },
      page: {
        type: Object,
        default() {
          return {}
        }
      },
      query: {
        type: Object,
        default() {
          return {}
        }
      }
    },
    watch: {
      'query': {
        handler() {
          this.noData = false
          this.musics = []
          this.innerPage.pageNumber = 1
          this.getMusics()
        },
        deep: true
      },
      'page': {
        handler() {
          this.noData = false
          this.musics = []
          this.innerPage = this.page
          this.getMusics()
        },
        deep: true
      }
    },
    created() {
      this.getMusics()
    },
    data() {
      return {
        loading: false,
        noData: false,
        innerPage: {
          pageSize: 5,
          pageNumber: 1
        },
        musics: [

        ]
      }
    },
    methods: {
      load() {
        // alert("触发分页");
        this.getMusics()
      },
      // view(id) {
      //   this.$router.push({path: `/view/${id}`})
      // },
      getMusics() {
        let that = this
        that.loading = true
          // that.musics = that.musics.concat([
          // ]);
          // that.noData = false
          // that.loading = false
        getMusics(that.query, that.innerPage).then(res => { //把分页信息和查询信息传入

          let newMusics = res.data
          if (newMusics && newMusics.length > 0) {
            that.innerPage.pageNumber += 1
            that.musics = that.musics.concat(newMusics)
          } else {
            that.noData = true
          }

        }).catch(error => {
          if (error !== 'error') {
            that.$message({type: 'error', message: '歌曲加载失败!', showClose: true})
          }
        }).finally(() => {
          that.loading = false
        })
      }
    },
    components: {
      'music-item': MusicItem,
      'scroll-page': ScrollPage
    }

  }
</script>

<style scoped>
  .el-card {
    border-radius: 0;
  }

  .el-card:not(:first-child) {
    margin-top: 10px;

  }
</style>
