
package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.codehaus.jettison.json.JSONArray;
import org.junit.Test;

public class DiskUtil {
	
	
	
	//结局了。。
	@Test
	public void indexFileCast(){
		//读取生活指数文件，保存成String的格式。
		String path = "D:\\Documents\\Tencent Files\\996245864\\FileRecv\\共享盘福\\生活指数_utf-8.txt";
//		readFileByLines(path);
		String rr = readFileByChars(path);
//		readFileByLines1(path);
//		System.out.println(rr);
		processRS(rr);
		
		//这里读取的字符串有缺失？，String能够存多大的字符数组？
//		String indexStr = read(path);
//		FileUtil.saveCSVToLocal(indexStr, "tmp.txt");
//		System.out.println(indexStr);
		//处理String字符串，得到目标结果
//		String standardResult = processRS(indexStr);
		
		
		
		
		
	}

	
	
	/**
	 * 传进来的时候是什么格式？
	 * 			站号	站名	最小相对湿度	等级	指数类别
	 * 			54624	黄骅	9999	2	舒适度指数
	 * 需要什么样的格式：
	 * 			站点号|发布日期|指数类型|级别|提示语|描述
				53781|2018-01-10 08:00|晨练指数|3|较适宜|请适当减少运动时间，降低运动强度。
				53781|2018-01-10 08:00|感冒指数|5|易发|天冷风大，易感冒，注意防护。
	 * @param indexStr
	 * @return
	 */
	private String processRS(String indexStr) {
		String[] hh = indexStr.split("站号\\s+站名\\s+最小相对湿度\\s+等级\\s+指数类别\\s+");
//		System.out.println(hh);
		String srcFileName = hh[0];
		
//		System.out.println("srcFileName ---- > " + srcFileName);
		StringBuffer sb = new StringBuffer();
		for(int i = 1 ; i < hh.length; i ++){
			String[] hhh = hh[i].split("\n");
			for(int j = 0 ; j < hhh.length; j++){
//				System.out.println(hhh[j]);
				String[] ss = hhh[j].split("\\s+");
//				System.out.println(ss[0] + "---" + ss[1] +"---"+ ss[2] + "---" + ss[3] + "---" +ss[4] );
				
				//在这里组织数据   //这里用回调函数感觉会看的很牛X
				sb.append(ss[0]).append("|").append("发布日期").append("|")
				.append(ss[4]).append("|").append(ss[3]).append("|")
				.append("提示语").append("|").append("描述").append("\r\n");
				
			}
		}
		System.out.println(sb.toString());
		
		
		
		return null;
	}

	   public static void readFileByLines(String fileName) {
	        File file = new File(fileName);
	        BufferedReader reader = null;
	        try {
	            System.out.println("以行为单位读取文件内容，一次读一整行：");
	            reader = new BufferedReader(new FileReader(file));
	            String tempString = null;
	            int line = 1;
	            // 一次读入一行，直到读入null为文件结束
	            while ((tempString = reader.readLine()) != null) {
	                // 显示行号
	                System.out.println("line " + line + ": " + tempString);
	                line++;
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e1) {
	                }
	            }
	        }
	    }
	

	
    /**
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件
     */
    public static String readFileByChars(String fileName) {
        File file = new File(fileName);
        Reader reader = null;
        
        StringBuffer sb = new StringBuffer();
        
        try {
//            System.out.println("以字符为单位读取文件内容，一次读多个字节：");
            // 一次读多个字符
            char[] tempchars = new char[1024];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName),"utf-8");
            // 读入多个字符到字符数组中，charread为一次读取字符数
            while ((charread = reader.read(tempchars)) != -1) {
                // 同样屏蔽掉\r不显示
                if ((charread == tempchars.length)
                        && (tempchars[tempchars.length - 1] != '\r')) {
//                    System.out.print(tempchars);
                    sb.append(tempchars);
                } else {
                    for (int i = 0; i < charread; i++) {
                        if (tempchars[i] == '\r') {
                            continue;
                        } else {
                        	sb.append(tempchars[i]);
//                            System.out.print(tempchars[i]);
                        }
                    }
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
		return sb.toString();
    }

}
