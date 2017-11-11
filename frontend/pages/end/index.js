// pages/end.js
const app = getApp(),
    _iss = app.globalData._iss;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    text:'感谢您的参与！'
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      var that =this;
    _iss.getEndpage("scene="+options.scene+"&&openid="+options.openid,res =>{

        wx.setNavigationBarTitle({
           title: res.data.barTitleText
        })
        that.setData({
            endPageDiscription:res.data.endPageDiscription,
            endPagePicName:_iss.serverDomain()+res.data.endPagePicName
        })

      },err =>{
          _iss.toast("获取内容失败");
      })

  },
  onShareAppMessage: function () {
  
  }
})