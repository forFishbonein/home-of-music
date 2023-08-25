<template>
  <div v-title data-title="音乐之家">
    <el-container>

      <el-main class="me-articles">

        <music-scroll-page></music-scroll-page>

      </el-main>

    </el-container>
  </div>
</template>

<script>
  import MusicScrollPage from '@/components/MusicScrollPage'
  export default {
    // eslint-disable-next-line vue/multi-word-component-names
    name: 'Index',
    components: {
      'music-scroll-page' : MusicScrollPage,
    }
  }
</script>

<style scoped>

  .el-container {
    width: 960px;
  }

  .el-aside {
    margin-left: 20px;
    width: 260px;
  }

  .el-main {
    padding: 0px;
    line-height: 16px;
  }

  .el-card {
    border-radius: 0;
  }

  .el-card:not(:first-child) {
    margin-top: 20px;
  }

</style>
