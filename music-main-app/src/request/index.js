import axios from "axios";
import { Message } from "element-ui";
import store from "@/store";
import { getToken } from "@/request/token";

const service = axios.create({
  baseURL: "http://124.220.15.95:8888",
  timeout: 10000,
});

//request拦截器
service.interceptors.request.use(
  (config) => {
    if (store.state.token) {
      config.headers["Authorization"] = getToken(); //这里必须是Authorization！——> 对应后端的拦截器！
    }
    return config;
  },
  (error) => {
    Promise.reject(error);
  }
);

// respone拦截器
service.interceptors.response.use(
  (response) => {
    //全局统一处理 Session超时
    if (response.headers["session_time_out"] == "timeout") {
      store.dispatch("fedLogOut");
    }

    const res = response.data;

    //200 为成功状态
    if (res.code !== 200) {
      //90001 Session超时
      if (res.code === 90001) {
        return Promise.reject("error");
      }

      //90002 用户未登录
      if (res.code === 90002) {
        Message({
          type: "warning",
          showClose: true,
          message: "未登录或登录超时，请重新登录哦~",
        });

        return Promise.reject("error");
      }

      return Promise.reject(res.msg);
    } else {
      return response.data;
    }
  },
  (error) => {
    Message({
      type: "warning",
      showClose: true,
      message: "连接超时",
    });
    return Promise.reject("error");
  }
);

export default service;
