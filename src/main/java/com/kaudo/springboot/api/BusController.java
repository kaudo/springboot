package com.kaudo.springboot.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BusController {

	static String URL="http://ws.bus.go.kr/api/rest/busRouteInfo/";
	static String SERVICE_KEY="GYF%2BDjKmLF7uE0CchJUGgbFS2%2BxLFwXxmD5bGzyCCdpyLhYHsjlTHQHvVBlIGjKLQc%2BsmvaI3zxR42spMFuLww%3D%3D";
	// apikey
	// GYF%2BDjKmLF7uE0CchJUGgbFS2%2BxLFwXxmD5bGzyCCdpyLhYHsjlTHQHvVBlIGjKLQc%2BsmvaI3zxR42spMFuLww%3D%3D
	// https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15000193
/*
getStaionsByRouteList	노선별 경유 정류소 조회 서비스	1000	확인
2	getRouteInfoItem	노선 기본정보 조회	1000	확인
3	getRoutePathList	노선의 지도상 경로를 리턴한다.	1000	확인
4	getBusRouteList		노선번호에 해당하는 노선 목록 조회
*/


	@RequestMapping(value="/getBusRouteList")
	public Map<String,Object> getBusRouteList(HttpServletResponse response, HttpServletRequest request) throws Exception {
/*
String strUrl="";
		try {
			URL url = new URL(strUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(5000); //서버에 연결되는 Timeout 시간 설정
			con.setReadTimeout(5000); // InputStream 읽어 오는 Timeout 시간 설정
			con.addRequestProperty("x-api-key","ㅁㄴㅇㄹ"); //key값 설정
			con.setRequestMethod("GET");
//URLConnection에 대한 doOutput 필드값을 지정된 값으로 설정한다. URL 연결은 입출력에 사용될 수 있다. URL 연결을 출력용으로 사용하려는 경우 DoOutput 플래그를 true로 설정하고, 그렇지 않은 경우는 false로 설정해야 한다. 기본값은 false이다.

			con.setDoOutput(false);
			StringBuilder sb = new StringBuilder();
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				//Stream을 처리해줘야 하는 귀찮음이 있음.
				BufferedReader br = new BufferedReader(
						new InputStreamReader(con.getInputStream(), "utf-8"));
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line).append("\n");
				}
				br.close();
				System.out.println("" + sb.toString());
			} else {
				System.out.println(con.getResponseMessage());
			}

		} catch (Exception e) {
			System.err.println(e.toString());
		}
*/
		/*
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonObject.toString());
		response.flushBuffer();
		*/
		HttpURLConnection conn=(HttpURLConnection)new URL(URL+"getBusRouteList?ServiceKey="+SERVICE_KEY+"&strSrch="+request.getParameter("strSrch")).openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());

		String xml = sb.toString();
		JSONObject jObject = org.json.XML.toJSONObject(xml);
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		Object json = mapper.readValue(jObject.toString(), Object.class);

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<>();
		map = objectMapper.readValue(jObject.toString(), new TypeReference<HashMap<String,Object>>(){});
		Map<String,Object> mapServiceResult=(Map<String,Object>)map.get("ServiceResult");
		Map<String,Object> mapHeader=(Map<String,Object>)mapServiceResult.get("msgHeader");
		Map<String,Object> mapBody=(Map<String,Object>)mapServiceResult.get("msgBody");
		List<Map<String,Object>> listItem=(List<Map<String,Object>>)mapBody.get("itemList");

		Map<String, Object> mapResult = new HashMap<>();
		mapResult.put("list",listItem);
		mapResult.put("header",mapHeader);
		//mapResult.put("mapServiceResult",mapServiceResult);
		//mapResult.put("mapServiceResult",mapServiceResult);
		return mapResult;
	}

	@RequestMapping(value="/getStaionByRoute")
	public Map<String,Object> getStaionByRoute(HttpServletResponse response, HttpServletRequest request) throws Exception{
		HttpURLConnection conn=(HttpURLConnection)new URL(URL+"getStaionByRoute?ServiceKey="+SERVICE_KEY+"&busRouteId="+request.getParameter("busRouteId")).openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		Map<String, Object> map=new ObjectMapper().readValue(org.json.XML.toJSONObject(sb.toString()).toString(), new TypeReference<HashMap<String,Object>>(){});
		Map<String,Object> mapServiceResult=(Map<String,Object>)map.get("ServiceResult");
		Map<String,Object> mapHeader=(Map<String,Object>)mapServiceResult.get("msgHeader");
		Map<String,Object> mapBody=(Map<String,Object>)mapServiceResult.get("msgBody");
		List<Map<String,Object>> listItem=(List<Map<String,Object>>)mapBody.get("itemList");

		Map<String, Object> mapResult = new HashMap<>();
		mapResult.put("list",listItem);
		mapResult.put("header",mapHeader);
		mapResult.put("message",mapServiceResult.get("comMsgHeader"));
		return mapResult;
	}


	@RequestMapping(value="/getRouteInfo")
	public Map<String,Object> getRouteInfo(HttpServletResponse response, HttpServletRequest request) throws Exception{
		HttpURLConnection conn=(HttpURLConnection)new URL(URL+"getRouteInfo?ServiceKey="+SERVICE_KEY+"&busRouteId="+request.getParameter("busRouteId")).openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		Map<String, Object> map=new ObjectMapper().readValue(org.json.XML.toJSONObject(sb.toString()).toString(), new TypeReference<HashMap<String,Object>>(){});
		Map<String,Object> mapServiceResult=(Map<String,Object>)map.get("ServiceResult");
		Map<String,Object> mapHeader=(Map<String,Object>)mapServiceResult.get("msgHeader");
		Map<String,Object> mapBody=(Map<String,Object>)mapServiceResult.get("msgBody");
		//List<Map<String,Object>> listItem=(List<Map<String,Object>>)mapBody.get("itemList");

		Map<String, Object> mapResult = new HashMap<>();
		mapResult.put("result",map);
		mapResult.put("body",mapBody);

		//mapResult.put("list",listItem);
		mapResult.put("header",mapHeader);
		mapResult.put("message",mapServiceResult.get("comMsgHeader"));
		return mapResult;
	}


	@RequestMapping(value="/getRoutePath")
	public Map<String,Object> getRoutePath(HttpServletResponse response, HttpServletRequest request) throws Exception{
		HttpURLConnection conn=(HttpURLConnection)new URL(URL+"getRoutePath?ServiceKey="+SERVICE_KEY+"&busRouteId="+request.getParameter("busRouteId")).openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		Map<String, Object> map=new ObjectMapper().readValue(org.json.XML.toJSONObject(sb.toString()).toString(), new TypeReference<HashMap<String,Object>>(){});
		Map<String,Object> mapServiceResult=(Map<String,Object>)map.get("ServiceResult");
		Map<String,Object> mapHeader=(Map<String,Object>)mapServiceResult.get("msgHeader");
		Map<String,Object> mapBody=(Map<String,Object>)mapServiceResult.get("msgBody");
		List<Map<String,Object>> listItem=(List<Map<String,Object>>)mapBody.get("itemList");

		Map<String, Object> mapResult = new HashMap<>();
		mapResult.put("result",map);
		mapResult.put("list",listItem);
		mapResult.put("header",mapHeader);
		mapResult.put("message",mapServiceResult.get("comMsgHeader"));
		return mapResult;
	}

}
