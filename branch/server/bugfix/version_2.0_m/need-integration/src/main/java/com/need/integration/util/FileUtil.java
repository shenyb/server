package com.need.integration.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class FileUtil {

	private final static int CONNECT_TIME_OUT = 15000;
	private final static int READ_TIME_OUT = 60000;

	private static class TrustAnyTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}
	
	public static byte[] unGzip(InputStream ins) throws IOException{  
		GZIPInputStream gis = new GZIPInputStream(ins);
		ByteArrayOutputStream baos = null;
		byte[] result = null;
		try {
			baos = new ByteArrayOutputStream();
			int b;
			while ((b = gis.read()) != -1) {
				baos.write(b);
			}
			result = baos.toByteArray();
		} finally {
			try{
				gis.close();
				baos.close();
			}catch (Exception e) {}
		}
		return result;
    }  

	public static Document createDocument(File f, String charset) throws Exception {
		FileInputStream fis = new FileInputStream(f) ;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document document;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(fis);
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		} catch (SAXException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			fis.close();
		}
		return document;
	}
	
	public static String buildQuery(Map<String, String> params, String charset)
			throws Exception {
		if (params == null || params.isEmpty()) {
			return null;
		}

		StringBuilder query = new StringBuilder();
		for (String name : params.keySet()) {
			String value = params.get(name);
			if (StringUtil.isBlank(name) && StringUtil.isBlank(value)) {
				if (query.length() > 0) {
					query.append("&");
				}

				query.append(name).append("=").append(
						URLEncoder.encode(value, charset));
			}
		}

		return query.toString();
	}

	public static String doPost(String url, Map<String, String> params, String charset) throws Exception {
		return doPost(url, params, charset, CONNECT_TIME_OUT, READ_TIME_OUT);
	}
	
	public static String doPost(String url, Map<String, String> params, String charset,
			Map<String, File> fileParams, int connectTimeout, int readTimeout)
			throws Exception {
		if ((fileParams == null) || (fileParams.isEmpty())) {
			return doPost(url, params, charset, connectTimeout, readTimeout);
		}
		return doPost(url, params, fileParams, charset, connectTimeout,
				readTimeout);
	}
	
	public static String doPost(String url, Map<String, String> params,
			String charset, int connectTimeout, int readTimeout)
			throws Exception {
		String ctype = "application/x-www-form-urlencoded;charset=" + charset;
		String query = buildQuery(params, charset);
		byte[] content = new byte[0];
		if (query != null) {
			content = query.getBytes(charset);
		}
		return doPost(url, ctype, content, connectTimeout, readTimeout);
	}
	public static String doPost(String url, String contentType, byte[] content,
			int connectTimeout, int readTimeout) throws IOException {
		HttpURLConnection conn = null;
		OutputStream out = null;
		String rsp = null;
		try {
			conn = getConnection(new URL(url), "POST", contentType);
			conn.setConnectTimeout(connectTimeout);
			conn.setReadTimeout(readTimeout);
			out = conn.getOutputStream();
			out.write(content);
			rsp = getResponseAsString(conn, "utf-8");
		} finally {
			if (out != null) {
				out.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}

		return rsp;
	}

	public static String doPost(String url, Map<String, String> params,
			Map<String, File> fileParams, String charset, int connectTimeout,
			int readTimeout) throws Exception {
		String boundary = System.currentTimeMillis() + "";
		HttpURLConnection conn = null;
		OutputStream out = null;
		String rsp = null;
		InputStream fio = null;
		
		try {
			String ctype = "multipart/form-data;charset=" + charset + ";boundary=" + boundary;
			conn = getConnection(new URL(url), "POST", ctype);
			conn.setConnectTimeout(connectTimeout);
			conn.setReadTimeout(readTimeout);
		} catch (Exception e) {
			throw new Exception(e);
		}
		try {
			out = conn.getOutputStream();
			byte[] entryBoundaryBytes = ("\r\n--" + boundary + "\r\n").getBytes(charset);

			if (params != null) for (String key : params.keySet()) {
				byte[] textBytes = getTextEntry(key, params.get(key), charset);
				out.write(entryBoundaryBytes);
				out.write(textBytes);
			}
			if(fileParams != null) for (String key : fileParams.keySet()) {
				File file = fileParams.get(key);
				
				// 文件内容
				fio = new FileInputStream(file);
				ByteArrayOutputStream filecontentOut = new ByteArrayOutputStream();
				int ch;
				while ((ch = fio.read()) != -1) {
					filecontentOut.write(ch);
				}
				byte[] content = filecontentOut.toByteArray();
				byte[] fileBytes = getFileEntry(key, file.getName(),
						getMimeType(content), charset);

				out.write(entryBoundaryBytes);
				out.write(fileBytes);

				content = content == null ? new byte[0] : content;
				out.write(content);
			}
			byte[] endBoundaryBytes = ("\r\n--" + boundary + "--\r\n").getBytes(charset);
			out.write(endBoundaryBytes);
			rsp = getResponseAsString(conn, charset);
		} catch (IOException e) {
			throw new Exception(e);
		} finally {
			if (out != null) {
				out.close();
			}
			if (conn != null) {
				conn.disconnect();
			} 
			if(fio != null) {
				fio.close();
			}
		}
		return rsp;
	}

	/**
     * 
     * @param url
     * @param method POST,GET
     * @param contentType
     * @return
     * @throws IOException
     */
	private static HttpURLConnection getConnection(URL url, String method,
			String contentType) throws IOException {
		HttpURLConnection conn = null;
		if ("https".equals(url.getProtocol())) {
			SSLContext sc = null;
			try {
				sc = SSLContext.getInstance("SSL");
				sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
						new java.security.SecureRandom());
			} catch (Exception e) {
				throw new IOException(e);
			}

			HttpsURLConnection lConn = (HttpsURLConnection) url
					.openConnection();
			lConn.setSSLSocketFactory(sc.getSocketFactory());
			lConn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn = lConn;
		} else {
			conn = (HttpURLConnection) url.openConnection();
		}

		conn.setRequestMethod(method);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Accept", "text/xml,text/javascript,text/html");
		//conn.setRequestProperty("User-Agent", "ISCS-NET");
		conn.setRequestProperty("Content-Type", contentType);
		return conn;
	}
	protected static String getResponseAsString(HttpURLConnection conn,
			String _charset) throws IOException {
		String charset = getResponseCharset(conn.getContentType(), _charset);
		InputStream es = conn.getErrorStream();
		if (es == null) {
			return getStreamAsString(conn.getInputStream(), charset);
		}
		String msg = getStreamAsString(es, charset);
		if (StringUtil.isBlank(msg)) {
			throw new IOException(conn.getResponseCode() + ":"
					+ conn.getResponseMessage());
		}
		throw new IOException(msg);
	}

	private static String getStreamAsString(InputStream stream, String charset)
			throws IOException {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					stream, charset));
			StringWriter writer = new StringWriter();

			char[] chars = new char[256];
			int count = 0;
			while ((count = reader.read(chars)) > 0) {
				writer.write(chars, 0, count);
			}

			return writer.toString();
		} finally {
			if (stream != null)
				stream.close();
		}
	}

	private static String getResponseCharset(String ctype, String _charset) {
		String charset = _charset;
		if (!StringUtil.isBlank(ctype)) {
			String[] params = ctype.split(";");
			for (String param : params) {
				param = param.trim();
				if (!param.startsWith("charset")) {
					continue;
				}
				String[] pair = param.split("=", 2);
				if ((pair.length != 2) || (StringUtil.isBlank(pair[1])))
					break;
				charset = pair[1].trim();
				break;
			}
		}
		return charset;
	}
	private static byte[] getTextEntry(String fieldName, String fieldValue,
			String charset) throws Exception {
		StringBuilder entry = new StringBuilder();
		entry.append("Content-Disposition:form-data;name=\"");
		entry.append(fieldName);
		entry.append("\"\r\nContent-Type:text/plain\r\n\r\n");
		entry.append(fieldValue);
		return entry.toString().getBytes(charset);
	}

	private static byte[] getFileEntry(String fieldName, String fileName,
			String mimeType, String charset) throws Exception {
		StringBuilder entry = new StringBuilder();
		entry.append("Content-Disposition:form-data;name=\"");
		entry.append(fieldName);
		entry.append("\";filename=\"");
		entry.append(fileName);
		entry.append("\"\r\nContent-Type:");
		entry.append(mimeType);
		entry.append("\r\n\r\n");
		return entry.toString().getBytes(charset);
	}

	 public static String getMimeType(byte[] bytes) {
		String suffix = getFileSuffix(bytes);
		String mimeType;
		if ("JPG".equals(suffix)) {
			mimeType = "image/jpeg";
		} else if ("GIF".equals(suffix)) {
			mimeType = "image/gif";
		} else if ("PNG".equals(suffix)) {
			mimeType = "image/png";
		} else if ("BMP".equals(suffix)) {
			mimeType = "image/bmp";
		} else {
			mimeType = "application/octet-stream";
		}
		return mimeType;
	}

	public static String getFileSuffix(byte[] bytes) {
		if ((bytes == null) || (bytes.length < 10)) {
			return null;
		}
		if ((bytes[0] == 71) && (bytes[1] == 73) && (bytes[2] == 70)) {
			return "GIF";
		}
		if ((bytes[1] == 80) && (bytes[2] == 78) && (bytes[3] == 71)) {
			return "PNG";
		}
		if ((bytes[6] == 74) && (bytes[7] == 70) && (bytes[8] == 73)
				&& (bytes[9] == 70)) {
			return "JPG";
		}
		if ((bytes[0] == 66) && (bytes[1] == 77)) {
			return "BMP";
		}
		return null;
	}
}

