const conf = {
  serverDomain: 'https://www.micropace.top'
}

export default class Iss {
    // 发起请求
    request(url, data, resolve, reject, method) {

        let that = this;
        wx.request({
            url: that.getServerUrl(url),
            data: data,
            method: method || 'GET',
            header: {
                'Content-Type': 'application/json'
            },
            success: function (res) {
                typeof resolve === 'function' && resolve(res.data);
            },
            fail: function (err) {
                typeof reject === 'function' && reject(err.errMsg);
            }
        });
    }
    redirect(url) {
        wx.redirectTo({
            url: url
        });
    }
   open(url){
       wx.navigateTo({
           url: url
       });
   }
    serverDomain(){
       return conf.serverDomain;
   }
  // 获取接口域名
  getServerUrl(url) {
      return conf.serverDomain + url;
  }
    //手机号提交
    registerIphone(param,resolve, reject) {
        this.request('/api/register',param, function (res) {
            typeof resolve === 'function' && resolve(res);
        }, function (err) {
            typeof reject === 'function' && reject(err);
        },"POST");
    }
  //获取id
  getOpenId(param,resolve, reject) {
    this.request('/api/user/login',param, function (res) {
      typeof resolve === 'function' && resolve(res);
    }, function (err) {
      typeof reject === 'function' && reject(err);
    },"POST");
  }
  //获取首页内容
    getHomepage(param,resolve, reject) {
        this.request('/api/homepage?'+param,{}, function (res) {
            typeof resolve === 'function' && resolve(res);
        }, function (err) {

            typeof reject === 'function' && reject(err);
        });
    }
    //获取题目
    getQuestions(param,resolve, reject) {
        this.request('/api/questions?'+param,{}, function (res) {
            typeof resolve === 'function' && resolve(res);
        }, function (err) {

            typeof reject === 'function' && reject(err);
        });
    }
    //提交答案
    toAnswers(param,resolve, reject) {
        this.request('/api/answers',param, function (res) {
            typeof resolve === 'function' && resolve(res);
        }, function (err) {

            typeof reject === 'function' && reject(err);
        },"POST");
    }
    //结束页
    getEndpage(param,resolve, reject) {
        this.request('/api/endpage?'+param,{}, function (res) {
            typeof resolve === 'function' && resolve(res);
        }, function (err) {

            typeof reject === 'function' && reject(err);
        });
    }
  // toast 提示
  toast(msg) {
    wx.showToast({
      title: msg,
      duration: 2000
    });
  }
}
