const   app     = getApp(),
    _iss    = app.globalData._iss;


// pages/subject/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    bText:'下一题',
    bannerText:'标题',
    bannerPicName:'',
    num:0,
    results:[],

    questions:[]

      
    
  },
  submit: function(){
    let length = this.data.questions.length,
        num = this.data.num+2,
        ans = [],
        mark=false;
    this.data.questions[this.data.num].options.forEach(function(v,k){
      if(v.sel){
        mark =true;
        ans.push(v.index)
       }
    })
    if (!mark){
      _iss.toast("请选择题目!");
      return;
      }
      this.data.questions[this.data.num].options.forEach(function(v,k){
          if(v.sel){

          }
      })
      this.data.results[this.data.num]={index:this.data.num+1,answers:ans};

      this.setData({
          results:this.data.results

      })



    if (length==num){
      this.setData({
        bText: "完成",
        num: ++this.data.num

      })
      return;
    }
    if (length < num){
        _iss.toAnswers({scene:this.data.scene,openid:this.data.openId,results:JSON.stringify(this.data.results)},res =>{
            _iss.redirect("/pages/end/index?scene="+this.data.scene+"&openid="+this.data.openId);
        },err=>{
            _iss.toast("提交失败");
        });


      return;
    }
    this.setData({
      num:++this.data.num
    })
  },
  onLoad: function (options) {
       this.setData({
           openId:options.openId,
           scene:options.scene
       })

    _iss.getQuestions("openid="+options.openId+"&&scene="+options.scene,res =>{
        wx.setNavigationBarTitle({
            title: res.data.barTitleText
        });

          wx.setNavigationBarColor({
              frontColor:'#000000',
              backgroundColor: res.data.backgroundColor
          })

        this.setData({
            questions:res.data.questions,
            backgroundColor:res.data.backgroundColor,
            bannerPicName:_iss.serverDomain()+res.data.bannerPicName,
            questionActiveColor:res.data.questionActiveColor,
            questionOptionColor:res.data.questionOptionColor
        })

    },err =>{
          _iss.toast("获取题目失败");
      })




  },
  select(e){
    let index = e.currentTarget.dataset.index;
    var data =this.data.questions,
      op = data[this.data.num].options;
    if(data[this.data.num].type=='single_select'){
        op.forEach(function(v,k){
            v.sel =false;
        });
        this.data.questions[this.data.num].options[index].sel=true;
        this.setData({
            questions: data
        })
    }else{
        this.data.questions[this.data.num].options[index].sel=!this.data.questions[this.data.num].options[index].sel;
        this.setData({
            questions: data
        })
    }


  },
  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  }
})