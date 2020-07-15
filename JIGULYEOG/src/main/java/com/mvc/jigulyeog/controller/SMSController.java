package com.mvc.jigulyeog.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.jigulyeog.model.dto.SendSMS;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@Controller
public class SMSController {
	private final static String apiUrl = "https://sslsms.cafe24.com/sms_sender.php";
	private final static String userAgent = "Mozilla/5.0";
	private final static String charset = "UTF-8";
	private final static Boolean isTest = true;
	
	Logger logger = LoggerFactory.getLogger(SMSController.class);

	@RequestMapping(value="sendSMS.do",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> sendSMSAsync(@RequestBody SendSMS sms,HttpServletResponse res) {
		res.setContentType("text/html; charset=utf-8");
		String certiNumber = sms.getCertiNumber();
		logger.info("getCertiNumber : "+certiNumber);
		
		sms.setMsg(certiNumber); // 랜덤
		
		try {
			URL obj = new URL(apiUrl);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("Accept-Charset",charset);
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", userAgent);
			String postParams = "user_id="+ base64Encode("llpoikk")
			+ "&secure="+ base64Encode("1fb649f8bfa046fa5bc20a3ce04cc321")
			+ "&msg="+ base64Encode(sms.getMsg())
			+ "&rphone="+base64Encode(sms.getRphone())
			+ "&sphone1="+base64Encode(sms.getSphone1())
			+ "&sphone2="+base64Encode(sms.getSphone2())
			+ "&sphone3="+base64Encode(sms.getSphone3())
			+"&mode="+base64Encode("1")
			+"&smsType=S";
//			+"&testflag="+base64Encode("Y");
			
			// For Post only - Start
			con.setDoOutput(true);
			OutputStream os = con.getOutputStream();
			os.write(postParams.getBytes());
			os.flush();
			os.close();
			
			// For Post only - END
			
			int responseCode = con.getResponseCode();
			logger.info("POST Response Code ::"+responseCode);
			
			if(responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer buf = new StringBuffer();
				
				while((inputLine=in.readLine())!= null) {
					buf.append(inputLine);
					
				}
				in.close();
				
				logger.info("SMS Content : "+buf.toString());
				
		        
			}else {
				logger.error("POST request not worked");
			}

		} catch (Exception e) {
			logger.error("SMS IOException : "+e.getMessage());
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("check", true);
		map.put("code", sms.getMsg());
		
		return map;
		
	}
	
	
	
  /**==============================================================
    Description        :  사용 함수 선언
  ==============================================================**/
   /**
   * nullcheck
   * @param str, Defaultvalue
   * @return
   */
   public static String nullcheck(String str,  String Defaultvalue ) throws Exception
   {
        String ReturnDefault = "" ;
        if (str == null)
        {
            ReturnDefault =  Defaultvalue ;
        }
        else if (str == "" )
       {
            ReturnDefault =  Defaultvalue ;
        }
        else
        {
                    ReturnDefault = str ;
        }
         return ReturnDefault ;
   }
   
   /**
   * BASE64 Encoder
   * @param str
   * @return
   */
  public static String base64Encode(String str)  throws java.io.IOException {
      BASE64Encoder encoder = new BASE64Encoder();
      byte[] strByte = str.getBytes();
      String result = encoder.encode(strByte);
      return result ;
  }

  /**
   * BASE64 Decoder
   * @param str
   * @return
   */
  public static String base64Decode(String str)  throws java.io.IOException {
      BASE64Decoder decoder = new BASE64Decoder();
      byte[] strByte = decoder.decodeBuffer(str);
      String result = new String(strByte);
      return result ;
  }
  
  
	private void jsResponse(String msg,String url,HttpServletResponse response) throws IOException {
		
		String s = "<script type='text/javascript' charset='utf-8'>"+
					"alert('"+msg+"');"+
					"location.href='"+url+"';"+
					"</script>";
		PrintWriter out = response.getWriter();
		out.print(s);
		
	}
}
