import Vue from "vue";
import App from "./App.vue";

import lodash from "lodash";

import router from "./router";
import store from "./store";
import  "./plugins/element.js";
import  "./plugins/axios";

import "@/assets/theme/index.css";
import "@/assets/icon/iconfont.css";

Vue.config.productionTip = false;

Object.defineProperty(Vue.prototype, "$_", { value: lodash });

Vue.directive("title", function(el, binding) {
  document.title = el.dataset.title;
});

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
