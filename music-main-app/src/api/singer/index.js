import request from "@/request";

export function getHotSinger() {
  return request({
    url: "/singers",
    method: "post",
  });
}
