import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.man.utils.YhHttpUtil;

public class TestDp {

	@Test
	public void testDp() throws IOException {
		Map<String,Object> param = new HashMap<>();
		Map<String,String> h = new HashMap<>();
		h.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
		h.put("Cookie","cy=267; cye=kunming; _lx_utm=utm_source%3DBaidu%26utm_medium%3Dorganic; _lxsdk_cuid=1699a1600227f-0410e1acf56ab2-5e442e19-144000-1699a160023c8; _lxsdk=1699a1600227f-0410e1acf56ab2-5e442e19-144000-1699a160023c8; _hc.v=c21b0842-65a8-55b6-1ed5-72a766f63b62.1553068327; s_ViewType=10; _lxsdk_s=1699a160023-bb2-de8-91b%7C%7C214");
		String url = "http://www.dianping.com/kunming/ch10/r2027";
		String content = YhHttpUtil.sendHttpGet(url, null,h);
		System.out.println(content);
	}
}
