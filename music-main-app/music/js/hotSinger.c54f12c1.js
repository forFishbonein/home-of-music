"use strict";(self["webpackChunkvue_test"]=self["webpackChunkvue_test"]||[]).push([[35],{5925:function(t,e,s){s.r(e),s.d(e,{default:function(){return d}});var r=function(){var t=this,e=t._self._c;return e("div",{directives:[{name:"title",rawName:"v-title"}],attrs:{"data-title":"音乐之家"}},[e("el-container",[e("el-main",{staticClass:"me-articles"},t._l(t.hotSingers,(function(s){return e("el-card",{key:s,staticClass:"box-card"},[e("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[e("span",[t._v(t._s(s.singerName))])]),t._l(s.singName,(function(s){return e("div",{key:s,staticClass:"text item"},[t._v(" "+t._s(s)+" ")])}))],2)})),1)],1)],1)},a=[],n=s(7344);function i(){return(0,n.Z)({url:"/singers",method:"post"})}var l={name:"HotSinger",data(){return{hotSingers:[]}},created(){this.getHotSinger()},methods:{getHotSinger(){let t=this;i().then((e=>{t.hotSingers=e.data})).catch((e=>{"error"!==e&&t.$message({type:"error",message:"最热歌手加载失败!",showClose:!0})}))}}},o=l,c=s(1001),u=(0,c.Z)(o,r,a,!1,null,null,null),d=u.exports}}]);
//# sourceMappingURL=hotSinger.c54f12c1.js.map