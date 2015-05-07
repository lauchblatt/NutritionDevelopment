package de.ur.infwiss.nutrition.defaultconsumer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MultivaluedMap;

public class ExtendHeaderRequestFilter implements ClientRequestFilter {

	private String targetUrl;
	
    private String headerName;
    
    private String headerValue;
    
    public ExtendHeaderRequestFilter(String targ, String hn) {
    	this.targetUrl=targ;
    	this.headerName=hn;
    }
	
	public String getHeaderValue() {
		return headerValue;
	}

	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}

	@Override
	public void filter(ClientRequestContext ctx) throws IOException {
		MultivaluedMap<String, Object> map = ctx.getHeaders();
        Set<String> s = map.keySet();
		PrintWriter wr=new PrintWriter(new FileOutputStream(new File("/home/sistbien/temp/filterlog"),false));
		Iterator<String> i=s.iterator();
		while (i.hasNext()) {
			wr.println(i.next());
		}
		wr.close();
	}

}
