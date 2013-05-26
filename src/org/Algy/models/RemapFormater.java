package org.Algy.models;

import java.util.HashMap;
import java.util.Map.Entry;

public class RemapFormater {
	
	private HashMap<String, String> clz = new HashMap<String, String>();
	private HashMap<String, String> pakages = new HashMap<String, String>();
	private HashMap<String, String> methods = new HashMap<String, String>();
	private HashMap<String, String> fields = new HashMap<String, String>();
	
	public void putClass(String className, String repl)
	{
		clz.put(className.replace('.', '/'), repl);
	}
	public void putPackage(String packageName, String repl)
	{
		String str =
				packageName.replace('.', '/');
		pakages.put(str, repl);
	}
	public void putMethod(String className, String methodName, String [] params, String repl)
	{
		String str = className.replace('.', '/');
		
		String formatted = str + "." + methodName + "(" + formatParamType(params) + ")";
		
		methods.put(formatted, repl);
	}
	public void putMethod(String className, String methodNameAndType,  String repl)
	{
		String str = className.replace('.', '/');
		
		String formatted = str + "." + methodNameAndType;
		
		methods.put(formatted, repl);
	}
	public void putField(String className, String fieldName, String repl)
	{
		String str = className.replace('.', '/');
		
		String formatted = str + "." + fieldName;
		fields.put(formatted, repl);
	}
	
	
	public String formatCommand()
	{
		String result ="";
		
		for(Entry<String, String> entry : clz.entrySet())
		{
			result += "c " + entry.getKey() + "=" + entry.getValue() + "\n";
		}
		
		for(Entry<String, String> entry : pakages.entrySet())
		{
			result += "p " + entry.getKey() + "=" + entry.getValue() + "\n";
		}
		for(Entry<String, String> entry : methods.entrySet())
		{
			result += "m " + entry.getKey() + "=" + entry.getValue() + "\n";
		}
		for(Entry<String, String> entry : fields.entrySet())
		{
			result += "m " + entry.getKey() + "=" + entry.getValue() + "\n";
		}
		return result;
	}
	
	public void clear()
	{
		clz.clear();
		pakages.clear();
		methods.clear();
		fields.clear();
	}
	public static String formatParamType(String [] arr)
	{
		String result = "";
		for(String str : arr)
		{
			String type = new String(str);
			int ed;
			while( (ed = type.lastIndexOf('[')) != -1)
			{
				result += "[";
				type = type.substring(0, ed);
			}
			type = type.trim();
			if("byte".equals(type))
			{
				result+= "B";
			}
			else if("char".equals(type))
			{
				result+= "C";
			}
			else if("double".equals(type))
			{
				result += "D";
			}
			else if("float".equals(type))
			{
				result += "F";
			}
			else if("int".equals(type))
			{
				result += "I";
			}
			else if("long".equals(type))
			{
				result += "J";
			}
			else if("short".equals(type))
			{
				result += "S";
			}
			else if("void".equals(type))
			{
				result += "V";
			}
			else if("boolean".equals(type))
			{
				result += "Z";
			}
			else
			{
				//not primitives
				type = type.replace('.', '/');
				result += "L" + type + ";";
			}
		}
		return result;
		
	}
	
	public HashMap<String, String> getClassMap() {
		return clz;
	}
	public HashMap<String, String> getPakageMap() {
		return pakages;
	}
	public HashMap<String, String> getMethodMap() {
		return methods;
	}
	public HashMap<String, String> getFieldMap() {
		return fields;
	}
}
