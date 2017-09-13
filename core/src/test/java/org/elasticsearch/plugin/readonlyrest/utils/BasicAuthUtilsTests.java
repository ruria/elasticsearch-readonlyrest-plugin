/*
 *    This file is part of ReadonlyREST.
 *
 *    ReadonlyREST is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    ReadonlyREST is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with ReadonlyREST.  If not, see http://www.gnu.org/licenses/
 */

package org.elasticsearch.plugin.readonlyrest.utils;

import static org.junit.Assert.*;

import java.util.Base64;
import java.util.Optional;

import org.elasticsearch.plugin.readonlyrest.utils.BasicAuthUtils;
import org.elasticsearch.plugin.readonlyrest.utils.BasicAuthUtils.BasicAuth;
import org.junit.Test;

public class BasicAuthUtilsTests {

	@Test
	public void basicAuthValidTest() {
		String user = "admin";
		String passwd = "passwd:";
		byte[] authToken = ((String) user + ":" + passwd).getBytes();
		String base64Value = new String(Base64.getEncoder().encodeToString(authToken));
		Optional<BasicAuth> basicAuth = BasicAuthUtils.getBasicAuthFromString(base64Value);
		
		assertTrue( user.equals(basicAuth.get().getUserName()));
		assertTrue( passwd.equals(basicAuth.get().getPassword()));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void basicAuthInvalidTest() {
		String user = "admin";
		String passwd = "";
		byte[] authToken = ((String) user + ":" + passwd).getBytes();
		String base64Value = new String(Base64.getEncoder().encodeToString(authToken));
		BasicAuthUtils.getBasicAuthFromString(base64Value);
	}


}
