const path = require('path')
const webpack = require('webpack')
const HtmlwebpackPlugin = require('html-webpack-plugin')

//定义了一些文件夹的路径
const ROOT_PATH = path.resolve(__dirname)
const APP_PATH = path.resolve(ROOT_PATH, 'app')
const PUBLIC_PATH = path.resolve(APP_PATH, 'public')
const BUILD_PATH = path.resolve(ROOT_PATH, 'src/main/resources/templates')

module.exports = {
    //项目的文件夹 可以直接用文件夹名称 默认会找index.js 也可以确定是哪个文件名字
    entry: {
        app: path.resolve(APP_PATH, 'src')
    },
    //输出的文件名 合并以后的js会命名为bundle.js
    output: {
        path: BUILD_PATH,
        filename: '[name].[hash].js'
    },
    devServer: {
        historyApiFallback: true,
        hot: true,
        inline: true,
        progress: true,
        port: 3000
    },
    module: {
        loaders: [
            {
                test: /\.jsx?$/,
                exclude: /node_modules/,
                loaders: 'babel-loader',
                query: {
                    presets: ['react', 'env', 'stage-0']
                }
            },
            {
                test: /\.s?css$/,
                loaders: ['style-loader', 'css-loader', 'sass-loader']
            },
            {
                test: /\.(png|jpg)$/,
                options: {
                    limit: 8192
                },
                loader: 'url-loader'
            }
        ]
    },
    plugins: [
        // 这个使用uglifyJs压缩你的js代码
        // new webpack.optimize.UglifyJsPlugin({
        //     minimize: true
        // }),
        //把入口文件里面的数组打包成verdors.js
        new webpack.optimize.CommonsChunkPlugin({
            name: 'vendors',
            filename: 'vendors.js'
        }),
        new HtmlwebpackPlugin({
            title: 'Hello World app',
            template: path.resolve(PUBLIC_PATH, 'index.html'),
            filename: 'index.html',
            // filename: 'templates/index.html',
            //chunks这个参数告诉插件要引用entry里面的哪几个入口
            chunks: ['app', 'vendors'],
            //要把script插入到标签里
            inject: 'body'
        })
    ]
};
