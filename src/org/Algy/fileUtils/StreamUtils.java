package org.Algy.fileUtils;

import java.io.IOException;
import java.io.InputStream;

public class StreamUtils {
	public static String streamToString(InputStream stream) throws IOException
	{
		byte [] b = new byte[4096];
		int n;
		StringBuffer strBuf = new StringBuffer();
		while((n = stream.read(b)) != -1)
		{
			strBuf.append(new String(b, 0, n));
		}
		return strBuf.toString();
	}
}