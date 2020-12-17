module.exports = {
  devServer: {
    host: 'localhost',
    port: 8081,
    https: false,
    open: true,
    hotOnly: true,
  },
  outputDir: './dist',
  assetsDir: './assets/',
  publicPath: './',
  indexPath: './index.html',
  transpileDependencies: ['vuetify']
}

Vue.prototype.$layer = layer(Vue, {
  msgtime: 3,//目前只有一项，即msg方法的默认消失时间，单位：秒
});
