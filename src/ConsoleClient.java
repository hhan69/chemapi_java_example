
/*
 * Copyright (c) 2017 ChemADVISOR, Inc. All rights reserved.
 * Licensed under The MIT License (MIT)
 * https://opensource.org/licenses/MIT
 */

import java.io.IOException;
import java.net.URLEncoder;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

//Add httpclient jar
import org.apache.http.client.ClientProtocolException;

//Add jersey jar
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class ConsoleClient {
	public static void main(String[] args) throws ClientProtocolException, IOException {

		// set base address
		String baseAddress = "https://sandbox.chemadvisor.io/chem/rest/v2/";

		// set accept header: MediaType.APPLICATION_JSON,
		// MediaType.APPLICATION_XML
		String mediaType = MediaType.APPLICATION_JSON;

		// set app_key header
		String appKey = "your_app_key";

		// set app_id header
		String appId = "your_app_id";

		// set resource
		String resource = "regulatory_lists";

		// set query parameters:q, limit, offset
		String q = URLEncoder.encode("{\"tags.tag.name\":\"Government Inventory Lists\"}", "UTF-8");
		Integer limit = 10;
		Integer offset = 0;

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);

		WebResource service = client.resource(UriBuilder.fromUri(baseAddress).path(resource).queryParam("q", q)
				.queryParam("limit", limit).queryParam("offset", offset).build());

		WebResource.Builder builder = service.header("app_key", appKey).header("app_id", appId);

		String response = builder.accept(mediaType).get(String.class);

		System.out.println(response);
	}
}
