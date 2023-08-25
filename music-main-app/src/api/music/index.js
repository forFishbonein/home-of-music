import request from "@/request";

export function getMusics(query, page) {
  return request({
    url: "/musics",
    method: "post",
    data: {
      page: page.pageNumber,
      pageSize: page.pageSize,
    },
  });
}
