package myproject01.service.movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;
import myproject01.domain.dto.movie.Movie;
import myproject01.domain.dto.movie.ResultJSONData;
import myproject01.domain.dto.movie.naver.Item;
import myproject01.domain.dto.movie.naver.NaverSearchResponse;


@Service
public class MovieAPIServiceImpl implements MovieAPIService {
	
	@Autowired
	HttpServletRequest request;
	
	@Override
	public List<Movie> getDailyBoxOfficeList() {
		// boolean isJson;
		String targetDt=
				request.getParameter("targetDt")==""?"20210721":request.getParameter("targetDt");
		String itemPerPage=
				request.getParameter("itemPerPage")==""?"20":request.getParameter("itemPerPage");
		String multiMovieYn=null;
		String repNationCd=null;
		String wideAreaCd=null;
		
		String key="f5eef3421c602c6cb7ea224104795888";
		KobisOpenAPIRestService kobisService=new KobisOpenAPIRestService(key);
		
		try {
			String jsonResult=kobisService.getDailyBoxOffice(true, targetDt, itemPerPage, multiMovieYn, repNationCd, wideAreaCd);
			// System.out.println(jsonResult);   // 문자열로 출력해보기
			// JSON 문자열 데이터를 -> class로 매핑
			ObjectMapper mapper=new ObjectMapper();
			ResultJSONData resultData=mapper.readValue(jsonResult, ResultJSONData.class);
			// System.out.println(resultData);   // class로 출력해보기
			
			return resultData.getBoxOfficeResult().getDailyBoxOfficeList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Item> searchNaverMovie(String searchText) {
		String clientId = "wlo2R4TVp_YQwXIENFDz"; //애플리케이션 클라이언트 아이디값"
        String clientSecret = "yqOeUirjR7"; //애플리케이션 클라이언트 시크릿값"

        
        String text = null;  
        try {
            text = URLEncoder.encode(searchText, "UTF-8");   // 검색어
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }


        String apiURL = "https://openapi.naver.com/v1/search/movie?query=" + text;    // json 결과
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);
        // System.out.println(responseBody);
        
        // jsondata:responseBody -> class 데이터로 매핑
        ObjectMapper mapper=new ObjectMapper();
        NaverSearchResponse result=null;
        try {
        	result=mapper.readValue(responseBody, NaverSearchResponse.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        
		return result.getItems();   // NaverSearchResponse에서 items만 가져오기
	}

	private String get(String apiURL, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiURL);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
	}
	
	private HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

}














