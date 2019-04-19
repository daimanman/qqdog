package com.man.qqdog.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.man.qqdog.biz.mapper.MtBtPoCustomMapper;
import com.man.qqdog.client.po.MtBtPo;
import com.man.utils.IdWorker;
import com.man.utils.ReqParam;

@Controller
@RequestMapping("/mtBt")
public class MtBtController extends BaseController {

	@Autowired
	public MtBtPoCustomMapper btMapper;
	
	@Autowired
	public IdWorker idWorker;
	
	public MtBtPo converMtBtPo(ReqParam param) {
		MtBtPo po = new MtBtPo();
		po.dmlDate = param.getStr("dmlDate");
		po.hash = param.getStr("hash");
		po.id = idWorker.nextId();
		po.link = param.getStr("link");
		po.size = param.getStr("size");
		po.sortName = param.getStr("sortName");
		po.tag = param.getStr("tag");
		po.title = param.getStr("title");
		po.torrent = param.getStr("torrent");
		po.tid = param.getInt("tid");
		return po;
	}
	@RequestMapping("/saveData")
	public void saveData(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param =  getParams(request);
		String targetType = param.getStr("targetType");
		btMapper.insert(converMtBtPo(param));
	}
	
	
}
