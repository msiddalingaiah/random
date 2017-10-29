
package com.madhu.ws;

import java.security.Principal;

public abstract class RestrictedService {
	private Principal principal;

	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}

	// TODO add authentication/authorization abstract methods
}
