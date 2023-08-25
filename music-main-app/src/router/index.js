import Vue from "vue";
import VueRouter from "vue-router";
import Home from "@/Home";

Vue.use(VueRouter);

import { Message } from "element-ui";

import store from "@/store";

import { getToken } from "@/request/token";

const routes = [
  {
    path: "",
    name: "Home",
    component: Home,
    children: [
      {
        path: "/",
        component: (r) =>
          require.ensure([], () => r(require("@/views/Index")), "index"),
      },
      {
        path: "/singer",
        component: (r) =>
          require.ensure(
            [],
            () => r(require("@/components/HotSinger")),
            "hotSinger"
          ),
      },
    ],
  },
  {
    path: "/login",
    component: (r) =>
      require.ensure([], () => r(require("@/views/Login")), "login"),
  },
  {
    path: "/register",
    component: (r) =>
      require.ensure([], () => r(require("@/views/Register")), "register"),
  },
];

const router = new VueRouter({
  routes,
});

// router.beforeEach((to, from, next) => {
//   if (getToken()) {
//     if (to.path === "/login") {
//       next({ path: "/" });
//     } else { //如果不是跳转到登录页面！那么获取用户信息！
//       if (store.state.account.length === 0) { //如果还没有用户信息
//         store
//           .dispatch("getUserInfo") //这里调用了获取用户信息
//           .then((data) => {
//             //获取用户信息
//             next();
//           })
//           .catch(() => {
//             Message({
//               type: "warning",
//               showClose: true,
//               message: "登录已过期",
//             });
//             next({ path: "/" });
//           });
//       } else {
//         next();
//       }
//     }
//   } else {
//     if (to.matched.some((r) => r.meta.requireLogin)) {
//       Message({
//         type: "warning",
//         showClose: true,
//         message: "请先登录哦",
//       });
//     } else {
//       next();
//     }
//   }
// });

export default router;
