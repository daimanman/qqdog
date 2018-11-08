package com.man.qqdog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;


public class BaseController {
	/**
	 * 向前端发送json数据
	 * 
	 * @param response
	 * @param targetObj
	 * @throws IOException
	 */
	protected void sendJson(HttpServletResponse response, Object targetObj, SerializerFeature... sf)
			throws IOException {
		response.setContentType("application/json;charset=utf-8");
		OutputStream os = response.getOutputStream();
		IOUtils.write(JSON.toJSONString(targetObj, sf), os, "utf-8");
		IOUtils.closeQuietly(os);
	}

	protected void sendJsonWithDateFormat(HttpServletResponse response, Object targetObj, String dateFormat,
			SerializerFeature... sf) throws IOException {
		response.setContentType("application/json;charset=utf-8");
		OutputStream os = response.getOutputStream();
		IOUtils.write(JSON.toJSONStringWithDateFormat(targetObj, dateFormat, sf), os, "utf-8");
		IOUtils.closeQuietly(os);
	}

}
