// { "framework": "Vue"} 

!function(e){function t(r){if(n[r])return n[r].exports;var o=n[r]={i:r,l:!1,exports:{}};return e[r].call(o.exports,o,o.exports,t),o.l=!0,o.exports}var n={};t.m=e,t.c=n,t.d=function(e,n,r){t.o(e,n)||Object.defineProperty(e,n,{configurable:!1,enumerable:!0,get:r})},t.n=function(e){var n=e&&e.__esModule?function(){return e.default}:function(){return e};return t.d(n,"a",n),n},t.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},t.p="",t(t.s=655)}({341:function(e,t){e.exports={tab1:{height:"80",display:"flex",flexDirection:"row",justifyContent:"space-between",alignItems:"center"},"tab-item":{flex:1,textAlign:"center",fontSize:"30",color:"#999999"},"tab-active":{color:"#f39c1f"}}},342:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default={name:"tab1",components:{},props:{tabs:Array,isActive:{type:Number,default:0}},data:function(){return{}},methods:{},computed:{},created:function(){}}},343:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{staticClass:["tab1"]},e._l(e.tabs,function(t,r){return n("text",{key:r,staticClass:["tab-item"],class:[r==e.isActive?"tab-active":""],on:{click:function(n){e.$emit("itemTap",{item:t,index:r})}}},[e._v(e._s(t.text))])}))},staticRenderFns:[]},e.exports.render._withStripped=!0},655:function(e,t,n){var r,o,s=[];s.push(n(341)),r=n(342);var i=n(343);o=r=r||{},"object"!=typeof r.default&&"function"!=typeof r.default||(Object.keys(r).some(function(e){return"default"!==e&&"__esModule"!==e})&&console.error("named exports are not supported in *.vue files."),o=r=r.default),"function"==typeof o&&(o=o.options),o.__file="F:\\webwork\\BarCode.Mobile\\APAC_ABI_Barcode_Enhancement\\src\\tools\\tab1.vue",o.render=i.render,o.staticRenderFns=i.staticRenderFns,o._scopeId="data-v-4437b004",o.style=o.style||{},s.forEach(function(e){for(var t in e)o.style[t]=e[t]}),"function"==typeof __register_static_styles__&&__register_static_styles__(o._scopeId,s),e.exports=r,e.exports.el="true",new Vue(e.exports)}});