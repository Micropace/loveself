//index.js
//获取应用实例
const app = getApp(),
  _iss = app.globalData._iss;

Page({
  data: {
    bannerPicName:'',
    nMark:true,
    homePageDiscription:'',
    rMark:true
  },
  enterPhone(e){
    var value = e.detail.value,
        that =this;
    var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
    if (myreg.test(value)) {
       that.setData({
         PhoneNumber: value,
         nMark:false
       })
    }else{
      that.setData({
        nMark: true
      })
    }
  },
  submit(){
    if(!this.data.rMark){
        return;
    }
    _iss.registerIphone({openid:this.data.openId,mobile:this.data.PhoneNumber},res =>{
        this.setData({
            rMark: false
        })
      _iss.open("/pages/subject/index?openId="+this.data.openId+"&&scene="+this.data.scene);

    },err =>{
          this.setData({
              rMark: true
          })
          _iss.toast("手机号提交失败");
    })

   
  },
  onLoad: function (options) {

      var scene = options.scene?decodeURIComponent(options.scene):10001,
          that =this;
      wx.login({
          success:function(res){
              _iss.getOpenId({code:res.code},res =>{
                   that.setData({
                        openId: res.data.openid,
                        scene:scene
                    })
                    let url = "scene="+scene+"&openid="+res.data.openid;
                    _iss.getHomepage(url,res =>{
                        wx.setNavigationBarTitle({
                            title: res.data.barTitleText
                        })
                        that.setData({
                          homePageDiscription:res.data.homePageDiscription,
                          bannerPicName:_iss.serverDomain()+res.data.bannerPicName
                        })

                      },err =>{
                        _iss.toast("首页获取内容失败");
                      })
              },err =>{
                  _iss.toast("获取code失败");
              })


          },
          fail:function(err){
            _iss.toast("获取openId失败");
          }
      })
     //

  },
  getUserInfo: function(e) {

  }
})
