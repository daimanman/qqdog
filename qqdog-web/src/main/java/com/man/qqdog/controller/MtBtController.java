package com.man.qqdog.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.man.qqdog.biz.mapper.MtBtPoCustomMapper;
import com.man.qqdog.client.po.MtBtPo;
import com.man.qqdog.client.po.MtMeishiInfoPo;
import com.man.utils.IdWorker;
import com.man.utils.ObjectUtil;
import com.man.utils.ReqParam;

@Controller
@RequestMapping("/mtBt")
public class MtBtController extends BaseController {

	@Autowired
	public MtBtPoCustomMapper btMapper;
	
	@Autowired
	public IdWorker idWorker;
	
	public Logger logger = LoggerFactory.getLogger(MtBtController.class);
	
	public MtBtPo converMtBtPo(ReqParam param) {
		MtBtPo po = new MtBtPo();
		po.dmlDate = param.getStr("dmlDate");
		po.hash = ObjectUtil.getStr(param,"hash",100);
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
	int totalNum = 0;
	@RequestMapping("/saveData")
	public void saveData(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ReqParam param =  getParams(request);
		String targetType = param.getStr("targetType");
		MtBtPo po = converMtBtPo(param);
		String hash = po.hash;
		int num = btMapper.getByHash(hash);
		if(num > 0) {
			totalNum++;
			logger.error("totalNum={} exists_bt {} ",totalNum,JSON.toJSONString(po));
		}else {
			btMapper.insert(po);
		}
		
	}
	
	@RequestMapping("/deleteData")
	public void deleteData(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<String> datas = btMapper.getClearDatas();
		int size = 500;
		List<Long> ids = new ArrayList<>(size);
		int num = 0;
		for(String idStr : datas) {
			String[] idArray = idStr.split(",");
			for(int i = 1;i<idArray.length;i++) {
				num++;
				ids.add(ObjectUtil.parseLong(idArray[i]));
				if(ids.size() >= size) {
					logger.info("delete datas size {} ",num);
					btMapper.deleteBatch(ids);
					ids = new ArrayList<>(size);
				}
			}
		}
		
	}
	
	@RequestMapping("/delfile")
	public void delfile(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<String> datas = btMapper.getClearDatas();
		List<Long> ids = new ArrayList<>(1000);
		FileOutputStream fos = new FileOutputStream(new File("D:\\1.sql"));
		StringBuffer sb = new StringBuffer();
		int num = 0;
		for(String idStr : datas) {
			String[] idArray = idStr.split(",");
			for(int i = 1;i<idArray.length;i++) {
				sb.append(idArray[i]).append("\n");
			}
		}
		
		IOUtils.write(sb.toString(),fos);
		IOUtils.closeQuietly(fos);
		sendDefaultJson(response,"is ok");
		
	}
	
	
}
