import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.man.utils.YhHttpUtil;

public class TestMt {

	
	@Test
	public void test01() throws IOException {
		Map<String,Object> params = new HashMap();
		params.put("cityName","昆明");
		params.put("cateId","0");
		params.put("areaId","0");
		params.put("sort","");
		params.put("dinnerCountAttrId"," ");
		params.put("page","1");
		params.put("userId","");
		params.put("uuid","9be63463e28b4a2dbe06.1548140116.1.0.0");
		params.put("platform","1");
		params.put("partner","126");
		params.put("originUrl","https");
		params.put("riskLevel","1");
		params.put("optimusCode","1");
		params.put("_token","eJxlj0lvgzAQhf+Lr6BgG0MgN0hpgcRkqUlpqx7YEvYQIEtT9b/XkdJD1dN7883T08wX6JwETBCEOoQiOKUdmAA0giMViGDo+UYhGiIQyQrSeSD+y7A6FkHUbR7A5J1TKGJd+7iRNQecyKqoqeRDvFvMLSbiTUDk8AjIhqHtJ5JU1qM6zYdj2IzifS1x32e5xG+4Bf7vAS+oGS/gWt41vOvwO1P+Cm/o813DXeqeqytDR6OwVmY6HfvzAuLuNJ+2ZuzMWsdaQ7umPnua2wIMcloZx5JR6C78mKqM6MsIy0YZEY2qe3ouhJ3AxlPDTjTJvm6X5wO7WrmNZl6fbTNUWe2UbMKHvi76z/0leM7T1H4Mys0FB0E8X+Smm4XFchYwT/EPyWdjKjpbnAdr0wiwqcx01cUvu4s/rnSS4UBp3ddmtn+U5RSVzTZ6CpOsMaLek6hzepPWzDscLwQmRfTi4Tq/CjuqumXig+8fO/qQEQ==");
		String url = "https://km.meituan.com/meishi/api/poi/getPoiList";
		
		Map<String,String> header = new HashMap<>();
		header.put("Cookie","_lxsdk_cuid=16772e423992-005ffce547e72d-5e442e19-144000-16772e4239ac8; ci=114; rvct=114; _lx_utm=utm_source%3DBaidu%26utm_medium%3Dorganic; mtcdn=K; lsu=; client-id=16febaa9-892f-4a19-9c81-56863b450c46; uuid=9be63463e28b4a2dbe06.1548140116.1.0.0; __mta=256801460.1546563702332.1548139284944.1548140118667.3; _lxsdk_s=168744ac4ca-fbb-9c9-ebd%7C%7C15");
		String content = YhHttpUtil.sendHttpGet(url, params, header);
		System.out.println(content);
	}
}
