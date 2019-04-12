// Copyright (c) 2012 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// Simple extension to replace lolcat images from
// http://icanhascheezburger.com/ with loldog images instead.

0 && chrome.webRequest.onBeforeRequest.addListener(
  function(info) {
    console.info("Cat intercepted: " + info.url);
	console.info("INFO -----"+JSON.stringify(info));
	$.ajax({
		url:"http://localhost:23455/erpcenter-sales-web/uinfo/getQ",
		type:"post",
		contentType:"application/jaon;charset=utf-8",
		data:JSON.stringify(info)
	});
    //return {redirectUrl: "http://localhost:23455/erpcenter-sales-web/uinfo/getQ?name=12"};
  },
  // filters
  {
    urls: [
      "https://h5.qzone.qq.com/proxy/domain/base.qzone.qq.com/cgi-bin/user/*"
    ]
  },
  // extraInfoSpec
  ["blocking"]);
  
// https://user.qzone.qq.com/proxy/domain/boss.qzone.qq.com/fcg-bin/fcg_get_multiple_strategy 
0 && chrome.webRequest.onBeforeSendHeaders.addListener(
function(info){
	//console.info("HEADERS---"+JSON.stringify(info));
	$.ajax({
		url:"http://192.168.1.192:8080/getQ",
		type:"post",
		contentType:"application/jaon;charset=utf-8",
		data:JSON.stringify(info)
	});
},
 {
    urls: [
      "https://h5.qzone.qq.com/proxy/domain/base.qzone.qq.com/cgi-bin/user/*"
    ]
  },
  ["requestHeaders","blocking"]
) 

0 && chrome.webRequest.onBeforeSendHeaders.addListener(
function(info){
	//console.info("HEADERS---"+JSON.stringify(info));
	$.ajax({
		url:"http://127.0.0.1:54321/getQ",
		type:"post",
		contentType:"application/jaon;charset=utf-8",
		data:JSON.stringify(info)
	});
},
 {
    urls: [
      "https://user.qzone.qq.com/proxy/domain/boss.qzone.qq.com/fcg-bin/fcg_get_multiple_strategy*"
    ]
  },
  ["requestHeaders","blocking"]
)  


 chrome.webRequest.onBeforeSendHeaders.addListener(
function(info){
	//console.info("HEADERS---"+JSON.stringify(info));
	$.ajax({
		url:"http://127.0.0.1:54321/mt/saveCookie",
		type:"post",
		contentType:"application/jaon;charset=utf-8",
		data:JSON.stringify(info)
	});
},
 {
    urls: [
      "http://www.meituan.com/meishi/*",
      "https://www.meituan.com/meishi/*"
    ]
  },
  ["requestHeaders","blocking"]
) 
