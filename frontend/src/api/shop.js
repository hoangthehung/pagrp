import request from 'request'
const API_URL = 'http://localhost:8088';
export function search(query) {
  return request({
    url: `${API_URL}/shop/search`,
    method: 'get',
    params: query
  })
}

export function upload(data) {
  return request({
    url: `${API_URL}/shop/upload`,
    method: 'post',
      headers: {
          'Content-Type': 'multipart/form-data',
          'boundary': 'HereGoes'
      },
    body: data
  })
}

