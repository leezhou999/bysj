package bysj.zjl.util;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
/**
 * Word2PDF工具包
 * @author leezhou
 *
 */
public class ToPdf {
	public static int office2PDF(String sourceFile, String destFile) {
		try {
			File inputFile = new File(sourceFile);
			if (!inputFile.exists()) {
				return -1;// 找不到源文件, 则返回-1
			}
 
			// 如果目标路径不存在, 则新建该路径
			File outputFile = new File(destFile);
			if (!outputFile.getParentFile().exists()) {
				outputFile.getParentFile().mkdirs();
			}
			
			// connect to an OpenOffice.org instance running on port 8100
			OpenOfficeConnection connection = new SocketOpenOfficeConnection(
					"127.0.0.1", 8100);
			connection.connect();
 
			// convert
			DocumentConverter converter = new OpenOfficeDocumentConverter(
					connection);
			converter.convert(inputFile, outputFile);
 
			// close the connection
			connection.disconnect();
 
			return 0;
		} catch (ConnectException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
 
		return 1;
	}
	public void OtherToPdf(File sourceFile,File pdfFile) {
		if(sourceFile.exists()) {
			if(!pdfFile.exists()) {
				try {
					office2PDF(sourceFile.toString(), pdfFile.toString());
					System.out.println("第二步：转换为PDF格式	路径" + pdfFile.getPath());
				} catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {
					e.printStackTrace();
					System.out.println("读取文件失败");
					throw e;
				} catch (Exception e){
					e.printStackTrace();
					try {
						throw e;
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			} else {
				System.out.println("已转换为PDF，无需再次转换");
			}
		} else {
			System.out.println("要转换的文件不存在");
		} 
	}
}
