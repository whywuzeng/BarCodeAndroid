// { "framework": "Vue"} 

!function(t){function e(i){if(o[i])return o[i].exports;var n=o[i]={i:i,l:!1,exports:{}};return t[i].call(n.exports,n,n.exports,e),n.l=!0,n.exports}var o={};e.m=t,e.c=o,e.d=function(t,o,i){e.o(t,o)||Object.defineProperty(t,o,{configurable:!1,enumerable:!0,get:i})},e.n=function(t){var o=t&&t.__esModule?function(){return t.default}:function(){return t};return e.d(o,"a",o),o},e.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},e.p="",e(e.s=646)}({1:function(t,e,o){"use strict";function i(t){return t&&t.__esModule?t:{default:t}}Object.defineProperty(e,"__esModule",{value:!0});var n=o(2);Object.defineProperty(e,"default",{enumerable:!0,get:function(){return i(n).default}})},2:function(t,e,o){var i,n,r=[];r.push(o(3)),i=o(4);var s=o(5);n=i=i||{},"object"!=typeof i.default&&"function"!=typeof i.default||(Object.keys(i).some(function(t){return"default"!==t&&"__esModule"!==t})&&console.error("named exports are not supported in *.vue files."),n=i=i.default),"function"==typeof n&&(n=n.options),n.__file="F:\\webwork\\BarCode.Mobile\\APAC_ABI_Barcode_Enhancement\\node_modules\\weex-ui\\packages\\wxc-minibar\\index.vue",n.render=s.render,n.staticRenderFns=s.staticRenderFns,n._scopeId="data-v-ac2fb226",n.style=n.style||{},r.forEach(function(t){for(var e in t)n.style[e]=t[e]}),"function"==typeof __register_static_styles__&&__register_static_styles__(n._scopeId,r),t.exports=i},3:function(t,e){t.exports={"wxc-minibar":{width:"750",height:"90",flexDirection:"row",justifyContent:"space-between",alignItems:"center",backgroundColor:"#009ff0"},left:{width:"180",paddingLeft:"32"},"middle-title":{fontSize:"30",color:"#ffffff",height:"36",lineHeight:"34"},right:{width:"180",paddingRight:"32",alignItems:"flex-end"},"left-button":{width:"21",height:"36"},"right-button":{width:"32",height:"32"},"icon-text":{fontSize:"28",color:"#ffffff"}}},4:function(t,e,o){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=Object.assign||function(t){for(var e=1;e<arguments.length;e++){var o=arguments[e];for(var i in o)Object.prototype.hasOwnProperty.call(o,i)&&(t[i]=o[i])}return t},n=weex.requireModule("navigator");e.default={props:{backgroundColor:{type:String,default:"#FFC900"},leftButton:{type:String,default:"https://gw.alicdn.com/tfs/TB1x18VpwMPMeJjy1XdXXasrXXa-21-36.png"},textColor:{type:String,default:"#3D3D3D"},rightButton:{type:String,default:""},title:{type:String,default:"标题"},leftText:{type:String,default:""},rightText:{type:String,default:""},useDefaultReturn:{type:Boolean,default:!0},show:{type:Boolean,default:!0},barStyle:{type:Object}},computed:{newBarStyle:function(){var t=this.backgroundColor,e=this.barStyle;return i({backgroundColor:t},e)}},methods:{leftButtonClicked:function(){this.useDefaultReturn&&n.pop({},function(t){}),this.$emit("wxcMinibarLeftButtonClicked",{})},rightButtonClicked:function(){(this.rightText||this.rightButton||this.$slots&&this.$slots.right)&&this.$emit("wxcMinibarRightButtonClicked",{})}}}},5:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,o=t._self._c||e;return t.show?o("div",{staticClass:["wxc-minibar"],style:t.newBarStyle},[o("div",{staticClass:["left"],attrs:{ariaLabel:"返回",accessible:!0},on:{click:t.leftButtonClicked}},[t._t("left",[t.leftButton&&!t.leftText?o("image",{staticClass:["left-button"],attrs:{src:t.leftButton}}):t._e(),t.leftText?o("text",{staticClass:["icon-text"],style:{color:t.textColor}},[t._v(t._s(t.leftText))]):t._e()])],2),t._t("middle",[o("text",{staticClass:["middle-title"],style:{color:t.textColor}},[t._v(t._s(t.title))])]),o("div",{staticClass:["right"],on:{click:t.rightButtonClicked}},[t._t("right",[t.rightButton&&!t.rightText?o("image",{staticClass:["right-button"],attrs:{src:t.rightButton,ariaHidden:!0}}):t._e(),t.rightText?o("text",{staticClass:["icon-text"],style:{color:t.textColor}},[t._v(t._s(t.rightText))]):t._e()])],2)],2):t._e()},staticRenderFns:[]},t.exports.render._withStripped=!0},646:function(t,e,o){var i,n,r=[];r.push(o(647)),r.push(o(648)),i=o(649);var s=o(650);n=i=i||{},"object"!=typeof i.default&&"function"!=typeof i.default||(Object.keys(i).some(function(t){return"default"!==t&&"__esModule"!==t})&&console.error("named exports are not supported in *.vue files."),n=i=i.default),"function"==typeof n&&(n=n.options),n.__file="F:\\webwork\\BarCode.Mobile\\APAC_ABI_Barcode_Enhancement\\src\\tools\\messageList.vue",n.render=s.render,n.staticRenderFns=s.staticRenderFns,n._scopeId="data-v-2af66df3",n.style=n.style||{},r.forEach(function(t){for(var e in t)n.style[e]=t[e]}),"function"==typeof __register_static_styles__&&__register_static_styles__(n._scopeId,r),t.exports=i,t.exports.el="true",new Vue(t.exports)},647:function(t,e){t.exports={wrapper:{height:"40",backgroundColor:"#da5926"},"w-ipx":{height:"66",backgroundColor:"#da5926"},scrollerWarp:{position:"absolute",top:90,right:0,left:0,bottom:0,backgroundColor:"#ffffff"},scrollerTop156:{top:156},scrollerTop130:{top:130},noInfo:{textAlign:"center",marginTop:"80",color:"#999999",fontSize:"30"},posRelative:{position:"relative"},center:{textAlign:"center"},posBot:{width:"100",height:"100",borderRadius:"100",backgroundColor:"#cccccc",position:"absolute",bottom:"50",right:"50"},topPos10:{position:"absolute",right:"20",bottom:"10",paddingTop:"15",paddingRight:"15",paddingBottom:"15",paddingLeft:"15",color:"#FFFFFF"},topPos:{position:"absolute",right:"20",bottom:"0",paddingTop:"15",paddingRight:"15",paddingBottom:"15",paddingLeft:"15",color:"#FFFFFF"},colorfff:{color:"#ffffff"},color222:{color:"#222222"},color666:{color:"#666666"},color999:{color:"#999999"},pink:{color:"rgb(219,88,38)"},dOrange:{color:"#e16b23"},themeColor:{color:"#e16b23"},blue:{color:"#589acb"},orange:{color:"#f39c1f"},green:{color:"#7aac52"},font22:{fontSize:"22"},font24:{fontSize:"24"},font26:{fontSize:"26"},font28:{fontSize:"28"},font30:{fontSize:"30"},font32:{fontSize:"32"},font40:{fontSize:"40"},iconfont:{fontFamily:"iconfont",fontSize:"40"},"text-deco":{textDecoration:"underline"},"width-70":{width:"75"},"width-150":{width:"150"},"width-210":{width:"210"},"width-280":{width:"280"},"width-350":{width:"350"},"width-420":{width:"420"},"width-490":{width:"490"},"width-560":{width:"560"},"width-630":{width:"630"},"width-750":{width:"750"}}},648:function(t,e){t.exports={xxlist:{flexDirection:"row"},icon:{width:"118",height:"118",borderRadius:"10",backgroundColor:"rgb(243,156,33)",marginTop:"25",marginRight:0,marginBottom:0,marginLeft:"20"},"r-xslist":{width:"612",paddingTop:"30",paddingRight:"20",paddingBottom:"45",paddingLeft:"20",borderBottomColor:"#e6e6e6",borderBottomWidth:"1"},"xx-top":{flexDirection:"row",justifyContent:"space-between"},"xx-text":{textOverflow:"ellipsis",whiteSpace:"nowrap",lines:1,paddingRight:"20",marginTop:"15"},remove:{width:"200",height:"175",lineHeight:"175",backgroundColor:"#c10000",color:"#ffffff",textAlign:"center",fontSize:"32"}}},649:function(t,e,o){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=o(1),n=function(t){return t&&t.__esModule?t:{default:t}}(i),r=(weex.requireModule("modal"),weex.requireModule("animation"));e.default={name:"messageList",components:{WxcMinibar:n.default},props:{lTitle:{type:String,default:"标题"},xslist:{type:Array,default:[]}},data:function(){return{startX:0,endX:0,moveX:0,disX:0,deleteSlider:"",test:""}},methods:{init:function(){},touchStart:function(t,e){1==t.changedTouches.length&&(this.startX=t.changedTouches[0].pageX)},touchMove:function(t,e){if(1==t.changedTouches.length)if(this.moveX=t.changedTouches[0].pageX,this.disX=this.startX-this.moveX,this.disX<0||0==this.disX)this.deleteSlider="translateX(0px)";else if(this.disX>0&&(this.deleteSlider="translateX(-"+5*this.disX+"px)",5*this.disX>=200)){this.deleteSlider="translateX(-200px)";var o=this.$refs["list"+e][0];r.transition(o,{styles:{transform:this.deleteSlider},duration:300,delay:0})}},touchEnd:function(t,e){if(1==t.changedTouches.length){var o=t.changedTouches[0].pageX;this.disX=this.startX-o,5*this.disX<100?this.deleteSlider="translateX(0px)":this.deleteSlider="translateX(-200px)"}var i=this.$refs["list"+e][0];r.transition(i,{styles:{transform:this.deleteSlider},duration:300,delay:0})}},computed:{isipx:function(){return weex&&("iPhone10,3"===weex.config.env.deviceModel||"iPhone10,6"===weex.config.env.deviceModel)},isiphone:function(){return weex&&-1!=weex.config.env.deviceModel.indexOf("iPhone")}},create:function(){}}},650:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{staticClass:["container"]},[t._l(t.xslist,function(e,i){return o("div",{key:e,ref:"list"+i,refInFor:!0,staticClass:["xxlist"],on:{touchstart:function(e){t.touchStart(e,i)},touchmove:function(e){t.touchMove(e,i)},touchend:function(e){t.touchEnd(e,i)}}},[o("div",{staticClass:["icon"]}),o("div",{staticClass:["r-xslist"]},[o("div",{staticClass:["xx-top"]},[o("text",{staticClass:["font32"]},[t._v(t._s(e.title))]),o("text",{staticClass:["font28","color999"]},[t._v(t._s(e.time))])]),o("text",{staticClass:["xx-text","font28","color999"]},[t._v(t._s(e.content))])]),o("text",{staticClass:["remove"]},[t._v("删除")])])}),0==t.xslist.length?o("text",{staticStyle:{textAlign:"center",marginTop:"80px"}},[t._v("没有数据")]):t._e()],2)},staticRenderFns:[]},t.exports.render._withStripped=!0}});