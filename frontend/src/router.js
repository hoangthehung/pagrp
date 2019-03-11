import Vue from 'vue'
import Router from 'vue-router'
import Upload from './views/Upload'
import Search from "./views/Search";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Upload',
      component: Upload
    },
    {
      path: '/search',
      name: 'Search',
      component: Search
    }
  ]
})
